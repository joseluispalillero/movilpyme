/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.dao;

import com.mx.movilpyme.model.Puesto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jpalillero
 */
@Repository
public class PuestoDAOImpl implements PuestoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Puesto getPuestoById(Integer id) {
        Puesto puesto = new Puesto();
        Session session = sessionFactory.openSession();
        try {
            Query q = session.createQuery("from Puesto as pue where pue.id = " + id);
            puesto = (Puesto) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return puesto;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
