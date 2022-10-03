/* =
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;


public class PlanDeEstudios {
    private int codigo;
    private ArrayList<Materia> materias = new ArrayList();
    private ArrayList<HistoriaAcademica> historia =new ArrayList();

     public PlanDeEstudios(int codigo, ArrayList<Materia> materias, ArrayList<HistoriaAcademica> historia) {
        this.codigo = codigo;
        this.materias = materias;
        this.historia = historia;
    } 
    
    public PlanDeEstudios(){ //constructor minimo?
        Materia matAux= new Materia();
        HistoriaAcademica historiaAux = new HistoriaAcademica();
        materias.add(matAux);
        historia.add(historiaAux);
    }
    
}
