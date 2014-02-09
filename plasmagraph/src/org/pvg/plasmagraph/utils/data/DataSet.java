package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
	 * 
	 * @param o
	 * @return
	 */
	public boolean add (DataColumn o) {
		return (this.values.add (o));
	}

	/**
	 * 
	 * @param o
	 * @return
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
	 * 
	 * @param o
	 * @return
	 */
	public int find (DataColumn o) {
		return (this.values.indexOf (o));
	}
	
	/**
	 * 
	 * @param o
	 * @return
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
	 * 
	 * @param o
	 * @return
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
	 * 
	 * @return
	 */
	public int size () {
		return (this.values.size ());
	}

	/**
	 * 
	 */
	@Override
	public String toString () {
		return (this.values.toString ());
	}
	
	/**
	 * 
	 * @return
	 */
	//public String getType (int column) {
	//	return (this.values.get (column).getType ());
	//}

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

	public XYSeries toXYGraphDataset () {
		 // TODO: Use DataReference to provide this!
		XYSeries series = new XYSeries (this.getDataSetName ());

		for (int row = 0; row < this.getColumnLength (); ++row) {
			// TODO: Implement check for strings and change type casts
			// manually per check.
			series.add ((Double) this.values.get (0).get (row),
					(Double) this.values.get (1).get (row));
		}
		
		return (series);
	}

	private Comparable<String> getDataSetName () {
		if (this.values.size () == 2) {
			return ("" + this.values.get(0).getColumnName () + " vs. " +
					this.values.get (1).getColumnName ());
		} else {
			return ("Default");
		}
	}

	private int getColumnLength () {
		if (this.values.size () > 0) {
			return (this.values.get (0).size ());
		} else {
			return (0);
		}
	}

	public PieDataset toPieGraphDataset () {
		// TODO Auto-generated method stub
		return null;
	}

	public XYSeriesCollection toLineGraphDataset () {
		// TODO Auto-generated method stub
		return null;
	}

	public DefaultCategoryDataset toBarGraphDataset () {
		// TODO Auto-generated method stub
		return null;
	}
}