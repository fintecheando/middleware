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
import com.mx.nibble.domain.AdUser;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDAOImpl implements UserDAO {
    
 Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
  @SessionTarget
  Session session;

  @TransactionTarget
  Transaction transaction;

  public void saveOrUpdateUser(AdUser user)
  {
    try
    {
      this.session.saveOrUpdate(user);
    } catch (Exception e) {
      this.transaction.rollback();
      e.printStackTrace();
    }
  }

  public void deleteUser(Long userId)
  {
    try
    {
      AdUser user = (AdUser)this.session.get(AdUser.class, userId);
      this.session.delete(user);
    } catch (Exception e) {
      this.transaction.rollback();
      e.printStackTrace();
    }
  }

  public List<AdUser> listUser()
  {
    List users = null;
    try {
      users = this.session.createQuery("from AdUser").list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return users;
  }

  public AdUser listUserById(Long userId)
  {
    AdUser user = null;
    try {
      user = (AdUser)this.session.get(AdUser.class, userId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return user;
  }

  public AdUser findByUserNameAndPassword(String userName, String userPwd)
  {
    AdUser user = null;
    try
    {
      if (this.session == null) {
        logger.error("Sesion a DB nula");
      }

      user = (AdUser)this.session.createQuery("from AdUser where name = :idu AND password = :idp")
        .setParameter("idu", userName)
        .setParameter("idp", userPwd)
        .uniqueResult();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return user;
  }
  
  public AdUser findByUserNameAndPasswordClientId(String userName, String userPwd,  String orgId)
  {
    AdUser user = null;
    try
    {
      if (this.session == null) {
        logger.error("Sesion a DB nula");
      }

      user = (AdUser)this.session.createQuery("from AdUser where name = :idu AND password = :idp AND ad_org_id = :idoi ")
        .setParameter("idu", userName)
        .setParameter("idp", userPwd)
        
        .setParameter("idoi", new Long(orgId))
        .uniqueResult();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return user;
  }
  
}