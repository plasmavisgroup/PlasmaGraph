package org.pvg.plasmagraph.utils.types;

/**
 * Setting container object for the possible types of Graphs available in 
 * the PlasmaGraph program.
 * 
 * @author Gerardo A. Navas Morales
 */
public final class AxisType {
	/** Reference for a "Number Axis" Chart. */
	public static final AxisType STANDARD = new AxisType ("Number Axis");
	/** Reference for a "Logarithmic Axis" Chart. */
	public static final AxisType LOG = new AxisType ("Logarithmic Axis");
	/** Reference for a "Date Axis" Chart. */
	public static final AxisType DATE = new AxisType ("Date Axis");
	/** Reference for a "Category Axis" Chart. */
	public static final AxisType CATEGORY = new AxisType ("Category Axis");
	/** Name of chart type. */
	private String name;
	
	private AxisType (String name) {
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
		if (!(obj instanceof AxisType )) { return (false); }
		AxisType c = (AxisType) obj;
		
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
	public static String [] getXYOptions () {
    	return (new String []
    			{AxisType.STANDARD.toString (), AxisType.LOG.toString ()});
    }

	/**
	 * Provides a list of options that may be selected.
	 * Specifically for use in the various views.
	 * 
	 * @return A String array containing all the possible types this class holds.
	 */
	public static String [] getBarOptions () {
    	return (new String []
    			{AxisType.CATEGORY.toString ()});//, AxisType.DATE.toString ()});
    }
}
