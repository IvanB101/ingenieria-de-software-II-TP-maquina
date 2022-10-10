
import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.ExamenDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ginop
 */
public class DAOExamenTest {
    private static final String DB_NAME = "nuevabd3";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
    private static final String DB_USER = "postgres";
    private static final String DB_PWD = "gino";
        
    public static void main(String[]args) {
        Conexion conexion = new Conexion(DB_NAME, DB_URL, DB_USER, DB_PWD);
        try {
            Connection con = conexion.getInstance();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        //create examen sin exp
        ExamenDAOImp examendao = new ExamenDAOImp(conexion);
        LocalDate date = LocalDate.of(2020, 1, 8); //año mes dia
        Examen e1 = new Examen(date,1,9.5f,10,3010820);
        boolean flag = examendao.create(e1);
        System.out.println(flag);
        
    }
}      
