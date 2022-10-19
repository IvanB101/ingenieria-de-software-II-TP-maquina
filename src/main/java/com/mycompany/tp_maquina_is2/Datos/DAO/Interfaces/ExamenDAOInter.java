/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;

/**
 *
 * @author ivanb
 */
public interface ExamenDAOInter {

    public boolean create(Examen examen);

    public Examen read(String codMateria, int nroRegistro, String codPlanEstudios);

    public boolean update(String codigo, Examen examen);

    public boolean delete(String codigo);
}
