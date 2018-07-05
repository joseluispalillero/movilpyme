/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.dao;

import com.mx.movilpyme.model.Empleado;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jpalillero
 */
@Repository
public class EmpleadoDAOImpl implements EmpleadoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Empleado getEmpleadoById(Integer id) {
        Empleado empleado = new Empleado();
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Empleado as empl where empl.id=" + id);
        empleado = (Empleado) q.uniqueResult();
        session.close();
        return empleado;
    }
    
    @Override
    public Empleado getEmpleadoByNumEmpleado(String numeroEmpleado) {
        Empleado empleado = new Empleado();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Empleado as empl where empl.numeroEmpleado='" + numeroEmpleado+"'");
            empleado = (Empleado) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
    }
    
    @Override
    public Empleado getEmpleadoByNumEmpleadoPassword(String numeroEmpleado, String password) {
        Session session = sessionFactory.openSession();
        
        return (Empleado) session.createCriteria(Empleado.class)
                .add(Restrictions.eq("numeroEmpleado", numeroEmpleado))
                .add(Restrictions.eq("password", password)).uniqueResult();        
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
