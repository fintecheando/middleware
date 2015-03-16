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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author victor
 */
public class ListProjectsJSONAction extends ActionSupport implements SessionAware {
    
    Logger logger = LoggerFactory.getLogger(ListProjectsJSONAction.class);

    private static final long serialVersionUID = 1L;
    private CProject project = null;
    private List<CProject> projects = null;
    private ProjectDAO projectsDao = new ProjectDAOImpl();
    private boolean success;
    private String message;
    private long CProjectId;

    private String id;
    private Map session;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public String list() {        
        try{
            this.setProjects(getProjectsDao().listProjects());
            setSuccess(true); //Se utiliza para indicar si la operación fue exitosa en este caso el valor por default es TRUE            
            this.setSuccess(true);
            this.setMessage("Display records");        
        }
        catch (Exception e){
            e.printStackTrace();
            this.setSuccess(false);
            this.setMessage("Failed to display record "+e.getMessage());
        }
        return SUCCESS;
    }    
    
    
    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
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
     * @return the session
     */
    public Map getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Map sess) {
        this.session = sess;
    }

    /**
     * @return the request
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * @return the response
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * @return the id
     */
    public String getId() {
        
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the CProjectId
     */
    public long getCProjectId() {
        return CProjectId;
    }

    /**
     * @param CProjectId the CProjectId to set
     */
    public void setCProjectId(long CProjectId) {
        this.CProjectId = CProjectId;
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
     * @return the projectsDao
     */
    public ProjectDAO getProjectsDao() {
        return projectsDao;
    }

    /**
     * @param projectsDao the projectsDao to set
     */
    public void setProjectsDao(ProjectDAO projectsDao) {
        this.projectsDao = projectsDao;
    }

   

}
