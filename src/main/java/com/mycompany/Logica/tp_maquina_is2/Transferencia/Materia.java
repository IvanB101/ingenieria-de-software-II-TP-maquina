/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Logica.tp_maquina_is2.Transferencia;

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
    
    
}
