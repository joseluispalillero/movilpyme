/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.movilpyme.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mx.movilpyme.dao.HorarioDAO;
import com.mx.movilpyme.model.Horario;

/**
 *
 * @author jpalillero
 */
@Service
@Transactional
public class HorarioService {

    @Autowired
    private HorarioDAO horarioDAO;

    public Horario getHorarioById(Integer id) {
        return horarioDAO.getHorarioById(id);
    }

    public void setHorarioDAO(HorarioDAO horarioDAO) {
        this.horarioDAO = horarioDAO;
    }

}
