
import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Managers.EstudianteManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.ExamenManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.HistoriaAcademicaManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.PlanEstudiosManager;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import com.mycompany.tp_maquina_is2.Logica.Util.ArchivosManager;
import java.io.File;
import java.io.IOException;

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
            Conexion conn = Conexion.getInstance();

            // Inicializacion de Managers
            ExamenManager.init(conn);
            EstudianteManager.init(conn);
            HistoriaAcademicaManager.init(conn);
            PlanEstudiosManager.init(conn);

            EstudianteManager.agregar(new Estudiante(3010820, "Gino", "Paoletti", 44075067));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
