/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ivanb
 */
public class ExamenDAOImpTest {

    private ExamenDAOImp examenDAOImp;
    private Examen examen;

    private HistoriaAcademicaDAOImp historiaAcademicaDAOImp;
    private HistoriaAcademica historiaAcademica;
    private String codPlanEstudios;
    private int nroRegistroEstudiante;

    private PlanEstudiosDAOImp planEstudiosDAOImp;
    private PlanEstudios planEstudios;

    private EstudianteDAOImp estudianteDAOImp;
    private Estudiante estudiante;

    private MateriaDAOImp materiaDAOImp;
    private Materia materia;
    private String codMateria;
    private LocalDate fecha;
    private String codigo;

    public ExamenDAOImpTest() {
        try {
            examenDAOImp = new ExamenDAOImp(Conexion.getInstance());

            codPlanEstudios = "32/12";
            nroRegistroEstudiante = 3119;

            planEstudiosDAOImp = new PlanEstudiosDAOImp(Conexion.getInstance());
            planEstudios = new PlanEstudios(codPlanEstudios, "Licenciatura en Ciencias de la Computacion");

            planEstudiosDAOImp.create(planEstudios);

            estudianteDAOImp = new EstudianteDAOImp(Conexion.getInstance());
            estudiante = new Estudiante(nroRegistroEstudiante, "Ivan", "Brocas", 43092790);

            estudianteDAOImp.create(estudiante);

            historiaAcademicaDAOImp = new HistoriaAcademicaDAOImp(Conexion.getInstance());
            historiaAcademica = new HistoriaAcademica(nroRegistroEstudiante, codPlanEstudios);

            historiaAcademicaDAOImp.create(historiaAcademica);

            codMateria = "CP12";
            fecha = LocalDate.of(2022, 7, 7);

            materiaDAOImp = new MateriaDAOImp(Conexion.getInstance());
            materia = new Materia(codMateria, "Matematica", codPlanEstudios);

            materiaDAOImp.create(materia);

            examen = new Examen(fecha, 7.0f, codMateria, historiaAcademica.getCodigo());
            codigo = examen.getCodigo();
        } catch (IOException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            failTest();
        }
    }

    @Test
    public void testExamenDAOImp() {
        try {
            System.out.println("create");

            examenDAOImp.create(examen);

            System.out.println("read");

            Examen result = examenDAOImp.read(codigo);

            assertTrue(examen.equals(result));

            System.out.println("update");

            examen.setNota(10.0f);
            examen.setFecha(fecha.minusDays(10));

            examenDAOImp.update(codigo, examen);

            result = examenDAOImp.read(examen.getCodigo());

            assertTrue(examen.equals(result));

            System.out.println("delete");
            examenDAOImp.delete(examen.getCodigo());
            materiaDAOImp.delete(codigo, codPlanEstudios);

            try {
                examenDAOImp.read(examen.getCodigo());

                // En caso de que haya podido leer el estudiante no ha sido borrado
                failTest();
            } catch (SQLException e) {
                try {
                    estudianteDAOImp.delete(nroRegistroEstudiante);
                } catch (SQLException e2) {
                }

                try {
                    planEstudiosDAOImp.delete(codPlanEstudios);
                } catch (SQLException e2) {
                }

                assertTrue(e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next."));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            failTest();
        }
    }

    public void failTest() {
        try {
            estudianteDAOImp.delete(nroRegistroEstudiante);
        } catch (SQLException e) {
        }

        try {
            planEstudiosDAOImp.delete(codPlanEstudios);
        } catch (SQLException e) {
        }

        fail();
    }

}
