package org.pvg.plasmagraph.utils.data;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.pvg.plasmagraph.utils.types.ColumnType;

public class DataColumn<E> implements Iterable<Object>, Iterator<Object> {

	/** Container for DataColumns. */
	private ArrayList<E> values;
	
	/** Name of the Column. */
	private String name;
	
	/** Type of the item contained, as per JMatIO's MLArray class.*/
	private ColumnType type;
	
	/** Position of Iterator object; 
	 * used for the implementation of Iterator and Iterable. */
	private int position = 0;
	
	public DataColumn (String n, String c_type) {// throws Exception {
		values = new ArrayList<E> ();
		if (c_type.equals ("string") || c_type.equals ("String")) {
			type = ColumnType.STRING;
		} else if (c_type.equals ("double") || c_type.equals ("Double")) {
			type = ColumnType.DOUBLE;
		} else {
			type = ColumnType.DOUBLE;
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
			type = ColumnType.DOUBLE;
			//throw (new Exception ("Incorrect Column type."));
		}
		name = n;
	}
	
	public boolean add (E o) {
		return (this.values.add (o));
	}

	public boolean remove (E o) {
		return (this.values.remove (o));
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
	 * Comparing method. Type variable is compared to the MLArray types in JMatIO.
	 * Provides verification for Doubles.
	 * 
	 * @return Boolean containing whether the Objects of this class are Doubles.
	 */
	public boolean containsDoubles () {
		return (false);
	}
	
	/**
	 * Comparing method. Type variable is compared to the MLArray types in JMatIO.
	 * Provides verification for Strings. (PlasmaGraph assumes Chars are turned into
	 * strings when read from a MATLAB file.)
	 * 
	 * @return Boolean containing whether the Objects of this class are Strings.
	 */
	public boolean containsStrings () {
		return (true);
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

	public String [] toArray () {
		String [] arr = new String [this.values.size ()];
		
		for (int i = 0; (i < this.values.size ()); ++i) {
			arr[i] = this.values.get (i).toString ();
		}
		
		return (arr);
	}

}
