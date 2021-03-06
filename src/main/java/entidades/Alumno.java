/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author alumno
 */
@Entity
public class Alumno implements Serializable {

    private String nombreYApellido;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nivel;
    private String division;
    private String turno;
    private String ultimoPago;
    private String deuda;
    
    private Boolean becado;

    private int montoBeca;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDeAlta;
   
  
    public void obtenerUltimoPago(){
        
        Pago ultimo;
        
        if(!pagos.isEmpty()){
            ultimo = pagos.get(pagos.size()-1);
            ultimoPago = ultimo.getMes() + " " + ultimo.getAnio();            
        }
        
    }
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pago> pagos;

    
    
    public Alumno(){
        becado=false;
    }

    public Date getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(Date fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public String getDeuda() {
        return deuda;
    }

    public void setDeuda(String deuda) {
        this.deuda = deuda;
    }
    
    
    
    
    public int getMontoBeca() {
        return montoBeca;
    }

    public void setMontoBeca(int montoBeca) {
        this.montoBeca = montoBeca;
    }

    
    
    public Boolean getBecado() {
        return becado;
    }

    public void setBecado(Boolean becado) {
        this.becado = becado;
    }

    
    
    public String getNombreYApellido() {
        return nombreYApellido;
    }

    public void setNombreYApellido(String nombreYApellido) {
        this.nombreYApellido = nombreYApellido;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void agregarPago(Pago pago) {
        pagos.add(pago);
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUltimoPago() {
        return ultimoPago;
    }

    public void setUltimoPago(String ultimoPago) {
        this.ultimoPago = ultimoPago;
    }
    
    

}
