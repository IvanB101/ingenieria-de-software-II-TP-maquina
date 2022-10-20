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
    public void create(Estudiante estudiante) throws SQLException {
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
    }

    @Override
    public Estudiante read(int nroRegistro) throws SQLException {
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
            switch (e.getMessage()) {
                case "ResultSet not positioned properly, perhaps you need to call next.":
                    throw new SQLException("No hay estudiante con nro registro: " + nroRegistro + " cargado", "", 1);
                default:
                    throw e;
            }
        }
    }

    @Override
    public void update(int nroRegistro, Estudiante estudiante) throws SQLException {
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
    }

    @Override
    public void delete(int nroRegistro) throws SQLException {
        String codigo = "e" + nroRegistro;

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("DELETE FROM Persona WHERE codigo=?");
        ps.setString(1, codigo);

        ps.executeUpdate();
    }

}
