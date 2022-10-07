/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import java.util.ArrayList;

/**
 *
 * @author ivanb
 */
public interface MateriaDAOInter {
    public boolean create(Materia materia);
    public ArrayList<Materia> read();
    public boolean update(int codigo, Materia materia);
    public boolean delete(int codigo);
}
