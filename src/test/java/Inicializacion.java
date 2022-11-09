
import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Managers.EstudianteManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.ExamenManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.HistoriaAcademicaManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.PlanEstudiosManager;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import java.io.IOException;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ivanb
 */
public class Inicializacion {

    public static void main(String[] args) throws ManagementException {

        try {
            Conexion conn = Conexion.getInstance();
            
            // Incializacion de la base de datos
            conn.init();

            // Inicializacion de Managers
            ExamenManager.init(conn);
            EstudianteManager.init(conn);
            HistoriaAcademicaManager.init(conn);
            PlanEstudiosManager.init(conn);

            // Carga de datos de prueba
            //EstudianteManager.agregar(new Estudiante(3010820, "Gino", "Paoletti", 44075067));
            EstudianteManager.agregar(new Estudiante(3026220, "Juan", "Sanchez", 44330220));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
