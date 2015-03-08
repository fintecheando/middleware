package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SResource generated by hbm2java
 */
public class SResource  implements java.io.Serializable {


     private long SResourceId;
     private SResourcetype SResourcetype;
     private AdUser adUser;
     private MWarehouse MWarehouse;
     private long adClientId;
     private long adOrgId;
     private char isactive;
     private Date created;
     private long createdby;
     private Date updated;
     private long updatedby;
     private String value;
     private String name;
     private String description;
     private char isavailable;
     private BigDecimal chargeableqty;
     private BigDecimal percentutilization;
     private BigDecimal dailycapacity;
     private Character ismanufacturingresource;
     private BigDecimal waitingtime;
     private String manufacturingresourcetype;
     private BigDecimal queuingtime;
     private Long planninghorizon;
     private Set adWorkflows = new HashSet(0);
     private Set adWfNodes = new HashSet(0);

    public SResource() {
    }

	
    public SResource(long SResourceId, SResourcetype SResourcetype, MWarehouse MWarehouse, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String value, String name, char isavailable, BigDecimal percentutilization) {
        this.SResourceId = SResourceId;
        this.SResourcetype = SResourcetype;
        this.MWarehouse = MWarehouse;
        this.adClientId = adClientId;
        this.adOrgId = adOrgId;
        this.isactive = isactive;
        this.created = created;
        this.createdby = createdby;
        this.updated = updated;
        this.updatedby = updatedby;
        this.value = value;
        this.name = name;
        this.isavailable = isavailable;
        this.percentutilization = percentutilization;
    }
    public SResource(long SResourceId, SResourcetype SResourcetype, AdUser adUser, MWarehouse MWarehouse, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String value, String name, String description, char isavailable, BigDecimal chargeableqty, BigDecimal percentutilization, BigDecimal dailycapacity, Character ismanufacturingresource, BigDecimal waitingtime, String manufacturingresourcetype, BigDecimal queuingtime, Long planninghorizon, Set adWorkflows, Set adWfNodes) {
       this.SResourceId = SResourceId;
       this.SResourcetype = SResourcetype;
       this.adUser = adUser;
       this.MWarehouse = MWarehouse;
       this.adClientId = adClientId;
       this.adOrgId = adOrgId;
       this.isactive = isactive;
       this.created = created;
       this.createdby = createdby;
       this.updated = updated;
       this.updatedby = updatedby;
       this.value = value;
       this.name = name;
       this.description = description;
       this.isavailable = isavailable;
       this.chargeableqty = chargeableqty;
       this.percentutilization = percentutilization;
       this.dailycapacity = dailycapacity;
       this.ismanufacturingresource = ismanufacturingresource;
       this.waitingtime = waitingtime;
       this.manufacturingresourcetype = manufacturingresourcetype;
       this.queuingtime = queuingtime;
       this.planninghorizon = planninghorizon;
       this.adWorkflows = adWorkflows;
       this.adWfNodes = adWfNodes;
    }
   
    public long getSResourceId() {
        return this.SResourceId;
    }
    
    public void setSResourceId(long SResourceId) {
        this.SResourceId = SResourceId;
    }
    public SResourcetype getSResourcetype() {
        return this.SResourcetype;
    }
    
    public void setSResourcetype(SResourcetype SResourcetype) {
        this.SResourcetype = SResourcetype;
    }
    public AdUser getAdUser() {
        return this.adUser;
    }
    
    public void setAdUser(AdUser adUser) {
        this.adUser = adUser;
    }
    public MWarehouse getMWarehouse() {
        return this.MWarehouse;
    }
    
    public void setMWarehouse(MWarehouse MWarehouse) {
        this.MWarehouse = MWarehouse;
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
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
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
    public char getIsavailable() {
        return this.isavailable;
    }
    
    public void setIsavailable(char isavailable) {
        this.isavailable = isavailable;
    }
    public BigDecimal getChargeableqty() {
        return this.chargeableqty;
    }
    
    public void setChargeableqty(BigDecimal chargeableqty) {
        this.chargeableqty = chargeableqty;
    }
    public BigDecimal getPercentutilization() {
        return this.percentutilization;
    }
    
    public void setPercentutilization(BigDecimal percentutilization) {
        this.percentutilization = percentutilization;
    }
    public BigDecimal getDailycapacity() {
        return this.dailycapacity;
    }
    
    public void setDailycapacity(BigDecimal dailycapacity) {
        this.dailycapacity = dailycapacity;
    }
    public Character getIsmanufacturingresource() {
        return this.ismanufacturingresource;
    }
    
    public void setIsmanufacturingresource(Character ismanufacturingresource) {
        this.ismanufacturingresource = ismanufacturingresource;
    }
    public BigDecimal getWaitingtime() {
        return this.waitingtime;
    }
    
    public void setWaitingtime(BigDecimal waitingtime) {
        this.waitingtime = waitingtime;
    }
    public String getManufacturingresourcetype() {
        return this.manufacturingresourcetype;
    }
    
    public void setManufacturingresourcetype(String manufacturingresourcetype) {
        this.manufacturingresourcetype = manufacturingresourcetype;
    }
    public BigDecimal getQueuingtime() {
        return this.queuingtime;
    }
    
    public void setQueuingtime(BigDecimal queuingtime) {
        this.queuingtime = queuingtime;
    }
    public Long getPlanninghorizon() {
        return this.planninghorizon;
    }
    
    public void setPlanninghorizon(Long planninghorizon) {
        this.planninghorizon = planninghorizon;
    }
    public Set getAdWorkflows() {
        return this.adWorkflows;
    }
    
    public void setAdWorkflows(Set adWorkflows) {
        this.adWorkflows = adWorkflows;
    }
    public Set getAdWfNodes() {
        return this.adWfNodes;
    }
    
    public void setAdWfNodes(Set adWfNodes) {
        this.adWfNodes = adWfNodes;
    }




}


