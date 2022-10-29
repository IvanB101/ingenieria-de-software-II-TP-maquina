/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

import java.util.ArrayList;
import java.util.HashMap;


public class PlanEstudios {
    private String codigo;
    private String propuesta;
    private HashMap<String, Materia> materias;
    private ArrayList<Integer> nroRegistroEstudiantes;

    public PlanEstudios(String codigo, String propuesta, HashMap<String, Materia> materias, ArrayList<Integer> nroRegistroEstudiantes) {
        this.codigo = codigo;
        this.propuesta = propuesta;
        this.materias = materias;
        this.nroRegistroEstudiantes = nroRegistroEstudiantes;
    }

    public PlanEstudios(String codigo, String propuesta) {
        this.codigo = codigo;
        this.propuesta = propuesta;
        this.nroRegistroEstudiantes = new ArrayList<>();
        this.materias = new HashMap<>();
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Integer> getNroRegistroEstudiantes() {
        return nroRegistroEstudiantes;
    }

    public void setNroRegistroEstudiantes(ArrayList<Integer> nroRegistroEstudiantes) {
        this.nroRegistroEstudiantes = nroRegistroEstudiantes;
    }

    public String getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(String propuesta) {
        this.propuesta = propuesta;
    }

    public HashMap<String, Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(HashMap<String, Materia> materias) {
        this.materias = materias;
    }
    
    @Override
    public String toString() {
        String ret = "Codigo: " + codigo + "\nCodigos Materias:";
        
        for (String codMateria : materias.keySet()) {
            ret += "\n" + codMateria;
        }
        
        return ret;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }

        return codigo.equals(((PlanEstudios)object).getCodigo())
                && propuesta.equals(((PlanEstudios)object).getPropuesta());
    }
}
