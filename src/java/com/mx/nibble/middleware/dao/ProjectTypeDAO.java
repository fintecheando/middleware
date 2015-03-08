/**
 * Nibble
 * @author Abraham Lopez Partida
 * Date: December 17, 2014
 */

package com.mx.nibble.middleware.dao;

import com.mx.nibble.domain.CProjectType;
import java.util.List;

public interface ProjectTypeDAO {

        public void saveOrUpdate(CProjectType projectType);        
        public List<CProjectType> searchByProjectName(String projectName);        
        public Long maxProjectTypeId();
        //public User2 listUserById(Long userId);
        //public void deleteUser(Long userId);    
}



