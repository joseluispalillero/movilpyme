/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.controller;

import com.google.gson.Gson;
import com.mx.movilpyme.responses.RegistroResponse;
import com.mx.movilpyme.services.RegistroService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping(value = "/registro")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @RequestMapping(value = "/listado", method = RequestMethod.GET, produces = "application/json")
    public String registro(@RequestParam(required = false, value = "numeroEmpleado") String numeroEmpleado,
            @RequestParam(required = false, value = "puesto") String puesto) {

        List<RegistroResponse> registros = registroService.find(numeroEmpleado, puesto);
        Map<String, Object> mapa = new HashMap<String, Object>();
        mapa.put("data", registros);
        return new Gson().toJson(mapa);
    }

    public void setRegistroService(RegistroService registroService) {
        this.registroService = registroService;
    }

}
