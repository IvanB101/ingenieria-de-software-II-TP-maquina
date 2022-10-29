/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.EstudianteDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public abstract class EstudianteManager {
    private static EstudianteDAOImp estudianteDAOImp;
    
    public static void init(Conexion conexion) {
        estudianteDAOImp = new EstudianteDAOImp(conexion);
    }
    
    public static void agregar(Estudiante estudiante) {
        try {
            estudianteDAOImp.create(estudiante);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if(e.getMessage().contains("llave duplicada")) {
                // TODO
            } else {
                // TODO
            }
        }
    }
    
    public static Estudiante leer(int nroRegistro) {
        try {
            Estudiante estudiante = estudianteDAOImp.read(nroRegistro);
            // TODO agregar codigos mesas inscriptas, historias academicas;
            
            return estudiante;
        } catch (SQLException e) {
            if(e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next.")) {
                // TODO no hay estudiante con el nro de registro
            } else {
                System.out.println(e.getMessage());
            }
            
            return null;
        }
    }
    
    public static void modificar(int nroRegistro, Estudiante estudiante) {
         try {
             estudianteDAOImp.update(nroRegistro, estudiante);
         } catch (SQLException e) {
             // TODO
             System.out.println(e.getMessage());
         }
    }
    
    public static void eliminar(int nroRegistro) {
        // TODO
    }
}
