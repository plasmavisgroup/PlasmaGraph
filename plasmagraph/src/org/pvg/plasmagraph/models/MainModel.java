package org.pvg.plasmagraph.models;

//Class Import Block
import org.pvg.plasmagraph.utils.data.DataFilter;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.template.Template;

/**
 * Primary Model for the PlasmaGraph product. 
 * Controls the primary JFrame's (MainView) view.
 * Manages MainView's menu option selections, and
 * deals with the modification of the Template, the DataSet, and DataFilter objects.
 * 
 * @author Gerardo A. Navas Morales
 */
public class MainModel {
	/** Reference to MainModel's Template, passed via constructor reference. */
	Template t;
	/** Reference to MainModel's DataSet, passed via constructor reference. */
	DataSet ds;
	/** Reference to MainModel's Template, passed via constructor reference. */
	DataFilter df;

	/**
	 * Creates a new MainModel with references to the data, settings and filter,
	 * as well as creates and updates its respective view.
	 * 
	 * @param t_reference Settings - Template reference provided by PlasmaGraph.
	 * @param ds_reference Data - DataSet reference provided by PlasmaGraph.
	 * @param df_reference Filter - DataFilter reference provided by PlasmaGraph.
	 */
	public MainModel (Template t_reference, DataSet ds_reference, DataFilter df_reference) {
		// Update currently-used Template, Data, and Data Filter Sources.
		t = t_reference;
		ds = ds_reference;
		df = df_reference;
		
		// Update current view.
		updateMainView ();
	}

	/**
	 * 
	 */
	private void updateMainView() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
