/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.dbf.project;

import com.mx.nibble.domain.CProjectphase;
import com.mx.nibble.domain.CProjecttask;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.xBaseJ.xBaseJException;

/**
 *
 * @author victor
 */
public interface LoadProjectTaskDAO {

	public void saveOrUpdateProjectTask(CProjecttask projecttask);
	public List<CProjecttask> listProjectTask();
	public CProjecttask listProjectTaskById(Long projecttaskId);
	public void deleteProjectTask(Long projecttaskId);
        
        public void loadProjectTask(String dbfPath, String dbfFileName, ArrayList<CProjectphase> cprojectphases, Double cprojectid) throws xBaseJException;
        public void insertProjectTask() throws SQLException;
        
        public void setAd_client_id(long ad_client_id);
        public void setAd_org_id(long ad_org_id);
        public void setAd_user_id(long ad_user_id);
}