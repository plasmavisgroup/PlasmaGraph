package org.pvg.plasmagraph.utils.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;
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
 * <p>Container of Pairs of Strings and ColumnTypes that will be visible
 * by the GUI and are available to be graphed.
 * 
 * <p>This class also handles the creation of DataSets based on the GraphPair
 * provided to it via the use of its knowledge of the file that is currently being used.
 * 
 * <p>It is important to note that this class fires events! Therefore, this class
 * should not be made more than once. (This class may be made into a singleton
 * someday, but not yet.)
 * 
 * @author Plasma Visualization Group
 */
public class HeaderData implements Iterable<HeaderColumn> {
	// Event Firing
    /** Collection of listeners for any change that occurs in this HeaderData. */
    private Set <ChangeListener> listeners;
	/** Container for HeaderColumns. */
	private ArrayList <HeaderColumn> columns;
	/** Container for Files containing data for this object. */
	private Pair <File, FileType> file;
	/** Container for PlasmaGraph's MessageLog object. */
	private MessageLog ml;
	
	/**
	 * <p>Default / test constructor. Creates a new ArrayList of HeaderColumns for this object.
	 * There should only exist one HeaderData for any given time.
	 */
	public HeaderData () {
		this.columns = new ArrayList <> ();
		this.listeners = new HashSet <> ();
		this.file = null;
		this.ml = new MessageLog ();
	}
	
	/**
	 * <p>Constructor. Creates a new ArrayList of HeaderColumns for this object.
	 * <p>Contains the MessageLog object to pass messages to and from the FileProcessors used
	 * in this object.
	 * <p>There should only exist one HeaderData for any given time.
	 */
	public HeaderData (MessageLog ml) {
		this.columns = new ArrayList <> ();
		this.listeners = new HashSet <> ();
		this.file = null;
		this.ml = ml;
	}
	
	/**
	 * <p>Allows a new HeaderColumn into the HeaderData if and only if its length
	 * is the same as every other column. (Read: The first one is checked.)
	 * 
	 * @param o HeaderColumn to add to the collection.
	 * @return Boolean describing the success or failure of the action.
	 */
	public boolean add (HeaderColumn o) {
		boolean b = this.columns.add (o);
		this.notifyListeners ();
		return (b);
	}
	
	/**
	 * <p>Allows a new HeaderColumn into the HeaderData if and only if its length
	 * is the same as every other column. (Read: The first one is checked.)
	 * 
	 * @param v Variable name of the column being added.
	 * @param g Graph's name of the column being added.
	 * @param o ColumnType of the column being added.
	 * @return Boolean describing the success or failure of the action.
	 */
	public boolean add (String v, ColumnType o) {//String g, ColumnType o) {
		return (this.add (new HeaderColumn (v, o)));//g, o)));
	}

	/**
	 * <p>Removes a HeaderColumn from the HeaderData.
	 * 
	 * @param o The HeaderColumn to remove.
	 * @return Boolean describing the success or failure of the action.
	 */
	public boolean remove (HeaderColumn o) {
		boolean b = this.columns.remove (o);
		this.notifyListeners ();
		return (b);
	}
	
	/**
	 * <p>Removes a HeaderColumn from the HeaderData via its index position.
	 * 
	 * @param i The index value of the HeaderColumn to remove.
	 * @return Boolean describing the success or failure of the action.
	 */
	public boolean remove (int i) {
		boolean b = this.columns.remove (this.columns.get (i));
		this.notifyListeners ();
		return (b);
	}
	
	/**
	 * <p>Searches all HeaderColumns for the column provided.
	 * 
	 * @param o The column being searched.
	 * @return The integer index of the HeaderColumn being searched for.
	 */
	public int find (HeaderColumn o) {
		return (this.columns.indexOf (o));
	}
	
	/**
	 * <p>Searches all HeaderColumns for the column name provided.
	 * 
	 * @param o The variable name / key of the pair being searched for.
	 * @return The integer index of the HeaderColumn being searched for.
	 */
	public int find (String o) {
		for (int i = 0; (i < this.size ()); ++i) {
			if (this.columns.get (i).getVariableName ().equals (o)) {
				return (i);
			}
		}

		return (-1);
	}

	/**
	 * <p>Searches for a specific HeaderColumn. Responds if it found it or not.
	 * 
	 * @param o Column being searched for.
	 * @return A boolean stating if the column was found or not.
	 */
	public boolean contains (HeaderColumn o) {
		return (this.columns.contains (o));
	}

	/**
	 * <p>Getter method. Provides access to a HeaderColumn at an index's location.
	 * 
	 * @param i The index where the desired HeaderColumn is located.
	 * @return The HeaderColumn at the index location.
	 */
	public HeaderColumn get (int i) {
		return (this.columns.get (i));
	}
	
	/**
	 * <p>Getter method. Provides whether the one HeaderColumn is a DoubleColumn.
	 * 
	 * @param index Integer value specifying the target column.
	 * @return Boolean stating if the column if of type Double.
	 */
	public boolean isDouble (int index) {
		return (this.columns.get (index).getColumnType () == ColumnType.DOUBLE);
	}
	
	/**
	 * <p>Getter method. Provides whether the one HeaderColumn is a StringColumn.
	 * 
	 * @param index Integer value specifying the target column.
	 * @return Boolean stating if the column if of type String.
	 */
	public boolean isString (int index) {
		return (this.columns.get (index).getColumnType () == ColumnType.STRING);
	}
	
	/**
	 * <p>Getter method. Provides whether the one HeaderColumn is a DateTimeColumn.
	 * 
	 * <p>As of this writing (V. 1.0), this returns false because the code to manage them in
	 * graphs is currently not working.
	 * 
	 * @param index Integer value specifying the target column.
	 * @return Boolean stating if the column if of type DateTime.
	 */
	public boolean isDateTime (int index) {
		return (false); 
		//return (this.columns.get (index).getColumnType () == ColumnType.DATETIME);
	}

	/**
	 * <p>Getter method. Provides the size of the ArrayList contained.
	 * 
	 * @return Integer value of the current size of the ArrayList.
	 */
	public int size () {
		return (this.columns.size ());
	}

	/**
	 * <p>Getter method. Provides a string representation of the ArrayList.
	 * 
	 * @return A String representation of the entire ArrayList.
	 */
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		
		for (HeaderColumn p : this.columns) {
			sb.append (p.getKey ()).append (", ")
				.append (p.getValue ().toString ()).append ("\n");
		}
		
		return (sb.toString ());
	}

	/**
	 * <p>Populates a DataSet based on the GraphPair provided and the files this object maintains.
	 * 
	 * <p>Note that data may or may not be grouped; grouping status is based on what the GraphPair says.
	 * 
	 * @param p GraphPair object containing the columns to be graphed.
	 * @return A new DataSet containing a full set of data, ready for graphing.
	 */
	public DataSet populateData (GraphPair p) {
		
		DataSet ds = new DataSet (p);
		
		// Try to pull the data out of every file and append it into the current DataSet.
		try {
			
				ds = this.getData (file, p);
				
		} // Errors!
		// 1. What if the classes are incorrect? Developer Error.
		catch (ClassCastException ex) {
			JOptionPane.showMessageDialog (null,
					"Data Type mismatch: Cannot populate graph with data due to invalid type.");
		}
		
		// 2. What if the function isn't implemented yet?
		catch (FunctionNotImplementedException ex) {
			JOptionPane.showMessageDialog (null,
					"Functional Error: Cannot graph using data due to lack of means to process it.");
		}
		
		// 3. General exceptions.
		catch (Exception ex) {
			
			// Trying to grab two data sets when there's only one?
			System.out.println ("Error in HeaderData!\n" + ex.toString ());
			
		}
		
		// Regardless, return the DataSet.
		return (ds);
	}
	
	private DataSet getData (Pair <File, FileType> e, GraphPair p) 
			throws FunctionNotImplementedException, InvalidDataSizeException {
		
		DataSet ds = new DataSet (p);
			
		if (e.getValue ().equals (FileType.MAT)) {
			
			MatlabProcessor mat_reader = new MatlabProcessor (e.getKey (), ml);//, t.isShowingInfoMessages ());
			
			mat_reader.toDataSet (ds, p, this);
			
		} else {
			
			throw (new FunctionNotImplementedException 
					("Extracting data from non-MAT files."));
			
		}
		
		return (ds);
	}
	
	// Iterator / Iterable methods.
	@Override
	public Iterator<HeaderColumn> iterator () {
		return (this.columns.iterator ());
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
	 * declaring that this object has been changed in some way.
	 */
	public void notifyListeners () {
	    for (ChangeListener c : listeners) {
	        c.stateChanged (new ChangeEvent (this));
	    }
	}

	/**
	 * <p>Testing method. Prints out a list of the listeners interested in
	 * this object into the out Stream.
	 */
	public void printListeners () {
		for (ChangeListener c : listeners) {
			System.out.println (c.toString ());
		}
	}

	// File Map methods.
	/**
	 * <p>Helper method. Provides interface to file_list.
	 * 
	 * @param file File object to add to Map.
	 * @param type File extension of object.
	 */
	public void setFile (File file, FileType type) {
		this.file = new Pair <File, FileType> (file, type);
	}
	
	/**
	 * <p>Helper method. Provides interface to file_list.
	 * 
	 * @return File and FileType pair contained in this object.
	 */
	public Pair <File, FileType> getFile () {
		return (this.file);
	}
	
	/**
	 * <p>Getter method. Provides information on the existance of data in this object.
	 * 
	 * @return True if this object has at least one pair of data; otherwise, false.
	 */
	public boolean isEmpty () {
		return (this.columns.isEmpty ());
	}

	/**
	 * <p>Resets all data contained in this object, save for the listeners attached to it.
	 */
	public void reset () {
		this.columns.clear ();
		this.file = null;
	}

	/**
	 * <p>Verifies if the chart to be made contains the correct ColumnTypes!
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