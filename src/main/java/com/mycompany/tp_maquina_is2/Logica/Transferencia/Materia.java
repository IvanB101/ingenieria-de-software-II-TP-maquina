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
    private String codPlanDeEstudios;
    private ArrayList<String> correlativas;
    // Materias que tienen a esta como correlativa
    private ArrayList<String> dependientes;
    // Codigo de la forma mes-anio
    private ArrayList<String> codMesas;
    // Numero de registro del estudiante con la historia academica que contiene al estado
    private ArrayList<Integer> codEstados;
    // Idem anterior
    private ArrayList<Integer> codExamenes;

    public Materia(String codigo, String nombre, String codPlanDeEstudios, ArrayList<String> correlativas,
            ArrayList<String> dependientes, ArrayList<String> codMesas, ArrayList<Integer> codEstados,
            ArrayList<Integer> codExamenes) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codPlanDeEstudios = codPlanDeEstudios;
        this.correlativas = correlativas;
        this.dependientes = dependientes;
        this.codMesas = codMesas;
        this.codEstados = codEstados;
        this.codExamenes = codExamenes;
    }

    public Materia(String codigo, String nombre, String codPlanDeEstudios, ArrayList<String> correlativas, ArrayList<String> dependientes) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codPlanDeEstudios = codPlanDeEstudios;
        this.correlativas = correlativas;
        this.dependientes = dependientes;
    }

    public Materia(String codigo, String nombre, String codPlanDeEstudios) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codPlanDeEstudios = codPlanDeEstudios;
        this.correlativas = new ArrayList<>();
        this.dependientes = new ArrayList<>();
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

    public ArrayList<String> getCodMesas() {
        return codMesas;
    }

    public void setCodMesas(ArrayList<String> codMesas) {
        this.codMesas = codMesas;
    }

    @Override
    public String toString() {
        String ret = "Codigo: " + codigo + " | Nombre: " + nombre + " | Correlativas: ";
        //ArrayList<String>codCorrelativas = new ArrayList<>();
        for (int i = 0; i < correlativas.size(); i++) {
            ret = ret + (correlativas.get(i));
        }

        return ret;
    }
    @Override
    public boolean equals(Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }
        
        boolean ret = codigo.equals(((Materia)object).getCodigo())
                && codPlanDeEstudios.equals(((Materia)object).codPlanDeEstudios)
                && nombre.equals(((Materia)object).getNombre());
        
        if(!ret) {
            return false;
        }

        if(correlativas.size() != ((Materia)object).getCorrelativas().size()
                || dependientes.size() != ((Materia)object).getDependientes().size()) {
            return false;
        }
        
        for (String correlativa : ((Materia)object).getCorrelativas()) {
            if(!correlativas.contains(correlativa)) {
                return false;
            }
        }
        
        for (String dependiente : ((Materia)object).getDependientes()) {
            if(!dependiente.contains(dependiente)) {
                return false;
            }
        }
        
        return true;
    }
    
}
