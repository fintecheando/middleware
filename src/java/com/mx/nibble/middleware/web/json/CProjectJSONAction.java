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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author victor
 */
@Result(type = "json")
public class CProjectJSONAction extends ActionSupport {
Logger logger = LoggerFactory.getLogger(CProjectJSONAction.class);
/**
*
*/
private static final long serialVersionUID = 1L;
private List<CProject> projects = null;
private CProject project = null;
private ProjectDAO projecsDao = new ProjectDAOImpl();
private boolean success;
private int totalCount;
private String CProjectId;

    public String list() {
        this.setProjects(getProjecsDao().listProjects());        
        this.setTotalCount(getProjecsDao().listProjects().size());
        this.setSuccess(true); //Se utiliza para indicar si la operación fue exitosa en este caso el valor por default es TRUE
        return SUCCESS;
    }

   public String find() {
        logger.debug("SE BUSCA EL PROJECTO " + this.getCProjectId());
                this.setProject(getProjecsDao().searchByProjectId(new Long(this.getCProjectId()).longValue()));
                this.setTotalCount(1);
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

    /**
     * @return the CProjectId
     */
    public String getCProjectId() {
        return CProjectId;
    }

    /**
     * @param CProjectId the CProjectId to set
     */
    public void setCProjectId(String CProjectId) {
        this.CProjectId = CProjectId;
    }

    /**
     * @return the projecsDao
     */
    public ProjectDAO getProjecsDao() {
        return projecsDao;
    }

    /**
     * @param projecsDao the projecsDao to set
     */
    public void setProjecsDao(ProjectDAO projecsDao) {
        this.projecsDao = projecsDao;
    }

    /**
     * @return the project
     */
    public CProject getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(CProject project) {
        this.project = project;
    }

   

} 