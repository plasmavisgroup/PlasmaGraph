package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;

/**
 * Container of DataColumns. Provides methods to create JFree Datasets.
 * 
 * @author Gerardo A. Navas Morales
 */
@SuppressWarnings ("rawtypes")
public class DataSet implements Iterable<DataColumn> {
	// Event Firing
    /** Collection of listeners for any change that occurs in this DataSet. */
    private Set <ChangeListener> listeners;
	/** Container for DataColumns. */
	private ArrayList<DataColumn> values;

	/**
	 * Constructor. Creates a new ArrayList of DataColumns for this object.
	 * There should only exist one DataSet for any given time.
	 */
	public DataSet () {
		this.values = new ArrayList<DataColumn> ();
		this.listeners = new HashSet <ChangeListener> ();
	}

	/**
	 * Allows a new DataColumn into the DataSet if and only if its length
	 * is the same as every other column. (Read: The first one is checked.)
	 * 
	 * @param o
	 * @return Boolean describing the success or failure of the action.
	 */
	public boolean add (DataColumn o) {
		if (this.size () == 0) {
			return (this.values.add (o));
		} else {
			if (this.values.get (0).size () == o.size ()) {
				boolean b = this.values.add (o);
				this.notifyListeners ();
				return (b);
			} else {
				return (false);
			}
		}
	}

	/**
	 * Removes a DataColumn from the DataSet.
	 * 
	 * @param o The DataColumn to remove.
	 * @return Boolean describing the success or failure of the action.
	 */
	public boolean remove (DataColumn o) {
		boolean b = this.values.remove (o);
		this.notifyListeners ();
		return (b);
	}
	
	/**
	 * Searches all DataColumns for the column provided.
	 * 
	 * @param o The column being searched.
	 * @return The integer index of the DataColumn being searched for.
	 */
	public int find (DataColumn o) {
		return (this.values.indexOf (o));
	}
	
	/**
	 * Searches all DataColumns for the column name provided.
	 * 
	 * @param o The name being searched for.
	 * @return The integer index of the DataColumn being searched for.
	 */
	public int find (String o) {
		int index = -1;
		
		for (int i = 0; (i < values.size ()) && (index == -1); ++i) {
			if (values.get (i).getColumnName ().equals (o)) {
				index = i;
			}
		}
		
		return (index);
	}

	/**
	 * Searches for a specific DataColumn. Responds if it found it or not.
	 * 
	 * @param o Column being searched for.
	 * @return A boolean stating if the column was found or not.
	 */
	public boolean contains (DataColumn o) {
		return (this.values.contains (o));
	}

	/**
	 * Getter method. Provides access to a DataColumn at an index's location.
	 * 
	 * @param i The index where the desired DataColumn is located.
	 * @return The DataColumn at the index location.
	 */
	public DataColumn get (int i) {
		return (this.values.get (i));
	}
	
	/**
	 * Getter method. Provides whether the entire DataSet is populated by DoubleColumns.
	 * 
	 * @return Boolean stating if all the columns are of type Double.
	 */
	public boolean isDouble () {
		for (DataColumn c : this.values) {
			if (!c.containsDoubles ()) {
				return (false);
			}
		}
		return (true);
	}
	
	/**
	 * Getter method. Provides whether the one DataColumn is a DoubleColumn.
	 * 
	 * @param index Integer value specifying the target column.
	 * @return Boolean stating if the column if of type Double.
	 */
	public boolean isDouble (int index) {
		return (this.values.get (index).containsDoubles ());
	}
	
	/**
	 * Getter method. Provides whether the entire DataSet is populated by StringColumns.
	 * 
	 * @return Boolean stating if all the columns are of type String.
	 */
	public boolean isString () {
		for (DataColumn c : this.values) {
			if (!c.containsStrings ()) {
				return (false);
			}
		}
		return (true);
	}
	
	/**
	 * Getter method. Provides whether the one DataColumn is a StringColumn.
	 * 
	 * @param index Integer value specifying the target column.
	 * @return Boolean stating if the column if of type String.
	 */
	public boolean isString (int index) {
		return (this.values.get (index).containsStrings ());
	}

	/**
	 * Getter method. Provides the size of the ArrayList contained.
	 * 
	 * @return Integer value of the current size of the ArrayList.
	 */
	public int size () {
		return (this.values.size ());
	}

	/**
	 * Getter method. Provides a string representation of the ArrayList.
	 * 
	 * @return A String representation of the entire ArrayList.
	 */
	@Override
	public String toString () {
		return (this.values.toString ());
	}
	
	/**
	 * Getter method. Provides the column length for the first column, the representative for all other columns.
	 * 
	 * @return An integer length of the columns.
	 */
	public int getColumnLength () {
		if (this.values.size () > 0) {
			return (this.values.get (0).size ());
		} else {
			return (0);
		}
	}
	
	/**
	 * Getter method. Provides the column length for a given column.
	 * 
	 * @param index The column whose length will be checked.
	 * @return An integer length of the selected column.
	 */
	public int getColumnLength (int index) {
		return (this.values.get (index).size ());
	}

	/**
	 * Given a group of index values and a name, provides a JFree XYSeries Dataset
	 * for the purpose of graphing XY Graphs.
	 * 
	 * @param p Pair of index values with a pre-defined name.
	 * @return An XYSeries containing the desired data.
	 */
	public XYSeries toXYGraphDataset (Pair p) {
		XYSeries series = new XYSeries (p.getName ());
		
		if (this.values.get (p.getIndex1 ()).containsDoubles () &&
				this.values.get (p.getIndex2 ()).containsDoubles ()) {
			for (int row = 0; row < this.getColumnLength (); ++row) {
				series.add ((double) this.values.get (p.getIndex1 ()).get (row),
						((double) this.values.get (p.getIndex2 ()).get (row)));
			}
		}
		
		return (series);
	}
	
	/**
	 * Given a group of index values and a name, provides a JFree CategoryDataset
	 * for the purpose of graphing Bar Graphs.
	 * TODO: Make the method more robust. Add flexibility for varying combinations of double and string!
	 * 
	 * @param p Pair of index values with a pre-defined name.
	 * @return A DefaultCategoryDataset containing the desired data.
	 */
	public DefaultCategoryDataset toBarGraphDataset (Pair p) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset ();
		
		// Assume the X column (Column 0) has the category data.
		// Assume the Y column (Column 1) has the quantity data. (Cannot be a string)
		
		// We need to know what are the column types for each of them.
		if (this.isDouble (p.getIndex1 ())) {
			for (int row = 0; (row < this.getColumnLength ()); ++row) {
				dataset.addValue ((Number) (double) this.values.get (p.getIndex2 ()).get (row),
						p.getName (), (double) this.values.get (p.getIndex1 ()).get (row));
			}
		} else {
			for (int row = 0; (row < this.getColumnLength ()); ++row) {
				dataset.addValue ((Number) (double) this.values.get (p.getIndex2 ()).get (row),
						p.getName (), (String) this.values.get (p.getIndex1 ()).get (row));
			}
		}
		
		return (dataset);
	}

	/**
	 * 
	 * @return
	 */
	public Comparable<String> getDataSetName () {
		if (this.values.size () == 2) {
			return ("" + this.values.get(0).getColumnName () + " vs. " +
					this.values.get (1).getColumnName ());
		} else {
			return ("Default");
		}
	}
	
	// Iterator / Iterable methods.
	@Override
	public Iterator<DataColumn> iterator () {
		//this.position = 0;
		return (this.values.iterator ());
		//return (this);
	}
	

	/**
	 * Creates a double [][] containing all the values in this DataSet.
	 * TODO: Currently can only be used for 2-column data sets.
	 * TODO: Currently assumes all values are doubles. Check first..
	 * 
	 * @return A 2-columned DataSet converted to a double [][].
	 */
	public double[][] toArray () {
		// Prepare vehicle for double 2DArray creation.
		Array2DRowRealMatrix matrix = new Array2DRowRealMatrix (this.getColumnLength (), 2);
		
		// Get both row and col indexes and start transferring data to the matrix.
		for (int i = 0; (i < matrix.getRowDimension ()); ++i) {
			for (int j = 0; (j < matrix.getColumnDimension ()); ++j) {
				matrix.setEntry (i, j, (double) this.values.get (j).get (i));
			}
		}
		
		// Return a double 2DArray.
		return (matrix.getData ());
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
	 * declaring that this object has been changed in some way.
	 */
	public void notifyListeners () {
	    for (ChangeListener c : listeners) {
	        c.stateChanged (new ChangeEvent (this));
	    }
	}

	/**
	 * Testing method. Prints out a list of the listeners interested in
	 * this object into the out Stream.
	 */
	public void printListeners () {
		for (ChangeListener c : listeners) {
			System.out.println (c.toString ());
		}
	}
}