
import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.EstudianteDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;

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
            EstudianteDAOImp estudianteDAOImp = new EstudianteDAOImp(Conexion.getInstance());
            Estudiante estudiante = new Estudiante(
                    7229,
                    "Ivan",
                    "Brocas",
                    43092790);

            estudianteDAOImp.create(estudiante);
            estudianteDAOImp.create(estudiante);

        } catch (Exception e) {
            e.printStackTrace();
            if(e.getMessage().contains("llave duplicada")) {
                System.out.println("Estoy aca");
            }
        }
    }
}
