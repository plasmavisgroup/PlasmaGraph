package org.pvg.plasmagraph.models;

// Class Import Block
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.template.Template;

/**
 * Model for the modification of PlasmaGraph's data sets.
 * Manipulates both the data sets that will be used in the
 * graphs as well as the DataSetView itself.
 * 
 * @author Gerardo A. Navas Morales
 */
public class DataSetModel {
	/** Reference to MainModel's Template, passed via constructor reference. */
	Template t;
	/** Reference to MainModel's DataSet, passed via constructor reference. */
	DataSet ds;
	
	/**
	 * Creates a new DataSetModel with references to the data and settings,
	 * as well as creates and updates its respective view.
	 * 
	 * @param t_reference Settings - Template reference provided by PlasmaGraph.
	 * @param ds_reference Data - DataSet reference provided by PlasmaGraph.
	 */
	public DataSetModel (Template t_reference, DataSet ds_reference) {
		// Update currently-used Template and Data Sources.
		t = t_reference;
		ds = ds_reference;
		
		// Update current view.
		updateDataSetView ();
	}

	/**
	 * Updates DataSetView's TextBoxes and RadioButton Groups based on the current state of the Template.
	 */
	private void updateDataSetView () {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Updates MainView's Template from data provided via this object's DataSetView.
	 */
	private void updateTemplate () {
		// TODO Auto-generated method stub
		
	}
}
