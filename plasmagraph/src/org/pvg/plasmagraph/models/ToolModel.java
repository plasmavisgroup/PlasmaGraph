package org.pvg.plasmagraph.models;

// Class Import Block
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.DataSet;
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
	// Externally-contained variables.
	/** Reference to MainModel's Template, passed via constructor reference. */
	private Template t;
	/** Reference to MainModel's DataSet, passed via constructor reference. */
	private DataSet ds;
	/** Reference to MainModel's DataReference, passed via constructor reference. */
	private DataReference dr;
	
	/**
	 * Creates a new ToolModel with references to MainModel's graph-manipulation objects,
	 * as well as creates and updates its respective view.
	 * 
	 * @param t_reference Template reference provided by MainModel.
	 * @param dr 
	 */
	public ToolModel (Template t_reference, DataSet ds_reference, DataReference dr_reference) {
		// Update currently-used Template and Data Sources.
		t = t_reference;
		ds = ds_reference;
		dr = dr_reference;
		
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

	public void outlier_search (DataSet ds, String selectedItem) {
		// TODO Auto-generated method stub
		OutlierSearch.scanForOutliers (ds);
		
	}

	public void interpolate(DataColumn col1, DataColumn col2, String selectedItem) {
		// TODO Auto-generated method stub
		Interpolator.interpolate (col1, col2);
	}
}
