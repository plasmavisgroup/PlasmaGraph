package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.jmatio.types.MLArray;

public class DataColumn implements Iterable<Object>, Iterator<Object> {
	/** Container for DataColumns. */
	@SuppressWarnings ("rawtypes")
	private ArrayList values;
	
	/** Name of the Column. */
	private String name;
	
	/** Type of the item contained, as per JMatIO's MLArray class.*/
	int type;
	
	/** Position of Iterator object; 
	 * used for the implementation of Iterator and Iterable. */
	private int position = 0;

	public DataColumn (int column_type) {
		if (column_type == MLArray.mxDOUBLE_CLASS) {
			values = new ArrayList<Double> ();
			type = MLArray.mxDOUBLE_CLASS;
		} else if (column_type == MLArray.mxCHAR_CLASS) {
			values = new ArrayList<String> ();
			type = MLArray.mxCHAR_CLASS;
		}
		name = "empty";
	}
	
	public DataColumn (int column_type, String n) {
		if (column_type == MLArray.mxDOUBLE_CLASS) {
			values = new ArrayList<Double> ();
			type = MLArray.mxDOUBLE_CLASS;
		} else if (column_type == MLArray.mxCHAR_CLASS) {
			values = new ArrayList<String> ();
			type = MLArray.mxCHAR_CLASS;
		}
		name = n;
	}

	@SuppressWarnings ("unchecked")
	public boolean add (Number o) {
		if (this.containsDoubles ()) {
			return (this.values.add ((Double) o));
		} else {
			throw (new UnsupportedOperationException ("Not a double, or putting a Double in a String list."));
		}
	}
	
	@SuppressWarnings ("unchecked")
	public boolean add (String o) {
		if (this.containsStrings ()) {
			return (this.values.add (o));
		} else {
			throw (new UnsupportedOperationException ("Not a String, or putting String in Double list."));
		}
	}

	public boolean remove (Object o) {
		return (this.values.remove (o));
	}

	public Object remove (int i) {
		return (this.values.remove (i));
	}

	public int find (Object o) {
		return (this.values.indexOf (o));
	}
	
	public boolean contains (Object o) {
		return (this.values.contains (o));
	}

	public Object get (int i) {
			return (this.values.get (i));
	}

	public int size () {
		return (this.values.size ());
	}
	
	public String getColumnName () {
		return (name);
	}
	
	public String getType () {
		return (MLArray.typeToString (type));
	}

	/**
	 * 
	 * @return
	 */
	public int getTypeValue () {
		return (type);
	}
	
	/**
	 * Comparing method. Type variable is compared to the MLArray types in JMatIO.
	 * Provides verification for Doubles.
	 * 
	 * @return Boolean containing whether the Objects of this class are Doubles.
	 */
	public boolean containsDoubles () {
		return (type == MLArray.mxDOUBLE_CLASS);
	}
	
	/**
	 * Comparing method. Type variable is compared to the MLArray types in JMatIO.
	 * Provides verification for Strings. (PlasmaGraph assumes Chars are turned into
	 * strings when read from a MATLAB file.)
	 * 
	 * @return Boolean containing whether the Objects of this class are Strings.
	 */
	public boolean containsStrings () {
		return (type == MLArray.mxCHAR_CLASS);
	}
	
	@Override
	public String toString () {
		return (this.values.toString ());
	}

	// Iterator / Iterable methods.
	@Override
	public boolean hasNext () {
		return (position < values.size ());
	}

	@Override
	public Object next () {
		if (position == values.size ()) {
			throw new NoSuchElementException ();
		}
		return (values.get (++position));
	}

	@Override
	public void remove () {
		this.values.remove (position--);
	}

	@Override
	public Iterator<Object> iterator () {
		this.position = 0;
		return (this);
	}

	public double [] toArray () {
		double [] arr = new double [this.values.size ()];
		
		for (int i = 0; (i < this.values.size ()); ++i) {
			arr[i] = (double) this.values.get (i);
		}
		
		return (arr);
	}
}
