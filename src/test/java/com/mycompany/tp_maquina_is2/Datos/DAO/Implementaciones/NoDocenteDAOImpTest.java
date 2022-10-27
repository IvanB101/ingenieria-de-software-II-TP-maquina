/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.NoDocente;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ivanb
 */
public class NoDocenteDAOImpTest {

    NoDocenteDAOImp noDocenteDAOImp;
    NoDocente noDocente;
    int nroLegajo;

    private void init() {
        try {
            nroLegajo = 7229;
            noDocenteDAOImp = new NoDocenteDAOImp(Conexion.getInstance());
            noDocente = new NoDocente(nroLegajo, "Ivan", "Brocas", 43092790);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            failTest();
        }
    }

    /**
     * Test of all methods, of class EstudianteDAOImp.
     */
    @Test
    public void testNoDocenteDAOImp() {
        try {
            init();

            System.out.println("create");

            noDocenteDAOImp.create(noDocente);

            System.out.println("read");

            NoDocente result = noDocenteDAOImp.read(noDocente.getNroLegajo());

            assertTrue(noDocente.equals(result));

            System.out.println("update");

            noDocente.setNroLegajo(noDocente.getNroLegajo() + 1);
            noDocente.setDni(noDocente.getDni() + 1);
            noDocente.setNombre(noDocente.getNombre() + 1);
            noDocente.setApellido(noDocente.getApellido() + 1);

            noDocenteDAOImp.update(nroLegajo, noDocente);

            result = noDocenteDAOImp.read(noDocente.getNroLegajo());

            assertTrue(noDocente.equals(result));

            System.out.println("delete");
            noDocenteDAOImp.delete(noDocente.getNroLegajo());

            try {
                noDocenteDAOImp.read(noDocente.getNroLegajo());

                // En caso de que haya podido leer el estudiante no ha sido borrado
                failTest();
            } catch (SQLException e) {
                assertTrue(e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next."));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            failTest();
        }
    }

    private void failTest() {
        try {
            noDocenteDAOImp.delete(noDocente.getNroLegajo());
        } catch (Exception e) {
        }

        fail();
    }
}
