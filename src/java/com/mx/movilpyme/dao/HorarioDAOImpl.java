/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.dao;

import com.mx.movilpyme.model.Horario;
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
public class HorarioDAOImpl implements HorarioDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Horario getHorarioById(Integer id) {
        Horario horario = new Horario();
        Session session = sessionFactory.getCurrentSession();
        try {
            //session.beginTransaction();
            Query q = session.createQuery("from Horario as hor where hor.id = " + id);
            horario = (Horario) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horario;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
