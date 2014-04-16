package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.pvg.plasmagraph.utils.exceptions.InvalidParametersException;
import org.pvg.plasmagraph.utils.exceptions.InvalidTypeException;
import org.pvg.plasmagraph.utils.types.ColumnType;

import com.jmatio.types.MLChar;

/**
 * Container of the data in a graph before conversion into a JFreeChart Dataset type.
 * <p> Provides methods to create JFree Datasets.
 * 
 * @author Gerardo A. Navas Morales
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
	 * Basic constructor. Creates an ungrouped DataSet.
	 * 
	 * @param p GraphPair object used to provide the Column names.
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
	 * Basic constructor. Creates an ungrouped DataSet.
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
		
		/*if (m.isDouble ()) {

			return (ColumnType.DOUBLE);

		} else if (m.isCell () || m.isChar ()) {

			return (ColumnType.STRING);

		} else {
			
			return (ColumnType.NONE);
			
		}*/
		
		this.group_type = ColumnType.NONE; // TODO: THIS NEEDS TO BE A TYPE THAT ISN'T NONE.
		group_column_double = null;
		group_column_string = null;
	}
	
	/**
	 * Basic constructor. Creates an ungrouped DataSet.
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
	
	// Add methods.
	
	// Assumption: It is assumed that any method putting values into this object
	// will make sure that each column has the same number of objects.
	/**
	 * @param d
	 * @return 
	 */
	public boolean addToX (double d) {
		return (x_column.add (d));
	}

	/**
	 * @param d
	 * @return
	 */
	public boolean addToX (double [] d) {
		ArrayList <Double> doubles_collection = new ArrayList <Double> (d.length);
		
		for (int i = 0; i < d.length; ++i) {
			doubles_collection.add (d[i]);
		}
		
		return (x_column.addAll (doubles_collection));
	}
	
	/**
	 * @param d
	 * @return 
	 */
	public boolean addToY (double d) {
		return (y_column.add (d));
	}
	
	/**
	 * @param d
	 * @return
	 */
	public boolean addToY (double [] d) {
		ArrayList <Double> doubles_collection = new ArrayList <Double> (d.length);
		
		for (int i = 0; i < d.length; ++i) {
			doubles_collection.add (d[i]);
		}
		
		return (y_column.addAll (doubles_collection));
	}
	
	/**
	 * @param d
	 * @return 
	 */
	public boolean addToGroup (double d) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			return (group_column_double.add (d));
		} else {
			return (false);
		}
	}
	
	/**
	 * @param d
	 * @return
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
	 * @param s
	 * @return 
	 */
	public boolean addToGroup (String s) {
		if (ColumnType.STRING.equals (group_type)) {
			return (group_column_string.add (s));
		} else {
			return (false);
		}
	}
	
	/**
	 * @param s
	 * @return
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
	/**
	 * @param d
	 * @return 
	 */
	public boolean removFromeX (double d) {
		return (x_column.remove (d));
	}
	
	/**
	 * @param i
	 * @return
	 */
	public boolean removFromeX (int i) {
		return (x_column.remove (i) != null);
	}
	
	/**
	 * @param d
	 * @return 
	 */
	public boolean removeFromY (double d) {
		return (y_column.remove (d));
	}
	
	/**
	 * @param i
	 * @return
	 */
	public boolean removFromeY (int i) {
		return (y_column.remove (i) != null);
	}
	
	/**
	 * @param d
	 * @return 
	 */
	public boolean removeFromGroup (double d) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			return (group_column_double.remove (d));
		} else {
			return (false);
		}
	}
	
	/**
	 * @param s
	 * @return 
	 */
	public boolean removeFromGroup (String s) {
		if (ColumnType.STRING.equals (group_type)) {
			return (group_column_string.remove (s));
		} else {
			return (false);
		}
	}
	
	/**
	 * @param i
	 * @return
	 */
	public boolean removFromeGroup (int i) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			
			return (group_column_double.remove (i) != null);
			
		} else if (ColumnType.STRING.equals (group_type)) {
			
			return (group_column_string.remove (i) != null);
			
		} else {
			
			return (false);
		}
	}
	
	// Get methods.
	public double getXValue (int i) {
		return (this.x_column.get (i));
	}
	
	public double getYValue (int i) {
		return (this.y_column.get (i));
	}
	
	public double getGroupDoubleValue (int i) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			return (this.group_column_double.get (i));
		} else {
			return (Double.NaN);
			//throw (new InvalidTypeException ("Group Column"));
		}
	}
	
	public String getGroupStringValue (int i) {
		if (ColumnType.STRING.equals (group_type)) {
			return (this.group_column_string.get (i));
		} else {
			return ("NaN");
			//throw (new InvalidTypeException ("Group Column"));
		}
	}
	
	/**
	 * Getter method. Provides the X Column's name.
	 * 
	 * @return The value in "x_column_name".
	 */
	public String getXName () {
		return (this.x_column_name);
	}
	
	/**
	 * Getter method. Provides the Y Column's name.
	 * 
	 * @return The value in "y_column_name".
	 */
	public String getYName () {
		return (this.y_column_name);
	}
	
	/**
	 * Getter method. Provides the Group Column's name.
	 * 
	 * @return The value in "group_column_name".
	 */
	public String getGroupName () {
		return (this.group_column_name);
	}
	
	/**
	 * Getter method. Provides the Group Column's ColumnType.
	 * 
	 * @return The value in "group_type".
	 */
	public ColumnType getGroupType () {
		return (this.group_type);
	}
	
	/**
	 * Getter method. Provides the size of the ArrayList contained.
	 * 
	 * @return Integer value of the current size of the ArrayLists.
	 */
	public int size () {
		return (this.x_column.size ());
	}
	
	public List <Double> getX () {
		return (this.x_column);
	}
	
	public List <Double> getY () {
		return (this.y_column);
	}
	
	/**
	 * @return
	 */
	public double getXMax () {
			double max = this.getXValue (0);
			
			for (double d : this.x_column) {
				max = (max < d) ? d : max;
			}
			
			return (max);
	}
	
	/**
	 * @return
	 */
	public double getXMin () {
			double min = this.getXValue (0);
			
			for (double d : this.x_column) {
				min = (min > d) ? d : min;
			}
			
			return (min);
	}
	
	/**
	 * @return
	 */
	public double getYMax () {
			double max = this.getYValue (0);
			
			for (double d : this.y_column) {
				max = (max < d) ? d : max;
			}
			
			return (max);
	}
	
	/**
	 * @return
	 */
	public double getYMin () {
			double min = this.getYValue (0);
			
			for (double d : this.y_column) {
				min = (min > d) ? d : min;
			}
			
			return (min);
	}
	
	// Find and contains methods.
	/**
	 * @param d
	 * @return 
	 */
	public boolean containsX (double d) {
		return (x_column.contains (d));
	}
	
	/**
	 * @param d
	 * @return 
	 */
	public boolean containsY (double d) {
		return (y_column.contains (d));
	}
	
	/**
	 * @param d
	 * @return 
	 */
	public boolean containsGroup (double d) {
		if (ColumnType.DOUBLE.equals (group_type)) {
			return (group_column_double.contains (d));
		} else {
			return (false);
		}
	}
	
	/**
	 * @param s
	 * @return 
	 */
	public boolean containsGroup (String s) {
		if (ColumnType.STRING.equals (group_type)) {
			return (group_column_string.contains (s));
		} else {
			return (false);
		}
	}
	
	public int findX (double o) {
		for (int i = 0; (i < this.x_column.size ()); ++i) {
			if (this.x_column.get (i) == o) {
				return (i);
			}
		}
		
		return (-1);
	}
	
	public int findY (double o) {
		for (int i = 0; (i < this.y_column.size ()); ++i) {
			if (this.y_column.get (i) == o) {
				return (i);
			}
		}
		
		return (-1);
	}
	
	public int findGroup (double x, double y) {
		for (int i = 0; (i < this.x_column.size ()) && 
				(i < this.y_column.size ()); ++i) {
			
			if ((this.x_column.get (i) == x) && (this.y_column.get (i) == y)) {
				return (i);
			}
		}
		
		return (-1);
	}
	
	// Set methods.
	/**
	 * @param d
	 * @return 
	 */
	public boolean setToX (double [] d) {
		ArrayList <Double> x = new ArrayList <> (d.length);
		
		for (int i = 0; i < d.length; ++i) {
			x.add (d[i]);
		}
		
		return (setToX (x));
	}
	
	/**
	 * @param d
	 * @return 
	 */
	public boolean setToX (ArrayList <Double> d) {
		x_column.clear ();
		return (x_column.addAll (d));
	}
	
	/**
	 * @param d
	 * @return 
	 */
	public boolean setToY (double [] d) {

		ArrayList <Double> y = new ArrayList <> (d.length);
		
		for (int i = 0; i < d.length; ++i) {
			y.add (d[i]);
		}
		
		return (setToY (y));
	}
	
	/**
	 * @param d
	 * @return 
	 */
	public boolean setToY (ArrayList <Double> d) {
		x_column.clear ();
		return (y_column.addAll (d));
	}
	
	/**
	 * @param d
	 * @return 
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
	 * @param s
	 * @return 
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
	 * 
	 * @param c
	 */
	public void setGroupType (ColumnType c) {
		this.group_type = c;
		
		if (ColumnType.DOUBLE.equals (this.group_type)) {
			
			this.group_column_double = new ArrayList <> ();
			this.group_column_string = null;
			
		} else { //if (ColumnType.STRING.equals (this.group_type)) {
			
			this.group_column_string = new ArrayList <> ();
			this.group_column_double = null;
			
		}
	}
	
	// Checking methods.
	/**
	 * Getter method. Provides whether the group column is of the type Double.
	 * 
	 * @return Boolean stating if the group column is of the type Double.
	 */
	public boolean isGroupDouble () {
		return (ColumnType.DOUBLE.equals (group_type));
	}
	
	/**
	 * Getter method. Provides whether the group column is of the type String.
	 * 
	 * @return Boolean stating if the group column is of the type Double.
	 */
	public boolean isGroupString () {
		return (ColumnType.STRING.equals (group_type));
	}
	
	// Conversion methods.
	
	/**
	 * Given a group of index values and a name, provides a JFree XYSeries Dataset
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
	 * Given a group of index values and a name, provides a JFree XYSeriesCollection
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
	 * Given a group of index values and a name, provides a JFree CategoryDataset
	 * for the purpose of graphing Bar Graphs.
	 * TODO: Make the method more robust. Add flexibility for varying combinations of double and string!
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
	 * Creates a double [][] containing all the values in this DataSet.
	 * TODO: Currently can only be used for 2-column data sets.
	 * TODO: Currently assumes all values are doubles. Check first..
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
	 * @param i The column to select. 0 signals the X column, whereas 1 signals the Y column.
	 * 
	 * @return
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
	 * @return
	 */
	public double [] getDoubleGroupColumnArray () {
		if (ColumnType.DOUBLE.equals (group_type)) {
			double [] column = new double [this.group_column_double.size ()];
			
			for (int i = 0; i < this.group_column_double.size (); ++i) {
				column[i] = this.group_column_double.get (i);
			}
			
			return (column);
		} else { // if (ColumnType.STRING.equals (group_type)) {
			return (new double [] {0.0});
		}
	}
	
	/**
	 * @return
	 */
	public String [] getStringGroupColumnArray () {
		if (ColumnType.DOUBLE.equals (group_type)) {
			String [] column = new String [this.group_column_string.size ()];
			
			for (int i = 0; i < this.group_column_string.size (); ++i) {
				column[i] = this.group_column_string.get (i);
			}
			
			return (column);
			
		} else { // if (ColumnType.STRING.equals (group_type)) {
			return (new String [] {"Empty"});
		}
	}
	
	/**
	 * Getter method. Provides a String representation of the ArrayList.
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
	 * Provides the number of parameters that exist in this DataSet.
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
	 * Getter method for the "grouped" private variable.
	 * 
	 * @return A boolean stating whether this DataSet contains a Group By column or not.
	 */
	public boolean isGrouped () {
		return (!ColumnType.NONE.equals (this.group_type));
	}
        
    /**
     * 
     * @param o
     * @return 
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
     * Orders data based on the X column's values.
     * 
     * @param x_column 
     * @param y_column 
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
	 * 
	 * @return
	 */
	public int getItemCount () {
		//if (this.getX ().size () == this.getY ().size ()) {
			return (this.getX ().size ());
		//} else {
		//	return (0);
		//}
	}
}