package com.mx.nibble.middleware.dao;

import com.mx.nibble.domain.MProduct;
import java.util.List;

public interface ProductDAO {
        public void saveOrUpdate(MProduct mproduct);        
        public List<MProduct> searchByProductNameAndDescription(String name, String Description);        
        public Long maxProductId();        
}
