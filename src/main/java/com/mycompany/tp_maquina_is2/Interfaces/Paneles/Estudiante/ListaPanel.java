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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JOptionPane;
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
        TablaMaterias.getTableHeader().setBackground(new Color(0, 153, 153));
        jScrollPane1.getViewport().setBackground(new Color(255, 255, 255)); //tabla color blanco
        TablaMaterias.getTableHeader().setReorderingAllowed(false);

        //listaFinales();
    }

    /* public void listaFinales() {
        try {
            HashMap<Materia, Integer> materias = HistoriaAcademicaManager.listaExamenes(nroRegistro, codPlanEstudios,);
            DefaultTableModel modelo = (DefaultTableModel) TablaMaterias.getModel();
            Set<Materia> keys2 = materias.keySet();

            ArrayList<Materia> keys = new ArrayList<>();

            for (Materia materia : keys2) {
                keys.add(materia);
            }

            keys.sort((c1, c2) -> {
                return materias.get(c2).compareTo(materias.get(c1));
            });

            for (Materia key : keys) {
                modelo.addRow(new Object[]{key.getCodigo(), key.getNombre(), materias.get(key)});
            }
        } catch (ManagementException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    } 
     */
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
        jScrollPane1.setViewportView(TablaMaterias);
        if (TablaMaterias.getColumnModel().getColumnCount() > 0) {
            TablaMaterias.getColumnModel().getColumn(0).setResizable(false);
            TablaMaterias.getColumnModel().getColumn(1).setResizable(false);
            TablaMaterias.getColumnModel().getColumn(2).setResizable(false);
        }

        PanelCambiable.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 670, 340));

        ComboBoxCriterio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ComboBoxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Correlativas", "Tiempo", "Vencimiento de regularidad", "Dificultad" }));
        ComboBoxCriterio.setBorder(null);
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

    private void ComboBoxCriterioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCriterioActionPerformed
        String criterio = ComboBoxCriterio.getSelectedItem().toString();
        DefaultTableModel modelo = (DefaultTableModel) TablaMaterias.getModel();
        try {
            HashMap<Materia, Object> materias = HistoriaAcademicaManager.listaExamenes(nroRegistro, codPlanEstudios, criterio);
            Set<Materia> keys2 = materias.keySet();
            ArrayList<Materia> keys = new ArrayList<>();
            if (criterio.equals("Correlativas")) {
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

            }//fin if dificultaD

        } catch (ManagementException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("HOLA!!!!");
        }

    }//GEN-LAST:event_ComboBoxCriterioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed

    }// GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxCriterio;
    private javax.swing.JPanel PanelCambiable;
    private javax.swing.JTable TablaMaterias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
  /*for (Materia materia : keys2) {
                    keys.add(materia);
                }
                keys.sort((c1, c2) -> {
                    Integer valuec1 = (Integer) materias.get(c2);
                    return valuec1.compareTo((Integer) materias.get(c1));
                });
                for (Materia key : keys) {
                    modelo.addRow(new Object[]{key.getCodigo(), key.getNombre(), materias.get(key)});
                }¨*/
