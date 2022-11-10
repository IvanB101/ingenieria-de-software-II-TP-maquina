/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Util;

import com.mycompany.tp_maquina_is2.Logica.Util.Facade.ExcelFacade;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Managers.ExamenManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.HistoriaAcademicaManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.PlanEstudiosManager;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado.Condicion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 *
 * @author ivanb
 */
public class ArchivosManager {

    /**
     *
     * @param file el archivo excel con el plan de estudios a cargar
     * @throws
     * com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException
     */
    public static void cargarPlanEstudios(File file) throws ManagementException {
        try {
            ExcelFacade excel = ExcelFacade.leerDocumento(file);

            excel.avanzarHasta("Carrera", 0);

            String[] datos = excel.leerFila(2);

            // Carga datos de plan de estudios
            PlanEstudios plan = new PlanEstudios(datos[1], datos[0]);

            /* Se mantiene una LinkedList para mantener el orden y evitar conflictos con las correlativas 
            en la posterior carga en la base de datos */
            LinkedList<Materia> materias = new LinkedList<>();
            
            excel.avanzarHasta("Nombre", 0);

            // Carga de la materias
            while (excel.hasNext()) {
                datos = excel.leerFila(6);

                if (datos[0].equals("")) {
                    break;
                }

                // Arreglo error de parseo en algunos de los codigos de las materias
                String codMateria = datos[1];
                if (codMateria.contains(".")) {
                    codMateria = (String) codMateria.subSequence(0, codMateria.indexOf("."));
                }

                Materia materia = new Materia(
                        codMateria,
                        datos[0],
                        plan.getCodigo(),
                        new ArrayList(),
                        new ArrayList());

                // Carga de las correlativas
                if (!datos[5].equals("No tiene")) {
                    String[] codCorrelativas = datos[5].split("-");

                    for (String codCorrelativa : codCorrelativas) {
                        if (codCorrelativa.contains(".")) {
                            codCorrelativa = (String) codCorrelativa.subSequence(0, codCorrelativa.indexOf("."));
                        }

                        materia.getCorrelativas().add(codCorrelativa);
                    }
                }

                materias.add(materia);
            }

            PlanEstudiosManager.agregar(plan, materias);
        } catch (IOException e) {
            throw new ManagementException("Error en la lectura del archivo .xlsx");
        }
    }

    /**
     * Metodo para leer una historia academica de un documento ArchivosManager
     *
     * @param nroRegistro es el numero de registro del estudiante al cual
     * pertenece la historia
     * @param file es el archivo con la historia a leer
     * @param codPlanEstudios es el codigo del plan de estudios de la carrera
     * @throws
     * com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException
     */
    public static void cargarHistoriaAcademica(int nroRegistro, String codPlanEstudios, File file) throws ManagementException {
        try {
            ExcelFacade excel = ExcelFacade.leerDocumento(file);

            LinkedList<Examen> examenes = new LinkedList<>();
            HistoriaAcademica historia = new HistoriaAcademica(nroRegistro, codPlanEstudios);

            excel.avanzarHasta("Actividad", 0);

            String[] datos = excel.leerFila(5);

            // Comprobacion de que la materia este en el plan de estudios
            String codMateria = codigoMateria(datos[0]);

            PlanEstudiosManager.comprobarMateria(codPlanEstudios, codMateria);

            String[] temp = datos[1].split("/");

            Estado estado = new Estado(
                    codMateria,
                    nroRegistro + "-" + codPlanEstudios,
                    formatCondicion(datos[2], datos[4]),
                    LocalDate.of(Integer.parseInt(temp[2]), Integer.parseInt(temp[1]), Integer.parseInt(temp[0])));

            // Carga de los estados
            while (excel.hasNext()) {
                if (datos[2].equals("Examen")) {
                    temp = datos[1].split("/");

                    Examen examen = new Examen(
                            LocalDate.of(Integer.parseInt(temp[2]), Integer.parseInt(temp[1]), Integer.parseInt(temp[0])),
                            Float.parseFloat(datos[3]),
                            codigoMateria(datos[0]),
                            nroRegistro + "-" + codPlanEstudios);

                    examenes.add(examen);
                    historia.getCodExamenes().add(examen.getCodigo());
                }

                datos = excel.leerFila(5);

                // Comprobacion de que la materia este en el plan de estudios
                codMateria = codigoMateria(datos[0]);

                PlanEstudiosManager.comprobarMateria(codPlanEstudios, codMateria);

                temp = datos[1].split("/");

                LocalDate fecha = LocalDate.of(Integer.parseInt(temp[2]), Integer.parseInt(temp[1]), Integer.parseInt(temp[0]));
                // Si cambia la materia de la fila del excel se carga el estado y se pasa a cargar el siguiente
                if (!codigoMateria(datos[0]).equals(estado.getCodMateria())) {
                    historia.getEstados().put(estado.getCodMateria(), estado);

                    estado = new Estado(
                            codigoMateria(datos[0]),
                            nroRegistro + "-" + codPlanEstudios,
                            formatCondicion(datos[2], datos[4]),
                            fecha);
                } else {
                    /* Si la materia esta aprobada, el resultados de otras actividades de la misma materia 
                    no cambia el resultado final del estudiante en la materia */
                    if (!estado.getCondicion().equals(Condicion.aprobado)) {
                        Condicion condicion = formatCondicion(datos[2], datos[4]);

                        if (!combinarCondicion(estado.getCondicion(), condicion).equals(estado.getCondicion())) {
                            estado.setCondicion(condicion);
                            // En caso de actualizar la condicion, también se actualiza la fecha del estado
                            estado.setFecha(fecha);
                        }
                    }
                }
            }

            //Carga de los datos de la ultima fila del excel
            historia.getEstados().put(estado.getCodMateria(), estado);

            if (datos[2].equals("Examen")) {
                temp = datos[1].split("/");

                examenes.add(new Examen(
                        LocalDate.of(Integer.parseInt(temp[2]), Integer.parseInt(temp[1]), Integer.parseInt(temp[0])),
                        Float.parseFloat(datos[3]),
                        codigoMateria(datos[0]),
                        nroRegistro + "-" + codPlanEstudios));
            }
            // Carga de los datos sacados del excel
            HistoriaAcademicaManager.cargar(historia);
            ExamenManager.cargar(examenes);
        } catch (IOException e) {
            throw new ManagementException("Error en la lectura del archivo .xlsx");
        } catch (ManagementException e) {
            throw e;
        } catch (NoSuchElementException e) {
            throw new ManagementException("Historia academica inválida");
        } catch (Exception e) {
            System.out.println(e);
            throw new ManagementException("Ha ocurrido un error");
        }
    }

    /**
     *
     * @param contenidoCelda contenido de la celda
     * @return codigo de la materia, -1 si el contenido de la celda es invalido
     */
    private static String codigoMateria(String contenidoCelda) throws ManagementException {
        int start = contenidoCelda.indexOf("(") + 1;
        int finish = contenidoCelda.indexOf(")");

        if (start == -1 || finish == -1) {
            throw new ManagementException("Formato materia invalido");
        }

        return (String) contenidoCelda.subSequence(start, finish);
    }

    /**
     * @param tipo valor de la columna tipo en el excel de la historia academica
     * @param resultado valor de la conluman resultado en el excel de la
     * historia academica
     * @return condicion del alumno a partir de los parametros dados
     */
    private static Condicion formatCondicion(String tipo, String resultado) throws ManagementException {
        Condicion ret = Condicion.libre;

        switch (tipo) {
            case "Examen":
                if (resultado.equals("Aprobado")) {
                    ret = Condicion.aprobado;
                }
                break;
            case "Regularidad":
                if (resultado.equals("Aprobado")) {
                    ret = Condicion.regular;
                }
                break;
            case "Promocion":
                if (resultado.equals("Promocionado")) {
                    ret = Condicion.aprobado;
                }
                break;
            case "En curso":
                ret = Condicion.cursando;
                break;
            default:
                throw new ManagementException("Formato invalido");
        }

        return ret;
    }

    /**
     * Metodo para generar la condicion del alumno a partir de dos condiciones
     * de la misma materia
     *
     * @param cond1 primera condicion a juntar
     * @param cond2 segunda condicion a juntar
     * @return condicion resultante de ambas condiciones de entrada
     */
    private static Condicion combinarCondicion(Condicion cond1, Condicion cond2) {
        Condicion ret = Condicion.libre;

        if (cond1.equals(Condicion.aprobado) || cond2.equals(Condicion.aprobado)) {
            ret = Condicion.aprobado;
        } else if (cond1.equals(Condicion.regular) || cond2.equals(Condicion.regular)) {
            ret = Condicion.regular;
        } else if (cond1.equals(Condicion.cursando) || cond2.equals(Condicion.cursando)) {
            ret = Condicion.cursando;
        }

        return ret;
    }
}
