/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces.HistoriaAcademicaDAOInter;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public class HistoriaAcademicaDAOImp implements HistoriaAcademicaDAOInter {

    Conexion conexion;

    public HistoriaAcademicaDAOImp(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(HistoriaAcademica historiaAcademica) throws SQLException {
        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("INSERT INTO HistoriaAcademica (Estudiante_nroRegistro, "
                + "PlanEstudios_codigo) VALUES (?,?)");
        ps.setInt(1, historiaAcademica.getNroRegEstudiante());
        ps.setString(2, historiaAcademica.getCodPlanDeEstudios());
        
        ps.executeUpdate();
    }

    @Override
    public HistoriaAcademica read(int nroRegistro, String codPlanEstudios) throws SQLException {
        Connection con = conexion.getConnection();
        
        PreparedStatement ps = con.prepareStatement("SELECT * FROM HistoriaAcademica "
                + "WHERE Estudiante_nroRegistro=? AND PlanEstudios_codigo=?");
        ps.setInt(1, nroRegistro);
        ps.setString(2, codPlanEstudios);
        
        ResultSet rs = ps.executeQuery();
        
        rs.next();

        return new HistoriaAcademica(rs.getInt("Estudiante_nroRegistro"), codPlanEstudios);
    }

    @Override
    public void update(int nroRegistro, String codPlanEstudios, HistoriaAcademica historiaAcademica) throws SQLException {
        // El metodo no hace nada porque la tabla HistoriaAcademica solamente cuenta con llaves foraneas
    }

    @Override
    public void delete(int nroRegistro, String codPlanEstudios) throws SQLException {
        Connection con = conexion.getConnection();
        
        PreparedStatement ps = con.prepareStatement("DELETE FROM HistoriaAcademica "
                + "WHERE Estudiante_nroRegistro=? AND PlanEstudios_codigo=?");
        
        ps.setInt(1, nroRegistro);
        ps.setString(2, codPlanEstudios);
        
        ps.executeUpdate();
    }

}
