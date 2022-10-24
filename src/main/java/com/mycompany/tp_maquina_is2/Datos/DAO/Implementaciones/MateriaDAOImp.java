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
    public void create(Materia materia) throws SQLException {
        Connection con = conexion.getConnection();

        // Carga de los datos de la materia
        PreparedStatement ps = con.prepareStatement("INSERT INTO Materia (codigo, nombre, PlanEstudios_codigo) VALUES (?,?,?)");
        ps.setString(1, materia.getCodigo());
        ps.setString(2, materia.getNombre());
        ps.setString(3, materia.getCodPlanDeEstudios());

        ps.executeUpdate();

        // Carga de las correlativas en caso de que las tenga
        for (String codCorrelativa : materia.getCorrelativas()) {
            ps = con.prepareStatement("INSERT INTO Correlativas (correlativa_codigo, materia_codigo, PlanEstudios_codigo) VALUES (?,?,?)");
            ps.setString(1, codCorrelativa);
            ps.setString(2, materia.getCodigo());
            ps.setString(3, materia.getCodPlanDeEstudios());

            ps.executeUpdate();
        }
    }

    @Override
    public Materia read(String codigo, String codPlanEstudios) throws SQLException {
        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * from Materia "
                + "WHERE codigo=? AND PlanEstudios_codigo");

        ResultSet rs = ps.executeQuery();

        rs.next();
        String nombre = rs.getString("nombre");

        // Carga de lso codigos de las correlativas de la materia
        ps = con.prepareStatement("SELECT Correlativa_codigo from Correlativas"
                + "WHERE Materia_codigo=? AND PlanEstudios_codigo=?");
        ps.setString(1, codigo);
        ps.setString(2, codPlanEstudios);

        rs = ps.executeQuery();

        ArrayList<String> correlativas = new ArrayList<>();
        while (rs.next()) {
            correlativas.add(rs.getString("Correlativa_codigo"));
        }

        // Carga de los codigos dependientes de la materia
        ps = con.prepareStatement("SELECT Correlativa_codigo from Correlativas"
                + "WHERE Correlativa_codigo=? AND PlanEstudios_codigo=?");
        ps.setString(1, codigo);
        ps.setString(2, codPlanEstudios);

        rs = ps.executeQuery();

        ArrayList<String> dependientes = new ArrayList<>();
        while (rs.next()) {
            dependientes.add(rs.getString("Materia_codigo"));
        }

        return new Materia(codigo, nombre, codPlanEstudios, correlativas, dependientes);
    }

    /**
     * Solamente se puede cambiar el nombre de la materia mediante este metodo,
     * el cambio en otros campos produciria problemas con las llaves foraneas en
     * la tabla Correlativas
     *
     * @param codigo de la materia que se desea modificar
     * @param codPlanEstudios de la materia que se desea modificar
     * @param materia objeto con la informacion nueva de la materia
     * @throws SQLException
     */
    @Override
    public void update(String codigo, String codPlanEstudios, Materia materia) throws SQLException {
        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("UPDATE Materia "
                + "SET nombre=? "
                + "WHERE codigo=? AND PlanEstudios_codigo=?");
        ps.setString(1, materia.getCodigo());
        ps.setString(2, codigo);
        ps.setString(3, codPlanEstudios);

        ps.executeUpdate();
    }

    @Override
    public void delete(String codigo, String codPlanEstudios) throws SQLException {

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("DELETE FROM Materia "
                + "WHERE codigo=? AND PlanEstudios_codigo=?");
        ps.setString(1, codigo);
        ps.setString(2, codPlanEstudios);

        ps.executeUpdate();
    }
}
