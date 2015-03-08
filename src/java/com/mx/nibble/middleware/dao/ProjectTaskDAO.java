/**
 * Nibble
 * @author Abraham Lopez Partida
 * Date: December 17, 2014
 */

package com.mx.nibble.middleware.dao;


import com.mx.nibble.domain.CProjecttask;
import java.util.List;

public interface ProjectTaskDAO {
        public void saveOrUpdate(CProjecttask cprojecttask);
        public List<CProjecttask> searchByKeyAndName(Long cprojectphaseId, String key, String name);
        public Long maxProductTaskId();
}