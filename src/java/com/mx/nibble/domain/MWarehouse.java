package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * MWarehouse generated by hbm2java
 */
public class MWarehouse  implements java.io.Serializable {


     private long MWarehouseId;
     private AdClient adClient;
     private MWarehouse MWarehouse;
     private CLocation CLocation;
     private AdOrg adOrg;
     private char isactive;
     private Date created;
     private long createdby;
     private Date updated;
     private long updatedby;
     private String value;
     private String name;
     private String description;
     private char separator;
     private String replenishmentclass;
     private Character isintransit;
     private Set MWarehouses = new HashSet(0);
     private Set SResources = new HashSet(0);

    public MWarehouse() {
    }

	
    public MWarehouse(long MWarehouseId, AdClient adClient, CLocation CLocation, AdOrg adOrg, char isactive, Date created, long createdby, Date updated, long updatedby, String value, String name, char separator) {
        this.MWarehouseId = MWarehouseId;
        this.adClient = adClient;
        this.CLocation = CLocation;
        this.adOrg = adOrg;
        this.isactive = isactive;
        this.created = created;
        this.createdby = createdby;
        this.updated = updated;
        this.updatedby = updatedby;
        this.value = value;
        this.name = name;
        this.separator = separator;
    }
    public MWarehouse(long MWarehouseId, AdClient adClient, MWarehouse MWarehouse, CLocation CLocation, AdOrg adOrg, char isactive, Date created, long createdby, Date updated, long updatedby, String value, String name, String description, char separator, String replenishmentclass, Character isintransit, Set MWarehouses, Set SResources) {
       this.MWarehouseId = MWarehouseId;
       this.adClient = adClient;
       this.MWarehouse = MWarehouse;
       this.CLocation = CLocation;
       this.adOrg = adOrg;
       this.isactive = isactive;
       this.created = created;
       this.createdby = createdby;
       this.updated = updated;
       this.updatedby = updatedby;
       this.value = value;
       this.name = name;
       this.description = description;
       this.separator = separator;
       this.replenishmentclass = replenishmentclass;
       this.isintransit = isintransit;
       this.MWarehouses = MWarehouses;
       this.SResources = SResources;
    }
   
    public long getMWarehouseId() {
        return this.MWarehouseId;
    }
    
    public void setMWarehouseId(long MWarehouseId) {
        this.MWarehouseId = MWarehouseId;
    }
    public AdClient getAdClient() {
        return this.adClient;
    }
    
    public void setAdClient(AdClient adClient) {
        this.adClient = adClient;
    }
    public MWarehouse getMWarehouse() {
        return this.MWarehouse;
    }
    
    public void setMWarehouse(MWarehouse MWarehouse) {
        this.MWarehouse = MWarehouse;
    }
    public CLocation getCLocation() {
        return this.CLocation;
    }
    
    public void setCLocation(CLocation CLocation) {
        this.CLocation = CLocation;
    }
    public AdOrg getAdOrg() {
        return this.adOrg;
    }
    
    public void setAdOrg(AdOrg adOrg) {
        this.adOrg = adOrg;
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
    public char getSeparator() {
        return this.separator;
    }
    
    public void setSeparator(char separator) {
        this.separator = separator;
    }
    public String getReplenishmentclass() {
        return this.replenishmentclass;
    }
    
    public void setReplenishmentclass(String replenishmentclass) {
        this.replenishmentclass = replenishmentclass;
    }
    public Character getIsintransit() {
        return this.isintransit;
    }
    
    public void setIsintransit(Character isintransit) {
        this.isintransit = isintransit;
    }
    public Set getMWarehouses() {
        return this.MWarehouses;
    }
    
    public void setMWarehouses(Set MWarehouses) {
        this.MWarehouses = MWarehouses;
    }
    public Set getSResources() {
        return this.SResources;
    }
    
    public void setSResources(Set SResources) {
        this.SResources = SResources;
    }




}


