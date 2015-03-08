/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.admin;

/**
 *
 * @author 43507296
 */


import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.mx.nibble.middleware.domain.admin.UserGroup;
import com.mx.nibble.domain.AdUser;
import com.mx.nibble.domain.AdUserRoles;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserGroupDAOImpl implements UserGroupDAO {
    
    Logger logger = LoggerFactory.getLogger(UserGroupDAOImpl.class);

  @SessionTarget
  Session session;

  @TransactionTarget
  Transaction transaction;

  public void saveOrUpdateGroup(UserGroup group)
  {
    try
    {
      this.session.saveOrUpdate(group);
    } catch (Exception e) {
      this.transaction.rollback();
      e.printStackTrace();
    }
  }

  public void deleteGroup(Long gruopId)
  {
    try
    {
      UserGroup grupo = (UserGroup)this.session.get(UserGroup.class, gruopId);
      this.session.delete(grupo);
    } catch (Exception e) {
      this.transaction.rollback();
      e.printStackTrace();
    }
  }

  public List<UserGroup> listGroup()
  {
    List gruops = null;
    try {
      gruops = this.session.createQuery("from UserGroup").list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return gruops;
  }

  public UserGroup listGroupById(Long gruopId)
  {
    UserGroup gruop = null;
    try
    {
      this.session.beginTransaction();
      
      gruop = (UserGroup)this.session.createQuery("from UserGroup where ID = :idgroup")
        .setParameter("idgroup", gruopId)
        .uniqueResult();
      this.session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return gruop;
  }

  public AdUserRoles listGroupById(AdUser adUser)
  {
    AdUserRoles adUserRoles = null;
    try
    {
      this.session.beginTransaction();
      logger.debug("USER ID"+adUser.getAdUserId());
      logger.debug("ORG ID"+adUser.getAdOrgByAdOrgId().getAdOrgId());
      logger.debug("CLIENT ID"+adUser.getAdClient().getAdClientId());
      
      adUserRoles = (AdUserRoles)this.session.createQuery("from AdUserRoles where "
              + "ad_user_id = :adUserId and ad_org_id= :adOrgId and ad_client_id= :adClientId")
        .setParameter("adUserId", Long.valueOf(adUser.getAdUserId()))
        .setParameter("adOrgId", Long.valueOf(adUser.getAdOrgByAdOrgId().getAdOrgId()))
        .setParameter("adClientId", Long.valueOf(adUser.getAdClient().getAdClientId()))
        .uniqueResult();
      this.session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return adUserRoles;
  }
}