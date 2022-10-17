/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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

    private String DB_NAME;
    private String DB_URL;
    private String DB_USER;
    private String DB_PWD;

    private static Conexion conexion;

    private Conexion() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src\\main\\java\\com\\mycompany\\tp_maquina_is2\\Datos\\conexion.txt")));

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
        // Creacion de las tablas si no han sido creadas
        createTables();

        return DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
    }

    private void createTables() throws SQLException {
        Statement query = conexion.getConnection().createStatement();

        query.execute("CREATE TABLE IF NOT EXISTS Persona("
                + "codigo varchar(50) NOT NULL, "
                + "dni int NOT NULL, "
                + "nombre varchar(50) NOT NULL, "
                + "apellido varchar(50) NOT NULL,"
                + "PRIMARY KEY (codigo))");

        query.execute("CREATE TABLE IF NOT EXISTS NoDocente("
                + "nroLegajo int NOT NULL, "
                + "Persona_codigo varchar(50) NOT NULL, "
                + "FOREIGN KEY (Persona_codigo) REFERENCES Persona(codigo) ON DELETE CASCADE, "
                + "PRIMARY KEY (nroLegajo))");

        query.execute("CREATE TABLE IF NOT EXISTS Estudiante("
                + "nroRegistro int NOT NULL, "
                + "Persona_codigo varchar(50) NOT NULL, "
                + "FOREIGN KEY (Persona_codigo) REFERENCES Persona(codigo) ON DELETE CASCADE, "
                + "PRIMARY KEY (nroRegistro))");

        query.execute("CREATE TABLE IF NOT EXISTS PlanEstudios("
                + "codigo varchar(50) NOT NULL, "
                + "PRIMARY KEY (codigo))");

        query.execute("CREATE TABLE IF NOT EXISTS Materia("
                + "codigo int NOT NULL, "
                + "nombre varchar(100) NOT NULL, "
                + "PlanEstudios_codigo varchar(50) NOT NULL, "
                + "FOREIGN KEY (PlanEstudios_codigo) REFERENCES PlanEstudios(codigo), "
                + "PRIMARY KEY (codigo))");

        query.execute("CREATE TABLE IF NOT EXISTS HistoriaAcademica("
                + "propuesta varchar(50) NOT NULL, "
                + "Estudiante_nroRegistro int UNIQUE NOT NULL, "
                + "PlanEstudios_codigo varchar(50) NOT NULL, "
                + "FOREIGN KEY (Estudiante_nroRegistro) REFERENCES Estudiante(nroRegistro), "
                + "FOREIGN KEY (PlanEstudios_codigo) REFERENCES PlanEstudios(codigo), "
                + "PRIMARY KEY (Estudiante_nroRegistro, PlanEstudios_codigo))");

        query.execute("CREATE TABLE IF NOT EXISTS Correlativas("
                + "Correlativa_codigo int NOT NULL, "
                + "Materia_codigo int NOT NULL, "
                + "FOREIGN KEY (Materia_codigo) REFERENCES Materia(codigo) ON DELETE CASCADE, "
                + "FOREIGN KEY (Correlativa_codigo) REFERENCES Materia(codigo) ON DELETE CASCADE, "
                + "PRIMARY KEY (Materia_codigo, Correlativa_codigo))");

        query.execute("CREATE TABLE IF NOT EXISTS Estado("
                + "regularidad varchar(50) NOT NULL, "
                + "Materia_codigo int NOT NULL, "
                + "HistoriaAcademica_Estudiante_nroRegistro int NOT NULL, "
                + "FOREIGN KEY (Materia_codigo) REFERENCES Materia(codigo), "
                + "FOREIGN KEY (HistoriaAcademica_Estudiante_nroRegistro) REFERENCES HistoriaAcademica(Estudiante_nroRegistro) ON DELETE CASCADE, "
                + "PRIMARY KEY (Materia_codigo, HistoriaAcademica_Estudiante_nroRegistro))");

        query.execute("CREATE TABLE IF NOT EXISTS Examen("
                + "codigo varchar(100) NOT NULL, "
                + "fecha date NOT NULL, "
                + "turno int NOT NULL, "
                + "nota float NOT NULL, "
                + "Materia_codigo int NOT NULL, "
                + "HistoriaAcademica_Estudiante_nroRegistro int NOT NULL, "
                + "FOREIGN KEY (Materia_codigo) REFERENCES Materia(codigo), "
                + "FOREIGN KEY (HistoriaAcademica_Estudiante_nroRegistro) REFERENCES HistoriaAcademica(Estudiante_nroRegistro) ON DELETE CASCADE, "
                + "PRIMARY KEY (codigo))");

        query.execute("CREATE TABLE IF NOT EXISTS Experiencia("
                + "Examen_codigo varchar(100) NOT NULL, "
                + "dificultad int NOT NULL, "
                + "dedicacion int NOT NULL, "
                + "dias int NOT NULL, "
                + "FOREIGN KEY (Examen_codigo) REFERENCES Examen(codigo) ON DELETE NO ACTION,"
                + "PRIMARY KEY (Examen_codigo))");

        query.execute("CREATE TABLE IF NOT EXISTS MesaExamen("
                + "codigo int NOT NULL, "
                + "turno int NOT NULL, "
                + "anio int NOT NULL, "
                + "Materia_codigo int NOT NULL, "
                + "FOREIGN KEY (Materia_codigo) REFERENCES Materia(codigo), "
                + "PRIMARY KEY (codigo))");

        query.execute("CREATE TABLE IF NOT EXISTS Inscripcion("
                + "Estudiante_nroRegistro int NOT NULL, "
                + "MesaExamen_codigo int NOT NULL, "
                + "FOREIGN KEY (Estudiante_nroRegistro) REFERENCES Estudiante(nroRegistro) ON DELETE CASCADE, "
                + "FOREIGN KEY (MesaExamen_codigo) REFERENCES MesaExamen(codigo) ON DELETE CASCADE, "
                + "PRIMARY KEY (Estudiante_nroRegistro, MesaExamen_codigo))");
    }
}
