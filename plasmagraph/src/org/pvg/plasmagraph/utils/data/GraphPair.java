package org.pvg.plasmagraph.utils.data;

/**
 * Container object for the DataReference class.
 * Contains the indexes of two different Data Columns in the main DataSet and
 * their name on the ListModel.
 * 
 * @author Gerardo A. Navas Morales
 */
public class GraphPair {
	/** Index of the grouping DataColumn. -1 signifies none. */
	private int group_index;
	/** Name of the grouping DataColumn. "" signifies none. */
	private String group_name;
	/** Index of the first DataColumn to graph. */
	private int index1;
	/** Name of the first DataColumn to graph. */
	private String name1;
	/** Index of the second DataColumn to graph. */
	private int index2;
	/** Name of the second DataColumn to graph. */
	private String name2;
	
	/**
	 * Constructor; creates a new grouped Pair.
	 * 
	 * @param group Group index.
	 * @param group_name Group index name.
	 * @param column1 First index to graph.
	 * @param column1_name First column index name.
	 * @param column2 Second index to graph.
	 * @param column2_name Second column index name.
	 */
	public GraphPair (int group, String group_name, 
			int column1, String column1_name, 
			int column2, String column2_name) {
		this.group_index = group;
		this.group_name = group_name;
		this.index1 = column1;
		this.name1 = column1_name;
		this.index2 = column2;
		this.name2 = column2_name;
	}
	
	/**
	 * Constructor; creates a new ungrouped Pair.
	 * 
	 * @param column1 First index to graph.
	 * @param column1_name First column index name.
	 * @param column2 Second index to graph.
	 * @param column2_name Second column index name.
	 */
	public GraphPair (int column1, String column1_name, 
			int column2, String column2_name) {
		this.group_index = -1;
		this.group_name = "None";
		this.index1 = column1;
		this.name1 = column1_name;
		this.index2 = column2;
		this.name2 = column2_name;
	}
	
	/**
	 * Default constructor; used for resetting in the DataReference.
	 */
	public GraphPair () {
		this.group_index = -1;
		this.group_name = "None";
		this.index1 = -1;
		this.name1 = "";
		this.index2 = -1;
		this.name2 = "";
	}

	/**
	 * Getter; Provides the object's group DataColumn index value.
	 * 
	 * @return group_index, an index of the group DataColumn in the main DataSet.
	 */
	public int getGroup () {
		return (group_index);
		
	}
	
	/**
	 * Getter; Provides the object's group DataColumn index value.
	 * 
	 * @return group_index, an index of the group DataColumn in the main DataSet.
	 */
	public String getGroupName () {
		return (group_name);
		
	}
	
	/**
	 * Getter; Provides the object's first DataColumn index value.
	 * 
	 * @return index1, an index of a DataColumn in the main DataSet.
	 */
	public int getIndex1 () {
		return (index1);
		
	}
	
	/**
	 * Getter; Provides the object's first DataColumn index value.
	 * 
	 * @return index1, an index of a DataColumn in the main DataSet.
	 */
	public String getIndex1Name () {
		return (name1);
		
	}
	
	/**
	 * Getter; Provides the object's second DataColumn index value.
	 * 
	 * @return index2, an index of a DataColumn in the main DataSet.
	 */
	public int getIndex2 () {
		return (index2);
		
	}
	
	/**
	 * Getter; Provides the object's second DataColumn index value.
	 * 
	 * @return index2, an index of a DataColumn in the main DataSet.
	 */
	public String getIndex2Name () {
		return (name2);
		
	}
	
	@Override
	public String toString () {
		return (group_name + ": <" + name1 + ", " + name2 + ">");
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

	/**
	 * Getter method. Returns the combination of both columns' names.
	 * 
	 * @return A String containing the combination of both column names.
	 */
	public String getName () {
		return (this.name1 + " vs. " + this.name2);
	}

	/**
	 * Getter method. States the status of the data within this object.
	 * 
	 * @return True if this object was initialized with parameters; else, False.
	 */
	public boolean isEmpty () {
		return (this.group_index == -1 &&
				this.group_name == "None" &&
				this.index1 == -1 &&
				this.name1 == "" &&
				this.index2 == -1 &&
				this.name2 == "");
	}

	/**
	 * Getter method. States whether the data will be grouped or not.
	 * 
	 * @return True if the "group_index" and "group_name" variables are initialized; else, False.
	 */
	public boolean isGrouped () {
		return (this.group_index != -1 &&
				this.group_name != "None");
	}
	
}