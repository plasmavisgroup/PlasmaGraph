package org.pvg.plasmagraph.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pvg.plasmagraph.models.ToolModel;
import org.pvg.plasmagraph.views.ToolView;

public class ToolController {
    /** Reference to model related to this controller. */
    private ToolModel tool_model;
    /** Reference to view related to this controller. */
    private ToolView tool_view;
    
    /**
     * 
     * @param tool_model
     * @param tool_view
     */
    public ToolController (ToolModel tool_model, ToolView tool_view) {
        // Set related objects into proper positions in object.
        this.tool_model = tool_model;
        this.tool_view = tool_view;
        
        // Automatically add listeners to Tools Tab via view.
        tool_view.addInterpolationListener (new InterpolationListener ());
        tool_view.addOutlierListener (new OutlierListener ());
        
        tool_view.addInterpolationTypeListener (new InterpolationTypeListener ());
        tool_view.addOutlierResponseListener (new OutlierResponseListener ());
        
        tool_model.addChangeListener (new ToolViewListener ());
    }
    
    /**
     * Listener for the Interpolation optional tool that is a part of the
     * ToolView pane.
     * Relies on ActionListener in order to manage messages.
     * 
     * @author Gerardo A. Navas Morales
     */
    class InterpolationListener implements ActionListener {
        
        /**
         * Calls a ToolModel method to begin the interpolation process.
         */
        @SuppressWarnings ("unchecked")
        @Override
        public void actionPerformed (ActionEvent arg0) {
            tool_model.interpolate (tool_view.getSelectedDataPairIndex ());
        }
        
    }
    
    /**
     * Listener for the Outlier Search optional tool that is a part of the
     * ToolView pane.
     * Relies on FocusListener in order to manage messages.
     * 
     * @author Gerardo A. Navas Morales
     */
    class OutlierListener implements ActionListener {
        
        /**
         * Calls a ToolModel method to being the Outlier Search process.
         */
        @SuppressWarnings ("unchecked")
        @Override
        public void actionPerformed (ActionEvent arg0) {
            tool_model.outlier_search (tool_view.getSelectedDataPairIndex ());
        }
        
    }
    
    /**
     * Listener for the Interpolation optional tool that is a part of the
     * ToolView pane.
     * Relies on ItemListener in order to know that a change in selected item
     * occured.
     * 
     * @author Gerardo A. Navas Morales
     */
    class InterpolationTypeListener implements ActionListener {
        
        /**
         * Bridge between ToolView and ToolModel; changes Template's
         * Interpolation Type value.
         */
        @Override
        public void actionPerformed (ActionEvent e) {
            tool_model.getTemplate ().setInterpolationType
            (tool_view.getInterpolationType ());
        }
        
    }
    
    /**
     * Listener for the Outlier Search optional tool that is a part of the
     * ToolView pane.
     * Relies on ItemListener in order to know that a change in selected item
     * occured.
     * 
     * @author Gerardo A. Navas Morales
     */
    class OutlierResponseListener implements ActionListener {
        
        /**
         * Response Type value.
         */
        @Override
        public void actionPerformed (ActionEvent e) {
            tool_model.getTemplate ().setOutlierResponse
            (tool_view.getOutlierResponseType ());
        }
        
    }
    
    /**
     * Listener for the Template that contains all settings for the program.
     * Relies on ChangeListener in order to know that a change has occurred
     * in the Template. 
     * 
     * @author Gerardo A. Navas Morales
     */
    class ToolViewListener implements ChangeListener {

        @Override
        public void stateChanged (ChangeEvent e) {
            SwingWorker <Void, Void> view_worker = new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    tool_view.updateView ();
                    return null;
                }
                
            };
            
            view_worker.run ();
        }
        
    }
}
