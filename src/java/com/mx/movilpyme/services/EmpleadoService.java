/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.services;

import com.mx.movilpyme.dao.EmpleadoDAO;
import com.mx.movilpyme.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jpalillero
 */
@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private EmpleadoDAO empleadoDAO;

    public Empleado getEmpleadoByNumeroEmpleadoPassword(String numeroEmpleado, String password) {
        return empleadoDAO.getEmpleadoByNumEmpleadoPassword(numeroEmpleado, password);
    }
    
    public Empleado getEmpleadoByNumeroEmpleado(String numeroEmpleado) {
        return empleadoDAO.getEmpleadoByNumEmpleado(numeroEmpleado);
    }

    public void setEmpleadoDAO(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

}
