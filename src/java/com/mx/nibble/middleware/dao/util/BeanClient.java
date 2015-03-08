package com.mx.nibble.middleware.dao.util;


/**
 * Insert the type's description here.
 * Creation date: (10/22/2002 6:35:43 PM)
 * @author:
 */

public class BeanClient {
    public java.util.Vector vClientes;
    public String mensaje;

    /**
     * Bean52 constructor comment.
     */
    public BeanClient() {
        vClientes = new java.util.Vector();
    }

    /**
     * Insert the method's description here.
     * Creation date: (10/22/2002 6:37:49 PM)
     * @return int
     */
    public int getClientesSize() {
        return vClientes.size();
    }

    /**
     * Insert the method's description here.
     * Creation date: (10/22/2002 6:39:17 PM)
     * @return int
     * @param index int
     */
    public long getId(int index) {
        dbCLIENTE a = (dbCLIENTE) vClientes.elementAt(index);

        return a.getAd_client_id();
    }

    /**
     * Insert the method's description here.
     * Creation date: (10/22/2002 6:39:17 PM)
     * @return int
     * @param index int
     */
    public String getName(int index) {
        dbCLIENTE a = (dbCLIENTE) vClientes.elementAt(index);

        return (a.getName() == null) ? "" : a.getName();
    }

    
    /**
     * Insert the method's description here.
     * Creation date: (10/30/2002 5:43:07 PM)
     * @return java.util.Vector
     */
    public java.util.Vector getVClientes() {
        return vClientes;
    }

    /**
     * Insert the method's description here.
     * Creation date: (10/30/2002 5:43:07 PM)
     * @param newVClientes java.util.Vector
     */
    public void setVClientes(java.util.Vector newVClientes) {
        vClientes = newVClientes;
    }

    /**
     * @return
     */
    public String getMensaje() {
        return (mensaje == null) ? "" : mensaje;
    }

    /**
     * @param string
     */
    public void setMensaje(String string) {
        mensaje = string;
    }
}
