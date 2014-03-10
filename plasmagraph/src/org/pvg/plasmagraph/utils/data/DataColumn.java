package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;
import java.util.Iterator;

import org.pvg.plasmagraph.utils.types.ColumnType;

public class DataColumn<E> implements Iterable<E> {

	/** Container for DataColumns. */
	private ArrayList<E> values;
	
	/** Name of the Column. */
	private String name;
	
	/** Type of the item contained, as per JMatIO's MLArray class.*/
	private ColumnType type;
	
	public DataColumn (String n, String c_type) {// throws Exception {
		values = new ArrayList<> ();
		if (c_type.equals ("string") || c_type.equals ("String")) {
			type = ColumnType.STRING;
		} else if (c_type.equals ("double") || c_type.equals ("Double")) {
			type = ColumnType.DOUBLE;
		} else {
			type = ColumnType.DATETIME;
			//throw (new Exception ("Incorrect Column type."));
		}
		name = n;
	}
	
	public DataColumn (String n, ColumnType c_type) {// throws Exception {
		values = new ArrayList<> ();
		if (c_type.equals (ColumnType.STRING)) {
			type = ColumnType.STRING;
		} else if (c_type.equals (ColumnType.DOUBLE)) {
			type = ColumnType.DOUBLE;
		} else {
			type = ColumnType.DATETIME;
			//throw (new Exception ("Incorrect Column type."));
		}
		name = n;
	}
	
	public DataColumn (String n, String c_type, ArrayList <E> s) {// throws Exception {
		values = s;
		if (c_type.equals ("string") || c_type.equals ("String")) {
			type = ColumnType.STRING;
		} else if (c_type.equals ("double") || c_type.equals ("Double")) {
			type = ColumnType.DOUBLE;
		} else {
			type = ColumnType.DATETIME;
			//throw (new Exception ("Incorrect Column type."));
		}
		name = n;
	}
	
	public DataColumn (String n, ColumnType c_type, ArrayList <E> s) {// throws Exception {
		values = s;
		if (c_type.equals (ColumnType.STRING)) {
			type = ColumnType.STRING;
		} else if (c_type.equals (ColumnType.DOUBLE)) {
			type = ColumnType.DOUBLE;
		} else {
			type = ColumnType.DATETIME;
			//throw (new Exception ("Incorrect Column type."));
		}
		name = n;
	}
	
	public boolean add (E o) {
		if (o == "" || o == null) {
			return (false);
		} else {
			return (this.values.add (o));
		}
	}

	public boolean remove (E o) {
		if (o == "" || o == null) {
			return (false);
		} else {
			return (this.values.remove (o));
		}
	}

	public E remove (int i) {
		return (this.values.remove (i));
	}

	public int find (E o) {
		return (this.values.indexOf (o));
	}
	
	public boolean contains (E o) {
		return (this.values.contains (o));
	}

	public E get (int i) {
			return (this.values.get (i));
	}

	public int size () {
		return (this.values.size ());
	}
	
	public String getColumnName () {
		return (name);
	}
	
	public String getType () {
		return (type.toString ());
	}
	
	/**
	 * Comparing method. Provides verification for Doubles.
	 * 
	 * @return Boolean containing whether the Objects of this class are Doubles.
	 */
	public boolean containsDoubles () {
		return (type.equals (ColumnType.DOUBLE));
	}
	
	/**
	 * Comparing method. Provides verification for Strings. 
	 * (PlasmaGraph assumes Chars are turned into strings when read from a MATLAB file.)
	 * 
	 * @return Boolean containing whether the Objects of this class are Strings.
	 */
	public boolean containsStrings () {
		return (type.equals (ColumnType.STRING));
	}
	
	/**
	 * Comparing method. Provides verification for Dates.
	 * 
	 * @return Boolean containing whether the Objects of this class are Dates.
	 */
	public boolean containsDates () {
		return (type.equals (ColumnType.DATETIME));
	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		
		sb.append (this.name).
				append (" - ").
				append (this.type.toString ()).
				append (": ");
		
		for (E e : this.values) {
			sb.append (e.toString ()).append (", ");
		}
		
		return (sb.toString ());
	}

	// Iterable methods.
	@Override
	public Iterator<E> iterator () {
		//this.position = 0;
		//return (this);
		return (this.values.iterator ());
	}

	public double [] toDoubleArray () {
		double [] arr = new double [this.values.size ()];
		if (this.containsDoubles ()) {
			for (int i = 0; (i < this.values.size ()); ++i) {
				arr[i] = (Double) this.values.get (i);
			}
			
			return (arr);
		} else {
			return (null);
		}
	}

	/**
	 * Getter method. Provides if there are any items in the ArrayList.
	 * 
	 * @return Boolean stating if the ArrayList in this object is populated by at least one object.
	 */
	public boolean isEmpty () {
		return (this.values.isEmpty ());
	}

	/**
	 * Appends all the values in a specified DataColumn into this column.
	 * 
	 * @param column The column whose values will be included into this one.
	 * @return Success or failure of the operation.
	 */
	public boolean append (DataColumn <E> column) {
		return (this.values.addAll (column.values));
	}

}
