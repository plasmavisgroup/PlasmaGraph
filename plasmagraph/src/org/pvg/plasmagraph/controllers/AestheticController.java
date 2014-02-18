package org.pvg.plasmagraph.controllers;

//Class Import Block
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.plot.PlotOrientation;
import org.pvg.plasmagraph.models.AestheticModel;
import org.pvg.plasmagraph.views.AestheticView;

/**
 * Controller for the visual modification pane, AestheticView.
 * Controls a JPanel embedded into MainView's JTabbedPane, and
 * allows for communication between its View and Model.
 * 
 * @author Gerardo A. Navas Morales
 */
public class AestheticController {
    
    /** Reference to model related to this controller. */
    private AestheticModel aesthetic_model;
    /** Reference to view related to this controller. */
    private AestheticView aesthetic_view;
    
    /**
     * Creates a new Controller for the AestheticModel/View.
     * Manages messages (mostly FocusGained/Lost messages) sent by the user via
     * the View.
     * Relies on the Model to change data.
     * 
     * @param aesthetic_model Model component reference of the Aesthetic Panel.
     * @param aesthetic_view View component reference of the Aesthetic Panel.
     */
    public AestheticController (AestheticModel aesthetic_model,
            AestheticView aesthetic_view) {
        // Set related objects into proper positions in object.
        this.aesthetic_model = aesthetic_model;
        this.aesthetic_view = aesthetic_view;
        
        // Automatically add listeners to Aesthetic Tab via view.
        // Update Template Listeners
        aesthetic_view.addChartNameListener (new ChartNameListener ());
        aesthetic_view.addXAxisLabelListener (new XAxisLabelListener ());
        aesthetic_view.addYAxisLabelListener (new YAxisLabelListener ());
        aesthetic_view.addHorizontalOrientationListener (new HorizontalOrientationListener ());
        aesthetic_view.addVerticalOrientationListener (new VerticalOrientationListener ());
        // Update View Listener
        aesthetic_model.addChangeListener (new AestheticChangeTemplateListener ());
    }
    
    /**
     * Listener for the "chart_name" text box part of the AestheticView.
     * Relies on FocusListener in order to manage messages.
     */
    class ChartNameListener implements FocusListener {
        
        /** Empty; This class doesn't do anything when focus is gained. */
        @Override
        public void focusGained (FocusEvent e) {
            // Empty.
            
        }
        
        /**
         * Calls an AestheticModel method to change the Chart Title on the
         * Template.
         */
        @Override
        public void focusLost (FocusEvent e) {
            aesthetic_model.changeChartTitle (((JTextField) e.getSource ())
                    .getText ());
        }
        
    }
    
    /**
     * Listener for the "x_axis_label" text box part of the AestheticView.
     * Relies on FocusListener in order to manage messages.
     */
    class XAxisLabelListener implements FocusListener {
        
        /** Empty; This class doesn't do anything when focus is gained. */
        @Override
        public void focusGained (FocusEvent e) {
            // Empty.
            
        }
        
        /**
         * Calls an AestheticModel method to change the X Axis Label on the
         * Template.
         */
        @Override
        public void focusLost (FocusEvent e) {
            aesthetic_model.changeXAxisLabel (((JTextField) e.getSource ())
                    .getText ());
            
        }
        
    }
    
    /**
     * Listener for the "y_axis_label" text box part of the AestheticView.
     * Relies on FocusListener in order to manage messages.
     */
    class YAxisLabelListener implements FocusListener {
        
        /** Empty; This class doesn't do anything when focus is gained. */
        @Override
        public void focusGained (FocusEvent e) {
            // Empty.
            
        }
        
        /**
         * Calls an AestheticModel method to change the Y Axis Label on the
         * Template.
         */
        @Override
        public void focusLost (FocusEvent e) {
            aesthetic_model.changeYAxisLabel (((JTextField) e.getSource ())
                    .getText ());
            
        }
        
    }
    
    /**
     * Listener for the "horizontal_orientation" radio button part of the
     * AestheticView.
     * Relies on FocusListener in order to manage messages.
     */
    class HorizontalOrientationListener implements ActionListener {
        
        /**
         * Calls an AestheticModel method to change the Plot Orientation on the
         * Template to PlotOrientation.HORIZONTAL.
         */
        @Override
        public void actionPerformed (ActionEvent arg0) {
            aesthetic_model.changePlotOrientation (PlotOrientation.HORIZONTAL);
        }
        
    }
    
    /**
     * Listener for the "vertical_orientation" radio button part of the
     * AestheticView.
     * Relies on FocusListener in order to manage messages.
     */
    class VerticalOrientationListener implements ActionListener {
        
        /**
         * Calls an AestheticModel method to change the Plot Orientation on the
         * Template to PlotOrientation.VERTICAL.
         */
        @Override
        public void actionPerformed (ActionEvent e) {
            aesthetic_model.changePlotOrientation (PlotOrientation.VERTICAL);
        }
        
    }
    
    /**
     * Listener for the Template that contains all settings for the program.
     * Relies on ChangeListener in order to know that a change has occurred
     * in the Template. 
     * 
     * @author Gerardo A. Navas Morales
     */
    class AestheticChangeTemplateListener implements ChangeListener {

        /**
         * Updates the AestheticView's current Template-based state.
         */
        @Override
        public void stateChanged (ChangeEvent e) {
            SwingWorker <Void, Void> view_worker = new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    aesthetic_view.updateView ();
                    return null;
                }
                
            };
            
            view_worker.run ();
        }
        
    }
}
