package org.pvg.plasmagraph.utils.data;

import java.util.HashSet;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * <p>Container object for the DataReference class. Contains the indexes
 * of two different Data Columns in the main DataSet and their name on the Java
 * Swing ListModel.
 * 
 * <p>Note: The first index value is always assumed to be the "None" option.
 * 
 * <p>It is important to note that this class fires events! Therefore, this class
 * should not be made more than once. (This class may be made into a singleton
 * someday, but not yet.)
 * 
 * @author Plasma Visualization Group
 */
public class GraphPair {
	/** Index of the grouping DataColumn. 0 points towards no proper column selected. */
	private int group_index;
	/** Name of the grouping DataColumn. "None" points towards no proper column selected. */
	private String group_name;
	/** Index of the first DataColumn to graph. 0 points towards no proper column selected. */
	private int x_index;
	/** Name of the first DataColumn to graph. "None" points towards no proper column selected. */
	private String x_name;
	/** Index of the second DataColumn to graph. 0 points towards no proper column selected. */
	private int y_index;
	/** Name of the second DataColumn to graph. "None" points towards no proper column selected. */
	private String y_name;
	
	/** Collection of listeners for any change that occurs in this DataReference. */
    private Set <ChangeListener> listeners;
	
    /**
	 * <p>Default constructor; used for resetting in the DataReference.
	 */
	public GraphPair () {
		this.listeners = new HashSet <> ();
		this.reset ();
	}
	
	/**
	 * <p>Constructor; creates a new grouped Pair.
	 * 
	 * @param group Group index.
	 * @param group_name Group index name.
	 * @param column1 First index to graph.
	 * @param column1_name First column index name.
	 * @param column2 Second index to graph.
	 * @param column2_name Second column index name.
	 */
	public void setAll (int group, String group_name, 
			int column1, String column1_name, 
			int column2, String column2_name) {
		this.reset ();
		this.group_index = group;
		this.group_name = group_name;
		this.x_index = column1;
		this.x_name = column1_name;
		this.y_index = column2;
		this.y_name = column2_name;
		
		this.notifyListeners ();
	}
	
	/**
	 * <p>Changes only the Group By column name and index for the GraphPair.
	 * 
	 * @param group Group column index.
	 * @param group_name Group column name.
	 */
	public void changeGroup (int group, String group_name) {
		if (this.group_index < 0) {
			
			this.group_index = 0;
			
		} else {
			
			this.group_index = group;
			
		}
		
		this.group_name = group_name;
		
		this.notifyListeners ();
	}
	
	/**
	 * <p>Changes only the X Axis name and index for the GraphPair.
	 * 
	 * @param x X Axis column index.
	 * @param x_name X Axis column name.
	 */
	public void changeX (int x, String x_name) {
		if (this.x_index < 0) {
			
			this.x_index = 0;
			
		} else {
			
			this.x_index = x;
			
		}
		
		this.x_name = x_name;

		this.notifyListeners ();
	}

	/**
	 * <p>Changes only the Y Axis name and index for the GraphPair.
	 * 
	 * @param y Y Axis column index.
	 * @param y_name Y Axis column name.
	 */
	public void changeY (int y, String y_name) {
		if (this.y_index < 0) {
			
			this.y_index = 0;
			
		} else {
			
			this.y_index = y;
			
		}
		
		this.y_name = y_name;

		this.notifyListeners ();
	}

	/**
	 * <p>Getter; Provides the object's group DataColumn index value.
	 * 
	 * @return group_index, an index of the group DataColumn in the main DataSet.
	 */
	public int getGroup () {
		return (group_index);
		
	}
	
	/**
	 * <p>Getter; Provides the object's group DataColumn index value.
	 * 
	 * @return group_index, an index of the group DataColumn in the main DataSet.
	 */
	public String getGroupName () {
		return (group_name);
		
	}
	
	/**
	 * <p>Getter; Provides the object's first DataColumn index value.
	 * 
	 * @return x_index, an index of a DataColumn in the HeaderData.
	 */
	public int getXColumnIndex () {
		return (x_index);
	}
	
	/**
	 * <p>Getter; Provides the object's first DataColumn index value.
	 * 
	 * @return x_index, an index of a DataColumn in the main DataSet.
	 */
	public String getXIndexName () {
		return (x_name);
		
	}
	
	/**
	 * <p>Getter; Provides the object's first DataColumn index value.
	 * 
	 * @return x_index, an index of a DataColumn in the HeaderData.
	 */
	public int getYColumnIndex () {
		return (y_index);
	}
	
	/**
	 * <p>Getter; Provides the object's second DataColumn index value.
	 * 
	 * @return y_index, an index of a DataColumn in the main DataSet.
	 */
	public String getYIndexName () {
		return (y_name);
	}
	
	@Override
	public String toString () {
		return (group_name + ": <" + x_name + ", " + y_name + ">");
	}

	/**
	 * <p>Getter method. Returns the combination of both columns' names.
	 * 
	 * @return A String containing the combination of both column names.
	 */
	public String getName () {
		StringBuilder sb = new StringBuilder ();
		if (!this.isGrouped ()) {
			sb.append (this.x_name).append (" vs. ").append (this.y_name);
		}
		
		else {
			sb.append (this.getGroupName ()).append (" Group ");
		}
		
		return (sb.toString ());
	}
	
	/**
	 * <p>Getter method. Returns the index values for the group and columns.
	 * 
	 * @return A string containing the index values of all columns being used.
	 */
	public String getIndexes () {
		return (group_index + ": <" + x_index + ", " + y_index + ">");
	}

	/**
	 * <p>Getter method. States whether the data will be grouped or not.
	 * 
	 * @return True if the "group_index" and "group_name" variables are initialized; else, False.
	 */
	public boolean isGrouped () {
		return (this.group_index >= 0 && this.group_name != "None");
	}

	/**
	 * <p>Getter method. Returns the number of columns that this GraphPair
	 * is containing information on.
	 * 
	 * @return The integer 2 if this object isn't containing a grouping column; else, the integer 3.
	 */
	public int getNumberOfColumns () {
		return (this.isGrouped ()) ? (3) : (2);
	}
	
	/**
	 * <p>Getter method. Checks to see if the X Axis Column is ready to be graphed.
	 * 
	 * @return True if the "x_index" and "x_name" is initialized; else, False.
	 */
	public boolean isXColumnReady () {
		return (this.x_name != "None" && this.x_index >= 0);
	}
	
	/**
	 * <p>Getter method. Checks to see if the Y Axis Column is ready to be graphed.
	 * 
	 * @return True if the "y_index" and "y_name" is initialized; else, False.
	 */
	public boolean isYColumnReady () {
		return (this.y_name != "None" && this.y_index >= 0);
	}
	
	/**
	 * <p>Getter method. Tells if the X and Y Column halfs of this pair have
	 * been initialized at least once.
	 * 
	 * @return True if both changeX and changeY were called at least once, or
	 * if any non-default constructor was called originally; else, False.
	 */
	public boolean isReady () {
		return (this.isXColumnReady () && this.isYColumnReady ());
	}
	
	/**
	 * <p>Returns all the variables contained in this object back to their default state,
	 * as defined in the default constructor.
	 */
	public void reset () {
		this.group_index = 0;
		this.group_name = "None";
		this.x_index = 0;
		this.x_name = "None";
		this.y_index = 0;
		this.y_name = "None";
		
		this.notifyListeners ();
	}
	
	@Override
	public boolean equals (Object obj) {
		// Same object?
		if (this == obj) { return (true); }
		
		// Not the same class?
		if (!(obj instanceof GraphPair )) { return (false); }
		GraphPair c = (GraphPair) obj;
		
		// Same name?
		if (!this.toString().equals(c.toString())) { return (false); }
		
		// Then they're the same type of Pair.
		return (true);
	}
	
	// Event Methods
	/**
	 * <p>Adds the listener provided to the notification list.
	 * 
	 * @param listener Listener to add to the notification list.
	 */
	public void addChangeListener (ChangeListener listener) {
	    this.listeners.add (listener);
	}
	
	/**
	 * <p>Removes the listener provided from the notification list.
	 * 
	 * @param listener Listener to remove from notification list.
	 */
	public void removeChangeListener (ChangeListener listener) {
	    this.listeners.remove (listener);
	}
	
	/**
	 * <p>Sends a ChangeEvent to all listeners of this object,
	 * declaring that this Template object has been changed in some way.
	 */
	public void notifyListeners () {
	    for (ChangeListener c : listeners) {
	        c.stateChanged (new ChangeEvent (this));
	    }
	}
	
}