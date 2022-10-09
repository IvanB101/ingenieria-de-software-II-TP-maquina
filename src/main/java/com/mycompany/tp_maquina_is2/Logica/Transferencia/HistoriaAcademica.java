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
public class HistoriaAcademica {

    private String propuesta;
    private int codEstudiante;
    private ArrayList<Estado> estados;
    private ArrayList<Integer> codExamenes;
    private int codPlanDeEstudios;
    private ArrayList<Integer> codMateriasExamenes;
    private ArrayList<Integer> codMateriasEstados;

    public HistoriaAcademica(String propuesta, int codEstudiante, int codPlanDeEstudios,
            ArrayList<Integer> codMateriasEstados, ArrayList<Estado> estados) {
        this.propuesta = propuesta;
        this.codEstudiante = codEstudiante;
        this.codPlanDeEstudios = codPlanDeEstudios;
        this.codMateriasEstados = codMateriasEstados;
        this.estados = estados;
    }

    public String getPropuesta() {
        return propuesta;
    }

    public int getNroRegEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(int codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public ArrayList<Integer> getCodExamenes() {
        return codExamenes;
    }

    public void setCodExamenes(ArrayList<Integer> codExamenes) {
        this.codExamenes = codExamenes;
    }

    public int getCodPlanDeEstudios() {
        return codPlanDeEstudios;
    }

    public void setCodPlanDeEstudios(int codPlanDeEstudios) {
        this.codPlanDeEstudios = codPlanDeEstudios;
    }

    public void setPropuesta(String propuesta) {
        this.propuesta = propuesta;
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public ArrayList<Integer> getCodMateriasExamenes() {
        return codMateriasExamenes;
    }

    public void setCodMateriasExamenes(ArrayList<Integer> codMateriasExamenes) {
        this.codMateriasExamenes = codMateriasExamenes;
    }

    public ArrayList<Integer> getCodMateriasEstados() {
        return codMateriasEstados;
    }

    public void setCodMateriasEstados(ArrayList<Integer> codMateriasEstados) {
        this.codMateriasEstados = codMateriasEstados;
    }
}
