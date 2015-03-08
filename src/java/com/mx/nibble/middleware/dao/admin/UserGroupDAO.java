/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.admin;

/**
 *
 * @author 43507296
 */

import com.mx.nibble.middleware.domain.admin.UserGroup;
import com.mx.nibble.domain.AdUser;
import com.mx.nibble.domain.AdUserRoles;
import java.util.List;

public abstract interface UserGroupDAO
{
  public abstract void saveOrUpdateGroup(UserGroup paramUserGroup);

  public abstract List<UserGroup> listGroup();

  public abstract AdUserRoles listGroupById(AdUser paramAdUser);

  public abstract void deleteGroup(Long paramLong);
}