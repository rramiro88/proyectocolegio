/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import entidades.Alumno;
import java.util.List;
import dao.DAOGeneral;
import java.time.LocalDate;
import javafx.collections.FXCollections;

/**
 *
 * @author ramiro
 */
public class Logica {

    DAOGeneral dao;

    public Logica() {

        dao = new DAOGeneral();

    }

    public List<Alumno> obtenerAlumnos(String nombre, String id) {
        int idEntero;
        Alumno a;
        if ("".equals(nombre)) {
            if ("".equals(id)) {
                return dao.obtenerTodosAlumnos();
            } else {

                idEntero = Integer.valueOf(id);
                a = dao.obtenerAlumnosPorId(idEntero);
                return FXCollections.observableArrayList(a);

            }
        }else{
            return dao.obtenerAlumnosPorNombre(nombre);
        }

        
    }

    public boolean actualizarAlumno(Alumno alumnoAActualizar) {
        return dao.actualizarAlumno(alumnoAActualizar);
    }

    public void eliminarAlumno(Alumno alumno) {
        dao.eliminarAlumno(alumno);
    }

//    public void guardarPago(Pago pago) {
//        dao.guardarPago(pago);
//    }

    public boolean guardarAlumno(Alumno alumnoACrear) {
        return dao.guardarAlumno(alumnoACrear);
    }

    public String obtenerTotalDia(LocalDate dia) {
        return dao.obtenerTotalDia(dia);
    }

    public String obtenerTotalMes(String mes, String anio) {
        
        int mesEntero = 0;
        
        
        switch(mes){
            case "Enero": {
                mesEntero = 1;
                break;
            }
            case "Febrero": {
                mesEntero = 2;
                break;
            }
            case "Marzo": {
                mesEntero = 3;
                break;
            }
            case "Abril": {
                mesEntero = 4;
                break;
            }
            case "Mayo": {
                mesEntero = 5;
                break;
            }
            case "Junio": {
                mesEntero = 6;
                break;
            }
            case "Julio": {
                mesEntero = 7;
                break;
            }
            case "Agosto": {
                mesEntero = 8;
                break;
            }
            case "Septiembre": {
                mesEntero = 9;
                break;
            }
            case "Octubre": {
                mesEntero = 10;
                break;
            }
            case "Noviembre": {
                mesEntero = 11;
                break;
            }
            case "Diciembre": {
                mesEntero = 12;
                break;
            }
           
        }
        
        
        
        
        return dao.obtenerTotalMes(mesEntero,Integer.parseInt(anio));
    }

    public String obtenerTotalAnio(String anio) {
        return dao.obtenerTotalAnio(anio);
    }
}
