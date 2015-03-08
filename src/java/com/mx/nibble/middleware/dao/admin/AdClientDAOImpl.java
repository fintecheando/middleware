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
import com.mx.nibble.domain.AdClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author victor
 */
public class AdClientDAOImpl implements AdClientDAO{
    
    Logger logger = LoggerFactory.getLogger(AdClientDAOImpl.class);
    
        //private SessionFactory factory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        
        @SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
         
	/**
	 * Used to save or update a AdClient.
	 */
	@Override
	public void saveOrUpdateAdClient(AdClient adClient) {
		try {
                        session.beginTransaction();
			session.saveOrUpdate(adClient);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Used to delete a AdClient.
	 */
	@Override
	public void deleteAdClient(Long adClientId) {
		try {
                        session.beginTransaction();
			AdClient adClient = (AdClient) session.get(AdClient.class, adClientId);
			session.delete(adClient);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} 
	}
	
	/**
	 * Used to list all the AdClient.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AdClient> listAdClient() {
		List<AdClient> adClients = null;                
		try {
                    session.beginTransaction();
                    logger.debug("CARGANDO CLIENTES");
			adClients = session.createQuery("from AdClient ORDER BY name ASC").list();
		} catch (Exception e) {
			e.printStackTrace();
                        session.getTransaction().rollback();
		}
		return adClients;
	}
        
        
        /**
	 * Used to list a single AdClient by Id.
	 */
	@Override
	public AdClient listAdClientById(Long adClientId) {
		AdClient adClient = null;
		try {
                        session.beginTransaction();
			adClient = (AdClient) session.get(AdClient.class, adClientId);
		} catch (Exception e) {
			e.printStackTrace();
                        session.getTransaction().rollback();
		}
		return adClient;
	}
    
}
