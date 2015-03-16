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
public class ListProjectsJSONAction extends ActionSupport {
    
    Logger logger = LoggerFactory.getLogger(ListProjectsJSONAction.class);

    /**
    *
    */
    private static final long serialVersionUID = 1L;
    private List<CProject> projects = null;
    private ProjectDAO projecsDao = new ProjectDAOImpl();
    private boolean success;
    private int totalCount;
    private String query;

    public String list() {
        if (this.getQuery()!= null)
        {
            logger.debug("VALOR DEL QUERY "+this.getQuery());
            this.setProjects(projecsDao.searchByProjectNameOrDescription(query));        
            this.setTotalCount(this.getProjects().size());
        }
        else{
            this.setProjects(projecsDao.listProjects());        
            this.setTotalCount(this.getProjects().size());
        }
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
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

} 