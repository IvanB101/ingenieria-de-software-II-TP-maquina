/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.MateriaDAOImp;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.PlanEstudiosDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ivanb
 */
public abstract class PlanEstudiosManager {

    private static PlanEstudiosDAOImp planEstudiosDAOImp;
    private static MateriaDAOImp materiaDAOImp;

    private static PlanEstudios planEstudios;

    public static void init(Conexion conexion) {
        planEstudiosDAOImp = new PlanEstudiosDAOImp(conexion);

        materiaDAOImp = new MateriaDAOImp(conexion);
    }

    /** Comprueba si una materia esta un plan de estudios, lanza una excepcion en el caso
     * de que la materia con el codigo dado no este
     * @param codPlanEstudios codigo del plan de estudios
     * @param codMateria codigo de la materia
     * @throws
     * com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException
     */
    public static void comprobarMateria(String codPlanEstudios, String codMateria) throws ManagementException {
        buscar(codPlanEstudios);
        
        if (planEstudios.getMaterias().get(codMateria) == null){
            throw new ManagementException("Materia con codigo " + codMateria + " no esta en el plan de estudios"
                            + " con codigo " + codPlanEstudios + ", o el plan de estudios no se encuentra cargado");
        }
    }

    /**
     * Busca un plan de estudios
     *
     * @param codigo del plan de estudios que se busca
     * @return el plan de estudios con el codigo dado, o null si no existe
     * @throws ManagementException
     */
    public static PlanEstudios buscar(String codigo) throws ManagementException {
        try {
            if (planEstudios == null || !planEstudios.getCodigo().equals(codigo)) {
                planEstudios = planEstudiosDAOImp.read(codigo);

                planEstudios.setMaterias(materiaDAOImp.materiasFromPlanEstudios(codigo));
            }

            return planEstudios;
        } catch (SQLException e) {
            if (e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next.")) {
                throw new ManagementException("No hay un plan de estudios con el codigo " + codigo + " cargado");
            }
        }

        return null;
    }

    /**
     * Agrega un plan de estudios a la base de datos
     *
     * @param nuevo plan de estudios a agregar
     * @param materias arreglo con las materias del plan de estudios
     * @throws ManagementException
     */
    public static void agregar(PlanEstudios nuevo, LinkedList<Materia> materias) throws ManagementException {
        try {
            planEstudiosDAOImp.create(nuevo);

            for (Materia materia : materias) {
                try {
                    materiaDAOImp.create(materia);
                } catch (SQLException e) {
                    throw new ManagementException("Error en carga de materia con codigo: " + materia.getCodigo()
                            + "\n" + e.getMessage());
                }
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("llave duplicada")) {
                throw new ManagementException("Ya existe un plan de estudios con el codigo: " + nuevo.getCodigo());
            } else {
                throw new ManagementException(e.getMessage());
            }
        }
    }

    /**
     * Modifica un plan de estudios de la base de datos
     *
     * @param codigo del plan de estudios original
     * @param modificado plan de estudios con las modificaciones realizadas
     */
    public static void modificar(String codigo, PlanEstudios modificado) throws ManagementException {
        try {
            planEstudiosDAOImp.update(codigo, modificado);
            
            planEstudios = null;
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }   
    }

    /**
     * Elimina el plan de estudios con el codigo dado
     *
     * @param codigo del plan de estudios
     * @throws ManagementException
     */
    public static void eliminar(String codigo) throws ManagementException {
        try {
            planEstudiosDAOImp.read(codigo);

            planEstudiosDAOImp.delete(codigo);
            
            planEstudios = null;
        } catch (SQLException e) {
            if (e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next.")) {
                throw new ManagementException("No hay plan de estudios con codigo: " + codigo);
            } else {
                System.out.println(e);
                throw new ManagementException("Ha ocurrido un error");
            }
        }
    }

    /**
     *
     * @param codMateria codigo de la materia
     * @param codPlanEstudios codigo del plan de estudios al que pertence la
     * materia
     * @return un ArrayList con los codigos de las materias correlativas a la
     * materia consultada
     * @throws ManagementException
     */
    public static ArrayList<String> getCodCorrelativas(String codMateria, String codPlanEstudios) throws ManagementException {
        buscar(codPlanEstudios);

        return planEstudios.getMaterias().get(codMateria).getCorrelativas();
    }

    /**
     *
     * @param codMateria codigo de la materia que se consulta
     * @param codPlanEstudios codigo del plan de estudios al que pertenece la
     * materia
     * @return la materia con el codigo y codigo de plan de estudios dados, o
     * null si no existe
     * @throws ManagementException
     */
    public static Materia buscarMateria(String codMateria, String codPlanEstudios) throws ManagementException {
        buscar(codPlanEstudios);

        return planEstudios.getMaterias().get(codMateria);
    }
    
    /**
     * Metodo para modificar la informacion referente a una materia
     * @param codMateria codigo de la materia
     * @param codPlanEstudios codigo del plan de estudios al que pertence la materia
     * @param modificada materia con la informacion modificada
     * @throws com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException
     */
    public static void modificarMateria(String codMateria, String codPlanEstudios, Materia modificada) throws ManagementException {
        try {
            // Control de existencia de la materia
            Materia materia = buscarMateria(codMateria, codPlanEstudios);
            
            if(!codMateria.equals(modificada.getCodigo()) && buscarMateria(modificada.getCodigo(), codPlanEstudios) != null) {
                throw new ManagementException("Ya existe otra materia con codigo: " + modificada.getCodigo());
            }
            
            materiaDAOImp.update(codMateria, codPlanEstudios, modificada);
            
            for (String codCorrelativa : modificada.getCorrelativas()) {
                if(!materia.getCorrelativas().contains(codCorrelativa)) {
                    materiaDAOImp.agregarCorrelativa(modificada.getCodigo(), codCorrelativa, codPlanEstudios);
                }
            }
            
            for(String codCorrelativa : materia.getCorrelativas()) {
                if(!modificada.getCorrelativas().contains(codCorrelativa)) {
                    materiaDAOImp.eliminarCorrelativa(modificada.getCodigo(), codCorrelativa, codPlanEstudios);
                }
            }
            
            planEstudios = null;
        } catch (SQLException e) {
            System.out.println(e);
            throw new ManagementException("Ha ocurrido un error");
        }
    }
    
    /**
     * Metodo para agregar una materia individual
     * @param materia materia a agregar
     * @throws ManagementException 
     */
    public static void agregarMateria(Materia materia) throws ManagementException {
        if(buscarMateria(materia.getCodigo(), materia.getCodPlanDeEstudios()) != null) {
            throw new ManagementException("Ya existe una materia con el codigo: " + materia.getCodigo());
        } else {
            try {
                materiaDAOImp.create(materia);
                
                planEstudios = null;
            } catch (SQLException e) {
                System.out.println(e);
                throw new ManagementException("Ha ocurrido un error en la carga de la materia");
            }
        }
    }
    
    /**
     * Metodo para eliminar una materia de un plan de estudios
     * @param codMateria codigo de la materia a eliminar
     * @param codPlanEstudios codigo del plan de estudios de la materia
     * @throws ManagementException 
     */
    public static void eliminarMateria(String codMateria, String codPlanEstudios) throws ManagementException {
        try {
            if(buscarMateria(codMateria, codPlanEstudios) == null) {
                throw new ManagementException("No hay materia con codigo: " + codMateria);
            }
            
            materiaDAOImp.delete(codMateria, codPlanEstudios);
            
            planEstudios = null;
        } catch (SQLException e) {
            System.out.println(e);
            throw new ManagementException("No se ha podido eliminar la materia");
        }
    }
    
    public static LinkedList<Materia> getMaterias(String codigo, List<String> codMaterias) throws ManagementException {
        buscar(codigo);
        
        LinkedList<Materia> materias = new LinkedList<>();
        
        Materia materia;
        
        for (String codMateria : codMaterias) {
            if((materia = planEstudios.getMaterias().get(codMateria)) != null) {
                materias.add(materia);
            }
        }
        
        return materias;
    }

    /**
     *
     * @param codMateria codigo de la materia por la que se consulta
     * @param codPlanEstudios codigo del plan de estudios al que pertenece la
     * materia
     * @return cantidad de materias que tiene a la materia como correlativa
     * @throws ManagementException
     */
    public static int getCantidadDependientes(String codMateria, String codPlanEstudios) throws ManagementException {
        buscar(codPlanEstudios);

        int cantidad = 0;
        for (Materia materia : planEstudios.getMaterias().values()) {
            if (materia.getCorrelativas().contains(codMateria)) {
                cantidad++;
            }
        }

        return cantidad;
    }

    /**
     *
     * @return Todos los planes de estudios cargados, solamente con su codigo y
     * propuesta
     * @throws ManagementException
     */
    public static LinkedList<PlanEstudios> leer() throws ManagementException {
        try {
            return planEstudiosDAOImp.read();
        } catch (SQLException e) {
            if (e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next.")) {
                throw new ManagementException("No hay ningun plan de estudios cargado");
            }
        }

        return null;
    }

    /**
     * 
     * @param codigo del plan de estudios
     * @return un HashMap de la forma <codigo materia, materia> con las materias del plan de estudios
     * @throws ManagementException 
     */
    public static HashMap<String, Materia> getMateriasDePlan(String codigo) throws ManagementException {
        buscar(codigo);

        return planEstudios.getMaterias();
    }
}
