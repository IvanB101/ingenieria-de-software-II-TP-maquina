/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Experiencia;
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
public class ExperienciaDAOImpTest {

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

    private ExperienciaDAOImp experienciaDAOImp;
    private Experiencia experiencia;

    public ExperienciaDAOImpTest() {
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
            
            examenDAOImp.create(examen);

            experienciaDAOImp = new ExperienciaDAOImp(Conexion.getInstance());
            experiencia = new Experiencia(7, 20, 6, codigo);
        } catch (IOException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            failTest();
        }
    }

    @Test
    public void testExperienciaDAOImp() {
        try {
            System.out.println("create");

            experienciaDAOImp.create(experiencia);

            System.out.println("read");

            Experiencia result = experienciaDAOImp.read(codigo);

            assertTrue(experiencia.equals(result));

            System.out.println("update");

            experiencia.setDedicacion(4);
            experiencia.setDias(30);
            experiencia.setDificultad(2);

            experienciaDAOImp.update(codigo, experiencia);

            result = experienciaDAOImp.read(codigo);

            assertTrue(experiencia.equals(result));

            System.out.println("delete");
            experienciaDAOImp.delete(codigo);

            try {
                experienciaDAOImp.read(codigo);

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
