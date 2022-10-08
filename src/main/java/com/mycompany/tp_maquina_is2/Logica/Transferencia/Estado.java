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
        regular, cursando, aprobado, libre
    }
    
    private int codMateria;
    private int codHistoriaAcademica;
    private Condicion condicion;

    public Estado(int codMateria, int codHistoriaAcademica, Condicion condicion) {
        this.codMateria = codMateria;
        this.codHistoriaAcademica = codHistoriaAcademica;
        this.condicion = condicion;
    }

    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public int getCodHistoriaAcademica() {
        return codHistoriaAcademica;
    }

    public void setCodHistoriaAcademica(int codHistoriaAcademica) {
        this.codHistoriaAcademica = codHistoriaAcademica;
    }
    
    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

}
