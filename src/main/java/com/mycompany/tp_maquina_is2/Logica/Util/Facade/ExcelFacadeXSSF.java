/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Util.Facade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ivanb
 */
public class ExcelFacadeXSSF extends ExcelFacade {
    // Estructura central dentro del libro de ArchivosManager
    private final XSSFWorkbook book;
    // Obtencion de la primera hoja del libro
    private final XSSFSheet sheet0;
    // Iterador sobre las filas de la hoja
    private final Iterator rowIterator;

    ExcelFacadeXSSF(File file) throws FileNotFoundException, IOException {
        book = new XSSFWorkbook(new FileInputStream(file));
        sheet0 = book.getSheetAt(0);
        rowIterator = sheet0.rowIterator();
    }

    @Override
    public void avanzarHasta(String ocurrencia, int columna) {
        while (true) {
            Iterator cellIterator = ((XSSFRow) rowIterator.next()).cellIterator();

            for (int i = 0; i < columna; i++) {
                cellIterator.next();
            }

            if (((XSSFCell) cellIterator.next()).toString().equals(ocurrencia)) {
                break;
            }
        }
    }

    @Override
    public String[] leerFila(int columnas) {
        String[] datos = new String[columnas];

        Iterator cellIterator = ((XSSFRow) rowIterator.next()).cellIterator();

        for (int i = 0; i < columnas; i++) {
            datos[i] = ((XSSFCell) cellIterator.next()).toString();
        }

        return datos;
    }

    @Override
    public boolean hasNext() {
        return rowIterator.hasNext();
    }
}
