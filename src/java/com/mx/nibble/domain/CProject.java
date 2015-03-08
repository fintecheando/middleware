package com.mx.nibble.domain;
// Generated Dec 19, 2014 6:33:59 PM by Hibernate Tools 3.6.0


import java.math.BigDecimal;
import java.util.Date;

/**
 * CProject generated by hbm2java
 */
public class CProject  implements java.io.Serializable {


     private long CProjectId;
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
     private String note;
     private char issummary;
     private Long adUserId;
     private Long CBpartnerId;
     private Long CBpartnerLocationId;
     private String poreference;
     private Long CPaymenttermId;
     private long CCurrencyId;
     private Long MPricelistVersionId;
     private Long CCampaignId;
     private char iscommitment;
     private BigDecimal plannedamt;
     private BigDecimal plannedqty;
     private BigDecimal plannedmarginamt;
     private BigDecimal committedamt;
     private Date datecontract;
     private Date datefinish;
     private Character generateto;
     private char processed;
     private Long salesrepId;
     private Character copyfrom;
     private Long CProjecttypeId;
     private BigDecimal committedqty;
     private BigDecimal invoicedamt;
     private BigDecimal invoicedqty;
     private BigDecimal projectbalanceamt;
     private Long CPhaseId;
     private char iscommitceiling;
     private Long MWarehouseId;
     private Character projectcategory;
     private Character processing;
     private Long CBpartnersrId;
     private char projinvoicerule;
     private char projectlinelevel;

    public CProject() {
    }

	
    public CProject(long CProjectId, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String value, String name, char issummary, long CCurrencyId, char iscommitment, BigDecimal plannedamt, BigDecimal plannedqty, BigDecimal plannedmarginamt, BigDecimal committedamt, char processed, BigDecimal committedqty, BigDecimal invoicedamt, BigDecimal invoicedqty, BigDecimal projectbalanceamt, char iscommitceiling, char projinvoicerule, char projectlinelevel) {
        this.CProjectId = CProjectId;
        this.adClientId = adClientId;
        this.adOrgId = adOrgId;
        this.isactive = isactive;
        this.created = created;
        this.createdby = createdby;
        this.updated = updated;
        this.updatedby = updatedby;
        this.value = value;
        this.name = name;
        this.issummary = issummary;
        this.CCurrencyId = CCurrencyId;
        this.iscommitment = iscommitment;
        this.plannedamt = plannedamt;
        this.plannedqty = plannedqty;
        this.plannedmarginamt = plannedmarginamt;
        this.committedamt = committedamt;
        this.processed = processed;
        this.committedqty = committedqty;
        this.invoicedamt = invoicedamt;
        this.invoicedqty = invoicedqty;
        this.projectbalanceamt = projectbalanceamt;
        this.iscommitceiling = iscommitceiling;
        this.projinvoicerule = projinvoicerule;
        this.projectlinelevel = projectlinelevel;
    }
    public CProject(long CProjectId, long adClientId, long adOrgId, char isactive, Date created, long createdby, Date updated, long updatedby, String value, String name, String description, String note, char issummary, Long adUserId, Long CBpartnerId, Long CBpartnerLocationId, String poreference, Long CPaymenttermId, long CCurrencyId, Long MPricelistVersionId, Long CCampaignId, char iscommitment, BigDecimal plannedamt, BigDecimal plannedqty, BigDecimal plannedmarginamt, BigDecimal committedamt, Date datecontract, Date datefinish, Character generateto, char processed, Long salesrepId, Character copyfrom, Long CProjecttypeId, BigDecimal committedqty, BigDecimal invoicedamt, BigDecimal invoicedqty, BigDecimal projectbalanceamt, Long CPhaseId, char iscommitceiling, Long MWarehouseId, Character projectcategory, Character processing, Long CBpartnersrId, char projinvoicerule, char projectlinelevel) {
       this.CProjectId = CProjectId;
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
       this.note = note;
       this.issummary = issummary;
       this.adUserId = adUserId;
       this.CBpartnerId = CBpartnerId;
       this.CBpartnerLocationId = CBpartnerLocationId;
       this.poreference = poreference;
       this.CPaymenttermId = CPaymenttermId;
       this.CCurrencyId = CCurrencyId;
       this.MPricelistVersionId = MPricelistVersionId;
       this.CCampaignId = CCampaignId;
       this.iscommitment = iscommitment;
       this.plannedamt = plannedamt;
       this.plannedqty = plannedqty;
       this.plannedmarginamt = plannedmarginamt;
       this.committedamt = committedamt;
       this.datecontract = datecontract;
       this.datefinish = datefinish;
       this.generateto = generateto;
       this.processed = processed;
       this.salesrepId = salesrepId;
       this.copyfrom = copyfrom;
       this.CProjecttypeId = CProjecttypeId;
       this.committedqty = committedqty;
       this.invoicedamt = invoicedamt;
       this.invoicedqty = invoicedqty;
       this.projectbalanceamt = projectbalanceamt;
       this.CPhaseId = CPhaseId;
       this.iscommitceiling = iscommitceiling;
       this.MWarehouseId = MWarehouseId;
       this.projectcategory = projectcategory;
       this.processing = processing;
       this.CBpartnersrId = CBpartnersrId;
       this.projinvoicerule = projinvoicerule;
       this.projectlinelevel = projectlinelevel;
    }
   
    public long getCProjectId() {
        return this.CProjectId;
    }
    
    public void setCProjectId(long CProjectId) {
        this.CProjectId = CProjectId;
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
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    public char getIssummary() {
        return this.issummary;
    }
    
    public void setIssummary(char issummary) {
        this.issummary = issummary;
    }
    public Long getAdUserId() {
        return this.adUserId;
    }
    
    public void setAdUserId(Long adUserId) {
        this.adUserId = adUserId;
    }
    public Long getCBpartnerId() {
        return this.CBpartnerId;
    }
    
    public void setCBpartnerId(Long CBpartnerId) {
        this.CBpartnerId = CBpartnerId;
    }
    public Long getCBpartnerLocationId() {
        return this.CBpartnerLocationId;
    }
    
    public void setCBpartnerLocationId(Long CBpartnerLocationId) {
        this.CBpartnerLocationId = CBpartnerLocationId;
    }
    public String getPoreference() {
        return this.poreference;
    }
    
    public void setPoreference(String poreference) {
        this.poreference = poreference;
    }
    public Long getCPaymenttermId() {
        return this.CPaymenttermId;
    }
    
    public void setCPaymenttermId(Long CPaymenttermId) {
        this.CPaymenttermId = CPaymenttermId;
    }
    public long getCCurrencyId() {
        return this.CCurrencyId;
    }
    
    public void setCCurrencyId(long CCurrencyId) {
        this.CCurrencyId = CCurrencyId;
    }
    public Long getMPricelistVersionId() {
        return this.MPricelistVersionId;
    }
    
    public void setMPricelistVersionId(Long MPricelistVersionId) {
        this.MPricelistVersionId = MPricelistVersionId;
    }
    public Long getCCampaignId() {
        return this.CCampaignId;
    }
    
    public void setCCampaignId(Long CCampaignId) {
        this.CCampaignId = CCampaignId;
    }
    public char getIscommitment() {
        return this.iscommitment;
    }
    
    public void setIscommitment(char iscommitment) {
        this.iscommitment = iscommitment;
    }
    public BigDecimal getPlannedamt() {
        return this.plannedamt;
    }
    
    public void setPlannedamt(BigDecimal plannedamt) {
        this.plannedamt = plannedamt;
    }
    public BigDecimal getPlannedqty() {
        return this.plannedqty;
    }
    
    public void setPlannedqty(BigDecimal plannedqty) {
        this.plannedqty = plannedqty;
    }
    public BigDecimal getPlannedmarginamt() {
        return this.plannedmarginamt;
    }
    
    public void setPlannedmarginamt(BigDecimal plannedmarginamt) {
        this.plannedmarginamt = plannedmarginamt;
    }
    public BigDecimal getCommittedamt() {
        return this.committedamt;
    }
    
    public void setCommittedamt(BigDecimal committedamt) {
        this.committedamt = committedamt;
    }
    public Date getDatecontract() {
        return this.datecontract;
    }
    
    public void setDatecontract(Date datecontract) {
        this.datecontract = datecontract;
    }
    public Date getDatefinish() {
        return this.datefinish;
    }
    
    public void setDatefinish(Date datefinish) {
        this.datefinish = datefinish;
    }
    public Character getGenerateto() {
        return this.generateto;
    }
    
    public void setGenerateto(Character generateto) {
        this.generateto = generateto;
    }
    public char getProcessed() {
        return this.processed;
    }
    
    public void setProcessed(char processed) {
        this.processed = processed;
    }
    public Long getSalesrepId() {
        return this.salesrepId;
    }
    
    public void setSalesrepId(Long salesrepId) {
        this.salesrepId = salesrepId;
    }
    public Character getCopyfrom() {
        return this.copyfrom;
    }
    
    public void setCopyfrom(Character copyfrom) {
        this.copyfrom = copyfrom;
    }
    public Long getCProjecttypeId() {
        return this.CProjecttypeId;
    }
    
    public void setCProjecttypeId(Long CProjecttypeId) {
        this.CProjecttypeId = CProjecttypeId;
    }
    public BigDecimal getCommittedqty() {
        return this.committedqty;
    }
    
    public void setCommittedqty(BigDecimal committedqty) {
        this.committedqty = committedqty;
    }
    public BigDecimal getInvoicedamt() {
        return this.invoicedamt;
    }
    
    public void setInvoicedamt(BigDecimal invoicedamt) {
        this.invoicedamt = invoicedamt;
    }
    public BigDecimal getInvoicedqty() {
        return this.invoicedqty;
    }
    
    public void setInvoicedqty(BigDecimal invoicedqty) {
        this.invoicedqty = invoicedqty;
    }
    public BigDecimal getProjectbalanceamt() {
        return this.projectbalanceamt;
    }
    
    public void setProjectbalanceamt(BigDecimal projectbalanceamt) {
        this.projectbalanceamt = projectbalanceamt;
    }
    public Long getCPhaseId() {
        return this.CPhaseId;
    }
    
    public void setCPhaseId(Long CPhaseId) {
        this.CPhaseId = CPhaseId;
    }
    public char getIscommitceiling() {
        return this.iscommitceiling;
    }
    
    public void setIscommitceiling(char iscommitceiling) {
        this.iscommitceiling = iscommitceiling;
    }
    public Long getMWarehouseId() {
        return this.MWarehouseId;
    }
    
    public void setMWarehouseId(Long MWarehouseId) {
        this.MWarehouseId = MWarehouseId;
    }
    public Character getProjectcategory() {
        return this.projectcategory;
    }
    
    public void setProjectcategory(Character projectcategory) {
        this.projectcategory = projectcategory;
    }
    public Character getProcessing() {
        return this.processing;
    }
    
    public void setProcessing(Character processing) {
        this.processing = processing;
    }
    public Long getCBpartnersrId() {
        return this.CBpartnersrId;
    }
    
    public void setCBpartnersrId(Long CBpartnersrId) {
        this.CBpartnersrId = CBpartnersrId;
    }
    public char getProjinvoicerule() {
        return this.projinvoicerule;
    }
    
    public void setProjinvoicerule(char projinvoicerule) {
        this.projinvoicerule = projinvoicerule;
    }
    public char getProjectlinelevel() {
        return this.projectlinelevel;
    }
    
    public void setProjectlinelevel(char projectlinelevel) {
        this.projectlinelevel = projectlinelevel;
    }




}


