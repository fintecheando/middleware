package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AdWfBlock generated by hbm2java
 */
public class AdWfBlock  implements java.io.Serializable {


     private long adWfBlockId;
     private AdWorkflow adWorkflow;
     private long adClientId;
     private long adOrgId;
     private char isactive;
     private Date created;
     private long createdby;
     private Date updated;
     private long updatedby;
     private String name;
     private String description;
     private Set adWfNodes = new HashSet(0);

    public AdWfBlock() {
    }

	
    public AdWfBlock(long adWfBlockId, AdWorkflow adWorkflow, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String name) {
        this.adWfBlockId = adWfBlockId;
        this.adWorkflow = adWorkflow;
        this.adClientId = adClientId;
        this.adOrgId = adOrgId;
        this.isactive = isactive;
        this.created = created;
        this.createdby = createdby;
        this.updated = updated;
        this.updatedby = updatedby;
        this.name = name;
    }
    public AdWfBlock(long adWfBlockId, AdWorkflow adWorkflow, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String name, String description, Set adWfNodes) {
       this.adWfBlockId = adWfBlockId;
       this.adWorkflow = adWorkflow;
       this.adClientId = adClientId;
       this.adOrgId = adOrgId;
       this.isactive = isactive;
       this.created = created;
       this.createdby = createdby;
       this.updated = updated;
       this.updatedby = updatedby;
       this.name = name;
       this.description = description;
       this.adWfNodes = adWfNodes;
    }
   
    public long getAdWfBlockId() {
        return this.adWfBlockId;
    }
    
    public void setAdWfBlockId(long adWfBlockId) {
        this.adWfBlockId = adWfBlockId;
    }
    public AdWorkflow getAdWorkflow() {
        return this.adWorkflow;
    }
    
    public void setAdWorkflow(AdWorkflow adWorkflow) {
        this.adWorkflow = adWorkflow;
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
    public Set getAdWfNodes() {
        return this.adWfNodes;
    }
    
    public void setAdWfNodes(Set adWfNodes) {
        this.adWfNodes = adWfNodes;
    }




}


