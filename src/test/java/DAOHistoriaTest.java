
import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.HistoriaAcademicaDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado.Condicion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class DAOHistoriaTest {
    private static final String DB_NAME = "nuevabd2";
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
        
        HistoriaAcademicaDAOImp hisDAO = new HistoriaAcademicaDAOImp(conexion);
        //create
        /*Condicion c1 = null;
        Estado e1 = new Estado(10,3010820,c1.parse("aprobado")); //funciona
        Estado e2 = new Estado(16,3010820,c1.parse("regular"));
        ArrayList<Estado> estaditos = new ArrayList();
        estaditos.add(e1);
        estaditos.add(e2);
        HistoriaAcademica h1 = new HistoriaAcademica("LICENCIATURA EN CIENCIAS DE LA COMPUTACIÓN",3010820,"32/12",new ArrayList(),estaditos);
        hisDAO.create(h1); 
        //delete
        //hisDAO.delete(3010820);
        */
        //read
       
         HashMap<Integer, HistoriaAcademica> historias = hisDAO.read();
         //Invoke keySet() on the HashMap object to get the keys as a set
        Set<Integer> keys = historias.keySet();
        for ( Integer key : keys ) {
           System.out.println(historias.get(key).toString());
        }
        
        
        
 }

}