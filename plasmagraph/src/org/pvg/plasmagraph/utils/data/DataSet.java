package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * 
 * @author Gerardo A. Navas Morales
 */
public class DataSet implements Iterable<DataColumn>, Iterator<DataColumn> {
	/** Container for DataColumns. */
	private ArrayList<DataColumn> values;
	/**
	 * Position of Iterator object; used for the implementation of Iterator and
	 * Iterable.
	 */
	private int position = 0;

	/**
	 * 
	 */
	public DataSet () {
		values = new ArrayList<DataColumn> ();
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
				return (this.values.add (o));
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
		return (this.values.remove (o));
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public DataColumn remove (int i) {
		return (this.values.remove (i));
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
	 * 
	 * @param i
	 * @return
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
	 * 
	 * @return
	 */
	public int getColumnLength () {
		if (this.values.size () > 0) {
			return (this.values.get (0).size ());
		} else {
			return (0);
		}
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public int getColumnLength (int index) {
		return (this.values.get (index).size ());
	}

	/**
	 * 
	 * @return
	 */
	public XYSeries toXYGraphDataset () {
		XYSeries series = new XYSeries (this.getDataSetName ());

		if (this.isDouble ()) {
			for (int row = 0; row < this.getColumnLength (); ++row) {
				series.add ((double) this.values.get (0).get (row),
						((double) this.values.get (1).get (row)));
			}
		}
		
		return (series);
	}
	
	/**
	 * 
	 * @return
	 */
	public DefaultCategoryDataset toBarGraphDataset () {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset ();
		String series = "Series 1";
		
		// Assume the X column (Column 0) has the category data.
		// Assume the Y column (Column 1) has the quantity data. (Cannot be a string)
		
		// We need to know what are the column types for each of them.
		if (this.isDouble (0)) {
			for (int row = 0; (row < this.getColumnLength ()); ++row) {
				dataset.addValue ((Number) (double) this.values.get (1).get (row),
						series, (double) this.values.get (0).get (row));
			}
		} else {
			for (int row = 0; (row < this.getColumnLength ()); ++row) {
				dataset.addValue ((Number) (double) this.values.get (1).get (row),
						series, (String) this.values.get (0).get (row));
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
		this.position = 0;
		return (this);
	}

	@Override
	public boolean hasNext () {
		return (position < values.size ());
	}

	@Override
	public DataColumn next () {
		if (position == values.size ()) {
			throw new NoSuchElementException ();
		}
		return (values.get (++position));
	}

	@Override
	public void remove () {
		this.values.remove (position--);
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