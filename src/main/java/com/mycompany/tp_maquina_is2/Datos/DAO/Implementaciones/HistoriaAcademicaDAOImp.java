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
import java.util.HashMap;
import javax.swing.JOptionPane;

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

        PreparedStatement ps;
        
        String codPlanEstudios = historiaAcademica.getCodPlanDeEstudios();

        // Carga de la historia academica
        ps = con.prepareStatement("INSERT INTO HistoriaAcademica (Estudiante_nroRegistro, "
                + "PlanEstudios_codigo) VALUES (?,?)");

        ps.setInt(1, historiaAcademica.getNroRegEstudiante());
        ps.setString(2, codPlanEstudios);

        ps.executeUpdate();

        ps = con.prepareStatement("INSERT INTO Estado (regularidad, Materia_codigo, "
                + "HistoriaAcademica_Estudiante_nroRegistro,PlanEstudios_codigo) VALUES (?,?,?,?)");
        // Carga de los estados de la historia academica
        for (Estado estado : historiaAcademica.getEstados().values()) {
            ps.setString(1, estado.getCondicion().toString());
            ps.setInt(2, estado.getCodMateria());
            ps.setInt(3, estado.getNroRegistroHistoria());
            ps.setString(4, codPlanEstudios);

            ps.executeUpdate();
        }
    }

    @Override
    public HistoriaAcademica read(int nroRegistro, String codPlanEstudios) throws SQLException {
        try {
            Connection con = conexion.getConnection();

            /* Realiza un ensamble de las tablas HistoriaAcademica y Estado para tener todos los
            estados de una historia academica seguidos en el ResultSet. Ademas, se realiza un ensamble
            con Materia para poder diferenciar dos historias academicas del mismo estudiante en
            distintas carreras*/
            PreparedStatement ps = con.prepareStatement("SELECT propuesta, Estudiante_nroRegistro, historiaacademica.PlanEstudios_codigo, "
                    + "Materia_codigo, regularidad FROM HistoriaAcademica, Estado, Materia "
                    + "WHERE Estudiante_nroRegistro=HistoriaAcademica_Estudiante_nroRegistro AND "
                    + "HistoriaAcademica.PlanEstudios_codigo = Materia.PlanEstudios_codigo AND "
                    + "Materia_codigo = codigo");
            ResultSet rs = ps.executeQuery();

            // Si no hay ninguna historia cargada se retorna el HashMap vacio
            if (!rs.next()) {
                return new HashMap<>();
            }
            // Se iniciaizan las primera historia academica y estado del ResultSet
            HashMap<Integer, Estado> estados = new HashMap<>();
            ArrayList<Integer> codMateriasEstados = new ArrayList<>();

            int codMateria = rs.getInt("materia_codigo");

            codMateriasEstados.add(codMateria);

            estados.put(codMateria, new Estado(codMateria,
                    rs.getInt("Estudiante_nroRegistro"),
                    Condicion.parse(rs.getString("regularidad"))));
            HistoriaAcademica historia = new HistoriaAcademica(
                    rs.getString("propuesta"),
                    rs.getInt("Estudiante_nroRegistro"),
                    rs.getString("PlanEstudios_codigo"),
                    new ArrayList<>(),
                    new HashMap<>());

            while (rs.next()) {
                /* Cuando cambia el codigo del plan de estudios o el numero de registro del estudiante se
                sabe que se ha pasado a otra historia*/
                if ((rs.getInt("Estudiante_nroRegistro") != historia.getNroRegEstudiante())
                        || !(rs.getString("PlanEstudios_codigo").equals(historia.getCodPlanDeEstudios()))) {
                    // Se cargan los datos de las historia en el ArrayList a retornar
                    historia.setCodMateriasEstados(codMateriasEstados);

                    historia.setEstados(estados);
                    historiasAcademicas.put(rs.getInt("Estudiante_nroRegistro"), historia);

                    // Se carga la nueva historia academica
                    historia = new HistoriaAcademica(
                            rs.getString("propuesta"),
                            rs.getInt("Estudiante_nroRegistro"),
                            rs.getString("PlanEstudios_codigo"),
                            new ArrayList<>(),
                            new HashMap<>());

                    codMateria = rs.getInt("materia_codigo");

                    codMateriasEstados = new ArrayList<>();
                    codMateriasEstados.add(codMateria);

                    estados = new HashMap<>();
                    estados.put(codMateria, new Estado(codMateria,
                            rs.getInt("Estudiante_nroRegistro"),
                            Condicion.parse(rs.getString("regularidad"))));
                } else {
                    codMateria = rs.getInt("materia_codigo");

                    codMateriasEstados.add(codMateria);

                    estados.put(codMateria, new Estado(codMateria,
                            rs.getInt("Estudiante_nroRegistro"),
                            Condicion.parse(rs.getString("regularidad"))));
                }
            }

            // Se carga la ultima historia academica con sus respectivos estados
            historia.setCodMateriasEstados(codMateriasEstados);

            historia.setEstados(estados);
            historiasAcademicas.put(historia.getNroRegEstudiante(), historia);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }

        return historiasAcademicas;
    }

    @Override
    public void update(int nroRegistro, String codPlanEstudios, HistoriaAcademica historiaAcademica) throws SQLException {
        if (delete(nroRegistro)) {
            return create(historiaAcademica);
        }

        return false;
    }

    @Override
    public void delete(int nroRegistro, String codPlanEstudios) throws SQLException {
        PreparedStatement ps;

        // Control existencia de la historia academica con código a eliminar
        try {
            Connection con = conexion.getConnection();

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
            Connection con = conexion.getConnection();

            ps = con.prepareStatement("DELETE FROM HistoriaAcademica WHERE Estudiante_nroRegistro=?");
            ps.setInt(1, nroRegistro);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

            return false;
        }

        return true;
    }

}
