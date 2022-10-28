/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ivanb
 */
public class HistoriaAcademicaDAOImpTest {

    private HistoriaAcademicaDAOImp historiaAcademicaDAOImp;
    private HistoriaAcademica historiaAcademica;
    private String codPlanEstudios;
    private int nroRegistroEstudiante;

    private PlanEstudiosDAOImp planDeEstudiosDAOImp;
    private PlanEstudios planEstudios;

    private EstudianteDAOImp estudianteDAOImp;
    private Estudiante estudiante;

    public HistoriaAcademicaDAOImpTest() {
        try {
            codPlanEstudios = "32/12";
            nroRegistroEstudiante = 3119;

            historiaAcademicaDAOImp = new HistoriaAcademicaDAOImp(Conexion.getInstance());
            historiaAcademica = new HistoriaAcademica(nroRegistroEstudiante, codPlanEstudios);

            planDeEstudiosDAOImp = new PlanEstudiosDAOImp(Conexion.getInstance());
            planEstudios = new PlanEstudios(codPlanEstudios, "Licenciatura en Ciencias de la Computacion");

            planDeEstudiosDAOImp.create(planEstudios);

            estudianteDAOImp = new EstudianteDAOImp(Conexion.getInstance());
            estudiante = new Estudiante(nroRegistroEstudiante, "Ivan", "Brocas", 43092790);

            estudianteDAOImp.create(estudiante);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            failTest();
        }
    }

    /**
     * Test of all methods, of class HistoriaAcademicaDAOImp.
     */
    @Test
    public void testHistoriaAcademicaDAOImp() {
        try {
            System.out.println("create");

            historiaAcademicaDAOImp.create(historiaAcademica);

            System.out.println("read");

            HistoriaAcademica result = historiaAcademicaDAOImp.read(nroRegistroEstudiante, codPlanEstudios);

            assertTrue(historiaAcademica.equals(result));

            System.out.println("delete");
            historiaAcademicaDAOImp.delete(nroRegistroEstudiante, codPlanEstudios);

            try {
                historiaAcademicaDAOImp.read(nroRegistroEstudiante, codPlanEstudios);

                // En caso de que haya podido leer el estudiante no ha sido borrado
                failTest();
            } catch (SQLException e) {
                planDeEstudiosDAOImp.delete(codPlanEstudios);

                estudianteDAOImp.delete(nroRegistroEstudiante);

                assertTrue(e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next."));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            failTest();
        }
    }

    public void failTest() {
        try {
            planDeEstudiosDAOImp.delete(codPlanEstudios);

            estudianteDAOImp.delete(nroRegistroEstudiante);
        } catch (SQLException e) {
        }

        fail();
    }

}
