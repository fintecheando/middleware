/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mx.nibble.middleware.dao.admin;

import com.mx.nibble.domain.AdClient;
import java.util.List;

/**
 *
 * @author victor
 */
public interface AdClientDAO{

	public void saveOrUpdateAdClient(AdClient adClient);
	public List<AdClient> listAdClient();        
        
	public AdClient listAdClientById(Long adClientId);
	public void deleteAdClient(Long adClientId);
}
