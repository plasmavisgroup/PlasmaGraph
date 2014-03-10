package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;

/**
 * A reference-holding object, to be used parallel to DataSet, that maintains 
 * DataColumn indexes and their name on the DataSetView's JLists' ListModels.
 * An answer to avoid doubling up on data usage by not having multiple DataSets
 * for the graphing process.
 * 
 * @author Gerardo A. Navas Morales
 */
public class DataReference implements Iterable<GraphPair> {
	/** Collection of listeners for any change that occurs in this DataReference. */
    private Set <ChangeListener> listeners;
	/** Container for Reference Pairs. */
	private ArrayList<GraphPair> table;
	
	/**
	 * Constructor; initializes the ArrayList container.
	 */
	public DataReference () {
		this.listeners = new HashSet <> ();
		table = new ArrayList <> ();
	}
	
	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * Adds the Pair object provided to it and responds if the process succeeded or not.
	 * 
	 * @param p Pair to add to the ArrayList.
	 * @return A boolean specifying method success (true) or failure (false).
	 */
	public boolean add (GraphPair p) {
	    // Check to see if the indices are even possible.
	    // Also check to see if the name of the Pair is even useful.
	    if ((p.getIndex1 () >= 0) && (p.getIndex2 () >= 0) &&
	            (p.getName ().contains ("vs."))) {
	    	boolean b = table.add(p);
	    	this.notifyListeners ();
	        return (b);
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
	    return (this.add (new GraphPair (p1, p2, name)));
	}

	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * Removes the Pair object at the index and returns it. 
	 * 
	 * @param index Location on the ArrayList where the object to be removed is located.
	 * @return A Pair that was removed from the ArrayList.
	 */
	public GraphPair remove (int index) {
		GraphPair p = table.remove (index);
		this.notifyListeners ();
	    return (p);
    }
	
	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * Removes the Pair object provided and responds if the process succeeded or not.
	 * 
	 * @param p Pair to remove from the ArrayList.
	 * @return A boolean specifying method success (true) or failure (false).
	 */
	public boolean remove (GraphPair p) {
		boolean b = table.remove (p);
		this.notifyListeners ();
		return (b);
	}
	
	/**
	 * Removes all pairs from the DataReference object.
	 * Only used to reset the Pairs when an incompatible Data file is imported.
	 */
	public void reset () {
		this.table.clear ();
		this.notifyListeners ();
	}

	/**
	 * Interfacing method between the exterior and the table contained in this object.
	 * Finds the Pair object provided and provides its index location in the ArrayList.
	 * 
	 * @param p Pair to find in the ArrayList.
	 * @return Index location of the object in the ArrayList.
	 */
	public int findIndex (GraphPair p) {
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
    public GraphPair findPair (int index) {
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
    public GraphPair findPair (String s) {
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
	public GraphPair get (int index) {
	    return (table.get (index));
	}
	
	/**
     * Provides an ArrayList of names for each of the Pairs in the table ArrayList.
     * @return ArrayList containing Pair names.
     */
    public String [] getNames () {
        String [] names = new String [table.size ()];
        ArrayList <String> pair_names = new ArrayList <> ();
        for (GraphPair p : this.table) {
            pair_names.add (p.getName ());
        }
        pair_names.toArray (names);
        return (names);
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
	    
	    for (GraphPair p : this.table) {
	        s += p.toString () + "\n";
	    }
	    
	    return (s);
	}

	@Override
	public Iterator<GraphPair> iterator () {
		return (this.table.iterator ());
	}

	public int size () {
		return (this.table.size ());
	}
	
	// Event Methods
	/**
	 * Adds the listener provided to the notification list.
	 * 
	 * @param listener Listener to add to the notification list.
	 */
	public void addChangeListener (ChangeListener listener) {
	    this.listeners.add (listener);
	}
	
	/**
	 * Removes the listener provided from the notification list.
	 * 
	 * @param listener Listener to remove from notification list.
	 */
	public void removeChangeListener (ChangeListener listener) {
	    this.listeners.remove (listener);
	}
	
	/**
	 * Sends a ChangeEvent to all listeners of this object,
	 * declaring that this Template object has been changed in some way.
	 */
	public void notifyListeners () {
	    for (ChangeListener c : listeners) {
	        c.stateChanged (new ChangeEvent (this));
	    }
	}
	
}