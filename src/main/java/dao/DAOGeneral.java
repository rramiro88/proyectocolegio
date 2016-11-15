/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Alumno;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
            s.persist(alumno);
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

        Query consulta = s.createQuery("From Alumno");
        respuesta = consulta.list();

        System.out.println("Alumnos cargados en la base de datos: " + respuesta.size());

        s.close();

        return respuesta;
    }

    public List<Alumno> obtenerAlumnosPorNombre(String nombre) {
        List<Alumno> respuesta;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        
//        Query consulta = s.createSQLQuery("select division, nivel, nombreYApellido, turno from Alumno where nombreYApellido like \"%" + nombre +"%\"");
        Query consulta = s.createQuery("From Alumno where nombreYApellido like :parametro");
        consulta.setParameter("parametro","%"+ nombre +"%" );
        
        respuesta = consulta.list();
        
        
//        Criteria c = s.createCriteria(Alumno.class)
//                .add(Restrictions.like("nombreYApellido", nombre, MatchMode.START));
//        respuesta = c.list();

        System.out.println("Alumnos que coinciden con la busqueda: " + respuesta.size());

        s.close();

        return respuesta;
    }
    
    
     public List<Alumno> obtenerAlumnosPorNivelYDivision(String nivel, String division) {
        List<Alumno> respuesta;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        
//        Query consulta = s.createSQLQuery("select division, nivel, nombreYApellido, turno from Alumno where nombreYApellido like \"%" + nombre +"%\"");
        Query consulta = s.createQuery("From Alumno where nivel like :parametroNivel and division like :parametroDivision" );
        consulta.setParameter("parametroNivel","%"+ nivel +"%" );
        consulta.setParameter("parametroDivision","%"+ division +"%" );
        
        respuesta = consulta.list();
        
        
//        Criteria c = s.createCriteria(Alumno.class)
//                .add(Restrictions.like("nombreYApellido", nombre, MatchMode.START));
//        respuesta = c.list();

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

            s.saveOrUpdate(alumno);
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

//    public boolean guardarPago(Pago pago) {
//        boolean respuesta = true;
//
//        SessionFactory sf = HibernateUtil.getSessionFactory();
//        Session s = sf.openSession();
//        s.beginTransaction();
//
//        try {
//            s.save(pago);
//            s.getTransaction().commit();
//
//        } catch (Exception e) {
//            respuesta = false;
//            s.getTransaction().rollback();
//            System.out.println("ROLLBACK EN TRANSACCION");
//            e.printStackTrace();
//
//        } finally {
//            s.close();
//        }
//
//        return respuesta;
//    }

    public String obtenerTotalDia(LocalDate dia) {
        String respuesta = null;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            List<Double> lista=s.createSQLQuery("select sum(monto) from Pago where fechaDePago=\'"+dia+"\'").list();
            if(lista!=null && lista.size()>0 &&lista.get(0)!=null){
                respuesta = lista.get(0).toString();
            }else{
                respuesta = "0";
            }
                

        } catch (Exception e) {
            System.out.println("No se pudo obtener el total del dia");
            e.printStackTrace();
        } finally {
            s.close();
        }

        
        return respuesta;
    }
    
    
    public String obtenerTotalMes(int mes, int anio) {
        
     
        String respuesta = null;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            List<Double> lista=s.createSQLQuery("select sum(monto) from Pago where MONTH(fechaDePago)="+mes+" and YEAR(fechaDePago)="+anio).list();
            if(lista!=null && lista.size()>0 &&lista.get(0)!=null){
                respuesta = lista.get(0).toString();
            }else{
                respuesta = "0";
            }
                

        } catch (Exception e) {
            System.out.println("No se pudo obtener el total del dia");
            e.printStackTrace();
        } finally {
            s.close();
        }

        
        return respuesta;
    }

    public String obtenerTotalAnio(String anio) {
        
        String respuesta = null;

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();

        try {

            List<Double> lista=s.createSQLQuery("select sum(monto) from Pago where YEAR(fechaDePago)="+anio).list();
            if(lista!=null && lista.size()>0 &&lista.get(0)!=null){
                respuesta = lista.get(0).toString();
            }else{
                respuesta = "0";
            }
                

        } catch (Exception e) {
            System.out.println("No se pudo obtener el total del dia");
            e.printStackTrace();
        } finally {
            s.close();
        }

        return respuesta;
     
    }
    
    
   
        
        
        
        
   

}