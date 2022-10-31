/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.MateriaDAOImp;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.PlanEstudiosDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author ivanb
 */
public abstract class PlanEstudiosManager {

    private static PlanEstudiosDAOImp planEstudiosDAOImp;
    private static MateriaDAOImp materiaDAOImp;

    private static PlanEstudios planEstudios;

    public static void init(Conexion conexion) {
        planEstudiosDAOImp = new PlanEstudiosDAOImp(conexion);

        materiaDAOImp = new MateriaDAOImp(conexion);
    }

    /**
     * @param codPlanEstudios codigo del plan de estudios
     * @param codMateria codigo de la materia
     * @return true si la materia pertenece al plan de estudios, false en otro
     * caso
     * @throws com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException
     */
    public static boolean comprobarMateria(String codPlanEstudios, String codMateria) throws ManagementException {
        try {
            if (!planEstudios.getCodigo().equals(codPlanEstudios)) {
                buscar(codPlanEstudios);
            }

            return (planEstudios.getMaterias().get(codMateria) != null);
        } catch (ManagementException e) {
            throw e;
        }
    }
    
    public static PlanEstudios buscar(String codPlanEstudios) throws ManagementException {
        try {
            if (!planEstudios.getCodigo().equals(codPlanEstudios)) {
                planEstudios = planEstudiosDAOImp.read(codPlanEstudios);

                planEstudios.setMaterias(materiaDAOImp.materiasFromPlanEstudios(codPlanEstudios));
            }

            return planEstudios;
        } catch (SQLException e) {
            if (e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next.")) {
                throw new ManagementException("No hay un plan de estudios con el codigo " + codPlanEstudios + " cargado");
            }
        }
        
        return null;
    }

    public static void agregar(PlanEstudios planEstudios, LinkedList<Materia> materias) {
        try {
            planEstudiosDAOImp.create(planEstudios);
            
            // TODO carga de las materias
        } catch (SQLException e) {
            // TODO
        }
    }
}
