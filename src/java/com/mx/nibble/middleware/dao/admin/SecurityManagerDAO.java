/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.admin;

import com.mx.nibble.domain.AdUser;

public abstract interface SecurityManagerDAO
{
  public abstract void setUser(AdUser paramAdUser);

  public abstract AdUser getUser();

  public abstract String loadMenu(Object paramObject);

  public abstract AdUser login(String paramString1, String paramString2);
  public abstract AdUser loginById(String paramString1, String paramString2,  String orgId);
}