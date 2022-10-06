/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Experiencia;

/**
 *
 * @author ivanb
 */
public interface ExperienciaDAOInter {
    public boolean create();
    public Experiencia read();
    public boolean update();
    public boolean delete();
}
