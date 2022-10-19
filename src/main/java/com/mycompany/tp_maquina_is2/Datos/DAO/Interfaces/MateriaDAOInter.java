/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;

/**
 *
 * @author ivanb
 */
public interface MateriaDAOInter {

    public boolean create(Materia materia);

    public Materia read(String codigo, String codPlanEstudios);

    public boolean update(String codigo, String codPlanEstudios, Materia materia);

    public boolean delete(String codigo, String codPlanEstudios);
}
