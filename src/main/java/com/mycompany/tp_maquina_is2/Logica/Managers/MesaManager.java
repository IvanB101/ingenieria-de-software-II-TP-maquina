/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.MesaExamenDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.MesaExamen;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan
 */
public abstract class MesaManager {

    private static MesaExamenDAOImp mesaExamenDAOImp;

    /**
     * Metodo que inicializa el DAO de mesa de examen
     *
     * @param conexion objeto del cual se obtiene la conexion a la base de datos
     */
    public static void init(Conexion conexion) {
        mesaExamenDAOImp = new MesaExamenDAOImp(conexion);
    }

    /**
     * Metodo para agregar una mesa de examen
     *
     * @param mesa de examen a agregar
     * @throws ManagementException
     */
    public static void agregarMesa(MesaExamen mesa) throws ManagementException {
        try {
            mesaExamenDAOImp.create(mesa);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    /**
     * Metodo para aliminar una mesa de examen
     *
     * @param codigo de la mesa de examen a eliminar
     * @throws ManagementException
     */
    public static void eliminarMesa(String codigo) throws ManagementException {
        try {
            mesaExamenDAOImp.delete(codigo);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    /**
     * Metodo para añadir una inscripcion de un estudiante a una mesa de examen
     *
     * @param codigo de la mesa de examen
     * @param nroRegistro del estudiante
     * @throws ManagementException
     */
    public static void añadirInscripcion(String codigo, int nroRegistro) throws ManagementException {
        try {
            mesaExamenDAOImp.createInscripcion(codigo, nroRegistro);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    /**
     * Metodo para eliminar una inscripcion a una mesa de examen
     *
     * @param codigo del planEstudios
     * @param codigo de la materia
     * @param nroRegistro del estudiante que desea eliminar la inscripcion
     * @throws ManagementException
     */
    public static void deleteInscripcion(String codPlanEstudios,String codMateria, int nroRegistro) throws ManagementException {
        Materia codMateriaaux=null;
        try {
            codMateriaaux = PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios);
        } catch (ManagementException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        String codigo=codPlanEstudios+"-"+codMateriaaux.getCodigo()+"-"+String.valueOf(LocalDate.now().getYear())+"-"+"12";
        try {
            mesaExamenDAOImp.deleteInscripcion(codigo, nroRegistro);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    /**
     *
     * @param nroRegistro de un estudiante
     * @return las mesas de examen a las que esta inscripto el estudiante
     * @throws ManagementException
     */
    public static ArrayList<MesaExamen> obtenerMesasEstudiante(int nroRegistro) throws ManagementException {
        ArrayList<MesaExamen> mesas = new ArrayList();
        try {
            mesas = mesaExamenDAOImp.obtenerMesasInscriptas(nroRegistro);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
        return mesas;
    }

    /**
     *
     * @param codigo de una mesa de examen
     * @return los estudiantes que estan inscriptos a la mesa de examen
     * @throws ManagementException
     */
    public static ArrayList<Estudiante> obtenerInscriptosMesa(String codigo) throws ManagementException {
        ArrayList<Estudiante> estudiantes = new ArrayList();
        try {
            estudiantes = mesaExamenDAOImp.obtenerEstudiantesMesa(codigo);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
        return estudiantes;
    }
}
