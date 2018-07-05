/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jpalillero
 */
@RestController
@RequestMapping(value = "/")
public class NavigationController {
    
    @RequestMapping(value = "/registro", method = RequestMethod.GET)
    public ModelAndView index() {
        
        ModelAndView modelAndView = new ModelAndView("registro");
        return modelAndView;
    }
    
    @RequestMapping(value = "/listado", method = RequestMethod.GET)
    public ModelAndView listado() {
        
        ModelAndView modelAndView = new ModelAndView("listado");
        return modelAndView;
    }
    
}
