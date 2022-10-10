/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.HistoriaAcademicaDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
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
    
    public static boolean agregar(HistoriaAcademica historiaAcademica) {
        // TODO
        return false;
    }
    public static HashMap<Integer,Integer> listaExamenes(int nroRegistro){
        HistoriaAcademica historia = historiasAcademicas.get(nroRegistro);
        ArrayList<Integer> codigosMat = new ArrayList();
        HashMap<Integer,Integer> ranking = new HashMap<>();
        //buscar los codigos de las materias regulares
        for(int i=0;i<historia.getEstados().size();i++){ 
            if( (historia.getEstados().get(i).getCondicion().toString()).equals("regular")){
                codigosMat.add(historia.getEstados().get(i).getCodMateria());
                int cant=MateriaManager.buscarCorrelativas(historia.getEstados().get(i).getCodMateria()).size();
                ranking.put(historia.getEstados().get(i).getCodMateria(),cant);
            }
            
        }
        return ranking;
    }
    
}
    
    
   
   

