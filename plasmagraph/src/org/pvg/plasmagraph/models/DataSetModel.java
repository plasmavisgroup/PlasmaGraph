package org.pvg.plasmagraph.models;

// Class Import Block
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderColumn;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.types.ColumnType;

/**
 * Model for the modification of PlasmaGraph's data sets.
 * Manipulates both the data sets that will be used in the
 * graphs as well as the HeaderDataView itself.
 * 
 * @author Plasma Visualization Group
 */
public class DataSetModel {
    // Externally-contained variables.
    /** Reference to MainModel's Template, passed via constructor reference. */
    private Template t;
    /** Reference to MainModel's HeaderData, passed via constructor reference. */
    private HeaderData hd;
    /** Reference to MainModel's GraphPair, passed via constructor reference. */
    private GraphPair p;
    
    /**
     * Creates a new HeaderDataModel with references to the data and settings,
     * as well as creates and updates its respective view.
     * 
     * @param t_reference
     *            Settings - Template reference provided by PlasmaGraph.
     * @param hd_reference Data - HeaderData reference provided by PlasmaGraph.
     * @param p_reference Pairs - GraphPair reference provided by PlasmaGraph.
     */
    public DataSetModel (Template t_reference, HeaderData hd_reference,
            GraphPair p_reference) {
        // Update currently-used Template and Data Sources.
        t = t_reference;
        hd = hd_reference;
        p = p_reference;
    }
    
    /**
     * Takes the current GraphPair and inserts the pair names into a new
     * ListModel<String>.
     * 
     * @return A ListModel of Strings containing the pair names of GraphPair.
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

		for (HeaderColumn p : this.hd) {
			if (ColumnType.DOUBLE.equals (p.getValue ())) {
				x_column.addElement (p.getKey ());
			}
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

		for (HeaderColumn p : this.hd) {
			if (ColumnType.DOUBLE.equals (p.getValue ())) {
				y_column.addElement (p.getKey ());
			}
		}
		
		return (y_column);
	}
	
	/**
	 * @param groupingByElement
	 */
	public void changeGroup (String groupingByElement) {
		if ("None".equals (groupingByElement) ||
				"".equals (groupingByElement)) {
			
			this.p.changeGroup (0, groupingByElement);
			
		} else {
			
			this.p.changeGroup (hd.find (groupingByElement), groupingByElement);
			
		}
	}

	/**
	 * @param xColumn
	 */
	public void changeXColumn (String xColumn) {
		this.p.changeX (hd.find (xColumn), xColumn);
	}

	/**
	 * @param yColumn
	 */
	public void changeYColumn (String yColumn) {
		this.p.changeY (hd.find (yColumn), yColumn);
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
     * Getter method. Returns GraphPair.
     * 
     * @return t, a reference to the GraphPair object.
     */
    public GraphPair getGraphPair () {
		// TODO Auto-generated method stub
		return (p);
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
     * Support method to add listeners to the GraphPair.
     * 
     * @param c Listener to add to GraphPair Notifier.
     */
	public void addGraphPairChangeListener (javax.swing.event.ChangeListener c) {
		p.addChangeListener (c);
	}
}
