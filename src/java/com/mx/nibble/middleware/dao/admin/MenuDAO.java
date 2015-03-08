/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.admin;

import com.mx.nibble.middleware.domain.admin.Menu;
import com.mx.nibble.domain.CMenuetl;
import java.util.List;

public abstract interface MenuDAO
{
  public abstract void saveOrUpdateMenu(Menu paramMenu);

  public abstract List<Menu> listMenu();

  public abstract Menu listMenuById(Long paramLong);

  public abstract void deleteMenu(Long paramLong);

  public abstract List<Menu> listMenuByGroupId(Long paramLong);

  public abstract List<CMenuetl> listMenuByGroupId();
}