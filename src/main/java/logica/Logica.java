/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import entidades.Alumno;
import java.util.List;
import dao.DAOGeneral;
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
}
