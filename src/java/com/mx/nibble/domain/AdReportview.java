package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AdReportview generated by hbm2java
 */
public class AdReportview  implements java.io.Serializable {


     private long adReportviewId;
     private AdTable adTable;
     private AdEntitytype adEntitytype;
     private long adClientId;
     private long adOrgId;
     private char isactive;
     private Date created;
     private long createdby;
     private Date updated;
     private long updatedby;
     private String name;
     private String description;
     private String whereclause;
     private String orderbyclause;
     private Set adPrintformats = new HashSet(0);
     private Set adProcesses = new HashSet(0);

    public AdReportview() {
    }

	
    public AdReportview(long adReportviewId, AdTable adTable, AdEntitytype adEntitytype, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String name) {
        this.adReportviewId = adReportviewId;
        this.adTable = adTable;
        this.adEntitytype = adEntitytype;
        this.adClientId = adClientId;
        this.adOrgId = adOrgId;
        this.isactive = isactive;
        this.created = created;
        this.createdby = createdby;
        this.updated = updated;
        this.updatedby = updatedby;
        this.name = name;
    }
    public AdReportview(long adReportviewId, AdTable adTable, AdEntitytype adEntitytype, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String name, String description, String whereclause, String orderbyclause, Set adPrintformats, Set adProcesses) {
       this.adReportviewId = adReportviewId;
       this.adTable = adTable;
       this.adEntitytype = adEntitytype;
       this.adClientId = adClientId;
       this.adOrgId = adOrgId;
       this.isactive = isactive;
       this.created = created;
       this.createdby = createdby;
       this.updated = updated;
       this.updatedby = updatedby;
       this.name = name;
       this.description = description;
       this.whereclause = whereclause;
       this.orderbyclause = orderbyclause;
       this.adPrintformats = adPrintformats;
       this.adProcesses = adProcesses;
    }
   
    public long getAdReportviewId() {
        return this.adReportviewId;
    }
    
    public void setAdReportviewId(long adReportviewId) {
        this.adReportviewId = adReportviewId;
    }
    public AdTable getAdTable() {
        return this.adTable;
    }
    
    public void setAdTable(AdTable adTable) {
        this.adTable = adTable;
    }
    public AdEntitytype getAdEntitytype() {
        return this.adEntitytype;
    }
    
    public void setAdEntitytype(AdEntitytype adEntitytype) {
        this.adEntitytype = adEntitytype;
    }
    public long getAdClientId() {
        return this.adClientId;
    }
    
    public void setAdClientId(long adClientId) {
        this.adClientId = adClientId;
    }
    public long getAdOrgId() {
        return this.adOrgId;
    }
    
    public void setAdOrgId(long adOrgId) {
        this.adOrgId = adOrgId;
    }
    public char getIsactive() {
        return this.isactive;
    }
    
    public void setIsactive(char isactive) {
        this.isactive = isactive;
    }
    public Date getCreated() {
        return this.created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    public long getCreatedby() {
        return this.createdby;
    }
    
    public void setCreatedby(long createdby) {
        this.createdby = createdby;
    }
    public Date getUpdated() {
        return this.updated;
    }
    
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    public long getUpdatedby() {
        return this.updatedby;
    }
    
    public void setUpdatedby(long updatedby) {
        this.updatedby = updatedby;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getWhereclause() {
        return this.whereclause;
    }
    
    public void setWhereclause(String whereclause) {
        this.whereclause = whereclause;
    }
    public String getOrderbyclause() {
        return this.orderbyclause;
    }
    
    public void setOrderbyclause(String orderbyclause) {
        this.orderbyclause = orderbyclause;
    }
    public Set getAdPrintformats() {
        return this.adPrintformats;
    }
    
    public void setAdPrintformats(Set adPrintformats) {
        this.adPrintformats = adPrintformats;
    }
    public Set getAdProcesses() {
        return this.adProcesses;
    }
    
    public void setAdProcesses(Set adProcesses) {
        this.adProcesses = adProcesses;
    }




}


