/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.ExamenDAOImp;
import java.util.HashMap;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Experiencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivanb
 */
public abstract class ExamenManager {

    private static HashMap<String, Examen> examenes;
    private static ExamenDAOImp examenDAOImp;

    public static void init(Conexion conexion) {
        examenDAOImp = new ExamenDAOImp(conexion);
        
        examenes = examenDAOImp.read();
    }

    public static ArrayList<Examen> examenesEstudiante(int cod_historia) {
        ArrayList<Examen> aux = new ArrayList<>();
        
        for (Examen examen : examenes.values()) {
            if (examen.getNroRegitroEstudiante() == cod_historia) {
                aux.add(examen);
            }
        }
        
        return aux;
    }

    public static boolean agregar(List<Examen> examenesA) {
        for (int i = 0; i < examenesA.size(); i++) {
            examenDAOImp.create(examenesA.get(i));
        }

        return false;
    }

    public static boolean agregar(Examen examen) {
        return examenDAOImp.create(examen);
    }
    
    public static boolean agregarExperiencia(Experiencia experiencia) {
        Examen examen = examenes.get(experiencia.getCodExamen());
        examen.setExperiencia(experiencia);
        
        return examenDAOImp.create(examen);
    }
}
