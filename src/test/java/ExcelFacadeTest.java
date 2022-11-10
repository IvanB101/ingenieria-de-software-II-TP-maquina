
import com.mycompany.tp_maquina_is2.Logica.Util.ExcelFacade;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ivanb
 */
public class ExcelFacadeTest {

    public static void main(String[] args) {
        try {
            File file = new File("D:\\Documentos\\GitHub\\TP_Maquina_IS2\\datos_prueba\\historia-gino.xls");

            ExcelFacade excel = new ExcelFacade(file);
            
            excel.avanzarHasta("Actividad", 0);
            
            while(excel.hasNext()) {
                System.out.println(Arrays.toString(excel.leerFila(2)));
            }
        } catch (IOException e) {
            
        } catch (NoSuchElementException e) {
            System.out.println("Hola");
        }

    }
}
