/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Interfaces.Paneles.NoDocente;

import java.awt.Color;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import com.mycompany.tp_maquina_is2.Graficos.Rotator;
import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Managers.ExamenManager;
import com.mycompany.tp_maquina_is2.Logica.Managers.PlanEstudiosManager;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.PlanEstudios;
import java.awt.GradientPaint;
import java.util.HashMap;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ivanb
 */
public class EstadisticasNoDocente extends javax.swing.JPanel {

    private String codPlan;

    /**
     * Creates new form EstadisticasNoDocente
     */
    public EstadisticasNoDocente(String codPlan) {
        initComponents();

        this.codPlan = codPlan;
    }

    private void cargarGrafico(String tipo) {
        try {
            JFreeChart chart = null;

            switch (tipo) {
                case "dificultad":
                    chart = graficaExperiencia("dificultad");
                    break;
                case "preparacion":
                    chart = graficaExperiencia("preparacion");
                    break;
                case "dedicacion":
                    chart = graficaExperiencia("dedicacion");
                    break;
                case "rendidas":
                    chart = graficoRendidas();
                    break;
            }

            contenedor.setLayout(new java.awt.CardLayout());
            contenedor.removeAll();
            contenedor.add(new ChartPanel(chart, false));
            contenedor.revalidate();
            contenedor.repaint();
        } catch (ManagementException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public JFreeChart graficoRendidas() throws ManagementException {
        JFreeChart chart;

        PlanEstudios planEstudios = PlanEstudiosManager.buscar(codPlan);

        HashMap<String, Double> result = ExamenManager.getCodTopRendidas(codPlan);

        DefaultPieDataset data = new DefaultPieDataset();

        for (String codMateria : result.keySet()) {
            data.setValue(planEstudios.getMaterias().get(codMateria).getNombre(), result.get(codMateria));
        }

        chart = ChartFactory.createPieChart3D(
                "Materias más rendidas", // chart title
                data, // data
                true, // include legend
                true,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelBackgroundPaint(Color.ORANGE);//Color de las etiquetas
        plot.setForegroundAlpha(0.60f);//Color de el fondo del grafico

        //esto es lo que lo hace rotar
        Rotator rotator = new Rotator(plot);
        rotator.start();

        return chart;
    }

    public JFreeChart graficaExperiencia(String tipo) throws ManagementException {
        JFreeChart chart;
        String titulo="",ejex="Materia",ejey="";
        PlanEstudios planEstudios = PlanEstudiosManager.buscar(codPlan);
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        HashMap<String, Double> result = ExamenManager.getEstadisticaExpPlan(codPlan, tipo);
        
        for (String codMateria : result.keySet()) {
            dataset.setValue(result.get(codMateria), "Materia", planEstudios.getMaterias().get(codMateria).getNombre());
        }
        switch (tipo) {
                    case "dificultad":
                        titulo="Dificultad promedio";
                        ejey="Dificultad promedio";
                        break;
                    case "preparacion":
                        titulo="Preparación";
                        ejey="Preparación";
                        break;
                    case "dedicacion":
                        titulo="Dedicación";
                        ejey="Dedicación";
                }
        
        chart = ChartFactory.createBarChart3D(
                titulo, //Nombre de la grafica
                ejex, //Nombre del eje Horizontal
                ejey, //Nombre del eje vertical
                dataset, //Data
                PlotOrientation.VERTICAL, //Orientacion HORIZONTAL o VERTICAL
                true, //Incluir leyenda
                true, //Informacion al pasar el mouse
                true);                      //URls

        chart.setBackgroundPaint(Color.GRAY);//Dar color al fondo del panel
        chart.getTitle().setPaint(Color.WHITE);//Dar color al titulo 
            	    	    
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);//Color del fondo del gr�fico

        plot.setDomainGridlinesVisible(true);//Lineas divisorias
        plot.setRangeGridlinePaint(Color.BLACK);//Color de lineas divisorias	    

        //Calculo de los valores en el eje x
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);//Asignar color de linea a las barras

        //Dar color a las barras
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
        
        return chart;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedor = new javax.swing.JPanel();
        SeleccionP = new javax.swing.JPanel();
        dificultadPromedioP = new javax.swing.JPanel();
        dificultadPromedioL = new javax.swing.JLabel();
        preparacionP = new javax.swing.JPanel();
        preparacionL = new javax.swing.JLabel();
        dedicacionP = new javax.swing.JPanel();
        dedicacionL = new javax.swing.JLabel();
        mesasP = new javax.swing.JPanel();
        mesasL = new javax.swing.JLabel();
        labelSeleccion = new javax.swing.JLabel();
        labelTop10 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contenedor.setBackground(new java.awt.Color(33, 33, 33));
        contenedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(contenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 840, 550));

        SeleccionP.setBackground(new java.awt.Color(118, 35, 47));
        SeleccionP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dificultadPromedioP.setBackground(new java.awt.Color(118, 35, 47));
        dificultadPromedioP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dificultadPromedioP.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        dificultadPromedioP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dificultadPromedioPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dificultadPromedioPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dificultadPromedioPMouseExited(evt);
            }
        });

        dificultadPromedioL.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        dificultadPromedioL.setForeground(new java.awt.Color(255, 255, 255));
        dificultadPromedioL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_effort_40px.png"))); // NOI18N
        dificultadPromedioL.setText("Dificultad promedio");
        dificultadPromedioL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout dificultadPromedioPLayout = new javax.swing.GroupLayout(dificultadPromedioP);
        dificultadPromedioP.setLayout(dificultadPromedioPLayout);
        dificultadPromedioPLayout.setHorizontalGroup(
            dificultadPromedioPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dificultadPromedioPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dificultadPromedioL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dificultadPromedioPLayout.setVerticalGroup(
            dificultadPromedioPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dificultadPromedioPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dificultadPromedioL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SeleccionP.add(dificultadPromedioP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        preparacionP.setBackground(new java.awt.Color(118, 35, 47));
        preparacionP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        preparacionP.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        preparacionP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                preparacionPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                preparacionPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                preparacionPMouseExited(evt);
            }
        });

        preparacionL.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        preparacionL.setForeground(new java.awt.Color(255, 255, 255));
        preparacionL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_tuition_40px.png"))); // NOI18N
        preparacionL.setText("Promedio preparación aprobados");
        preparacionL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout preparacionPLayout = new javax.swing.GroupLayout(preparacionP);
        preparacionP.setLayout(preparacionPLayout);
        preparacionPLayout.setHorizontalGroup(
            preparacionPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(preparacionPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(preparacionL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        preparacionPLayout.setVerticalGroup(
            preparacionPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(preparacionPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(preparacionL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SeleccionP.add(preparacionP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        dedicacionP.setBackground(new java.awt.Color(118, 35, 47));
        dedicacionP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dedicacionP.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        dedicacionP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dedicacionPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dedicacionPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dedicacionPMouseExited(evt);
            }
        });

        dedicacionL.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        dedicacionL.setForeground(new java.awt.Color(255, 255, 255));
        dedicacionL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_winner_40px.png"))); // NOI18N
        dedicacionL.setText("Dedicación promedio aprobados");
        dedicacionL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout dedicacionPLayout = new javax.swing.GroupLayout(dedicacionP);
        dedicacionP.setLayout(dedicacionPLayout);
        dedicacionPLayout.setHorizontalGroup(
            dedicacionPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dedicacionPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dedicacionL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dedicacionPLayout.setVerticalGroup(
            dedicacionPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dedicacionPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dedicacionL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SeleccionP.add(dedicacionP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        mesasP.setBackground(new java.awt.Color(118, 35, 47));
        mesasP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mesasP.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        mesasP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mesasPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mesasPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mesasPMouseExited(evt);
            }
        });

        mesasL.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        mesasL.setForeground(new java.awt.Color(255, 255, 255));
        mesasL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_investment_portfolio_40px.png"))); // NOI18N
        mesasL.setText("Materias más rendidas");
        mesasL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout mesasPLayout = new javax.swing.GroupLayout(mesasP);
        mesasP.setLayout(mesasPLayout);
        mesasPLayout.setHorizontalGroup(
            mesasPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mesasPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mesasL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mesasPLayout.setVerticalGroup(
            mesasPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mesasPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mesasL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SeleccionP.add(mesasP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        labelSeleccion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelSeleccion.setForeground(new java.awt.Color(255, 255, 255));
        labelSeleccion.setText("Seleccione una Estadística:");
        SeleccionP.add(labelSeleccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        labelTop10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelTop10.setForeground(new java.awt.Color(255, 255, 255));
        labelTop10.setText("TOP 10");
        SeleccionP.add(labelTop10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        add(SeleccionP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 680));
    }// </editor-fold>//GEN-END:initComponents

    private void dificultadPromedioPMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_dificultadPromedioPMouseClicked
        cargarGrafico("dificultad");
    }// GEN-LAST:event_dificultadPromedioPMouseClicked

    private void dificultadPromedioPMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_dificultadPromedioPMouseEntered
        // TODO add your handling code here:
    }// GEN-LAST:event_dificultadPromedioPMouseEntered

    private void dificultadPromedioPMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_dificultadPromedioPMouseExited
        // TODO add your handling code here:
    }// GEN-LAST:event_dificultadPromedioPMouseExited

    private void preparacionPMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_preparacionPMouseClicked
        cargarGrafico("preparacion");
    }// GEN-LAST:event_preparacionPMouseClicked

    private void preparacionPMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_preparacionPMouseEntered
        // TODO add your handling code here:
    }// GEN-LAST:event_preparacionPMouseEntered

    private void preparacionPMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_preparacionPMouseExited
        // TODO add your handling code here:
    }// GEN-LAST:event_preparacionPMouseExited

    private void dedicacionPMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_dedicacionPMouseClicked
        cargarGrafico("dedicacion");
    }// GEN-LAST:event_dedicacionPMouseClicked

    private void dedicacionPMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_dedicacionPMouseEntered
        // TODO add your handling code here:
    }// GEN-LAST:event_dedicacionPMouseEntered

    private void dedicacionPMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_dedicacionPMouseExited
        // TODO add your handling code here:
    }// GEN-LAST:event_dedicacionPMouseExited

    private void mesasPMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_mesasPMouseClicked
        cargarGrafico("rendidas");
    }// GEN-LAST:event_mesasPMouseClicked

    private void mesasPMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_mesasPMouseEntered
        // TODO add your handling code here:
    }// GEN-LAST:event_mesasPMouseEntered

    private void mesasPMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_mesasPMouseExited
        // TODO add your handling code here:
    }// GEN-LAST:event_mesasPMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SeleccionP;
    private javax.swing.JPanel contenedor;
    private javax.swing.JLabel dedicacionL;
    private javax.swing.JPanel dedicacionP;
    private javax.swing.JLabel dificultadPromedioL;
    private javax.swing.JPanel dificultadPromedioP;
    private javax.swing.JLabel labelSeleccion;
    private javax.swing.JLabel labelTop10;
    private javax.swing.JLabel mesasL;
    private javax.swing.JPanel mesasP;
    private javax.swing.JLabel preparacionL;
    private javax.swing.JPanel preparacionP;
    // End of variables declaration//GEN-END:variables
}
