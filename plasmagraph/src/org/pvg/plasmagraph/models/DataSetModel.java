package org.pvg.plasmagraph.models;

// Class Import Block
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import org.apache.commons.math3.util.Pair;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.HeaderData;
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
     * @param hd_reference Data - HeaderData reference provided by PlasmaGraph.
     * @param dr_reference Pairs - DataReference reference provided by PlasmaGraph.
     */
    public DataSetModel (Template t_reference, HeaderData hd_reference,
            DataReference dr_reference) {
        // Update currently-used Template and Data Sources.
        t = t_reference;
        hd = hd_reference;
        dr = dr_reference;
    }
    
    /**
     * Takes the current DataReference and inserts the pair names into a new
     * ListModel<String>.
     * 
     * @return A ListModel of Strings containing the pair names of DataReference.
     */
    public ComboBoxModel <String> resetGroupByBox () {	
    	// This int's value must not change.
    	int s = hd.size ();
    	
    	String [] group_array = new String [s + 1];
    	
    	group_array[0] = "None";
    	
    	if (s > 0) {
    		for (int i = 1; (i <= s); ++i) {
        		group_array[i] = hd.get (i - 1).getKey ();
        	}
    	}
    	
        
        return (new DefaultComboBoxModel <> (group_array));
    }
    
    /**
	 * Resets the status of the X Axis Column JComboBox to its default state based on
	 * the current status of the HeaderData object. Creates a new DefaultComboBoxModel
	 * and returns it.
	 * 
	 * @return A new DefaultComboBoxModel containing the String representations of each 
	 * header in "hd".
	 */
	public ComboBoxModel <String> resetXAxisColumn () {
		DefaultComboBoxModel <String> x_column = new DefaultComboBoxModel <String> ();
		
		for (Pair <String, ColumnType> p : this.hd) {
			x_column.addElement (p.getKey ());
		}
		
		return (x_column);
	}

	/**
	 * Resets the status of the Y Axis Column JComboBox to its default state based on
	 * the current status of the HeaderData object. Creates a new DefaultComboBoxModel
	 * and returns it.
	 * 
	 * @return A new DefaultComboBoxModel containing the String representations of each 
	 * header in "hd".
	 */
	public ComboBoxModel <String> resetYAxisColumn () {
		DefaultComboBoxModel <String> y_column = new DefaultComboBoxModel <String> ();
		
		for (Pair <String, ColumnType> p : this.hd) {
			y_column.addElement (p.getKey ());
		}
		
		return (y_column);
	}

	/**
	 * Changes the GraphPair in the DataReference object.
	 * 
	 * @param gColumn Name of the grouping Column.
	 * @param xColumn Name of the Column to assign to the X Axis.
	 * @param yColumn Name of the Column to assign to the Y Axis.
	 */
	public void setGraphPair (String gColumn, String xColumn, String yColumn) {
		
		this.dr.add (hd.get (hd.find (gColumn)), hd.find (gColumn),
				hd.get (hd.find (xColumn)), hd.find (xColumn),
				hd.get (hd.find (yColumn)), hd.find (yColumn));
	}
	
	/**
	 * @param groupingByElement
	 */
	public void changeGroup (String groupingByElement) {
		this.dr.changeGroup (hd.find (groupingByElement), groupingByElement);
	}

	/**
	 * @param xColumn
	 */
	public void changeXColumn (String xColumn) {
		this.dr.changeX (hd.find (xColumn), xColumn);
	}

	/**
	 * @param yColumn
	 */
	public void changeYColumn (String yColumn) {
		this.dr.changeY (hd.find (yColumn), yColumn);
	}
    
    /**
     * Getter method. Returns template.
     * 
     * @return t, a reference to the Template object.
     */
    public Template getTemplate () {
        return (t);
    }
    
    /**
	 * Getter method. Returns data reference.
     * 
     * @return dr, a reference to the DataReference object.
	 */
	public DataReference getReference () {
		return (dr);
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
