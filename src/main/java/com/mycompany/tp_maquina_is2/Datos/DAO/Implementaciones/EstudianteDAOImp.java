/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces.EstudianteDAOInter;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ivanb
 */
public class EstudianteDAOImp implements EstudianteDAOInter {

    Connection con;

    public EstudianteDAOImp(Conexion conexion) {
        try {
            con = conexion.getInstance();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public boolean create(Estudiante estudiante) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Persona (codigo, dni, nombre, apellido) VALUES (?,?,?,?)");

            ps.setInt(1, estudiante.getCodigo());
            ps.setInt(2, estudiante.getDni());
            ps.setString(3, estudiante.getNombre());
            ps.setString(4, estudiante.getApellido());

            ps.executeUpdate();
            
            ps = con.prepareStatement("INSERT INTO Estudiante (nroRegistro, Persona_codigo) VALUES (?,?)");
            
            ps.setInt(1, estudiante.getNroRegistro());
            ps.setInt(2, estudiante.getCodigo());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public ArrayList<Estudiante> read() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(int nroRegistro, Estudiante estudiante) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int nroRegistro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
