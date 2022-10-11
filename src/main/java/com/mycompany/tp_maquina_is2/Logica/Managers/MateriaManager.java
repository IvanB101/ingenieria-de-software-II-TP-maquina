/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.MateriaDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ivanb
 */
public abstract class MateriaManager {

    private static HashMap<Integer, Materia> materias;
    private static MateriaDAOImp materiaDAOImp;

    public static void init(Conexion conexion) {
        materiaDAOImp = new MateriaDAOImp(conexion);
        materias = materiaDAOImp.read();
    }
    
    /**
     * Completa las asociaciones que no se leen directamente de la base de datos
     */
    public static void initAsociaciones() {
        // codigo mesas de examenes
        // codigo examenes
        // estados
    }

    public static boolean agregar(List<Materia> materias) {
        for (Materia materia : materias) {
            if(!materiaDAOImp.create(materia)) {
                return false;
            }
            
            MateriaManager.materias.put(materia.getCodigo(), materia);
        }
        
        return true;
    }

    public static ArrayList<Materia> buscarCorrelativas(int codigo) {
        return materias.get(codigo).getCorrelativas(); //obtengo las correlativas de una materia
    }
    public static Integer esCorrelativaDe(int codigo){
       int cantidad=0;
       for(Materia materia : materias.values()){ //por cada materia
           //si en las correlativas de una materia esta la mat
           if( materia.getCorrelativas().contains((buscarMateria(codigo))) )
               cantidad++; 
       }
    return  cantidad;
    }
    
    public static Materia buscarMateria(int codigo) {
        return materias.get(codigo);
    }

    public static ArrayList<Materia> obtenerMaterias() {
        return (ArrayList) materias.values();
    }

}
