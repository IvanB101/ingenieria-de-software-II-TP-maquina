/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.EstudianteDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import java.util.HashMap;

/**
 *
 * @author ivanb
 */
public abstract class EstudianteManager {
    private static HashMap<Integer, Estudiante> estudiantes;
    private static EstudianteDAOImp estudianteDAOImp;
    
    public static void init(Conexion conexion) {
        estudianteDAOImp = new EstudianteDAOImp(conexion);
        estudiantes = estudianteDAOImp.read();
    }
}
