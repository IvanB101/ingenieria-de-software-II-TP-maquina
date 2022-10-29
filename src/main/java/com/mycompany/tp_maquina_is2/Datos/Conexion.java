/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ivanb
 */
public class Conexion {

    private final String DB_NAME;
    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PWD;

    private static Conexion conexion;

    private Conexion() throws IOException {
        // Archivo con los datos referentes a la conexion
        BufferedReader reader = new BufferedReader(new FileReader(new File(
                "src\\main\\java\\com\\mycompany\\tp_maquina_is2\\Datos\\conexion.txt")));

        this.DB_NAME = reader.readLine();
        this.DB_URL = reader.readLine() + DB_NAME;
        this.DB_USER = reader.readLine();
        this.DB_PWD = reader.readLine();
    }

    public static Conexion getInstance() throws IOException {
        if (conexion == null) {
            conexion = new Conexion();
        }

        return conexion;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
    }

    /**
     * Inicializa la base de datos a la que se esta conectando
     * @throws SQLException 
     */
    public void init() throws SQLException {
        try {
            Statement query = conexion.getConnection().createStatement();

            String tablas = "", line;
            BufferedReader reader = new BufferedReader(new FileReader(new File(
                    "src\\main\\java\\com\\mycompany\\tp_maquina_is2\\Datos\\tablas.sql")));

            while ((line = reader.readLine()) != null) {
                tablas += line;
            }
            
            query.execute(tablas);
        } catch (IOException e) {
            System.out.println("Problema en la lectura del archivo con las tablas");
        }
    }
}
