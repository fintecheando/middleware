package com.mx.nibble.dbf.conceptos;

import com.mx.nibble.dbf.partidas.CargaPartidas;
import com.mx.nibble.dbf.partidas.Partida;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import org.xBaseJ.DBF;
import org.xBaseJ.fields.*;
import org.xBaseJ.xBaseJException;
import java.util.ArrayList;
import java.util.Properties;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.ext.XLogger.Level;
import org.xBaseJ.Util.*;


public class CargaConceptos{
    public ArrayList<Conceptos> conceptos;
    private static String EOL_STRING = System.getProperty("line.separator");
    private long ad_client_id;
    private long ad_org_id;
    private long ad_user_id;
    Logger logger = LoggerFactory.getLogger(CargaConceptos.class);

    public void CargaConceptos(String dbfPath, String dbfFileName, ArrayList<Partida> partidas, Double obra_id) throws xBaseJException{
        try{
            //Definir conceptos como un ArrayList de tipo Conceptos
            conceptos = new ArrayList<Conceptos>();
            //Read DBF File Name ended in "F"
            DBF classDB=new DBF(dbfPath+dbfFileName+"f.dbf",DBF.READ_ONLY);
            //Define fields of table <F>
            NumField cantidad = (NumField) classDB.getField("CANTIDAD");
            NumField costo_unitario = (NumField) classDB.getField("COSTO");
            CharField clave_partida  = (CharField) classDB.getField("NOMBRE");
            CharField clave_concepto  = (CharField) classDB.getField("COMPONENTE");
            NumField concepto_prefijo_id  = (NumField) classDB.getField("PREFCOMP");                
            NumField importe_total = (NumField) classDB.getField("IMPORTE");                
            //Elemento para iterar las partidas
            Partida partida = new Partida();            
            //Elemento para almacenar conceptos            
            for(int i = 0; i< partidas.size(); i++){
                partida = partidas.get(i);
                //Iterar partidas para encontrar los conceptos de cada una de las clave_partida                                 
                    //Iterate each DataBase record, create a ConstructionParty element and add it into the ArrayList
                    classDB.gotoRecord(1);
                    for (int j = 1; j <= classDB.getRecordCount(); j++)
                    {                
                        Conceptos concepto = new Conceptos();
                        classDB.gotoRecord(j);
                        if (classDB.deleted() == false) {
                            if(partida.getClave().toString().trim().equals(clave_partida.get().toString().trim()))
                            {                                
                                concepto.setObra_id(obra_id);
                                concepto.setPartida_id(partida.getPartida_id());
                                concepto.setCantidad(Double.parseDouble(cantidad.get()));
                                concepto.setCosto_unitario(Double.parseDouble(costo_unitario.get()));
                                concepto.setClave_partida(clave_partida.get().trim());
                                concepto.setClave_concepto(clave_concepto.get().trim());
                                concepto.setConcepto_prefijo_id(Double.parseDouble(concepto_prefijo_id.get()));
                                concepto.setImporte_total(Double.parseDouble(importe_total.get()));
                                conceptos.add(concepto);
                            }                            
                        }
                    }                  
            }
            classDB.close();
            //Fields for DBF "P"
            DBF classDBP =new DBF(dbfPath+"/"+dbfFileName+"p.dbf",DBF.READ_ONLY);
            //Define fields
            MemoField descripcion = (MemoField) classDBP.getField("DESCRIPCIO");
            CharField unidad  = (CharField) classDBP.getField("UNIDAD");
            CharField clave_conceptop  = (CharField) classDBP.getField("NOMBRE");
            NumField concepto_prefijo_idp  = (NumField) classDBP.getField("PREFIJO");
            for (int k = 1; k <= classDBP.getRecordCount(); k++)
            {                                    
                classDBP.read();
                if (classDB.deleted() == false) {
                    for(int h = 0; h< conceptos.size(); h++){
                        Conceptos currentElement = new Conceptos();
                        currentElement = conceptos.get(h);
                        if (clave_conceptop.get().toString().trim().equals(currentElement.getClave_concepto().toString().trim()) &&
                            Double.parseDouble(concepto_prefijo_idp.get()) == currentElement.getConcepto_prefijo_id()){
                            if (unidad.get() != null && unidad.get() != ""){
                                currentElement.setUnidad(unidad.get().trim());
                            }
                            currentElement.setDescripcion(descripcion.get().trim().replace("'",""));
                            conceptos.set(h, currentElement);
                        }                        
                    }
                }
            }
            classDBP.close();
        } catch(Exception e){
            e.printStackTrace();
            //return null;
        }      
    }

   public void InsertaConceptos() throws SQLException 
    {
        try{  
        Double maxId = 0.0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ResultSet rs = null;
        Connection conn = null;    
        conn = connectToDatabase();        
        for(int i = 0; i < conceptos.size(); i++){           
            // connect to the database
            Conceptos currentElement = new Conceptos();
            currentElement = conceptos.get(i);  
            // Validate if record exists or get maxid + 1                 
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);                    
            rs = st.executeQuery("SELECT * FROM adempiere.c_concepto where c_obra_id = " + currentElement.getObra_id() + 
                " and c_partida_id = " + currentElement.getPartida_id() + " and clave_partida = '" + 
                currentElement.getClave_partida().trim() + "' and clave_concepto = '" + currentElement.getClave_concepto() + "'");
            if (rs.first()){                
                logger.debug("Concepto ya existente: " + currentElement.getClave_concepto()+ " | " + rs.getString("clave_partida") + "," + rs.getString("clave_concepto"));
                rs.close();
            }else{            
                rs = st.executeQuery("SELECT COALESCE(max(c_concepto_id),1000000)  c_concepto_id FROM adempiere.c_concepto");
                
                while ( rs.next() )
                {
                    maxId = rs.getDouble("c_concepto_id") + 1;
                }
                rs.close();                                                
                //Insertamos datos del concepto
                String statement;
                statement = "INSERT INTO adempiere.c_concepto (c_concepto_id, ad_client_id, ad_org_id, " +
                        "c_obra_id, c_partida_id, clave_partida, clave_concepto, concepto_prefijo_id, " +
                        "descripcion, unidad, cantidad, costo_unitario, importe_total, ad_user_id ) VALUES (";
                statement = statement + maxId + "," + this.getAd_client_id() + "," + this.getAd_org_id() + "," + currentElement.getObra_id()+
                        "," + currentElement.getPartida_id() + ",'" + currentElement.getClave_partida() + "','" +
                        currentElement.getClave_concepto() + "'," + currentElement.getConcepto_prefijo_id()+
                        ",'" + currentElement.getDescripcion() + "','" + currentElement.getUnidad() + "'," + 
                        currentElement.getCantidad() + "," + currentElement.getCosto_unitario() + "," + 
                        currentElement.getImporte_total() + "," +this.getAd_user_id()+")";                       
                logger.debug(""+i);
                logger.debug(statement);
                st.execute(statement);
                rs.close();
                st.close();
            }    
        }        
    }                       
    catch (SQLException se) {
      logger.error(se.getMessage());
      se.printStackTrace();
    }
  }
    
    public void exportarCSVConceptos(String cvsFileName) throws IllegalAccessException{
        try {
            FileWriter writer = new FileWriter(cvsFileName);                       
            for(int i = 0; i < conceptos.size(); i++){
                Conceptos currentElement = new Conceptos();
                currentElement = conceptos.get(i);  
                //Write headers                
                if (i==0){
                    for (java.lang.reflect.Field field : currentElement.getClass().getDeclaredFields()) {
                        writer.append(field.getName().toString());
                        writer.append("|");
                    }                     
                    writer.append(EOL_STRING);
                }                                
                //Write records 
                for (java.lang.reflect.Field field : currentElement.getClass().getDeclaredFields()) {
                     writer.append((field.get(currentElement) != null) ? field.get(currentElement).toString().replaceAll("(\\r|\\n)", "") : "");
                     writer.append("|");
                } 
                writer.append(EOL_STRING);                       
            }
            writer.flush();
            writer.close();     
        } catch (IOException ex) {
            ex.printStackTrace();//Logger.getLogger(CargaPartidas.class.getName()).log(Level.SEVERE, null, ex);
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