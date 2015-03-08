package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CTaxgroup generated by hbm2java
 */
public class CTaxgroup  implements java.io.Serializable {


     private long CTaxgroupId;
     private String name;
     private long adOrgId;
     private Date created;
     private long createdby;
     private String description;
     private String help;
     private char isactive;
     private Date updated;
     private long updatedby;
     private long adClientId;
     private String value;
     private Set CBpartners = new HashSet(0);

    public CTaxgroup() {
    }

	
    public CTaxgroup(long CTaxgroupId, String name, long adOrgId, Date created, long createdby, char isactive, Date updated, long updatedby, long adClientId, String value) {
        this.CTaxgroupId = CTaxgroupId;
        this.name = name;
        this.adOrgId = adOrgId;
        this.created = created;
        this.createdby = createdby;
        this.isactive = isactive;
        this.updated = updated;
        this.updatedby = updatedby;
        this.adClientId = adClientId;
        this.value = value;
    }
    public CTaxgroup(long CTaxgroupId, String name, long adOrgId, Date created, long createdby, String description, String help, char isactive, Date updated, long updatedby, long adClientId, String value, Set CBpartners) {
       this.CTaxgroupId = CTaxgroupId;
       this.name = name;
       this.adOrgId = adOrgId;
       this.created = created;
       this.createdby = createdby;
       this.description = description;
       this.help = help;
       this.isactive = isactive;
       this.updated = updated;
       this.updatedby = updatedby;
       this.adClientId = adClientId;
       this.value = value;
       this.CBpartners = CBpartners;
    }
   
    public long getCTaxgroupId() {
        return this.CTaxgroupId;
    }
    
    public void setCTaxgroupId(long CTaxgroupId) {
        this.CTaxgroupId = CTaxgroupId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public long getAdOrgId() {
        return this.adOrgId;
    }
    
    public void setAdOrgId(long adOrgId) {
        this.adOrgId = adOrgId;
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
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getHelp() {
        return this.help;
    }
    
    public void setHelp(String help) {
        this.help = help;
    }
    public char getIsactive() {
        return this.isactive;
    }
    
    public void setIsactive(char isactive) {
        this.isactive = isactive;
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
    public long getAdClientId() {
        return this.adClientId;
    }
    
    public void setAdClientId(long adClientId) {
        this.adClientId = adClientId;
    }
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    public Set getCBpartners() {
        return this.CBpartners;
    }
    
    public void setCBpartners(Set CBpartners) {
        this.CBpartners = CBpartners;
    }




}


