package org.pvg.plasmagraph.models;

// Class Import Block
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import org.apache.commons.math3.util.Pair;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.types.ColumnType;

/**
 * Model for the modification of PlasmaGraph's data sets.
 * Manipulates both the data sets that will be used in the
 * graphs as well as the HeaderDataView itself.
 * 
 * @author Gerardo A. Navas Morales
 */
public class DataSetModel {
    // Externally-contained variables.
    /** Reference to MainModel's Template, passed via constructor reference. */
    private Template t;
    /** Reference to MainModel's HeaderData, passed via constructor reference. */
    private HeaderData hd;
    /** Reference to MainModel's DataReference, passed via constructor reference. */
    private DataReference dr;
    
    /**
     * Creates a new HeaderDataModel with references to the data and settings,
     * as well as creates and updates its respective view.
     * 
     * @param t_reference
     *            Settings - Template reference provided by PlasmaGraph.
     * @param ds_reference
     *            Data - HeaderData reference provided by PlasmaGraph.
     */
    public DataSetModel (Template t_reference, HeaderData hd_reference,
            DataReference dr_reference) {
        // Update currently-used Template and Data Sources.
        t = t_reference;
        hd = hd_reference;
        dr = dr_reference;
    }
    
    /**
     * Takes the current HeaderData and inserts the column names into a new
     * ListModel<String>.
     * 
     * @return A ListModel of Strings containing the column names of HeaderData.
     */
    public ListModel<String> resetAvailableList () {
    	// Reset the old lists.
    	DefaultListModel <String> list_available = new DefaultListModel <> ();
    	
    	// Populate AvailableHeaderDatasList's ListModel
    	for (Pair<String, ColumnType> p : hd) {
            list_available.addElement (p.getKey ());
        }
    	
    	return (list_available);
    }
    
    /**
     * Takes the current DataReference and inserts the pair names into a new
     * ListModel<String>.
     * 
     * @return A ListModel of Strings containing the pair names of DataReference.
     */
    public ListModel<String> resetSelectedList () {
    	// Reset the old lists.
    	DefaultListModel <String> list_selected = new DefaultListModel <> ();
    	
    	// Populate SelectedHeaderDatasList's ListModel
        for (GraphPair p : dr) {
        	list_selected.addElement (p.getName ());
        }
        
        return (list_selected);
    }
    
    /**
     * Adds all the elements in the list provided to the SelectedHeaderDatas List.
     * 
     * @param list List of elements to add to the SelectedHeaderDatas List.
     * @throws Exception Has no elements in the parameter provided.
	 */
    public void addToSelectedHeaderData (ArrayList <String> list) throws Exception {
        
        // We're attempting dangerous things that should throw errors.
        // First, check if there's the correct number of things in that list.
        if (list.size () == 2) {
        	
            // Bundle them into a pair and include that pair in DataReference "dr".
            GraphPair added_element = new GraphPair (hd.find (list.get (0)),
            		hd.find (list.get (1)), "" + list.get (0) + " vs. "
                            + list.get (1));
            
            if (!dr.add (added_element)) {
            	
            	throw (new Exception ("Adding into DataReference failed."));
            	
            }
            
        } else {
            // Otherwise, complain about having an empty list.
            // TODO: Make Exception specifically for Adding values.
            throw (new Exception ("There must be exactly two columns selected."));
        }
    }
    
    /**
     * Removes all the elements in the list provided from the SelectedHeaderDatas List.
     * 
     * @param list List of elements to remove from the SelectedHeaderDatas List.
     * @throws Exception Has no elements in the parameter provided.
	 */
    public void removeFromSelectedHeaderData (ArrayList <String> list) throws Exception {
        // We're attempting dangerous things that should throw errors.
        // First, check if there's something in the list.
        if (list.size () >= 1) {
            
            // For all the objects to remove...
            for (String s : list) {
            	
                // ... Remove them., if they exist. If they don't, do nothing.
                if (dr.contains (s)) {
                	
                    dr.remove (dr.findIndex (s));
                    
                } else {
                	
                	throw (new Exception ("Pair not found in reference list."));
                	
                }
                
            }
            
        } else {
            // Otherwise, complain about having an empty list.
            // TODO: Make Exception specifically for Removing values.
        	throw (new Exception ("In order to remove, there must be at least one Pair selected."));
        }
    }
    
    /**
     * Getter method. Returns template.
     * Used for HeaderDataView's "updateView ()" method.
     * 
     * @return t, a reference to the Template object.
     */
    public Template getTemplate () {
        return (t);
    }
    
    /**
     * Support method to add listeners to the Template.
     * 
     * @param c Listener to add to Template Notifier.
     */
    public void addTemplateChangeListener (javax.swing.event.ChangeListener c) {
        t.addChangeListener (c);
    }
    
    /**
     * Support method to add listeners to the HeaderData.
     * 
     * @param c Listener to add to HeaderData Notifier.
     */
    public void addHeaderDataChangeListener (javax.swing.event.ChangeListener c) {
    	hd.addChangeListener (c);
    }

    /**
     * Support method to add listeners to the DataReference.
     * 
     * @param c Listener to add to DataReference Notifier.
     */
	public void addDataReferenceChangeListener (javax.swing.event.ChangeListener c) {
		dr.addChangeListener (c);
	}
}
