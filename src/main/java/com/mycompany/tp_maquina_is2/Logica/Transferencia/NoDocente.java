/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

/**
 *
 * @author ivanb
 */
public class NoDocente extends Persona {
    
    private int nroLegajo;

    public NoDocente(int nroLegajo, String nombre, String apellido, int dni) {
        super("n" + nroLegajo, nombre, apellido, dni);
        this.nroLegajo = nroLegajo;
    }

    public int getNroLegajo() {
        return nroLegajo;
    }

    public void setNroLegajo(int nroLegajo) {
        this.nroLegajo = nroLegajo;
        
        super.setCodigo("n" + nroLegajo);
    }
}
