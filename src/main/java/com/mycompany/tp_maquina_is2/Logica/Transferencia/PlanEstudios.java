/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

import java.util.ArrayList;


public class PlanEstudios {
    private String codigo;
    private String propuesta;
    private ArrayList<String> codMaterias;
    private ArrayList<Integer> nroRegistroEstudiantes;

    public PlanEstudios(String codigo, String propuesta, ArrayList<String> codMaterias, ArrayList<Integer> nroRegistroEstudiantes) {
        this.codigo = codigo;
        this.propuesta = propuesta;
        this.codMaterias = codMaterias;
        this.nroRegistroEstudiantes = nroRegistroEstudiantes;
    }

    public PlanEstudios(String codigo, String propuesta) {
        this.codigo = codigo;
        this.propuesta = propuesta;
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

    public ArrayList<String> getCodMaterias() {
        return codMaterias;
    }

    public void setCodMaterias(ArrayList<String> codMaterias) {
        this.codMaterias = codMaterias;
    }
    
    @Override
    public String toString() {
        String ret = "Codigo: " + codigo + "\nCodigos Materias:";
        
        for (String codMateria : codMaterias) {
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
