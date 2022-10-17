/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces.ExamenDAOInter;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Experiencia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author ivanb
 */
public class ExamenDAOImp implements ExamenDAOInter {

    Conexion conexion;

    public ExamenDAOImp(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean create(Examen examen) {
        try {
            Connection con = conexion.getConnection();
            
            PreparedStatement ps = con.prepareStatement("INSERT INTO Examen (codigo, fecha, "
                    + "turno, nota, Materia_codigo, HistoriaAcademica_Estudiante_nroRegistro) VALUES (?,?,?,?,?,?)");

            ps.setString(1, examen.getCodigo());
            ps.setDate(2, Date.valueOf(examen.getFecha()));
            ps.setInt(3, examen.getTurno());
            ps.setFloat(4, examen.getNota());
            ps.setInt(5, examen.getCodMateria());
            ps.setInt(6, examen.getNroRegitroEstudiante());

            Experiencia experiencia = examen.getExperiencia();
            
            ps.executeUpdate();
            if (experiencia == null) {
                return true;
            }
            
            ps = con.prepareStatement("INSERT INTO Experiencia (Examen_codigo, dificultad, dedicacion, dias) VALUES (?,?,?,?)");

            ps.setString(1, experiencia.getCodExamen());
            ps.setInt(2, experiencia.getDificultad());
            ps.setInt(3, experiencia.getDedicacion());
            ps.setInt(4, experiencia.getDias());

            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public HashMap<String, Examen> read() {
        HashMap<String, Examen> examenes = new HashMap<>();
        try {
            Connection con = conexion.getConnection();
            
            PreparedStatement ps = con.prepareStatement("SELECT codigo, fecha, turno, nota, Materia_codigo,HistoriaAcademica_Estudiante_nroRegistro, dificultad, dedicacion, dias "
                    + "FROM Examen, Experiencia "
                    + "WHERE codigo = Examen_codigo");
            ResultSet rs = ps.executeQuery();

            // Agregado de examenes que poseen experiencia
            while (rs.next()) {
                Examen examen = new Examen(
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("turno"),
                        rs.getFloat("nota"),
                        rs.getInt("Materia_codigo"),
                        rs.getInt("HistoriaAcademica_Estudiante_nroRegistro"));
                examen.setExperiencia(new Experiencia(
                        rs.getInt("dificultad"),
                        rs.getInt("dias"),
                        rs.getInt("dedicacion"),
                        rs.getString("codigo")));
                examenes.put(rs.getString("codigo"), examen);
            }
            
            ps = con.prepareStatement("SELECT codigo, fecha, turno, nota, Materia_codigo,HistoriaAcademica_Estudiante_nroRegistro "
                    + "FROM Examen "
                    + "EXCEPT "
                    + "SELECT codigo, fecha, turno, nota, Materia_codigo,HistoriaAcademica_Estudiante_nroRegistro "
                    + "FROM Examen,Experiencia WHERE codigo = Examen_codigo");
            rs = ps.executeQuery();
            
            // Agregado de examenes que no poseen experiencia
            while (rs.next()) {
                examenes.put(rs.getString("codigo"), new Examen(
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("turno"),
                        rs.getFloat("nota"),
                        rs.getInt("Materia_codigo"),
                        rs.getInt("HistoriaAcademica_Estudiante_nroRegistro")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }

        return examenes;
    }

    @Override
    public boolean update(String codigo, Examen examen) {
        if(delete(codigo)) {
            return create(examen);
        }
        
        return false;
    }
    
    @Override
    /**
    * Elimina el examen con el codigo dado de la base de datos, en el caso de que el examen
    * posea una experiencia asociada, esta tambien sera eliminada
    * @param codigo correspondiente al codigo del examen a eliminar
    * @return boolean correspondiente al exito de la operacion
    */
    public boolean delete(String codigo) {
        PreparedStatement ps;
        
        // Control existencia del examen con código a eliminar
        try {
            Connection con = conexion.getConnection();
            
            ps = con.prepareStatement("SELECT * FROM Examen WHERE codigo=?");
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rs.getString(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay ningún examen cargado con el código: " + codigo);
        }

        try {
            Connection con = conexion.getConnection();
            
            /* Tambien se borrara la experiencia correspondiente al examen ya que posee la opcion
            ON DELETE CASCADE */
            ps = con.prepareStatement("DELETE FROM Examen WHERE codigo=?");
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "El examen no puede ser eliminado porque está referenciado en Se_Consume");
            return false;
        }

        return true;
    }

}
