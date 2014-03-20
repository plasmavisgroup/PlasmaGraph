package org.pvg.plasmagraph.controllers;

import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.pvg.plasmagraph.models.GraphModel;
import org.pvg.plasmagraph.views.GraphView;

/**
 * 
 * @author Gerardo A. Navas Morales
 */
public class GraphController {
	
	 /** Reference to model related to this controller. */
	GraphModel graph_model;
    /** Reference to view related to this controller. */
	GraphView graph_view;
    
	/**
	 * Constructor for GraphControllers. Used only by the PlasmaGraph class, and only used once.
	 * 
	 * @param graph_model Reference to this object's model.
	 * @param graph_view Reference to this object's view.
	 */
	public GraphController (GraphModel graph_model, GraphView graph_view) {
		this.graph_model = graph_model;
		this.graph_view = graph_view;
		
		// Automatically add listeners via view methods.
		// Update View Listener
        graph_model.addTemplateChangeListener (new GraphViewTemplateListener ());
        graph_model.addHeaderDataChangeListener (new GraphViewHeaderDataListener ());
        graph_model.addDataReferenceChangeListener (new GraphViewReferenceListener ());
	}
	
	/**
     * Listener for the Template that contains all settings for the program.
     * Relies on ChangeListener in order to know that a change has occurred
     * in the Template. 
     * 
     * @author Gerardo A. Navas Morales
     */
    class GraphViewTemplateListener implements ChangeListener {

        /**
         * Updates the DataSetView's current Template-based state.
         */
        @Override
        public void stateChanged (ChangeEvent e) {
            SwingWorker <Void, Void> template_worker = new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    graph_view.templateUpdate ();
                    return null;
                }
                
            };
            
            template_worker.run ();
        }
        
    }
    
    /**
     * Listener for the DataSet that contains all settings for the program.
     * Relies on ChangeListener in order to know that a change has occurred
     * in the DataSet. 
     * 
     * @author Gerardo A. Navas Morales
     */
    class GraphViewHeaderDataListener implements ChangeListener {

        /**
         * Updates the AestheticView's current Template-based state.
         */
        @Override
        public void stateChanged (ChangeEvent e) {
            SwingWorker <Void, Void> data_worker = new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    graph_view.headerUpdate ();
                    return null;
                }
                
            };
            
            data_worker.run ();
        }
        
    }
    
    /**
     * Listener for the DataReference that contains all settings for the program.
     * Relies on ChangeListener in order to know that a change has occurred
     * in the DataReference. 
     * 
     * @author Gerardo A. Navas Morales
     */
    
    class GraphViewReferenceListener implements ChangeListener {

        /**
         * Updates the AestheticView's current Template-based state.
         */
        @Override
        public void stateChanged (ChangeEvent e) {
            SwingWorker <Void, Void> reference_worker = new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    graph_view.referenceUpdate ();
                    return null;
                }
                
            };
            
            reference_worker.run ();
        }
        
    }

}
