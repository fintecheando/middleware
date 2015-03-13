/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao;

import com.mx.nibble.domain.CCivilWorkConcept;
import java.util.List;

/**
 *
 * @author victor
 */
public interface CivilWorkConceptDAO {
        public void saveOrUpdateCivilWorkConcept(CCivilWorkConcept civilWorkConcept); 
        public void deleteCivilWorkConcept(CCivilWorkConcept civilWorkConcept);
        
        public List<CCivilWorkConcept> listCivilWorkConcept();                
        public CCivilWorkConcept listCivilWorkConceptById(Long civilWorkConceptId);
        public long findMaxId();
}
