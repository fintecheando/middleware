package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0



/**
 * AdMenuTrlId generated by hbm2java
 */
public class AdMenuTrlId  implements java.io.Serializable {


     private long adMenuId;
     private String adLanguage;

    public AdMenuTrlId() {
    }

    public AdMenuTrlId(long adMenuId, String adLanguage) {
       this.adMenuId = adMenuId;
       this.adLanguage = adLanguage;
    }
   
    public long getAdMenuId() {
        return this.adMenuId;
    }
    
    public void setAdMenuId(long adMenuId) {
        this.adMenuId = adMenuId;
    }
    public String getAdLanguage() {
        return this.adLanguage;
    }
    
    public void setAdLanguage(String adLanguage) {
        this.adLanguage = adLanguage;
    }




}


