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
    private int codigo;
    private String nombre;
    private ArrayList<Materia> correlativas;
    private ArrayList<MesaExamen> mesas;
    private PlanDeEstudios planDeEstudios;
    private ArrayList<Estado> estados;
    private ArrayList<Examen> examenes;

    public Materia(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.planDeEstudios = new PlanDeEstudios();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Materia> getCorrelativas() {
        return correlativas;
    }

    public void setCorrelativas(ArrayList<Materia> correlativas) {
        this.correlativas = correlativas;
    }

    public PlanDeEstudios getPlanDeEstudios() {
        return planDeEstudios;
    }

    public void setPlanDeEstudios(PlanDeEstudios planDeEstudios) {
        this.planDeEstudios = planDeEstudios;
    }
    
    @Override
    public String toString() {
        String retorno = "Codigo: " + codigo + " Nombre: " + nombre +
                " Plan de Estudios: " + planDeEstudios.getCodigo() + "Correlativas:\n";
        for (Materia correlativa : correlativas) {
            retorno += correlativa.toStringSimplificado();
        }
        return retorno;
    }
    
    public String toStringSimplificado() {
        return "Codigo: " + codigo + " Nombre: " + nombre;
    }
}
