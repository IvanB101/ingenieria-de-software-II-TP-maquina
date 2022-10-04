/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

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
    
    @Override
    public String toString() {
        return "Materia: " + materia.toString() + "\n" +
                "Condicion: " + condicion;
    }
}
