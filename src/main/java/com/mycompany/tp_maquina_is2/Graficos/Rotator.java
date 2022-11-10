/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp_maquina_is2.Graficos;

/**
 *
 * @author ivanb
 */
import java.awt.event.*;
import javax.swing.*;

import org.jfree.chart.plot.PiePlot3D;

public class Rotator extends Timer implements ActionListener {

    /**
     * The plot.
     */
    private PiePlot3D plot;

    /**
     * The angle.
     */
    private int angle = 270;

    /**
     * Constructor.
     *
     * @param plot the plot.
     */
    public Rotator(final PiePlot3D plot) {
        super(100, null);
        this.plot = plot;
        addActionListener(this);
    }

    /**
     * Modifies the starting angle.
     *
     * @param event the action event.
     */
    public void actionPerformed(final ActionEvent event) {
        this.plot.setStartAngle(this.angle);
        this.angle = this.angle + 1;
        if (this.angle == 360) {
            this.angle = 0;
        }
    }

}
