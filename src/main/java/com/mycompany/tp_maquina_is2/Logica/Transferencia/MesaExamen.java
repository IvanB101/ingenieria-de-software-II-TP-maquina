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

    private int codigo;
    private int turno;
    private int anio;
    private ArrayList<Integer> codInscriptos;
    private int codMateria;

    public MesaExamen(int codigo, int turno, int anio, int codMateria) {
        this.codigo = codigo;
        this.turno = turno;
        this.anio = anio;
        this.codMateria = codMateria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }
}
