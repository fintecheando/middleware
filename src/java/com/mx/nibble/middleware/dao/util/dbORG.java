/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author victor
 */
public class dbORG implements dbInterfase {
	private Connection conn;
	private ResultSet rSet;
	private PreparedStatement stmt;
        static Logger logger= Logger.getLogger(dbORG.class);

	private long ad_org_id;
        private long ad_client_id;        
	private String name;



	public void create() throws Exception, SQLException {
		/*
                StringBuffer query = new StringBuffer();
		query.append("INSERT INTO CLIENTE (siidpais,siidviaembarque,iidnivel,iidcatcliente,vchrfc,vchnombre,vchrazonsocial,vchcalle,vchcolonia,chcodigop,vchmail,vchdescregion,vchdescestado,vchtel1,vchtel2,vchfax,vchcurp,bitdadodebaja,declimitecredito,deccreditutilizado,sidiadepago,bsuceptiblecredito,siplazodiaspago,iidestado,iidmunicipio, vchnumexterior, vchnuminterior, vchlocalidad) ");
		query.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidpais);
		stmt.setShort(2,siidviaembarque);		
		stmt.executeUpdate();
		stmt.close();
                */
	}
	public boolean load() throws Exception, SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT ad_org_id,ad_client_id,name FROM adempiere.ad_org ");
		query.append("ORDER BY name ASC");
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		//stmt.setLong(1,ad_org_id);
		rSet = stmt.executeQuery();

		if (rSet.next()) {
			this.ad_org_id = rSet.getLong("ad_org_id");
			this.ad_client_id = rSet.getLong("ad_client_id");
                        this.name = rSet.getString("name");
			
		}
		else
			throw new Exception ("No se encontro el registro");
		rSet.close();
		stmt.close();
		return false;
	}
	public void remove() throws Exception, SQLException {
		StringBuffer query = new StringBuffer();
		//query.append("DELETE FROM CLIENTE ");
		//query.append("WHERE iidnocliente=? ");
		//stmt = conn.prepareStatement(query.toString().toLowerCase());
		//stmt.setInt(1,iidnocliente);
		//stmt.executeUpdate();
		//stmt.close();
	}
	public void store() throws SQLException {
		StringBuffer query = new StringBuffer();
		/*
                query.append("UPDATE CLIENTE SET siidpais=? , siidviaembarque=? , iidnivel=? , iidcatcliente=? , vchrfc=? , vchnombre=? , vchrazonsocial=? , vchcalle=? , vchcolonia=? , chcodigop=? , vchmail=? , vchdescregion=? , vchdescestado=? , vchtel1=? , vchtel2=? , vchfax=? , vchcurp=? , bitdadodebaja=? , declimitecredito=? , deccreditutilizado=? , sidiadepago=? , bsuceptiblecredito=? , siplazodiaspago=? , iidestado=? , iidmunicipio=?,  vchnumexterior=?, vchnuminterior=?, vchlocalidad=? ");
		query.append("WHERE iidnocliente=? ");
                logger.debug(query.toString().toLowerCase());                
		stmt = conn.prepareStatement(query.toString().toLowerCase());
		stmt.setShort(1,siidpais);
		stmt.setShort(2,siidviaembarque);
		
                logger.debug("EL ID DE CLIENTE ES " + iidnocliente);
                
		stmt.executeUpdate();
		stmt.close();
                */
	}
	public void setConnection(java.sql.Connection conn) {
		this.conn = conn;
	}

    /**
     * @return the ad_org_id
     */
    public long getAd_org_id() {
        return ad_org_id;
    }

    /**
     * @param ad_org_id the ad_org_id to set
     */
    public void setAd_org_id(long ad_org_id) {
        this.ad_org_id = ad_org_id;
    }

    /**
     * @return the ad_client_id
     */
    public long getAd_client_id() {
        return ad_client_id;
    }

    /**
     * @param ad_client_id the ad_client_id to set
     */
    public void setAd_client_id(long ad_client_id) {
        this.ad_client_id = ad_client_id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
	
}
