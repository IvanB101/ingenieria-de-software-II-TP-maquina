/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Util;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
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
     * @param estudiante es el estudiante al que corresponde la historia
     * @param file es el archivo con la historia a leer
     * @return un objeto tipo HistoriaAcadamica con los datos del documento Excel
    */
    public static HistoriaAcademica leerHistoriaAcademica(Estudiante estudiante, File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            // Estructura central dentro del libro de Excel
            HSSFWorkbook book = new HSSFWorkbook(fileInputStream);
            // Obtencion de la primera hoja del libro
            HSSFSheet sheet0 = book.getSheetAt(0);
            // Iterador sobre las filas de la hoja
            Iterator rowIterator = sheet0.rowIterator();
            
            while(rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();
                // Iterador sobre las celdas de las filas
                Iterator cellIterator = row.cellIterator();
                
                while(cellIterator.hasNext()) {
                    HSSFCell cell = (HSSFCell) cellIterator.next();
                    System.out.println(cell.toString());
                }
                System.out.println("");
            }
        } catch (IOException e) {
            System.out.println("Error en la lectura del archivo .xlsx");
        }
        
        
        return null;
    }
}
