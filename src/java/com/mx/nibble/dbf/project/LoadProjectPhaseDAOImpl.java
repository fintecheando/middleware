/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.dbf.project;

import com.mx.nibble.domain.CProjectphase;
import com.mx.nibble.middleware.dao.ProjectPhaseDAO;
import com.mx.nibble.middleware.dao.ProjectPhaseDAOImpl;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.xBaseJ.DBF;
import org.xBaseJ.fields.*;
import org.xBaseJ.xBaseJException;

public class LoadProjectPhaseDAOImpl implements LoadProjectPhaseDAO{
    private ArrayList<CProjectphase> cprojectphase;
    private static String EOL_STRING = System.getProperty("line.separator");
    private long ad_client_id;
    private long ad_org_id;
    private long ad_user_id;
    org.slf4j.Logger logger = LoggerFactory.getLogger(LoadProjectPhaseDAO.class);
    ProjectPhaseDAO projectphaseDAO = new ProjectPhaseDAOImpl();

    public void loadProjectPhase(String dbfPath, String dbfFileName,  Long cprojectId) throws xBaseJException{
        try{            
            setCprojectphase(new ArrayList<CProjectphase>());
            String previouskey = "";
            Integer previouslevel = 0;
            //Read DBF File Name ended in "3"
            DBF classDB=new DBF(dbfPath+dbfFileName+"3.dbf",DBF.READ_ONLY);         
            //Define fields of table <3>
                NumField id = (NumField) classDB.getField("ID");
                NumField uniqueid = (NumField) classDB.getField("IDUNICO");
                CharField level  = (CharField) classDB.getField("NIVEL");
                CharField sign  = (CharField) classDB.getField("SIGNO");
                CharField key  = (CharField) classDB.getField("NOMBRE");
                //CharField clave  = (CharField) classDB.getField("CLAVEUSUAR");
                MemoField description = (MemoField) classDB.getField("DESCRIPCIO");
                CharField unit  = (CharField) classDB.getField("UNIDAD");
                NumField quantity = (NumField) classDB.getField("CANTIDAD");
                NumField totalamt = (NumField) classDB.getField("COSTO");
                DateField startdate = (DateField) classDB.getField("FINI");
                DateField enddate = (DateField) classDB.getField("FTER");

                //Iterate each DataBase record, create a ConstructionParty element and add it into the ArrayList
                for (int i = 1; i <= classDB.getRecordCount(); i++)
                {                
                    classDB.read();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                    CProjectphase currentElement = new CProjectphase();                                        
                    if ((Integer.parseInt(level.get()) >= previouslevel) && (sign.get().toString().trim().equals("-"))){
                        currentElement.setPhasekey(previouskey);
                    }else{
                        previouskey = key.get().replace("'","").trim();
                    }                
                    previouslevel = Integer.parseInt(level.get().trim());
                    currentElement.setUniqueid(Long.parseLong(uniqueid.get().trim()));
                    currentElement.setCProjectId(cprojectId);
                    currentElement.setLevel(Byte.valueOf(level.get().trim()));
                    currentElement.setSign(sign.get().replace("'","").trim().substring(0,0));
                    currentElement.setKey(substr(key.get().replace("'","").replace("|",".").trim(),30));
                    currentElement.setName(substr(key.get().replace("'","").replace("|",".").trim() + description.get().replace("'","").trim(),60));
                    currentElement.setDescription(substr(description.get().replace("'","").trim(),255));
                    currentElement.setUnit(substr(unit.get().replace("'","").trim(),7));
                    currentElement.setSeqno(Long.parseLong(uniqueid.get().replace("'","").trim()));
                    currentElement.setQty(new BigDecimal(quantity.get().trim()));
                    currentElement.setPlannedamt(new BigDecimal(totalamt.get().trim()));
                    currentElement.setStartdate(formatter.parse(startdate.get().trim()));
                    currentElement.setEnddate(formatter.parse(enddate.get().trim()));
                    getCprojectphase().add(currentElement);
                }                  
                classDB.close();
        } catch(Exception e){
            e.printStackTrace();
        }      
    }
    
    public ArrayList<CProjectphase> get(){
        return getCprojectphase();
    }
    
    public void exportCSVProjectPhases(String cvsFileName) throws IllegalAccessException{
        try {
            FileWriter writer = new FileWriter(cvsFileName);                       
            for(int i = 0; i < getCprojectphase().size(); i++){
                CProjectphase currentElement = new CProjectphase();
                currentElement = getCprojectphase().get(i);  
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
            Logger.getLogger(LoadProjectPhaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertProjectPhases(Long cprojectId) throws SQLException 
    {
        try{
        
        Double maxId = 0.0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");        
        int duplicado = 0;
            for(int i = 0; i < getCprojectphase().size(); i++){
                duplicado = 0;
                CProjectphase currentElement = new CProjectphase();
                currentElement = getCprojectphase().get(i);  
                // Validate if record exists or get maxid + 1
                if (currentElement.getPhasekey()==null){                
                    List <CProjectphase> tempcprojectphase = projectphaseDAO.searchByKey(currentElement.getCProjectId() ,currentElement.getKey().trim(),currentElement.getUniqueid());
                    if (tempcprojectphase.size()>0){
                        currentElement.setCProjectphaseId(tempcprojectphase.get(0).getCProjectphaseId());
                        getCprojectphase().set(i, currentElement);
                        logger.debug("CProjectPhase already exists: " + currentElement.getKey()+ " | " + tempcprojectphase.get(0).getKey());
                        duplicado = 1;
                    }    
                }else {
                    List <CProjectphase> tempcprojectphase = projectphaseDAO.searchByKeyAndPhaseKey(currentElement.getCProjectId(), currentElement.getKey().trim(), currentElement.getPhasekey().trim(), currentElement.getUniqueid());
                    if (tempcprojectphase.size()>0){
                        currentElement.setCProjectphaseId(tempcprojectphase.get(0).getCProjectphaseId());
                        getCprojectphase().set(i, currentElement);
                        logger.debug("CProjectPhase already exists: " + currentElement.getKey()+ " | " + tempcprojectphase.get(0).getKey());
                        duplicado = 1;
                    }
                }
                if (duplicado == 0){                    
                    currentElement.setCProjectphaseId(projectphaseDAO.maxProjectPhaseId());
                    //currentElement.setCProjectId();
                    currentElement.setAdClientId(this.getAd_client_id());
                    currentElement.setAdOrgId(this.getAd_org_id());
                    currentElement.setIsactive('Y');
                    currentElement.setCreated(Calendar.getInstance().getTime());
                    currentElement.setCreatedby(this.getAd_user_id());
                    currentElement.setUpdated(Calendar.getInstance().getTime());
                    currentElement.setUpdatedby(this.getAd_user_id());
                    //currentElement.setDescription();
                    //currentElement.setStartdate();
                    //currentElement.setEnddate();
                    currentElement.setIscomplete('N');
                    currentElement.setPriceactual(BigDecimal.ZERO);
                    //currentElement.setName();
                    //currentElement.setQty();
                    //currentElement.setSeqno();
                    currentElement.setCommittedamt(BigDecimal.ZERO);
                    currentElement.setIscommitceiling('N');
                    //currentElement.setPlannedamt();
                    //currentElement.setLevel();
                    //currentElement.setSign();
                    //currentElement.setKey();
                    //currentElement.setPhasekey();
                    //currentElement.setUniqueid();
                    //currentElement.setUnit();                            
                    getCprojectphase().set(i, currentElement);
                    projectphaseDAO.saveOrUpdate(currentElement);
                }
            }        
        }           
        catch (Exception se) {
        logger.error(se.getMessage());
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
    
    public String substr(String text, int maxlength){
        int length = text.length();
        return ((length<maxlength)?  text  :  text.substring(0,maxlength-1));
    }

    @Override
    public void saveOrUpdateProjectphase(CProjectphase projectphase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CProjectphase> listProjectphase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CProjectphase listProjectphaseById(Long projectphaseId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProjectphase(Long projectphaseId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the cprojectphase
     */
    public ArrayList<CProjectphase> getCprojectphase() {
        return cprojectphase;
    }

    /**
     * @param cprojectphase the cprojectphase to set
     */
    public void setCprojectphase(ArrayList<CProjectphase> cprojectphase) {
        this.cprojectphase = cprojectphase;
    }
}