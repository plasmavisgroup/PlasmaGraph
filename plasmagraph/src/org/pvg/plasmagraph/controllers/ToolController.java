package org.pvg.plasmagraph.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pvg.plasmagraph.models.ToolModel;
import org.pvg.plasmagraph.views.ToolView;

/**
 * Controller for the Tool MVC.
 * Manages messages being sent around the program from the ToolView.
 * 
 * @author Gerardo A. Navas Morales
 */
public class ToolController {
    // Externally-contained variables.
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
        
        // Automatically add listeners to Tools Tab via view
        // Update Template Listeners
        tool_view.addInterpolationTypeListener (new InterpolationTypeListener ());
        tool_view.addOutlierResponseListener (new OutlierResponseListener ());
        // Update View Listeners
        tool_model.addTemplateChangeListener (new ToolViewTemplateListener ());
    }
    
    /**
     * Listener for the Interpolation optional tool that is a part of the
     * ToolView pane.
     * Relies on ActionListener in order to know that a change in selected item
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
            
            // Notify relevant listeners.
            tool_model.getTemplate ().notifyListeners ();
        }
        
    }
    
    /**
     * Listener for the Outlier Search optional tool that is a part of the
     * ToolView pane.
     * Relies on ActionListener in order to know that a change in selected item
     * occured.
     * 
     * @author Gerardo A. Navas Morales
     */
    class OutlierResponseListener implements ActionListener {
        
        /**
         * Updates the Template's Outlier Response value.
         */
        @Override
        public void actionPerformed (ActionEvent e) {
            tool_model.getTemplate ().setOutlierResponse
            (tool_view.getOutlierResponseType ());
            
         // Notify relevant listeners.
            tool_model.getTemplate ().notifyListeners ();
        }
        
    }
    
    /**
     * Listener for the Template that contains all settings for the program.
     * Relies on ChangeListener in order to know that a change has occurred
     * in the Template. 
     * 
     * @author Gerardo A. Navas Morales
     */
    class ToolViewTemplateListener implements ChangeListener {

        /**
         * Updates the ToolView's current Template-based state.
         */
        @Override
        public void stateChanged (ChangeEvent e) {
            SwingWorker <Void, Void> view_worker = new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    tool_view.updateTemplateView ();
                    return null;
                }
                
            };
            
            view_worker.run ();
        }
        
    }
}
