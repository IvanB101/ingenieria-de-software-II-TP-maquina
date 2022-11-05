/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Interfaces.Paneles.NoDocente.ABMPlanDeEstudios;

import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Managers.PlanEstudiosManager;
import com.mycompany.tp_maquina_is2.Logica.Util.ArchivosManager;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ivanb
 */
public class ABMPlanEstudios extends javax.swing.JPanel {

    /**
     * Creates new form ABMPlanEstudios
     */
    public ABMPlanEstudios() {
        initComponents();
    }

    private void changePane(JPanel jPanel) {
        contenedor.setLayout(new java.awt.CardLayout());
        contenedor.removeAll();
        contenedor.add(jPanel);
        contenedor.revalidate();
        contenedor.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SelectorPlan = new javax.swing.JFileChooser();
        contenedor = new javax.swing.JPanel();
        modificarBP = new javax.swing.JPanel();
        modificarBL = new javax.swing.JLabel();
        cargarBP = new javax.swing.JPanel();
        cargarBL = new javax.swing.JLabel();
        eliminarBP = new javax.swing.JPanel();
        eliminarBL = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contenedor.setBackground(new java.awt.Color(255, 255, 255));
        contenedor.setPreferredSize(new java.awt.Dimension(950, 500));
        contenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        modificarBP.setBackground(new java.awt.Color(118, 35, 47));
        modificarBP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        modificarBP.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        modificarBP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarBPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                modificarBPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                modificarBPMouseExited(evt);
            }
        });

        modificarBL.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        modificarBL.setForeground(new java.awt.Color(255, 255, 255));
        modificarBL.setText("Modificar");
        modificarBL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout modificarBPLayout = new javax.swing.GroupLayout(modificarBP);
        modificarBP.setLayout(modificarBPLayout);
        modificarBPLayout.setHorizontalGroup(
            modificarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarBPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modificarBL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        modificarBPLayout.setVerticalGroup(
            modificarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modificarBPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modificarBL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contenedor.add(modificarBP, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        cargarBP.setBackground(new java.awt.Color(118, 35, 47));
        cargarBP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cargarBP.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        cargarBP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cargarBPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cargarBPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cargarBPMouseExited(evt);
            }
        });

        cargarBL.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cargarBL.setForeground(new java.awt.Color(255, 255, 255));
        cargarBL.setText("Cargar");
        cargarBL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout cargarBPLayout = new javax.swing.GroupLayout(cargarBP);
        cargarBP.setLayout(cargarBPLayout);
        cargarBPLayout.setHorizontalGroup(
            cargarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cargarBPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cargarBL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cargarBPLayout.setVerticalGroup(
            cargarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cargarBPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cargarBL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contenedor.add(cargarBP, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 70, -1));

        eliminarBP.setBackground(new java.awt.Color(118, 35, 47));
        eliminarBP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        eliminarBP.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        eliminarBP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarBPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eliminarBPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                eliminarBPMouseExited(evt);
            }
        });

        eliminarBL.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        eliminarBL.setForeground(new java.awt.Color(255, 255, 255));
        eliminarBL.setText("Eliminar");
        eliminarBL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout eliminarBPLayout = new javax.swing.GroupLayout(eliminarBP);
        eliminarBP.setLayout(eliminarBPLayout);
        eliminarBPLayout.setHorizontalGroup(
            eliminarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminarBPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eliminarBL)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        eliminarBPLayout.setVerticalGroup(
            eliminarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eliminarBPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eliminarBL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contenedor.add(eliminarBP, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 80, -1));

        add(contenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 560));
    }// </editor-fold>//GEN-END:initComponents

    private void modificarBPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarBPMouseClicked
        changePane(new SeleccionarPlan());
    }//GEN-LAST:event_modificarBPMouseClicked

    private void modificarBPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarBPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarBPMouseEntered

    private void modificarBPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarBPMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarBPMouseExited

    private void cargarBPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargarBPMouseClicked
        int returnVal = SelectorPlan.showOpenDialog(this);
        if (returnVal == SelectorPlan.APPROVE_OPTION) {
            File file = SelectorPlan.getSelectedFile();
            try {
                ArchivosManager.cargarPlanEstudios(file);
                
                JOptionPane.showMessageDialog(null, "Plan de estudios cargado con exito");
            } catch (ManagementException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
        }
    }//GEN-LAST:event_cargarBPMouseClicked

    private void cargarBPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargarBPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cargarBPMouseEntered

    private void cargarBPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargarBPMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_cargarBPMouseExited

    private void eliminarBPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBPMouseClicked
        changePane(new EliminarPlan());
    }//GEN-LAST:event_eliminarBPMouseClicked

    private void eliminarBPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarBPMouseEntered

    private void eliminarBPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBPMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarBPMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser SelectorPlan;
    private javax.swing.JLabel cargarBL;
    private javax.swing.JPanel cargarBP;
    private javax.swing.JPanel contenedor;
    private javax.swing.JLabel eliminarBL;
    private javax.swing.JPanel eliminarBP;
    private javax.swing.JLabel modificarBL;
    private javax.swing.JPanel modificarBP;
    // End of variables declaration//GEN-END:variables
}
