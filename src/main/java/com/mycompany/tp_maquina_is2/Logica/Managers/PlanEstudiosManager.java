/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.PlanEstudiosDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.util.HashMap;

/**
 *
 * @author ivanb
 */
public abstract class PlanEstudiosManager {
    private static HashMap<String, PlanEstudios> planesEstudios;
    private static PlanEstudiosDAOImp planEstudiosDAOImp;
    
    public static void init(Conexion conexion) {
        planEstudiosDAOImp = new PlanEstudiosDAOImp(conexion);
        
        planesEstudios = planEstudiosDAOImp.read();
    }
    
    /**
     * Completa las asociaciones que no se leen directamente de la base de datos
     */
    public static void initAsociaciones() {
        // numero registro estudiantes
    }
    
    /**
     * @param codPlanEstudios codigo del plan de estudios
     * @param codMateria codigo de la materia
     * @return true si la materia pertenece al plan de estudios, false en otro caso
    */
    public static boolean comprobarMateria(String codPlanEstudios, int codMateria) {
        return planesEstudios.get(codPlanEstudios).getCodMaterias().indexOf(codMateria) != -1;
    }
    
    public static boolean agregar(PlanEstudios planEstudios) {
        planesEstudios.put(planEstudios.getCodigo(), planEstudios);
        
        return planEstudiosDAOImp.create(planEstudios);
    }
}
