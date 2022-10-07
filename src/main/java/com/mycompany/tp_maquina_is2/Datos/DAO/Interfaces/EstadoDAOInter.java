/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado;
import java.util.ArrayList;

/**
 *
 * @author ivanb
 */
public interface EstadoDAOInter {
    public boolean create(Estado estado);
    public ArrayList<Estado> read();
    public boolean update(int codigo, Estado estado);
    public boolean delete(int codigo);
}
