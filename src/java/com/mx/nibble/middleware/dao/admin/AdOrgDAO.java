/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.admin;

import com.mx.nibble.domain.AdOrg;
import java.util.List;

/**
 *
 * @author victor
 */
public interface AdOrgDAO{

	public void saveOrUpdateAdOrg(AdOrg adOrg);
	public List<AdOrg> listAdOrg();        
        
	public AdOrg listAdOrgById(Long adOrgId);
	public void deleteAdOrg(Long adOrgId);
}
