/**
 * Nibble
 * @author Abraham Lopez Partida
 * Date: December 17, 2014
 */

package com.mx.nibble.middleware.dao;

import com.mx.nibble.domain.CProjectphase;
import java.util.List;

public interface ProjectPhaseDAO {
        public void saveOrUpdate(CProjectphase cprojectphase);        
        public List<CProjectphase> searchByKey(Long cprojectid, String key, Long uniqueid);
        public List<CProjectphase> searchByKeyAndPhaseKey(Long cprojectid, String key, String phasekey, Long uniqueid);
        public Long maxProjectPhaseId();
}