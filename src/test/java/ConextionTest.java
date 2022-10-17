
import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Managers.EstudianteManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.ExamenManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.HistoriaAcademicaManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.MateriaManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.PlanEstudiosManager;
import com.mycompany.tp_maquina_is2.Logica.Util.Excel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    public static void main(String[] args) {

        try {
            /*
            EstudianteManager.init(conexion);
            ExamenManager.init(conexion);
            HistoriaAcademicaManager.init(conexion);
            MateriaManager.init(conexion);
            PlanEstudiosManager.init(conexion);
             */
            // Excel.cargarPlanEstudios(new File("LIC. 32_12.xls"));
            // Excel.cargarHistoriaAcademica(3010820, "32/12", new File("historia-gino.xls"));
            //System.out.println(HistoriaAcademicaManager.eliminar(3010820));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
