package com.mx.nibble.middleware.dao;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.domain.CProject;
import com.mx.nibble.domain.MProduct;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ProductDAOImpl implements ProductDAO{
                //private SessionFactory factory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();                

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	@Override
	public void saveOrUpdate(MProduct mproduct) {
                        try {
                                //Session session = factory.openSession();
                                Transaction tx = null;
                                tx = session.beginTransaction();
                                session.saveOrUpdate(mproduct);
                                tx.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
	}

                public List<MProduct> searchByProductNameAndDescription(String name, String description) {
                        List<MProduct> list  = null;
                        try {
                                //Session session = factory.openSession();
                                Query query = session.createQuery("FROM MProduct WHERE name = :name AND description = :description");
                                query.setParameter("name", name);
                                query.setParameter("description", description);
                                list  = query.list();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return list;
                }

                public Long maxProductId() {
                        List <Long> list = null;
                        Long max = null;
                        try {
                                //Session session = factory.openSession();
                                Query query = session.createSQLQuery("SELECT MAX(m_product_id) FROM M_Product").addScalar("MAX", Hibernate.LONG );
                                list  = query.list();
                                max = list.get(0);
                                max = ((max == null) ? 1000000 : max+1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return max;
                }
}
