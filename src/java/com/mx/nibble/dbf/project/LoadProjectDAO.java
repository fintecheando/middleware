/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.dbf.project;

import com.mx.nibble.domain.CProject;
import java.io.IOException;
import java.util.List;
import org.xBaseJ.xBaseJException;

/**
 *
 * @author 43507296
 */
public interface LoadProjectDAO {

	public void saveOrUpdateProject(CProject project);
	public List<CProject> listProject();
	public CProject listProjectById(Long projectId);
	public void deleteProject(Long projectId);
        
        public void loadProject(String dbfPath, String dbfFileName)throws xBaseJException,IOException;
        public void insertProject();
        public CProject getCProject();
        
        public void setAd_client_id(long ad_client_id);
        public void setAd_org_id(long ad_org_id);
        public void setAd_user_id(long ad_user_id);
}
