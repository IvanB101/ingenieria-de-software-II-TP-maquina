/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public interface EstudianteDAOInter {

    public void create(Estudiante estudiante) throws SQLException;

    public Estudiante read(int nroRegistro) throws SQLException;

    public void update(int nroRegistro, Estudiante estudiante) throws SQLException;

    public void delete(int nroRegistro) throws SQLException;
}
