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
    private String codigo;
    private int turno;
    private int codMateria;
    private int nroRegitroEstudiante;
    private Experiencia experiencia;

    public Examen(LocalDate fecha, int turno, float nota, int codMateria, int nroRegistroEstudiante) {
        //dia-año-codmat-codhistoria
        this.codigo = "" + fecha.getDayOfYear() + "-" + fecha.getYear() + "-" + codMateria + "-" + nroRegistroEstudiante;
        this.fecha = fecha;
        this.nota = nota;
        this.turno = turno;
        this.codMateria = codMateria;
        this.nroRegitroEstudiante = nroRegistroEstudiante;
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

    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public int getNroRegitroEstudiante() {
        return nroRegitroEstudiante;
    }

    public void setNroRegitroEstudiante(int codHistoriaAcademica) {
        this.nroRegitroEstudiante = codHistoriaAcademica;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Nro Registro: " + nroRegitroEstudiante + " | Codigo Materia: " + codMateria
                + " | Turno: " + turno + " | Codigo: " + codigo + " | Nota: " + nota + " | Experiencia: " + experiencia;
    }
}
