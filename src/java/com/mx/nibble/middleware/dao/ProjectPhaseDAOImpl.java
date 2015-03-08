/**
 * Nibble
 * @author Abraham Lopez Partida
 * Date: December 17, 2014
 */

package com.mx.nibble.middleware.dao;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.domain.CProjectphase;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ProjectPhaseDAOImpl implements ProjectPhaseDAO{
                //private SessionFactory factory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();

	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;

	public void saveOrUpdate(CProjectphase cprojectphase) {
                        try {                                
                                //Session session = factory.openSession();
                                Transaction tx = null;
                                tx = session.beginTransaction();
                                session.saveOrUpdate(cprojectphase);
                                tx.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
	}

                public List<CProjectphase> searchByKey(Long cprojectid, String key, Long uniqueid){
                        List<CProjectphase> list  = null;
                        try {                                                            
                                //Session session = factory.openSession();
                                Query query = session.createQuery("FROM CProjectphase WHERE CProjectId = :cprojectid AND key = :key AND uniqueid = :uniqueid");
                                query.setParameter("cprojectid", cprojectid);
                                query.setParameter("key", key);
                                query.setParameter("uniqueid", uniqueid);
                                list  = query.list();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }                                
                        return list;
                }

                public List<CProjectphase> searchByKeyAndPhaseKey(Long cprojectid, String key, String phasekey, Long uniqueid) {
                        List<CProjectphase> list  = null;
                        try {                                                            
                                //Session session = factory.openSession();
                                Query query = session.createQuery("FROM CProjectphase WHERE CProjectId = :cprojectid AND key = :key AND phasekey = :phasekey AND uniqueid = :uniqueid");
                                query.setParameter("cprojectid", cprojectid);
                                query.setParameter("key", key);
                                query.setParameter("phasekey", phasekey);
                                query.setParameter("uniqueid", uniqueid);
                                list  = query.list();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }                                
                        return list;
                }

                public Long maxProjectPhaseId() {
                        List <Long> list = null;
                        Long max = null;
                        try {
                                //Session session = factory.openSession();
                                Query query = session.createSQLQuery("SELECT MAX(c_projectphase_id) FROM C_Projectphase").addScalar("MAX", Hibernate.LONG );
                                list  = query.list();
                                max = list.get(0);
                                max = ((max == null) ? 1000000 : max+1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return max;
                }
}