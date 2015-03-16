/**
 * Nibble
 * @author Abraham Lopez Partida
 * Date: December 17, 2014
 */

package com.mx.nibble.middleware.dao;

import com.mx.nibble.domain.CProject;
import java.util.List;

public interface ProjectDAO {
        public void saveOrUpdate(CProject project);        
        public List<CProject> searchByProjectName(String projectName);        
        public List<CProject> searchByProjectNameOrDescription(String nameOrDescription);        
        public List<CProject> listProjects();        
        public Long maxProjectId();        
}
