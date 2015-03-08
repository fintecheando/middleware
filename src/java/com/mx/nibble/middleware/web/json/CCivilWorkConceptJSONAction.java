/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.web.json;

import com.mx.nibble.domain.CCivilWorkConcept;
import com.mx.nibble.middleware.dao.CivilWorkConceptDAO;
import com.mx.nibble.middleware.dao.CivilWorkConceptDAOImpl;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author victor
 */
public class CCivilWorkConceptJSONAction extends ActionSupport {

/**
*
*/
private static final long serialVersionUID = 1L;
private List<CCivilWorkConcept> civilWorkConcept = null;
private CivilWorkConceptDAO civilWorkConceptDao = new CivilWorkConceptDAOImpl();
private boolean success;

    public String list() {
        setCivilWorkConcept(civilWorkConceptDao.listCivilWorkConcept());
        setSuccess(true); //Se utiliza para indicar si la operación fue exitosa en este caso el valor por default es TRUE
        return SUCCESS;
    }

   

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    

    /**
     * @return the civilWorkConcept
     */
    public List<CCivilWorkConcept> getCivilWorkConcept() {
        return civilWorkConcept;
    }

    /**
     * @param civilWorkConcept the civilWorkConcept to set
     */
    public void setCivilWorkConcept(List<CCivilWorkConcept> civilWorkConcept) {
        this.civilWorkConcept = civilWorkConcept;
    }

   

} 