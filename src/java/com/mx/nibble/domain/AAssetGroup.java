package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AAssetGroup generated by hbm2java
 */
public class AAssetGroup  implements java.io.Serializable {


     private long AAssetGroupId;
     private long adClientId;
     private long adOrgId;
     private char isactive;
     private Date created;
     private long createdby;
     private Date updated;
     private long updatedby;
     private String name;
     private String description;
     private String help;
     private char isowned;
     private char isdepreciated;
     private char isoneassetperuom;
     private char iscreateasactive;
     private Character istrackissues;
     private Set MProductCategories = new HashSet(0);

    public AAssetGroup() {
    }

	
    public AAssetGroup(long AAssetGroupId, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String name, char isowned, char isdepreciated, char isoneassetperuom, char iscreateasactive) {
        this.AAssetGroupId = AAssetGroupId;
        this.adClientId = adClientId;
        this.adOrgId = adOrgId;
        this.isactive = isactive;
        this.created = created;
        this.createdby = createdby;
        this.updated = updated;
        this.updatedby = updatedby;
        this.name = name;
        this.isowned = isowned;
        this.isdepreciated = isdepreciated;
        this.isoneassetperuom = isoneassetperuom;
        this.iscreateasactive = iscreateasactive;
    }
    public AAssetGroup(long AAssetGroupId, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String name, String description, String help, char isowned, char isdepreciated, char isoneassetperuom, char iscreateasactive, Character istrackissues, Set MProductCategories) {
       this.AAssetGroupId = AAssetGroupId;
       this.adClientId = adClientId;
       this.adOrgId = adOrgId;
       this.isactive = isactive;
       this.created = created;
       this.createdby = createdby;
       this.updated = updated;
       this.updatedby = updatedby;
       this.name = name;
       this.description = description;
       this.help = help;
       this.isowned = isowned;
       this.isdepreciated = isdepreciated;
       this.isoneassetperuom = isoneassetperuom;
       this.iscreateasactive = iscreateasactive;
       this.istrackissues = istrackissues;
       this.MProductCategories = MProductCategories;
    }
   
    public long getAAssetGroupId() {
        return this.AAssetGroupId;
    }
    
    public void setAAssetGroupId(long AAssetGroupId) {
        this.AAssetGroupId = AAssetGroupId;
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
    public String getHelp() {
        return this.help;
    }
    
    public void setHelp(String help) {
        this.help = help;
    }
    public char getIsowned() {
        return this.isowned;
    }
    
    public void setIsowned(char isowned) {
        this.isowned = isowned;
    }
    public char getIsdepreciated() {
        return this.isdepreciated;
    }
    
    public void setIsdepreciated(char isdepreciated) {
        this.isdepreciated = isdepreciated;
    }
    public char getIsoneassetperuom() {
        return this.isoneassetperuom;
    }
    
    public void setIsoneassetperuom(char isoneassetperuom) {
        this.isoneassetperuom = isoneassetperuom;
    }
    public char getIscreateasactive() {
        return this.iscreateasactive;
    }
    
    public void setIscreateasactive(char iscreateasactive) {
        this.iscreateasactive = iscreateasactive;
    }
    public Character getIstrackissues() {
        return this.istrackissues;
    }
    
    public void setIstrackissues(Character istrackissues) {
        this.istrackissues = istrackissues;
    }
    public Set getMProductCategories() {
        return this.MProductCategories;
    }
    
    public void setMProductCategories(Set MProductCategories) {
        this.MProductCategories = MProductCategories;
    }




}


