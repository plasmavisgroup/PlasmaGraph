package org.pvg.plasmagraph.utils.data;

import java.util.HashSet;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.math3.util.Pair;
import org.pvg.plasmagraph.utils.types.ColumnType;

/**
 * A reference-holding object, to be used parallel to DataSet, that maintains 
 * DataColumn indexes and their name on the DataSetView's JLists' ListModels.
 * An answer to avoid doubling up on data usage by not having multiple DataSets
 * for the graphing process.
 * 
 * @author Gerardo A. Navas Morales
 */
public class DataReference {
	/** Collection of listeners for any change that occurs in this DataReference. */
    private Set <ChangeListener> listeners;
	/** Container for Reference Pairs. */
	private GraphPair pair;
	
	/**
	 * Constructor; initializes the ArrayList container.
	 */
	public DataReference () {
		this.listeners = new HashSet <> ();
		pair = new GraphPair ();
	}
	
	/**
	 * Adds the Pair object components provided to it and responds if the process succeeded or not.
	 * 
	 * @param p GraphPair to add to this object.
	 */
	public void add (GraphPair p) {

		pair = new GraphPair (p.getGroup (), p.getGroupName (),
				p.getXIndex (), p.getXIndexName (),
				p.getYIndex (), p.getYIndexName ());
	}
	
	/**
	 * Adds the Pair object components provided to it and responds if the process succeeded or not.
	 * 
	 * @param pg Pair containing the group column, if it exists.
	 * @param pgi Index position of the group column, if it exists.
	 * @param p1 Pair containing the first column.
	 * @param p1i Index position of the first half of the pair object.
	 * @param p2 Pair containing the second columns.
	 * @param p2i Index position of the second half of the pair object.
	 */
	public void add (Pair<String, ColumnType> pg, int pgi,
			Pair<String, ColumnType> p1, int p1i,
			Pair<String, ColumnType> p2, int p2i) {

		pair = new GraphPair (pgi, pg.getKey (), 
				p1i, p1.getKey (), p2i, p2.getKey ());
	}
	
	/**
	 * Adds the Pair object components provided to it and responds if the process succeeded or not.
	 * 
	 * @param p1 Pair containing the first column.
	 * @param p1i Index position of the first half of the pair object.
	 * @param p2 Pair containing the second columns.
	 * @param p2i Index position of the second half of the pair object.
	 */
	public void add (Pair<String, ColumnType> p1, int p1i,
			Pair<String, ColumnType> p2, int p2i) {

		pair = new GraphPair (-1, "", 
				p1i, p1.getKey (), p2i, p2.getKey ());
	}
	
	/**
	 * Creates a new GraphPair based on the parameters.
	 * 
	 * @param group Group column index.
	 * @param group_name Group column name.
	 * @param column1 X Axis column index.
	 * @param column1_name X Axis column name.
	 * @param column2 Y Axis column index.
	 * @param column2_name Y Axis column name.
	 */
	public void add (int group, String group_name, 
			int column1, String column1_name, 
			int column2, String column2_name) {
		
		pair = new GraphPair (group, group_name,
				column1, column1_name,
				column2, column2_name);
	}
	
	/**
	 * Changes only the Group By column name and index for the GraphPair.
	 * 
	 * @param group Group column index.
	 * @param group_name Group column name.
	 */
	public void changeGroup (int group, String group_name) {
		
		this.pair.changeGroup (group, group_name);
		//System.out.println (this.pair.toString ());
	}
	
	/**
	 * Changes only the X Axis name and index for the GraphPair.
	 * 
	 * @param x X Axis column index.
	 * @param x_name X Axis column name.
	 */
	public void changeX (int x, String x_name) {
		
		this.pair.changeX (x, x_name);
		//System.out.println (this.pair.toString ());
	}

	/**
	 * Changes only the Y Axis name and index for the GraphPair.
	 * 
	 * @param y Y Axis column index.
	 * @param y_name Y Axis column name.
	 */
	public void changeY (int y, String y_name) {
	
		this.pair.changeY (y, y_name);
		//System.out.println (this.pair.toString ());
	}
	

	/**
	 * Removes the Pair object provided and sets it to its default GraphPair form.
	 */
	public void remove () {
		this.pair = new GraphPair ();
		this.notifyListeners ();
	}
	
	/**
	 * Removes all pairs from the DataReference object.
	 * Only used to reset the Pairs when an incompatible Data file is imported.
	 */
	public void reset () {
		this.pair = new GraphPair ();
		this.notifyListeners ();
	}
	
	/**
	 * Returns a pair as specified by an index.
	 * 
	 * @return The desired Pair at the provided index.
	 */
	public GraphPair get () {
	    return (pair);
	}
	
	@Override
	public String toString () {
	    String s = "";
	    
	    s += this.pair.toString () + "\n";
	    
	    return (s);
	}
	
	// Event Methods
	/**
	 * Adds the listener provided to the notification list.
	 * 
	 * @param listener Listener to add to the notification list.
	 */
	public void addChangeListener (ChangeListener listener) {
	    this.listeners.add (listener);
	}
	
	/**
	 * Removes the listener provided from the notification list.
	 * 
	 * @param listener Listener to remove from notification list.
	 */
	public void removeChangeListener (ChangeListener listener) {
	    this.listeners.remove (listener);
	}
	
	/**
	 * Sends a ChangeEvent to all listeners of this object,
	 * declaring that this Template object has been changed in some way.
	 */
	public void notifyListeners () {
	    for (ChangeListener c : listeners) {
	        c.stateChanged (new ChangeEvent (this));
	    }
	}

	/**
	 * Getter method. States the status of the data within this object.
	 * 
	 * @return True if a not-default GraphPair is in this object; else, False.
	 */
	public boolean isEmpty () {
		return (pair.isEmpty ());
	}
	
}