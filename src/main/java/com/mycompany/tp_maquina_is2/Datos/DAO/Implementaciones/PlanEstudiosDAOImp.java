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
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author ivanb
 */
public class PlanEstudiosDAOImp implements PlanEstudiosDAOInter {

    Connection con;

    public PlanEstudiosDAOImp(Conexion conexion) {
        try {
            con = conexion.getInstance();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public boolean create(PlanEstudios planEstudios) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO PlanEstudios (codigo) VALUES (?)");

            ps.setString(1, planEstudios.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public HashMap<String, PlanEstudios> read() {
        HashMap<String, PlanEstudios> planesDeEstudios = new HashMap<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM PlanEstudios");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                planesDeEstudios.put(rs.getString("codigo"), new PlanEstudios(rs.getString("codigo")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }

        return planesDeEstudios;
    }

    @Override
    public boolean update(String codigo, PlanEstudios planEstudios) {
        if (delete(codigo)) {
            return create(planEstudios);
        }
        
        return false;
    }

    @Override
    public boolean delete(String codigo) {
        PreparedStatement ps;
        
        try {
            // Comprobacion existencia del plan de estudios a eliminar
            ps = con.prepareStatement("SELECT * FROM PlanEstudios WHERE codigo=?");
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rs.getString(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay ningun plan cargado con el código: " + codigo);
            return false;
        }
        
        try {
            ps = con.prepareStatement("DELETE FROM PlanEstudios WHERE codigo=?");
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo eliminar el plan de estudios");
            return false;
        }

        return true;
    }

}
