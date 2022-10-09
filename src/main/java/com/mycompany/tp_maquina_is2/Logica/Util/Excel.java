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
    public static HistoriaAcademica leerHistoriaAcademica(int nroRegistro, int codPlanEstudios, File file) throws Exception {
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
            }
            
            Estado estado = new Estado(
                    codigoMateria(datos[0]),
                    nroRegistro,
                    formatCondicion(datos[2], datos[4])
            );

            // Carga de los estados
            String[] datos = new String[7];

            while (rowIterator.hasNext()) {
                row = (HSSFRow) rowIterator.next();
                cellIterator = row.cellIterator();

                i = 0;

                while (cellIterator.hasNext()) {
                    cell = (HSSFCell) cellIterator.next();

                    datos[i] = cell.toString();
                    
                    i++;
                }
            }

            // HistoriaAcademica historia = new HistoriaAcademica();
        } catch (IOException e) {
            System.out.println("Error en la lectura del archivo .xlsx");
            return null;
        }

        return null;
    }

    private static int codigoMateria(String contenidoCelda) {
        int start = contenidoCelda.indexOf("(");
        int finish = contenidoCelda.indexOf(")");

        return Integer.parseInt((String) contenidoCelda.subSequence(start, finish));
    }
    
    private static Condicion formatCondicion(String tipo, String resultado) throws Exception {
        Condicion ret = Condicion.libre;
        
        switch (tipo) {
            case "Examen":
                if(resultado.equals("Aprobado")) {
                    ret = Condicion.parse("aprobado");
                }
            case "Regularidad":
                break;
            case "Promocion":
                break;
            default:
                throw new Exception("Historia Academica invalida");
        }
        
        return ret;
    }
}
