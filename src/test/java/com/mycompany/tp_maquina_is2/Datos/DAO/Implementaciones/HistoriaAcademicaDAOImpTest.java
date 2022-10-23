/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones;

import com.mycompany.tp_maquina_is2.Logica.Transferencia.HistoriaAcademica;
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
public class HistoriaAcademicaDAOImpTest {
    
    public HistoriaAcademicaDAOImpTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of create method, of class HistoriaAcademicaDAOImp.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        HistoriaAcademica historiaAcademica = null;
        HistoriaAcademicaDAOImp instance = null;
        boolean expResult = false;
        boolean result = instance.create(historiaAcademica);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class HistoriaAcademicaDAOImp.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        HistoriaAcademicaDAOImp instance = null;
        HashMap<Integer, HistoriaAcademica> expResult = null;
        HashMap<Integer, HistoriaAcademica> result = instance.read();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class HistoriaAcademicaDAOImp.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        int nroRegistro = 0;
        HistoriaAcademica historiaAcademica = null;
        HistoriaAcademicaDAOImp instance = null;
        boolean expResult = false;
        boolean result = instance.update(nroRegistro, historiaAcademica);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class HistoriaAcademicaDAOImp.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        int nroRegistro = 0;
        HistoriaAcademicaDAOImp instance = null;
        boolean expResult = false;
        boolean result = instance.delete(nroRegistro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
