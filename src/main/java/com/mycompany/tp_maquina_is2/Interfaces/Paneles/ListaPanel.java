/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Interfaces.Paneles;

import com.mycompany.tp_maquina_is2.Logica.Managers.HistoriaAcademicaManager;
import com.mycompany.tp_maquina_is2.Logica.Transferencia.Materia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juan_
 */
public class ListaPanel extends javax.swing.JPanel {
    private int cod_historia_usuario;
    /**
     * Creates new form ListaPanel
     */
    public ListaPanel(int cod_historia_usuario) {
        this.cod_historia_usuario=cod_historia_usuario;
        initComponents();
 
    }
    public void listaFinales(){
        HashMap<Materia, Integer> materias = new HashMap();
        materias=HistoriaAcademicaManager.listaExamenes(cod_historia_usuario);
        DefaultTableModel modelo = (DefaultTableModel) TablaMaterias.getModel();
        Set<Materia> keys = materias.keySet(); //obtengo las llaves 
        List<Entry<Materia, Integer>> list = new ArrayList<>(materias.entrySet()); //uso clase entry
        list.sort(Entry.comparingByValue()); //ordeno por nombre
        for ( Materia key : keys ) {
            modelo.addRow(new Object []{key.getCodigo(),key.getNombre()});
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelCambiable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaMaterias = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        PanelCambiable.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaMaterias);
        if (TablaMaterias.getColumnModel().getColumnCount() > 0) {
            TablaMaterias.getColumnModel().getColumn(0).setResizable(false);
            TablaMaterias.getColumnModel().getColumn(1).setResizable(false);
        }

        PanelCambiable.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 320, 220));

        jButton1.setText("Buscar finales a rendir!!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        PanelCambiable.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCambiable, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCambiable, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        listaFinales();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelCambiable;
    private javax.swing.JTable TablaMaterias;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
