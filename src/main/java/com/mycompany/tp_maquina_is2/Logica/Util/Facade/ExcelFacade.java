/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Util.Facade;

import java.io.File;
import java.io.IOException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;

/**
 *
 * @author ivanb
 */
public abstract class ExcelFacade {
    
    public static ExcelFacade leerDocumento(File file) throws IOException {
        try {            
            return new ExcelFacadeHSSF(file);
        } catch (OfficeXmlFileException e) {
            return new ExcelFacadeXSSF(file);
        }
    }

    public abstract void avanzarHasta(String ocurrencia, int columna);
    
    public abstract String[] leerFila(int columnas);

    public abstract boolean hasNext();
}
