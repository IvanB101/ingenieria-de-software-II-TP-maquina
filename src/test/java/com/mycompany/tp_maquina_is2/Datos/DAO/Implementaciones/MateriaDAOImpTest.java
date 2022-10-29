/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
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
public class MateriaDAOImpTest {

    String codPlanEstudios;

    // Carga plan de estudios por llave foranea
    PlanEstudiosDAOImp planEstudiosDAOImp;
    PlanEstudios planEstudios;

    MateriaDAOImp materiaDAOImp;

    String codigo;

    Materia materia, correlativa, dependiente;

    private void init() {
        try {
            codPlanEstudios = "32/23";

            // Carga plan de estudios por llave foranea
            planEstudiosDAOImp = new PlanEstudiosDAOImp(Conexion.getInstance());
            planEstudios = new PlanEstudios(codPlanEstudios, "Ingeniería en Informática");

            planEstudiosDAOImp.create(planEstudios);

            materiaDAOImp = new MateriaDAOImp(Conexion.getInstance());

            codigo = "123";

            ArrayList<String> dependientes = new ArrayList<>(), correlativas = new ArrayList<>(),
                    dependientesM = new ArrayList<>(), correlativasM = new ArrayList<>();
            dependientes.add(codigo);
            correlativas.add(codigo);
            dependientesM.add(codigo + 2);
            correlativasM.add(codigo + 1);
            materia = new Materia(codigo, "Matematica", codPlanEstudios, correlativasM, dependientesM);
            correlativa = new Materia(codigo + 1, "Matematica 1", codPlanEstudios, new ArrayList<>(), dependientes);
            dependiente = new Materia(codigo + 2, "Matematica 2", codPlanEstudios, correlativas, new ArrayList<>());

        } catch (IOException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            failTest();
        }

    }

    /**
     * Test of all methods, of class EstudianteDAOImp.
     */
    @Test
    public void testMateriaDAOImp() {
        init();

        try {

            System.out.println("create");

            materiaDAOImp.create(correlativa);
            materiaDAOImp.create(materia);
            materiaDAOImp.create(dependiente);

            System.out.println("read");

            Materia result = materiaDAOImp.read(materia.getCodigo(), materia.getCodPlanDeEstudios());

            assertTrue(materia.equals(result));

            System.out.println("update");

            materia.setCodigo("CP" + codigo);
            materia.setNombre("Optativa");

            materiaDAOImp.update(codigo, codPlanEstudios, materia);

            result = materiaDAOImp.read(materia.getCodigo(), materia.getCodPlanDeEstudios());

            assertTrue(materia.equals(result));

            System.out.println("delete");
            materiaDAOImp.delete(codigo, codPlanEstudios);

            try {
                materiaDAOImp.read(codigo, codPlanEstudios);

                // En caso de que haya podido leer el estudiante no ha sido borrado
                failTest();
            } catch (SQLException e) {
                planEstudiosDAOImp.delete(planEstudios.getCodigo());

                materiaDAOImp.delete(codigo, codPlanEstudios);

                assertTrue(e.getMessage().equals("ResultSet not positioned properly, perhaps you need to call next."));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            failTest();
        }
    }

    @Test
    public void testmateriasFromPlanEstudios() {
        try {
            System.out.println("materiasFromPlanEstudios");
            
            materiaDAOImp.materiasFromPlanEstudios(codPlanEstudios);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            failTest();
        }
    }

    private void failTest() {
        try {
            planEstudiosDAOImp.delete(planEstudios.getCodigo());

            materiaDAOImp.delete(materia.getCodigo(), codPlanEstudios);

            materiaDAOImp.delete(correlativa.getCodigo(), codPlanEstudios);

            materiaDAOImp.delete(dependiente.getCodigo(), codPlanEstudios);
        } catch (SQLException e) {
        }

        fail();
    }

}
