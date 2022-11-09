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
    private String codPlanDeEstudios;
    private HashMap<String, Estado> estados;
    private ArrayList<String> codExamenes;

    public HistoriaAcademica(int nroRegEstudiante, String codPlanDeEstudios, HashMap<String, Estado> estados, ArrayList<String> codExamenes, ArrayList<String> codMateriasExamenes) {
        this.nroRegEstudiante = nroRegEstudiante;
        this.codPlanDeEstudios = codPlanDeEstudios;
        this.estados = estados;
        this.codExamenes = codExamenes;
    }

    public HistoriaAcademica(int nroRegEstudiante, String codPlanDeEstudios) {
        this.nroRegEstudiante = nroRegEstudiante;
        this.codPlanDeEstudios = codPlanDeEstudios;
        this.estados = new HashMap<>();
        this.codExamenes = new ArrayList<>();
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

    public ArrayList<String> getCodExamenes() {
        return codExamenes;
    }

    public void setCodExamenes(ArrayList<String> codExamenes) {
        this.codExamenes = codExamenes;
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
