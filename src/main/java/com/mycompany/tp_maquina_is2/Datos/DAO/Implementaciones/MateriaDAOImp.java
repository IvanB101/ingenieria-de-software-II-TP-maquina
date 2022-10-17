/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces.MateriaDAOInter;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author ginopaoletti
 */
public class MateriaDAOImp implements MateriaDAOInter {

    Conexion conexion;

    public MateriaDAOImp(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean create(Materia materia) {
        try {
            Connection con = conexion.getConnection();

            // Carga de los datos de la materia
            PreparedStatement ps = con.prepareStatement("INSERT INTO Materia (codigo, nombre, PlanEstudios_codigo) VALUES (?,?,?)");
            ps.setString(1, materia.getCodigo());
            ps.setString(2, materia.getNombre());
            ps.setString(3, materia.getCodPlanDeEstudios());

            ps.executeUpdate();

            // Carga de las correlativas en caso de que las tenga
            if (!materia.getCorrelativas().isEmpty()) {
                for (String codCorrelativa : materia.getCorrelativas()) {
                    ps = con.prepareStatement("INSERT INTO Correlativas (correlativa_codigo, materia_codigo) VALUES (?,?)");
                    ps.setString(1, codCorrelativa);
                    ps.setString(2, materia.getCodigo());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public HashMap<String, Materia> read() {
        HashMap<String, Materia> materias = new HashMap();//todas las mat de todos los planes
        ArrayList<String> codCorrelativas = new ArrayList(); // todos los codigos de las correlativas

        try {
            Connection con = conexion.getConnection();

            // Carga de las materias sin sus correlativas
            PreparedStatement ps = con.prepareStatement("SELECT * from Materia");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                materias.put(rs.getString("codigo") + "-" + rs.getString("PlanEstudios_codigo"),
                        new Materia(
                                rs.getString("codigo"),
                                rs.getString("nombre"),
                                rs.getString("PlanEstudios_codigo"),
                                new ArrayList()));
            }

            // Carga de las correlativas de cada materia
            ps = con.prepareStatement("SELECT * from Correlativas");
            rs = ps.executeQuery();
            while (rs.next()) {
                materias.get(rs.getString("Materia_codigo")).getCorrelativas().add(
                        rs.getString("Correlativa_codigo"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
        return materias;
    }

    @Override
    public boolean update(String codigo, String codPlanEstudios, Materia materia) {
        if (delete(codigo, codPlanEstudios)) {
            return create(materia);
        }

        return false;
    }

    @Override
    public boolean delete(String codigo, String codPlanEstudios) {
        PreparedStatement ps;
        try {
            Connection con = conexion.getConnection();

            ps = con.prepareStatement("SELECT * FROM Materia WHERE codigo=? AND PlanEstudios_codigo=?");
            ps.setString(1, codigo);
            ps.setString(2, codPlanEstudios);

            ResultSet rs = ps.executeQuery();
            rs.next();
            rs.getString(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay ninguna Materia cargada con el código: " + codigo);
            return false;
        }

        try {
            Connection con = conexion.getConnection();

            ps = con.prepareStatement("SELECT * FROM Materia WHERE codigo=? AND PlanEstudios_codigo=?");
            ps.setString(1, codigo);
            ps.setString(2, codPlanEstudios);

            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }

        return true;
    }
}
