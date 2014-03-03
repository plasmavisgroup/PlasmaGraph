package org.pvg.plasmagraph.utils.data;

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
		name = n.trim ();
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
		name = n.trim ();
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
	
	public String getName(){
		return (this.name);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Comparing method. Type variable is compared to the MLArray types in JMatIO.
	 * Provides verification for Doubles.
	 * 
	 * @return Boolean containing whether the Objects of this class are Doubles.
	 */
	public boolean containsDoubles () {
		return (type.equals (ColumnType.DOUBLE));
	}
	
	/**
	 * Comparing method. Type variable is compared to the MLArray types in JMatIO.
	 * Provides verification for Strings. (PlasmaGraph assumes Chars are turned into
	 * strings when read from a MATLAB file.)
	 * 
	 * @return Boolean containing whether the Objects of this class are Strings.
	 */
	public boolean containsStrings () {
		return (type.equals (ColumnType.STRING));
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

	public double [] toDoubleArray () {
		double [] arr = new double [this.values.size ()];
		
		for (int i = 0; (i < this.values.size ()); ++i) {
			arr[i] = (Double) this.values.get (i);
		}
		
		return (arr);
	}

	/**
	 * Getter method. Provides if there are any items in the ArrayList.
	 * 
	 * @return Boolean stating if the ArrayList in this object is populated by at least one object.
	 */
	public boolean isEmpty () {
		return (this.values.isEmpty ());
	}

	public void addAll(DataColumn column) {
		for(int index = 0; index < column.size(); index++){
			this.values.add((E) column.get(index));
		}		
	}
        
        @Override
        public boolean equals(Object o){
            boolean rval = false;
            
            if(o instanceof DataColumn){
                rval = true;
                DataColumn dc = (DataColumn) o;
                
                if(this.size() != dc.size()){
                	
                    rval = false;
                }
                
                if(!this.getName().equals (dc.getName())){
                	
                	System.out.println (this.getName () + " versus " + dc.getName ());
                    rval = false;
                }
                
                if(this.getType() != dc.getType()){
                	
                    rval = false;
                }
                
                for(int i = 0; i < this.size() && rval; i++){
                    if(!this.get(i).equals (dc.get(i))){
                        rval = false;
                    }
                }
            }
            
            return rval;
        }

}
