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
import java.sql.ResultSet;
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

            ps.setString(1, estudiante.getCodigo());
            ps.setInt(2, estudiante.getDni());
            ps.setString(3, estudiante.getNombre());
            ps.setString(4, estudiante.getApellido());

            ps.executeUpdate();
            ps = con.prepareStatement("INSERT INTO Estudiante (nroRegistro, Persona_codigo) VALUES (?,?)");

            ps.setInt(1, estudiante.getNroRegistro());
            ps.setString(2, estudiante.getCodigo());

            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public ArrayList<Estudiante> read() {
        ArrayList<Estudiante> estudiantes = new ArrayList();
        
        try {
            PreparedStatement ps = con.prepareStatement("SELECT nroRegistro, codigo, nombre, apellido,"
                    + " dni FROM Estudiante, Persona WHERE Persona_codigo = codigo");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Estudiante estudiante = new Estudiante(
                        rs.getInt("nroRegistro"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("dni")
                );
                
                estudiantes.add(estudiante);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
        
        return estudiantes;
    }

    @Override
    public boolean update(int nroRegistro, Estudiante estudiante) {
        if(delete(nroRegistro)) {
            return create(estudiante);
        }
        
        return false;
    }

    @Override
    public boolean delete(int nroRegistro) {
        PreparedStatement ps;
        String codigo = "e" + nroRegistro;
        // Control existencia del consumo con código a eliminar
        try {
            ps = con.prepareStatement("SELECT * FROM Personas WHERE codigo=?");
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rs.getString(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay ningún estudiante cargado con el código: " + codigo);
        }

        try {
            ps = con.prepareStatement("DELETE FROM Personas WHERE codigo=?");
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "El consumo np puede ser eliminado porque está referenciado en Se_Consume");
            return false;
        }

        return true;
    }

}
