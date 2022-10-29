/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public interface MateriaDAOInter {

    public void create(Materia materia) throws SQLException;

    public Materia read(String codigo, String codPlanEstudios) throws SQLException;

    public void update(String codigo, String codPlanEstudios, Materia materia) throws SQLException;

    public void delete(String codigo, String codPlanEstudios) throws SQLException;
}
