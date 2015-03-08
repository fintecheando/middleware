/**
 * Nibble
 * @author Abraham Lopez Partida
 * Date: December 17, 2014
 */

package com.mx.nibble.dbf.project;

import com.mx.nibble.domain.CProjectphase;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.xBaseJ.xBaseJException;

public interface LoadProjectPhaseDAO {

	public void saveOrUpdateProjectphase(CProjectphase projectphase);
	public List<CProjectphase> listProjectphase();
	public CProjectphase listProjectphaseById(Long projectphaseId);
	public void deleteProjectphase(Long projectphaseId);
        
        public void loadProjectPhase(String dbfPath, String dbfFileName,  Long cprojectId) throws xBaseJException;
        public void insertProjectPhases(Long cprojectId) throws SQLException ;
        public ArrayList<CProjectphase> getCprojectphase();
        
        public void setAd_client_id(long ad_client_id);
        public void setAd_org_id(long ad_org_id);
        public void setAd_user_id(long ad_user_id);
}