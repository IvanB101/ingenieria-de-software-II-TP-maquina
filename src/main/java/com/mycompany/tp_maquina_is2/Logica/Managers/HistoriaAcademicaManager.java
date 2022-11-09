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

/**
 *
 * @author ivanb
 */
public abstract class HistoriaAcademicaManager {

    private static HistoriaAcademica historiasAcademica;
    private static HistoriaAcademicaDAOImp historiaAcademicaDAOImp;
    private static EstadoDAOImp estadoDAOImp;

    public static void init(Conexion conexion) {
        historiaAcademicaDAOImp = new HistoriaAcademicaDAOImp(conexion);

        estadoDAOImp = new EstadoDAOImp(conexion);
    }

    public static void modificar(HistoriaAcademica modificada) throws ManagementException {
        try {
            buscar(modificada.getNroRegEstudiante(), modificada.getCodPlanDeEstudios());

            historiaAcademicaDAOImp.update(modificada.getNroRegEstudiante(), modificada.getCodPlanDeEstudios(), historiasAcademica);
        } catch (ManagementException e) {
            throw e;
        } catch (SQLException e) {
            // TODO
        }
    }

    public static void cargar(HistoriaAcademica nueva) throws ManagementException {
        try {
            historiaAcademicaDAOImp.create(nueva);

            for (Estado estado : nueva.getEstados().values()) {
                try {
                    estadoDAOImp.create(estado);
                } catch (SQLException e) {
                    // TODO
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
                                    throw new ManagementException(e3.getMessage());
                                }
                            }
                        }
                    }
                } catch (SQLException ex) {
                    throw new ManagementException(ex.getMessage());
                }
            } else {
                throw new ManagementException(e.getMessage());
            }
        }
    }

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

    public static HashMap<Materia, Object> listaExamenes(int nroRegistro, String codPlanEstudios, String criterio, int dias) throws ManagementException {
        HistoriaAcademica historia = buscar(nroRegistro, codPlanEstudios);
        HashMap<Materia, Object> ranking = new HashMap<>();
        ArrayList<String> codCorrelativas;
        for (Estado estado : historia.getEstados().values()) { 
            String codMateria = estado.getCodMateria();
            if (estado.getCondicion().equals(Condicion.regular)) { 
                //correlativas de una materia regular
                codCorrelativas = PlanEstudiosManager.getCodCorrelativas(codMateria, codPlanEstudios);
                if (cumpleRequisitos(codCorrelativas, historia)) {
                    if (criterio.equals("Correlativas")) {
                        //busco en cuantas materias es correlativa
                        ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios),
                                PlanEstudiosManager.getCantidadDependientes(codMateria, codPlanEstudios));
                    }//fin correlativas
                    if (criterio.equals("Dificultad")) {
                        //saco la dificultad promedio de una materia, entrada: todas las exp de una materia
                        if ((ExamenManager.getExperiencias(codMateria).size()) == 0) { //si la materia no tiene experiencias
                            ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios), 0.0); //0.0 PQ ES DOUBLE!!!!!!
                        } else {
                            ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios), ExamenManager.promedioDificultad(codMateria));
                        }
                    }//fin dificultad  
                    if (criterio.equals("Tiempo")) {
                        if ((ExamenManager.getExperienciasAprobados(codMateria).size()) == 0) {//si no hay experiencias de aprobados
                            ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios), 0);
                        } else {
                            ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios), ExamenManager.cantidadAprobadosUnaMateria(codMateria, dias));
                        }
                    }//fin tiempo
                    //gracias a la linea 113 solo veo los estados de las regulares por lo que veo la fecha de la regularidad.
                    if (criterio.equals("Vencimiento de regularidad")) {
                        ranking.put(PlanEstudiosManager.buscarMateria(codMateria, codPlanEstudios), semanasVencimiento(estado.getFecha()));
                    }//fin vencimiento
                }
            }
        }//finfor

        return ranking;
    }

    public static boolean cumpleRequisitos(ArrayList<String> codCorrelativas, HistoriaAcademica historia) {
        for (String correlativa : codCorrelativas) {
            if (!historia.getEstados().get(correlativa).getCondicion().equals(Condicion.aprobado)) {
                return false;
            }
        }

        return true;
    }

    public static long semanasVencimiento(LocalDate fecha) {
        LocalDate limite = LocalDate.of(fecha.plusMonths(32).getYear(),fecha.plusMonths(32).getMonthValue(),fecha.plusMonths(32).getDayOfMonth());
        fecha.plusMonths(32).getYear();
        return ChronoUnit.WEEKS.between(java.time.LocalDate.now(),limite);
        /*Datos: 
        LocalDate no se puede modificar
        el plus solo sirve con año o con mes pero no ambos si no el mes da toda la vuelta
        sin sumar años
        Como no se modifica el plus lo modifica para mostrar pero no de verdad por eso esta instanciado asi
        
        */
    }

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
