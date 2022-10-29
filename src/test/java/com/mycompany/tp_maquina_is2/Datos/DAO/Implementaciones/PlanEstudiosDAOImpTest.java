/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ivanb
 */
public class PlanEstudiosDAOImpTest {

    /**
     * Test of all methods, of class EstudianteDAOImp.
     */
    @Test
    public void testEstudianteDAOImp() {
        try {
            PlanEstudiosDAOImp planEstudiosDAOImp = new PlanEstudiosDAOImp(Conexion.getInstance());
            PlanEstudios planEstudios = new PlanEstudios("32/23", "Ingeniería en Informática");

            System.out.println("create");
            
            planEstudiosDAOImp.create(planEstudios);

            System.out.println("read");
            
            PlanEstudios result = planEstudiosDAOImp.read(planEstudios.getCodigo());

            assertTrue(planEstudios.equals(result));

            System.out.println("update");
            
            String codigo = planEstudios.getCodigo();

            planEstudios.setCodigo("123");
            planEstudios.setPropuesta("Ingeniería en Computación");
            
            planEstudiosDAOImp.update(codigo, planEstudios);

            result = planEstudiosDAOImp.read(planEstudios.getCodigo());
            
            assertTrue(planEstudios.equals(result));
            
            System.out.println("delete");
            
            planEstudiosDAOImp.delete(planEstudios.getCodigo());

            try {
                planEstudiosDAOImp.read(planEstudios.getCodigo());
                
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
