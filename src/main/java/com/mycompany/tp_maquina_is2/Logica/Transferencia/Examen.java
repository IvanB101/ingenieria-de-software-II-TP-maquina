/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

import java.time.LocalDate;

/**
 *
 * @author ivanb
 */
public class Examen {

    private LocalDate fecha;
    private float nota;
    private int turno;
    private String codMateria;
    // Codigo de la forma nroRegistro-codPlanEstudios
    private String codHistoriaAcademica;
    private Experiencia experiencia;

    public Examen(LocalDate fecha, int turno, float nota, String codMateria, String codHistoriaAcademica) {
        //dia-año-codmat-codhistoria
        this.fecha = fecha;
        this.nota = nota;
        this.turno = turno;
        this.codMateria = codMateria;
        this.codHistoriaAcademica = codHistoriaAcademica;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    public String getCodigo() {
        return codHistoriaAcademica + "-" + codMateria + "-" + fecha;
    }

    public String getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    public String getCodHistoriaAcademica() {
        return codHistoriaAcademica;
    }

    public void setCodHistoriaAcademica(String codHistoriaAcademica) {
        this.codHistoriaAcademica = codHistoriaAcademica;
    }

    @Override
    public String toString() {
        return "Nro Registro: " + codHistoriaAcademica + " | Codigo Materia: " + codMateria
                + " | Turno: " + turno + " | Codigo: " + codMateria + " | Nota: " + nota + " | Experiencia: " + experiencia;
    }
}
