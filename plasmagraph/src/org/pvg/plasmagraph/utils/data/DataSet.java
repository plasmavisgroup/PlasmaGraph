package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.pvg.plasmagraph.utils.exceptions.IncorrectParametersException;
import org.pvg.plasmagraph.utils.template.Template;

/**
 * Container of DataColumns. Provides methods to create JFree Datasets.
 * 
 * @author Gerardo A. Navas Morales
 */
@SuppressWarnings ("rawtypes")
public class DataSet implements Iterable<DataColumn> {
	/** Container for DataColumns. */
	private ArrayList <DataColumn> values;
	/***/
	private boolean grouped;

	/**
	 * Constructor. Creates a new ArrayList of DataColumns for this object.
	 * There should only exist one DataSet for any given time.
	 * 
	 * @param grouped Boolean flag stating whether the DataSet includes a group by column or not.
	 */
	public DataSet (boolean grouped) {
		this.values = new ArrayList <> ();
		this.grouped = grouped;
	}

	/**
	 * Allows a new DataColumn into the DataSet if and only if its length
	 * is the same as every other column. (Read: The first one is checked.)
	 * 
	 * @param o DataColumn to add to the DataSet.
	 * @return Boolean describing the success or failure of the action.
	 */
	public boolean add (DataColumn o) {
		if (this.size () == 0) {
			return (this.values.add (o));
		} else {
			if (this.values.get (0).size () == o.size ()) {
				return (this.values.add (o));
			} else {
				return (false);
			}
		}
	}

	/**
	 * Appends all the columns of a DataSet into this DataSet
	 * TODO: JavaDocs.
	 * @param column
	 * @return
	 */
	public boolean append (DataSet ds) {
		boolean success = true;
		
		for (int i = 0; (i < ds.size () && success); ++i) {
			success = values.get (i).append (ds.get (i));
		}
		
		return (success);
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
	 * Getter method. Provides access to a DataColumn at an index's location.
	 * 
	 * @param i The index where the desired DataColumn is located.
	 * @return The DataColumn at the index location.
	 */
	public DataColumn get (int i) {
		return (this.values.get (i));
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
	 * Provides the Group By column, if it exists.
	 * 
	 * @return The Group By DataColumn if it exists, or null if it does not exist.
	 */
	public DataColumn getGroup () {
		if (!this.isGrouped ()) {
			return (null);
		} else {
			return (this.values.get (0));
		}
	}
	
	/**
	 * Provides the DataColumn that contains the X values of the DataSet.
	 * 
	 * @return The X value DataColumn, depending on the size of the DataSet.
	 */
	public DataColumn getX () {
		if (!this.isGrouped ()) {
			return (this.values.get (0));
		} else {
			return (this.values.get (1));
		}
	}

	/**
	 * Provides the DataColumn that contains the Y values of the DataSet.
	 * 
	 * @return The Y value DataColumn, depending on the size of the DataSet.
	 */
	public DataColumn getY () {
		if (!this.isGrouped ()) {
			return (this.values.get (1));
		} else {
			return (this.values.get (2));
		}
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
	
	public Iterator<DataColumn> iterator () {
		return (this.values.iterator ());
	}
	
	/**
	 * Removes a DataColumn from the DataSet.
	 * 
	 * @param o The DataColumn to remove.
	 * @return Boolean describing the success or failure of the action.
	 */
	public boolean remove (DataColumn o) {
		return (this.values.remove (o));
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
	
	/**
	 * Given a group of index values and a name, provides a JFree CategoryDataset
	 * for the purpose of graphing Bar Graphs.
	 * TODO: Make the method more robust. Add flexibility for varying combinations of double and string!
	 * 
	 * @param p Pair of index values with a pre-defined name.
	 * @return A DefaultCategoryDataset containing the desired data.
	 */
	public DefaultCategoryDataset toBarGraphDataset (GraphPair p) {
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
	 * Given a group of index values and a name, provides a JFree XYSeriesCollection
	 * Dataset for the purpose of graphing XY Graphs.
	 * 
	 * @param t 
	 * @param p Pair of index values with a pre-defined name.
	 * @return An XYSeries containing the desired data.
	 * @throws IncorrectParametersException 
	 */
	public XYSeriesCollection toGroupedXYGraphDataset (String series_name, Template t) throws IncorrectParametersException {
		XYSeriesCollection grouped_series = new XYSeriesCollection ();
		
		// Find the group_by column index.
		int grouped_column = this.find (t.getGroupByColumn ());
		
		HashMap <Object, XYSeries> sets = new HashMap <> ();
		
		// For each row
		// Check the type of the group.
		boolean valid = true;
		for (DataColumn dc : this.values) {
			valid = valid && (dc.containsDoubles () || (this.find (dc) == grouped_column));
		}
		
		if (valid) {
			for (int i = 0; (i < this.getColumnLength ()); ++i) {
				
				// If the type is new (I.E. does not exist yet in the collection)
				// Add it into the collection as a new XYSeries.
				Object key = this.values.get (grouped_column).get (i);
				if (sets.containsKey (key)) {
					sets.get (key).add (new org.jfree.data.xy.XYDataItem (
							(double) this.values.get (1).get (i),
							(double) this.values.get (2).get (i)));
				}
			}
			
			for (XYSeries xy : sets.values ()) {
				grouped_series.addSeries (xy);
			}
			
			return (grouped_series);
		} else {
			throw (new IncorrectParametersException ());
		}
		
	}
	
	/**
	 * Getter method. Provides a string representation of the ArrayList.
	 * 
	 * @return A String representation of the entire ArrayList.
	 */
	@Override
	public String toString () {		
                StringBuilder str = new StringBuilder();	
                ListIterator<DataColumn> litr = this.values.listIterator();
                while(litr.hasNext()) {
                    Object column = litr.next();
                    str.append("-- Column --");
                    str.append(System.getProperty("line.separator"));
                    str.append(column.toString());
                    str.append(System.getProperty("line.separator"));	
                    str.append(System.getProperty("line.separator"));
                }

                return str.toString();
	}
	
	/**
	 * Given a group of index values and a name, provides a JFree XYSeries Dataset
	 * for the purpose of graphing XY Graphs.
	 * 
	 * @param p Pair of index values with a pre-defined name.
	 * @return An XYSeries containing the desired data.
	 */
	public XYSeries toXYGraphDataset (GraphPair p) {
		XYSeries series = new XYSeries (p.getName ());

		if (this.isDouble ()) {
			for (int row = 0; row < this.getColumnLength (); ++row) {
				series.add ((double) this.values.get (0).get (row),
						((double) this.values.get (1).get (row)));
			}
		}
		
		return (series);
	}

	/**
	 * Provides the number of parameters that exist in this DataSet.
	 * This is also known as the K in some statistical functions.
	 * 
	 * @return The number of parameters that this DataSet contains.
	 */
	public int getNumParameters () {
		if (this.grouped) {
			return (this.size () - 1);
		} else {
			return (this.size ());
		}
	}
	
	/**
	 * Getter method for the "grouped" private variable.
	 * 
	 * @return A boolean stating whether this DataSet contains a Group By column or not.
	 */
	public boolean isGrouped () {
		return (this.grouped);
	}
        
    /**
     * 
     * @param o
     * @return 
     */
    @Override
    public boolean equals (Object o) {
        boolean rval = false;
        if (o instanceof DataSet) {
            DataSet ds = (DataSet) o;
            rval = true;
            
            if(this.size() != ds.size()){
            	
                rval = false;
            }
            
            for(int i = 0; i < this.values.size() && rval; i++){
                if(!this.values.get(i).equals(ds.get(i))){
                    rval = false;
                }
            }               
        }
        
        return rval;
    }
}