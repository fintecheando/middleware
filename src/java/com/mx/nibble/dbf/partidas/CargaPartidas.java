package com.mx.nibble.dbf.partidas;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;
import org.xBaseJ.DBF;
import org.xBaseJ.fields.*;
import org.xBaseJ.xBaseJException;

public class CargaPartidas {
    private ArrayList<Partida> partida;
    private static String EOL_STRING = System.getProperty("line.separator");
    private long ad_client_id;
    private long ad_org_id;
    private long ad_user_id;
    
    org.slf4j.Logger logger = LoggerFactory.getLogger(CargaPartidas.class);

    public void CargaPartidas(String dbfPath, String dbfFileName,  Double obra_id) throws xBaseJException{
        try{
            //Define partida as an ArrayList of ConstructionParty
            partida = new ArrayList<Partida>();
            String clave_anterior = "";
            Integer nivel_anterior = 0;
            //Read DBF File Name ended in "3"
            DBF classDB=new DBF(dbfPath+dbfFileName+"3.dbf",DBF.READ_ONLY);
         
            //Define fields of table <3>
                NumField id = (NumField) classDB.getField("ID");
                NumField idUnico = (NumField) classDB.getField("IDUNICO");
                CharField nivel  = (CharField) classDB.getField("NIVEL");
                CharField signo  = (CharField) classDB.getField("SIGNO");
                CharField clave  = (CharField) classDB.getField("NOMBRE");
                //CharField clave  = (CharField) classDB.getField("CLAVEUSUAR");
                MemoField descripcion = (MemoField) classDB.getField("DESCRIPCIO");
                CharField unidad  = (CharField) classDB.getField("UNIDAD");
                NumField cantidad = (NumField) classDB.getField("CANTIDAD");
                NumField importeTotal = (NumField) classDB.getField("COSTO");
                DateField fechaInicio = (DateField) classDB.getField("FINI");
                DateField fechaTermino = (DateField) classDB.getField("FTER");

                //Iterate each DataBase record, create a ConstructionParty element and add it into the ArrayList
                for (int i = 1; i <= classDB.getRecordCount(); i++)
                {                
                    classDB.read();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                    Partida currentElement = new Partida();                                        
                    if ((Integer.parseInt(nivel.get()) >= nivel_anterior) && (signo.get().toString().trim().equals("-"))){
                        currentElement.setClave_partida(clave_anterior);
                    }else{
                        clave_anterior = clave.get().replace("'","").trim();
                    }                
                    nivel_anterior = Integer.parseInt(nivel.get());
                    currentElement.setIdUnico(Double.parseDouble(idUnico.get()));
                    currentElement.setObra_id(obra_id);
                    currentElement.setNivel(Integer.parseInt(nivel.get()));
                    currentElement.setSigno(signo.get().replace("'","").trim());
                    currentElement.setClave(clave.get().replace("'","").replace("|",".").trim());
                    currentElement.setDescripcion(descripcion.get().replace("'","").trim());
                    currentElement.setUnidad(unidad.get().replace("'","").trim());
                    currentElement.setCantidad(Double.parseDouble(cantidad.get()));
                    currentElement.setImporte_total(Double.parseDouble(importeTotal.get()));    
                    currentElement.setFecha_inicio(formatter.parse(fechaInicio.get()));
                    currentElement.setFecha_termino(formatter.parse(fechaTermino.get()));
                    partida.add(currentElement);
                }                  
                classDB.close();
                
            //Fields for DBF "P"
                DBF classDBP =new DBF(dbfPath+"/"+dbfFileName+"p.dbf",DBF.READ_ONLY);
                //Define fields
                CharField nombrep  = (CharField) classDBP.getField("NOMBRE");
                NumField precio_unitario = (NumField) classDBP.getField("PUNIT");
                for (int i = 1; i <= classDBP.getRecordCount(); i++)
                {                                    
                    classDBP.read();
                    for(int j = 0; j< partida.size(); j++){
                        Partida currentElement = new Partida();
                        currentElement = partida.get(j);
                        //Comparte Table3.nombre == TableP.nombre                        
                        if (nombrep.get().toString().trim().equals(currentElement.getClave().toString().trim())){
                            if (precio_unitario.get() != null && precio_unitario.get() != ""){
                                currentElement.setPrecio_unitario(Double.parseDouble(precio_unitario.get()));
                                partida.set(j, currentElement);
                            }
                        }                        
                    }
                }
                classDBP.close();
            
            //Fields for DBF "N"
                DBF classDBN =new DBF(dbfPath+"/"+dbfFileName+"n.dbf",DBF.READ_ONLY);
                //Define fields
                CharField nombren  = (CharField) classDBN.getField("NOMBRE");
                NumField indirectos = (NumField) classDBN.getField("INDIRECTOS");
                NumField indirectos_campo = (NumField) classDBN.getField("INDIRECTO2");
                NumField financiamiento = (NumField) classDBN.getField("FINANCIA");
                NumField utilidad = (NumField) classDBN.getField("UTILIDAD");
                NumField cargos_adicionales = (NumField) classDBN.getField("OTROS");                
                for (int i = 1; i <= classDBN.getRecordCount(); i++)
                {                                    
                    classDBN.read();
                    for(int j = 0; j< partida.size(); j++){
                        Partida currentElement = new Partida();
                        currentElement = partida.get(j);
                        //Comparte Table3.nombre == TableN.nombre                        
                        if (nombren.get().toString().trim().equals(currentElement.getClave().toString().trim())){
                            if (indirectos.get() != null && indirectos.get() != ""){
                                currentElement.setIndirectos(Double.parseDouble(indirectos.get()));                                
                            }
                            if (indirectos_campo.get() != null && indirectos_campo.get() != ""){
                                currentElement.setIndirectos_campo(Double.parseDouble(indirectos_campo.get()));                                
                            }
                            if (financiamiento.get() != null && financiamiento.get() != ""){
                                currentElement.setFinanciamiento(Double.parseDouble(financiamiento.get()));                                
                            }
                            if (utilidad.get() != null && utilidad.get() != ""){
                                currentElement.setUtilidad(Double.parseDouble(utilidad.get()));                                
                            }
                            if (cargos_adicionales.get() != null && cargos_adicionales.get() != ""){
                                currentElement.setCargos_adicionales(Double.parseDouble(cargos_adicionales.get()));                                
                            }                                                        
                            partida.set(j, currentElement);
                        }                        
                    }
                }
                classDBN.close();                    
        } catch(Exception e){
            e.printStackTrace();
            //return null;
        }      
    }
    
    public ArrayList<Partida> get(){
        return partida;
    }
    
    public void exportarCSVPartidas(String cvsFileName) throws IllegalAccessException{
        try {
            FileWriter writer = new FileWriter(cvsFileName);                       
            for(int i = 0; i < partida.size(); i++){
                Partida currentElement = new Partida();
                currentElement = partida.get(i);  
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
            Logger.getLogger(CargaPartidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void InsertaPartidas(Double obraId) throws SQLException 
    {
        try{  
        Double maxId = 0.0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ResultSet rs = null;
        Connection conn = null;    
        conn = connectToDatabase();
        int duplicado = 0;
        for(int i = 0; i < partida.size(); i++){
            duplicado = 0;
            // connect to the database
            Partida currentElement = new Partida();
            currentElement = partida.get(i);  
            // Validate if record exists or get maxid + 1                 
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);        


            if (currentElement.getClave_partida()==null){
                rs = st.executeQuery("SELECT * FROM adempiere.c_partida where clave = '" + currentElement.getClave().trim() + "' and idUnico =" + currentElement.getIdUnico());
                if (rs.first()){                    
                    currentElement.setPartida_id(rs.getDouble("c_partida_id"));
                    partida.set(i, currentElement);
                    logger.debug("Partida ya existente: " + currentElement.getClave() + " | " + rs.getString("clave"));
                    duplicado = 1;
                    rs.close();
                    st.close();
                }
            }else{            
                rs = st.executeQuery("SELECT *  FROM adempiere.c_partida where clave = '" + currentElement.getClave().trim() + "' and clave_partida = '" + currentElement.getClave_partida().trim() + "' and idUnico = " + currentElement.getIdUnico());
                if (rs.first()){                
                    currentElement.setPartida_id(rs.getDouble("c_partida_id"));
                    partida.set(i, currentElement);
                    logger.debug("Partida ya existente: " + currentElement.getClave() + " | " + rs.getString("clave"));
                    duplicado = 1;
                    rs.close();
                    st.close();                    
                }
            }
            if (duplicado == 0){
                rs = st.executeQuery("SELECT COALESCE(max(c_partida_id),1000000) c_partida_id FROM adempiere.c_partida");
                                                                        
                while ( rs.next() )
                {
                    maxId = rs.getDouble("c_partida_id") + 1;
                    currentElement.setPartida_id(maxId);
                }
                rs.close();
                //Insertamos datos de la obra               
                String statement;                
                statement = "INSERT INTO adempiere.c_partida (c_partida_id, ad_client_id, ad_org_id, c_obra_id," +
                "nivel, signo, clave, clave_partida, idunico,descripcion, unidad, cantidad, precio_unitario, importe_total, fecha_inicio,"+
                "fecha_termino, indirectos, indirectos_campo, financiamiento, utilidad, cargos_adicionales, ad_user_id)" +
                "VALUES (" + maxId + "," + this.getAd_client_id() + "," + this.getAd_org_id() + "," + obraId + ",'" + currentElement.getNivel() + "','" +
                currentElement.getSigno() + "','" + currentElement.getClave()  + "','" + currentElement.getClave_partida() + "','" +  currentElement.getIdUnico()+ "','" +
                currentElement.getDescripcion() + "','" + currentElement.getUnidad() + "'," + currentElement.getCantidad() + "," + 
                currentElement.getPrecio_unitario() + "," + currentElement.getImporte_total() + "," ;                        
                if(currentElement.getFecha_inicio()!=null) 
                    statement = statement + "'" + formatter.format(currentElement.getFecha_inicio()) + "'";
                    else
                    statement = statement + "null";                                                
                statement = statement + ",";
                if (currentElement.getFecha_termino()!=null)
                statement = statement + "'" + formatter.format(currentElement.getFecha_termino()) + "'";
                else 
                statement = statement + "null"; 
                statement = statement  +  ","  +  currentElement.getIndirectos()  + "," + currentElement.getIndirectos_campo() + "," + 
                currentElement.getFinanciamiento() + "," + currentElement.getUtilidad() + "," + currentElement.getCargos_adicionales() + "," + 
                this.getAd_user_id() + ")"; 
                logger.debug(""+i);
                logger.debug(statement);
                st.execute(statement);                        
                st.close();
                partida.set(i, currentElement);
            }
        }        
    }           
    catch (SQLException se) {
      logger.error(se.getMessage());
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