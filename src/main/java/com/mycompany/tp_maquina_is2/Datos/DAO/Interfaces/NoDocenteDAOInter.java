/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.NoDocente;
import java.util.ArrayList;

/**
 *
 * @author ivanb
 */
public interface NoDocenteDAOInter {
    public boolean create(NoDocente noDocente);
    public ArrayList<NoDocente> read();
    public boolean update(int codigo, NoDocente noDocente);
    public boolean delete(int codigo);
}
