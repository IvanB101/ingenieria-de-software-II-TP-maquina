/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Interfaces.MesaExamenDAOInter;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.MesaExamen;
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
public class MesaExamenDAOImp implements MesaExamenDAOInter {

    Connection con;

    public MesaExamenDAOImp(Conexion conexion) {
        try {
            con = conexion.getInstance();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public boolean create(MesaExamen mesaExamen) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO MesaExamen (codigo, turno, anio, Materia_codigo) VALUES (?,?,?,?)");

            ps.setInt(1, mesaExamen.getCodigo());
            ps.setInt(2, mesaExamen.getTurno());
            ps.setInt(3, mesaExamen.getAnio());
            ps.setInt(4, mesaExamen.getCodMateria());

            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public ArrayList<MesaExamen> read() {
        ArrayList<Integer> aux = new ArrayList();
        ArrayList<MesaExamen> mesasExamenes = new ArrayList();

        try {
            /*Se realiza un ensamble de las tablas MesaExamen e Inscripciones para que obtener
            los datos de una mesa junto con las incripciones a la misma*/
            PreparedStatement ps = con.prepareStatement("SELECT codigo, turno, anio, Materia_codigo, Estudiante_nroRegistro "
                    + "FROM MesaExamen, Inscripciones "
                    + "WHERE MesaExamen_codigo = codigo");
            ResultSet rs = ps.executeQuery();

            // Inicializacion de la primera mesa
            rs.next();
            MesaExamen mesa = new MesaExamen(
                    rs.getInt("codigo"),
                    rs.getInt("turno"),
                    rs.getInt("anio"),
                    rs.getInt("Materia_codigo"));
            // Inicializacion de la lista de codigos de estudiantes inscriptos a la mesa
            ArrayList<Integer> inscriptos = new ArrayList<>();
            inscriptos.add(rs.getInt("Estudiante_nroRegistro"));

            while (rs.next()) {
                // Cuando cambia el codigo de la mesa sabemos que se pasa a otra mesa
                if (mesa.getCodigo() != rs.getInt("codigo")) {
                    // Se añaden los datos acumulados en las variables declaradas anteriormente
                    mesa.setCodInscriptos(inscriptos);
                    mesasExamenes.add(mesa);

                    // Se cargan los datos correspondientes a la nueva mesa
                    mesa = new MesaExamen(
                            rs.getInt("codigo"),
                            rs.getInt("turno"),
                            rs.getInt("anio"),
                            rs.getInt("Materia_codigo"));

                    // Se reinicia la lista de codigos de inscriptos
                    inscriptos = new ArrayList<>();
                    inscriptos.add(rs.getInt("Estudiante_nroRegistro"));
                } else {
                    // Si no cambio la mesa se acumulan los codigos de inscriptos
                    inscriptos.add(rs.getInt("Estudiante_nroRegistro"));
                }
            }

            // Se agregan los datos correspondientes a la ultima mesa
            mesa.setCodInscriptos(inscriptos);
            mesasExamenes.add(mesa);

            // Se realiza otra consulta para las mesas que no tengan ningun inscripto
            ps = con.prepareStatement("SELECT codigo, turno, anio, Materia_codigo, Estudiante_nroRegistro "
                    + "FROM MesaExamen, Inscripciones "
                    + "WHERE codigo NOT IN "
                    + "(SELECT codigo FROM MesaExamen, Inscripciones WHERE MesaExamen_codigo = codigo)");
            rs = ps.executeQuery();

            while (rs.next()) {
                mesasExamenes.add(new MesaExamen(
                        rs.getInt("codigo"),
                        rs.getInt("turno"),
                        rs.getInt("anio"),
                        rs.getInt("Materia_codigo")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }

        return mesasExamenes;
    }

    @Override
    public boolean update(int codigo, MesaExamen mesaExamen) {
        if (delete(codigo)) {
            return create(mesaExamen);
        }
        return false;
    }

    @Override
    public boolean delete(int codigo) {
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM MesaExamen WHERE codigo=?");
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rs.getString(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay ninguna mesa cargada con el código: " + codigo);
            return false;
        }
        try {
            ps = con.prepareStatement("DELETE FROM MesaExamen WHERE codigo=?");
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo eliminar la mesa de examenes");
            return false;
        }

        return true;
    }

}