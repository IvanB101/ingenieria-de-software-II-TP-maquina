/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica.Transferencia;

import java.util.ArrayList;

/**
 *
 * @author ivanb
 */
public class MesaExamen {
    private int turno;
    private int anio;
    private ArrayList<Estudiante> inscriptos;
    private Materia materia;

    public MesaExamen() {
    }

    public MesaExamen(int turno, int anio, ArrayList<Estudiante> inscriptos, Materia materia) {
        this.turno = turno;
        this.anio = anio;
        this.inscriptos = inscriptos;
        this.materia = materia;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public ArrayList<Estudiante> getInscriptos() {
        return inscriptos;
    }

    public void setInscriptos(ArrayList<Estudiante> inscriptos) {
        this.inscriptos = inscriptos;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
