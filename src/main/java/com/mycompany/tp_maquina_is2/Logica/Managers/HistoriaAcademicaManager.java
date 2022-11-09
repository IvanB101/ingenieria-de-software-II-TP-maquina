/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.EstadoDAOImp;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.HistoriaAcademicaDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado.Condicion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author ivanb
 */
public abstract class HistoriaAcademicaManager {

    private static HistoriaAcademica historiasAcademica;
    private static HistoriaAcademicaDAOImp historiaAcademicaDAOImp;
    private static EstadoDAOImp estadoDAOImp;

    /**
     * Metodo que instancia los DAO de estado e historia academica
     *
     * @param conexion onjeto del cual se obtiene la conexion a la base de datos
     */
    public static void init(Conexion conexion) {
        historiaAcademicaDAOImp = new HistoriaAcademicaDAOImp(conexion);

        estadoDAOImp = new EstadoDAOImp(conexion);
    }

    /**
     * Metodo para actualizar los estados de una historia academica
     *
     * @param modificada
     * @throws ManagementException
     */
    public static void actualizar(HistoriaAcademica modificada) throws ManagementException {
        try {
            buscar(modificada.getNroRegEstudiante(), modificada.getCodPlanDeEstudios());

            historiaAcademicaDAOImp.update(modificada.getNroRegEstudiante(), modificada.getCodPlanDeEstudios(), historiasAcademica);
        } catch (ManagementException e) {
            throw e;
        } catch (SQLException e) {
            throw new ManagementException("Error en la actualizacion de la historia academica");
        }
    }

    /**
     * El metodo carga la historia academica en la base de datos o la actualiza
     * en el caso de que ya exista
     *
     * @param nueva historia academica a cargar
     * @throws ManagementException
     */
    public static void cargar(HistoriaAcademica nueva) throws ManagementException {
        try {
            historiaAcademicaDAOImp.create(nueva);

            for (Estado estado : nueva.getEstados().values()) {
                try {
                    estadoDAOImp.create(estado);
                } catch (SQLException e) {
                    throw new ManagementException("Error en la carga de la historia academica");
                }
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("llave duplicada")) {
                try {
                    historiaAcademicaDAOImp.update(nueva.getNroRegEstudiante(), nueva.getCodPlanDeEstudios(), historiasAcademica);

                    for (Estado estado : nueva.getEstados().values()) {
                        try {
                            estadoDAOImp.create(estado);
                        } catch (SQLException e2) {
                            if (e2.getMessage().contains("llave duplicada")) {
                                try {
                                    estadoDAOImp.update(estado.getCodigo(), estado);
                                } catch (SQLException e3) {
                                    throw new ManagementException("Error en la actualizacion del estado con codigo: " + estado.getCodMateria());
                                }
                            }
                        }
                    }
                } catch (SQLException ex) {
                    throw new ManagementException("Error en la actualizacion de la historia academica");
                }
            } else {
                throw new ManagementException("Ha ocurrido un error en la carga de la historia academica");
            }
        }
    }

    /**
     * Metodo para elimiar una historia academica
     *
     * @param nroRegistro nro de registro del estudiante del cual es la historia
     * @param codPlanEstudios codigo del plan la carrera que esta cursando el
     * estudiante
     * @throws ManagementException
     */
    public static void eliminar(int nroRegistro, String codPlanEstudios) throws ManagementException {
        try {
            historiaAcademicaDAOImp.read(nroRegistro, codPlanEstudios);

            historiaAcademicaDAOImp.delete(nroRegistro, codPlanEstudios);
        } catch (SQLException e) {
            if (e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next.")) {
                throw new ManagementException("No hay una historia academica con codigo: "
                        + nroRegistro + "-" + codPlanEstudios);
            } else {
                System.out.println(e);
                throw new ManagementException("Ha ocurrido un error");
            }
        }
    }

    /**
     *
     * @param nroRegistro del estudiante
     * @param codPlanEstudios codigo del plan de estudios de la carrera
     * @param criterio de prioridad para ordenar a las materias
     * @param dias con los que se cuenta para preparar la materia
     * @return lista de arreglos de objetos para llenar la tabla de materias
     * ordenadas segun el criterio
     * @throws ManagementException
     */
    public static ArrayList<Object[]> listaExamenes(int nroRegistro, String codPlanEstudios, String criterio, int dias) throws ManagementException {
        HistoriaAcademica historia = buscar(nroRegistro, codPlanEstudios);

        HashMap<Materia, Object> ranking = new HashMap<>();

        for (Estado estado : historia.getEstados().values()) {

            String codMateria = estado.getCodMateria();

            if (estado.getCondicion().equals(Condicion.regular)) {
                //correlativas de una materia regular
                if (cumpleRequisitos(PlanEstudiosManager.getCodCorrelativas(codMateria, codPlanEstudios), historia)) {
                    switch (criterio) {
                        case "Correlativas":
                            //busco en cuantas materias es correlativa
                            ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios),
                                    PlanEstudiosManager.getCantidadDependientes(codMateria, codPlanEstudios));
                            break;
                        case "Dificultad":
                            //saco la dificultad promedio de una materia, entrada: todas las exp de una materia
                            if ((ExamenManager.getExperiencias(codMateria, codPlanEstudios).size()) == 0) { //si la materia no tiene experiencias
                                ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios), 0.0);
                            } else {
                                ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios), ExamenManager.promedioDificultad(codMateria, codPlanEstudios));
                            }
                            break;
                        case "Tiempo":
                            if ((ExamenManager.getExperienciasAprobados(codMateria, codPlanEstudios).size()) == 0) {//si no hay experiencias de aprobados
                                ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios), 0);
                            } else {
                                ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios), ExamenManager.cantidadAprobadosUnaMateria(codMateria, dias, codPlanEstudios));
                            }
                            break;
                        case "Vencimiento":
                            ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios), semanasVencimiento(estado.getFecha()));
                    }
                }
            }
        }//finfor

        // Pasaje del hashmap a una lista para poder ordenarla
        Set<Materia> keys2 = ranking.keySet();
        ArrayList<Materia> keys = new ArrayList<>();

        for (Materia materia : keys2) {
            keys.add(materia);
        }

        // Se ordena la lista en funcion del criterio
        switch (criterio) {
            case "Correlativas":
                keys.sort((c1, c2) -> {
                    Integer valuec1 = (Integer) ranking.get(c2);
                    return valuec1.compareTo((Integer) ranking.get(c1));
                });
                break;
            case "Dificultad":
                keys.sort((c1, c2) -> {
                    Double valuec1D = (Double) ranking.get(c2);
                    return valuec1D.compareTo((Double) ranking.get(c1));
                });
                break;
            case "Tiempo":
                keys.sort((c1, c2) -> {
                    Integer valuec1 = (Integer) ranking.get(c2);
                    return valuec1.compareTo((Integer) ranking.get(c1));
                });
                break;
            case "Vencimiento":
                keys.sort((c1, c2) -> {
                    Long valuec1 = (Long) ranking.get(c1);
                    return valuec1.compareTo((Long) ranking.get(c2));
                });
        }

        ArrayList<Object[]> filasTabla = new ArrayList<>();

        // Pasaje al formato necesario para llenar la tabla
        for (Materia key : keys) {
            filasTabla.add(new Object[]{key.getCodigo(), key.getNombre(), ranking.get(key)});
        }

        return filasTabla;
    }

    /**
     *
     * @param codCorrelativas codigos de materias correlativas a una materia
     * @param historia academica de un estudiante
     * @return booleano que indica si el estudiante esta en condiciones de
     * rendir la materia con las correlativas pasadas como parametro
     */
    public static boolean cumpleRequisitos(ArrayList<String> codCorrelativas, HistoriaAcademica historia) {
        for (String correlativa : codCorrelativas) {
            if (!historia.getEstados().get(correlativa).getCondicion().equals(Condicion.aprobado)) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param fecha de regularizacion de una materia
     * @return cantidad de semanas restantes para el vencimiento de una
     * regularidad
     */
    public static long semanasVencimiento(LocalDate fecha) {
        LocalDate limite = LocalDate.of(fecha.plusMonths(32).getYear(), fecha.plusMonths(32).getMonthValue(), fecha.plusMonths(32).getDayOfMonth());
        fecha.plusMonths(32).getYear();
        return ChronoUnit.WEEKS.between(java.time.LocalDate.now(), limite);
        /*Datos: 
        LocalDate no se puede actualizar
        el plus solo sirve con año o con mes pero no ambos si no el mes da toda la vuelta
        sin sumar años
        Como no se modifica el plus lo modifica para mostrar pero no de verdad por eso esta instanciado asi
        
         */
    }

    /**
     *
     * @param nroRegistro del estudiante
     * @param codPlanEstudios codigo del plan de estudios
     * @return historia academica con el nro de registro y plan de estudios
     * correspondientes
     * @throws ManagementException
     */
    public static HistoriaAcademica buscar(int nroRegistro, String codPlanEstudios) throws ManagementException {
        try {
            if (historiasAcademica == null || historiasAcademica.getNroRegEstudiante() != nroRegistro
                    || !historiasAcademica.getCodPlanDeEstudios().equals(codPlanEstudios)) {
                historiasAcademica = historiaAcademicaDAOImp.read(nroRegistro, codPlanEstudios);

                historiasAcademica.setEstados(estadoDAOImp.getEstadosHistoria(nroRegistro, codPlanEstudios));
            }

            return historiasAcademica;
        } catch (SQLException e) {
            if (e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next.")) {
                throw new ManagementException("No hay historia academica con nroRegistro " + nroRegistro + " y "
                        + "codigo de plan de estudios " + codPlanEstudios + " cargado");
            }
        }

        return null;
    }
}
