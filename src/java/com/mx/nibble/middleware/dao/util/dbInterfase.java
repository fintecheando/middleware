/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.nibble.middleware.dao.util;

import java.sql.Connection;

/**
 *
 * @author victor
 */
public interface dbInterfase {
void create() throws java.sql.SQLException, Exception;
boolean load() throws java.sql.SQLException, java.lang.Exception;
void remove() throws java.sql.SQLException, java.lang.Exception;
void setConnection(Connection conn);
void store() throws  java.sql.SQLException, java.lang.Exception;
}
