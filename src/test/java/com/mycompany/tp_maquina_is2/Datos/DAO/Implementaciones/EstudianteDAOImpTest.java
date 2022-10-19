/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author ivanb
 */
@TestMethodOrder(OrderAnnotation.class)
public class EstudianteDAOImpTest {

    /**
     * Test of create method, of class EstudianteDAOImp.
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

            assertTrue(estudianteDAOImp.create(estudiante));

            System.out.println("read");

            Estudiante result = estudianteDAOImp.read(estudiante.getNroRegistro());

            assertEquals(estudiante.getNroRegistro(), result.getNroRegistro());
            assertEquals(estudiante.getDni(), result.getDni());
            assertEquals(estudiante.getNombre(), result.getNombre());
            assertEquals(estudiante.getApellido(), result.getApellido());
            assertEquals(estudiante.getCodigo(), result.getCodigo());

            System.out.println("update");

            int nroRegistro = estudiante.getNroRegistro();

            estudiante.setNroRegistro(estudiante.getNroRegistro() + 1);
            estudiante.setDni(estudiante.getDni() + 1);
            estudiante.setNombre(estudiante.getNombre() + 1);
            estudiante.setApellido(estudiante.getApellido() + 1);

            if (estudianteDAOImp.update(nroRegistro, estudiante)) {
                result = estudianteDAOImp.read(estudiante.getNroRegistro());
                
                assertEquals(estudiante.getNroRegistro(), result.getNroRegistro());
                assertEquals(estudiante.getDni(), result.getDni());
                assertEquals(estudiante.getNombre(), result.getNombre());
                assertEquals(estudiante.getApellido(), result.getApellido());
                assertEquals(estudiante.getCodigo(), result.getCodigo());
            } else {
                fail();
            }

            System.out.println("delete");

            assertTrue(estudianteDAOImp.delete(estudiante.getNroRegistro())
                    && ((estudianteDAOImp.read(estudiante.getNroRegistro())) == null));
        } catch (IOException e) {
            System.out.println("Error en conexion con base de datos");
            fail();
        }
    }

}
