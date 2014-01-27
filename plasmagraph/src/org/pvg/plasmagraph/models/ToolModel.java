package org.pvg.plasmagraph.models;

// Class Import Block
import org.pvg.plasmagraph.utils.template.Template;

// import utils.data.DataSet;
import org.pvg.plasmagraph.utils.tools.*;

/**
 * Model for the usage of additional tools to aid in data modelling.
 * Manipulates the ToolView to provide functionality to the multiple
 * tools available.
 * 
 * @author Gerardo A. Navas Morales
 */
public class ToolModel {
	/** Reference to MainModel's Template, passed via constructor reference. */
	Template t;
	/** Reference to MainModel's DataSet, passed via constructor reference. */
	// DataSet ds;
	
	/**
	 * Creates a new ToolModel with references to MainModel's graph-manipulation objects,
	 * as well as creates and updates its respective view.
	 * 
	 * @param t_reference Template reference provided by MainModel.
	 */
	public ToolModel (Template t_reference) {
		// Update currently-used Template and Data Sources.
		t = t_reference;
		
		// Update current view.
		updateToolView ();
	}

	/**
	 * Updates ToolView's TextBoxes and RadioButton Groups based on the current state of the Template.
	 */
	private void updateToolView () {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Updates MainView's Template from data provided via this object's ToolView.
	 */
	private void updateTemplate () {
		// TODO Auto-generated method stub
		
	}
}
