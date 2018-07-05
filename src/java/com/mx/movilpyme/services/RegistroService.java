/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.services;

import com.mx.movilpyme.dao.EmpleadoDAO;
import com.mx.movilpyme.dao.PuestoDAO;
import com.mx.movilpyme.dao.RegistroDAO;
import com.mx.movilpyme.model.Empleado;
import com.mx.movilpyme.model.Puesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.movilpyme.model.Registro;
import com.mx.movilpyme.responses.RegistroResponse;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jpalillero
 */
@Service
public class RegistroService {

    @Autowired
    private RegistroDAO registroDAO;
    
    @Autowired
    private EmpleadoDAO empleadoDAO;
    
    @Autowired
    private PuestoDAO puestoDAO;

    @Transactional(readOnly = true)
    public Registro getRegistroByEmpleadoFecha(Integer idEmpleado, Date fecha) {
        return registroDAO.getRegistroByEmpleadoFecha(idEmpleado, fecha);
    }

    @Transactional
    public Registro saveRegistro(Registro registro) {
        return registroDAO.saveRegistro(registro);
    }
    
    @Transactional
    public Registro updateRegistro(Registro registro) {
        return registroDAO.updateRegistro(registro);
    }

    @Transactional(readOnly = true)
    public List<RegistroResponse> find(String numeroEmpleado, String puesto) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dateFormatC = new SimpleDateFormat("dd/MMM/YYYY");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        List<RegistroResponse> registroResponseList = new ArrayList<RegistroResponse>();
        for (Registro registro : registroDAO.find(numeroEmpleado, puesto)){
            RegistroResponse registroResponse = new RegistroResponse();
            Empleado empleado = empleadoDAO.getEmpleadoById(registro.getEmpleadoId());
            Puesto puestoE = puestoDAO.getPuestoById(empleado.getPuestoId());
            registroResponse.setHoraEntrada(dateFormat.format(registro.getHoraEntrada()));
            registroResponse.setHoraSalida(registro.getHoraSalida() != null ? dateFormat.format(registro.getHoraSalida()) : "-");
            registroResponse.setNombre(empleado.getNombre());
            registroResponse.setPuesto(puestoE.getNombre());
            registroResponse.setFecha(dateFormatC.format(registro.getFechaRegistro()));
            registroResponse.setRetardo(registro.getRetardo()? "Retardo": "No retardo");
            registroResponse.setSueldo(formatter.format(empleado.getSueldo()));
            registroResponseList.add(registroResponse);
        }
        return registroResponseList; 
    }

    public void setRegistroDAO(RegistroDAO registroDAO) {
        this.registroDAO = registroDAO;
    }
   
}
