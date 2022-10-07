/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Experiencia;
import java.util.ArrayList;

/**
 *
 * @author ivanb
 */
public interface ExperienciaDAOInter {
    public boolean create(Experiencia experiencia);
    public ArrayList<Experiencia> read();
    public boolean update(int codigo, Experiencia experiencia);
    public boolean delete(int codigo);
}
