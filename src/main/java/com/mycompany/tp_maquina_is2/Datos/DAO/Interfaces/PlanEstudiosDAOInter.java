/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;


public interface PlanEstudiosDAOInter {

    public boolean create(PlanEstudios planEstudios);

    public PlanEstudios read(String codigo);

    public boolean update(String codigo, PlanEstudios planEstudios);

    public boolean delete(String codigo);
    
}
