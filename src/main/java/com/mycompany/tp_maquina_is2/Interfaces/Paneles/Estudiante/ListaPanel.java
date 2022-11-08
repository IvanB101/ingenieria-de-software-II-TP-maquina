/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Interfaces.Paneles.Estudiante;

import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Managers.HistoriaAcademicaManager;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
        TablaMaterias.getTableHeader().setBackground(new Color(0, 153, 153));
        jScrollPane1.getViewport().setBackground(new Color(255, 255, 255)); //tabla color blanco
        TablaMaterias.getTableHeader().setReorderingAllowed(false);
        //doy aceptar
        ActionEvent evt = null;
        jButton1ActionPerformed(evt);
        
        textfieldias.setVisible(false);
        labeldias.setVisible(false);
    }

    public void listaFinales(int dias) {
        String criterio = ComboBoxCriterio.getSelectedItem().toString();
        DefaultTableModel modelo = (DefaultTableModel) TablaMaterias.getModel();
        modelo.setRowCount(0);
        try {
            HashMap<Materia, Object> materias = HistoriaAcademicaManager.listaExamenes(nroRegistro, codPlanEstudios, criterio, dias);
            Set<Materia> keys2 = materias.keySet();
            ArrayList<Materia> keys = new ArrayList<>();
            if (criterio.equals("Correlativas")) {
               TablaMaterias.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("CORRELATIVAS");
               TablaMaterias.getTableHeader().repaint();
               for (Materia materia : keys2) {
                    keys.add(materia);
                }
                keys.sort((c1, c2) -> {
                    Integer valuec1 = (Integer) materias.get(c2);
                    return valuec1.compareTo((Integer) materias.get(c1));
                });
                for (Materia key : keys) {
                    modelo.addRow(new Object[]{key.getCodigo(), key.getNombre(), materias.get(key)});
                }

            }//fin if correlativas
            if (criterio.equals("Dificultad")) { 
                TablaMaterias.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("DIFICULTAD PROMEDIO");
                TablaMaterias.getTableHeader().repaint();
                for (Materia materia : keys2) {
                    keys.add(materia);
                }
                keys.sort((c1, c2) -> {
                    Double valuec1D = (Double) materias.get(c2);
                    return valuec1D.compareTo((Double) materias.get(c1));
                });
                
                for (Materia key : keys) {
                    modelo.addRow(new Object[]{key.getCodigo(), key.getNombre(), materias.get(key)});
                }

            }//fin if dificultad
            if (criterio.equals("Tiempo")) {
               if(dias != 0){
                try {
                    dias = Integer.parseInt(textfieldias.getText().trim());
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                TablaMaterias.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("APROBARON EN "+dias+" DIAS O MENOS");
                TablaMaterias.getTableHeader().repaint();
                for (Materia materia : keys2) {
                    keys.add(materia);
                }
                keys.sort((c1, c2) -> {
                    Integer valuec1 = (Integer) materias.get(c2);
                    return valuec1.compareTo((Integer) materias.get(c1));
                });
                for (Materia key : keys) {
                    modelo.addRow(new Object[]{key.getCodigo(), key.getNombre(), materias.get(key)});
                }
                //control 
            }//fin if tiempo
        }//control
        } catch (ManagementException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
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
        Inscripcion = new javax.swing.JPanel();
        estudianteBL = new javax.swing.JLabel();

        PanelCambiable.setBackground(new java.awt.Color(255, 255, 255));
        PanelCambiable.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        PanelCambiable.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 670, 340));

        ComboBoxCriterio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Correlativas", "Tiempo", "Vencimiento", "Dificultad" }));
        ComboBoxCriterio.setBorder(null);
        ComboBoxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Correlativas", "Tiempo", "Proximidad a cursar", "Profundidad a cursar", "Vencimiento de regularidad", "Dificultad" }));
        ComboBoxCriterio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ComboBoxCriterio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCriterioActionPerformed(evt);
            }
        });
        PanelCambiable.add(ComboBoxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, 170, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("A continuación se muestran las materias que el Estudiante está en condiciones de rendir, es decir, cumple con las correlativas ");
        PanelCambiable.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("El criterio seleccionado por default es la cantidad de materias que tienen a dicha materia como correlativa.");
        PanelCambiable.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        textfieldias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldiasActionPerformed(evt);
            }
        });
        PanelCambiable.add(textfieldias, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 170, -1));

        labeldias.setForeground(new java.awt.Color(51, 51, 51));
        labeldias.setText("Dias para estudiar:");
        PanelCambiable.add(labeldias, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, -1, -1));

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        PanelCambiable.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 210, 160, 20));
        Inscripcion.setBackground(new java.awt.Color(118, 35, 47));
        Inscripcion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Inscripcion.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Inscripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InscripcionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InscripcionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InscripcionMouseExited(evt);
            }
        });

        estudianteBL.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        estudianteBL.setForeground(new java.awt.Color(255, 255, 255));
        estudianteBL.setText("Inscribirse a examen");
        estudianteBL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout InscripcionLayout = new javax.swing.GroupLayout(Inscripcion);
        Inscripcion.setLayout(InscripcionLayout);
        InscripcionLayout.setHorizontalGroup(
            InscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InscripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(estudianteBL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        InscripcionLayout.setVerticalGroup(
            InscripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InscripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(estudianteBL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelCambiable.add(Inscripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 440, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(PanelCambiable, javax.swing.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(PanelCambiable, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addContainerGap())
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
        }

    }//GEN-LAST:event_ComboBoxCriterioActionPerformed

    private void textfieldiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldiasActionPerformed

    }//GEN-LAST:event_textfieldiasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int dias = 0;
        if(ComboBoxCriterio.getSelectedItem().toString().equals("Tiempo")){
        try {
            dias = Integer.parseInt(textfieldias.getText().trim());
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debes ingresar la cantidad de días que tenés para estudiar");
            }
        }
        this.listaFinales(dias);
    }//GEN-LAST:event_jButton1ActionPerformed
    private void TablaMateriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMateriasMouseClicked
        Inscripcion.setVisible(true);
    }//GEN-LAST:event_TablaMateriasMouseClicked

    private void InscripcionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InscripcionMouseClicked
        changePane(new InscripcionPanel(nroRegistro,codPlanEstudios,(String)TablaMaterias.getValueAt(TablaMaterias.getSelectedRow(),0)));
    }//GEN-LAST:event_InscripcionMouseClicked

    private void InscripcionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InscripcionMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_InscripcionMouseEntered

    private void InscripcionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InscripcionMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_InscripcionMouseExited

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxCriterio;
    private javax.swing.JPanel Inscripcion;
    private javax.swing.JPanel PanelCambiable;
    private javax.swing.JTable TablaMaterias;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel estudianteBL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labeldias;
    private javax.swing.JTextField textfieldias;
    // End of variables declaration//GEN-END:variables
}
