/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import entidades.Alumno;
import java.util.List;
import dao.DAOGeneral;
import entidades.Pago;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public List<Alumno> obtenerDeudores(String nombre, String id, String nivel, String division) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int mesActual = cal.get(Calendar.MONTH);
        int anioActual = cal.get(Calendar.YEAR);
        List<Alumno> seleccionados;
        if ("".equals(nivel) || "".equals(division)) {
            seleccionados = obtenerAlumnos(nombre, id);
        } else {
            seleccionados = dao.obtenerAlumnosPorNivelYDivision(nivel, division);
        }

        List<Alumno> deudores = new ArrayList<>();

        for (Alumno seleccionado : seleccionados) {

            if (seleccionado.getPagos().size() > 0) {
                Pago ultimoPago = seleccionado.getPagos().get(seleccionado.getPagos().size() - 1);
                int anioUltimoPago = Integer.parseInt(ultimoPago.getAnio());
                int mesUltimoPago = mesAEntero(ultimoPago.getMes());

                int aniosAdeudados = anioActual - anioUltimoPago;
                int mesesAdeudados = mesActual - mesUltimoPago;

                if ((mesesAdeudados > 0) || (aniosAdeudados > 0)) {

                    seleccionado.setDeuda(aniosAdeudados + " años y " + mesesAdeudados + " meses.");
                    deudores.add(seleccionado);

                }
            }

        }

        return deudores;

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
        } else {
            return dao.obtenerAlumnosPorNombre(nombre);
        }

    }

    public boolean actualizarAlumno(Alumno alumnoAActualizar) {
        return dao.actualizarAlumno(alumnoAActualizar);
    }

    public void eliminarAlumno(Alumno alumno) {
        dao.eliminarAlumno(alumno);
    }

    public boolean guardarAlumno(Alumno alumnoACrear) {
        return dao.guardarAlumno(alumnoACrear);
    }

    public String obtenerTotalDia(LocalDate dia) {
        return dao.obtenerTotalDia(dia);
    }

    public String obtenerTotalMes(String mes, String anio) {

        int mesEntero = mesAEntero(mes);

        return dao.obtenerTotalMes(mesEntero, Integer.parseInt(anio));
    }

    int mesAEntero(String mes) {
        int mesEntero = -1;

        switch (mes) {
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

        return mesEntero;
    }

    public String obtenerTotalAnio(String anio) {
        return dao.obtenerTotalAnio(anio);
    }
}
