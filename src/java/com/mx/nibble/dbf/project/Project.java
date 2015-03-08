/**
 * Nibble
 * @author Abraham Lopez Partida
 * Date: December 17, 2014
 */

package com.mx.nibble.dbf.project;

import java.math.BigDecimal;
import java.util.Date;
import org.xBaseJ.fields.DateField;
import org.xBaseJ.fields.NumField;

public class Project {
    public Double projectype_id;
    public String projectName;
    public String description;
    public String tender;
    public Date dateTender;
    public String location;
    public Double plannedamt;      
    public Date dateContract;
    public Date dateFinish;
    public String note;

    public Double getProjectype_id(){return projectype_id;}
    public String getProjectName(){return projectName;}
    public String getDescription(){return description;}
    public String getTender(){return tender;}
    public Date getDateTender(){return dateTender;}
    public String getLocation(){return location;}
    public Double getPlannedAmt(){return plannedamt;}
    public Date getDateContract(){return dateContract;}
    public Date getDateFinish(){return dateFinish;}
    public String getNote(){return note;}
    
       
    public void setProjectype_id(Double newProjectype_id){projectype_id =newProjectype_id;}
    public void setProjectName(String newProjectName){projectName =newProjectName;}
    public void setDescripcion(String newDescription){description=newDescription;}
    public void setTender(String newTender){ tender=newTender;}
    public void setDateTender(Date newDateTender){dateTender=newDateTender;}
    public void setLocation(String newLocation){location=newLocation;}
    public void setPlannedAmt(Double newPlannedamt){plannedamt=newPlannedamt;}
    public void setDateContract(Date newDateContract){dateContract=newDateContract;}
    public void setDateFinish(Date newDateFinish){dateFinish=newDateFinish;}
    public void setNote(String newNote){note=newNote;}  
}
