package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0



/**
 * AdRoleIncludedId generated by hbm2java
 */
public class AdRoleIncludedId  implements java.io.Serializable {


     private long adRoleId;
     private long includedRoleId;

    public AdRoleIncludedId() {
    }

    public AdRoleIncludedId(long adRoleId, long includedRoleId) {
       this.adRoleId = adRoleId;
       this.includedRoleId = includedRoleId;
    }
   
    public long getAdRoleId() {
        return this.adRoleId;
    }
    
    public void setAdRoleId(long adRoleId) {
        this.adRoleId = adRoleId;
    }
    public long getIncludedRoleId() {
        return this.includedRoleId;
    }
    
    public void setIncludedRoleId(long includedRoleId) {
        this.includedRoleId = includedRoleId;
    }




}


