
import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.ExamenDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Experiencia;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ginop
 */
public class DAOExamenTest {
    private static final String DB_NAME = "nuevabd4";
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
        ExamenDAOImp examendao = new ExamenDAOImp(conexion);
        /*create examen sin exp el codigo es: 8-2020-10-3010820
        LocalDate date = LocalDate.of(2020, 1, 8); //año mes dia
        Examen e1 = new Examen(date,1,9.5f,10,3010820);
        boolean flag = examendao.create(e1);
        System.out.println(flag);
        */
        /*crear examen con experiencia 
        LocalDate date = LocalDate.of(2021,2,9);
        Examen e2 = new Examen(date,2,8,16,3010820);
        Experiencia ex2 = new Experiencia(5,30,8,e2.getCodigo());
        e2.setExperiencia(ex2);
        examendao.create(e2); */
        //read
        HashMap<String, Examen> examenes = examendao.read();
       
        // Invoke keySet() on the HashMap object to get the keys as a set
        Set<String> keys = examenes.keySet();
        for ( String key : keys ) {
           System.out.println(examenes.get(key).toString());
        }
        
           
     
}
}      
