/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces.EstadoDAOInter;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author ivanb
 */
public class EstadoDAOImp implements EstadoDAOInter {

    Conexion conexion;

    public EstadoDAOImp(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(Estado estado) throws SQLException {
        Connection con = conexion.getConnection();

        String[]datos = estado.getCodHistoriaAcademica().split("-");
        int nroRegistro = Integer.parseInt(datos[0]);

        PreparedStatement ps = con.prepareStatement("INSERT INTO Estado (regularidad, Materia_codigo, "
                + "HistoriaAcademica_Estudiante_nroRegistro, PlanEstudios_codigo, fecha) VALUES(?,?,?,?,?)");
        ps.setString(1, estado.getCondicion().toString());
        ps.setString(2, estado.getCodMateria());
        ps.setInt(3, nroRegistro);
        ps.setString(4, datos[1]);
        ps.setDate(5, Date.valueOf(estado.getFecha()));

        ps.executeUpdate();
    }

    @Override
    public Estado read(String codigo) throws SQLException {
        Connection con = conexion.getConnection();

        String[] datos = codigo.split("-");
        int nroRegistro = Integer.parseInt(datos[0]);

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Estado "
                + "WHERE Materia_codigo=? AND HistoriaAcademica_Estudiante_nroRegistro=? "
                + "AND PlanEstudios_codigo=?");
        ps.setString(1, datos[2]);
        ps.setInt(2, nroRegistro);
        ps.setString(3, datos[1]);

        ResultSet rs = ps.executeQuery();

        rs.next();

        return new Estado(datos[2], datos[0] + "-" + datos[1],
                Estado.Condicion.parse(rs.getString("regularidad")),
                LocalDate.parse(rs.getDate("fecha") + ""));
    }

    @Override
    public void update(String codigo, Estado estado) throws SQLException {
        Connection con = conexion.getConnection();

        String[] datos = codigo.split("-");
        int nroRegistro = Integer.parseInt(datos[0]);

        PreparedStatement ps = con.prepareStatement("UPDATE Estado "
                + "SET regularidad=?, fecha=?"
                + "WHERE Materia_codigo=? AND HistoriaAcademica_Estudiante_nroRegistro=? "
                + "AND PlanEstudios_codigo=?");
        ps.setString(1, estado.getCondicion().toString());
        ps.setDate(2, Date.valueOf(estado.getFecha()));
        ps.setString(3, datos[2]);
        ps.setInt(4, nroRegistro);
        ps.setString(5, datos[1]);

        ps.executeUpdate();
    }

    @Override
    public void delete(String codigo) throws SQLException {
        Connection con = conexion.getConnection();

        String[] datos = codigo.split("-");
        int nroRegistro = Integer.parseInt(datos[0]);

        PreparedStatement ps = con.prepareStatement("DELETE FROM Estado "
                + "WHERE Materia_codigo=? AND HistoriaAcademica_Estudiante_nroRegistro=? "
                + "AND PlanEstudios_codigo=?");
        ps.setString(1, datos[2]);
        ps.setInt(2, nroRegistro);
        ps.setString(3, datos[1]);

        ps.executeUpdate();
    }

    public HashMap<String, Estado> getEstadosHistoria(int nroRegistro, String codPlanEstudios) throws SQLException {
        HashMap<String, Estado> estados = new HashMap<>();

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Estado "
                + "WHERE HistoriaAcademica_Estudiante_nroRegistro=? "
                + "AND PlanEstudios_codigo=?");
        ps.setInt(1, nroRegistro);
        ps.setString(2, codPlanEstudios);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String codMateria = rs.getString("Materia_codigo");
            estados.put(codMateria, new Estado(codMateria, nroRegistro + "-" + codPlanEstudios,
                    Estado.Condicion.parse(rs.getString("regularidad")),
                    LocalDate.parse(rs.getDate("fecha") + "")));
        }

        return estados;
    }
}
