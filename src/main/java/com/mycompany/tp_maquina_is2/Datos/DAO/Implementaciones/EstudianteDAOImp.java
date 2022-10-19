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

/**
 *
 * @author ivanb
 */
public class EstudianteDAOImp implements EstudianteDAOInter {

    Conexion conexion;

    public EstudianteDAOImp(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean create(Estudiante estudiante) {
        try {
            Connection con = conexion.getConnection();

            // Insercion de los datos correspondientes a Persona
            PreparedStatement ps = con.prepareStatement("INSERT INTO Persona (codigo, dni, nombre, apellido) VALUES (?,?,?,?)");

            ps.setString(1, estudiante.getCodigo());
            ps.setInt(2, estudiante.getDni());
            ps.setString(3, estudiante.getNombre());
            ps.setString(4, estudiante.getApellido());

            ps.executeUpdate();

            // Insercion de los datos correspondientes a Estudiante
            ps = con.prepareStatement("INSERT INTO Estudiante (nroRegistro, Persona_codigo) VALUES (?,?)");

            ps.setInt(1, estudiante.getNroRegistro());
            ps.setString(2, estudiante.getCodigo());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + ": " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Estudiante read(int nroRegistro) {
        try {
            Connection con = conexion.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT nroRegistro, codigo, nombre, apellido, dni "
                    + "FROM Estudiante, Persona "
                    + "WHERE Persona_codigo=codigo AND nroRegistro=?");
            ps.setInt(1, nroRegistro);
            ResultSet rs = ps.executeQuery();

            rs.next();
            return new Estudiante(
                    rs.getInt("nroRegistro"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("dni"));
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(int nroRegistro, Estudiante estudiante) {
        try {
            Connection con = conexion.getConnection();

            PreparedStatement ps = con.prepareStatement("UPDATE Persona "
                    + "SET codigo=?, dni=?, nombre=?, apellido=?"
                    + "WHERE codigo=?;"
                    + "UPDATE Estudiante "
                    + "SET nroRegistro=?, Persona_codigo=?"
                    + "WHERE nroRegistro=?");

            ps.setString(1, estudiante.getCodigo());
            ps.setInt(2, estudiante.getDni());
            ps.setString(3, estudiante.getNombre());
            ps.setString(4, estudiante.getApellido());
            ps.setString(5, "e" + nroRegistro);
            ps.setInt(6, estudiante.getNroRegistro());
            ps.setString(7, estudiante.getCodigo());
            ps.setInt(8, nroRegistro);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + ": " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(int nroRegistro) {
        PreparedStatement ps;
        String codigo = "e" + nroRegistro;

        try {
            Connection con = conexion.getConnection();

            ps = con.prepareStatement("SELECT * FROM Persona WHERE codigo=?");
            ps.setString(1, codigo);

            ResultSet rs = ps.executeQuery();

            rs.next();
            rs.getString(1);
        } catch (SQLException e) {
            System.out.println("No hay un estudiante cargado con el nro de registro: " + nroRegistro);
            return false;
        }

        try {
            Connection con = conexion.getConnection();

            ps = con.prepareStatement("DELETE FROM Persona WHERE codigo=?");
            ps.setString(1, codigo);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + ": " + e.getMessage());
            return false;
        }

        return true;
    }

}
