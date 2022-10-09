/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.HistoriaAcademicaDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
import java.util.HashMap;

/**
 *
 * @author ivanb
 */
public abstract class HistoriaAcademicaManager {
    private static HashMap<Integer, HistoriaAcademica> historiasAcademicas;
    private static HistoriaAcademicaDAOImp historiaAcademicaDAOImp;
    
    public static void init(Conexion conexion) {
        historiaAcademicaDAOImp = new HistoriaAcademicaDAOImp(conexion);
        historiasAcademicas = historiaAcademicaDAOImp.read();
    }
}
