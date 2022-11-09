/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.MesaExamenDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.MesaExamen;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public abstract class MesaManager {
    
    private static MesaExamenDAOImp mesaExamenDAOImp;
    
    public static void init(Conexion conexion) {
        mesaExamenDAOImp = new MesaExamenDAOImp(conexion);
    }
    
    public static void agregarMesa(MesaExamen mesa) throws ManagementException {
        try {
            mesaExamenDAOImp.create(mesa);
        } catch (SQLException e) {
                throw new ManagementException(e.getMessage());
            }
        }
        
        public static void eliminarMesa(String codigo) throws ManagementException {
        try {
            mesaExamenDAOImp.delete(codigo);
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
