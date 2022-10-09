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
import javax.swing.JOptionPane;

/**
 *
 * @author ginopaoletti
 */
public class MateriaDAOImp implements MateriaDAOInter {

    Connection con;

    public MateriaDAOImp(Conexion conexion) {
        try {
            con = conexion.getInstance();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public boolean create(Materia materia) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Materia (codigo, nombre, PlanEstudios_codigo,) VALUES (?,?,?)");
            ps.setInt(1, materia.getCodigo());
            ps.setString(2, materia.getNombre());
            ps.setInt(3, materia.getCodPlanDeEstudios());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Materia> read() {
        ArrayList<Materia> materias = new ArrayList();//todas las mat de todos los planes
        ArrayList<Integer> codscorres = new ArrayList(); // todos los codigos de las correlativas
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from Materia");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //me traigo todas las materias
                Materia materia = new Materia(rs.getInt("codigo"), rs.getString("nombre"), rs.getInt("PlanEstudios_codigo"), new ArrayList()); //le paso un array vacio
                materias.add(materia);
            }
            //me traigo las correlativas de cada materia
            for (int i = 0; i < materias.size(); i++) {
                ps = con.prepareStatement("SELECT Correlativa_codigo from Correlativas,Materia WHERE"
                        + materias.get(i).getCodigo() + "= Materia_codigo");
                while (rs.next()) { //obtengo el codigo de la correlativa de una 
                    codscorres.add(rs.getInt("Correlativa_codigo"));
                }
                materias.get(i).setCorrelativas(buscarMat(materias,codscorres));
            }//fin for materia
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
        return materias;
    }

    @Override
    public boolean update(int codigo, Materia materia) {
        if (delete(codigo)) {
            return create(materia);
        }
        return false;
    }

    @Override
    public boolean delete(int codigo) {
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM Materia WHERE codigo=?");
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rs.getString(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay ninguna Materia cargada con el código: " + codigo);
        }

        try {
            ps = con.prepareStatement("DELETE FROM Materia WHERE codigo=?");
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No pudo ser eliminada la materia");
            return false;
        }

        return true;
    }
 public ArrayList<Materia> buscarMat(ArrayList<Materia> materias, ArrayList<Integer> codigos){
     ArrayList<Materia> correlativas = new ArrayList();   
     for(int i=0;i<materias.size();i++){
         for(int j=0;i<codigos.size();j++){
            if(materias.get(i).getCodigo()==codigos.get(j))
                correlativas.add(materias.get(i));
               }
     }
        return correlativas;
    }
}
