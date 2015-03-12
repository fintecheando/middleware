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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author victor
 */
public class CCivilWorkConceptJSONAction extends ActionSupport  implements ServletRequestAware {
private HttpServletRequest request;

/**
*
*/
private static final long serialVersionUID = 1L;
private CCivilWorkConcept civilWorkConcept = null;
private List<CCivilWorkConcept> civilWorkConcepts = null;
private CivilWorkConceptDAO civilWorkConceptDao = new CivilWorkConceptDAOImpl();
private boolean success;
private String message;
private long CCivilWorkConceptId;
private String name;
private String type;
private String code;


//private List<CCivilWorkConcept> data;

@Override
public void setServletRequest(HttpServletRequest servletRequest) {
    // TODO Auto-generated method stub
    this.request = servletRequest;
}

    public String list() {
        setCivilWorkConcepts(civilWorkConceptDao.listCivilWorkConcept());
        setSuccess(true); //Se utiliza para indicar si la operación fue exitosa en este caso el valor por default es TRUE
        return SUCCESS;
    }
    
    public void saveOrUpdate(){
        System.out.println("ID A GUARDAR "+ this.getCCivilWorkConceptId());
        System.out.println("ID A getCode "+ this.getCode());
        System.out.println("ID A getName "+ this.getName());
        System.out.println("ID A getType "+ this.getType());
        //System.out.println("DATOS DEL REQUEST "+ this.getData());        
        
        civilWorkConcept.setCode(this.getCode());
        civilWorkConcept.setName(this.getName());
        civilWorkConcept.setType(this.getType());
            
        civilWorkConceptDao.saveOrUpdateCivilWorkConcept(civilWorkConcept);
    }
    
    public void delete(){        
        this.getCCivilWorkConceptId();        
        this.getCode();
        this.getName();
        this.getCode();
        System.out.println("ID A BORRAR "+ this.getCCivilWorkConceptId());
        
        civilWorkConceptDao.deleteCivilWorkConcept(civilWorkConcept);
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
    public CCivilWorkConcept getCivilWorkConcept() {
        return civilWorkConcept;
    }

    /**
     * @param civilWorkConcept the civilWorkConcept to set
     */
    public void setCivilWorkConcept(CCivilWorkConcept civilWorkConcept) {
        this.civilWorkConcept = civilWorkConcept;
    }

    /**
     * @return the civilWorkConcepts
     */
    public List<CCivilWorkConcept> getCivilWorkConcepts() {
        return civilWorkConcepts;
    }

    /**
     * @param civilWorkConcepts the civilWorkConcepts to set
     */
    public void setCivilWorkConcepts(List<CCivilWorkConcept> civilWorkConcepts) {
        this.civilWorkConcepts = civilWorkConcepts;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the CCivilWorkConceptId
     */
    public long getCCivilWorkConceptId() {
        return CCivilWorkConceptId;
    }

    /**
     * @param CCivilWorkConceptId the CCivilWorkConceptId to set
     */
    public void setCCivilWorkConceptId(long CCivilWorkConceptId) {
        this.CCivilWorkConceptId = CCivilWorkConceptId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    }
