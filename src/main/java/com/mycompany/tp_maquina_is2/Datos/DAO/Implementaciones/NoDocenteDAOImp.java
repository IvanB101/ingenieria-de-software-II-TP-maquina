/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces.NoDocenteDAOInter;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.NoDocente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public class NoDocenteDAOImp implements NoDocenteDAOInter {
    
    Conexion conexion;

    public NoDocenteDAOImp(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(NoDocente noDocente) throws SQLException {
        Connection con = conexion.getConnection();

        // Insercion de los datos correspondientes a Persona
        PreparedStatement ps = con.prepareStatement("INSERT INTO Persona (codigo, dni, nombre, apellido) VALUES (?,?,?,?)");

        ps.setString(1, noDocente.getCodigo());
        ps.setInt(2, noDocente.getDni());
        ps.setString(3, noDocente.getNombre());
        ps.setString(4, noDocente.getApellido());

        ps.executeUpdate();

        // Insercion de los datos correspondientes a Estudiante
        ps = con.prepareStatement("INSERT INTO NoDocente (nroLegajo, Persona_codigo) VALUES (?,?)");

        ps.setInt(1, noDocente.getNroLegajo());
        ps.setString(2, noDocente.getCodigo());

        ps.executeUpdate();
    }

    @Override
    public NoDocente read(int nroLegajo) throws SQLException {
        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT nroLegajo, codigo, nombre, apellido, dni "
                + "FROM NoDocente, Persona "
                + "WHERE Persona_codigo=codigo AND nroLegajo=?");
        ps.setInt(1, nroLegajo);
        ResultSet rs = ps.executeQuery();

        rs.next();
        return new NoDocente(
                rs.getInt("nroLegajo"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getInt("dni"));
    }

    @Override
    public void update(int nroLegajo, NoDocente noDocente) throws SQLException {
        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("UPDATE Persona "
                + "SET codigo=?, dni=?, nombre=?, apellido=?"
                + "WHERE codigo=?;"
                + "UPDATE NoDocente "
                + "SET nroLegajo=?, Persona_codigo=?"
                + "WHERE nroLegajo=?");

        ps.setString(1, noDocente.getCodigo());
        ps.setInt(2, noDocente.getDni());
        ps.setString(3, noDocente.getNombre());
        ps.setString(4, noDocente.getApellido());
        ps.setString(5, "n" + nroLegajo);
        ps.setInt(6, noDocente.getNroLegajo());
        ps.setString(7, noDocente.getCodigo());
        ps.setInt(8, nroLegajo);

        ps.executeUpdate();
    }

    @Override
    public void delete(int nroLegajo) throws SQLException {
        String codigo = "n" + nroLegajo;

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("DELETE FROM Persona WHERE codigo=?");
        ps.setString(1, codigo);

        ps.executeUpdate();
    }

}
