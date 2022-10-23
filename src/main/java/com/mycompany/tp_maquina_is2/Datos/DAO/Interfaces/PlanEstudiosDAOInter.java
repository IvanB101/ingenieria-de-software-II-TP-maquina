/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.sql.SQLException;

public interface PlanEstudiosDAOInter {

    public void create(PlanEstudios planEstudios) throws SQLException;

    public PlanEstudios read(String codigo) throws SQLException;

    public void update(String codigo, PlanEstudios planEstudios) throws SQLException;

    public void delete(String codigo) throws SQLException;

}
