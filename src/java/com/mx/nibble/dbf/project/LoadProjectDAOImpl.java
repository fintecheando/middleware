/**
 * Nibble
 * @author Abraham Lopez Partida
 * Date: December 17, 2014
 */

package com.mx.nibble.dbf.project;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.domain.CProject;
import com.mx.nibble.domain.CProjectType;
import com.mx.nibble.middleware.dao.ProjectDAO;
import com.mx.nibble.middleware.dao.ProjectDAOImpl;
import com.mx.nibble.middleware.dao.ProjectTypeDAO;
import com.mx.nibble.middleware.dao.ProjectTypeDAOImpl;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xBaseJ.DBF;
import org.xBaseJ.fields.CharField;
import org.xBaseJ.fields.DateField;
import org.xBaseJ.fields.NumField;
import org.xBaseJ.xBaseJException;

 public class LoadProjectDAOImpl implements LoadProjectDAO {
    
    Logger logger = LoggerFactory.getLogger(LoadProjectDAOImpl.class);
    
    @SessionTarget
    Session session;	
    @TransactionTarget
    Transaction transaction;             

    private Project project = new Project();
    private CProject cproject = new CProject();
    
    CProjectType projectType = new CProjectType();
    ProjectTypeDAO projecttypeDAO = new ProjectTypeDAOImpl();  
    
    ProjectDAO projectDAO = new ProjectDAOImpl();

    private static String EOL_STRING = System.getProperty("line.separator");
    private long ad_client_id;
    private long ad_org_id;
    private long ad_user_id;
    private DBF classDB;
        
    public void loadProject(String dbfPath, String dbfFileName) throws xBaseJException,IOException{
        logger.debug(dbfPath);
        logger.debug(dbfFileName);    
        try{                        
            logger.debug("SE IMPORTAN DATOS DE "+dbfPath+dbfFileName+"c.dbf");    
            classDB=new DBF(dbfPath+dbfFileName+"c.dbf",DBF.READ_ONLY);            
            //Define fields
            CharField projectName  = (CharField) classDB.getField("DEPREN1");
            CharField tender  = (CharField) classDB.getField("CONNUM");
            DateField dateTender = (DateField) classDB.getField("CONFEC");
            CharField description  = (CharField) classDB.getField("OBRDES");
            CharField location = (CharField) classDB.getField("OBRUBI");
            NumField plannedamt = (NumField) classDB.getField("OBRPRE");      
            DateField dateContract = (DateField) classDB.getField("OBRFINI");
            DateField dateFinish = (DateField) classDB.getField("OBRFTER");
            NumField note = (NumField) classDB.getField("VERSION");

            for (int i = 1; i <= classDB.getRecordCount(); i++)
            {                
                classDB.read();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");                
                project.setProjectName(projectName.get().trim());
                project.setTender(tender.get().trim());
                if (dateTender.get() == null){ project.setDateTender(formatter.parse(dateTender.get()));}
                project.setDescripcion(description.get().trim());
                project.setLocation(location.get().trim());
                project.setPlannedAmt(Double.parseDouble(plannedamt.get()));
                project.setDateContract(formatter.parse(dateContract.get()));
                project.setDateFinish(formatter.parse(dateFinish.get()));
                project.setNote(note.get());
            }                  
            
        } catch(Exception e){
            e.printStackTrace();
        }
        finally{
        classDB.close();
        }       
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public CProject getCProject() {
        return cproject;
    }

    public void setCProject(CProject cproject) {
        this.cproject = cproject;
    }    
    
    
    public void exportCSVProject(String cvsFileName) throws IllegalAccessException{
    try {
        FileWriter writer = new FileWriter(cvsFileName);                             
        Project currentElement = new Project();
        currentElement = project;  
        //Write headers                           
        for (java.lang.reflect.Field field : currentElement.getClass().getDeclaredFields()) {
            writer.append(field.getName().toString());
            writer.append("|");
        }                     
        writer.append(EOL_STRING);

        for (java.lang.reflect.Field field : currentElement.getClass().getDeclaredFields()) {
             writer.append((field.get(currentElement) != null) ? field.get(currentElement).toString().replaceAll("(\\r|\\n)", "") : "");
             writer.append("|");
        } 
        writer.append(EOL_STRING);                       
        writer.flush();
        writer.close();     
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
    }
    
    public void insertProject() 
    {  
        Long maxProjectTypeId = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");                          
            
            logger.debug(":::::::NOMBRE DEL PROYECTO::::: "+project.projectName);
            List<CProjectType> lsProjectType = new ArrayList();
            lsProjectType = projecttypeDAO.searchByProjectName(project.projectName);                                 
            if (lsProjectType.size()>0){
                logger.debug("Error C_ProjectType already exists:" + project.projectName);
                maxProjectTypeId = lsProjectType.get(0).getCProjectTypeId();
            }else {
                //Request max CProjectTypeId 
                logger.debug("Insert C_ProjectType :" + project.projectName);
                maxProjectTypeId = projecttypeDAO.maxProjectTypeId();
                projectType.setCProjectTypeId(maxProjectTypeId);
                projectType.setAdClientId(this.getAd_client_id());
                projectType.setAdOrgId(this.getAd_org_id());
                projectType.setIsactive('Y');
                projectType.setCreated(Calendar.getInstance().getTime());
                projectType.setCreatedby(this.getAd_user_id());
                projectType.setUpdated(Calendar.getInstance().getTime());
                projectType.setUpdatedby(this.getAd_user_id());
                projectType.setName(project.getProjectName().replace('\0', ' ').replace("\\x00", " "));
                projectType.setDescription(project.getDescription().replace('\0', ' ').replace("\\x00", " "));
                projectType.setHelp("Concurso: " + project.getTender().replace('\0', ' ').replace("\\x00", " ") +
                        ((project.getDateTender() != null) ? " Fecha Concurso: "  + formatter.format(project.getDateTender()) : "") +
                        " Ubicacion: "  + project.getLocation().replace('\0', ' ').replace("\\x00", " "));
                projectType.setProjectcategory('N');
                projecttypeDAO.saveOrUpdate(projectType);                
            }

            
            List<CProject> lsProject = projectDAO.searchByProjectName(project.projectName);
            if (lsProject.size()>0){
                logger.debug("Error C_Project already exists:" + project.projectName);
                cproject.setCProjectId(lsProject.get(0).getCProjectId());
            }else {
                    logger.debug("Insert  C_Project" + project.projectName);
                    Long maxProjectId = projectDAO.maxProjectId();
                    cproject.setCProjectId(maxProjectId);
                    cproject.setAdClientId(this.getAd_client_id());
                    cproject.setAdOrgId(this.getAd_org_id());
                    cproject.setIsactive('Y');
                    cproject.setCreated(Calendar.getInstance().getTime());
                    cproject.setCreatedby(this.getAd_user_id());
                    cproject.setUpdated(Calendar.getInstance().getTime());
                    cproject.setUpdatedby(this.getAd_user_id());
                    cproject.setValue(project.getProjectName());
                    cproject.setName(project.getProjectName());
                    cproject.setDescription(project.getDescription().replace('\0', ' ').replace("\\x00", " "));
                    cproject.setNote("Concurso: " + project.getTender().replace('\0', ' ').replace("\\x00", " ") +
                        ((project.getDateTender() != null) ? " Fecha Concurso: "  + formatter.format(project.getDateTender()) : "") +
                        " Ubicacion: "  + project.getLocation().replace('\0', ' ').replace("\\x00", " "));
                    cproject.setIssummary('N');
                    cproject.setAdUserId(this.getAd_user_id());
                    cproject.setCCurrencyId(130); // Default MXN
                    cproject.setIscommitment('Y');
                    cproject.setPlannedamt(new BigDecimal(project.getPlannedAmt()));
                    cproject.setPlannedqty(BigDecimal.ZERO);
                    cproject.setPlannedmarginamt(BigDecimal.ZERO);
                    cproject.setCommittedamt(BigDecimal.ZERO);
                    cproject.setDatecontract(project.getDateContract());
                    cproject.setDatefinish(project.getDateFinish());
                    cproject.setProcessed('N');
                    cproject.setCProjecttypeId(maxProjectTypeId);
                    cproject.setCommittedqty(BigDecimal.ZERO);
                    cproject.setInvoicedamt(BigDecimal.ZERO);
                    cproject.setInvoicedqty(BigDecimal.ZERO);
                    cproject.setProjectbalanceamt(BigDecimal.ZERO);
                    cproject.setIscommitceiling('N');
                    cproject.setProjinvoicerule('-');
                    cproject.setProjectlinelevel('T');
                    projectDAO.saveOrUpdate(cproject);
                }                            
        } catch(Exception e){
            e.printStackTrace();
        }            
    }
        
    public long getAd_client_id() {
        return ad_client_id;
    }

    public void setAd_client_id(long ad_client_id) {
        this.ad_client_id = ad_client_id;
    }

    public long getAd_org_id() {
        return ad_org_id;
    }

    public void setAd_org_id(long ad_org_id) {
        this.ad_org_id = ad_org_id;
    }

    public long getAd_user_id() {
        return ad_user_id;
    }

    public void setAd_user_id(long ad_user_id) {
        this.ad_user_id = ad_user_id;
    }

    @Override
    public void saveOrUpdateProject(CProject project) {
        try
        {
          this.session.saveOrUpdate(project);
        } catch (Exception e) {
          this.transaction.rollback();
          e.printStackTrace();
        }
    }

    @Override
    public List<CProject> listProject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CProject listProjectById(Long projectId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProject(Long projectId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}