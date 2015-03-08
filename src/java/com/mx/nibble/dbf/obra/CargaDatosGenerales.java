package com.mx.nibble.dbf.obra;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.domain.*;
import com.mx.nibble.middleware.dao.ProjectTypeDAO;
import com.mx.nibble.middleware.dao.ProjectTypeDAOImpl;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xBaseJ.DBF;
import org.xBaseJ.fields.*;
import org.xBaseJ.xBaseJException;
//import static org.xBaseJ.Util.setxBaseJProperty;


public class CargaDatosGenerales{
    Logger logger = LoggerFactory.getLogger(CargaDatosGenerales.class);
    
    @SessionTarget
    Session session;	
    @TransactionTarget
    Transaction transaction;             

    private DatosGenerales datGral = new DatosGenerales();
    private static String EOL_STRING = System.getProperty("line.separator");
    private long ad_client_id;
    private long ad_org_id;
    private long ad_user_id;
    private DBF classDB;
        
    public void CargaDatosGenerales(String dbfPath, String dbfFileName) throws xBaseJException,IOException{
        logger.debug(dbfPath);
        logger.debug(dbfFileName);
        
        try{
                        
            classDB=new DBF(dbfPath+dbfFileName+"c.dbf",DBF.READ_ONLY);
            
            //Define fields
            CharField obra  = (CharField) classDB.getField("DEPREN1");
            CharField concurso  = (CharField) classDB.getField("CONNUM");
            DateField fecha_concurso = (DateField) classDB.getField("CONFEC");
            CharField descripcion  = (CharField) classDB.getField("OBRDES");
            CharField ubicacion = (CharField) classDB.getField("OBRUBI");
            NumField obra_presupuesto = (NumField) classDB.getField("OBRPRE");      
            DateField fecha_inicio = (DateField) classDB.getField("OBRFINI");
            DateField fecha_termino = (DateField) classDB.getField("OBRFTER");
            NumField presupuesto_version = (NumField) classDB.getField("VERSION");

            for (int i = 1; i <= classDB.getRecordCount(); i++)
            {                
                classDB.read();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");                
                datGral.setObra(obra.get().trim());
                datGral.setConcurso(concurso.get().trim());
                if (fecha_concurso.get() == null){ datGral.setFecha_concurso(formatter.parse(fecha_concurso.get()));}
                datGral.setDescripcion(descripcion.get().trim());
                datGral.setUbicacion(ubicacion.get().trim());
                datGral.setObra_presupuesto(Double.parseDouble(obra_presupuesto.get()));
                datGral.setFecha_inicio(formatter.parse(fecha_inicio.get()));
                datGral.setFecha_termino(formatter.parse(fecha_termino.get()));
                datGral.setPresupuesto_version(presupuesto_version.get());
            }                  
            
            //return getDatGral();                    
        } catch(Exception e){
            e.printStackTrace();
            //return null;
        }
        finally{
        classDB.close();
        }
        
    }

    /**
     * @return the datGral
     */
    public DatosGenerales getDatGral() {
        return datGral;
    }

    /**
     * @param datGral the datGral to set
     */
    public void setDatGral(DatosGenerales datGral) {
        this.datGral = datGral;
    }
    public void exportarCSVDatosGenerales(String cvsFileName) throws IllegalAccessException{
    try {
        FileWriter writer = new FileWriter(cvsFileName);                             
        DatosGenerales currentElement = new DatosGenerales();
        currentElement = datGral;  
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
    
  public void InsertaDatosGenerales() 
  {      
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
      Connection conn = null;    
      try{
          
          
          
          
            //Connection conn = null;    
            // connect to the database
            conn = connectToDatabase();
            // Validate if record exists or get maxid + 1     
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);        
            ResultSet rs = st.executeQuery("SELECT * FROM adempiere.c_obra where obra = '" + datGral.getObra().trim() + "'");
            logger.debug("SELECT * FROM adempiere.c_obra where obra = '" + datGral.getObra().trim() + "'");
            if (rs.first()){                
                    datGral.setObraId(rs.getDouble("c_obra_id"));
                    rs.close();
                    st.close();                                
            }else{
                rs = st.executeQuery("SELECT COALESCE(max(c_obra_id),1000000) c_obra_id FROM adempiere.c_obra");
                while ( rs.next() )
                {
                     datGral.setObraId(rs.getDouble("c_obra_id") + 1);
                }
                rs.close();
                //Insertamos datos de la obra    
                logger.debug("DATOS DE LA OBRA "+datGral.getObraId());
                String statement;
                statement = "INSERT INTO adempiere.c_obra (c_obra_id, ad_client_id, ad_org_id, obra, concurso," +
                "fecha_concurso, descripcion, ubicacion, obra_presupuesto,fecha_inicio, fecha_termino,"+
                "presupuesto_version,ad_user_id,createdby,updatedby)" +
                "VALUES (" + datGral.getObraId() + "," + this.getAd_client_id() + "," + this.getAd_org_id() + ",'" + datGral.getObra() + "','" + 
                datGral.getConcurso() + "',"; 
                    if(datGral.getFecha_concurso()!=null) 
                    statement = statement + "'" + formatter.format(datGral.getFecha_concurso()) + "'";
                    else
                    statement = statement + "null";                
                statement = statement + ",'" + datGral.getDescripcion() + "','" +
                datGral.getUbicacion() + "'," + datGral.getObra_presupuesto() + ",";
                    if (datGral.getFecha_inicio()!=null)
                    statement = statement + "'" + formatter.format(datGral.getFecha_inicio()) + "'";
                    else 
                    statement = statement + "null";
                statement = statement + ",";
                    if(datGral.getFecha_termino()!=null)
                    statement = statement + "'" + formatter.format(datGral.getFecha_termino())+ "'" ;
                    else 
                    statement = statement + "null";
                statement = statement + "," + datGral.getPresupuesto_version() + "," + 
                        this.getAd_user_id() + "," + this.getAd_user_id() + "," + this.getAd_user_id()+")";                               
                st.execute(statement);                        
                st.close();     
                //conn.commit();                                                   
                    CProjectType projectType = new CProjectType();
                    projectType.setCProjectTypeId(datGral.getObraId().longValue());
                    projectType.setAdClientId(1000000);
                    //projectType.setAdClientId(this.getAd_client_id());
                    projectType.setAdOrgId(1000000);
                    projectType.setIsactive('Y');
                    //projectType.setAdOrgId(this.getAd_org_id());                    
                    projectType.setCreated(Calendar.getInstance().getTime());
                    projectType.setCreatedby(this.getAd_client_id());
                    projectType.setUpdated(Calendar.getInstance().getTime());
                    projectType.setUpdatedby(this.getAd_client_id());
                    projectType.setName(datGral.getObra().replace('\0', ' ').replace("\\x00", " "));
                    //projectType.setName("Prueba");
                    projectType.setDescription(datGral.getDescripcion().replace('\0', ' ').replace("\\x00", " "));
                    //projectType.setDescription("Prueba");
                    projectType.setProjectcategory('N');
                    ProjectTypeDAO projecttypeDAO = new ProjectTypeDAOImpl();
                    projecttypeDAO.saveOrUpdate(projectType);
            }        
        }
        catch (SQLException se) {            
            se.printStackTrace();
            try{
              conn.rollback();
            }
            catch(Exception e){
              e.printStackTrace();
            }
          
        }
        finally{
          try{
          conn.close();
          }
          catch(Exception e){
          e.printStackTrace();
          }
      }
  }
  
  public Connection connectToDatabase()
  {
      String driverClassHB="";
      String urlHB="";
      String userHB="";
      String passwordHB="";
      Properties prop = new Properties();
	InputStream input = null;
      try{
                input = this.getClass().getClassLoader().getResourceAsStream("customDB.properties");
                // load a properties file
                prop.load(input);
                driverClassHB = prop.getProperty("hibernate.connection.driver_class");
                urlHB = prop.getProperty("hibernate.connection.url");
                userHB = prop.getProperty("hibernate.connection.username");
                passwordHB = prop.getProperty("connection.password");
 
		
          /*Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
          driverClassHB = cfg.getProperty("hibernate.connection.driver_class");
          urlHB = cfg.getProperty("hibernate.connection.url");
          userHB = cfg.getProperty("hibernate.connection.username");
          passwordHB = cfg.getProperty("connection.password");*/
          logger.debug("SE CARGA EL DRIVER "+driverClassHB);
          
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
    Connection conn = null;
    try
    {
      Class.forName(driverClassHB);
      //String url = urlHB;
      conn = DriverManager.getConnection(urlHB,userHB, passwordHB);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      //System.exit(1);
    }
    return conn;
  }

    /**
     * @return the ad_client_id
     */
    public long getAd_client_id() {
        return ad_client_id;
    }

    /**
     * @param ad_client_id the ad_client_id to set
     */
    public void setAd_client_id(long ad_client_id) {
        this.ad_client_id = ad_client_id;
    }

    /**
     * @return the ad_org_id
     */
    public long getAd_org_id() {
        return ad_org_id;
    }

    /**
     * @param ad_org_id the ad_org_id to set
     */
    public void setAd_org_id(long ad_org_id) {
        this.ad_org_id = ad_org_id;
    }

    /**
     * @return the ad_user_id
     */
    public long getAd_user_id() {
        return ad_user_id;
    }

    /**
     * @param ad_user_id the ad_user_id to set
     */
    public void setAd_user_id(long ad_user_id) {
        this.ad_user_id = ad_user_id;
    }
}