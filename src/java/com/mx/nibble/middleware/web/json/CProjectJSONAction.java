/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.web.json;

import com.mx.nibble.domain.CProject;
import com.mx.nibble.middleware.dao.ProjectDAO;
import com.mx.nibble.middleware.dao.ProjectDAOImpl;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author victor
 */
@Result(type = "json")
public class CProjectJSONAction extends ActionSupport {

/**
*
*/
private static final long serialVersionUID = 1L;
private List<CProject> projects = null;
private ProjectDAO projecsDao = new ProjectDAOImpl();
private boolean success;
private int totalCount;

    public String list() {
        this.setProjects(projecsDao.listProjects());        
        this.setTotalCount(projecsDao.listProjects().size());
        this.setSuccess(true); //Se utiliza para indicar si la operación fue exitosa en este caso el valor por default es TRUE
        return SUCCESS;
    }

   

    

    /**
     * @return the projects
     */
    public List<CProject> getProjects() {
        return projects;
    }

    /**
     * @param projects the projects to set
     */
    public void setProjects(List<CProject> projects) {
        this.projects = projects;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

   

} 