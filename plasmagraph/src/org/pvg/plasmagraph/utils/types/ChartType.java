package org.pvg.plasmagraph.utils.types;

/**
 * Setting container object for the possible types of Graphs available in 
 * the PlasmaGraph program.
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
	 * Provides a list of options that may be selected.
	 * Specifically for use in the various views.
	 * 
	 * @return A String array containing all the possible types this class holds.
	 */
	public static String [] getOptions () {
    	return (new String []
    			{ChartType.XY_GRAPH.toString (), ChartType.BAR_GRAPH.toString ()});
    }
}
