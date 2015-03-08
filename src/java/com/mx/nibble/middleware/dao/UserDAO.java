/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao;

import com.mx.nibble.middleware.domain.admin.User2;
import java.util.List;

/**
 *
 * @author 43507296
 */
public interface UserDAO {

	public void saveOrUpdateUser(User2 user);
	public List<User2> listUser();
	public User2 listUserById(Long userId);
	public void deleteUser(Long userId);
}
