/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

import java.util.ArrayList;


public class PlanEstudios {
    private String codigo;
    private ArrayList<Integer> codMaterias;

    public PlanEstudios(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Integer> getCodMaterias() {
        return codMaterias;
    }

    public void setCodMaterias(ArrayList<Integer> codMaterias) {
        this.codMaterias = codMaterias;
    }
    
    @Override
    public String toString() {
        String ret = "Codigo: " + codigo + "\nCodigos Materias:";
        
        for (Integer codMateria : codMaterias) {
            ret += "\n" + codMateria;
        }
        
        return ret;
    }
}
