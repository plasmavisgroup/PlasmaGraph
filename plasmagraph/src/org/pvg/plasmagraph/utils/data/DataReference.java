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
	    // Check to see if the indices are even possible.
	    // Also check to see if the name of the Pair is even useful.
	    if ((p.getIndex1 () > 0) && (p.getIndex2 () > 0) &&
	            (p.getName ().contains ("vs."))) {
	        return (table.add(p));
	    } else {
	        return (false);
	    }
	}
	
	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * Adds the Pair object components provided to it and responds if the process succeeded or not.
	 * 
	 * @param p1 Index position of the first half of the pair object.
	 * @param p2 Index position of the second half of the pair object.
	 * @param name Name of the Pair.
	 * @return A boolean specifying method success (true) or failure (false).
	 */
	public boolean add (int p1, int p2, String name) {
	    // Check to see if the indices are even possible.
	    // Also check to see if the name of the Pair is even useful.
	    if ((p1 > 0) && (p2 > 0) && (name.contains ("vs."))) {
	        return (table.add (new Pair (p1, p2, name)));
	    } else {
	        return (false);
	    }
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
	public int findIndex (Pair p) {
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
	public int findIndex (String s) {
		int j = -1; boolean found = false;
		for (int i = 0; (i < table.size()) && !found; ++i) {
			if (table.get(i).getName().equals(s)) {
				j = i; found = true;
			}
		}
		return (j);
	}
	
	/**
     * Interfacing method between the exterior and the table contained in this object.
     * Finds the Pair object provided and provides its index location in the ArrayList.
     * 
     * @param p Pair to find in the ArrayList.
     * @return Index location of the object in the ArrayList.
     */
    public Pair findPair (int index) {
        return (table.get (index));
    }
    
    /**
     * Interfacing method between the exterior and the table contained in this object.
     * Finds the Pair object via the name provided and provides its index location
     * in the ArrayList.
     * 
     * @param s Sting name of the Pair in the ArrayList.
     * @return Index location of the object in the ArrayList.
     */
    public Pair findPair (String s) {
        int j = 0; boolean found = false;
        for (int i = 0; (i < table.size()) && !found; ++i) {
            if (table.get(i).getName().equals(s)) {
                j = i; found = true;
            }
        }
        return (this.table.get (j));
    }
	
	/**
	 * Returns a pair as specified by an index.
	 * 
	 * @param index Location of Pair.
	 * @return The desired Pair at the provided index.
	 */
	public Pair get (int index) {
	    return (table.get (index));
	}
	
	/**
     * Provides an ArrayList of names for each of the Pairs in the table ArrayList.
     * @return ArrayList containing Pair names.
     */
    public String [] getNames () {
        String [] names = new String [table.size ()];
        ArrayList <String> pair_names = new ArrayList <String> ();
        for (Pair p : this.table) {
            pair_names.add (p.getName ());
        }
        pair_names.toArray (names);
        return (names);
    }
    
    /**
     * Provides a DataSet around a Pair whose index is provided.
     * Based on the primary dataset passed by reference.
     * 
     * @param pair_position Index location of the Pair to extract.
     * @param main_dataset Reference to the primary DataSet.
     * @return A new DataSet, containing the desired Pair of data.
     */
    public DataSet createPair (int pair_position, DataSet main_dataset) {
        DataSet d = new DataSet ();
        
        Pair p = get (pair_position);
        	
    	d.add (main_dataset.get(p.getIndex1 () - 1));
        d.add (main_dataset.get(p.getIndex2 () - 1));
        
        return (d);
    }
	
	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * States if an object with the name provided exists in the ArrayList.
	 * 
	 * @param s Sting name of the Pair in the ArrayList.
	 * @return Index location of the object in the ArrayList.
	 */
	public boolean contains (String s) {
		return (findIndex(s) != -1);
	}
	
	public boolean isEmpty () {
	    return (table.isEmpty ());
	}
	
	@Override
	public String toString () {
	    String s = "";
	    
	    for (Pair p : this.table) {
	        s += p.toString () + "\n";
	    }
	    
	    return (s);
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
		throw new UnsupportedOperationException();
	}
	
}