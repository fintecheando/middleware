package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CDunning generated by hbm2java
 */
public class CDunning  implements java.io.Serializable {


     private long CDunningId;
     private long adClientId;
     private long adOrgId;
     private char isactive;
     private Date created;
     private long createdby;
     private Date updated;
     private long updatedby;
     private String name;
     private String description;
     private char senddunningletter;
     private char isdefault;
     private char createlevelssequentially;
     private Set CBpGroups = new HashSet(0);
     private Set CBpartners = new HashSet(0);

    public CDunning() {
    }

	
    public CDunning(long CDunningId, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String name, char senddunningletter, char isdefault, char createlevelssequentially) {
        this.CDunningId = CDunningId;
        this.adClientId = adClientId;
        this.adOrgId = adOrgId;
        this.isactive = isactive;
        this.created = created;
        this.createdby = createdby;
        this.updated = updated;
        this.updatedby = updatedby;
        this.name = name;
        this.senddunningletter = senddunningletter;
        this.isdefault = isdefault;
        this.createlevelssequentially = createlevelssequentially;
    }
    public CDunning(long CDunningId, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String name, String description, char senddunningletter, char isdefault, char createlevelssequentially, Set CBpGroups, Set CBpartners) {
       this.CDunningId = CDunningId;
       this.adClientId = adClientId;
       this.adOrgId = adOrgId;
       this.isactive = isactive;
       this.created = created;
       this.createdby = createdby;
       this.updated = updated;
       this.updatedby = updatedby;
       this.name = name;
       this.description = description;
       this.senddunningletter = senddunningletter;
       this.isdefault = isdefault;
       this.createlevelssequentially = createlevelssequentially;
       this.CBpGroups = CBpGroups;
       this.CBpartners = CBpartners;
    }
   
    public long getCDunningId() {
        return this.CDunningId;
    }
    
    public void setCDunningId(long CDunningId) {
        this.CDunningId = CDunningId;
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
    public char getSenddunningletter() {
        return this.senddunningletter;
    }
    
    public void setSenddunningletter(char senddunningletter) {
        this.senddunningletter = senddunningletter;
    }
    public char getIsdefault() {
        return this.isdefault;
    }
    
    public void setIsdefault(char isdefault) {
        this.isdefault = isdefault;
    }
    public char getCreatelevelssequentially() {
        return this.createlevelssequentially;
    }
    
    public void setCreatelevelssequentially(char createlevelssequentially) {
        this.createlevelssequentially = createlevelssequentially;
    }
    public Set getCBpGroups() {
        return this.CBpGroups;
    }
    
    public void setCBpGroups(Set CBpGroups) {
        this.CBpGroups = CBpGroups;
    }
    public Set getCBpartners() {
        return this.CBpartners;
    }
    
    public void setCBpartners(Set CBpartners) {
        this.CBpartners = CBpartners;
    }




}


