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
import java.time.LocalDate;
import java.util.ArrayList;

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
    public void create(Examen examen) throws SQLException {
        Connection con = conexion.getConnection();
        String[] datos = examen.getCodHistoriaAcademica().split("-");
        int nroRegistro = Integer.parseInt(datos[0]);

        PreparedStatement ps = con.prepareStatement("INSERT INTO Examen (fecha, nota, "
                + "Materia_codigo, HistoriaAcademica_Estudiante_nroRegistro, PlanEstudios_codigo) VALUES (?,?,?,?,?)");

        ps.setDate(1, Date.valueOf(examen.getFecha()));
        ps.setFloat(2, examen.getNota());
        ps.setString(3, examen.getCodMateria());
        ps.setInt(4, nroRegistro);
        ps.setString(5, datos[1]);

        ps.executeUpdate();
    }

    @Override
    public Examen read(String codigo) throws SQLException {
        Connection con = conexion.getConnection();
        String[] datos = codigo.split("-");
        LocalDate fecha = LocalDate.of(Integer.parseInt(datos[3]), Integer.parseInt(datos[4]),
                Integer.parseInt(datos[5]));
        String codMateria = datos[2];

        PreparedStatement ps = con.prepareStatement("SELECT * "
                + "FROM Examen "
                + "WHERE Materia_codigo=? AND HistoriaAcademica_Estudiante_nroRegistro=? "
                + "AND PlanEstudios_codigo=? AND fecha=?");
        ps.setString(1, codMateria);
        ps.setInt(2, Integer.parseInt(datos[0]));
        ps.setString(3, datos[1]);
        ps.setDate(4, Date.valueOf(fecha));
        ResultSet rs = ps.executeQuery();

        rs.next();
        return new Examen(fecha, rs.getFloat("nota"), codMateria, datos[0] + "-" + datos[1]);
    }

    @Override
    public void update(String codigo, Examen examen) throws SQLException {
        String[] datos = codigo.split("-");
        LocalDate fecha = LocalDate.of(Integer.parseInt(datos[3]), Integer.parseInt(datos[4]),
                Integer.parseInt(datos[5]));
        String codMateria = datos[2];

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("UPDATE Examen "
                + "SET fecha=?, nota=? "
                + "WHERE Materia_codigo=? AND HistoriaAcademica_Estudiante_nroRegistro=? "
                + "AND PlanEstudios_codigo=? AND fecha=?");

        ps.setDate(1, Date.valueOf(examen.getFecha()));
        ps.setFloat(2, examen.getNota());
        ps.setString(3, codMateria);
        ps.setInt(4, Integer.parseInt(datos[0]));
        ps.setString(5, datos[1]);
        ps.setDate(6, Date.valueOf(fecha));

        ps.executeUpdate();
    }

    @Override
    public void delete(String codigo) throws SQLException {
        String[] datos = codigo.split("-");
        LocalDate fecha = LocalDate.of(Integer.parseInt(datos[3]), Integer.parseInt(datos[4]),
                Integer.parseInt(datos[5]));
        String codMateria = datos[2];

        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("DELETE FROM Examen "
                + "WHERE Materia_codigo=? AND HistoriaAcademica_Estudiante_nroRegistro=? "
                + "AND PlanEstudios_codigo=? AND fecha=?");
        ps.setString(1, codMateria);
        ps.setInt(2, Integer.parseInt(datos[0]));
        ps.setString(3, datos[1]);
        ps.setDate(4, Date.valueOf(fecha));

        ps.executeUpdate();
    }
    
    public ArrayList<Examen> getExamenesEstudianteSinExp(int nroRegistro) throws SQLException {
        ArrayList<Examen> examenes = new ArrayList<>();
        
        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT fecha,nota,materia_codigo,planestudios_codigo from examen,experiencia " +
"EXCEPT " + "SELECT fecha,nota,materia_codigo,planestudios_codigo from examen,experiencia where examen_materia_codigo=materia_codigo "
                + "and examen_historiaacademica_estudiante_nroregistro="+nroRegistro
                + " and examen_planestudios_codigo=planestudios_codigo and fecha=examen_fecha ");
        
        ResultSet rs = ps.executeQuery();
        if (!rs.isBeforeFirst()){
            ps=con.prepareStatement("SELECT fecha,nota,materia_codigo,planestudios_codigo from examen");
            rs=ps.executeQuery();
        }
        
        while(rs.next()) {
            Date fecha = rs.getDate("fecha");
            examenes.add(new Examen(LocalDate.parse(fecha.toString()),
                    rs.getFloat("nota"),
                    rs.getString("Materia_codigo"),
                    nroRegistro + "-" + rs.getString("PlanEstudios_codigo")));
        }
        
        return examenes;
    }
    
    public ArrayList<Examen> getExamenesEstudianteConExp(int nroRegistro) throws SQLException {
        ArrayList<Examen> examenes = new ArrayList<>();
        Examen aux;
        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT fecha,nota,materia_codigo,planestudios_codigo,dificultad,dedicacion,dias from examen,experiencia where examen_materia_codigo=materia_codigo "
                + "and examen_historiaacademica_estudiante_nroregistro="+nroRegistro
                + " and examen_planestudios_codigo=planestudios_codigo and fecha=examen_fecha ");
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()) {
            Date fecha = rs.getDate("fecha");
            aux=new Examen(LocalDate.parse(fecha.toString()),
                    rs.getFloat("nota"),
                    rs.getString("Materia_codigo"),
                    nroRegistro + "-" + rs.getString("PlanEstudios_codigo"));
            aux.setExperiencia(new Experiencia(rs.getInt("dificultad"),rs.getInt("dias"),rs.getInt("dedicacion"),nroRegistro+"-"+rs.getString("PlanEstudios_codigo")+"-"+rs.getString("Materia_codigo")+"-"+fecha.toString()));
            examenes.add(aux);
        }
        
        return examenes;
    }

}
