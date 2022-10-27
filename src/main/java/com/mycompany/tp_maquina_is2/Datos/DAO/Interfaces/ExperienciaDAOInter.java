/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Experiencia;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public interface ExperienciaDAOInter {

    public void create(Experiencia experiencia) throws SQLException;

    public Experiencia read(String codigo) throws SQLException;

    public void update(String codigo, Experiencia experiencia) throws SQLException;

    public void delete(String codigo) throws SQLException;
}
