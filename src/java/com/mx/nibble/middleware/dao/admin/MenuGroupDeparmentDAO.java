/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.admin;

import com.mx.nibble.middleware.domain.admin.MenuGroupDeparment;
import java.util.List;

public abstract interface MenuGroupDeparmentDAO
{
  public abstract void saveOrUpdateMenuGroupDeparment(MenuGroupDeparment paramMenuGroupDeparment);

  public abstract List<MenuGroupDeparment> listMenuGroupDeparment();

  public abstract MenuGroupDeparment listMenuGroupDeparmentById(Long paramLong1, Long paramLong2, Long paramLong3);

  public abstract void deleteMenuGroupDeparment(Long paramLong1, Long paramLong2, Long paramLong3);
}