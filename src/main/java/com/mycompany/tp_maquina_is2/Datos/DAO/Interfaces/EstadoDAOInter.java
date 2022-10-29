/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public interface EstadoDAOInter {

    public void create(Estado estado) throws SQLException;

    public Estado read(String codigo) throws SQLException;

    public void update(String codigo, Estado estado) throws SQLException;

    public void delete(String codigo) throws SQLException;
}
