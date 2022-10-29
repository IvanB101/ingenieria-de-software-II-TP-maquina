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
    // Codigo de la forma Nro_Registro_estudiante-Codigo_planEstudios-Codigo_materia-fecha
    private String codExamen;

    public Experiencia(int dificultad, int dias, int dedicacion, String codExamen) {
        this.dificultad = dificultad;
        this.dias = dias;
        this.dedicacion = dedicacion;
        this.codExamen = codExamen;
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

    public String getCodExamen() {
        return codExamen;
    }

    public void setCodExamen(String codExamen) {
        this.codExamen = codExamen;
    }
    
    @Override
    public String toString(){
        return "Dificultad:"+dificultad+ " | Dias: " + dias + " | Dedicacion: " + dedicacion +" | Cod examen: "+codExamen; 
    }

    @Override
    public boolean equals(Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }

        return dificultad == ((Experiencia)object).getDificultad()
                && dedicacion == ((Experiencia)object).getDedicacion()
                && dias == ((Experiencia)object).getDias()
                && codExamen.equals(((Experiencia)object).getCodExamen());
    }
}
