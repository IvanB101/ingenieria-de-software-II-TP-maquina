/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Estado.Condicion;
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
public class EstadoDAOImpTest {

    private EstadoDAOImp estadoDAOImp;
    private Estado estado;

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
    private String codigo;

    public EstadoDAOImpTest() {
        try {
            estadoDAOImp = new EstadoDAOImp(Conexion.getInstance());

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

            materiaDAOImp = new MateriaDAOImp(Conexion.getInstance());
            materia = new Materia(codMateria, "Matematica", codPlanEstudios);

            materiaDAOImp.create(materia);

            estado = new Estado(codMateria, nroRegistroEstudiante + "-" + codPlanEstudios, Condicion.regular, LocalDate.of(2022, 7, 7));
            codigo = estado.getCodigo();
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
            failTest();
        }
    }

    @Test
    public void testEstadoDAOImp() {
        try {
            System.out.println("create");

            estadoDAOImp.create(estado);

            System.out.println("read");

            Estado result = estadoDAOImp.read(codigo);
            
            assertTrue(estado.equals(result));

            System.out.println("update");

            estado.setCondicion(Condicion.aprobado);
            estado.setFecha(LocalDate.of(2022, 8, 8));

            estadoDAOImp.update(codigo, estado);

            result = estadoDAOImp.read(codigo);

            assertTrue(estado.equals(result));

            System.out.println("delete");
            estadoDAOImp.delete(codigo);

            try {
                estadoDAOImp.read(estado.getCodigo());

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
            planEstudiosDAOImp.delete(codPlanEstudios);
            estudianteDAOImp.delete(nroRegistroEstudiante);
        } catch (SQLException e) {
        }

        fail();
    }

}
