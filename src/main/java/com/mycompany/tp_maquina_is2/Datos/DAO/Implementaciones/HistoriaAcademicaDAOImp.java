/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces.HistoriaAcademicaDAOInter;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado.Condicion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
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
public class HistoriaAcademicaDAOImp implements HistoriaAcademicaDAOInter {

    Connection con;

    public HistoriaAcademicaDAOImp(Conexion conexion) {
        try {
            con = conexion.getInstance();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public boolean create(HistoriaAcademica historiaAcademica) {
        try {
            PreparedStatement ps;

            ps = con.prepareStatement("INSERT INTO HistoriaAcademica (propuesta, Estudiante_nroRegistro, "
                    + "PlanEstudios_codigo) VALUES (?,?,?)");

            ps.setString(1, historiaAcademica.getPropuesta());
            ps.setInt(2, historiaAcademica.getNroRegEstudiante());
            ps.setInt(3, historiaAcademica.getCodPlanDeEstudios());

            ps.executeUpdate();

            ps = con.prepareStatement("INSERT INTO Estado (regularidad, Materia_codigo, "
                    + "HistoriaAcademica_Estudiante_nroRegistro) VALUES (?,?,?)");
            // Carga de los estados de la historia academica
            for (Estado estado : historiaAcademica.getEstados()) {
                ps.setString(1, estado.getCondicion().toString());
                ps.setInt(0, estado.getCodMateria());
                ps.setInt(0, estado.getCodHistoriaAcademica());

                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public ArrayList<HistoriaAcademica> read() {
        ArrayList<HistoriaAcademica> historiasAcademicas = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT propuesta, Estudiante_nroRegistro, PlanEstudios_codigo, "
                    + "Materia_codigo, regularidad FROM HistoriaAcademica, Estado, Materia "
                    + "WHERE Estudiante_nroRegistro=HistoriaAcademica_Estudiante_nroRegistro AND "
                    + "HistoriaAcademica.PlanEstudios_codigo = Materia.PlanEstudios_codigo AND "
                    + "Materia_codigo = codigo");
            ResultSet rs = ps.executeQuery();

            rs.next();
            ArrayList<Estado> estados = new ArrayList<>();
            estados.add(new Estado(rs.getInt("codigo"),
                    rs.getInt("Estudiante_nroRegistro"),
                    Condicion.parse(rs.getString("regularidad"))));
            HistoriaAcademica historia = new HistoriaAcademica(
                    rs.getString("propuesta"),
                    rs.getInt("Estudiante_nroRegistro"),
                    rs.getInt("PlanEstudios_codigo"),
                    new ArrayList<>(),
                    new ArrayList<>());

            while (rs.next()) {
                if (rs.getInt("Estudiante_nroRegistro") != historia.getNroRegEstudiante()
                        || rs.getInt("PlanEstudios_codigo") != historia.getCodPlanDeEstudios()) {
                    historia.setEstados(estados);
                    historiasAcademicas.add(historia);

                    historia = new HistoriaAcademica(
                            rs.getString("propuesta"),
                            rs.getInt("Estudiante_nroRegistro"),
                            rs.getInt("PlanEstudios_codigo"),
                            new ArrayList<>(),
                            new ArrayList<>());

                    estados = new ArrayList<>();
                    estados.add(new Estado(rs.getInt("codigo"),
                            rs.getInt("Estudiante_nroRegistro"),
                            Condicion.parse(rs.getString("regularidad"))));
                } else {
                    estados.add(new Estado(rs.getInt("codigo"),
                            rs.getInt("Estudiante_nroRegistro"),
                            Condicion.parse(rs.getString("regularidad"))));
                }
            }

            historia.setEstados(estados);
            historiasAcademicas.add(historia);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }

        return historiasAcademicas;
    }

    @Override
    public boolean update(int nroRegistro, HistoriaAcademica historiaAcademica) {
        if (delete(nroRegistro)) {
            return create(historiaAcademica);
        }

        return false;
    }

    @Override
    public boolean delete(int nroRegistro) {
        PreparedStatement ps;

        // Control existencia de la historia academica con código a eliminar
        try {
            ps = con.prepareStatement("SELECT * FROM HistoriaAcademica WHERE Estudiante_nroRegistro=?");
            ps.setInt(1, nroRegistro);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rs.getString(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay ninguna historia academica cargado asociada"
                    + " al estudiante con número de registro: " + nroRegistro);
        }

        try {
            ps = con.prepareStatement("DELETE FROM HistoriaAcademica WHERE Estudiante_nroRegistro=?");
            ps.setInt(1, nroRegistro);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "El examen no puede ser eliminado porque está referenciado en Se_Consume");

            return false;
        }

        return true;
    }

}
