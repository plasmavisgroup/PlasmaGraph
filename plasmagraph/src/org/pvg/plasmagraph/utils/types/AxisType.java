package org.pvg.plasmagraph.utils.types;

/**
 * <p>Setting container object for the possible types of Graphs available in 
 * the PlasmaGraph program.
 * 
 * <p>The available options are:
 * 
 * <ul>
 * <li>STANDARD: (Default - XY_GRAPH) The system will use an axis that displays
 * numbers in the typical manner. Exponents will not be used in this format.</li>
 * <li>LOG: The system will use an axis that displays numbers in a logarithmic
 * scale. Useful for extremely small number ranges. (Usually on the order of
 * 10^-4 and beyond.)</li>
 * <li>DATE: (Disabled) The system will use an axis that displays dates
 * instead of numbers or words.</li>
 * <li>CATEGORY: (Default - BAR_GRAPH) The system will use an axis that displays
 * categories; these are usually words, but can also be numbers.</li>
 * </ul>
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
	 * <p>Provides a list of options for Bar Graphs that may be selected. 
	 * Specifically for use in the various views that compose PlasmaGraph when
	 * the "XY_GRAPH" ChartType option is selected.
	 * 
	 * <p>The order of the array is designed to contain the default option, that which
	 * counts as the starting point for the data type, as the first option.
	 * 
	 * @return A String array containing all the valid possible types this class holds.
	 */
	public static String [] getXYOptions () {
    	return (new String []
    			{AxisType.STANDARD.toString (), AxisType.LOG.toString ()});
    }

	/**
	 * <p>Provides a list of options for Bar Graphs that may be selected. 
	 * Specifically for use in the various views that compose PlasmaGraph when
	 * the "BAR_GRAPH" ChartType option is selected.
	 * 
	 * <p>The order of the array is designed to contain the default option, that which
	 * counts as the starting point for the data type, as the first option.
	 * 
	 * @return A String array containing all the valid possible types this class holds.
	 */
	public static String [] getBarOptions () {
    	return (new String []
    			{AxisType.CATEGORY.toString ()});//, AxisType.DATE.toString ()});
    }

	/**
	 * <p>Converts a string of text that is hopefully the string version of an object in this class
     * to an object of this class' type. Default is "LOG", assuming the output doesn't match
     * anything else.
     * 
     * @param output The string to translate into an object of AxisType.
     * @return An AxisType object that is either the default value or the objectized
     * representation of the parameter provided.
	 */
	public static AxisType parse (String output) {
		
		if (AxisType.STANDARD.toString ().equals (output)) {
			
            return (AxisType.STANDARD);
            
        } else {
        	
        	return (AxisType.LOG);
        	
        }
	}
}
