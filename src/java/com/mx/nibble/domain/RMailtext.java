package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * RMailtext generated by hbm2java
 */
public class RMailtext  implements java.io.Serializable {


     private long RMailtextId;
     private long adClientId;
     private long adOrgId;
     private char isactive;
     private Date created;
     private long createdby;
     private Date updated;
     private long updatedby;
     private String name;
     private char ishtml;
     private String mailheader;
     private String mailtext;
     private String mailtext2;
     private String mailtext3;
     private Set adWfNodes = new HashSet(0);

    public RMailtext() {
    }

	
    public RMailtext(long RMailtextId, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String name, char ishtml, String mailtext) {
        this.RMailtextId = RMailtextId;
        this.adClientId = adClientId;
        this.adOrgId = adOrgId;
        this.isactive = isactive;
        this.created = created;
        this.createdby = createdby;
        this.updated = updated;
        this.updatedby = updatedby;
        this.name = name;
        this.ishtml = ishtml;
        this.mailtext = mailtext;
    }
    public RMailtext(long RMailtextId, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String name, char ishtml, String mailheader, String mailtext, String mailtext2, String mailtext3, Set adWfNodes) {
       this.RMailtextId = RMailtextId;
       this.adClientId = adClientId;
       this.adOrgId = adOrgId;
       this.isactive = isactive;
       this.created = created;
       this.createdby = createdby;
       this.updated = updated;
       this.updatedby = updatedby;
       this.name = name;
       this.ishtml = ishtml;
       this.mailheader = mailheader;
       this.mailtext = mailtext;
       this.mailtext2 = mailtext2;
       this.mailtext3 = mailtext3;
       this.adWfNodes = adWfNodes;
    }
   
    public long getRMailtextId() {
        return this.RMailtextId;
    }
    
    public void setRMailtextId(long RMailtextId) {
        this.RMailtextId = RMailtextId;
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
    public char getIshtml() {
        return this.ishtml;
    }
    
    public void setIshtml(char ishtml) {
        this.ishtml = ishtml;
    }
    public String getMailheader() {
        return this.mailheader;
    }
    
    public void setMailheader(String mailheader) {
        this.mailheader = mailheader;
    }
    public String getMailtext() {
        return this.mailtext;
    }
    
    public void setMailtext(String mailtext) {
        this.mailtext = mailtext;
    }
    public String getMailtext2() {
        return this.mailtext2;
    }
    
    public void setMailtext2(String mailtext2) {
        this.mailtext2 = mailtext2;
    }
    public String getMailtext3() {
        return this.mailtext3;
    }
    
    public void setMailtext3(String mailtext3) {
        this.mailtext3 = mailtext3;
    }
    public Set getAdWfNodes() {
        return this.adWfNodes;
    }
    
    public void setAdWfNodes(Set adWfNodes) {
        this.adWfNodes = adWfNodes;
    }




}


