/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author ivanb
 */
@TestMethodOrder(OrderAnnotation.class)
public class EstudianteDAOImpTest {

    /**
     * Test of all methods, of class EstudianteDAOImp.
     */
    @Test
    public void testEstudianteDAOImp() {
        try {
            EstudianteDAOImp estudianteDAOImp = new EstudianteDAOImp(Conexion.getInstance());
            Estudiante estudiante = new Estudiante(
                    7229,
                    "Ivan",
                    "Brocas",
                    43092790);

            System.out.println("create");

            estudianteDAOImp.create(estudiante);

            System.out.println("read");

            Estudiante result = estudianteDAOImp.read(estudiante.getNroRegistro());

            assertTrue(estudiante.equals(result));

            System.out.println("update");

            int nroRegistro = estudiante.getNroRegistro();

            estudiante.setNroRegistro(estudiante.getNroRegistro() + 1);
            estudiante.setDni(estudiante.getDni() + 1);
            estudiante.setNombre(estudiante.getNombre() + 1);
            estudiante.setApellido(estudiante.getApellido() + 1);

            estudianteDAOImp.update(nroRegistro, estudiante);
            
            result = estudianteDAOImp.read(estudiante.getNroRegistro());

            assertTrue(estudiante.equals(result));

            System.out.println("delete");
            estudianteDAOImp.delete(estudiante.getNroRegistro());

            try {
                estudianteDAOImp.read(estudiante.getNroRegistro());
                
                // En caso de que haya podido leer el estudiante no ha sido borrado
                fail();
            } catch (SQLException e) {
                assertTrue(e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next."));
            }
        } catch (IOException e) {
            System.out.println("Error en conexion con base de datos");
            fail();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            fail();
        }
    }

}
