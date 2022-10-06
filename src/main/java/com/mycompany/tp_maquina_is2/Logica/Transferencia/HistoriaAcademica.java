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
    private Estudiante estudiante;
    private ArrayList<Estado> estados;
    private ArrayList<Examen> examenes;
    private PlanDeEstudios planDeEstudios;

    public String getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(String propuesta) {
        this.propuesta = propuesta;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public ArrayList<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(ArrayList<Examen> examenes) {
        this.examenes = examenes;
    }

    public PlanDeEstudios getPlanDeEstudios() {
        return planDeEstudios;
    }

    public void setPlanDeEstudios(PlanDeEstudios planDeEstudios) {
        this.planDeEstudios = planDeEstudios;
    }
    
    @Override
    public String toString() {
        String retorno = "Propuesta: " + propuesta + "\nPlan de Estudios: " + planDeEstudios.getCodigo() +
                "\nEstudiante: " + estudiante.toString() + "\nEstados:";
        
        for (Estado estado : estados) {
            retorno += "\t" + estado.toString();
        }
        
        retorno += "Examenes:";
        for (Examen examen : examenes) {
            retorno += examen.toString();
        }
        
        return retorno;
    }
}
