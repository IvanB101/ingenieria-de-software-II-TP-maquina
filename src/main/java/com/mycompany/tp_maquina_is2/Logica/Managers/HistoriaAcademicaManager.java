/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.HistoriaAcademicaDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado.Condicion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import java.util.ArrayList;
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
    
    /**
     * Completa las asociaciones que no se leen directamente de la base de datos
     */
    public static void initAsociaciones() {
        // codigo materias examenes
        // codigo examenes
    }

    public static boolean agregar(HistoriaAcademica historiaAcademica) {
        historiasAcademicas.put(historiaAcademica.getNroRegEstudiante(), historiaAcademica);

        return historiaAcademicaDAOImp.create(historiaAcademica);
    }

    public static HashMap<Materia, Integer> listaExamenes(int nroRegistro) {
        HistoriaAcademica historia = historiasAcademicas.get(nroRegistro);
        HashMap<Materia, Integer> ranking = new HashMap<>();
        ArrayList<Materia> correlativas = new ArrayList();
        int cant = 0;
        for (int i = 0; i < historia.getEstados().size(); i++) {
            //si el estado de una materia de la historia es regular
            if ((historia.getEstados().get(i).getCondicion().toString()).equals("regular")) {
                //ver cuales son sus correlativas| y si no tiene correlativas? ay
                correlativas = MateriaManager.buscarCorrelativas(historia.getEstados().get(i).getCodMateria());
                //ver si las correlativas las tiene aprobadas
                if (cumpleRequisitos(correlativas, historia)) {
                    //contar la cantidad de correlativas
                    cant = correlativas.size();
                    ranking.put(MateriaManager.buscarMateria(historia.getEstados().get(i).getCodMateria()), cant);
                }
            }

        }
        return ranking;

    }
    
    public static boolean cumpleRequisitos(ArrayList<Materia> codCorrelativas, HistoriaAcademica historia) {
        for (Materia correlativa : codCorrelativas) {
            if(!historia.getEstados().get(correlativa.getCodigo()).getCondicion().equals(Condicion.aprobado)) {
                return false;
            }
        }
        
        return true;
    }

}
