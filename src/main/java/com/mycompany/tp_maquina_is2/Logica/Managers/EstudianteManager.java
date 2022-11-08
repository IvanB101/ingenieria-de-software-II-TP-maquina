/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.EstudianteDAOImp;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.MesaExamenDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.MesaExamen;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ivanb
 */
public abstract class EstudianteManager {
    private static EstudianteDAOImp estudianteDAOImp;
    private static MesaExamenDAOImp mesaExamenDAOImp;
    
    public static void init(Conexion conexion) {
        estudianteDAOImp = new EstudianteDAOImp(conexion);
        mesaExamenDAOImp = new MesaExamenDAOImp(conexion);
    }
    
    public static void agregar(Estudiante estudiante) throws ManagementException {
        try {
            estudianteDAOImp.create(estudiante);
        } catch (SQLException e) {
            if(e.getMessage().contains("llave duplicada")) {
                throw new ManagementException("Ya existe un estudiante con el nro de registro ingresado");
            } else {
                throw new ManagementException(e.getMessage());
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
                JOptionPane.showMessageDialog(null, "No existe un estudiante con el Nro de Registro ingresado");
            } else {
                System.out.println(e.getMessage());
            }
            
            return null;
        }
    }
    
    public static void modificar(int nroRegistro, Estudiante estudiante) throws ManagementException {
         try {
             estudianteDAOImp.update(nroRegistro, estudiante);
         } catch (SQLException e) {
             throw new ManagementException(e.getMessage());
         }
    }
    
    public static void eliminar(int nroRegistro) throws ManagementException {
        try {
             estudianteDAOImp.delete(nroRegistro);
         } catch (SQLException e) {
             throw new ManagementException(e.getMessage());
         }
    }
    
    public static void añadirInscripcion(String codigo,int nroRegistro) throws ManagementException {
        try {
             mesaExamenDAOImp.createInscripcion(codigo,nroRegistro);
         } catch (SQLException e) {
             throw new ManagementException(e.getMessage());
         }
    }
       public static void deleteInscripcion(String codigo,int nroRegistro) throws ManagementException {
        try {
             mesaExamenDAOImp.deleteInscripcion(codigo, nroRegistro);
         } catch (SQLException e) {
             throw new ManagementException(e.getMessage());
         }
    }
       public static ArrayList<MesaExamen> obtenerMesasEstudiante(int nroRegistro) throws ManagementException {
        ArrayList<MesaExamen> mesas =new ArrayList();
           try {
             mesas=mesaExamenDAOImp.obtenerMesasInscriptas(nroRegistro);
         } catch (SQLException e) {
             throw new ManagementException(e.getMessage());
         }
           return mesas;
    }
       public static ArrayList<Estudiante> ObtenerInscriptosMesa(String codigo) throws ManagementException {
        ArrayList<Estudiante> estudiantes=new ArrayList();
           try {
             estudiantes = mesaExamenDAOImp.obtenerEstudiantesMesa(codigo);
         } catch (SQLException e) {
             throw new ManagementException(e.getMessage());
         }
           return estudiantes;
    }
       
    
    
}
