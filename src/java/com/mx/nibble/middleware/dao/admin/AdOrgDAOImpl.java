/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.admin;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.domain.AdOrg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author victor
 */
public class AdOrgDAOImpl implements AdOrgDAO{
    
    Logger logger = LoggerFactory.getLogger(AdOrgDAOImpl.class);
        
        @SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
         
	/**
	 * Used to save or update a AdOrg.
	 */
	@Override
	public void saveOrUpdateAdOrg(AdOrg adOrg) {
		try {
                        session.beginTransaction();
			session.saveOrUpdate(adOrg);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Used to delete a AdOrg.
	 */
	@Override
	public void deleteAdOrg(Long adOrgId) {
		try {
                        session.beginTransaction();
			AdOrg adOrg = (AdOrg) session.get(AdOrg.class, adOrgId);
			session.delete(adOrg);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} 
	}
	
        /**
	 * Used to list all the AdOrg.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdOrg> listAdOrg() {
                
		List<AdOrg> adOrgs = null;                
		try {
                    session.beginTransaction();
                    logger.debug("CARGANDO AdOrg");
			adOrgs = session.createQuery("from AdOrg ORDER BY name ASC").list();
		} catch (Exception e) {
			e.printStackTrace();
                        session.getTransaction().rollback();
		}
		return adOrgs;
	}
        
	
        
        /**
	 * Used to list a single AdOrg by Id.
	 */
	@Override
	public AdOrg listAdOrgById(Long adOrgId) {
		AdOrg adOrg = null;
		try {
                        session.beginTransaction();
			adOrg = (AdOrg) session.get(AdOrg.class, adOrgId);
		} catch (Exception e) {
			e.printStackTrace();
                        session.getTransaction().rollback();
		}
		return adOrg;
	}
    
}
