/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.ExamenDAOImp;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.ExperienciaDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Experiencia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivanb
 */
public abstract class ExamenManager {

    private static ExamenDAOImp examenDAOImp;
    private static ExperienciaDAOImp experienciaDAOImp;

    public static void init(Conexion conexion) {
        examenDAOImp = new ExamenDAOImp(conexion);

        experienciaDAOImp = new ExperienciaDAOImp(conexion);
    }

    public static ArrayList<Examen> examenesEstudianteSinExp(int nroRegistro) throws ManagementException {
        try {
            return examenDAOImp.getExamenesEstudianteSinExp(nroRegistro);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    public static ArrayList<Examen> examenesEstudianteConExp(int nroRegistro) throws ManagementException {
        try {
            return examenDAOImp.getExamenesEstudianteConExp(nroRegistro);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    public static void cargar(List<Examen> examenes) throws ManagementException {
        for (Examen examen : examenes) {
            try {
                examenDAOImp.create(examen);
            } catch (SQLException e) {
                if (e.getMessage().contains("llave duplicada")) {
                    try {
                        examenDAOImp.update(examen.getCodigo(), examen);
                    } catch (SQLException e2) {
                        throw new ManagementException(e2.getMessage());
                    }
                } else {
                    throw new ManagementException(e.getMessage());
                }
            }
        }
    }

    public static void crearExperiencia(Experiencia experiencia) throws ManagementException {
        try {
            experienciaDAOImp.create(experiencia);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    //debería controlar el plan de estudios? creo que no pq c1 es la misma
    public static ArrayList<Experiencia> getExperiencias(String codMateria, String codPlan) throws ManagementException {
        try {
            return experienciaDAOImp.getExperienciasDAO(codMateria, codPlan);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }

    }

    public static ArrayList<Experiencia> getExperienciasAprobados(String codMateria, String codPlan) throws ManagementException {
        try {
            return experienciaDAOImp.getExperienciasAprobadosDAO(codMateria, codPlan);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    public static void ModificarExperiencia(String codigo, Experiencia experiencia) throws ManagementException {
        try {
            experienciaDAOImp.update(codigo, experiencia);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    public static void EliminarExperiencia(String codigo) throws ManagementException {
        try {
            experienciaDAOImp.delete(codigo);

        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    public static double promedioDificultad(String codMateria, String codPlan) throws ManagementException {
        ArrayList<Experiencia> experiencias = getExperiencias(codMateria, codPlan);

        int cant = 0;
        for (Experiencia exp : experiencias) {
            cant += exp.getDificultad();
        }

        return cant / experiencias.size();

    }

    public static int cantidadAprobadosUnaMateria(String codMateria, int dias, String codPlan) throws ManagementException {
        ArrayList<Experiencia> experiencias = getExperiencias(codMateria, codPlan);

        int cant = 0;
        for (Experiencia exp : experiencias) {
            if (exp.getDias() <= dias) {
                cant++;
            }
        }

        return cant;
    }
}
