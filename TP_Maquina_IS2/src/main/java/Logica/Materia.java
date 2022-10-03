/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;


public class Materia {
    private int codigo;
    private String nombre;
    private ArrayList<Materia> correlativas;
    private ArrayList<MesaExamen> mesas;
    private PlanDeEstudios planDeEstudios = new PlanDeEstudios(); //multiplicidad es1
    private ArrayList<Estado> estados;//la materia tiene un único estado o no?
    private ArrayList<Examen> examenes;
    
    public Materia(){  
    }

    public Materia(int codigo, String nombre, ArrayList<Materia> correlativas, ArrayList<MesaExamen> mesas, ArrayList<Estado> estados, ArrayList<Examen> examenes) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.correlativas = correlativas;
        this.mesas = mesas;
        this.estados = estados;
        this.examenes = examenes;
    }
    
}
