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
	private int x_index;
	/** Name of the first DataColumn to graph. */
	private String x_name;
	/** Index of the second DataColumn to graph. */
	private int y_index;
	/** Name of the second DataColumn to graph. */
	private String y_name;
	
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
		this.x_index = column1;
		this.x_name = column1_name;
		this.y_index = column2;
		this.y_name = column2_name;
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
		this.x_index = column1;
		this.x_name = column1_name;
		this.y_index = column2;
		this.y_name = column2_name;
	}
	
	/**
	 * Default constructor; used for resetting in the DataReference.
	 */
	public GraphPair () {
		this.group_index = -1;
		this.group_name = "None";
		this.x_index = -1;
		this.x_name = "";
		this.y_index = -1;
		this.y_name = "";
	}
	
	/**
	 * Changes only the Group By column name and index for the GraphPair.
	 * 
	 * @param group Group column index.
	 * @param group_name Group column name.
	 */
	public void changeGroup (int group, String group_name) {
		
		this.group_index = group;
		this.group_name = group_name;
		
		System.out.println (this.toString ());
	}
	
	/**
	 * Changes only the X Axis name and index for the GraphPair.
	 * 
	 * @param x X Axis column index.
	 * @param x_name X Axis column name.
	 */
	public void changeX (int x, String x_name) {
		
		this.x_index = x;
		this.x_name = x_name;

		System.out.println (this.toString ());
	}

	/**
	 * Changes only the Y Axis name and index for the GraphPair.
	 * 
	 * @param y Y Axis column index.
	 * @param y_name Y Axis column name.
	 */
	public void changeY (int y, String y_name) {
	
		this.y_index = y;
		this.y_name = y_name;

		System.out.println (this.toString ());
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
	 * @return x_index, an index of a DataColumn in the main DataSet.
	 */
	public int getXIndex () {
		return (x_index);
		
	}
	
	/**
	 * Getter; Provides the object's first DataColumn index value.
	 * 
	 * @return x_index, an index of a DataColumn in the main DataSet.
	 */
	public String getXIndexName () {
		return (x_name);
		
	}
	
	/**
	 * Getter; Provides the object's second DataColumn index value.
	 * 
	 * @return y_index, an index of a DataColumn in the main DataSet.
	 */
	public int getYIndex () {
		return (y_index);
		
	}
	
	/**
	 * Getter; Provides the object's second DataColumn index value.
	 * 
	 * @return y_index, an index of a DataColumn in the main DataSet.
	 */
	public String getYIndexName () {
		return (y_name);
		
	}
	
	@Override
	public String toString () {
		return (group_name + ": <" + x_name + ", " + y_name + ">");
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
		StringBuilder sb = new StringBuilder ();
		if (!this.isGrouped ()) {
			sb.append (this.x_name).append (" vs. ").append (this.y_name);
		}
		
		else {
			sb.append (this.getGroupName ()).append (" Group ");
		}
		
		return (sb.toString ());
	}

	/**
	 * Getter method. States the status of the data within this object.
	 * 
	 * @return True if this object was initialized with parameters; else, False.
	 */
	public boolean isEmpty () {
		return (this.group_index == -1 &&
				this.group_name == "None" &&
				this.x_index == -1 &&
				this.x_name == "" &&
				this.y_index == -1 &&
				this.y_name == "");
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

	/**
	 * Getter method. Returns the number of columns that this GraphPair is containing information on.
	 * 
	 * @return The integer 2 if this object isn't containing a grouping column; else, the integer 3.
	 */
	public int getNumberOfColumns () {
		return (this.isGrouped ()) ? (3) : (2);
	}
	
	/**
	 * Getter method. Tells if the X and Y Column halfs of this pair have been initialized at least once.
	 * 
	 * @return True if both changeX and changeY were called at least once, or if any non-default constructor was called originally; else, False.
	 */
	public boolean isReady () {
		return (this.isXColumnReady () && this.isYColumnReady ());
	}
	
	/**
	 * Returns all the variables contained in this object back to their default state,
	 * as defined in the default constructor.
	 */
	public void reset () {
		this.group_index = -1;
		this.group_name = "None";
		this.x_index = -1;
		this.x_name = "";
		this.y_index = -1;
		this.y_name = "";
	}
	
	/**
	 * Getter method. Checks to see if the X Axis Column is ready to be graphed.
	 * 
	 * @return True if the "x_index" and "x_name" is initialized; else, False.
	 */
	public boolean isXColumnReady () {
		return (this.x_index != -1 &&
				this.x_name != "");
	}
	
	/**
	 * Getter method. Checks to see if the Y Axis Column is ready to be graphed.
	 * 
	 * @returnTrue if the "y_index" and "y_name" is initialized; else, False.
	 */
	public boolean isYColumnReady () {
		return (this.y_index != -1 &&
				this.y_name != "");
	}
	
}