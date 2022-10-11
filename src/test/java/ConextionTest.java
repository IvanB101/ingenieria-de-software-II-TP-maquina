
import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Util.Excel;
import java.io.File;
import java.sql.Connection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ivanb
 */
public class ConextionTest {

    private static final String DB_NAME = "TP_Maquina_IS2";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
    private static final String DB_USER = "postgres";
    private static final String DB_PWD = "admin";

    public static void main(String[] args) {
        Conexion conexion = new Conexion(DB_NAME, DB_URL, DB_USER, DB_PWD);
        /*
        try {
            Connection con = conexion.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        try {
            Excel.cargarPlanEstudios(new File("LIC. 32_12.xls"));
            
            // Excel.cargarHistoriaAcademica(0, "32/12", new File("historia_academica.xls"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
