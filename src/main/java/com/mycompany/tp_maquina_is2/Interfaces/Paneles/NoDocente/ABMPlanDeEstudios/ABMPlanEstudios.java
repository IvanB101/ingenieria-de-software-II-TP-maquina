/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Interfaces.Paneles.NoDocente.ABMPlanDeEstudios;

import com.mycompany.tp_maquina_is2.Logica.Excepciones.ManagementException;
import com.mycompany.tp_maquina_is2.Logica.Managers.PlanEstudiosManager;
import com.mycompany.tp_maquina_is2.Logica.Util.ArchivosManager;
import java.awt.Color;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ivanb
 */
public class ABMPlanEstudios extends javax.swing.JPanel {
    Color DefaultColor,ClickedColor;
    /**
     * Creates new form ABMPlanEstudios
     */
    public ABMPlanEstudios() {
        initComponents();
        DefaultColor=new Color(51,51,51);
        ClickedColor=new Color(118,35,47);
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
        navlateral = new javax.swing.JPanel();
        modificarBP = new javax.swing.JPanel();
        modificarBL = new javax.swing.JLabel();
        cargarBP = new javax.swing.JPanel();
        cargarBL = new javax.swing.JLabel();
        eliminarBP = new javax.swing.JPanel();
        eliminarBL = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contenedor.setBackground(new java.awt.Color(33, 33, 33));
        contenedor.setPreferredSize(new java.awt.Dimension(950, 500));
        contenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navlateral.setBackground(new java.awt.Color(51, 51, 51));

        modificarBP.setBackground(new java.awt.Color(51, 51, 51));
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
        modificarBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_book_and_pencil_40px.png"))); // NOI18N
        modificarBL.setText("Modificar");
        modificarBL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modificarBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarBLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                modificarBLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                modificarBLMouseExited(evt);
            }
        });

        javax.swing.GroupLayout modificarBPLayout = new javax.swing.GroupLayout(modificarBP);
        modificarBP.setLayout(modificarBPLayout);
        modificarBPLayout.setHorizontalGroup(
            modificarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificarBPLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(modificarBL, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        modificarBPLayout.setVerticalGroup(
            modificarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(modificarBL, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        cargarBP.setBackground(new java.awt.Color(51, 51, 51));
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
        cargarBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_add_book_40px.png"))); // NOI18N
        cargarBL.setText("Cargar");
        cargarBL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cargarBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cargarBLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cargarBLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cargarBLMouseExited(evt);
            }
        });

        javax.swing.GroupLayout cargarBPLayout = new javax.swing.GroupLayout(cargarBP);
        cargarBP.setLayout(cargarBPLayout);
        cargarBPLayout.setHorizontalGroup(
            cargarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cargarBPLayout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addComponent(cargarBL, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        cargarBPLayout.setVerticalGroup(
            cargarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cargarBL, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        eliminarBP.setBackground(new java.awt.Color(51, 51, 51));
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
        eliminarBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8_delete_40px.png"))); // NOI18N
        eliminarBL.setText("Eliminar");
        eliminarBL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eliminarBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarBLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eliminarBLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                eliminarBLMouseExited(evt);
            }
        });

        javax.swing.GroupLayout eliminarBPLayout = new javax.swing.GroupLayout(eliminarBP);
        eliminarBP.setLayout(eliminarBPLayout);
        eliminarBPLayout.setHorizontalGroup(
            eliminarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eliminarBPLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(eliminarBL, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        eliminarBPLayout.setVerticalGroup(
            eliminarBPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eliminarBL, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout navlateralLayout = new javax.swing.GroupLayout(navlateral);
        navlateral.setLayout(navlateralLayout);
        navlateralLayout.setHorizontalGroup(
            navlateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cargarBP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(modificarBP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(eliminarBP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        navlateralLayout.setVerticalGroup(
            navlateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navlateralLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(cargarBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(modificarBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(eliminarBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );

        contenedor.add(navlateral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 670));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abm plan (1).png"))); // NOI18N
        contenedor.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 660, 470));

        add(contenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 670));
    }// </editor-fold>//GEN-END:initComponents

    private void modificarBPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarBPMouseClicked
       // changePane(new SeleccionarPlan("modificar"));
    }//GEN-LAST:event_modificarBPMouseClicked

    private void modificarBPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarBPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarBPMouseEntered

    private void modificarBPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarBPMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarBPMouseExited

    private void cargarBPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargarBPMouseClicked
       /* int returnVal = SelectorPlan.showOpenDialog(this);
        if (returnVal == SelectorPlan.APPROVE_OPTION) {
            File file = SelectorPlan.getSelectedFile();
            try {
                ArchivosManager.cargarPlanEstudios(file);
                
                JOptionPane.showMessageDialog(null, "Plan de estudios cargado con exito");
            } catch (ManagementException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
        } */
    }//GEN-LAST:event_cargarBPMouseClicked

    private void cargarBPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargarBPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cargarBPMouseEntered

    private void cargarBPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargarBPMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_cargarBPMouseExited

    private void eliminarBPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBPMouseClicked
        //changePane(new EliminarPlan());
    }//GEN-LAST:event_eliminarBPMouseClicked

    private void eliminarBPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarBPMouseEntered

    private void eliminarBPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBPMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarBPMouseExited

    private void cargarBLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargarBLMouseEntered
        cargarBP.setBackground(ClickedColor);
    }//GEN-LAST:event_cargarBLMouseEntered

    private void cargarBLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargarBLMouseExited
        cargarBP.setBackground(DefaultColor);
    }//GEN-LAST:event_cargarBLMouseExited

    private void modificarBLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarBLMouseEntered
        modificarBP.setBackground(ClickedColor);
    }//GEN-LAST:event_modificarBLMouseEntered

    private void modificarBLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarBLMouseExited
        modificarBP.setBackground(DefaultColor);
    }//GEN-LAST:event_modificarBLMouseExited

    private void eliminarBLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBLMouseEntered
        eliminarBP.setBackground(ClickedColor);
    }//GEN-LAST:event_eliminarBLMouseEntered

    private void eliminarBLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBLMouseExited
        eliminarBP.setBackground(DefaultColor);
    }//GEN-LAST:event_eliminarBLMouseExited

    private void cargarBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargarBLMouseClicked
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
    }//GEN-LAST:event_cargarBLMouseClicked

    private void modificarBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarBLMouseClicked
        changePane(new SeleccionarPlan("modificar"));
    }//GEN-LAST:event_modificarBLMouseClicked

    private void eliminarBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarBLMouseClicked
        changePane(new EliminarPlan());
    }//GEN-LAST:event_eliminarBLMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser SelectorPlan;
    private javax.swing.JLabel cargarBL;
    private javax.swing.JPanel cargarBP;
    private javax.swing.JPanel contenedor;
    private javax.swing.JLabel eliminarBL;
    private javax.swing.JPanel eliminarBP;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel modificarBL;
    private javax.swing.JPanel modificarBP;
    private javax.swing.JPanel navlateral;
    // End of variables declaration//GEN-END:variables
}
