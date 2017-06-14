/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbustamante.bankdatareader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel.bustamante
 */
@XmlRootElement(name = "Movimiento")
public class PlainTransaction {
    
    @XmlElement(name = "VALOR")
    private String valor;
    
    @XmlElement(name = "FECHA")
    private String fecha;
    
    @XmlElement(name = "OFICINA")
    private String oficina;
    
    @XmlElement(name = "DESCRIPCIÓN")
    private String descripcion;
    
    @XmlElement(name = "REFERENCIA")
    private String referencia;

    public PlainTransaction() {
    }

    public PlainTransaction(String valor, String fecha, String oficina, String descripcion, String referencia) {
        this.valor = valor;
        this.fecha = fecha;
        this.oficina = oficina;
        this.descripcion = descripcion;
        this.referencia = referencia;
    } 
    
    public String getValor() {
        return valor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getOficina() {
        return oficina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

}
