/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Alumno;
import entidades.Pago;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ramiro
 */
public class DAOGeneral {

    public boolean guardarAlumno(Alumno alumno) {
        boolean respuesta = true;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {
            s.save(alumno);
            s.getTransaction().commit();

        } catch (Exception e) {
            respuesta = false;
            s.getTransaction().rollback();
            System.out.println("ROLLBACK EN TRANSACCION");
            e.printStackTrace();

        } finally {
            s.close();
        }

        return respuesta;
    }

    public List<Alumno> obtenerTodosAlumnos() {
        List<Alumno> respuesta;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        Criteria c = s.createCriteria(Alumno.class);
        respuesta = c.list();

        System.out.println("Alumnos cargados en la base de datos: " + respuesta.size());

        s.close();

        return respuesta;
    }

    public List<Alumno> obtenerAlumnosPorNombre(String nombre) {
        List<Alumno> respuesta;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        Criteria c = s.createCriteria(Alumno.class)
                .add(Restrictions.like("nombreYApellido", nombre));
        respuesta = c.list();

        System.out.println("Alumnos que coinciden con la busqueda: " + respuesta.size());

        s.close();

        return respuesta;
    }

    public Alumno obtenerAlumnosPorId(int id) {
        Alumno respuesta = null;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            respuesta = (Alumno) s.get(Alumno.class, id);

        } catch (Exception e) {
            System.out.println("No se pudo obtener el alumno por su ID");
        } finally {
            s.close();
        }

        return respuesta;
    }

    public boolean actualizarAlumno(Alumno alumno) {
        boolean respuesta = true;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            s.merge(alumno);
            s.getTransaction().commit();

        } catch (Exception e) {
            respuesta = false;
            s.getTransaction().rollback();
            System.out.println("ROLLBACK EN TRANSACCION");
            e.printStackTrace();

        } finally {
            s.close();
        }

        return respuesta;
    }

    public void eliminarAlumno(Alumno alumno) {

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {
            s.delete(alumno);
            s.getTransaction().commit();

        } catch (Exception e) {

            s.getTransaction().rollback();
            System.out.println("ROLLBACK EN TRANSACCION");
            e.printStackTrace();

        } finally {
            s.close();
        }

    }

    public boolean guardarPago(Pago pago) {
        boolean respuesta = true;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {
            s.save(pago);
            s.getTransaction().commit();

        } catch (Exception e) {
            respuesta = false;
            s.getTransaction().rollback();
            System.out.println("ROLLBACK EN TRANSACCION");
            e.printStackTrace();

        } finally {
            s.close();
        }

        return respuesta;
    }

}
