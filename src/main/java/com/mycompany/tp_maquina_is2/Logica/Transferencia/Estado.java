/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Transferencia;

import java.time.LocalDate;

/**
 *
 * @author ivanb
 */
public class Estado {

    public enum Condicion {
        regular, cursando, aprobado, libre;

        public static Condicion parse(String text) {
            if (regular.name().equalsIgnoreCase(text)) {
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

    private String codMateria;
    private String codHistoriaAcademica;
    private Condicion condicion;
    private LocalDate fecha;

    public Estado(String codMateria, String codHistoriaAcademica, Condicion condicion, LocalDate fecha) {
        this.codMateria = codMateria;
        this.codHistoriaAcademica = codHistoriaAcademica;
        this.condicion = condicion;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codHistoriaAcademica + "-" + codMateria;
    }

    public String getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public String getCodHistoriaAcademica() {
        return codHistoriaAcademica;
    }

    public void setCodHistoriaAcademica(String codHitoriaAcademica) {
        this.codHistoriaAcademica = codHitoriaAcademica;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "codHistoriaAcademica: " + codHistoriaAcademica + " | codMat: " + codMateria
                + " | Estado: " + condicion + " | Fecha: " + fecha;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }

        return codMateria.equals(((Estado)object).getCodMateria())
                && codHistoriaAcademica.equals(((Estado)object).getCodHistoriaAcademica())
                && condicion.equals(((Estado)object).getCondicion())
                && (fecha + "").equals(((Estado)object).getFecha() + "");
    }
}
