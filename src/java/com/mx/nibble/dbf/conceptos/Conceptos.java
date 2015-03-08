package com.mx.nibble.dbf.conceptos;

import java.util.Date;

public class Conceptos {
    public Double obra_id;
    public Double partida_id;
    public String clave_partida;
    public String clave_concepto;
    public Double concepto_prefijo_id;
    public String descripcion;
    public String unidad;
    public Double cantidad;
    public Double costo_unitario;
    public Double importe_total;

    /**
     * @return the obra_id
     */
    public Double getObra_id() {
        return obra_id;
    }

    /**
     * @param obra_id the obra_id to set
     */
    public void setObra_id(Double obra_id) {
        this.obra_id = obra_id;
    }

    /**
     * @return the partida_id
     */
    public Double getPartida_id() {
        return partida_id;
    }

    /**
     * @param partida_id the partida_id to set
     */
    public void setPartida_id(Double partida_id) {
        this.partida_id = partida_id;
    }

    /**
     * @return the concepto_prefijo_id
     */
    public Double getConcepto_prefijo_id() {
        return concepto_prefijo_id;
    }

    /**
     * @param concepto_prefijo_id the concepto_prefijo_id to set
     */
    public void setConcepto_prefijo_id(Double concepto_prefijo_id) {
        this.concepto_prefijo_id = concepto_prefijo_id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the unidad
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the cantidad
     */
    public Double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the costo_unitario
     */
    public Double getCosto_unitario() {
        return costo_unitario;
    }

    /**
     * @param costo_unitario the costo_unitario to set
     */
    public void setCosto_unitario(Double costo_unitario) {
        this.costo_unitario = costo_unitario;
    }

    /**
     * @return the importe_total
     */
    public Double getImporte_total() {
        return importe_total;
    }

    /**
     * @param importe_total the importe_total to set
     */
    public void setImporte_total(Double importe_total) {
        this.importe_total = importe_total;
    }

    /**
     * @return the clave_partida
     */
    public String getClave_partida() {
        return clave_partida;
    }

    /**
     * @param clave_partida the clave_partida to set
     */
    public void setClave_partida(String clave_partida) {
        this.clave_partida = clave_partida;
    }

    /**
     * @return the clave_concepto
     */
    public String getClave_concepto() {
        return clave_concepto;
    }

    /**
     * @param clave_concepto the clave_concepto to set
     */
    public void setClave_concepto(String clave_concepto) {
        this.clave_concepto = clave_concepto;
    }
    
}
