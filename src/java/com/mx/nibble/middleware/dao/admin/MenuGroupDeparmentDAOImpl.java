/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.admin;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import com.mx.nibble.middleware.domain.admin.MenuGroupDeparment;
import org.hibernate.Query;

/**
 *
 * @author 43507296
 */
public class MenuGroupDeparmentDAOImpl implements MenuGroupDeparmentDAO {
	
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;

	/**
	 * Used to save or update a menu.
	 */
	@Override
	public void saveOrUpdateMenuGroupDeparment(MenuGroupDeparment menugroupdeparment) {
		try {
			session.saveOrUpdate(menugroupdeparment);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Used to delete a menu.
	 */
	@Override
	public void deleteMenuGroupDeparment(Long idmenu,Long idgroup,Long iddepartment) {
            //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			//MenuGroupDeparment menugroupdeparment = (MenuGroupDeparment) session.get(MenuGroupDeparment.class, menuId);
			//session.delete(menugroupdeparment);
                    String hql = "delete from MenuGroupDeparment where idmenu = :idmenu AND idgroup = :idgroup AND iddepartment = :iddepartment";
                    Query query = session.createQuery(hql)
                            .setParameter("idmenu", idmenu)
                                    .setParameter("idgroup", idgroup)
                            .setParameter("iddepartment", iddepartment);

                    int rowCount = query.executeUpdate();
                    session.getTransaction().commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}
	
	/**
	 * Used to list all the menus.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuGroupDeparment> listMenuGroupDeparment() {
		List<MenuGroupDeparment> menugroupdeparments = null;
		try {
			menugroupdeparments = session.createQuery("from MenuGroupDeparment").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menugroupdeparments;
	}

	/**
	 * Used to list a single menu by Id.
	 */
	@Override
	public MenuGroupDeparment listMenuGroupDeparmentById(Long idmenu,Long idgroup,Long iddepartment) {
		MenuGroupDeparment menu = null;
		try {
                        String hql = "delete from MenuGroupDeparment where idmenu = :idmenu AND idgroup = :idgroup AND iddepartment = :iddepartment";
                        menu = (MenuGroupDeparment) session.createQuery(hql)
                            .setParameter("idmenu", idmenu)
                                    .setParameter("idgroup", idgroup)
                            .setParameter("iddepartment", iddepartment).uniqueResult();
                        session.getTransaction().commit();
			//menu = (MenuGroupDeparment) session.get(MenuGroupDeparment.class, menuId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menu;
	}
}
