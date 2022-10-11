
import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.EstudianteDAOImp;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.NoDocenteDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.NoDocente;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ginop
 */
public class DAOPersonas {
    private static final String DB_NAME = "finalDB";
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
    
    
    //DAOS
    EstudianteDAOImp estDAOImp = new EstudianteDAOImp(conexion);
    NoDocenteDAOImp nodocDAOImp = new NoDocenteDAOImp(conexion);
    //transfers
    Estudiante e1 = new Estudiante(3010820,"Gino","Paoletti",43810354);
    Estudiante e2 = new Estudiante(4128100,"Juancito","Sanchez",44222111);
    NoDocente nd1 = new NoDocente(6541217,"Hernancito","Bernardis",32842391);
    NoDocente nd2 = new NoDocente(8260284,"Alejandro","Grosso",62124550);
    Estudiante e3Eliminar = new Estudiante(6313777,"Horacio","Thompson",83510334);
    Estudiante e4Update = new Estudiante(111111,"ungrande","Thompson",83510334);
    Estudiante e5Update = new Estudiante(222222,"ungrande2","Thompson2",83510334);
    NoDocente nd3Eliminar = new NoDocente(1234567,"corina","is2",77821269);
    NoDocente nd4Update = new NoDocente(8260284,"unhdp","grosso",62124550);
    //llamada a metodos
    
    estDAOImp.create(e1);
    estDAOImp.create(e2);
    nodocDAOImp.create(nd1);
    nodocDAOImp.create(nd2);
    /*
    Elimianr estudiante
    estDAOImp.create(e3Eliminar);
    estDAOImp.delete(6313777);
    
    Update Estudiante
    estDAOImp.update(111111,e5Update);
    
    Eliminar Docente
    nodocDAOImp.create(nd3Eliminar);
    nodocDAOImp.delete(1234567);
    
    Update Docente
    nodocDAOImp.update(8260284,nd4Update);
    
    READ Estudiante por consola
    
    Map<Integer, Estudiante> estudiantes = new HashMap<Integer, Estudiante>();
    estudiantes = estDAOImp.read();
    for (Map.Entry<Integer, Estudiante> entry : estudiantes.entrySet()) {
    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
    }
    */
    Map<Integer, NoDocente> nodocentes = new HashMap<Integer, NoDocente>();
    nodocentes = nodocDAOImp.read();
    for (Map.Entry<Integer,NoDocente > entry : nodocentes.entrySet()) {
    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
    }
    }
}