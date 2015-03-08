/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.web;


import com.mx.nibble.domain.AdClient;
import com.mx.nibble.domain.AdOrg;
import com.mx.nibble.domain.AdRole;
import com.mx.nibble.domain.AdUser;
import com.mx.nibble.domain.MWarehouse;
import com.mx.nibble.middleware.dao.admin.AdClientDAO;
import com.mx.nibble.middleware.dao.admin.AdClientDAOImpl;
import com.mx.nibble.middleware.dao.admin.SecurityManagerDAO;
import com.mx.nibble.middleware.dao.admin.SecurityManagerDAOImpl;
import com.mx.nibble.middleware.web.util.ConstantParameters;
import com.mx.nibble.middleware.web.util.TrueOrFalse;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostLoginAction extends ActionSupport implements Preparable, ModelDriven<AdUser> 
{
    private static final long serialVersionUID = -6759925652584240539L;
    Logger logger = LoggerFactory.getLogger(PostLoginAction.class);
    private String userName;
    private String password;
    String menu = null;
    AdUser user = new AdUser(); 
    SecurityManagerDAO securityManager = new SecurityManagerDAOImpl();
    
    private AdUser adUser = new AdUser();
    private List<AdUser> adUserList = new ArrayList<AdUser>();
    
    private AdClient adClient = new AdClient();
    private List<AdClient> adClientList = new ArrayList<AdClient>();
    private AdClientDAO adClientDAO = new AdClientDAOImpl();
    
    private AdOrg adOrg = new AdOrg();
    private List<AdOrg> adOrgList = new ArrayList<AdOrg>();
	
    private AdRole adRole = new AdRole();
    private List<AdRole> adRoleList = new ArrayList<AdRole>();
    
    private MWarehouse mWarehouse = new MWarehouse();
    private List<MWarehouse> mWarehouseList = new ArrayList<MWarehouse>();
    
    
    /**
	 * To list all adClient.
	 * @return String
	 */
	public String list()
	{
		adClientList = adClientDAO.listAdClient();
		return SUCCESS;
	}
    
    @Override
        public void prepare() throws Exception {
                logger.debug("CARGANDO AD CLIENT LIST");
                setAdClientList(adClientDAO.listAdClient());
	}
	
	@Override
	public AdUser getModel() {
		return adUser;
	}
    
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
    this.user = securityManager.login(this.userName, this.password);
    if (this.user == null) {
      addActionError("Wrong Username or Password");
      return "login";
    }

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
    if ((this.userName.trim().equalsIgnoreCase("")) || (this.password.trim().equalsIgnoreCase("")))
    {
      addActionError("Username and Password cann't be blanked");
    }
  }

    /**
     * @return the adUser
     */
    public AdUser getAdUser() {
        return adUser;
    }

    /**
     * @param adUser the adUser to set
     */
    public void setAdUser(AdUser adUser) {
        this.adUser = adUser;
    }

    /**
     * @return the adUserList
     */
    public List<AdUser> getAdUserList() {
        return adUserList;
    }

    /**
     * @param adUserList the adUserList to set
     */
    public void setAdUserList(List<AdUser> adUserList) {
        this.adUserList = adUserList;
    }

    /**
     * @return the adClient
     */
    public AdClient getAdClient() {
        return adClient;
    }

    /**
     * @param adClient the adClient to set
     */
    public void setAdClient(AdClient adClient) {
        this.adClient = adClient;
    }

    /**
     * @return the adClientList
     */
    public List<AdClient> getAdClientList() {
        return adClientList;
    }

    /**
     * @param adClientList the adClientList to set
     */
    public void setAdClientList(List<AdClient> adClientList) {
        this.adClientList = adClientList;
    }

    /**
     * @return the adOrg
     */
    public AdOrg getAdOrg() {
        return adOrg;
    }

    /**
     * @param adOrg the adOrg to set
     */
    public void setAdOrg(AdOrg adOrg) {
        this.adOrg = adOrg;
    }

    /**
     * @return the adOrgList
     */
    public List<AdOrg> getAdOrgList() {
        return adOrgList;
    }

    /**
     * @param adOrgList the adOrgList to set
     */
    public void setAdOrgList(List<AdOrg> adOrgList) {
        this.adOrgList = adOrgList;
    }

    /**
     * @return the adRole
     */
    public AdRole getAdRole() {
        return adRole;
    }

    /**
     * @param adRole the adRole to set
     */
    public void setAdRole(AdRole adRole) {
        this.adRole = adRole;
    }

    /**
     * @return the adRoleList
     */
    public List<AdRole> getAdRoleList() {
        return adRoleList;
    }

    /**
     * @param adRoleList the adRoleList to set
     */
    public void setAdRoleList(List<AdRole> adRoleList) {
        this.adRoleList = adRoleList;
    }

    /**
     * @return the mWarehouse
     */
    public MWarehouse getmWarehouse() {
        return mWarehouse;
    }

    /**
     * @param mWarehouse the mWarehouse to set
     */
    public void setmWarehouse(MWarehouse mWarehouse) {
        this.mWarehouse = mWarehouse;
    }

    /**
     * @return the mWarehouseList
     */
    public List<MWarehouse> getmWarehouseList() {
        return mWarehouseList;
    }

    /**
     * @param mWarehouseList the mWarehouseList to set
     */
    public void setmWarehouseList(List<MWarehouse> mWarehouseList) {
        this.mWarehouseList = mWarehouseList;
    }
}