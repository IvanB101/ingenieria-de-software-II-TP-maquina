
import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.MateriaDAOImp;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.PlanEstudiosDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.NoDocente;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ginop
 */
public class DAOMateriaTest {
    private static final String DB_NAME = "nuevabd4";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
    private static final String DB_USER = "postgres";
    private static final String DB_PWD = "gino";
        
    public static void main(String[]args) {
        Conexion conexion = new Conexion(DB_NAME, DB_URL, DB_USER, DB_PWD);
        
        try {
            Connection con = conexion.getInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }
        MateriaDAOImp matDAOImp = new MateriaDAOImp(conexion);
        
        //cargar un plan de estudio pa referencia
        PlanEstudiosDAOImp DAOplan = new PlanEstudiosDAOImp(conexion);
        PlanEstudios plan = new PlanEstudios("32/12");
        //DAOplan.create(plan);
        
        
        
        //Prueba Create Materia, delete sin correlativas anda
        Materia mat1 = new Materia(10,"ALGEBRA I","32/12",new ArrayList());
        matDAOImp.create(mat1); //añado materia sin correlativas 
 
        ArrayList<Materia> correlativas = new ArrayList();
        correlativas.add(mat1); //añado la alg1 a las correlativas de alg2
        Materia mat2 = new Materia(16,"ALGEBRA II","32/12",correlativas);
        matDAOImp.create(mat2); //añado materia con correlativas
        
        
      
       //Prueba delete con correlativas si yo elimino algebra 1 se elimina como correlativa de alg2
        //matDAOImp.delete(10);
       // matDAOImp.delete(16);
       
       Map<Integer, Materia> mats = new HashMap<Integer, Materia>();
        mats = matDAOImp.read();  
        // Invoke keySet() on the HashMap object to get the keys as a set
        Set<Integer> keys = mats.keySet();
        for ( Integer key : keys ) {
           System.out.println(mats.get(key).toString());
        }
    
       
        
     
    }
}
