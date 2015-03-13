/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.domain.CCivilWorkConcept;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author victor
 */
public class CivilWorkConceptDAOImpl implements CivilWorkConceptDAO {
	
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;

	/**
	 * Used to save or update a civilWorkConcept.
	 */
	@Override
	public void saveOrUpdateCivilWorkConcept(CCivilWorkConcept civilWorkConcept) {
		try {
			session.saveOrUpdate(civilWorkConcept);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	
	
	/**
	 * Used to list all the civilWorkConcepts.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CCivilWorkConcept> listCivilWorkConcept() {
		List<CCivilWorkConcept> civilWorkConcepts = null;
		try {
			civilWorkConcepts = session.createQuery("from CCivilWorkConcept").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return civilWorkConcepts;
	}

	/**
	 * Used to list a single civilWorkConcept by Id.
	 */
	@Override
	public CCivilWorkConcept listCivilWorkConceptById(Long civilWorkConceptId) {
		CCivilWorkConcept civilWorkConcept = null;
		try {
			civilWorkConcept = (CCivilWorkConcept) session.get(CCivilWorkConcept.class, civilWorkConceptId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return civilWorkConcept;
	}

    @Override
    public void deleteCivilWorkConcept(CCivilWorkConcept civilWorkConceptId) {
		try {
			CCivilWorkConcept civilWorkConcept = (CCivilWorkConcept) session.get(CCivilWorkConcept.class, civilWorkConceptId);
			session.delete(civilWorkConcept);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}

    @Override
    public long findMaxId() {
        CCivilWorkConcept maxId = null;
        String SQL_QUERY = "SELECT MAX(civilWorkConceptId AS LONG) FROM CCivilWorkConcept";
        try {
            maxId = 
            (CCivilWorkConcept) session.createCriteria(CCivilWorkConcept.class)
            .addOrder(Order.desc("civilWorkConceptId"))
            .setMaxResults(1)
            .uniqueResult();
            System.out.println("Max ID: " + maxId.getCCivilWorkConceptId()); 
	} catch (Exception e) {
            e.printStackTrace();
	}
            return maxId.getCCivilWorkConceptId();
	}

   

}
