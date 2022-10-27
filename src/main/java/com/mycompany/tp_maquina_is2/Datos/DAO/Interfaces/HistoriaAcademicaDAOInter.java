/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public interface HistoriaAcademicaDAOInter {

    public void create(HistoriaAcademica historiaAcademica) throws SQLException;

    public HistoriaAcademica read(int nroRegistro, String codPlanEstudios) throws SQLException;

    public void update(int nroRegistro, String codPlanEstudios, HistoriaAcademica historiaAcademica) throws SQLException;

    public void delete(int nroRegistro, String codPlanEstudios) throws SQLException;

}
