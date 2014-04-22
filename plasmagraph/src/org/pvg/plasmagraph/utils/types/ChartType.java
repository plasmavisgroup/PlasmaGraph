package org.pvg.plasmagraph.utils.types;

import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;

/**
 * <p>Setting container object for the possible types of Graphs available in 
 * the PlasmaGraph program.
 * 
 * <p>The available options are:
 * 
 * <ul>
 * <li>XY_GRAPH: (Default) The system will create an XY Scatter Graph with the
 * data provided.</li>
 * <li>BAR_GRAPH: (Disabled) The system will create a Bar Graph with the data
 * provided.</li>
 * </ul>
 * 
 * @author Gerardo A. Navas Morales
 */
public final class ChartType {
	/** Reference for a "XY Graph" Chart. */
	public static final ChartType XY_GRAPH = new ChartType ("XY Graph");
	/** Reference for a "Bar Graph" Chart. */
	public static final ChartType BAR_GRAPH = new ChartType ("Bar Graph");
	/** Name of chart type. */
	private String name;
	
	private ChartType (String name) {
		this.name = name;
	}
	
	@Override
	public String toString () {
		return (name);
	}
	
	@Override
	public boolean equals (Object obj) {
		// Same object?
		if (this == obj) { return (true); }
		
		// Not the same class?
		if (!(obj instanceof ChartType )) { return (false); }
		ChartType c = (ChartType) obj;
		
		// Same name?
		if (!this.name.equals(c.toString())) { return (false); }
		
		// Then they're the same type of Graph.
		return (true);
	}
	
	/**
	 * <p>Provides a list of options that may be selected. Specifically for use 
	 * in the various views that compose PlasmaGraph.
	 * 
	 * <p>The order of the array is designed to contain the default option, that which
	 * counts as the starting point for the data type, as the first option.
	 * 
	 * @return A String array containing all the valid possible types this class holds.
	 */
	public static String [] getOptions () {
    	return (new String []
    			{ChartType.XY_GRAPH.toString ()//, 
    			//ChartType.BAR_GRAPH.toString ()
    			});
    }

	/**
	 * Allows for easy verification of column types based on the GraphPair provided.
	 * 
	 * @param hd The HeaderData object containing all the metadata regarding columns.
	 * @param p The GraphPair object containing the X Axis and Y Axis column indexes.
	 * @return True if the ColumnTypes of each column are of the proper types for their ChartType; else, False.
	 */
	public boolean hasProperColumns (HeaderData hd, GraphPair p) {
		
		if (ChartType.XY_GRAPH.toString ().equals (this.name)) {
			
			// Correct pairings: (Double / Double).
			// In that order.
			return (ColumnType.DOUBLE.equals (hd.get (p.getXColumnIndex ()).getValue ()) &&
					ColumnType.DOUBLE.equals (hd.get (p.getYColumnIndex ()).getValue ()));
			
		} else { // if (ChartType.XY_GRAPH.toString ().equals (this.name)) {
			
			// Correct pairings: (String / Double) or (DateTime / Double).
			// In that order.
			return ((ColumnType.STRING.equals (hd.get (p.getXColumnIndex ()).getValue ()) &&
					ColumnType.DOUBLE.equals (hd.get (p.getYColumnIndex ()).getValue ())) /*||
					(ColumnType.DATETIME.equals (hd.get (p.getXColumnIndex ()).getValue ()) &&
					ColumnType.DOUBLE.equals (hd.get (p.getYColumnIndex ()).getValue ()))*/
					);
			
		}
	}

	/**
	 * <p>Converts a string of text that is hopefully the string version of an object in this class
     * to an object of this class' type. Default is "XY_GRAPH", assuming the output doesn't match
     * anything else.
     * 
     * @param output The string to translate into an object of ChartType.
     * @return An ChartType object that is either the default value or the objectized
     * representation of the parameter provided.
	 */
	public static ChartType parse (String output) {
		
		if (ChartType.BAR_GRAPH.equals (output)) {
			
			return (ChartType.BAR_GRAPH);
			
		} else {
			
			return (ChartType.XY_GRAPH);
			
		}
	}
}
