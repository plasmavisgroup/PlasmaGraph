package org.pvg.plasmagraph.models;

// Class Import Block
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import org.pvg.plasmagraph.errors.ExceptionHandler;
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.Pair;
import org.pvg.plasmagraph.utils.graphs.ChartType;
import org.pvg.plasmagraph.utils.template.Template;

/**
 * Model for the modification of PlasmaGraph's data sets.
 * Manipulates both the data sets that will be used in the
 * graphs as well as the DataSetView itself.
 * 
 * @author Gerardo A. Navas Morales
 */
public class DataSetModel {
	// Externally-contained variables.
	/** Reference to MainModel's Template, passed via constructor reference. */
	private Template t;
	/** Reference to MainModel's DataSet, passed via constructor reference. */
	private DataSet ds;
	/** Reference to MainModel's DataReference, passed via constructor reference. */
	private DataReference dr;
	
	// Internally-contained variables.
	/** Container for all DataColumns found in the "available_dataset_list" JList. */
	private DefaultListModel<String> list_available;
	/** Container for all DataSets found in the "selected_dataset_list" JList. */
	private DefaultListModel<String> list_selected;
	
	/**
	 * Creates a new DataSetModel with references to the data and settings,
	 * as well as creates and updates its respective view.
	 * 
	 * @param t_reference Settings - Template reference provided by PlasmaGraph.
	 * @param ds_reference Data - DataSet reference provided by PlasmaGraph.
	 */
	public DataSetModel (Template t_reference, DataSet ds_reference, DataReference dr_reference) {
		// Update currently-used Template and Data Sources.
		t = t_reference;
		ds = ds_reference;
		dr = dr_reference;
		
		// Initialize ListModels
		resetListModels ();
	}

	/**
	 * 
	 */
	private void resetListModels() {
		// Reset the old lists.
		list_available = new DefaultListModel<String> ();
		list_selected = new DefaultListModel<String> ();
		
		// Populate AvailableDatasetsList's ListModel
		for (DataColumn dc : ds) {
			list_available.addElement (dc.toString());
		}
		
		// Populate SelectedDatasetsList's ListModel
		// Do not need to populate SelectedDatasetsList's ListModel
	}
	
	/**
	 * 
	 * @return
	 */
	public String getChartType () {
		return (t.getChartType());
	}
	
	/**
	 * 
	 * @return
	 */
	public ListModel<String> getAvailableListModel () {
		return (list_available);
	}

	/**
	 * 
	 * @return
	 */
	public ListModel<String> getSelectedListModel () {
		return (list_selected);
	}

	/**
	 * 
	 */
	public void changeChartType (String source) {
		this.t.setChartType (source);
	}

	/**
	 * 
	 */
	public void addToSelectedDataset (ArrayList<String> list) {
		// TODO: Verify that the DataSet doesn't exist already.
		
		// We're attempting dangerous things that should throw errors.
		// First, check if there's the correct number of things in that list.
		if (list.size() != 2) {
			// Bundle them into a pair and include that pair in DataReference "dr".
			// TODO: Wait for partner to provide DataSet code to remove "ds" method errors.
			Pair added_element = new Pair (ds.find (list.get(0)), ds.find (list.get(1)),
					"" + list.get(0) + " vs. " + list.get(1));
			dr.add(added_element);
			
			// Add the textual representation of that new DataSet to the ListModel.
			this.list_selected.addElement (added_element.getName());
			
		} else {
			// Otherwise, complain about having an empty list.
			// TODO: Make Exception specifically for Adding values.
			ExceptionHandler.createEmptyArrayException ();
		}
	}

	/**
	 * 
	 */
	public void removeFromSelectedDataset (ArrayList<String> list) {
		// We're attempting dangerous things that should throw errors.
		// First, check if there's something in the list.
		if (list.size() >= 1) {
			
			// For all the objects to remove...
			for (String s : list) {
				// ... Remove them., if they exist.
				// If they don't, do nothing.
				if (dr.contains(s)) {
					// TODO: Do we need to do error checking with these operations?
					this.list_selected.removeElement(s);
					dr.remove(dr.find(s));
				}
			}
			
		} else {
			// Otherwise, complain about having an empty list.
			// TODO: Make Exception specifically for Removing values.
			ExceptionHandler.createEmptyArrayException ();
		}
	}
	
	
}
