package com.mx.nibble.dbf.project;

import java.util.Date;

public class ProjectTask {
    public Double projectid;
    public Long phaseid;
    public String key;
    public String name;
    public Double taskprefixid;
    public String description;
    public String unit;
    public Double qty;
    public Double unitarycost;
    public Double plannedamt;
    public Long productid;

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public Double getProjectid() {
        return projectid;
    }

    public void setProjectid(Double projectid) {
        this.projectid = projectid;
    }

    public Long getPhaseid() {
        return phaseid;
    }

    public void setPhaseid(Long phaseid) {
        this.phaseid = phaseid;
    }

    public Double getTaskprefixid() {
        return taskprefixid;
    }

    public void setTaskPrefixId(Double taskprefixid) {
        this.taskprefixid = taskprefixid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getUnitarycost() {
        return unitarycost;
    }

    public void setUnitarycost(Double unitarycost) {
        this.unitarycost = unitarycost;
    }

    public Double getPlannedamt() {
        return plannedamt;
    }

    public void setPlannedamt(Double plannedamt) {
        this.plannedamt = plannedamt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
