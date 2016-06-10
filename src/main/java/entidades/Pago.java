/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author ramiro
 */

@Entity
public class Pago implements Serializable {
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDePago;
    
    private String concepto;
    private float monto;
    private String mes;

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
    
    

    public Date getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public String getConcepto() {
        return concepto;
    }


    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
}
