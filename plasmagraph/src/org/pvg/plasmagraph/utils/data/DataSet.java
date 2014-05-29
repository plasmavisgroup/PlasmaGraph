package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.pvg.plasmagraph.utils.exceptions.InvalidParametersException;
import org.pvg.plasmagraph.utils.types.ColumnType;

/**
 * <p>Container of the data in a graph before conversion into a JFreeChart Dataset type.
 * <p>Provides methods to create JFree Datasets.
 * 
 * @author Plasma Visualization Group
 */
public class DataSet {
	
	/** X Column variables. */
	String x_column_name;
	ArrayList <Double> x_column;
	
	/** Y Column variables. */
	String y_column_name;
	ArrayList <Double> y_column;
	
	/** Group Column variables.*/
	String group_column_name;
	ColumnType group_type;
	ArrayList <Double> group_column_double;
	ArrayList <String> group_column_string;
	
	/**
	 * <p>Basic constructor. Creates an ungrouped DataSet.
	 */
	public DataSet () {
		// Set names
		this.x_column_name = "";
		this.y_column_name = "";
		this.group_column_name = "None";
		
		// Set X / Y Column data.
		x_column = new ArrayList <> ();
		y_column = new ArrayList <> ();
		
		// Set Group Column data.
		this.group_type = ColumnType.NONE;
		group_column_double = null;
		group_column_string = null;
	}
	
	/**
	 * <p>Constructor. Creates an ungrouped DataSet.
	 * 
	 * @param p GraphPair object used to provide the Column names.
	 */
	public DataSet (GraphPair p) {
		// Set names
		this.x_column_name = p.getXIndexName ();
		this.y_column_name = p.getYIndexName ();
		this.group_column_name = "None";
		
		// Set X / Y Column data.
		x_column = new ArrayList <> ();
		y_column = new ArrayList <> ();
		
		// Set Group Column data.
		this.group_type = ColumnType.NONE;
		group_column_double = null;
		group_column_string = null;
	}
	
	/**
	 * <p>Constructor. Creates a grouped DataSet.
	 * 
	 * @param group_type The ColumnType of the Group Column.
	 */
	public DataSet (ColumnType group_type) {
		// Set names
		this.x_column_name = "";
		this.y_column_name = "";
		this.group_column_name ="";
		
		// Set X / Y Column data.
		x_column = new ArrayList <> ();
		y_column = new ArrayList <> ();
		
		// Set Group Column data.
		this.group_type = group_type;
		
		if (ColumnType.DOUBLE.equals (group_type)) {
			
			group_column_double = new ArrayList <> ();
			group_column_string = null;
			
		} else if (ColumnType.STRING.equals (group_type)) {
			
			group_column_double = null;
			group_column_string = new ArrayList <> ();
			
		} else { // if (ColumnType.NONE.equals (group_type)) {
			
			group_column_double = null;
			group_column_string = null;
			
		}
	}
	
	/**
	 * <p>Constructor. Creates a grouped DataSet.
	 * 
	 * @param group_type The ColumnType of the Group Column.
	 * @param p GraphPair object used to provide the Column names.
	 */
	public DataSet (ColumnType group_type, GraphPair p) {
		// Set names
		this.x_column_name = p.getXIndexName ();
		this.y_column_name = p.getYIndexName ();
		this.group_column_name = p.getGroupName ();
		
		// Set X / Y Column data.
		x_column = new ArrayList <> ();
		y_column = new ArrayList <> ();
		
		// Set Group Column data.
		this.group_type = group_type;
		
		if (ColumnType.DOUBLE.equals (group_type)) {
			
			group_column_double = new ArrayList <> ();
			group_column_string = null;
			
		} else if (ColumnType.STRING.equals (group_type)) {
			
			group_column_double = null;
			group_column_string = new ArrayList <> ();
			
		} else { // if (ColumnType.NONE.equals (group_type)) {
			
			group_column_double = null;
			group_column_string = null;
			
		}
	}
	
	/**
	 * <p>Constructor. Creates an ungrouped DataSet.
	 * 
	 * @param x_name Name to use for the X Column.
	 * @param y_name Name to use for the Y Column.
	 */
	public DataSet (String x_name, String y_name) {
		// Set names
		this.x_column_name = x_name;
		this.y_column_name = y_name;
		this.group_column_name ="";
		
		// Set X / Y Column data.
		x_column = new ArrayList <> ();
		y_column = new ArrayList <> ();
		
		// Set Group Column data.
		this.group_type = ColumnType.NONE;
		this.group_column_double = null;
		this.group_column_string = null;
	}
	
	/**
	 * <p>Constructor. Creates a grouped DataSet.
	 * 
	 * @param group_type The ColumnType of the Group Column.
	 * @param x_name Name to use for the X Column.
	 * @param y_name Name to use for the Y Column.
	 * @param group_name Name to use for the Group Column.
	 */
	public DataSet (ColumnType group_type, String x_name, String y_name, 
			String group_name) {
		// Set names
		this.x_column_name = x_name;
		this.y_column_name = y_name;
		this.group_column_name = group_name;
		
		// Set X / Y Column data.
		x_column = new ArrayList <> ();
		y_column = new ArrayList <> ();
		
		// Set Group Column data.
		this.group_type = group_type;
		
		if (ColumnType.DOUBLE.equals (group_type)) {
			
			group_column_double = new ArrayList <> ();
			group_column_string = null;
			
		} else if (ColumnType.STRING.equals (group_type)) {
			
			group_column_double = null;
			group_column_string = new ArrayList <> ();
			
		} else { // if (ColumnType.NONE.equals (group_type)) {
			
			group_column_double = null;
			group_column_string = null;
			
		}
	}
	
	// Add methods.
	
	// Assumption: It is assumed that any method putting values into this object
	// will make sure that each column has the same number of objects.
	/**
	 * <p>Appends an additional double value to the X Column.
	 * 
	 * @param d The double value to include in the X Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean addToX (double d) {
		return (x_column.add (d));
	}

	/**
	 * <p>Appends the entirety of a doubles array into the X Column.
	 * 
	 * @param d The doubles values to add into the X Column.
	 * @return A boolean describing the success of the operations.
	 */
	public boolean addToX (double [] d) {
		ArrayList <Double> doubles_collection = new ArrayList <Double> (d.length);
		
		for (int i = 0; i < d.length; ++i) {
			doubles_collection.add (d[i]);
		}
		
		return (x_column.addAll (doubles_collection));
	}
	
	/**
	 * <p>Appends an additional doubles value into the Y Column.
	 * 
	 * @param d The double value to add into the Y Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean addToY (double d) {
		return (y_column.add (d));
	}
	
	/**
	 * <p>Appends the entirety of a doubles array into the Y Column.
	 * 
	 * @param d The doubles values to add into the Y Column.
	 * @return A boolean describing the success of the operations.
	 */
	public boolean addToY (double [] d) {
		ArrayList <Double> doubles_collection = new ArrayList <Double> (d.length);
		
		for (int i = 0; i < d.length; ++i) {
			doubles_collection.add (d[i]);
		}
		
		return (y_column.addAll (doubles_collection));
	}
	
	/**
	 * <p>Appends the entirety of a doubles array into the Group Column,
	 * if the data will be grouped.
	 * 
	 * @param d The double value to add into the Group Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean addToGroup (double d) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			return (group_column_double.add (d));
		} else {
			return (false);
		}
	}
	
	/**
	 * <p>Appends the entirety of a doubles array into the Group Column,
	 * if the data will be grouped.
	 * 
	 * @param d The doubles values to add into the Group Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean addToGroup (double [] d) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			ArrayList <Double> doubles_collection = new ArrayList <Double> (d.length);
			
			for (int i = 0; i < d.length; ++i) {
				doubles_collection.add (d[i]);
			}
			
			return (group_column_double.addAll (doubles_collection));
		} else {
			return (false);
		}
	}
	
	/**
	 * <p> Appends a single String value into the Group Column,
	 * if the data will be grouped.
	 * 
	 * @param s The String value to add into the Group Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean addToGroup (String s) {
		if (ColumnType.STRING.equals (group_type)) {
			return (group_column_string.add (s));
		} else {
			return (false);
		}
	}
	
	/**
	 * <p>Appends the entirety of a String array into the Group Column,
	 * if the data will be grouped.
	 * 
	 * @param s The String values to add into the Group Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean addToGroup (String [] s) {
		if (ColumnType.STRING.equals (group_type)) {
			ArrayList <String> Strings_collection = new ArrayList <String> (s.length);
			
			for (int i = 0; i < s.length; ++i) {
				Strings_collection.add (s[i]);
			}
			
			return (group_column_string.addAll (Strings_collection));
		} else {
			return (false);
		}
	}
	
	// Remove methods.
	public boolean remove (int row) {
		if (this.isGrouped ()) {
			return (this.removeFromX (row) && 
					this.removeFromY (row) && 
					this.removeFromGroup (row));
		} else {
			return (this.removeFromX (row) && 
					this.removeFromY (row));
		}
	}
	/**
	 * <p>Removes the first value found with the value provided in the X Column.
	 * 
	 * @param d The double value to remove from the X Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean removeFromX (double d) {
		return (x_column.remove (d));
	}
	
	/**
	 * <p>Removes the value found in the index provided in the X Column.
	 * 
	 * @param i The index of the value to remove from the X Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean removeFromX (int i) {
		return (x_column.remove (i) != null);
	}
	
	/**
	 * <p>Removes the first value found with the value provided in the Y Column.
	 * 
	 * @param d The double value to remove from the Y Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean removeFromY (double d) {
		return (y_column.remove (d));
	}
	
	/**
	 * <p>Removes the value found in the index provided in the Y Column.
	 * 
	 * @param i The index of the value to remove from the Y Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean removeFromY (int i) {
		return (y_column.remove (i) != null);
	}
	
	/**
	 * <p>Removes the double value found with the value provided in the Group Column.
	 * 
	 * @param d The double value to remove from the Group Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean removeFromGroup (double d) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			return (group_column_double.remove (d));
		} else {
			return (false);
		}
	}
	
	/**
	 * <p>Removes the String value found with the value provided in the Group Column.
	 * 
	 * @param s The String value to remove from the Group Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean removeFromGroup (String s) {
		if (ColumnType.STRING.equals (group_type)) {
			return (group_column_string.remove (s));
		} else {
			return (false);
		}
	}
	
	/**
	 * <p>Removes the value found in the index value provided in the Group Column.
	 * 
	 * @param i The index value to remove from the Group Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean removeFromGroup (int i) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			
			return (group_column_double.remove (i) != null);
			
		} else if (ColumnType.STRING.equals (group_type)) {
			
			return (group_column_string.remove (i) != null);
			
		} else {
			
			return (false);
		}
	}
	
	// Get methods.
	/**
	 * <p>Getter method. Provides the value found at the index of the X Column.
	 * 
	 * @param i The index value of the value to obtain.
	 * @return The double value at the index location.
	 */
	public double getXValue (int i) {
		return (this.x_column.get (i));
	}
	
	/**
	 * <p>Getter method. Provides the value found at the index of the Y Column.
	 * 
	 * @param i The index value of the value to obtain.
	 * @return The double value at the index location.
	 */
	public double getYValue (int i) {
		return (this.y_column.get (i));
	}
	
	/**
	 * <p>Getter method. Provides the double value found at the index of the Group Column.
	 * 
	 * @param i The index value of the value to obtain.
	 * @return The double value at the index location.
	 */
	public double getGroupDoubleValue (int i) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			return (this.group_column_double.get (i));
		} else {
			return (Double.NaN);
			//throw (new InvalidTypeException ("Group Column"));
		}
	}
	
	/**
	 * <p>Getter method. Provides the String value found at the index of the Group Column.
	 * 
	 * @param i The index value of the value to obtain.
	 * @return The String value at the index location.
	 */
	public String getGroupStringValue (int i) {
		if (ColumnType.STRING.equals (group_type)) {
			return (this.group_column_string.get (i));
		} else {
			return ("NaN");
			//throw (new InvalidTypeException ("Group Column"));
		}
	}
	
	/**
	 * <p>Getter method. Provides the X Column's name.
	 * 
	 * @return The value in the variable "x_column_name".
	 */
	public String getXName () {
		return (this.x_column_name);
	}
	
	/**
	 * <p>Getter method. Provides the Y Column's name.
	 * 
	 * @return The value in the variable "y_column_name".
	 */
	public String getYName () {
		return (this.y_column_name);
	}
	
	/**
	 * <p>Getter method. Provides the Group Column's name.
	 * 
	 * @return The value in the variable "group_column_name".
	 */
	public String getGroupName () {
		return (this.group_column_name);
	}
	
	/**
	 * <p>Getter method. Provides the Group Column's ColumnType.
	 * 
	 * @return The value in the variable "group_type".
	 */
	public ColumnType getGroupType () {
		return (this.group_type);
	}
	
	/**
	 * <p>Getter method. Provides the size of the ArrayList contained.
	 * 
	 * @return Integer value of the current size of the ArrayLists.
	 */
	public int size () {
		return (this.x_column.size ());
	}
	
	/**
	 * <p>Getter method. Provides the list of values contained in the X Column.
	 * 
	 * @return The variable "x_column".
	 */
	public List <Double> getX () {
		return (this.x_column);
	}
	
	/**
	 * <p>Getter method. Provides the list of values contained in the Y Column.
	 * 
	 * @return The variable "y_column".
	 */
	public List <Double> getY () {
		return (this.y_column);
	}
	
	// Find and contains methods.
	/**
	 * <p>Tells if a value is contained in the X Column.
	 * 
	 * @param d The double value to search for in the X Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean containsX (double d) {
		return (x_column.contains (d));
	}
	
	/**
	 * <p>Tells if a value is contained in the Y Column.
	 * 
	 * @param d The double value to search for in the Y Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean containsY (double d) {
		return (y_column.contains (d));
	}
	
	/**
	 * <p>Tells if a value is contained in the Group Column, assuming there is one.
	 * 
	 * @param d The double value to search for in the Group Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean containsGroup (double d) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			return (group_column_double.contains (d));
		} else {
			return (false);
		}
	}
	
	/**
	 * <p>Tells if a value is contained in the Group Column, assuming there is one.
	 * 
	 * @param s The String value to search for in the Group Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean containsGroup (String s) {
		if (ColumnType.STRING.equals (group_type)) {
			return (group_column_string.contains (s));
		} else {
			return (false);
		}
	}
	
	/**
	 * <p>Tells if the value provided exists in the X Column.
	 * 
	 * @param o The X Column value.
	 * @return The row index position of the values provided, or -1 if it wasn't found.
	 */
	public int findX (double o) {
		for (int i = 0; (i < this.x_column.size ()); ++i) {
			if (this.x_column.get (i) == o) {
				return (i);
			}
		}
		
		return (-1);
	}
	
	/**
	 * <p>Tells if the value provided exists in the Y Column.
	 * 
	 * @param o The Y Column value.
	 * @return The row index position of the values provided, or -1 if it wasn't found.
	 */
	public int findY (double o) {
		for (int i = 0; (i < this.y_column.size ()); ++i) {
			if (this.y_column.get (i) == o) {
				return (i);
			}
		}
		
		return (-1);
	}
	
	/**
	 * <p>Tells if the values provided exists in the X and Y Column if there exists a Group column.
	 * 
	 * @param x The X Column value.
	 * @param y The Y Column value.
	 * @return The row index position of the values provided, or -1 if it wasn't found or there is no Group column in this DataSet.
	 */
	public int findGroup (double x, double y) {
		if (!ColumnType.NONE.equals (this.group_type)) {
			for (int i = 0; (i < this.x_column.size ()) && 
					(i < this.y_column.size ()); ++i) {
				
				if ((this.x_column.get (i) == x) && (this.y_column.get (i) == y)) {
					return (i);
				}
			}	
		}
		
		return (-1);
	}
	
	// Set methods.
	/**
	 * <p>Changes the values found in the X Column to those provided.
	 * 
	 * @param d The double array to insert into the X Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean setToX (double [] d) {
		ArrayList <Double> x = new ArrayList <> (d.length);
		
		for (int i = 0; i < d.length; ++i) {
			x.add (d[i]);
		}
		
		return (setToX (x));
	}
	
	/**
	 * <p>Changes the values found in the X Column to those provided.
	 * 
	 * @param d The ArrayList to insert into the X Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean setToX (ArrayList <Double> d) {
		x_column.clear ();
		return (x_column.addAll (d));
	}
	
	/**
	 * <p>Changes the values found in the Y Column to those provided.
	 * 
	 * @param d The double array to insert into the Y Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean setToY (double [] d) {

		ArrayList <Double> y = new ArrayList <> (d.length);
		
		for (int i = 0; i < d.length; ++i) {
			y.add (d[i]);
		}
		
		return (setToY (y));
	}
	
	/**
	 * <p>Changes the values found in the Y Column to those provided.
	 * 
	 * @param d The ArrayList to insert into the Y Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean setToY (ArrayList <Double> d) {
		x_column.clear ();
		return (y_column.addAll (d));
	}
	
	/**
	 * <p>Changes the values found in the Group Column to those provided.
	 * 
	 * @param d The double array to insert into the Group Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean setToGroup (double [] d) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			group_column_double.clear ();
			
			boolean completion = false;
			for (int i = 0; i < d.length; ++i) {
				completion = group_column_double.add (d[i]);
			}
			
			return (completion);
		} else {
			return (false);
		}
	}
	
	/**
	 * <p>Changes the values found in the Group Column to those provided.
	 * 
	 * @param s The String array to insert into the Group Column.
	 * @return A boolean describing the success of the operation.
	 */
	public boolean setToGroup (String [] s) {
		if (ColumnType.STRING.equals (group_type)) {
			group_column_string.clear ();
			
			boolean completion = false;
			for (int i = 0; i < s.length; ++i) {
				completion = group_column_string.add (s[i]);
			}
			
			return (completion);
		} else {
			return (false);
		}
	}
	
	/**
	 * <p>Setter method. Changes the ColumnType of the Group Column to the provided value.
	 * 
	 * @param c The new ColumnType variable.
	 */
	public void setGroupType (ColumnType c) {
		this.group_type = c;
		
		if (ColumnType.DOUBLE.equals (this.group_type)) {
			
			this.group_column_double = new ArrayList <> ();
			this.group_column_string = null;
			
		} else if (ColumnType.STRING.equals (this.group_type)) {
			
			this.group_column_string = new ArrayList <> ();
			this.group_column_double = null;
			
		} else {
			
			this.group_column_double = null;
			this.group_column_string = null;
			
		}
	}
	
	// Checking methods.
	/**
	 * <p>Getter method. Provides whether the group column is of the type Double.
	 * 
	 * @return Boolean stating if the group column is of the type Double.
	 */
	public boolean isGroupDouble () {
		return (ColumnType.DOUBLE.equals (group_type));
	}
	
	/**
	 * <p>Getter method. Provides whether the group column is of the type String.
	 * 
	 * @return Boolean stating if the group column is of the type Double.
	 */
	public boolean isGroupString () {
		return (ColumnType.STRING.equals (group_type));
	}
	
	// Conversion methods.
	
	/**
	 * <p>Given a group of index values and a name, provides a JFree XYSeries Dataset
	 * for the purpose of graphing XY Graphs.
	 * 
	 * @param p Pair of index values with a pre-defined name.
	 * @return An XYSeries containing the desired data.
	 */
	public XYSeries toXYGraphDataset (GraphPair p) {
		XYSeries series = new XYSeries (p.getName ());

		for (int row = 0; row < this.size (); ++row) {
			series.add (this.getXValue (row), (this.getYValue (row)));
		}
		
		return (series);
	}
	
	/**
	 * <p>Given a group of index values and a name, provides a JFree XYSeriesCollection
	 * Dataset for the purpose of graphing XY Graphs.
	 * 
	 * @param p GraphPair object used to determine various graph-related data.
	 * @return An XYSeries containing the desired data.
	 * @throws InvalidParametersException Whenever a non-numeric column is found in this process.
	 */
	public XYSeriesCollection toGroupedXYGraphDataset (GraphPair p) throws InvalidParametersException {
		
		// Create each individual Series.
		HashMap <Object, XYSeries> sets = new HashMap <> ();
		
		for (int i = 0; (i < this.size ()); ++i) {
			
			// If the type is new (IE. does not exist yet in the collection)
			// Add it into the collection as a new XYSeries.
			if (ColumnType.DOUBLE.equals (this.group_type)) {
				double key = this.getGroupDoubleValue (i);
				
				if (sets.containsKey (key)) {
					sets.get (key).add (new org.jfree.data.xy.XYDataItem (
							this.getXValue (i),
							this.getYValue (i)));
				} else  {
					XYSeries s = new XYSeries (p.getName () + Double.toString (key));
					
					s.add (new org.jfree.data.xy.XYDataItem (
							this.getXValue (i),
							this.getYValue (i)));
					
					sets.put (key, s);
				}
			} else { // if (ColumnType.STRING.equals (this.group_type)) {
				String key = this.getGroupStringValue (i);
				
				if (sets.containsKey (key)) {
					sets.get (key).add (new org.jfree.data.xy.XYDataItem (
							this.getXValue (i),
							this.getYValue (i)));
				} else  {
					XYSeries s = new XYSeries (p.getName () + key);
					
					s.add (new org.jfree.data.xy.XYDataItem (
							this.getXValue (i),
							this.getYValue (i)));
					
					sets.put (key, s);
				}
			}
		}
		
		// Put all the series into a collection and ship it!
		XYSeriesCollection grouped_series = new XYSeriesCollection ();
		
		for (XYSeries xy : sets.values ()) {
			grouped_series.addSeries (xy);
		}
		
		return (grouped_series);
	}
	
	/**
	 * <p>Given a group of index values and a name, provides a JFree CategoryDataset
	 * for the purpose of graphing Bar Graphs.
	 * <p>TODO: Make the method more robust. Add flexibility for varying combinations of double and string!
	 * 
	 * @param p Pair of index values with a pre-defined name.
	 * @return A DefaultCategoryDataset containing the desired data.
	 */
	/*public DefaultCategoryDataset toBarGraphDataset (GraphPair p) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset ();
		
		// Assume the X column (Column 0) has the category data.
		// Assume the Y column (Column 1) has the quantity data. (Cannot be a string)
		
		// We need to know what are the column types for each of them.
		if (this.isDouble (p.getXColumnIndex ())) {
			for (int row = 0; (row < this.getColumnLength ()); ++row) {
				dataset.addValue ((Number) (double) this.values.get (p.getYColumnIndex ()).get (row),
						p.getXIndexName (), (double) this.values.get (p.getXColumnIndex ()).get (row));
			}
		} else {
			for (int row = 0; (row < this.getColumnLength ()); ++row) {
				dataset.addValue ((Number) (double) this.values.get (p.getYColumnIndex ()).get (row),
						p.getName (), (String) this.values.get (p.getXColumnIndex ()).get (row));
			}
		}
		
		return (dataset);
	}*/
	
	/**
	 * <p>Creates a double [][] containing all the values in this DataSet. Can only be used for 2-column data sets.
	 * 
	 * @return A 2-columned DataSet converted to a double [][].
	 */
	public double[][] toArray () {
		// Prepare vehicle for double 2DArray creation.
		Array2DRowRealMatrix matrix = new Array2DRowRealMatrix (this.size (), 2);
		
		// Get both row and col indexes and start transferring data to the matrix.
		for (int i = 0; (i < matrix.getRowDimension ()); ++i) {
			matrix.setEntry (i, 0, this.getXValue (i));
			matrix.setEntry (i, 1, this.getYValue (i));
		}
		
		// Return a double 2DArray.
		return (matrix.getData ());
	}
	
	/**
	 * <p>Makes a copy of one of the two columns as a doubles array based on the index provided.
	 * 
	 * @param i The column to select. 0 signals the X column, whereas 1 signals the Y column. Any other value is invalid.
	 * @return An array of doubles containing a copy of the current state of the X or Y Column, or an array with one "0.0"
	 * value if the index is invalid.
	 */
	public double [] getColumnArray (int i) {
		if (i == 0) {
			double [] column = new double [this.x_column.size ()];
			
			for (int index = 0; index < this.x_column.size (); ++index) {
				column[index] = this.x_column.get (index);
			}
			
			return (column);
		} else if (i == 1) {
			double [] column = new double [this.y_column.size ()];
			
			for (int index = 0; index < this.y_column.size (); ++index) {
				column[index] = this.y_column.get (index);
			}
			
			return (column);
		} else {
			return (new double [] {0.0});
		}
	}
	
	/**
	 * <p>Getter method. Provides a String representation of the ArrayList.
	 * 
	 * @return A String representation of the entire ArrayList.
	 */
	@Override
	public String toString () {		
        StringBuilder sb = new StringBuilder();
        
        sb.append("-- X Column --");
        sb.append(System.getProperty("line.separator"));
        sb.append(this.getColumnString (0));
        sb.append(System.getProperty("line.separator"));
        
        sb.append("-- Y Column --");
        sb.append(System.getProperty("line.separator"));
        sb.append(this.getColumnString (1));	
        sb.append(System.getProperty("line.separator"));
        
        if (this.isGrouped ()) {
        	sb.append("-- Group Column --");
            sb.append(System.getProperty("line.separator"));
            sb.append(this.getColumnString (2));	
            sb.append(System.getProperty("line.separator"));
        }

        return sb.toString();
	}
	
	/**
	 * <p>A helper method used to split the "toString" code for each column.
	 * Delegates the String to be provided to one of three methods depending
	 * on the index provided.
	 * 
	 * @param i The index value of the column to describe; 0 refers to the X Column,
	 * 1 to the Y Column, and 2 to the Group Column. All other values are invalid.
	 * @return A String describing the column requested via the index value.
	 */
	private String getColumnString (int i) {
		if (i == 0) {
			return (this.getXColumnString ());
		} else if (i == 1) {
			return (this.getYColumnString ());
		} else if (i == 2) {
			return (this.getGroupColumnString ());
		} else {
			return ("");
		}
	}
	
	/**
	 * <p>Provides a textual representation of the status of the X Column. 
	 * Effectively a "toString" method just for the X Column.
	 * 
	 * @return A String describing the X Column of this object.
	 */
	private String getXColumnString () {
		StringBuilder sb = new StringBuilder();	

        sb.append("Name: ");
        sb.append(this.getXName());
        sb.append(System.getProperty("line.separator"));

        sb.append("Type: ");
        sb.append(ColumnType.DOUBLE.toString ());
        sb.append(System.getProperty("line.separator"));

        sb.append("Size: ");
        sb.append(this.x_column.size());
        sb.append(System.getProperty("line.separator"));

        sb.append("Values: [");

        for (Double d : this.x_column) {
    		sb.append (d).append (", ");
    	}

        sb.append("]");

        return sb.toString();
	}
	
	/**
	 * <p>Provides a textual representation of the status of the Y Column. 
	 * Effectively a "toString" method just for the Y Column.
	 * 
	 * @return A String describing the Y Column of this object.
	 */
	private String getYColumnString () {
		StringBuilder sb = new StringBuilder();	

        sb.append("Name: ");
        sb.append(this.getYName());
        sb.append(System.getProperty("line.separator"));

        sb.append("Type: ");
        sb.append(ColumnType.DOUBLE.toString ());
        sb.append(System.getProperty("line.separator"));

        sb.append("Size: ");
        sb.append(this.y_column.size());
        sb.append(System.getProperty("line.separator"));

        sb.append("Values: [");

        for (Double d : this.y_column) {
    		sb.append (d).append (", ");
    	}

        sb.append("]");

        return sb.toString();
	}
	
	/**
	 * <p>Provides a textual representation of the status of the Group Column. 
	 * Effectively a "toString" method just for the Group Column.
	 * 
	 * @return A String describing the Group Column of this object.
	 */
	private String getGroupColumnString () {
		StringBuilder sb = new StringBuilder();	

        sb.append("Name: ");
        sb.append(this.getGroupName());
        sb.append(System.getProperty("line.separator"));

        sb.append("Type: ");
        sb.append(this.group_type.toString ());
        sb.append(System.getProperty("line.separator"));

        sb.append("Size: ");
        if (ColumnType.DOUBLE.equals (group_type)) {
        	sb.append(this.group_column_double.size ());
        } else {// if (ColumnType.STRING.equals (group_type)) {
        	sb.append(this.group_column_string.size ());
        }
        sb.append(System.getProperty("line.separator"));

        sb.append("Values: [");

        if (ColumnType.DOUBLE.equals (group_type)) {
        	
        	for (Double d : this.group_column_double) {
        		sb.append (d).append (", ");
        	}
        	
        } else {// if (ColumnType.STRING.equals (group_type)) {
        	
        	for (String s : this.group_column_string) {
        		sb.append (s).append (", ");
        	}
        	
        }
        
        sb.append("]");

        return (sb.toString ());
	}
	
	/**
	 * <p>Provides the number of parameters that exist in this DataSet.
	 * This is also known as the K in some statistical functions.
	 * 
	 * @return The number of parameters that this DataSet contains.
	 */
	public int getNumParameters () {
		if (this.isGrouped ()) {
			return (2);
		} else {
			return (3);
		}
	}
	
	/**
	 * <p>Getter method for the "grouped" private variable.
	 * 
	 * @return A boolean stating whether this DataSet contains a Group By column or not.
	 */
	public boolean isGrouped () {
		return (!ColumnType.NONE.equals (this.group_type));
	}
        
    /**
     * <p>Tests equality between this object and some other object.
     * 
     * @param o An object that may or may not be an equivalent DataSet.
     * @return A boolean describing if both objects are roughly equivalent to each other.
     */
    @Override
    public boolean equals (Object o) {
        boolean rval = false;
        // Type Comparison
        if (o instanceof DataSet) {
        	DataSet ds = (DataSet) o;
            rval = true;
            
            // Size comparison
            if (this.size() != ds.size()) {
                rval = false;
            }
            
            // Column comparison
            if (this.isGrouped () ^ ds.isGrouped ()) {
            	rval = false;
            }
            
            // Group type comparison, if they're grouped.
            if (this.isGrouped () && ds.isGrouped ()) {
            	if (!(this.getGroupType ().equals (ds.getGroupType ()))) {
            		rval = false;
            	}
            }
            
            for (int i = 0; i < this.size () && rval; i++){
                if (this.getXValue (i) != ds.getXValue (i)){
                    rval = false;
                }
                
                if (this.getYValue (i) != ds.getYValue (i)){
                    rval = false;
                }
                
                if (this.isGrouped () && ds.isGrouped ()) {
                	if (ColumnType.DOUBLE.equals (this.getGroupType ())) {
                		if (this.getGroupDoubleValue (i) != ds.getGroupDoubleValue (i)){
                            rval = false;
                        }
                	} else {// if (ColumnType.DOUBLE.equals (this.getGroupType ())) {
                		if (this.getGroupStringValue (i) != ds.getGroupStringValue (i)){
                            rval = false;
                        }
                	}
                	
                }
            }               
        }
        
        return rval;
    }

    /**
     * <p>Orders data based on the X column's values.
     * <p>WARNING: Uses Objects.
     * 
     * @param x_column Double array to put the X Column's ordered values into.
     * @param y_column Double array to put the Y Column's ordered values into.
     */
	public void orderData (double [] x_column, double [] y_column) {

		// Create the sorting container.
		Object [] xy_array = new Object [this.x_column.size ()];
		
		// Populate the sorting container.
		for (int i = 0; (i < this.x_column.size ()); ++i) {
			XYDataItem xy = new XYDataItem (this.x_column.get (i), this.y_column.get (i));
			
			xy_array[i] = xy;
		}
		
		// Sort the data in the "xy_column" based on the x column values.
		Arrays.sort (xy_array, new Comparator <Object> () {
			
			@Override
			public int compare (Object o1, Object o2) {
				return (Double.compare (((XYDataItem) o1).getXValue (), 
						((XYDataItem) o2).getXValue ()));
			}
		});
		
		// Split the sorted data into two double arrays.
		for (int i = 0; (i < xy_array.length); ++i) {
			XYDataItem xy = (XYDataItem) xy_array[i];
			
			x_column[i] = xy.getXValue ();
			y_column[i] = xy.getYValue ();
		}
		
	}

	/**
	 * <p>Provides the number of items in this DataSet.
	 * <p>It is assumed that the columns of the DataSet all have the same length.
	 * 
	 * @return The number of values in the X Column.
	 */
	public int getItemCount () {
		//if (this.getX ().size () == this.getY ().size ()) {
			return (this.getX ().size ());
		//} else {
		//	return (0);
		//}
	}
	
	public int getNumberOfUniqueGroups () {
		if (ColumnType.DOUBLE.equals (this.group_type)) {
			
			ArrayList <Double> d_array = new ArrayList <> ();
			
			for (double d : this.group_column_double) {
				if (!d_array.contains (d)) {
					d_array.add (d);
				}
			}
			
			return (d_array.size ());
			
		} else if (ColumnType.STRING.equals (this.group_type)) {
			
			ArrayList <String> s_array = new ArrayList <> ();
			
			for (String s : this.group_column_string) {
				if (!s_array.contains (s)) {
					s_array.add (s);
				}
			}
			
			return (s_array.size ());
			
		} else {
			
			return (0);
			
		}
	}
}