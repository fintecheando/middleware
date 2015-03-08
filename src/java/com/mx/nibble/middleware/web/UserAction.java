/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.web;

import com.mx.nibble.middleware.dao.UserDAO;
import com.mx.nibble.middleware.dao.UserDAOImpl;
import com.mx.nibble.middleware.domain.admin.User2;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class UserAction extends ActionSupport implements ModelDriven<User2> {

	private static final long serialVersionUID = -6659925652584240539L;

	private User2 user = new User2();
	private List<User2> userList = new ArrayList<User2>();
	private UserDAO userDAO = new UserDAOImpl();
	
	@Override
	public User2 getModel() {
		return user;
	}
	
	/**
	 * To save or update user.
	 * @return String
	 */
	public String saveOrUpdate()
	{	
		userDAO.saveOrUpdateUser(user);
		return SUCCESS;
	}
	
	/**
	 * To list all users.
	 * @return String
	 */
	public String list()
	{
		userList = userDAO.listUser();
		return SUCCESS;
	}
	
	/**
	 * To delete a user.
	 * @return String
	 */
	public String delete()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		userDAO.deleteUser(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}
	
	/**
	 * To list a single user by Id.
	 * @return String
	 */
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		user = userDAO.listUserById(Long.parseLong(request.getParameter("id")));
		return SUCCESS;
	}
	
	public User2 getUser() {
		return user;
	}

	public void setUser(User2 user) {
		this.user = user;
	}

	public List<User2> getUserList() {
		return userList;
	}

	public void setUserList(List<User2> userList) {
		this.userList = userList;
	}

}
