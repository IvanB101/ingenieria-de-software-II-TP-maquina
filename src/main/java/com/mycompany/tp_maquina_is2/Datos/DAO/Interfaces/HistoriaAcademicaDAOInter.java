/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
import java.util.ArrayList;

/**
 *
 * @author ivanb
 */
public interface HistoriaAcademicaDAOInter {
    public boolean create(HistoriaAcademica historiaAcademica);
    public ArrayList<HistoriaAcademica> read();
    public boolean update(int codigo, HistoriaAcademica historiaAcademica);
    public boolean delete(int codigo);
}
