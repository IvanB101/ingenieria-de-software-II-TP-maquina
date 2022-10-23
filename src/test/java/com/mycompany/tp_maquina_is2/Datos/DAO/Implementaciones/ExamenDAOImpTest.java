/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ivanb
 */
public class ExamenDAOImpTest {

    /**
     * Test of create method, of class ExamenDAOImp.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Examen examen = null;
        ExamenDAOImp instance = null;
        boolean expResult = false;
        boolean result = instance.create(examen);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class ExamenDAOImp.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        ExamenDAOImp instance = null;
        HashMap<String, Examen> expResult = null;
        HashMap<String, Examen> result = instance.read();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ExamenDAOImp.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        String codigo = "";
        Examen examen = null;
        ExamenDAOImp instance = null;
        boolean expResult = false;
        boolean result = instance.update(codigo, examen);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ExamenDAOImp.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String codigo = "";
        ExamenDAOImp instance = null;
        boolean expResult = false;
        boolean result = instance.delete(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
