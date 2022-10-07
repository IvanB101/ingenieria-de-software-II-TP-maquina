/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

import java.util.ArrayList;

/**
 *
 * @author ivanb
 */
public class Estudiante extends Persona {
    private ArrayList<HistoriaAcademica> historiaAcademica;
    private ArrayList<MesaExamen> inscripciones;

    public Estudiante(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
    }

    public ArrayList<HistoriaAcademica> getHistoriaAcademica() {
        return historiaAcademica;
    }

    public void setHistoriaAcademica(ArrayList<HistoriaAcademica> historiaAcademica) {
        this.historiaAcademica = historiaAcademica;
    }

    public ArrayList<MesaExamen> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<MesaExamen> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
