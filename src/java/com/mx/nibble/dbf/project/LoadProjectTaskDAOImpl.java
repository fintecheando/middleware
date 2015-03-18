/**
 * Nibble
 * @author Abraham Lopez Partida
 * Date: December 17, 2014
 */

package com.mx.nibble.dbf.project;

import com.mx.nibble.domain.CProjectphase;
import com.mx.nibble.domain.CProjecttask;
import com.mx.nibble.domain.MProduct;
import com.mx.nibble.middleware.dao.ProductDAO;
import com.mx.nibble.middleware.dao.ProductDAOImpl;
import com.mx.nibble.middleware.dao.ProjectTaskDAO;
import com.mx.nibble.middleware.dao.ProjectTaskDAOImpl;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xBaseJ.DBF;
import org.xBaseJ.fields.*;
import org.xBaseJ.xBaseJException;

public class LoadProjectTaskDAOImpl implements LoadProjectTaskDAO {
       
    Logger logger = LoggerFactory.getLogger(LoadProjectTaskDAOImpl.class);
    
    public ArrayList<ProjectTask> projecttasks;
    private static String EOL_STRING = System.getProperty("line.separator");
    private long ad_client_id;
    private long ad_org_id;
    private long ad_user_id;
    ProductDAO productDAO = new ProductDAOImpl();
    ProjectTaskDAO ptaskDAO = new ProjectTaskDAOImpl();


    public void loadProjectTask(String dbfPath, String dbfFileName, ArrayList<CProjectphase> cprojectphases, Double cprojectid) throws xBaseJException{
        try{
            projecttasks = new ArrayList<ProjectTask>();
            DBF classDB=new DBF(dbfPath+dbfFileName+"f.dbf",DBF.READ_ONLY);
            //Define fields of table <F>
            NumField qty = (NumField) classDB.getField("CANTIDAD");
            NumField unitarycost = (NumField) classDB.getField("COSTO");
            CharField key  = (CharField) classDB.getField("NOMBRE");
            CharField name  = (CharField) classDB.getField("COMPONENTE");
            NumField taskprefixid  = (NumField) classDB.getField("PREFCOMP");                
            NumField plannedamt = (NumField) classDB.getField("IMPORTE");                            
            CProjectphase pphase = new CProjectphase();            
            for(int i = 0; i< cprojectphases.size(); i++){
                pphase = cprojectphases.get(i);
                    classDB.gotoRecord(1);
                    for (int j = 1; j <= classDB.getRecordCount(); j++)
                    {                
                        ProjectTask ptask = new ProjectTask();
                        classDB.gotoRecord(j);
                        if (classDB.deleted() == false) {
                            if(pphase.getKey().toString().trim().equals(key.get().toString().trim()))
                            {                                
                                ptask.setProjectid(cprojectid);
                                ptask.setPhaseid(pphase.getCProjectphaseId());
                                ptask.setQty(Double.parseDouble(qty.get()));
                                ptask.setUnitarycost(Double.parseDouble(unitarycost.get()));
                                ptask.setKey(key.get().trim());
                                ptask.setName(substr(name.get().trim(),255).toUpperCase());
                                ptask.setTaskPrefixId(Double.parseDouble(taskprefixid.get()));
                                ptask.setPlannedamt(Double.parseDouble(plannedamt.get()));
                                projecttasks.add(ptask);
                            }                            
                        }
                    }                  
            }
            classDB.close();
            DBF classDBP =new DBF(dbfPath+"/"+dbfFileName+"p.dbf",DBF.READ_ONLY);
            MemoField description = (MemoField) classDBP.getField("DESCRIPCIO");
            CharField unit  = (CharField) classDBP.getField("UNIDAD");
            CharField namep  = (CharField) classDBP.getField("NOMBRE");
            NumField taskprefixidp  = (NumField) classDBP.getField("PREFIJO");
            for (int k = 1; k <= classDBP.getRecordCount(); k++)
            {                                    
                classDBP.read();
                if (classDB.deleted() == false) {
                    for(int h = 0; h< projecttasks.size(); h++){
                        ProjectTask currentElement = new ProjectTask();
                        currentElement = projecttasks.get(h);
                        if (namep.get().toString().trim().equals(currentElement.getName().toString().trim()) &&
                            Double.parseDouble(taskprefixidp.get()) == currentElement.getTaskprefixid()){
                            if (unit.get() != null && unit.get() != ""){
                                currentElement.setUnit(unit.get().trim());
                            }
                            currentElement.setDescription(substr(description.get().trim().replace("'",""),255));
                            projecttasks.set(h, currentElement);
                        }                        
                    }
                }
            }
            classDBP.close();
        } catch(Exception e){
            e.printStackTrace();
        }      
    }

   public void insertProjectTask() throws SQLException 
    {
        try{  
        Double maxId = 0.0;
        

        for(int i = 0; i < projecttasks.size(); i++){           
            ProjectTask currentElement = new ProjectTask();
            currentElement = projecttasks.get(i);          
            //Validate product            
            List <MProduct> tempmproduct = productDAO.searchByProductNameAndDescription(currentElement.getName() , currentElement.getDescription());
            if (tempmproduct.size()>0){
                currentElement.setProductid(tempmproduct.get(0).getMProductId());
                projecttasks.set(i, currentElement);
                logger.debug("MProduct already exists: " + currentElement.getProductid());
            } else {
                    MProduct productElement = new MProduct();
                    productElement.setMProductId(productDAO.maxProductId());
                    productElement.setAdClientId(this.ad_client_id);
                    productElement.setAdOrgId(this.ad_org_id);
                    productElement.setIsactive('Y');
                    productElement.setCreated(Calendar.getInstance().getTime());
                    productElement.setCreatedby(this.getAd_user_id());
                    productElement.setUpdated(Calendar.getInstance().getTime());
                    productElement.setUpdatedby(this.getAd_user_id());
                    productElement.setValue(currentElement.name);
                    productElement.setName(currentElement.name);
                    productElement.setDescription(currentElement.getDescription());
                    productElement.setSku(currentElement.getUnit());
                    productElement.setCUomId(100);
                    productElement.setIssummary('N');
                    productElement.setIsstocked('Y');               
                    productElement.setIspurchased('Y');
                    productElement.setIssold('N');
                    productElement.setIsbom('N');
                    productElement.setIsinvoiceprintdetails('N');
                    productElement.setIspicklistprintdetails('N');
                    productElement.setIsverified('N');
                    productElement.setMProductCategoryId(1000000);   // modificar por mano de obra, Equipo y materiales   1000000=Mano de Obra, 1000001=Equipo, 1000002=Materiales
                    productElement.setVolume(BigDecimal.ZERO);
                    productElement.setWeight(BigDecimal.ZERO);
                    productElement.setShelfwidth(Long.parseLong("0"));
                    productElement.setCTaxcategoryId(1000000);  //Estandard
                    productElement.setDiscontinued('N');
                    productElement.setProcessing('N');
                    productElement.setProducttype('I');  // I=Articulo, R=Recurso, S=Servicio, E=Tipo de Gasto
                    productElement.setMAttributesetinstanceId(Long.parseLong("0"));
                    productElement.setIswebstorefeatured('N');
                    productElement.setIsselfservice('Y');
                    productElement.setIsdropship('N');
                    productElement.setIsexcludeautodelivery('N');
                    productElement.setLowlevel(0);
                    productElement.setUnitsperpack(1);
                    productElement.setCopyfrom('N');                                        
                    productDAO.saveOrUpdate(productElement);
                    currentElement.setProductid(productElement.getMProductId());
            }


            List <CProjecttask> tempptask = ptaskDAO.searchByKeyAndName(currentElement.getPhaseid(), currentElement.getKey(), currentElement.getName());
            if (tempptask.size()>0){
                currentElement.setPhaseid(tempptask.get(0).getCProjecttaskId());
                projecttasks.set(i, currentElement);
                logger.debug("CProjecttask already exists: " + currentElement.getPhaseid());
            } else {
                CProjecttask taskElement = new CProjecttask();
                taskElement.setCProjecttaskId(ptaskDAO.maxProductTaskId());
                taskElement.setAdClientId(this.ad_client_id);
                taskElement.setAdOrgId(this.ad_org_id);
                taskElement.setIsactive('y');
                taskElement.setCreated(Calendar.getInstance().getTime());
                taskElement.setCreatedby(this.getAd_user_id());
                taskElement.setUpdated(Calendar.getInstance().getTime());
                taskElement.setUpdatedby(this.getAd_user_id());
                taskElement.setSeqno(i);
                taskElement.setName(currentElement.name);
                taskElement.setDescription(currentElement.description);
                taskElement.setMProductId(currentElement.getProductid());
                taskElement.setCProjectphaseId(currentElement.getPhaseid());
                taskElement.setQty(new BigDecimal(currentElement.getQty()));
                taskElement.setPlannedamt(new BigDecimal(currentElement.getPlannedamt()));
                taskElement.setCommittedamt(BigDecimal.ZERO);
                taskElement.setKey(currentElement.getKey());                
                ptaskDAO.saveOrUpdate(taskElement);
            }    
        }        
    }                       
    catch (Exception se) {
      logger.error(se.getMessage());
      se.printStackTrace();
    }
  }
    
    public void exportrCSVProjectTask(String cvsFileName) throws IllegalAccessException{
        try {
            FileWriter writer = new FileWriter(cvsFileName);                       
            for(int i = 0; i < projecttasks.size(); i++){
                ProjectTask currentElement = new ProjectTask();
                currentElement = projecttasks.get(i);  
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
    public void saveOrUpdateProjectTask(CProjecttask projecttask) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CProjecttask> listProjectTask() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CProjecttask listProjectTaskById(Long projecttaskId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProjectTask(Long projecttaskId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}