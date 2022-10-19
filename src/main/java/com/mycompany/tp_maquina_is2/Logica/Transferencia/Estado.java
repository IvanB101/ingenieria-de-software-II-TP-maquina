/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

/**
 *
 * @author ivanb
 */
public class Estado {
    public enum Condicion {
        regular, cursando, aprobado, libre;
        
        public static Condicion parse(String text) {
            if(regular.name().equalsIgnoreCase(text)) {
                return regular;
            } else if (cursando.name().equalsIgnoreCase(text)) {
                return cursando;
            } else if (aprobado.name().equalsIgnoreCase(text)) {
                return aprobado;
            } else {
                return libre;
            }
        }
    }
    
    private int codMateria;
    private int nroRegistroHistoria;
    private Condicion condicion;

    public Estado(int codMateria, int nroRegistroHistoria, Condicion condicion) {
        this.codMateria = codMateria;
        this.nroRegistroHistoria = nroRegistroHistoria;
        this.condicion = condicion;
    }

    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public int getNroRegistroHistoria() {
        return nroRegistroHistoria;
    }

    public void setNroRegistroHistoria(int nroRegistroHistoria) {
        this.nroRegistroHistoria = nroRegistroHistoria;
    }
    
    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return "nroReg: " + nroRegistroHistoria + " | codMat: " + codMateria + " | Estado: " + condicion;
    }
}
