/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Util;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado.Condicion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 *
 * @author ivanb
 */
public class Excel {

    /**
     * Metodo para leer una historia academica de un documento Excel
     *
     * @param nroRegistro es el numero de registro del estudiante al cual
     * pertenece la historia
     * @param file es el archivo con la historia a leer
     * @param codPlanEstudios es el codigo del plan de estudios de la carrera
     * @return un objeto tipo HistoriaAcadamica con los datos del documento
     * Excel
     * @throws java.lang.Exception
     */
    public static HistoriaAcademica leerHistoriaAcademica(int nroRegistro, String codPlanEstudios, File file) throws Exception {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            // Estructura central dentro del libro de Excel
            HSSFWorkbook book = new HSSFWorkbook(fileInputStream);
            // Obtencion de la primera hoja del libro
            HSSFSheet sheet0 = book.getSheetAt(0);
            // Iterador sobre las filas de la hoja
            Iterator rowIterator = sheet0.rowIterator();

            HSSFRow row = (HSSFRow) rowIterator.next();
            // Iterador sobre las celdas de las filas
            Iterator cellIterator = row.cellIterator();
            HSSFCell cell = (HSSFCell) cellIterator.next();

            ArrayList<Estado> estados = new ArrayList<>();
            ArrayList<Integer> codMaterias = new ArrayList<>();

            // Obtencion de la propuesta de la primera celda del excel
            String eliminar = "Propuesta: ";
            String propuesta = cell.toString();
            propuesta = (String) propuesta.subSequence(eliminar.length(), propuesta.length());

            // Iteracion para salteas las filas vacias y el encabezado de la tabla
            while (rowIterator.hasNext()) {
                row = (HSSFRow) rowIterator.next();

                cellIterator = row.cellIterator();
                // Iterador sobre las celdas de las filas
                cell = (HSSFCell) cellIterator.next();

                if (cell.toString().equals("Actividad")) {
                    break;
                }
            }

            // Carga de la primera fila de la historia
            row = (HSSFRow) rowIterator.next();
            cellIterator = row.cellIterator();

            String[] datos = new String[7];
            int i = 0;

            while (cellIterator.hasNext()) {
                cell = (HSSFCell) cellIterator.next();
                datos[i] = cell.toString();
                i++;
            }
            Estado estado = new Estado(
                    codigoMateria(datos[0]),
                    nroRegistro,
                    formatCondicion(datos[2], datos[4])
            );

            // Carga de los estados
            while (rowIterator.hasNext()) {
                row = (HSSFRow) rowIterator.next();
                cellIterator = row.cellIterator();

                // Carga de los datos de la fila del excel
                i = 0;
                while (cellIterator.hasNext()) {
                    cell = (HSSFCell) cellIterator.next();
                    datos[i] = cell.toString();
                    i++;
                }

                // Si cambia la materia de la fila del excel se carga el estado y se pasa a cargar el siguiente
                if (codigoMateria(datos[0]) != estado.getCodMateria()) {
                    estados.add(estado);
                    codMaterias.add(estado.getCodMateria());

                    estado = new Estado(
                            codigoMateria(datos[0]),
                            nroRegistro,
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
            estados.add(estado);
            codMaterias.add(estado.getCodMateria());

            return new HistoriaAcademica(propuesta, nroRegistro, codPlanEstudios, codMaterias, estados);
        } catch (IOException e) {
            System.out.println("Error en la lectura del archivo .xlsx");
            return null;
        }
    }

    private static int codigoMateria(String contenidoCelda) {
        int start = contenidoCelda.indexOf("(") + 1;
        int finish = contenidoCelda.indexOf(")");

        return Integer.parseInt((String) contenidoCelda.subSequence(start, finish));
    }

    /**
     * @param tipo valor de la columna tipo en el excel de la historia academica
     * @param resultado valor de la conluman resultado en el excel de la
     * historia academica
     * @return condicion del alumno a partir de los parametros dados
     */
    private static Condicion formatCondicion(String tipo, String resultado) throws Exception {
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
                throw new Exception("Historia Academica invalida");
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
