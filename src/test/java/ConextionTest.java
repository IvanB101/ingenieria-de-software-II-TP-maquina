
import com.mycompany.tp_maquina_is2.Datos.Conexion;

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
            Conexion.getInstance().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
