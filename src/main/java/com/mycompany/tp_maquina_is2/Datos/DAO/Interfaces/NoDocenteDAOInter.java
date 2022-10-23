/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.NoDocente;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public interface NoDocenteDAOInter {

    public void create(NoDocente noDocente) throws SQLException;

    public NoDocente read(int nroLegajo) throws SQLException;

    public void update(int codigo, NoDocente noDocente) throws SQLException;

    public void delete(int codigo) throws SQLException;
}
