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

    public Estudiante(int nroRegistro, String nombre, String apellido, int dni) {
        super("e" + nroRegistro, nombre, apellido, dni);
        this.nroRegistro = nroRegistro;
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
        
        super.setCodigo("e" + nroRegistro);
    }

    @Override
    public String toString() {
        return super.toString() + "Nro Registro: " + nroRegistro;
    }
}
