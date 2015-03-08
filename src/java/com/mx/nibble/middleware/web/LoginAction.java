/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.web;


import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.domain.AdUser;
import com.mx.nibble.middleware.dao.admin.SecurityManagerDAO;
import com.mx.nibble.middleware.dao.admin.SecurityManagerDAOImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginAction extends ActionSupport {
    @SessionTarget
    Session session;

    @TransactionTarget
    Transaction transaction;
    
    private static final long serialVersionUID = -6659925652584240539L;    
    Logger logger = LoggerFactory.getLogger(LoginAction.class);
    private String userName;
    private String password;
    
    private String idClient;
    private String idOrg;

    String menu = null;
    AdUser user = new AdUser();
    SecurityManagerDAO securityManager = new SecurityManagerDAOImpl();
    
  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String execute()
  {   
      logger.debug("ORGANIZACION "+idOrg);
    logger.debug("CLIENTE "+idClient);
    this.user = securityManager.loginById(this.userName, this.password,  this.idOrg);
    if (this.user == null) {
      addActionError("Wrong Username or Password");
      return "login";
    }
    
    logger.debug("ORGANIZACION "+idOrg);
    logger.debug("CLIENTE "+idClient);
    
    this.menu = this.securityManager.loadMenu(this.user);
    ServletActionContext.getRequest().getSession().setAttribute("ad_client_id", Long.valueOf(this.user.getAdClient().getAdClientId()));
    ServletActionContext.getRequest().getSession().setAttribute("ad_org_id", Long.valueOf(this.user.getAdOrgByAdOrgId().getAdOrgId()));
    ServletActionContext.getRequest().getSession().setAttribute("ad_user_id", Long.valueOf(this.user.getAdUserId()));
    ServletActionContext.getRequest().getSession().setAttribute("loggedInUser", this.userName);
    ServletActionContext.getRequest().getSession().setAttribute("menu", this.menu);
    logger.debug("login-success");
    logger.debug("Validando usuario");
    addActionMessage("You are valid user!");
    
    return "login-success";
  }

  public void validate()
  {
    logger.debug("EJECUTANDO public void validate()");
    if ((this.userName.trim().equalsIgnoreCase("")) || (this.password.trim().equalsIgnoreCase("")) 
            || (this.idOrg.trim().equalsIgnoreCase("")) || (this.idClient.trim().equalsIgnoreCase("")))
    {
      addActionError("Username and Password cann't be blanked");
    }
  }

    /**
     * @return the idClient
     */
    public String getIdClient() {
        return idClient;
    }

    /**
     * @param idClient the idClient to set
     */
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    /**
     * @return the idOrg
     */
    public String getIdOrg() {
        return idOrg;
    }

    /**
     * @param idOrg the idOrg to set
     */
    public void setIdOrg(String idOrg) {
        this.idOrg = idOrg;
    }

    }
