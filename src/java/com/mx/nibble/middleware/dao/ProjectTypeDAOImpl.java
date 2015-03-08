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
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectTypeDAOImpl implements ProjectTypeDAO{
           //     private SessionFactory factory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;

	/**
	 * Used to save or update a user.
	 */
	@Override
	public void saveOrUpdate(CProjectType projectType) {
                        try {                                
                                //Session session = factory.openSession();
                                Transaction tx = null;
                                tx = session.beginTransaction();
                                session.saveOrUpdate(projectType);
                                tx.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
	}

                public List<CProjectType> searchByProjectName(String projectName) {                        
                        List<CProjectType> list  = null;
                        try {   
                            if (this.session == null) {
                                logger.error("Sesion a DB nula");
                              }
                                //Session session = factory.openSession();
                                logger.debug("SE BUSCA PROYECTO "+projectName);
                                Query query = this.session.createQuery("FROM CProjectType WHERE name LIKE :name");
                                query.setParameter("name", projectName);
                                list  = query.list();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }                                
                        return list;
                }
       
                public Long maxProjectTypeId() {
                        List <Long> list = null;
                        Long max = null;
                        try {
                                //Session session = factory.openSession();
                                Query query = session.createSQLQuery("SELECT MAX(c_projecttype_id) FROM C_ProjectType").addScalar("MAX", Hibernate.LONG );
                                list  = query.list();
                                max = list.get(0);
                                max = ((max <= 0) ? 1000000 : max+1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return max;
                }
}