/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

/**
 *
 * @author ivanb
 */
public class Experiencia {
    private int dificultad;
    private int dias;
    private int dedicacion;
    private Examen examen;

    public Experiencia(int dificultad, int dias, int dedicacion, Examen examen) {
        this.dificultad = dificultad;
        this.dias = dias;
        this.dedicacion = dedicacion;
        this.examen = examen;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getDedicacion() {
        return dedicacion;
    }

    public void setDedicacion(int dedicacion) {
        this.dedicacion = dedicacion;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
    
    @Override
    public String toString() {
        return "Examen: " + examen.toString() + "\nDificultad Percibida: " + dificultad +
                " Dias estudiados: " + dias + "Dedicacion: " + dedicacion;
    }
}
