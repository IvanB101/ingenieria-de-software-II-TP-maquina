/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estudiante;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.MesaExamen;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ivanb
 */
public class MesaExamenDAOImpTest {

    private PlanEstudiosDAOImp planEstudiosDAOImp;
    private PlanEstudios planEstudios;
    private String codPlanEstudios;

    private EstudianteDAOImp estudianteDAOImp;
    private Estudiante estudiante;
    private int nroRegistroEstudiante;

    private MateriaDAOImp materiaDAOImp;
    private Materia materia;
    private String codMateria;

    private MesaExamenDAOImp mesaExamenDAOImp;
    private MesaExamen mesaExamen;
    private int turno;
    private int anio;

    public MesaExamenDAOImpTest() {
        try {
            codPlanEstudios = "32/12";
            nroRegistroEstudiante = 3119;

            planEstudiosDAOImp = new PlanEstudiosDAOImp(Conexion.getInstance());
            planEstudios = new PlanEstudios(codPlanEstudios, "Licenciatura en Ciencias de la Computacion");

            planEstudiosDAOImp.create(planEstudios);

            estudianteDAOImp = new EstudianteDAOImp(Conexion.getInstance());
            estudiante = new Estudiante(nroRegistroEstudiante, "Ivan", "Brocas", 43092790);

            estudianteDAOImp.create(estudiante);

            codMateria = "CP12";

            materiaDAOImp = new MateriaDAOImp(Conexion.getInstance());
            materia = new Materia(codMateria, "Matematica", codPlanEstudios);
            
            materiaDAOImp.create(materia);

            anio = 2022;
            turno = 3;

            mesaExamenDAOImp = new MesaExamenDAOImp(Conexion.getInstance());
            mesaExamen = new MesaExamen(turno, anio, new ArrayList<>(), codMateria, codPlanEstudios);
        } catch (IOException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            failTest();
        }

    }

    @Test
    public void testMesaExamenDAOImp() {
        try {
            System.out.println("create");
            
            mesaExamenDAOImp.create(mesaExamen);

            System.out.println("read");

            MesaExamen result = mesaExamenDAOImp.read(mesaExamen.getCodigo());

            assertTrue(mesaExamen.equals(result));

            System.out.println("delete");
            mesaExamenDAOImp.delete(mesaExamen.getCodigo());

            try {
                mesaExamenDAOImp.read(mesaExamen.getCodigo());

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
