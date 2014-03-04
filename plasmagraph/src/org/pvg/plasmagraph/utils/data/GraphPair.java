package org.pvg.plasmagraph.utils.data;

/**
 * Container object for the DataReference class.
 * Contains the indexes of two different Data Columns in the main DataSet and
 * their name on the ListModel.
 * 
 * @author Gerardo A. Navas Morales
 */
public class GraphPair {
	/** Index of the first DataColumn to graph. */
	private final int index1;
	/** Index of the second DataColumn to graph. */
	private final int index2;
	/** Name of the DataSet as per its appearance on the ListModel. */
	private final String name;
	
	/**
	 * Constructor; creates a new Pair.
	 * 
	 * @param i First index to graph.
	 * @param j Second index to graph.
	 * @param n Name of DataSet provided in ListModel.
	 */
	public GraphPair (int i, int j, String n) {
		index1 = i;
		index2 = j;
		name = n;
	}
	
	/**
	 * Getter; Provides the object's first DataColumn index value.
	 * 
	 * @return index1, an index of a DataColumn in the main DataSet.
	 */
	public int getIndex1 () {
		return index1;
		
	}
	
	/**
	 * Getter; Provides the object's second DataColumn index value.
	 * 
	 * @return index2, an index of a DataColumn in the main DataSet.
	 */
	public int getIndex2 () {
		return index2;
		
	}
	
	/**
	 * Getter; Provides the object's name value given in the ListModel.
	 * 
	 * @return name, the name provided in the ListModel for this Pair.
	 */
	public String getName () {
		return name;
		
	}
	
	@Override
	public String toString () {
		return ("<" + index1 + ", " + index2 + ">");
	}
	
	@Override
	public boolean equals (Object obj) {
		// Same object?
		if (this == obj) { return (true); }
		
		// Not the same class?
		if (!(obj instanceof GraphPair )) { return (false); }
		GraphPair c = (GraphPair) obj;
		
		// Same name?
		if (!this.toString().equals(c.toString())) { return (false); }
		
		// Then they're the same type of Pair.
		return (true);
	}
	
}