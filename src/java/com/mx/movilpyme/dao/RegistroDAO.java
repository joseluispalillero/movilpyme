/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.dao;

import com.mx.movilpyme.model.Registro;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jpalillero
 */
public interface RegistroDAO {

    public Registro getRegistroByEmpleadoFecha(Integer idEmpleado, Date fecha);
    public Registro saveRegistro(Registro registro);
    public Registro updateRegistro(Registro registro);
    public List<Registro> find(String numeroEmpleado, String puesto);
}
