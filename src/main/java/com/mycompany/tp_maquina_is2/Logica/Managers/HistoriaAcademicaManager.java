/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.HistoriaAcademicaDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado;
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
    //CONTAR DE QUE MATERIAS ES CORRELATIVA
    public static HashMap<Materia, Integer> listaExamenes(int nroRegistro) {
        HistoriaAcademica historia = historiasAcademicas.get(nroRegistro);
        HashMap<Materia, Integer> ranking = new HashMap<>();
        ArrayList<Materia> correlativas = new ArrayList();
        int cant=0;
        for (Estado estado : historiasAcademicas.get(nroRegistro).getEstados().values()) {
            if(estado.getCondicion().equals(Condicion.regular)) {
                //correlativas de una materia regular
                correlativas = MateriaManager.buscarCorrelativas(estado.getCodMateria());
                if (cumpleRequisitos(correlativas, historia)) {
                    //busco en cuantas materias es correlativa
                    cant=MateriaManager.esCorrelativaDe(estado.getCodMateria());
                    ranking.put(MateriaManager.buscarMateria(estado.getCodMateria()),cant );
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
