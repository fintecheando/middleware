/**
 * Nibble
 * @author Abraham Lopez Partida
 * Date: December 17, 2014
 */

package com.mx.nibble.middleware.dao;


import java.util.*;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.domain.CProject;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProjectDAOImpl implements ProjectDAO{
                //private SessionFactory factory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;

	/**
	 * Used to save or update a user.
	 */
	@Override
	public void saveOrUpdate(CProject project) {
                        try {                                
                                //Session session = factory.openSession();
                                Transaction tx = null;
                                tx = session.beginTransaction();
                                session.saveOrUpdate(project);
                                tx.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
	}

                public List<CProject> searchByProjectName(String projectName) {                        
                        List<CProject> list  = null;
                        try {    
                            if (this.session == null) {
                                logger.error("Sesion a DB nula");
                              }
                            logger.debug("SE BUSCA PROYECTO "+projectName);
                                //Session session = factory.openSession();
                                Query query = session.createQuery("FROM CProject WHERE name LIKE :name");
                                query.setParameter("name", projectName);
                                list  = query.list();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }                                
                        return list;
                }
       
                public Long maxProjectId() {
                        List <Long> list = null;
                        Long max = null;
                        try {
                                //Session session = factory.openSession();
                                Query query = session.createSQLQuery("SELECT MAX(c_project_id) FROM C_Project").addScalar("MAX", Hibernate.LONG );
                                list  = query.list();
                                max = list.get(0);
                                max = ((max == null) ? 1000000 : max+1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return max;
                }        

    @Override
    public List<CProject> listProjects() {
        List<CProject> list  = null;
                        try {    
                            if (this.session == null) {
                                logger.error("Sesion a DB nula");
                              }
                            logger.debug("SE BUSCA PROYECTOS ");
                                //Session session = factory.openSession();
                                Query query = session.createQuery("FROM CProject");                                
                                list  = query.list();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }                                
                        return list;
                }

    @Override
    public List<CProject> searchByProjectNameOrDescription(String nameOrDescription) {
        List<CProject> list  = null;
                        try {    
                            if (this.session == null) {
                                logger.error("Sesion a DB nula");
                              }
                            logger.debug("SE BUSCA PROYECTO "+nameOrDescription);
                                //Session session = factory.openSession();
                                Query query = session.createQuery("FROM CProject "
                                        + "WHERE name LIKE :nameOrDescription "
                                        + "OR description LIKE :nameOrDescription ");
                                query.setParameter("nameOrDescription", "%"+nameOrDescription+"%");
                                list  = query.list();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }                                
                        return list;
    }

    @Override
    public CProject searchByProjectId(long projectId) {
        CProject project  = null;
        try {    
            if (this.session == null) {
                logger.error("Sesion a DB nula");
              }
            logger.debug("SE BUSCA PROYECTO "+projectId);
                //Session session = factory.openSession();
                Query query = session.createQuery("FROM CProject WHERE CProjectId = :id");
                query.setParameter("id", projectId);
                project  = (CProject) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }                                
        return project;
    }

    @Override
    public CProject searchResumeByProjectId(long projectId) {
        CProject project  = null;
        try {    
            if (this.session == null) {
                logger.error("Sesion a DB nula");
              }
            logger.debug("SE BUSCA PROYECTO "+projectId);
                //Session session = factory.openSession();
                Query query = session.createQuery("FROM CProject WHERE CProjectId = :id");
                query.setParameter("id", projectId);
                project  = (CProject) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }                                
        return project;
    }
}
