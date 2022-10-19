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
public class MesaExamen {

    private int turno;
    private int anio;
    private ArrayList<Integer> codInscriptos;
    private String codMateria;
    private String codPlanEstudios;

    public MesaExamen(int turno, int anio, ArrayList<Integer> codInscriptos, String codMateria, String codPlanEstudios) {
        this.turno = turno;
        this.anio = anio;
        this.codInscriptos = codInscriptos;
        this.codMateria = codMateria;
        this.codPlanEstudios = codPlanEstudios;
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

    public ArrayList<Integer> getCodInscriptos() {
        return codInscriptos;
    }

    public void setCodInscriptos(ArrayList<Integer> codInscriptos) {
        this.codInscriptos = codInscriptos;
    }

    public String getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    public String getCodPlanEstudios() {
        return codPlanEstudios;
    }

    public void setCodPlanEstudios(String codPlanEstudios) {
        this.codPlanEstudios = codPlanEstudios;
    }
}
