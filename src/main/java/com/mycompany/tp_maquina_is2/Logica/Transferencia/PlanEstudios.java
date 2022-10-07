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
public class PlanEstudios {
    private int codigo;
    private ArrayList<Materia> materias;

    public PlanEstudios(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }
    
    @Override
    public String toString() {
        String retorno = "Codigo: " + codigo + "Materias: ";
        for (Materia materia : materias) {
            retorno += "\t" + materia;
        }
        
        return retorno;
    }
}
