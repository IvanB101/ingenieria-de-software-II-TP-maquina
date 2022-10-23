/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces.PlanEstudiosDAOInter;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public class PlanEstudiosDAOImp implements PlanEstudiosDAOInter {

    Conexion conexion;

    public PlanEstudiosDAOImp(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(PlanEstudios planEstudios) throws SQLException {
        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("INSERT INTO PlanEstudios (codigo, propuesta) VALUES (?,?)");

        ps.setString(1, planEstudios.getCodigo());
        ps.setString(2, planEstudios.getPropuesta());
        ps.executeUpdate();
    }

    @Override
    public PlanEstudios read(String codigo) throws SQLException {

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM PlanEstudios WHERE codigo=?");
        ps.setString(1, codigo);

        ResultSet rs = ps.executeQuery();

        rs.next();

        return new PlanEstudios(rs.getString("codigo"), rs.getString("propuesta"));
    }

    @Override
    public void update(String codigo, PlanEstudios planEstudios) throws SQLException {
        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("UPDATE PlanEstudios "
                + "SET codigo=?, propuesta=?"
                + "WHERE codigo=?");

        ps.setString(1, planEstudios.getCodigo());
        ps.setString(2, planEstudios.getPropuesta());
        ps.setString(3, codigo);

        ps.executeUpdate();
    }

    @Override
    public void delete(String codigo) throws SQLException {

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("DELETE FROM PlanEstudios WHERE codigo=?");
        ps.setString(1, codigo);
        ps.executeUpdate();
    }

}
