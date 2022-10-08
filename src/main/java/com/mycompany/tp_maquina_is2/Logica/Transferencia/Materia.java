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
    private ArrayList<Integer> codMesas;
    private int codPlanDeEstudios;
    private ArrayList<Integer> codEstados;
    private ArrayList<Integer> codExamenes;

    public Materia(int codigo, String nombre, int codigoPlan, ArrayList<Materia> correlativas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.correlativas = correlativas;
        this.codPlanDeEstudios = codigoPlan;
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

   /* public void setCorrelativas(ArrayList<Materia> correlativas) {
        this.correlativas = correlativas;
    } */
    public void setCorrelativas(Materia mat){
        this.correlativas.add(mat);
    }

    public ArrayList<Integer> getCodMesas() {
        return codMesas;
    }

    public void setCodMesas(ArrayList<Integer> codMesas) {
        this.codMesas = codMesas;
    }

    public int getCodPlanDeEstudios() {
        return codPlanDeEstudios;
    }

    public void setCodPlanDeEstudios(int codPlanDeEstudios) {
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
}
