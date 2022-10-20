/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public interface ExamenDAOInter {

    public void create(Examen examen) throws SQLException;

    public Examen read(String codMateria, int nroRegistro, String codPlanEstudios) throws SQLException;

    public void update(String codigo, Examen examen) throws SQLException;

    public void delete(String codigo) throws SQLException;
}
