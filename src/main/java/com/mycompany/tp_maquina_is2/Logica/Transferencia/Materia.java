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
public class Materia {

    private String codigo;
    private String nombre;
    private ArrayList<String> correlativas;
    private ArrayList<String> dependientes;
    private ArrayList<Integer> codMesas;
    private String codPlanDeEstudios;
    private ArrayList<Integer> codEstados;
    private ArrayList<Integer> codExamenes;

    public Materia(String codigo, String nombre, String codigoPlan, ArrayList<String> correlativas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.correlativas = correlativas;
        this.codPlanDeEstudios = codigoPlan;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getCodMesas() {
        return codMesas;
    }

    public void setCodMesas(ArrayList<Integer> codMesas) {
        this.codMesas = codMesas;
    }

    public String getCodPlanDeEstudios() {
        return codPlanDeEstudios;
    }

    public void setCodPlanDeEstudios(String codPlanDeEstudios) {
        this.codPlanDeEstudios = codPlanDeEstudios;
    }

    public ArrayList<Integer> getCodEstados() {
        return codEstados;
    }

    public void setCodEstados(ArrayList<Integer> codEstados) {
        this.codEstados = codEstados;
    }

    public ArrayList<Integer> getCodExamenes() {
        return codExamenes;
    }

    public void setCodExamenes(ArrayList<Integer> codExamenes) {
        this.codExamenes = codExamenes;
    }

    public ArrayList<String> getCorrelativas() {
        return correlativas;
    }

    public void setCorrelativas(ArrayList<String> correlativas) {
        this.correlativas = correlativas;
    }

    public ArrayList<String> getDependientes() {
        return dependientes;
    }

    public void setDependientes(ArrayList<String> dependientes) {
        this.dependientes = dependientes;
    }
    
    @Override
    public String toString() {
        String ret = "Codigo: " + codigo + " | Nombre: " + nombre + " | Correlativas: ";
        //ArrayList<String>codCorrelativas = new ArrayList<>();
        for(int i =0; i<correlativas.size();i++){
            ret = ret + (correlativas.get(i));
        }
        
        return ret;
    }
}
