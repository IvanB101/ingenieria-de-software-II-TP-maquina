/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Logica.Managers;

import com.mycompany.tp_maquina_is2.Datos.Conexion;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.ExamenDAOImp;
import com.mycompany.tp_maquina_is2.Datos.DAO.Implementaciones.ExperienciaDAOImp;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Examen;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Experiencia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ivanb
 */
public abstract class ExamenManager {

    private static ExamenDAOImp examenDAOImp;
    private static ExperienciaDAOImp experienciaDAOImp;

    public static void init(Conexion conexion) {
        examenDAOImp = new ExamenDAOImp(conexion);

        experienciaDAOImp = new ExperienciaDAOImp(conexion);
    }

    public static ArrayList<Examen> examenesEstudianteSinExp(int nroRegistro,String codPlan) throws ManagementException {
        try {
            return examenDAOImp.getExamenesEstudianteSinExp(nroRegistro,codPlan);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    public static ArrayList<Examen> examenesEstudianteConExp(int nroRegistro,String codPlan) throws ManagementException {
        try {
            return examenDAOImp.getExamenesEstudianteConExp(nroRegistro,codPlan);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    public static void cargar(List<Examen> examenes) throws ManagementException {
        for (Examen examen : examenes) {
            try {
                examenDAOImp.create(examen);
            } catch (SQLException e) {
                if (e.getMessage().contains("llave duplicada")) {
                    try {
                        examenDAOImp.update(examen.getCodigo(), examen);
                    } catch (SQLException e2) {
                        throw new ManagementException(e2.getMessage());
                    }
                } else {
                    throw new ManagementException(e.getMessage());
                }
            }
        }
    }

    public static void crearExperiencia(Experiencia experiencia) throws ManagementException {
        try {
            experienciaDAOImp.create(experiencia);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    //debería controlar el plan de estudios? creo que no pq c1 es la misma
    public static ArrayList<Experiencia> getExperiencias(String codMateria, String codPlan) throws ManagementException {
        try {
            return experienciaDAOImp.getExperienciasDAO(codMateria, codPlan);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }

    }

    public static ArrayList<Experiencia> getExperienciasAprobados(String codMateria, String codPlan) throws ManagementException {
        try {
            return experienciaDAOImp.getExperienciasAprobadosDAO(codMateria, codPlan);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    public static void ModificarExperiencia(String codigo, Experiencia experiencia) throws ManagementException {
        try {
            experienciaDAOImp.update(codigo, experiencia);
        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    public static void EliminarExperiencia(String codigo) throws ManagementException {
        try {
            experienciaDAOImp.delete(codigo);

        } catch (SQLException e) {
            throw new ManagementException(e.getMessage());
        }
    }

    public static double promedioDificultad(String codMateria, String codPlan) throws ManagementException {
        ArrayList<Experiencia> experiencias = getExperiencias(codMateria, codPlan);

        int cant = 0;
        for (Experiencia exp : experiencias) {
            cant += exp.getDificultad();
        }

        return cant / experiencias.size();

    }

    public static int cantidadAprobadosUnaMateria(String codMateria, int dias, String codPlan) throws ManagementException {
        ArrayList<Experiencia> experiencias = getExperienciasAprobados(codMateria, codPlan);

        int cant = 0;
        for (Experiencia exp : experiencias) {
            if (exp.getDias() <= dias) {
                cant++;
            }
        }

        return cant;
    }
    
    public static ArrayList<Double> promediosEstadisticas(String codMateria,String codPlan) throws ManagementException {
        int i=0;
        ArrayList<Double> promedios =new ArrayList();
        ArrayList<Experiencia> exps = getExperienciasAprobados(codMateria,codPlan);
        double dias = 0,dificultad =0,dedicacion=0;
        for (i=0;i<exps.size();i++){
            dias+=exps.get(i).getDias();
            dificultad+=exps.get(i).getDificultad();
            dedicacion+=exps.get(i).getDedicacion();
        }
        dias=dias/i;
        dificultad=dificultad/i;
        dedicacion=dedicacion/i;
        promedios.add(dificultad);
        promedios.add(dias);
        promedios.add(dedicacion);
        
        return promedios;
   }

    public static HashMap<String, Double> getCodTopRendidas(String codPlan) throws ManagementException {
        try {
            HashMap<String, Integer> temp = new HashMap<>();
            HashMap<String, Double> result = new HashMap<>();
            int cantidad = 10, total = 0;
            ArrayList<Examen> examenes = examenDAOImp.getExamenesPlan(codPlan);

            for (Examen examen : examenes) {
                Integer cant;
                String codMateria = examen.getCodMateria();
                if ((cant = temp.get(codMateria)) == null) {
                    temp.put(codMateria, 1);
                } else {
                    temp.put(codMateria, ++cant);
                }
            }

            Set<String> keys2 = temp.keySet();
            ArrayList<String> keys = new ArrayList<>();

            for (String codMateria : keys2) {
                keys.add(codMateria);
            }

            keys.sort((c1, c2) -> {
                Integer valuec1 = temp.get(c2);
                return valuec1.compareTo(temp.get(c1));
            });

            if (keys.size() < 10) {
                cantidad = keys.size();
            }

            for (int i = 0; i < cantidad; i++) {
                String codMateria = keys.get(i);
                double temp2 = temp.get(codMateria);

                result.put(codMateria, temp2);
                total += temp2;
            }

            for (String codMateria : result.keySet()) {
                result.put(codMateria, result.get(codMateria) / ((double) total));
            }

            return result;
        } catch (SQLException e) {
            System.out.println(e);
            throw new ManagementException("Ha ocurrido un error");
        }
    }

    public static HashMap<String, Double> getEstadisticaExpPlan(String codPlan, String estadistica) throws ManagementException {
        try {
            HashMap<String, Double> temp = new HashMap<>();
            HashMap<String, Integer> cantidades = new HashMap<>();
            HashMap<String, Double> result = new HashMap<>();
            int cantidad = 10;
            ArrayList<Experiencia> experiencias = new ArrayList<>();

            switch (estadistica) {
                case "dificultad":
                    experiencias = experienciaDAOImp.getExperienciasPlan(codPlan);
                    break;
                case "preparacion":
                case "decicacion":
                    experiencias = experienciaDAOImp.getExperienciasAprobadosPlan(codPlan);
            }

            for (Experiencia experiencia : experiencias) {
                String codMateria = experiencia.getCodMateria();
                Double dato = temp.get(codMateria);
                Double sumar = 0.0;

                switch (estadistica) {
                    case "dificultad":
                        sumar = (double) experiencia.getDificultad();
                        break;
                    case "preparacion":
                        sumar = (double) experiencia.getDias();
                        break;
                    case "decicacion":
                        sumar = (double) experiencia.getDedicacion();
                }
                if (dato == null) {
                    temp.put(codMateria, sumar);

                    cantidades.put(codMateria, 1);
                } else {
                    temp.put(codMateria, dato + sumar);

                    cantidades.put(codMateria, cantidades.get(codMateria) + 1);
                }
            }

            // Pasaje a promedios
            for (String codMateria : temp.keySet()) {
                temp.put(codPlan, temp.get(codMateria) / cantidades.get(codMateria));
            }

            Set<String> keys2 = temp.keySet();
            ArrayList<String> keys = new ArrayList<>();

            for (String codMateria : keys2) {
                keys.add(codMateria);
            }

            keys.sort((c1, c2) -> {
                Double valuec1 = temp.get(c2);
                return valuec1.compareTo(temp.get(c1));
            });

            if (keys.size() < 10) {
                cantidad = keys.size();
            }

            for (int i = 0; i < cantidad; i++) {
                String codMateria = keys.get(i);

                result.put(codMateria, temp.get(codMateria));
            }

            return result;
        } catch (SQLException e) {
            System.out.println(e);
            throw new ManagementException("Ha ocurrido un error");
        }

    }
}
