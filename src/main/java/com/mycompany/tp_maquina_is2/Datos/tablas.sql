CREATE TABLE IF NOT EXISTS Persona(
codigo varchar(30) NOT NULL, 
dni int NOT NULL, 
nombre varchar(50) NOT NULL, 
apellido varchar(50) NOT NULL,
PRIMARY KEY (codigo));

CREATE TABLE IF NOT EXISTS NoDocente(
nroLegajo int NOT NULL, 
Persona_codigo varchar(50) NOT NULL, 
FOREIGN KEY (Persona_codigo) REFERENCES Persona(codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
PRIMARY KEY (nroLegajo));

CREATE TABLE IF NOT EXISTS Estudiante(
nroRegistro int NOT NULL, 
Persona_codigo varchar(50) NOT NULL, 
FOREIGN KEY (Persona_codigo) REFERENCES Persona(codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
PRIMARY KEY (nroRegistro));

CREATE TABLE IF NOT EXISTS PlanEstudios(
propuesta varchar(50) NOT NULL,
codigo varchar(50) NOT NULL, 
PRIMARY KEY (codigo));

CREATE TABLE IF NOT EXISTS Materia(
codigo varchar(20) NOT NULL, 
nombre varchar(100) NOT NULL, 
PlanEstudios_codigo varchar(50) NOT NULL, 
FOREIGN KEY (PlanEstudios_codigo) REFERENCES PlanEstudios(codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
PRIMARY KEY (codigo, PlanEstudios_codigo));

CREATE TABLE IF NOT EXISTS HistoriaAcademica( 
Estudiante_nroRegistro int NOT NULL, 
PlanEstudios_codigo varchar(30) NOT NULL, 
FOREIGN KEY (Estudiante_nroRegistro) REFERENCES Estudiante(nroRegistro) ON DELETE CASCADE ON UPDATE CASCADE, 
FOREIGN KEY (PlanEstudios_codigo) REFERENCES PlanEstudios(codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
PRIMARY KEY (Estudiante_nroRegistro, PlanEstudios_codigo));

CREATE TABLE IF NOT EXISTS Correlativas(
Materia_codigo varchar(20) NOT NULL,
Correlativa_codigo varchar(20) NOT NULL, 
PlanEstudios_codigo varchar(50) NOT NULL,
FOREIGN KEY (Materia_codigo, PlanEstudios_codigo) 
		REFERENCES Materia(codigo, PlanEstudios_codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
FOREIGN KEY (Correlativa_codigo, PlanEstudios_codigo) 
		REFERENCES Materia(codigo, PlanEstudios_codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
FOREIGN KEY (PlanEstudios_codigo) REFERENCES PlanEstudios(codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
PRIMARY KEY (Materia_codigo, Correlativa_codigo, PlanEstudios_codigo));

CREATE TABLE IF NOT EXISTS Estado(
regularidad varchar(20) NOT NULL, 
fecha date NOT NULL,
Materia_codigo varchar(20) NOT NULL, 
HistoriaAcademica_Estudiante_nroRegistro int NOT NULL, 
PlanEstudios_codigo varchar(30) NOT NULL,
FOREIGN KEY (Materia_codigo, PlanEstudios_codigo) 
		REFERENCES Materia(codigo, PlanEstudios_codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
FOREIGN KEY (HistoriaAcademica_Estudiante_nroRegistro, PlanEstudios_codigo)
    REFERENCES HistoriaAcademica(Estudiante_nroRegistro, PlanEstudios_codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
PRIMARY KEY (Materia_codigo, HistoriaAcademica_Estudiante_nroRegistro, PlanEstudios_codigo));

CREATE TABLE IF NOT EXISTS Examen(
fecha date NOT NULL, 
nota float NOT NULL, 
Materia_codigo varchar(20) NOT NULL, 
HistoriaAcademica_Estudiante_nroRegistro int NOT NULL, 
PlanEstudios_codigo varchar(30) NOT NULL,
FOREIGN KEY (Materia_codigo, PlanEstudios_codigo) 
	REFERENCES Materia(codigo, PlanEstudios_codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
FOREIGN KEY (HistoriaAcademica_Estudiante_nroRegistro, PlanEstudios_codigo) 
    REFERENCES HistoriaAcademica(Estudiante_nroRegistro, PlanEstudios_codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
PRIMARY KEY (Materia_codigo, HistoriaAcademica_Estudiante_nroRegistro, PlanEstudios_codigo, fecha));

CREATE TABLE IF NOT EXISTS Experiencia(
dificultad int NOT NULL, 
dedicacion int NOT NULL, 
dias int NOT NULL,
Examen_Materia_codigo varchar(20) NOT NULL, 
Examen_HistoriaAcademica_Estudiante_nroRegistro int NOT NULL, 
Examen_PlanEstudios_codigo varchar(30) NOT NULL,
Examen_fecha date NOT NULL, 
FOREIGN KEY (Examen_Materia_codigo, Examen_HistoriaAcademica_Estudiante_nroRegistro, Examen_PlanEstudios_codigo, Examen_fecha) 
    REFERENCES Examen(Materia_codigo, HistoriaAcademica_Estudiante_nroRegistro, PlanEstudios_codigo, fecha) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY (Examen_Materia_codigo, Examen_HistoriaAcademica_Estudiante_nroRegistro, Examen_PlanEstudios_codigo));

CREATE TABLE IF NOT EXISTS MesaExamen( 
turno int NOT NULL, 
anio int NOT NULL, 
Materia_codigo varchar(20) NOT NULL, 
Materia_PlanEstudios_codigo varchar(30) NOT NULL,
FOREIGN KEY (Materia_codigo, Materia_PlanEstudios_codigo) 
	REFERENCES Materia(codigo, PlanEstudios_codigo) ON DELETE CASCADE ON UPDATE CASCADE, 
PRIMARY KEY (Materia_codigo, Materia_PlanEstudios_codigo, anio, turno));

CREATE TABLE IF NOT EXISTS Inscripcion(
Estudiante_nroRegistro int NOT NULL, 
MesaExamen_turno int NOT NULL, 
MesaExamen_anio int NOT NULL, 
MesaExamen_Materia_codigo varchar(20) NOT NULL, 
MesaExamen_Materia_PlanEstudios_codigo varchar(30) NOT NULL,
FOREIGN KEY (Estudiante_nroRegistro) REFERENCES Estudiante(nroRegistro) ON DELETE CASCADE ON UPDATE CASCADE, 
FOREIGN KEY (MesaExamen_Materia_codigo, MesaExamen_Materia_PlanEstudios_codigo, MesaExamen_anio, MesaExamen_turno) 
    REFERENCES MesaExamen(Materia_codigo, Materia_PlanEstudios_codigo, anio, turno) ON DELETE CASCADE ON UPDATE CASCADE, 
PRIMARY KEY (MesaExamen_turno, MesaExamen_anio, MesaExamen_Materia_codigo, MesaExamen_Materia_PlanEstudios_codigo, Estudiante_nroRegistro));