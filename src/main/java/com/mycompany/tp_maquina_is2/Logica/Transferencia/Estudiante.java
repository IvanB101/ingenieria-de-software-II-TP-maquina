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
public class Estudiante extends Persona {
    private int nroRegistro;
    private ArrayList<Integer> codHistoriasAcademicas;
    private ArrayList<Integer> codMesasInscriptas;

    public Estudiante(int nroRegistro, ArrayList<Integer> codHistoriasAcademicas, ArrayList<Integer> codMesasInscriptas, int codigo, String nombre, String apellido, String dni) {
        super(codigo, nombre, apellido, dni);
        this.nroRegistro = nroRegistro;
        this.codHistoriasAcademicas = codHistoriasAcademicas;
        this.codMesasInscriptas = codMesasInscriptas;
    }

    public ArrayList<Integer> getCodHistoriasAcademicas() {
        return codHistoriasAcademicas;
    }

    public void setCodHistoriasAcademicas(ArrayList<Integer> codHistoriasAcademicas) {
        this.codHistoriasAcademicas = codHistoriasAcademicas;
    }

    public ArrayList<Integer> getCodMesasInscriptas() {
        return codMesasInscriptas;
    }

    public void setCodMesasInscriptas(ArrayList<Integer> codMesasInscriptas) {
        this.codMesasInscriptas = codMesasInscriptas;
    }

    public int getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(int nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
