/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
        Statement query = conexion.createStatement();

        query.execute("CREATE TABLE IF NOT EXISTS Persona("
                + "codigo varchar(50) NOT NULL, "
                + "dni int NOT NULL, "
                + "nombre varchar(50) NOT NULL, "
                + "apellido varchar(50) NOT NULL,"
                + "PRIMARY KEY (codigo))");
        
        query.execute("CREATE TABLE IF NOT EXISTS NoDocente("
                + "nroLegajo int NOT NULL, "
                + "Persona_codigo int NOT NULL, "
                + "FOREIGN KEY (Persona_codigo) REFERENCES Persona(codigo) ON DELETE CASCADE, "
                + "PRIMARY KEY (nroLegajo))");
        
        query.execute("CREATE TABLE IF NOT EXISTS Estudiante("
                + "nroRegistro int NOT NULL, "
                + "Persona_codigo int NOT NULL, "
                + "FOREIGN KEY (Persona_codigo) REFERENCES Persona(codigo) ON DELETE CASCADE, "
                + "PRIMARY KEY (nroRegistro))");
        
        query.execute("CREATE TABLE IF NOT EXISTS PlanEstudios("
                + "codigo int NOT NULL, "
                + "PRIMARY KEY (codigo))");
        
        query.execute("CREATE TABLE IF NOT EXISTS Materia("
                + "codigo int NOT NULL, "
                + "nombre varchar(50) NOT NULL, "
                + "PlanEstudios_codigo int NOT NULL, "
                + "FOREIGN KEY (PlanEstudios_codigo) REFERENCES PlanEstudios(codigo), "
                + "PRIMARY KEY (codigo))");
        
        query.execute("CREATE TABLE IF NOT EXISTS HistoriaAcademica("
                + "propuesta varchar(50) NOT NULL, "
                + "Estudiante_nroRegistro int UNIQUE NOT NULL, "
                + "PlanEstudios_codigo int NOT NULL, "
                + "FOREIGN KEY (Estudiante_nroRegistro) REFERENCES Estudiante(nroRegistro), "
                + "FOREIGN KEY (PlanEstudios_codigo) REFERENCES PlanEstudios(codigo), "
                + "PRIMARY KEY (Estudiante_nroRegistro, PlanEstudios_codigo))");
        
        query.execute("CREATE TABLE IF NOT EXISTS Correlativas("
                + "Correlativa_codigo int NOT NULL, "
                + "Materia_codigo int NOT NULL, "
                + "FOREIGN KEY (Materia_codigo) REFERENCES Materia(codigo) ON DELETE CASCADE, "
                + "FOREIGN KEY (Correlativa_codigo) REFERENCES Materia(codigo), "
                + "PRIMARY KEY (Materia_codigo, Correlativa_codigo))");
        
        query.execute("DO $$ "
                        + "BEGIN "
                            + "IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'regularidadenum') THEN "
                                + "CREATE TYPE regularidadenum as ENUM('libre', 'cursando', 'regular', 'aprobado'); "
                            + "END IF; "
                        + "END$$; ");
        
        query.execute("CREATE TABLE IF NOT EXISTS Estado("
                + "regularidad regularidadENUM NOT NULL, "
                + "Materia_codigo int NOT NULL, "
                + "HistoriaAcademica_Estudiante_nroRegistro int NOT NULL, "
                + "FOREIGN KEY (Materia_codigo) REFERENCES Materia(codigo), "
                + "FOREIGN KEY (HistoriaAcademica_Estudiante_nroRegistro) REFERENCES HistoriaAcademica(Estudiante_nroRegistro) ON DELETE CASCADE, "
                + "PRIMARY KEY (Materia_codigo, HistoriaAcademica_Estudiante_nroRegistro))");
        
        query.execute("CREATE TABLE IF NOT EXISTS Examen("
                + "codigo int NOT NULL, "
                + "fecha date NOT NULL, "
                + "turno int NOT NULL, "
                + "nota float NOT NULL, "
                + "Materia_codigo int NOT NULL, "
                + "HistoriaAcademica_Estudiante_nroRegistro int NOT NULL, "
                + "FOREIGN KEY (Materia_codigo) REFERENCES Materia(codigo), "
                + "FOREIGN KEY (HistoriaAcademica_Estudiante_nroRegistro) REFERENCES HistoriaAcademica(Estudiante_nroRegistro), "
                + "PRIMARY KEY (codigo))");
        
        query.execute("CREATE TABLE IF NOT EXISTS Experiencia("
                + "Examen_codigo int NOT NULL, "
                + "dificultad int NOT NULL, "
                + "dedicacion int NOT NULL, "
                + "dias int NOT NULL, "
                + "FOREIGN KEY (Examen_codigo) REFERENCES Examen(codigo), ON DELETE CASCADE"
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
