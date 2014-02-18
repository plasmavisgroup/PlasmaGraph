/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvg.plasmagraph.views;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import org.jfree.chart.plot.PlotOrientation;
import org.pvg.plasmagraph.models.AestheticModel;

/**
 * View for the visual modification pane.
 * Defines the visual organization of a JPanel embedded
 * into MainView's JTabbedPane, and communicates changes
 * done to the View to the Model via the Controller's Listeners.
 * 
 * @author Gerardo A. Navas Morales
 */
@SuppressWarnings ("serial")
public class AestheticView extends javax.swing.JPanel {
    /** Reference to model related to this controller. */
    private AestheticModel aesthetic_model;
    
    /**
     * Creates a new AestheticView form, containing references to itsmodel and the underlying settings container.
     * @param aesthetic_model Model reference provided by PlasmaGraph.
     */
    public AestheticView (AestheticModel aesthetic_model) {
        this.aesthetic_model = aesthetic_model;
        
        // Initialize visual components, as per NetBeans IDE code.
        this.initComponents ();
    }
    
    // Created by Gerardo A. Navas Morales.
    /**
     * Updates AestheticView's TextBoxes and RadioButton Groups based on the current state of the Template.
     */
    public void updateView () {
        this.chart_title_text_box.setText (this.aesthetic_model.getTemplate ().getChartName ());
        this.x_axis_text_box.setText (this.aesthetic_model.getTemplate ().getXAxisLabel ());
        this.y_axis_text_box.setText (this.aesthetic_model.getTemplate ().getYAxisLabel ());
        this.horizontal_orientation.setSelected (this.aesthetic_model.getTemplate ().getOrientation () == PlotOrientation.HORIZONTAL);
        this.vertical_orientation.setSelected (this.aesthetic_model.getTemplate ().getOrientation () == PlotOrientation.VERTICAL);
    }
    
    // Created by NetBeans IDE.
    /**
     * Initializes the visual components of this view form.
     */
    private void initComponents () {
        this.setName ("Aesthetic View");
        
        plot_orientation_button_group = new javax.swing.ButtonGroup ();
        chart_title_label = new javax.swing.JLabel ();
        x_axis_label = new javax.swing.JLabel ();
        y_axis_label = new javax.swing.JLabel ();
        plot_orientation_label = new javax.swing.JLabel ();
        label_orientation_separator = new javax.swing.JSeparator ();
        chart_title_text_box = new javax.swing.JTextField (50);
        x_axis_text_box = new javax.swing.JTextField ();
        y_axis_text_box = new javax.swing.JTextField ();
        horizontal_orientation = new javax.swing.JRadioButton ();
        vertical_orientation = new javax.swing.JRadioButton ();
        
        chart_title_label.setText ("Chart Title");
        
        x_axis_label.setText ("X Axis Name");
        
        y_axis_label.setText ("Y Axis Name");
        
        plot_orientation_label.setText ("Plot Orientation");
        
        plot_orientation_button_group.add (horizontal_orientation);
        horizontal_orientation.setText ("Horizontal");
        
        plot_orientation_button_group.add (vertical_orientation);
        vertical_orientation.setText ("Vertical");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout (this);
        this.setLayout (layout);
        layout.setHorizontalGroup (layout
                .createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup (
                        layout.createSequentialGroup ()
                                .addContainerGap ()
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup (
                                                        layout.createSequentialGroup ()
                                                                .addGroup (
                                                                        layout.createParallelGroup (
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent (
                                                                                        x_axis_label)
                                                                                .addComponent (
                                                                                        chart_title_label)
                                                                                .addComponent (
                                                                                        y_axis_label))
                                                                .addGap (18,
                                                                        18, 18)
                                                                .addGroup (
                                                                        layout.createParallelGroup (
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent (
                                                                                        y_axis_text_box,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        247,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent (
                                                                                        chart_title_text_box,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        247,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent (
                                                                                        x_axis_text_box,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        247,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap (
                                                                        51,
                                                                        Short.MAX_VALUE))
                                                .addGroup (
                                                        layout.createSequentialGroup ()
                                                                .addComponent (
                                                                        plot_orientation_label)
                                                                .addGap (18,
                                                                        18, 18)
                                                                .addComponent (
                                                                        horizontal_orientation)
                                                                .addPreferredGap (
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent (
                                                                        vertical_orientation)
                                                                .addGap (
                                                                        0,
                                                                        0,
                                                                        Short.MAX_VALUE))))
                .addComponent (label_orientation_separator));
        layout.setVerticalGroup (layout
                .createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup (
                        layout.createSequentialGroup ()
                                .addContainerGap ()
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent (
                                                        chart_title_label)
                                                .addComponent (
                                                        chart_title_text_box,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap (18, 18, 18)
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent (x_axis_label)
                                                .addComponent (
                                                        x_axis_text_box,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap (
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent (y_axis_label)
                                                .addComponent (
                                                        y_axis_text_box,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap (
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent (label_orientation_separator,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap (4, 4, 4)
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent (
                                                        plot_orientation_label)
                                                .addComponent (
                                                        horizontal_orientation)
                                                .addComponent (
                                                        vertical_orientation))
                                .addContainerGap (144, Short.MAX_VALUE)));
    }
    
    // Variables declaration - do not modify
    private javax.swing.JLabel chart_title_label;
    private javax.swing.JTextField chart_title_text_box;
    private javax.swing.JRadioButton horizontal_orientation;
    private javax.swing.JSeparator label_orientation_separator;
    private javax.swing.ButtonGroup plot_orientation_button_group;
    private javax.swing.JLabel plot_orientation_label;
    private javax.swing.JRadioButton vertical_orientation;
    private javax.swing.JLabel x_axis_label;
    private javax.swing.JTextField x_axis_text_box;
    private javax.swing.JLabel y_axis_label;
    private javax.swing.JTextField y_axis_text_box;
    // End of variables declaration
    

    // Created by Gerardo A. Navas Morales.
    /**
     * Registers the "chart_name" text box as an object that should be
     * listened upon gaining and/or losing focus by the user.
     * 
     * @param chartNameListener
     *            FocusListener object provided by its Controller.
     */
    public void addChartNameListener (FocusListener chartNameListener) {
        this.chart_title_text_box.addFocusListener (chartNameListener);
    }
    
    /**
     * Registers the "x_axis_label" text box as an object that should be
     * listened upon gaining and/or losing focus by the user.
     * 
     * @param xAxisLabelListener
     *            FocusListener object provided by its Controller.
     */
    public void addXAxisLabelListener (FocusListener xAxisLabelListener) {
        this.x_axis_text_box.addFocusListener (xAxisLabelListener);
    }
    
    /**
     * Registers the "y_axis_label" text box as an object that should be
     * listened upon gaining and/or losing focus by the user.
     * 
     * @param yAxisLabelListener
     *            FocusListener object provided by its Controller.
     */
    public void addYAxisLabelListener (FocusListener yAxisLabelListener) {
        this.y_axis_text_box.addFocusListener (yAxisLabelListener);
    }
    
    /**
     * Registers the "horizontal_orientation" radio button as an object that
     * should be listened upon gaining and/or losing focus by the user.
     * 
     * @param horizontalOrientationListener
     *            FocusListener object provided by its Controller.
     */
    public void addHorizontalOrientationListener (
            ActionListener horizontalOrientationListener) {
        this.horizontal_orientation
                .addActionListener (horizontalOrientationListener);
    }
    
    /**
     * Registers the "vertical_orientation" radio button as an object that
     * should be listened upon gaining and/or losing focus by the user.
     * 
     * @param verticalOrientationListener
     *            FocusListener object provided by its Controller.
     */
    public void addVerticalOrientationListener (
            ActionListener verticalOrientationListener) {
        this.vertical_orientation
                .addActionListener (verticalOrientationListener);
    }
}
