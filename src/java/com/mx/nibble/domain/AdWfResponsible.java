package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AdWfResponsible generated by hbm2java
 */
public class AdWfResponsible  implements java.io.Serializable {


     private long adWfResponsibleId;
     private AdUser adUser;
     private AdOrg adOrg;
     private AdEntitytype adEntitytype;
     private AdRole adRole;
     private long adClientId;
     private char isactive;
     private Date created;
     private long createdby;
     private Date updated;
     private long updatedby;
     private String name;
     private String description;
     private char responsibletype;
     private Set adWfNodes = new HashSet(0);
     private Set adWorkflows = new HashSet(0);

    public AdWfResponsible() {
    }

	
    public AdWfResponsible(long adWfResponsibleId, AdOrg adOrg, AdEntitytype adEntitytype, long adClientId, char isactive, Date created, long createdby, Date updated, long updatedby, String name, char responsibletype) {
        this.adWfResponsibleId = adWfResponsibleId;
        this.adOrg = adOrg;
        this.adEntitytype = adEntitytype;
        this.adClientId = adClientId;
        this.isactive = isactive;
        this.created = created;
        this.createdby = createdby;
        this.updated = updated;
        this.updatedby = updatedby;
        this.name = name;
        this.responsibletype = responsibletype;
    }
    public AdWfResponsible(long adWfResponsibleId, AdUser adUser, AdOrg adOrg, AdEntitytype adEntitytype, AdRole adRole, long adClientId, char isactive, Date created, long createdby, Date updated, long updatedby, String name, String description, char responsibletype, Set adWfNodes, Set adWorkflows) {
       this.adWfResponsibleId = adWfResponsibleId;
       this.adUser = adUser;
       this.adOrg = adOrg;
       this.adEntitytype = adEntitytype;
       this.adRole = adRole;
       this.adClientId = adClientId;
       this.isactive = isactive;
       this.created = created;
       this.createdby = createdby;
       this.updated = updated;
       this.updatedby = updatedby;
       this.name = name;
       this.description = description;
       this.responsibletype = responsibletype;
       this.adWfNodes = adWfNodes;
       this.adWorkflows = adWorkflows;
    }
   
    public long getAdWfResponsibleId() {
        return this.adWfResponsibleId;
    }
    
    public void setAdWfResponsibleId(long adWfResponsibleId) {
        this.adWfResponsibleId = adWfResponsibleId;
    }
    public AdUser getAdUser() {
        return this.adUser;
    }
    
    public void setAdUser(AdUser adUser) {
        this.adUser = adUser;
    }
    public AdOrg getAdOrg() {
        return this.adOrg;
    }
    
    public void setAdOrg(AdOrg adOrg) {
        this.adOrg = adOrg;
    }
    public AdEntitytype getAdEntitytype() {
        return this.adEntitytype;
    }
    
    public void setAdEntitytype(AdEntitytype adEntitytype) {
        this.adEntitytype = adEntitytype;
    }
    public AdRole getAdRole() {
        return this.adRole;
    }
    
    public void setAdRole(AdRole adRole) {
        this.adRole = adRole;
    }
    public long getAdClientId() {
        return this.adClientId;
    }
    
    public void setAdClientId(long adClientId) {
        this.adClientId = adClientId;
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
    public char getResponsibletype() {
        return this.responsibletype;
    }
    
    public void setResponsibletype(char responsibletype) {
        this.responsibletype = responsibletype;
    }
    public Set getAdWfNodes() {
        return this.adWfNodes;
    }
    
    public void setAdWfNodes(Set adWfNodes) {
        this.adWfNodes = adWfNodes;
    }
    public Set getAdWorkflows() {
        return this.adWorkflows;
    }
    
    public void setAdWorkflows(Set adWorkflows) {
        this.adWorkflows = adWorkflows;
    }




}


