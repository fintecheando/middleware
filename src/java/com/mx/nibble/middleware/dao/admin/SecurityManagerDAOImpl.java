/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.admin;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.middleware.web.util.MenuUtil;
import com.mx.nibble.domain.AdRole;
import com.mx.nibble.domain.AdUser;
import com.mx.nibble.domain.AdUserRoles;
import java.io.PrintStream;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityManagerDAOImpl implements SecurityManagerDAO {
    
  Logger logger = LoggerFactory.getLogger(SecurityManagerDAOImpl.class);

  @SessionTarget
  Session session;

  @TransactionTarget
  Transaction transaction;
  private AdUserRoles group = new AdUserRoles();
  private UserGroupDAO groupDAO = new UserGroupDAOImpl();
  private AdUser user = new AdUser();
  private UserDAO userDAO = new UserDAOImpl();

  private MenuDAO menuDAO = new MenuDAOImpl();

  private String profile = null;

  public void setUser(AdUser user)
  {
    this.user = user;
  }

  public AdUser getUser() {
    return this.user;
  }

  public AdUserRoles getUserGroup() {
    return this.group;
  }

  public void setUserGroup(AdUserRoles group) {
    this.group = group;
  }

  public AdUser login(String username, String password)
  {
    this.user = this.userDAO.findByUserNameAndPassword(username, password);
    return this.user;
  }
  
  public AdUser loginById(String username, String password, String  orgId)
  {
    this.user = this.userDAO.findByUserNameAndPasswordClientId(username, password, orgId);
    return this.user;
  }

  public String loadMenu(Object user)
  {
    String menu = null;
    this.user = ((AdUser)user);

    MenuUtil menuUtil = new MenuUtil();

    this.logger.debug("LOADING MENU FOR USER " + this.user.getDescription());

    if (this.user.getAdUserId() != -1L)
    {
      this.logger.debug("SE CARGA EL GRUPO " + this.user.getDescription());
      this.group = this.groupDAO.listGroupById(this.user);
      logger.debug("La decripcion del grupo es " + this.group.getAdRole().getName());
      this.logger.debug("SE CARGA EL MENU DEL GRUPO " + this.user.getDescription());
      menuUtil.setOpciones(this.menuDAO.listMenuByGroupId());
      logger.debug("ELEMENTOS DEL MENU TRAIDOS " + menuUtil.getOpciones().size());

      logger.debug("MENU ALTERNO " + menuUtil.headerToString());
      menu = menuUtil.headerToString();
      return menu;
    }

    this.logger.error("NO SE ENCONTRO PERFIL ASOCIADO");
    return menu;
  }

  public String getProfile()
  {
    return this.profile;
  }

  public void setProfile(String profile)
  {
    this.profile = profile;
  }
}