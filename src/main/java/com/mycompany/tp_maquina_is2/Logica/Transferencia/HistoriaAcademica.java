/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ivanb
 */
public class HistoriaAcademica {

    private int nroRegEstudiante;
    private HashMap<String, Estado> estados;
    private ArrayList<Integer> codExamenes;
    private String codPlanDeEstudios;
    private ArrayList<Integer> codMateriasExamenes;
    private ArrayList<Integer> codMateriasEstados;

    public HistoriaAcademica(int nroRegEstudiante, HashMap<String, Estado> estados,
            ArrayList<Integer> codExamenes, String codPlanDeEstudios, ArrayList<Integer> codMateriasExamenes,
            ArrayList<Integer> codMateriasEstados) {
        this.nroRegEstudiante = nroRegEstudiante;
        this.estados = estados;
        this.codExamenes = codExamenes;
        this.codPlanDeEstudios = codPlanDeEstudios;
        this.codMateriasExamenes = codMateriasExamenes;
        this.codMateriasEstados = codMateriasEstados;
    }

    public HistoriaAcademica(int nroRegEstudiante, String codPlanDeEstudios) {
        this.nroRegEstudiante = nroRegEstudiante;
        this.codPlanDeEstudios = codPlanDeEstudios;
        this.estados = new HashMap<>();
    }

    public String getCodigo() {
        return nroRegEstudiante + "-" + codPlanDeEstudios; 
    }
    public int getNroRegEstudiante() {
        return nroRegEstudiante;
    }

    public void setNroRegEstudiante(int codEstudiante) {
        this.nroRegEstudiante = codEstudiante;
    }

    public ArrayList<Integer> getCodExamenes() {
        return codExamenes;
    }

    public void setCodExamenes(ArrayList<Integer> codExamenes) {
        this.codExamenes = codExamenes;
    }

    public String getCodPlanDeEstudios() {
        return codPlanDeEstudios;
    }

    public void setCodPlanDeEstudios(String codPlanDeEstudios) {
        this.codPlanDeEstudios = codPlanDeEstudios;
    }

    public HashMap<String, Estado> getEstados() {
        return estados;
    }

    public void setEstados(HashMap<String, Estado> estados) {
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

    @Override
    public String toString() {
        String ret = "NroRegistro: " + nroRegEstudiante + " | Plan de Estudios: " + codPlanDeEstudios
                + "\nEstados:\n";

        for (Estado estado : estados.values()) {
            ret += estado + "\n";
        }

        return ret;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }

        return !(nroRegEstudiante != ((HistoriaAcademica)object).getNroRegEstudiante()
                || !codPlanDeEstudios.equals(((HistoriaAcademica)object).getCodPlanDeEstudios()));
    }
}
