/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author ivanb
 */
public class Estado {
    public enum Condicion {
        regular, cursando, aprobado, libre
    }
    
    private Materia materia;
    private HistoriaAcademica historiaAcademica;
    private Condicion condicion;
}
