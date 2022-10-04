
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import com.mycompany.tp_maquina_is2.Logica.Util.Excel;
import java.io.File;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ivanb
 */
public class ExcelTest {
    public static void main(String[]args) {
        File file = new File("historia_academica.xls");
        
        Estudiante estudiante = new Estudiante();
        
        Excel.leerHistoriaAcademica(estudiante, file);
    }
}
