/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mx.nibble.middleware.web.util;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.dbf.project.LoadProjectDAO;
import com.mx.nibble.dbf.project.LoadProjectDAOImpl;
import com.mx.nibble.dbf.project.LoadProjectPhaseDAO;
import com.mx.nibble.dbf.project.LoadProjectPhaseDAOImpl;
import com.mx.nibble.dbf.project.LoadProjectTaskDAO;
import com.mx.nibble.dbf.project.LoadProjectTaskDAOImpl;
import com.mx.nibble.dbf.project.Project;
import com.mx.nibble.domain.CProject;
import com.mx.nibble.domain.CProjectType;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author victor
 */
public class FileUploadAction extends ActionSupport implements
        SessionAware, ServletRequestAware, ServletResponseAware {
    
    Logger logger = LoggerFactory.getLogger(FileUploadAction.class);
    @SessionTarget
    Session sessiondb;

    @TransactionTarget
    Transaction transaction;
    
    private Project project = new Project();
    private CProject cproject = new CProject();
    
    CProjectType projectType = new CProjectType();
    LoadProjectDAO lProject = new LoadProjectDAOImpl(); 
    LoadProjectTaskDAO lProjectTask = new LoadProjectTaskDAOImpl();
    
    LoadProjectPhaseDAO lProjectPhase = new LoadProjectPhaseDAOImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int BUFFER_SIZE = 2 * 1024;

	private int id = -1;

	private File upload;
	private String name;
	private List<String> names;
	private String uploadFileName;
	private String uploadContentType;
	private String savePath;
	private int chunk;
	private int chunks;
	
	private String result;
        
        private final String UPLOAD_DIR = "/opt/cyssa/erp/import/obras";
        
        private Map session;
        private HttpServletRequest request;
        private HttpServletResponse response;
    
    

	public String upload() throws Exception {
		//String dstPath = UPLOAD_DIR + "\\" +  this.getName();// ServletActionContext.getServletContext().getRealPath(this.getSavePath()) + "\\" + this.getName();
            
                String dstPath = UPLOAD_DIR + "/" + uploadFileName.toLowerCase().replaceAll("\\s+","");//this.getName();
		File dstFile = new File(dstPath);
                logger.debug("VARIABLE dstPath : " +dstPath);
                logger.debug("VARIABLE dstFile : " +dstFile);
		// El archivo ya existe (cargado un archivo del mismo nombre)
		if (chunk == 0 && dstFile.exists()) {
			dstFile.delete();
			dstFile = new File(dstPath);
		}

		saveUploadFile(this.upload, dstFile);
		logger.debug("ARCHIVO:" + uploadFileName + " TIPO：" + uploadContentType + " "	+ chunk + " " + chunks);
		if (chunk == chunks - 1) {
			logger.debug("Completado: un archivo completamente cargado");
		}
                logger.debug("return SUCCESS");
		return SUCCESS;
	}
	
	private void saveUploadFile(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			if (dst.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(dst, true),
						BUFFER_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
			}
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);

			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String submit() {
		String filePath = ServletActionContext.getServletContext().getRealPath(this.getSavePath());
		logger.debug("RUTA DE ARCHIVO： " + filePath);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		result =  "";
		int count = Integer.parseInt(request.getParameter("uploader_count"));
		for (int i = 0; i < count; i++) {
			uploadFileName = request.getParameter("uploader_" + i + "_name");
			name = request.getParameter("uploader_" + i + "_tmpname");
			logger.debug(uploadFileName + " " + name);
			try {
				//do something with file;
				result += uploadFileName + "importación está completo. <br />";  
			} catch(Exception e) {
				result += uploadFileName + "Importación fallida:" + e.getMessage() + ". <br />";
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
        
        public String cargar(){
            
            try{
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                logger.debug(dateFormat.format(date));
                
                
                
                String path = UPLOAD_DIR + "/";
                
                List<String> results = new ArrayList<String>();
                File[] files = new File(path).listFiles();
                String prefijoArchivo = "";

                for (File file : files) {
                    if (file.isFile()) {
                        logger.debug("NOMBRE ARCHIVO ::: "+file.getName().replaceAll("\\s+",""));
                        String fileName = file.getName().replaceAll("\\s+","").toLowerCase();
                        String last4;
                        if (fileName == null || fileName.length() < 4) {
                            last4 = fileName;
                        } else {
                            //Se obtiene el sufijo del archivo requerido para la carga de acuerdo al mapeo de datos
                            last4 = fileName.substring(fileName.length() - 4);
                            //Se obtiene el nombre base del archivo
                            
                            String prePrefijoArchivo = fileName.substring(0,fileName.length() - 5);
                            logger.debug("prePrefijoArchivo::: "+prePrefijoArchivo +" prePrefijoArchivo LENGTH "+prePrefijoArchivo.length());
                            logger.debug("prefijoArchivo::: "+prefijoArchivo +" prefijoArchivo LENGTH "+prefijoArchivo.length());
                            if(prePrefijoArchivo.length() >= prefijoArchivo.length())
                                prefijoArchivo = prePrefijoArchivo;
                            logger.debug("Prefijo Archivo::: "+prefijoArchivo);
                        }
                        if( last4.equalsIgnoreCase("3.DBF") || last4.equalsIgnoreCase("P.DBF") ||
                                last4.equalsIgnoreCase("N.DBF") || last4.equalsIgnoreCase("C.DBF") ||
                                last4.equalsIgnoreCase("F.DBF") )
                        {
                            logger.debug("NOMBRE Archivo a procesar ::: "+prefijoArchivo);
                            results.add(file.getName().toLowerCase());
                        }
                    }
                }
                
                
                
                String fileName = prefijoArchivo.toLowerCase();
                logger.debug("Prefijo Archivo 2 ::: "+prefijoArchivo);
                //Carga parametros de session
                long ad_client_id = (Long)this.getSession().get("ad_client_id");
                long ad_org_id = (Long)this.getSession().get("ad_org_id");
                long ad_user_id = (Long)this.getSession().get("ad_user_id");
                
                       
                lProject.setAd_client_id(ad_client_id); 
                lProject.setAd_org_id(ad_org_id);
                lProject.setAd_user_id(ad_user_id);
                lProject.loadProject(path, fileName);        
                logger.debug("CARGANDO PROYECTO ");
                //lProject.exportCSVProject(path + fileName + "DatGral.csv");
                lProject.insertProject();

                logger.debug("CARGANDO PHASE ");
                lProjectPhase.setAd_client_id(ad_client_id); 
                lProjectPhase.setAd_org_id(ad_org_id);
                lProjectPhase.setAd_user_id(ad_user_id);                        
                lProjectPhase.loadProjectPhase(path, fileName, lProject.getCProject().getCProjectId());
                //lProjectPhase.exportCSVProjectPhases(path + fileName + "Partidas.csv");
                logger.debug("INSERTANDO PROYECT PHASE ");
                Long projectId=lProject.getCProject().getCProjectId();
                logger.debug("INSERTANDO PROYECT ID "+projectId);
                lProjectPhase.insertProjectPhases(projectId);
                
                
                long pid = lProject.getCProject().getCProjectId();
                
                logger.debug("CARGANDO PARTIDAS ");

                
                lProjectTask.setAd_client_id(ad_client_id); 
                lProjectTask.setAd_org_id(ad_org_id);
                lProjectTask.setAd_user_id(ad_user_id);        
                lProjectTask.loadProjectTask(path, fileName, lProjectPhase.getCprojectphase(), (double) pid );
                //lProjectTask.exportrCSVProjectTask(path + fileName + "Partidas.csv");
                lProjectTask.insertProjectTask();
                
                

                /*//Carga Datos Generales
                CargaDatosGenerales lData = new CargaDatosGenerales();
                lData.CargaDatosGenerales(path, fileName);
                //lData.exportarCSVDatosGenerales(path + fileName + "DatGral.csv");
                lData.setAd_client_id(ad_client_id);
                lData.setAd_org_id(ad_org_id);
                lData.setAd_user_id(ad_user_id);
                
                lData.InsertaDatosGenerales();
                logger.debug("Obraid: " + lData.getDatGral().getObraId());

                CargaPartidas lPartidas = new CargaPartidas();
                lPartidas.setAd_client_id(ad_client_id);
                lPartidas.setAd_org_id(ad_org_id);
                lPartidas.setAd_user_id(ad_user_id);
                lPartidas.CargaPartidas(path, fileName, lData.getDatGral().getObraId());
                //lPartidas.exportarCSVPartidas(path + fileName + "Partidas.csv");
                lPartidas.InsertaPartidas(lData.getDatGral().getObraId());
                //Carga conceptos
                CargaConceptos lConceptos = new CargaConceptos();
                lConceptos.setAd_client_id(ad_client_id);
                lConceptos.setAd_org_id(ad_org_id);
                lConceptos.setAd_user_id(ad_user_id);
                lConceptos.CargaConceptos(path, fileName, lPartidas.get(), lData.getDatGral().getObraId());
                lConceptos.InsertaConceptos();
                //lConceptos.exportarCSVConceptos(path + fileName + "Conceptos.csv");
                */
                date = new Date();
                logger.debug(dateFormat.format(date));
                for (File file : files) {
                    if (file.isFile()) {
                        logger.debug("NOMBRE ARCHIVO ::: "+file.getName());
                        try{
 
                            //File file = new File("c:\\logfile20100131.log");

                            if(file.delete()){
                                    logger.info(file.getName() + " is deleted!");
                            }else{
                                    logger.info("Delete operation is failed.");
                            }

                        }catch(Exception e){

                                e.printStackTrace();

                        }
                    }
                }
                addActionMessage("Importacion de Informacion Completada de Manera exitosa");
            }
            catch(Exception e){
                addActionError("Error" + e.getMessage());
                e.printStackTrace();
                return ERROR;
            }
            return SUCCESS;
        }

	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<String> getNames() {
		return names;
	}

	

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public int getChunk() {
		return chunk;
	}

	public void setChunk(int chunk) {
		this.chunk = chunk;
	}

	public int getChunks() {
		return chunks;
	}

	public void setChunks(int chunks) {
		this.chunks = chunks;
	}


	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

    
    /**
     * @return the upload
     */
    public File getUpload() {
        return upload;
    }

    /**
     * @param upload the upload to set
     */
    public void setUpload(File upload) {
        this.upload = upload;
    }

    /**
     * @return the session
     */
    public Map getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Map sess) {
        this.session = sess;
    }

    /**
     * @return the request
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * @return the response
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setServletRequest(HttpServletRequest req) {
         this.request = req;
    }

    @Override
    public void setServletResponse(HttpServletResponse resp) {
        this.response = resp;
    }

}