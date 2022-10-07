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

    public Estado(Materia materia, HistoriaAcademica historiaAcademica, Condicion condicion) {
        this.materia = materia;
        this.historiaAcademica = historiaAcademica;
        this.condicion = condicion;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }
    
    @Override
    public String toString() {
        return "Materia: " + materia.toStringSimplificado() + " Condicion: " + condicion;
    }
}
