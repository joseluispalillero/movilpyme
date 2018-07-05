/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.controller;

import com.google.gson.Gson;
import com.mx.movilpyme.model.Empleado;
import com.mx.movilpyme.model.Horario;
import com.mx.movilpyme.model.Registro;
import com.mx.movilpyme.responses.MovilPymeResponse;
import com.mx.movilpyme.services.EmpleadoService;
import com.mx.movilpyme.services.HorarioService;
import com.mx.movilpyme.services.RegistroService;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jpalillero
 */
@RestController
@RequestMapping(value = "/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private RegistroService registroService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public String registro(@RequestParam("numeroEmpleado") String numeroEmpleado,
            @RequestParam("password") String password) {
        MovilPymeResponse movilPymeResponse = new MovilPymeResponse();

        Empleado empleado = empleadoService.getEmpleadoByNumeroEmpleadoPassword(numeroEmpleado, password);

        if (empleado == null) {
            movilPymeResponse.setStatus(500);
            movilPymeResponse.setMessage("Error no se encontro el numero de empleado o el password no corresponde");
            movilPymeResponse.setResponse("");
        } else {
            movilPymeResponse.setStatus(200);
            movilPymeResponse.setMessage("");
            movilPymeResponse.setResponse("ok");
        }

        return new Gson().toJson(movilPymeResponse);
    }

    @RequestMapping(value = "/registro", method = RequestMethod.POST, produces = "application/json")
    public String registro(@RequestParam("numeroEmpleado") String numeroEmpleado) {
        MovilPymeResponse movilPymeResponse = new MovilPymeResponse();
        Empleado empleado = empleadoService.getEmpleadoByNumeroEmpleado(numeroEmpleado);

        //verificar si existe empleado
        if (empleado == null) {
            movilPymeResponse.setStatus(500);
            movilPymeResponse.setMessage("No se encontro el numero de empleado");
            movilPymeResponse.setResponse("");
        } else {
            Calendar horaRegistro = Calendar.getInstance();
            // verificar si existe registro existente, solo puede registrar al dia un registro
            Registro registroExiste = registroService.getRegistroByEmpleadoFecha(empleado.getId(), horaRegistro.getTime());
            if (registroExiste != null) {
                movilPymeResponse.setStatus(500);
                movilPymeResponse.setMessage("Ya se hizo el registro a las " + registroExiste.getHoraEntrada());
                movilPymeResponse.setResponse("");
            } else {
                // verificar si llego tarde, con los minutos de retardo
                Horario horario = horarioService.getHorarioById(empleado.getHorarioTrabajoId());
                Calendar horaEntrada = Calendar.getInstance();
                horaEntrada.set(Calendar.HOUR, horario.getHoraEntrada().getHours());
                horaEntrada.set(Calendar.MINUTE, horario.getHoraEntrada().getMinutes() + horario.getTolerancia().getMinutes());
                horaEntrada.set(Calendar.SECOND, horario.getTolerancia().getSeconds());

                //Creamos el registro de entrada
                Registro registro = new Registro();
                Calendar fechaRegistro = Calendar.getInstance();
                fechaRegistro.set(Calendar.HOUR, 0);
                fechaRegistro.set(Calendar.MINUTE, 0);
                fechaRegistro.set(Calendar.SECOND, 0);
                registro.setFechaRegistro(fechaRegistro.getTime());
                registro.setEmpleadoId(empleado.getId());
                registro.setHoraEntrada(horaRegistro.getTime());
                registro.setRetardo(horaRegistro.compareTo(horaEntrada) > 0 ? true : false);
                registroService.saveRegistro(registro);

                movilPymeResponse.setStatus(200);
                movilPymeResponse.setResponse("Hola, " + empleado.getNombre() + ", se ha marcado tu entrada");
            }
        }
        return new Gson().toJson(movilPymeResponse);
    }

    @RequestMapping(value = "/registroSalida", method = RequestMethod.POST, produces = "application/json")
    public String registroSalida(@RequestParam("numeroEmpleado") String numeroEmpleado) {
        MovilPymeResponse movilPymeResponse = new MovilPymeResponse();
        Empleado empleado = empleadoService.getEmpleadoByNumeroEmpleado(numeroEmpleado);

        //verificar si existe empleado
        if (empleado == null) {
            movilPymeResponse.setStatus(500);
            movilPymeResponse.setMessage("No se encontro el numero de empleado");
            movilPymeResponse.setResponse("");
        } else {
            Calendar horaSalida = Calendar.getInstance();
            // verificar si existe un registro del dia, para poder cerrarlo
            Registro registro = registroService.getRegistroByEmpleadoFecha(empleado.getId(), horaSalida.getTime());
            if (registro == null) {
                movilPymeResponse.setStatus(500);
                movilPymeResponse.setMessage("Aun no se ha realizado el registro de entrada ");
                movilPymeResponse.setResponse("");
            } else {

                //verificar si ya se cerro el registro
                if (registro.getHoraSalida() != null) {
                    movilPymeResponse.setStatus(500);
                    movilPymeResponse.setMessage("Ya se realizo el registro de salida a las " + registro.getHoraSalida());
                    movilPymeResponse.setResponse("");
                } else {
                    //se marca como cerrado el registro y se pone la fecha de salida
                    registro.setHoraEntrada(horaSalida.getTime());
                    registroService.updateRegistro(registro);

                    movilPymeResponse.setStatus(200);
                    movilPymeResponse.setResponse("Hola, " + empleado.getNombre() + ", se ha marcado tu salida");
                }
            }
        }
        return new Gson().toJson(movilPymeResponse);
    }

    public void setEmpleadoService(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public void setHorarioService(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    public void setRegistroService(RegistroService registroService) {
        this.registroService = registroService;
    }

}
