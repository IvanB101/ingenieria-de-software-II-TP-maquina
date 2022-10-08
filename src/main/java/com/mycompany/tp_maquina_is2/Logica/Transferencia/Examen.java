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
    private int codMateria;
    private int codHistoriaAcademica;
    private Experiencia experiencia;

    public Examen(Date fecha, double nota, int turno, int codMateria, int codHistoriaAcademica) {
        this.fecha = fecha;
        this.nota = nota;
        this.turno = turno;
        this.codMateria = codMateria;
        this.codHistoriaAcademica = codHistoriaAcademica;
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

    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public int getCodHistoriaAcademica() {
        return codHistoriaAcademica;
    }

    public void setCodHistoriaAcademica(int codHistoriaAcademica) {
        this.codHistoriaAcademica = codHistoriaAcademica;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }
}
