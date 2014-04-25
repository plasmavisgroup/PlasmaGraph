package org.pvg.plasmagraph.controllers;

import org.pvg.plasmagraph.models.GraphModel;
import org.pvg.plasmagraph.views.GraphView;

/**
 * 
 * @author Plasma Visualization Group
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
		// None
	}

    // Other methods.
    
	/**
	 * Forces the program to graph the chart immediately by calling the actual chart method.
	 */
	public void graph () {
		graph_view.graphUpdate ();
	}
}
