/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Interfaces.Paneles.Estudiante;

import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Managers.HistoriaAcademicaManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juan_
 */
public class ListaPanel extends javax.swing.JPanel {

    private int nroRegistro;
    private String codPlanEstudios;

    /**
     * Creates new form ListaPanel
     */
    public ListaPanel(int nroRegistro, String codPlanEstudios) {
        this.nroRegistro = nroRegistro;
        this.codPlanEstudios = codPlanEstudios;
        initComponents();
        TablaMaterias.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TablaMaterias.getTableHeader().setOpaque(true);
        TablaMaterias.getTableHeader().setBackground(new Color(58,56,56));
        TablaMaterias.getTableHeader().setForeground(Color.white);
        jScrollPane1.getViewport().setBackground(new Color(33, 33, 33)); //tabla color negro
        TablaMaterias.getTableHeader().setReorderingAllowed(false);
        TablaMaterias.setBackground(new Color(58,56,56));//coloresrow
        
        //doy aceptar
        ActionEvent evt = null;
        jButton1ActionPerformed(evt);

        textfieldias.setVisible(false);
        labeldias.setVisible(false);

        inscribirseExamenLabel.setVisible(false);
        verListaInscriptosLabel.setVisible(false);
        mesa.setVisible(false);

    }

    public void listaFinales(int dias) {
        String criterio = ComboBoxCriterio.getSelectedItem().toString();
        DefaultTableModel modelo = (DefaultTableModel) TablaMaterias.getModel();
        modelo.setRowCount(0);
        try {
            switch (criterio) {
                case "Correlativas":
                    TablaMaterias.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("CORRELATIVAS");
                    break;
                case "Dificultad":
                    TablaMaterias.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("DIFICULTAD PROMEDIO");
                    break;
                case "Tiempo":
                    if (dias != 0) {
                        try {
                            dias = Integer.parseInt(textfieldias.getText().trim());
                        } catch (HeadlessException | NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        }
                        
                        TablaMaterias.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("APROBARON EN " + dias + " DIAS O MENOS");
                    }
                    break;

                case "Vencimiento":
                    TablaMaterias.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("SE VENCE EN: (SEMANAS)");
            }
            TablaMaterias.getTableHeader().repaint();

            ArrayList<Object[]> filasTabla = HistoriaAcademicaManager.listaExamenes(nroRegistro, codPlanEstudios, criterio, dias);

            for (Object[] objects : filasTabla) {
                modelo.addRow(objects);
            }
        } catch (ManagementException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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

        PanelCambiable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaMaterias = new javax.swing.JTable();
        ComboBoxCriterio = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textfieldias = new javax.swing.JTextField();
        labeldias = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        mesa = new javax.swing.JLabel();
        verListaInscriptosLabel = new javax.swing.JLabel();
        inscribirseExamenLabel = new javax.swing.JLabel();
        verEstadisticasLabel = new javax.swing.JLabel();

        PanelCambiable.setBackground(new java.awt.Color(33, 33, 33));
        PanelCambiable.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaMaterias.setForeground(new java.awt.Color(255, 255, 255));
        TablaMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "CORRELATIVAS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaMaterias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TablaMaterias.setFocusable(false);
        TablaMaterias.setRowHeight(25);
        TablaMaterias.setSelectionBackground(new java.awt.Color(118, 35, 47));
        TablaMaterias.setSelectionForeground(new java.awt.Color(255, 255, 255));
        TablaMaterias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMateriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaMaterias);
        if (TablaMaterias.getColumnModel().getColumnCount() > 0) {
            TablaMaterias.getColumnModel().getColumn(0).setResizable(false);
            TablaMaterias.getColumnModel().getColumn(1).setResizable(false);
            TablaMaterias.getColumnModel().getColumn(2).setResizable(false);
        }

        PanelCambiable.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 630, 350));

        ComboBoxCriterio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Correlativas", "Tiempo", "Vencimiento", "Dificultad" }));
        ComboBoxCriterio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ComboBoxCriterio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCriterioActionPerformed(evt);
            }
        });
        PanelCambiable.add(ComboBoxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 170, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("A continuación se muestran las materias que el Estudiante está en condiciones de rendir, es decir, cumple con las correlativas ");
        PanelCambiable.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("El criterio seleccionado por default es la cantidad de materias que tienen a dicha materia como correlativa.");
        PanelCambiable.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        textfieldias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldiasActionPerformed(evt);
            }
        });
        PanelCambiable.add(textfieldias, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 170, 20));

        labeldias.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labeldias.setForeground(new java.awt.Color(255, 255, 255));
        labeldias.setText("Días para estudiar:");
        PanelCambiable.add(labeldias, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 120, -1, -1));

        jButton1.setBackground(new java.awt.Color(153, 38, 54));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ACEPTAR");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        PanelCambiable.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 170, 30));

        mesa.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        mesa.setForeground(new java.awt.Color(102, 255, 51));
        mesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_calendar_40px_1.png"))); // NOI18N
        mesa.setText("Se corresponden a la mesa siguiente : Diciembre");
        mesa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelCambiable.add(mesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 380, -1));

        verListaInscriptosLabel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        verListaInscriptosLabel.setForeground(new java.awt.Color(255, 255, 255));
        verListaInscriptosLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_view_40px_1.png"))); // NOI18N
        verListaInscriptosLabel.setText("Ver Lista Inscriptos");
        verListaInscriptosLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verListaInscriptosLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verListaInscriptosLabelMouseClicked(evt);
            }
        });
        PanelCambiable.add(verListaInscriptosLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 260, -1, -1));

        inscribirseExamenLabel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        inscribirseExamenLabel.setForeground(new java.awt.Color(255, 255, 255));
        inscribirseExamenLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_agreement_new_40px.png"))); // NOI18N
        inscribirseExamenLabel.setText("Inscribirse a examen");
        inscribirseExamenLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inscribirseExamenLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inscribirseExamenLabelMouseClicked(evt);
            }
        });
        PanelCambiable.add(inscribirseExamenLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 320, -1, -1));

        verEstadisticasLabel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        verEstadisticasLabel.setForeground(new java.awt.Color(255, 255, 255));
        verEstadisticasLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_combo_chart_40px.png"))); // NOI18N
        verEstadisticasLabel.setText("Ver Estadisticas de Examen");
        verEstadisticasLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verEstadisticasLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verEstadisticasLabelMouseClicked(evt);
            }
        });
        PanelCambiable.add(verEstadisticasLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 370, 230, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCambiable, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCambiable, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void changePane(JPanel jPanel) {
        PanelCambiable.setLayout(new java.awt.CardLayout());
        PanelCambiable.removeAll();
        PanelCambiable.add(jPanel);
        PanelCambiable.revalidate();
        PanelCambiable.repaint();
    }

    private void ComboBoxCriterioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCriterioActionPerformed
        if (ComboBoxCriterio.getSelectedItem().toString().equals("Tiempo")) {
            labeldias.setVisible(true);
            textfieldias.setVisible(true);
        } else {
            labeldias.setVisible(false);
            textfieldias.setVisible(false);
        }

    }//GEN-LAST:event_ComboBoxCriterioActionPerformed

    private void textfieldiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldiasActionPerformed

    }//GEN-LAST:event_textfieldiasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int dias = 0;
        if (ComboBoxCriterio.getSelectedItem().toString().equals("Tiempo")) {
            try {
                dias = Integer.parseInt(textfieldias.getText().trim());
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debes ingresar la cantidad de días que tenés para estudiar");
            }
        }
        this.listaFinales(dias);
    }//GEN-LAST:event_jButton1ActionPerformed
    private void TablaMateriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMateriasMouseClicked
        inscribirseExamenLabel.setVisible(true);
        verListaInscriptosLabel.setVisible(true);
        mesa.setVisible(true);

    }//GEN-LAST:event_TablaMateriasMouseClicked

    private void verListaInscriptosLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verListaInscriptosLabelMouseClicked
        changePane(new InscripcionPanel(nroRegistro, codPlanEstudios, (String) TablaMaterias.getValueAt(TablaMaterias.getSelectedRow(), 0), true));
    }//GEN-LAST:event_verListaInscriptosLabelMouseClicked

    private void inscribirseExamenLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inscribirseExamenLabelMouseClicked
        changePane(new InscripcionPanel(nroRegistro, codPlanEstudios, (String) TablaMaterias.getValueAt(TablaMaterias.getSelectedRow(), 0), false));
    }//GEN-LAST:event_inscribirseExamenLabelMouseClicked

    private void verEstadisticasLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verEstadisticasLabelMouseClicked
        changePane(new EstadisticasEstudiante( (String) TablaMaterias.getValueAt(TablaMaterias.getSelectedRow(), 0),codPlanEstudios));
    }//GEN-LAST:event_verEstadisticasLabelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxCriterio;
    private javax.swing.JPanel PanelCambiable;
    private javax.swing.JTable TablaMaterias;
    private javax.swing.JLabel inscribirseExamenLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labeldias;
    private javax.swing.JLabel mesa;
    private javax.swing.JTextField textfieldias;
    private javax.swing.JLabel verEstadisticasLabel;
    private javax.swing.JLabel verListaInscriptosLabel;
    // End of variables declaration//GEN-END:variables
}
