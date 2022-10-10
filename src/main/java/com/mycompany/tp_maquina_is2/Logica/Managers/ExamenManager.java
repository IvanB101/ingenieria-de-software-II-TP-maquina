/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.ExamenDAOImp;
import java.util.HashMap;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import java.util.List;

/**
 *
 * @author ivanb
 */
public abstract class ExamenManager {

    private static HashMap<Integer, Examen> examenes;
    private static ExamenDAOImp examenDAOImp;

    public static void init(Conexion conexion) {
        examenDAOImp = new ExamenDAOImp(conexion);
        examenes = examenDAOImp.read();
    }

    public static boolean agregar(List<Examen> examenes) {
        for (Examen examen : examenes) {
            System.out.println(examen);
        }

        return false;
    }
}
