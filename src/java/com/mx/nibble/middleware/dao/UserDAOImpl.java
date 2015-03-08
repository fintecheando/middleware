/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao;

/**
 *
 * @author 43507296
 */
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.middleware.domain.admin.User2;

public class UserDAOImpl implements UserDAO {
	
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;

	/**
	 * Used to save or update a user.
	 */
	@Override
	public void saveOrUpdateUser(User2 user) {
		try {
			session.saveOrUpdate(user);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Used to delete a user.
	 */
	@Override
	public void deleteUser(Long userId) {
		try {
			User2 user = (User2) session.get(User2.class, userId);
			session.delete(user);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	}
	
	/**
	 * Used to list all the users.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User2> listUser() {
		List<User2> courses = null;
		try {
			courses = session.createQuery("from User").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}

	/**
	 * Used to list a single user by Id.
	 */
	@Override
	public User2 listUserById(Long userId) {
		User2 user = null;
		try {
			user = (User2) session.get(User2.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
