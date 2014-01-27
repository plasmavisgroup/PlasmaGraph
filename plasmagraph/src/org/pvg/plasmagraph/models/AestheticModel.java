package org.pvg.plasmagraph.models;

// Class Import Block
import org.pvg.plasmagraph.utils.template.Template;

/**
 * Model for the visual modification pane, AestheticView.
 * Controls a JPanel embedded into MainView's JTabbedPane, and
 * allows for modifications of the visual components of the data.
 * 
 * @author Gerardo A. Navas Morales
 */
public class AestheticModel {
	/** Reference to MainModel's Template, passed via constructor reference. */
	Template t;
	/** Reference to MainModel's DataSet, passed via constructor reference. */
	//DataSet ds;
	
	/**
	 * Creates a new AestheticModel with references to MainModel's graph-manipulation objects,
	 * as well as creates and updates its respective view.
	 * 
	 * @param t_reference Template reference provided by MainModel.
	 */
	public AestheticModel (Template t_reference) {
		// Update currently-used Template and Data Sources.
		t = t_reference;
		
		// Update current view.
		updateAestheticView ();
	}

	/**
	 * Updates AestheticView's TextBoxes and RadioButton Groups based on the current state of the Template.
	 */
	private void updateAestheticView () {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Updates MainView's Template from data provided via this object's AestheticView.
	 */
	private void updateTemplate () {
		// TODO Auto-generated method stub
		
	}
}
