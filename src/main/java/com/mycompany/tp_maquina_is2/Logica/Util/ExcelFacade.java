/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author ivanb
 */
public class ExcelFacade {

    // Estructura central dentro del libro de ArchivosManager
    HSSFWorkbook book;
    // Obtencion de la primera hoja del libro
    HSSFSheet sheet0;
    // Iterador sobre las filas de la hoja
    Iterator rowIterator;

    public ExcelFacade(File file) throws FileNotFoundException, IOException {
        book = new HSSFWorkbook(new FileInputStream(file));
        sheet0 = book.getSheetAt(0);
        rowIterator = sheet0.rowIterator();
    }

    public void avanzarHasta(String ocurrencia, int columna) {
        while (true) {
            Iterator cellIterator = ((HSSFRow) rowIterator.next()).cellIterator();

            for (int i = 0; i < columna; i++) {
                cellIterator.next();
            }

            if (((HSSFCell) cellIterator.next()).toString().equals(ocurrencia)) {
                break;
            }
        }
    }

    public String[] leerFila(int columnas) {
        String[] datos = new String[columnas];

        Iterator cellIterator = ((HSSFRow) rowIterator.next()).cellIterator();

        for (int i = 0; i < columnas; i++) {
            datos[i] = ((HSSFCell) cellIterator.next()).toString();
        }

        return datos;
    }

    public boolean hasNext() {
        return rowIterator.hasNext();
    }
}
