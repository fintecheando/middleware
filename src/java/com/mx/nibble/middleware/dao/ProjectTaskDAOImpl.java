/**
 * Nibble
 * @author Abraham Lopez Partida
 * Date: December 17, 2014
 */

package com.mx.nibble.middleware.dao;

import java.util.*;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.domain.CProjectType;
import com.mx.nibble.domain.CProjecttask;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ProjectTaskDAOImpl implements ProjectTaskDAO{
               // private SessionFactory factory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();

	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;

	/**
	 * Used to save or update a user.
	 */
	@Override
	public void saveOrUpdate(CProjecttask cprojecttask){
                        try {                                
                                //Session session = factory.openSession();
                                Transaction tx = null;
                                tx = session.beginTransaction();
                                session.saveOrUpdate(cprojecttask);
                                tx.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
	}

                public List<CProjecttask> searchByKeyAndName(Long cprojectphaseId, String key, String name){
                        List<CProjecttask> list  = null;
                        try {                                                            
                                //Session session = factory.openSession();
                                Query query = session.createQuery("FROM CProjecttask WHERE CProjectphaseId = :cprojectphaseId AND key = :key AND name = :name");
                                query.setParameter("cprojectphaseId", cprojectphaseId);
                                query.setParameter("key", key);
                                query.setParameter("name", name);
                                list  = query.list();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }                                
                        return list;
                }
       
                public Long maxProductTaskId(){
                        List <Long> list = null;
                        Long max = null;
                        try {
                                //Session session = factory.openSession();
                                Query query = session.createSQLQuery("SELECT MAX(c_projecttask_id) FROM C_ProjectTask").addScalar("MAX", Hibernate.LONG );
                                list  = query.list();
                                max = list.get(0);
                                max = ((max == null) ? 1000000 : max+1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return max;
                }
}