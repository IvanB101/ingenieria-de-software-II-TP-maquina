/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import java.util.HashMap;

/**
 *
 * @author ivanb
 */
public interface EstudianteDAOInter {

    public boolean create(Estudiante estudiante);

    public HashMap<Integer, Estudiante> read();

    public boolean update(int nroRegistro, Estudiante estudiante);

    public boolean delete(int nroRegistro);
}
