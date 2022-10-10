/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.ExamenDAOImp;
import java.util.HashMap;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
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
    public static ArrayList<Examen> examenesEstudiante(int cod_historia){
        int i=0;
        ArrayList<Examen> aux=new ArrayList<Examen>();
        ArrayList<Examen> aux2=(ArrayList<Examen>)examenes.values();
        for(i=0;i<aux2.size();i++){
            if(aux2.get(i).getCodHistoriaAcademica()==cod_historia){
                aux.add(aux2.get(i));
            }
        }
        return aux;
    
    }
    
    public static boolean agregar(List<Examen> examenesA) {
        int i=0;
        for(i=0;i<examenesA.size();i++){
            examenDAOImp.create(examenesA.get(i));
        }
        
        return false;
    }
    public static boolean agregarUnico(Examen examen){
        examenDAOImp.create(examen);
        return false;
    }
    public static boolean agregarExpExamen(Examen examen){
        if(examenDAOImp.update(examen.getCodigo(),examen))
            return true;
        return false;
    }
}
