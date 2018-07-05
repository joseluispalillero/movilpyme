/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.dao;

import com.mx.movilpyme.model.Registro;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jpalillero
 */
@Repository
public class RegistroDAOImpl implements RegistroDAO {

    private static final Logger logger = LoggerFactory.getLogger(RegistroDAOImpl.class);
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Registro getRegistroByEmpleadoFecha(Integer idEmpleado, Date fecha) {
        Registro registro;
        fecha.setHours(0);
        fecha.setMinutes(0);
        fecha.setSeconds(0);
        Session session = sessionFactory.getCurrentSession();
        registro = (Registro) session.createCriteria(Registro.class)
                .add(Restrictions.eq("empleadoId", idEmpleado))
                .add(Restrictions.eq("fechaRegistro", fecha)).uniqueResult();
        return registro;
    }
    
    @Override
    public Registro saveRegistro(Registro registro){
        Session session = sessionFactory.openSession();
        session.persist(registro);
        session.flush();
        session.close();
        logger.info("Registro: " + registro);
        return registro;
    }
    
    @Override
    public Registro updateRegistro(Registro registro){
        Session session = sessionFactory.openSession();
        session.update(registro);
        session.flush();
        session.close();
        logger.info("Registro: " + registro);
        return registro;
    }

    @Override
    public List<Registro> find(String numeroEmpleado, String puesto) {
        Session session = sessionFactory.openSession();
        
        List<Registro> registroList = session.createQuery("from Registro").list();
        for(Registro registro : registroList){
            logger.info("Registro::" + registro);
        }
        session.close();
        return registroList;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
