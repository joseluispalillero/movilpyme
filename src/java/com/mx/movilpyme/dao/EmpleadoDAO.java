/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.dao;

import com.mx.movilpyme.model.Empleado;

/**
 *
 * @author jpalillero
 */
public interface EmpleadoDAO {
    public Empleado getEmpleadoById(Integer id);
    public Empleado getEmpleadoByNumEmpleado(String numEmpleado);
    public Empleado getEmpleadoByNumEmpleadoPassword(String numEmpleado, String password);

}
