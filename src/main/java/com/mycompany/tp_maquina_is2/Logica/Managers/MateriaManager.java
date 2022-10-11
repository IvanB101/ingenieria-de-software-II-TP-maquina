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

    public static boolean agregar(List<Materia> materias) {
        for (Materia materia : materias) {
            System.out.println(materia);
        }
       return true; 
    }

    public static ArrayList<Materia> buscarCorrelativas(int codigo){
        return materias.get(codigo).getCorrelativas(); //obtengo las correlativas de una materia
    }
    public static Materia buscarMateria(int codigo){
        return materias.get(codigo);
    }
    public static ArrayList<Materia> obtenerMaterias(){
        return (ArrayList)materias.values();
    }
    
}
