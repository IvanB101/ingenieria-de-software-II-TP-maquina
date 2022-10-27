/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces.ExperienciaDAOInter;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Experiencia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author ivanb
 */
public class ExperienciaDAOImp implements ExperienciaDAOInter {

    Conexion conexion;

    public ExperienciaDAOImp(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(Experiencia experiencia) throws SQLException {
        String[] datos = experiencia.getCodExamen().split("-");
        int nroRegistro = Integer.parseInt(datos[0]);
        LocalDate fecha = LocalDate.of(Integer.parseInt(datos[3]), Integer.parseInt(datos[4]),
                Integer.parseInt(datos[5]));
        String codMateria = datos[2];

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("INSERT INTO Experiencia (Examen_fecha, dificultad, dedicacion, dias, "
                + "Examen_Materia_codigo, Examen_HistoriaAcademica_Estudiante_nroRegistro,"
                + "Examen_PlanEstudios_codigo) VALUES (?,?,?,?,?,?,?)");
        ps.setDate(1, Date.valueOf(fecha));
        ps.setInt(2, experiencia.getDificultad());
        ps.setInt(3, experiencia.getDedicacion());
        ps.setInt(4, experiencia.getDias());
        ps.setString(5, codMateria);
        ps.setInt(6, nroRegistro);
        ps.setString(7, datos[1]);

        ps.executeUpdate();
    }

    @Override
    public Experiencia read(String codigo) throws SQLException {
        String[] datos = codigo.split("-");
        int nroRegistro = Integer.parseInt(datos[0]);
        LocalDate fecha = LocalDate.of(Integer.parseInt(datos[3]), Integer.parseInt(datos[4]),
                Integer.parseInt(datos[5]));
        String codMateria = datos[2];

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Experiencia"
                + "WHERE Materia_codigo=? AND HistoriaAcademica_Estudiante_nroRegistro=? "
                + "AND PlanEstudios_codigo=? AND Examen_fecha=?");
        ps.setString(1, codMateria);
        ps.setInt(2, nroRegistro);
        ps.setString(3, datos[1]);
        ps.setDate(4, Date.valueOf(fecha));
        ResultSet rs = ps.executeQuery();

        return new Experiencia(
                rs.getInt("dificultad"),
                rs.getInt("dias"),
                rs.getInt("dedicacion"),
                codigo);
    }

    @Override
    public void update(String codigo, Experiencia experiencia) throws SQLException {
        String[] datos = codigo.split("-");
        LocalDate fecha = LocalDate.of(Integer.parseInt(datos[3]), Integer.parseInt(datos[4]),
                Integer.parseInt(datos[5]));
        String codMateria = datos[2];

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("UPDATE Experiencia "
                + "SET dificultad=?, dias=?, dedicacion=?"
                + "WHERE Materia_codigo=? AND HistoriaAcademica_Estudiante_nroRegistro=? "
                + "AND PlanEstudios_codigo=? AND Examen_fecha=?");

        ps.setInt(1, experiencia.getDificultad());
        ps.setInt(2, experiencia.getDias());
        ps.setInt(3, experiencia.getDedicacion());
        ps.setString(4, codMateria);
        ps.setInt(5, Integer.parseInt(datos[0]));
        ps.setString(6, datos[1]);
        ps.setDate(7, Date.valueOf(fecha));
        
        ps.executeUpdate();
    }

    @Override
    public void delete(String codigo) throws SQLException {
        String[] datos = codigo.split("-");
        LocalDate fecha = LocalDate.of(Integer.parseInt(datos[3]), Integer.parseInt(datos[4]),
                Integer.parseInt(datos[5]));
        String codMateria = datos[2];

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("DELETE FROM Experiencia"
                + "WHERE Materia_codigo=? AND HistoriaAcademica_Estudiante_nroRegistro=? "
                + "AND PlanEstudios_codigo=? AND Examen_fecha=?");
        ps.setString(1, codMateria);
        ps.setInt(2, Integer.parseInt(datos[0]));
        ps.setString(3, datos[1]);
        ps.setDate(4, Date.valueOf(fecha));

        ps.executeUpdate();
    }

}
