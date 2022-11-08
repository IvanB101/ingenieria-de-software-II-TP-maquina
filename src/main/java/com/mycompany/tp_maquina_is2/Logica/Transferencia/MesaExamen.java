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
    public MesaExamen(int turno, int anio,String codMateria, String codPlanEstudios) {
        this.turno = turno;
        this.anio = anio;
        this.codMateria = codMateria;
        this.codPlanEstudios = codPlanEstudios;
    }

    public String getCodigo() {
        return codPlanEstudios + "-" + codMateria + "-" + anio + "-" + turno;
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

    @Override
    public boolean equals(Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }

        if (turno != ((MesaExamen) object).getTurno()
                || anio != ((MesaExamen) object).getAnio()
                || !codMateria.equals(((MesaExamen) object).getCodMateria())
                || !codPlanEstudios.equals(((MesaExamen) object).getCodPlanEstudios())) {
            return false;
        }

        if (((MesaExamen) object).getCodInscriptos().size() != codInscriptos.size()) {
            return false;
        }

        for (Integer codInscripto : ((MesaExamen) object).getCodInscriptos()) {
            if (!codInscriptos.contains(codInscripto)) {
                return false;
            }
        }

        return true;
    }
}
