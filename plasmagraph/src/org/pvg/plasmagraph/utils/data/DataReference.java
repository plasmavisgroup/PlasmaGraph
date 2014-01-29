package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A reference-holding object, to be used parallel to DataSet, that maintains 
 * DataColumn indexes and their name on the DataSetView's JLists' ListModels.
 * An answer to avoid doubling up on data usage by not having multiple DataSets
 * for the graphing process.
 * 
 * @author Gerardo A. Navas Morales
 */
public class DataReference implements Iterator<Pair>, Iterable<Pair>{
	/** Container for Reference Pairs. */
	private ArrayList<Pair> table;
	/** Position of Iterator object; used for the implementation of Iterator and Iterable. */
	private int position = 0;
	
	/**
	 * Constructor; initializes the ArrayList container.
	 */
	public DataReference () {
		table = new ArrayList <Pair> ();
	}
	
	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * Adds the Pair object provided to it and responds if the process succeeded or not.
	 * 
	 * @param p Pair to add to the ArrayList.
	 * @return A boolean specifying method success (true) or failure (false).
	 */
	public boolean add (Pair p) {
		return (table.add(p));
	}

	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * Removes the Pair object at the index and returns it. 
	 * 
	 * @param index Location on the ArrayList where the object to be removed is located.
	 * @return A Pair that was removed from the ArrayList.
	 */
	public Pair remove (int index) {
		return (table.remove(index));
	}
	
	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * Removes the Pair object provided and responds if the process succeeded or not.
	 * 
	 * @param p Pair to remove from the ArrayList.
	 * @return A boolean specifying method success (true) or failure (false).
	 */
	public boolean remove (Pair p) {
		return (table.remove(p));
	}
	
	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * Finds the Pair object provided and provides its index location in the ArrayList.
	 * 
	 * @param p Pair to find in the ArrayList.
	 * @return Index location of the object in the ArrayList.
	 */
	public int find (Pair p) {
		return (table.indexOf(p));
	}
	
	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * Finds the Pair object via the name provided and provides its index location
	 * in the ArrayList.
	 * 
	 * @param s Sting name of the Pair in the ArrayList.
	 * @return Index location of the object in the ArrayList.
	 */
	public int find (String s) {
		int j = 0; boolean found = false;
		for (int i = 0; (i < table.size()) || !found; ++i) {
			if (table.get(i).getName().equals(s)) {
				j = i; found = true;
			}
		}
		return (j);
	}
	
	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * States if an object with the name provided exists in the ArrayList.
	 * 
	 * @param s Sting name of the Pair in the ArrayList.
	 * @return Index location of the object in the ArrayList.
	 */
	public boolean contains (String s) {
		return (find(s) != -1);
	}

	@Override
	public Iterator<Pair> iterator () {
		return (this);
	}

	@Override
	public boolean hasNext () {
		return (position < table.size());
	}

	@Override
	public Pair next () {
		if (position == table.size()) { throw new NoSuchElementException (); }
	    return (table.get(++position));
	}

	@Override
	public void remove () {
		// TODO: Want to really keep it unsupported?
		throw new UnsupportedOperationException();
	}
	
}