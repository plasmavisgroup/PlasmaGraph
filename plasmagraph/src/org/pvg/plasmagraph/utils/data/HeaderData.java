package org.pvg.plasmagraph.utils.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.math3.util.Pair;
import org.pvg.plasmagraph.utils.data.readers.MatlabProcessor;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
import org.pvg.plasmagraph.utils.exceptions.InvalidDataSizeException;
import org.pvg.plasmagraph.utils.types.ChartType;
import org.pvg.plasmagraph.utils.types.ColumnType;
import org.pvg.plasmagraph.utils.types.FileType;

/**
 * Container of Pair <String, ColumnType>s. Provides methods to create JFree 
 * HeaderDatas.
 * 
 * @author Gerardo A. Navas Morales
 */
public class HeaderData implements Iterable<Pair <String, ColumnType>> {
	// Event Firing
    /** Collection of listeners for any change that occurs in this HeaderData. */
    private Set <ChangeListener> listeners;
	/** Container for Pair <String, ColumnType>s. */
	private ArrayList <Pair<String, ColumnType>> columns;
	/** Container for Files containing data for this object. */
	private Pair <File, FileType> file;

	/**
	 * Constructor. Creates a new ArrayList of Pair <String, ColumnType>s for this object.
	 * There should only exist one HeaderData for any given time.
	 */
	public HeaderData () {
		this.columns = new ArrayList <> ();
		this.listeners = new HashSet <> ();
		this.file = null;
	}

	/**
	 * Allows a new Pair <String, ColumnType> into the HeaderData if and only if its length
	 * is the same as every other column. (Read: The first one is checked.)
	 * 
	 * @param o Pair <String, ColumnType> to add to the HeaderData.
	 * @return Boolean describing the success or failure of the action.
	 */
	public boolean add (Pair <String, ColumnType> o) {
		boolean b = this.columns.add (o);
		this.notifyListeners ();
		return (b);
	}
	
	/**
	 * Allows a new Pair <String, ColumnType> into the HeaderData if and only if its length
	 * is the same as every other column. (Read: The first one is checked.)
	 * 
	 * @param s String name of the column being added.
	 * @param o ColumnType of the column being added.
	 * @return Boolean describing the success or failure of the action.
	 */
	public boolean add (String s, ColumnType o) {
		return (this.add (new Pair<> (s, o)));
	}

	/**
	 * Removes a Pair <String, ColumnType> from the HeaderData.
	 * 
	 * @param o The Pair <String, ColumnType> to remove.
	 * @return Boolean describing the success or failure of the action.
	 */
	public boolean remove (Pair <String, ColumnType> o) {
		boolean b = this.columns.remove (o);
		this.notifyListeners ();
		return (b);
	}
	
	/**
	 * Searches all Pair <String, ColumnType>s for the column provided.
	 * 
	 * @param o The column being searched.
	 * @return The integer index of the Pair <String, ColumnType> being searched for.
	 */
	public int find (Pair <String, ColumnType> o) {
		return (this.columns.indexOf (o));
	}
	
	/**
	 * Searches all Pair <String, ColumnType>s for the column name provided.
	 * 
	 * @param o The name / key of the pair being searched for.
	 * @return The integer index of the Pair <String, ColumnType> being searched for.
	 */
	public int find (String o) {
		ListIterator <Pair<String, ColumnType>> find_iterator = this.columns.listIterator ();
		
		for (int i = 0; (i < this.size ()); ++i) {
			if (this.columns.get (i).getKey ().equals (o)) {
				return (i);
			}
		}

		return (-1);
	}

	/**
	 * Searches for a specific Pair <String, ColumnType>. Responds if it found it or not.
	 * 
	 * @param o Column being searched for.
	 * @return A boolean stating if the column was found or not.
	 */
	public boolean contains (Pair <String, ColumnType> o) {
		return (this.columns.contains (o));
	}

	/**
	 * Getter method. Provides access to a Pair <String, ColumnType> at an index's location.
	 * 
	 * @param i The index where the desired Pair <String, ColumnType> is located.
	 * @return The Pair <String, ColumnType> at the index location.
	 */
	public Pair <String, ColumnType> get (int i) {
		return (this.columns.get (i));
	}
	
	/**
	 * Getter method. Provides whether the one Pair <String, ColumnType> is a DoubleColumn.
	 * 
	 * @param index Integer value specifying the target column.
	 * @return Boolean stating if the column if of type Double.
	 */
	public boolean isDouble (int index) {
		return (this.columns.get (index).getValue () == ColumnType.DOUBLE);
	}
	
	/**
	 * Getter method. Provides whether the one Pair <String, ColumnType> is a StringColumn.
	 * 
	 * @param index Integer value specifying the target column.
	 * @return Boolean stating if the column if of type String.
	 */
	public boolean isString (int index) {
		return (this.columns.get (index).getValue () == ColumnType.STRING);
	}
	
	/**
	 * Getter method. Provides whether the one Pair <String, ColumnType> is a DateTimeColumn.
	 * 
	 * @param index Integer value specifying the target column.
	 * @return Boolean stating if the column if of type DateTime.
	 */
	public boolean isDateTime (int index) {
		return (this.columns.get (index).getValue () == ColumnType.DATETIME);
	}

	/**
	 * Getter method. Provides the size of the ArrayList contained.
	 * 
	 * @return Integer value of the current size of the ArrayList.
	 */
	public int size () {
		return (this.columns.size ());
	}

	/**
	 * Getter method. Provides a string representation of the ArrayList.
	 * 
	 * @return A String representation of the entire ArrayList.
	 */
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		
		for (Pair <String, ColumnType> p : this.columns) {
			sb.append (p.getKey ()).append (", ")
				.append (p.getValue ().toString ()).append ("\n");
		}
		
		return (sb.toString ());
	}

	/**
	 * Populates a DataSet based on the GraphPair provided and the files this object maintains.<p>
	 * Note that data may or may not be grouped; grouping status is based on what the GraphPair says.
	 * 
	 * @param p GraphPair object containing the columns to be graphed.
	 * @return A new DataSet containing a full set of data, ready for graphing.
	 */
	public DataSet populateData (GraphPair p) {
		
		DataSet ds = new DataSet (p);
		
		// Try to pull the data out of every file and append it into the current DataSet.
		try {
			
				ds = this.getData (file, p);
				
		} catch (Exception ex) {
			
			// Trying to grab two data sets when there's only one?
			System.out.println ("Error in HeaderData!\n" + ex.toString ());
			
		}
		
		// Regardless, return the DataSet.
		return (ds);
	}
	
	private DataSet getData (Pair <File, FileType> e, GraphPair p) 
			throws FunctionNotImplementedException, InvalidDataSizeException {
		
		DataSet ds;
		
		if (p.isGrouped ()) {
			ds = new DataSet (p);
			
			/*if (e.getValue ().equals (FileType.CSV)) {
				
				CSVProcessor csv_reader = new CSVProcessor (e.getKey ());
				
				csv_reader.toDataSet (ds, p, this);
				
			} else */if (e.getValue ().equals (FileType.MAT)) {
				
				MatlabProcessor mat_reader = new MatlabProcessor (e.getKey ());
				
				mat_reader.toDataSet (ds, p, this);
				
				System.out.println ("Derp: " + ds.toString ());
				
			} else {
				
				throw (new FunctionNotImplementedException 
						("Extracting data from non-CSV / MAT files."));
				
			}
			
		} else {
			
			ds = new DataSet (p);
			
			/*if (e.getValue ().equals (FileType.CSV)) {
				
				CSVProcessor csv_reader = new CSVProcessor (e.getKey ());
				
				csv_reader.toDataSet (ds, p, this);
				
			} else */if (e.getValue ().equals (FileType.MAT)) {
				
				MatlabProcessor mat_reader = new MatlabProcessor (e.getKey ());
				
				mat_reader.toDataSet (ds, p, this);
				
				System.out.println ("Derp: " + ds.toString ());
				
			} else {
				
				throw (new FunctionNotImplementedException 
						("Extracting data from non-CSV / MAT files."));
				
			}
			
		}
		
		return (ds);
	}
	
	// Iterator / Iterable methods.
	@Override
	public Iterator<Pair <String, ColumnType>> iterator () {
		return (this.columns.iterator ());
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

	// File Map methods.
	/**
	 * Helper method. Provides interface to file_list.
	 * 
	 * @param file File object to add to Map.
	 * @param type File extension of object.
	 * @return Boolean describinb the success or failure of the operation.
	 */
	public void setFile (File file, FileType type) {
		this.file = new Pair <File, FileType> (file, type);
	}
	
	/**
	 * Helper method. Provides interface to file_list.
	 * 
	 * @return File and FileType pair contained in this object.
	 */
	public Pair <File, FileType> getFile () {
		return (this.file);
	}
	
	/**
	 * Getter method. Provides information on the existance of data in this object.
	 * 
	 * @return True if this object has at least one pair of data; otherwise, false.
	 */
	public boolean isEmpty () {
		return (this.columns.isEmpty ());
	}

	/**
	 * Resets all data contained in this object, save for the listeners attached to it.
	 */
	public void reset () {
		this.columns.clear ();
		this.file = null;
	}

	/**
	 * Verifies if the chart to be made contains the correct ColumnTypes!
	 * 
	 * @param chart_type The ChartType object that defines the type of chart that will be made.
	 * @param p The GraphPair object containing the X Axis and Y Axis column indexes.
	 * @return True if the ColumnTypes of each column are of the proper types for their ChartType; else, False.
	 */
	public boolean hasValidGraphTypes (ChartType chart_type, GraphPair p) {	
		
		if (!this.isEmpty ()) {
			
			return (chart_type.hasProperColumns (this, p));
			
		} else {
			
			return (false);
			
		}
	}
}