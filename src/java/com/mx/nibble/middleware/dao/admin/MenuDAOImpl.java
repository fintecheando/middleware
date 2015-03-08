/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.admin;


import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.middleware.domain.admin.Menu;
import com.mx.nibble.domain.CMenuetl;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuDAOImpl
  implements MenuDAO
{
  Logger logger = LoggerFactory.getLogger(MenuDAOImpl.class);

  @SessionTarget
  Session session;

  @TransactionTarget
  Transaction transaction;

  public void saveOrUpdateMenu(Menu menu)
  {
    try
    {
      this.session.saveOrUpdate(menu);
    } catch (Exception e) {
      this.transaction.rollback();
      e.printStackTrace();
    }
  }

  public void deleteMenu(Long menuId)
  {
    try
    {
      Menu menu = (Menu)this.session.get(Menu.class, menuId);
      this.session.delete(menu);
    } catch (Exception e) {
      this.transaction.rollback();
      e.printStackTrace();
    }
  }

  public List<Menu> listMenu()
  {
    List menus = null;
    try {
      menus = this.session.createQuery("from Menu").list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return menus;
  }

  public Menu listMenuById(Long menuId)
  {
    Menu menu = null;
    try {
      menu = (Menu)this.session.get(Menu.class, menuId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return menu;
  }

  public List<Menu> listMenuByGroupId(Long groupId)
  {
    List menus = null;
    try
    {
      this.session.beginTransaction();

      menus = this.session.createSQLQuery("SELECT  MENU.* FROM MENUGROUPDEPARMENT, MENU where MENUGROUPDEPARMENT.idgroup = :idgroup and MENU.id = MENUGROUPDEPARMENT.idmenu ORDER BY MENU.description ASC")
        .addEntity(Menu.class)
        .setParameter("idgroup", groupId)
        .list();
    }
    catch (Exception e) {
      this.logger.error(e.getMessage());
      this.session.getTransaction().rollback();
    }
    finally {
      this.session.getTransaction().rollback();
    }
    return menus;
  }

  public List<CMenuetl> listMenuByGroupId()
  {
    List menus = null;
    try {
      menus = this.session.createQuery("from CMenuetl").list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return menus;
  }
}