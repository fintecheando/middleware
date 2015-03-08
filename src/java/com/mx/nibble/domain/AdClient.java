package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AdClient generated by hbm2java
 */
public class AdClient  implements java.io.Serializable {


     private long adClientId;
     private AdLanguage adLanguage;
     private AdReplicationstrategy adReplicationstrategy;
     private long adOrgId;
     private char isactive;
     private Date created;
     private long createdby;
     private Date updated;
     private long updatedby;
     private String value;
     private String name;
     private String description;
     private String smtphost;
     private String requestemail;
     private String requestuser;
     private String requestuserpw;
     private String requestfolder;
     private char ismultilingualdocument;
     private char issmtpauthorization;
     private char isusebetafunctions;
     private String ldapquery;
     private String modelvalidationclasses;
     private char autoarchive;
     private char mmpolicy;
     private Character emailtest;
     private char isserveremail;
     private String documentdir;
     private char ispostimmediate;
     private char iscostimmediate;
     private char storeattachmentsonfilesystem;
     private String windowsattachmentpath;
     private String unixattachmentpath;
     private char storearchiveonfilesystem;
     private String windowsarchivepath;
     private String unixarchivepath;
     private char isuseasp;
     private Set adReferences = new HashSet(0);
     private Set CRegions = new HashSet(0);
     private Set CCities = new HashSet(0);
     private Set CLocations = new HashSet(0);
     private Set adLanguages = new HashSet(0);
     private Set adOrgs = new HashSet(0);
     private Set adRoles = new HashSet(0);
     private Set CCurrencies = new HashSet(0);
     private Set adUsers = new HashSet(0);
     private Set MWarehouses = new HashSet(0);
     private Set adMenus = new HashSet(0);
     private Set adTables = new HashSet(0);
     private Set adValRules = new HashSet(0);
     private Set CUoms = new HashSet(0);
     private Set CBpartnerLocations = new HashSet(0);
     private Set adTasks = new HashSet(0);
     private Set CBpartners = new HashSet(0);
     private Set adWfNodes = new HashSet(0);
     private Set adUserRoleses = new HashSet(0);
     private Set adWindows = new HashSet(0);
     private Set adWorkflows = new HashSet(0);
     private Set adColumns = new HashSet(0);
     private Set CCountries = new HashSet(0);

    public AdClient() {
    }

	
    public AdClient(long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String value, String name, char ismultilingualdocument, char issmtpauthorization, char isusebetafunctions, char autoarchive, char mmpolicy, char isserveremail, char ispostimmediate, char iscostimmediate, char storeattachmentsonfilesystem, char storearchiveonfilesystem, char isuseasp) {
        this.adClientId = adClientId;
        this.adOrgId = adOrgId;
        this.isactive = isactive;
        this.created = created;
        this.createdby = createdby;
        this.updated = updated;
        this.updatedby = updatedby;
        this.value = value;
        this.name = name;
        this.ismultilingualdocument = ismultilingualdocument;
        this.issmtpauthorization = issmtpauthorization;
        this.isusebetafunctions = isusebetafunctions;
        this.autoarchive = autoarchive;
        this.mmpolicy = mmpolicy;
        this.isserveremail = isserveremail;
        this.ispostimmediate = ispostimmediate;
        this.iscostimmediate = iscostimmediate;
        this.storeattachmentsonfilesystem = storeattachmentsonfilesystem;
        this.storearchiveonfilesystem = storearchiveonfilesystem;
        this.isuseasp = isuseasp;
    }
    public AdClient(long adClientId, AdLanguage adLanguage, AdReplicationstrategy adReplicationstrategy, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String value, String name, String description, String smtphost, String requestemail, String requestuser, String requestuserpw, String requestfolder, char ismultilingualdocument, char issmtpauthorization, char isusebetafunctions, String ldapquery, String modelvalidationclasses, char autoarchive, char mmpolicy, Character emailtest, char isserveremail, String documentdir, char ispostimmediate, char iscostimmediate, char storeattachmentsonfilesystem, String windowsattachmentpath, String unixattachmentpath, char storearchiveonfilesystem, String windowsarchivepath, String unixarchivepath, char isuseasp, Set adReferences, Set CRegions, Set CCities, Set CLocations, Set adLanguages, Set adOrgs, Set adRoles, Set CCurrencies, Set adUsers, Set MWarehouses, Set adMenus, Set adTables, Set adValRules, Set CUoms, Set CBpartnerLocations, Set adTasks, Set CBpartners, Set adWfNodes, Set adUserRoleses, Set adWindows, Set adWorkflows, Set adColumns, Set CCountries) {
       this.adClientId = adClientId;
       this.adLanguage = adLanguage;
       this.adReplicationstrategy = adReplicationstrategy;
       this.adOrgId = adOrgId;
       this.isactive = isactive;
       this.created = created;
       this.createdby = createdby;
       this.updated = updated;
       this.updatedby = updatedby;
       this.value = value;
       this.name = name;
       this.description = description;
       this.smtphost = smtphost;
       this.requestemail = requestemail;
       this.requestuser = requestuser;
       this.requestuserpw = requestuserpw;
       this.requestfolder = requestfolder;
       this.ismultilingualdocument = ismultilingualdocument;
       this.issmtpauthorization = issmtpauthorization;
       this.isusebetafunctions = isusebetafunctions;
       this.ldapquery = ldapquery;
       this.modelvalidationclasses = modelvalidationclasses;
       this.autoarchive = autoarchive;
       this.mmpolicy = mmpolicy;
       this.emailtest = emailtest;
       this.isserveremail = isserveremail;
       this.documentdir = documentdir;
       this.ispostimmediate = ispostimmediate;
       this.iscostimmediate = iscostimmediate;
       this.storeattachmentsonfilesystem = storeattachmentsonfilesystem;
       this.windowsattachmentpath = windowsattachmentpath;
       this.unixattachmentpath = unixattachmentpath;
       this.storearchiveonfilesystem = storearchiveonfilesystem;
       this.windowsarchivepath = windowsarchivepath;
       this.unixarchivepath = unixarchivepath;
       this.isuseasp = isuseasp;
       this.adReferences = adReferences;
       this.CRegions = CRegions;
       this.CCities = CCities;
       this.CLocations = CLocations;
       this.adLanguages = adLanguages;
       this.adOrgs = adOrgs;
       this.adRoles = adRoles;
       this.CCurrencies = CCurrencies;
       this.adUsers = adUsers;
       this.MWarehouses = MWarehouses;
       this.adMenus = adMenus;
       this.adTables = adTables;
       this.adValRules = adValRules;
       this.CUoms = CUoms;
       this.CBpartnerLocations = CBpartnerLocations;
       this.adTasks = adTasks;
       this.CBpartners = CBpartners;
       this.adWfNodes = adWfNodes;
       this.adUserRoleses = adUserRoleses;
       this.adWindows = adWindows;
       this.adWorkflows = adWorkflows;
       this.adColumns = adColumns;
       this.CCountries = CCountries;
    }
   
    public long getAdClientId() {
        return this.adClientId;
    }
    
    public void setAdClientId(long adClientId) {
        this.adClientId = adClientId;
    }
    public AdLanguage getAdLanguage() {
        return this.adLanguage;
    }
    
    public void setAdLanguage(AdLanguage adLanguage) {
        this.adLanguage = adLanguage;
    }
    public AdReplicationstrategy getAdReplicationstrategy() {
        return this.adReplicationstrategy;
    }
    
    public void setAdReplicationstrategy(AdReplicationstrategy adReplicationstrategy) {
        this.adReplicationstrategy = adReplicationstrategy;
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
    public String getSmtphost() {
        return this.smtphost;
    }
    
    public void setSmtphost(String smtphost) {
        this.smtphost = smtphost;
    }
    public String getRequestemail() {
        return this.requestemail;
    }
    
    public void setRequestemail(String requestemail) {
        this.requestemail = requestemail;
    }
    public String getRequestuser() {
        return this.requestuser;
    }
    
    public void setRequestuser(String requestuser) {
        this.requestuser = requestuser;
    }
    public String getRequestuserpw() {
        return this.requestuserpw;
    }
    
    public void setRequestuserpw(String requestuserpw) {
        this.requestuserpw = requestuserpw;
    }
    public String getRequestfolder() {
        return this.requestfolder;
    }
    
    public void setRequestfolder(String requestfolder) {
        this.requestfolder = requestfolder;
    }
    public char getIsmultilingualdocument() {
        return this.ismultilingualdocument;
    }
    
    public void setIsmultilingualdocument(char ismultilingualdocument) {
        this.ismultilingualdocument = ismultilingualdocument;
    }
    public char getIssmtpauthorization() {
        return this.issmtpauthorization;
    }
    
    public void setIssmtpauthorization(char issmtpauthorization) {
        this.issmtpauthorization = issmtpauthorization;
    }
    public char getIsusebetafunctions() {
        return this.isusebetafunctions;
    }
    
    public void setIsusebetafunctions(char isusebetafunctions) {
        this.isusebetafunctions = isusebetafunctions;
    }
    public String getLdapquery() {
        return this.ldapquery;
    }
    
    public void setLdapquery(String ldapquery) {
        this.ldapquery = ldapquery;
    }
    public String getModelvalidationclasses() {
        return this.modelvalidationclasses;
    }
    
    public void setModelvalidationclasses(String modelvalidationclasses) {
        this.modelvalidationclasses = modelvalidationclasses;
    }
    public char getAutoarchive() {
        return this.autoarchive;
    }
    
    public void setAutoarchive(char autoarchive) {
        this.autoarchive = autoarchive;
    }
    public char getMmpolicy() {
        return this.mmpolicy;
    }
    
    public void setMmpolicy(char mmpolicy) {
        this.mmpolicy = mmpolicy;
    }
    public Character getEmailtest() {
        return this.emailtest;
    }
    
    public void setEmailtest(Character emailtest) {
        this.emailtest = emailtest;
    }
    public char getIsserveremail() {
        return this.isserveremail;
    }
    
    public void setIsserveremail(char isserveremail) {
        this.isserveremail = isserveremail;
    }
    public String getDocumentdir() {
        return this.documentdir;
    }
    
    public void setDocumentdir(String documentdir) {
        this.documentdir = documentdir;
    }
    public char getIspostimmediate() {
        return this.ispostimmediate;
    }
    
    public void setIspostimmediate(char ispostimmediate) {
        this.ispostimmediate = ispostimmediate;
    }
    public char getIscostimmediate() {
        return this.iscostimmediate;
    }
    
    public void setIscostimmediate(char iscostimmediate) {
        this.iscostimmediate = iscostimmediate;
    }
    public char getStoreattachmentsonfilesystem() {
        return this.storeattachmentsonfilesystem;
    }
    
    public void setStoreattachmentsonfilesystem(char storeattachmentsonfilesystem) {
        this.storeattachmentsonfilesystem = storeattachmentsonfilesystem;
    }
    public String getWindowsattachmentpath() {
        return this.windowsattachmentpath;
    }
    
    public void setWindowsattachmentpath(String windowsattachmentpath) {
        this.windowsattachmentpath = windowsattachmentpath;
    }
    public String getUnixattachmentpath() {
        return this.unixattachmentpath;
    }
    
    public void setUnixattachmentpath(String unixattachmentpath) {
        this.unixattachmentpath = unixattachmentpath;
    }
    public char getStorearchiveonfilesystem() {
        return this.storearchiveonfilesystem;
    }
    
    public void setStorearchiveonfilesystem(char storearchiveonfilesystem) {
        this.storearchiveonfilesystem = storearchiveonfilesystem;
    }
    public String getWindowsarchivepath() {
        return this.windowsarchivepath;
    }
    
    public void setWindowsarchivepath(String windowsarchivepath) {
        this.windowsarchivepath = windowsarchivepath;
    }
    public String getUnixarchivepath() {
        return this.unixarchivepath;
    }
    
    public void setUnixarchivepath(String unixarchivepath) {
        this.unixarchivepath = unixarchivepath;
    }
    public char getIsuseasp() {
        return this.isuseasp;
    }
    
    public void setIsuseasp(char isuseasp) {
        this.isuseasp = isuseasp;
    }
    public Set getAdReferences() {
        return this.adReferences;
    }
    
    public void setAdReferences(Set adReferences) {
        this.adReferences = adReferences;
    }
    public Set getCRegions() {
        return this.CRegions;
    }
    
    public void setCRegions(Set CRegions) {
        this.CRegions = CRegions;
    }
    public Set getCCities() {
        return this.CCities;
    }
    
    public void setCCities(Set CCities) {
        this.CCities = CCities;
    }
    public Set getCLocations() {
        return this.CLocations;
    }
    
    public void setCLocations(Set CLocations) {
        this.CLocations = CLocations;
    }
    public Set getAdLanguages() {
        return this.adLanguages;
    }
    
    public void setAdLanguages(Set adLanguages) {
        this.adLanguages = adLanguages;
    }
    public Set getAdOrgs() {
        return this.adOrgs;
    }
    
    public void setAdOrgs(Set adOrgs) {
        this.adOrgs = adOrgs;
    }
    public Set getAdRoles() {
        return this.adRoles;
    }
    
    public void setAdRoles(Set adRoles) {
        this.adRoles = adRoles;
    }
    public Set getCCurrencies() {
        return this.CCurrencies;
    }
    
    public void setCCurrencies(Set CCurrencies) {
        this.CCurrencies = CCurrencies;
    }
    public Set getAdUsers() {
        return this.adUsers;
    }
    
    public void setAdUsers(Set adUsers) {
        this.adUsers = adUsers;
    }
    public Set getMWarehouses() {
        return this.MWarehouses;
    }
    
    public void setMWarehouses(Set MWarehouses) {
        this.MWarehouses = MWarehouses;
    }
    public Set getAdMenus() {
        return this.adMenus;
    }
    
    public void setAdMenus(Set adMenus) {
        this.adMenus = adMenus;
    }
    public Set getAdTables() {
        return this.adTables;
    }
    
    public void setAdTables(Set adTables) {
        this.adTables = adTables;
    }
    public Set getAdValRules() {
        return this.adValRules;
    }
    
    public void setAdValRules(Set adValRules) {
        this.adValRules = adValRules;
    }
    public Set getCUoms() {
        return this.CUoms;
    }
    
    public void setCUoms(Set CUoms) {
        this.CUoms = CUoms;
    }
    public Set getCBpartnerLocations() {
        return this.CBpartnerLocations;
    }
    
    public void setCBpartnerLocations(Set CBpartnerLocations) {
        this.CBpartnerLocations = CBpartnerLocations;
    }
    public Set getAdTasks() {
        return this.adTasks;
    }
    
    public void setAdTasks(Set adTasks) {
        this.adTasks = adTasks;
    }
    public Set getCBpartners() {
        return this.CBpartners;
    }
    
    public void setCBpartners(Set CBpartners) {
        this.CBpartners = CBpartners;
    }
    public Set getAdWfNodes() {
        return this.adWfNodes;
    }
    
    public void setAdWfNodes(Set adWfNodes) {
        this.adWfNodes = adWfNodes;
    }
    public Set getAdUserRoleses() {
        return this.adUserRoleses;
    }
    
    public void setAdUserRoleses(Set adUserRoleses) {
        this.adUserRoleses = adUserRoleses;
    }
    public Set getAdWindows() {
        return this.adWindows;
    }
    
    public void setAdWindows(Set adWindows) {
        this.adWindows = adWindows;
    }
    public Set getAdWorkflows() {
        return this.adWorkflows;
    }
    
    public void setAdWorkflows(Set adWorkflows) {
        this.adWorkflows = adWorkflows;
    }
    public Set getAdColumns() {
        return this.adColumns;
    }
    
    public void setAdColumns(Set adColumns) {
        this.adColumns = adColumns;
    }
    public Set getCCountries() {
        return this.CCountries;
    }
    
    public void setCCountries(Set CCountries) {
        this.CCountries = CCountries;
    }




}


