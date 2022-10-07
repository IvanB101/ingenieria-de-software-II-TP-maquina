/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

import java.util.Date;

/**
 *
 * @author ivanb
 */
public class Examen {
    private Date fecha;
    private double nota;
    private int turno;
    private Materia materia;
    private HistoriaAcademica historiaAcademica;
    private Experiencia experiencia;

    public Examen(Date fecha, double nota, int turno, Materia materia, HistoriaAcademica historiaAcademica) {
        this.fecha = fecha;
        this.nota = nota;
        this.turno = turno;
        this.materia = materia;
        this.historiaAcademica = historiaAcademica;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public HistoriaAcademica getHistoriaAcademica() {
        return historiaAcademica;
    }

    public void setHistoriaAcademica(HistoriaAcademica historiaAcademica) {
        this.historiaAcademica = historiaAcademica;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }
    
    @Override
    public String toString() {
        return "Materia: " + materia + "\n" +
                "Fecha: " + fecha + " Nota: " + nota + " Turno: " + turno;
    }
}