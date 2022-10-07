/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ivanb
 */
public class Conexion {

    // private String DB_NAME;
    private String DB_URL;
    private String DB_USER;
    private String DB_PWD;

    private static Connection conexion;

    public Conexion(String DB_NAME, String DB_URL, String DB_USER, String DB_PWD) {
        // this.DB_NAME = DB_NAME;
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PWD = DB_PWD;
    }

    public Connection getInstance() throws SQLException {
        if (conexion == null) {
            conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

            createTables();
        }
        return conexion;
    }

    private void createTables() throws SQLException {
        // TODO
    }
}
