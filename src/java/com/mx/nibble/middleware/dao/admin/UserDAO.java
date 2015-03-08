/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.admin;

import com.mx.nibble.domain.AdUser;
import java.util.List;

public abstract interface UserDAO
{
  public abstract void saveOrUpdateUser(AdUser paramAdUser);

  public abstract List<AdUser> listUser();

  public abstract AdUser listUserById(Long paramLong);

  public abstract void deleteUser(Long paramLong);

  public abstract AdUser findByUserNameAndPassword(String paramString1, String paramString2);
  
  public abstract AdUser findByUserNameAndPasswordClientId(String username, String password, String orgId);
  
}
