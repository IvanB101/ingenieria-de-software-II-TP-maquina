
import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Managers.EstudianteManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.ExamenManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.HistoriaAcademicaManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.PlanEstudiosManager;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Experiencia;
import com.mycompany.tp_maquina_is2.Logica.Util.ArchivosManager;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
            EstudianteManager.agregar(new Estudiante(3026220, "Juan", "Sanchez", 44330220));
            EstudianteManager.agregar(new Estudiante(3010820, "Gino", "Paoletti", 44075067));
            //EstudianteManager.agregar(new Estudiante(3026224, "Franco", "Sarubbi", 44330220));
            //EstudianteManager.agregar(new Estudiante(3026223, "Luu", "loyola", 44330220));
            //EstudianteManager.agregar(new Estudiante(3026222, "Pol", "santiago", 44330220));
            //EstudianteManager.agregar(new Estudiante(3026221, "Bianca", "Calderoni", 44330220));
            Estudiante[] estudiantes = {
                new Estudiante(1234567, "Tebiense", "Reyes", 44178000),
                new Estudiante(1234568, "Hernan", "un grande", 50075067),
                new Estudiante(1234569, "Bad", "Bunny", 44111178),
                new Estudiante(1234510, "HTML", "es un leng prog", 11115067),
                new Estudiante(1234511, "LOUTA", "Soria", 61123567),
                new Estudiante(1234512, "es que", "es ordenada", 44099999),
                new Estudiante(1234513, "Norita", "REYE", 41111122),
                new Estudiante(1234514, "fernandito", "keyshan", 60128989)
            };
            
            String codPlan = "32/12";

            for (Estudiante estudiante : estudiantes) {
                EstudianteManager.agregar(estudiante);

                ArchivosManager.cargarHistoriaAcademica(estudiante.getNroRegistro(), codPlan,
                        new File("datos_prueba\\historia-gino.xls"));

                ArrayList<Examen> examenes = ExamenManager.examenesEstudianteSinExp(estudiante.getNroRegistro(), codPlan);

                for (Examen examen : examenes) {
                    ExamenManager.crearExperiencia(new Experiencia((int) (Math.random() * 10),
                            (int) (Math.random() * 45 + 15),
                            (int) (Math.random() * 10),
                            examen.getCodigo()));
                }
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
