package org.pvg.plasmagraph.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pvg.plasmagraph.controllers.DataSetController.GraphListener;
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
    /** Reference to graphing MVC related to this controller. */
	GraphController graph_controller;
    
    /**
     * Constructor for the ToolController class. Manages the creation of connectors between
     * the ToolView and ToolModel.
     * 
     * @param tool_model ToolModel reference.
     * @param tool_view ToolView reference.
     */
    public ToolController (ToolModel tool_model, ToolView tool_view, GraphController graph_controller) {
        // Set related objects into proper positions in object.
        this.tool_model = tool_model;
        this.tool_view = tool_view;
        this.graph_controller = graph_controller;
        
        // Automatically add listeners to Tools Tab via view
        // Update Template Listeners
        tool_view.addInterpolationTypeListener (new InterpolationTypeListener ());
        tool_view.addOutlierResponseListener (new OutlierResponseListener ());
        tool_view.addOutlierDistanceTypeListener (new OutlierDistanceTypeListener ());
        tool_view.addOutlierDistanceListener (new OutlierDistanceListener ());
        //tool_view.addLowerBoundListener (new InterpolationLowerBoundListener ());
        //tool_view.addUpperBoundListener (new InterpolationUpperBoundListener ());
        // Graph Trigger
        tool_view.addGraphListener (new GraphListener ());
     		
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
    class InterpolationTypeListener implements ItemListener {
        
        /**
         * Bridge between ToolView and ToolModel; changes Template's
         * Interpolation Type value.
         */
		@Override
		public void itemStateChanged (ItemEvent e) {
			// TODO Auto-generated method stub
			if (e.getStateChange () == ItemEvent.SELECTED) {
				tool_model.getTemplate ().setInterpolationType
	            (tool_view.getInterpolationType ());
	            
	            // Notify relevant listeners.
	            tool_model.getTemplate ().notifyListeners ();
			}
		}
        
    }
    
    /**
     * Listener for the Interpolation optional tool that is a part of the
     * ToolView pane.
     * Relies on ActionListener in order to know that a change in selected item
     * occured.
     * 
     * @author Gerardo A. Navas Morales
     */
 /*   class InterpolationLowerBoundListener extends FocusAdapter {
        
        *//**
         * Bridge between ToolView and ToolModel; changes Template's
         * Interpolation Lower Bound value.
         *//*
		@Override
		public void focusLost (FocusEvent e) {
			
			tool_model.getTemplate ().setLowerInterval (tool_view.getLowerInterval ());
			
			// Notify relevant listeners.
			tool_model.getTemplate ().notifyListeners ();
		}
        
    }*/
    
    /**
     * Listener for the Interpolation optional tool that is a part of the
     * ToolView pane.
     * Relies on ActionListener in order to know that a change in selected item
     * occured.
     * 
     * @author Gerardo A. Navas Morales
     */
/*    class InterpolationUpperBoundListener extends FocusAdapter {
        
        *//**
         * Bridge between ToolView and ToolModel; changes Template's
         * Interpolation Upper Bound value.
         *//*
    	@Override
		public void focusLost (FocusEvent e) {
    		
    		tool_model.getTemplate ().setUpperInterval (tool_view.getUpperInterval ());
			
			// Notify relevant listeners.
			tool_model.getTemplate ().notifyListeners ();
		}
        
    }*/
    
    /**
     * Listener for the Outlier Search optional tool that is a part of the
     * ToolView pane.
     * Relies on ActionListener in order to know that a change in selected item
     * occured.
     * 
     * @author Gerardo A. Navas Morales
     */
    class OutlierResponseListener implements ItemListener {
        
        /**
         * Updates the Template's Outlier Response value.
         */
		@Override
		public void itemStateChanged (ItemEvent e) {
			if (e.getStateChange () == ItemEvent.SELECTED) {
				 tool_model.getTemplate ().setOutlierResponse
		            (tool_view.getOutlierResponseType ());
		            
		         // Notify relevant listeners.
		            tool_model.getTemplate ().notifyListeners ();
			}
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
    class OutlierDistanceTypeListener implements ItemListener {
        
        /**
         * Updates the Template's Outlier Response value.
         */
		@Override
		public void itemStateChanged (ItemEvent e) {
			if (e.getStateChange () == ItemEvent.SELECTED) {
				 tool_model.getTemplate ().setOutlierDistanceType
		            (tool_view.getOutlierDistanceType ());
		            
		         // Notify relevant listeners.
		            tool_model.getTemplate ().notifyListeners ();
			}
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
    class OutlierDistanceListener extends FocusAdapter {
        
        /**
         * Updates the Template's Outlier Response value.
         */
		@Override
		public void focusLost (FocusEvent e) {
			tool_model.getTemplate ().setOutlierDistance
		    		(tool_view.getMaximumDistance ());
		    
			// Notify relevant listeners.
			tool_model.getTemplate ().notifyListeners ();
		}
        
    }
    
    /**
	 * Listener for the "graph" JButton part of the DataSetView. Relies
	 * on ActionListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class GraphListener implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent arg0) {
			graph_controller.graph ();
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
                    tool_view.updateView ();
                    return null;
                }
                
            };
            
            view_worker.run ();
        }
        
    }
}
