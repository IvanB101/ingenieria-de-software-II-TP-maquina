/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Util;

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
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;

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
            FileInputStream fileInputStream = new FileInputStream(file);
            // Estructura central dentro del libro de ArchivosManager
            HSSFWorkbook book = new HSSFWorkbook(fileInputStream);
            // Obtencion de la primera hoja del libro
            HSSFSheet sheet0 = book.getSheetAt(0);
            // Iterador sobre las filas de la hoja
            Iterator rowIterator = sheet0.rowIterator();

            if (!avanzarIteradorFilasHasta(rowIterator, "Carrera")) {
                throw new ManagementException("Plan de Estudios invalido");
            }

            HSSFRow row = (HSSFRow) rowIterator.next();
            // Iterador sobre las celdas de las filas
            Iterator cellIterator = row.cellIterator();

            // Carga de la propuesta del plan de estudios
            HSSFCell cell = (HSSFCell) cellIterator.next();
            String propuesta = cell.toString();

            // Carga del codigo del plan de estudios
            cell = (HSSFCell) cellIterator.next();
            String codigo = cell.toString();

            /* Se mantiene una LinkedList para mantener el orden y evitar conflictos con las correlativas 
            en la posterior carga en la base de datos */
            LinkedList<Materia> materiasLista = new LinkedList<>();

            if (!avanzarIteradorFilasHasta(rowIterator, "Nombre")) {
                throw new ManagementException("Plan de Estudios invalido");
            }

            String[] datos = new String[7];
            // Carga de la materias
            while (rowIterator.hasNext()) {
                row = (HSSFRow) rowIterator.next();
                cellIterator = row.cellIterator();

                ArrayList<String> correlativas = new ArrayList<>();

                int i = 0;
                while (cellIterator.hasNext()) {
                    cell = (HSSFCell) cellIterator.next();
                    datos[i] = cell.toString();

                    i++;
                }

                if (!datos[5].equals("No tiene") && !datos[5].equals("")) {
                    String[] codCorrelativas = datos[5].split("-");

                    for (String codCorrelativa : codCorrelativas) {
                        if (codCorrelativa.contains(".")) {
                            codCorrelativa = (String) codCorrelativa.subSequence(0, codCorrelativa.indexOf("."));
                        }
                        
                        correlativas.add(codCorrelativa);
                    }
                }

                String codMateria = datos[1];
                if (codMateria.contains(".")) {
                    codMateria = (String) codMateria.subSequence(0, codMateria.indexOf("."));
                }

                materiasLista.add(new Materia(
                        codMateria,
                        datos[0],
                        codigo,
                        correlativas,
                        new ArrayList<>()));
            }

            PlanEstudiosManager.agregar(new PlanEstudios(codigo, propuesta), materiasLista);
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
     * @throws FormatFlagsConversionMismatchException
     */
    public static void cargarHistoriaAcademica(int nroRegistro, String codPlanEstudios, File file) throws ManagementException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            // Estructura central dentro del libro de ArchivosManager
            HSSFWorkbook book = new HSSFWorkbook(fileInputStream);
            // Obtencion de la primera hoja del libro
            HSSFSheet sheet0 = book.getSheetAt(0);
            // Iterador sobre las filas de la hoja
            Iterator rowIterator = sheet0.rowIterator();

            HashMap<String, Estado> estados = new HashMap<>();
            ArrayList<String> codMateriasExamenes = new ArrayList<>();
            LinkedList<Examen> examenes = new LinkedList<>();
            ArrayList<String> codExamenes = new ArrayList<>();

            if (!avanzarIteradorFilasHasta(rowIterator, "Actividad")) {
                throw new ManagementException("Historia Academica invalida");
            }

            // Carga de la primera fila de la historia
            HSSFRow row = (HSSFRow) rowIterator.next();
            Iterator cellIterator = row.cellIterator();
            HSSFCell cell;

            String[] datos = new String[7];
            int i = 0;

            while (cellIterator.hasNext()) {
                cell = (HSSFCell) cellIterator.next();
                datos[i] = cell.toString();
                i++;
            }

            // Comprobacion de que la materia este en el plan de estudios
            String codMateria = codigoMateria(datos[0]);

            if (!PlanEstudiosManager.comprobarMateria(codPlanEstudios, codMateria)) {
                throw new ManagementException("Materia con codigo " + codMateria + " no esta en el plan de estudios"
                        + " con codigo " + codPlanEstudios + ", o el plan de estudios no se encuentra cargado");
            }

            Estado estado = new Estado(
                    codMateria,
                    nroRegistro + "-" + codPlanEstudios,
                    formatCondicion(datos[2], datos[4]));

            // Carga de los estados
            while (rowIterator.hasNext()) {
                
                if (datos[2].equals("Examen")) {
                    String[] temp = datos[1].split("/");

                    Examen examen = new Examen(
                            LocalDate.of(Integer.parseInt(temp[2]), Integer.parseInt(temp[1]), Integer.parseInt(temp[0])),
                            Float.parseFloat(datos[3]),
                            codigoMateria(datos[0]),
                            nroRegistro + "-" + codPlanEstudios);

                    examenes.add(examen);
                    codExamenes.add(examen.getCodigo());
                    codMateriasExamenes.add(examen.getCodMateria());
                }

                row = (HSSFRow) rowIterator.next();
                cellIterator = row.cellIterator();

                // Carga de los datos de la fila del excel
                i = 0;
                while (cellIterator.hasNext()) {
                    cell = (HSSFCell) cellIterator.next();
                    datos[i] = cell.toString();
                    i++;
                }

                // Comprobacion de que la materia este en el plan de estudios
                codMateria = codigoMateria(datos[0]);

                if (!PlanEstudiosManager.comprobarMateria(codPlanEstudios, codMateria)) {
                    throw new ManagementException("Materia con codigo " + codMateria + " no esta en el plan de estudios"
                            + " con codigo " + codPlanEstudios + ", o el plan de estudios no se encuentra cargado");
                }

                // Si cambia la materia de la fila del excel se carga el estado y se pasa a cargar el siguiente
                if (!codigoMateria(datos[0]).equals(estado.getCodMateria())) {
                    estados.put(estado.getCodMateria(), estado);

                    estado = new Estado(
                            codigoMateria(datos[0]),
                            nroRegistro + "-" + codPlanEstudios,
                            formatCondicion(datos[2], datos[4]));
                } else {
                    /* Si la materia esta aprobada, el resultados de otras actividades de la misma materia 
                    no cambia el resultado final del estudiante en la materia */
                    if (!estado.getCondicion().equals(Condicion.aprobado)) {
                        estado.setCondicion(compararCondicion(estado.getCondicion(), formatCondicion(datos[2], datos[4])));
                    }
                }
            }

            //Carga de los datos de la ultima fila del excel
            estados.put(estado.getCodMateria(), estado);

            if (datos[2].equals("Examen")) {
                String[] temp = datos[1].split("/");
                
                examenes.add(new Examen(
                        LocalDate.of(Integer.parseInt(temp[2]), Integer.parseInt(temp[1]), Integer.parseInt(temp[0])),
                        Float.parseFloat(datos[3]),
                        codigoMateria(datos[0]),
                        nroRegistro + "-" + codPlanEstudios));
            }
            // Carga de los datos sacados del excel
            HistoriaAcademicaManager.cargar(new HistoriaAcademica(nroRegistro, codPlanEstudios, estados,
                    codExamenes, codMateriasExamenes));
            ExamenManager.cargar(examenes);
        } catch (IOException e) {
            throw new ManagementException("Error en la lectura del archivo .xlsx");
        } catch (ManagementException e) {
            throw e;
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
     * Avanza el iterador de fila del documento excel hasta la fila posterior a
     * la ocurrencia
     *
     * @param rowIterator iterador de las filas del documento a avanzar
     * @param ocurrencia contenido de la primera celda de la fila anterior a la
     * que se desea avanzar
     * @return true si la ocurrencia fue encontrada, false en otro caso
     */
    private static boolean avanzarIteradorFilasHasta(Iterator rowIterator, String ocurrencia) {
        while (rowIterator.hasNext()) {
            HSSFRow row = (HSSFRow) rowIterator.next();

            Iterator cellIterator = row.cellIterator();
            // Iterador sobre las celdas de las filas
            HSSFCell cell = (HSSFCell) cellIterator.next();

            if (cell.toString().equals(ocurrencia)) {
                return true;
            }
        }

        return false;
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

    private static Condicion compararCondicion(Condicion cond1, Condicion cond2) {
        Condicion ret = Condicion.libre;

        if (cond1.equals(Condicion.aprobado) || cond2.equals(Condicion.aprobado)) {
            ret = Condicion.aprobado;
        } else if (cond1.equals(Condicion.regular) || cond2.equals(Condicion.regular)) {
            ret = Condicion.regular;
        } else if (cond1.equals(Condicion.cursando) || cond2.equals(Condicion.cursando)) {
            ret = Condicion.cursando;
        }

        // System.out.println("Condicion 1: " + cond1 + " Condicion 2: " + cond2 + " Condicion final: " + ret);
        return ret;
    }
}
