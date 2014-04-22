package org.pvg.plasmagraph.utils.types;

/**
 * <p>Setting container object for the possible types of interpolation performed
 * by the Interpolation optional tool.
 * 
 * <p>The available options are:
 * 
 * <ul>
 * <li>NONE: (Default) The system will not create any interpolations.</li>
 * <li>LINEAR: The system will create a linear equation to fit as many points
 * as possible.</li>
 * <li>QUADRATIC: The system will create a 4th degree polynomial equation to fit
 * as many points as possible.</li>
 * <li>CUBIC: The system will create a 4th degree polynomial equation to fit
 * as many points as possible.</li>
 * <li>QUARTIC: (Disabled) The system will create a 4th degree polynomial equation to fit
 * as many points as possible.</li>
 * <li>SPLINE: The system will create a unclamped Spline Interpolation that will
 * pass through every point in the graph.</li>
 * </ul>
 * 
 * @author Gerardo A. Navas Morales
 */
public final class InterpolationType {
	/** Reference for No Regression. */
    public static final InterpolationType NONE = new InterpolationType ("None");
    /** Reference for a "Linear" Regression. */
    public static final InterpolationType LINEAR = new InterpolationType ("Linear");
    /** Reference for a "Quadratic" Polynomial Regression. */
    public static final InterpolationType QUADRATIC = new InterpolationType ("Quadratic");
    /** Reference for a "Cubic" Polynomial Regression. */
    public static final InterpolationType CUBIC = new InterpolationType ("Cubic");
    /** Reference for a "Quartic" Polynomial Regression. */
    public static final InterpolationType QUARTIC = new InterpolationType ("Quartic");
    /** Reference for a "Polynomial Spline" Regression. */
    public static final InterpolationType SPLINE = new InterpolationType ("Spline");
    
    /** Name of chart type. */
    private String name;
    
    private InterpolationType (String name) {
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
        if (!(obj instanceof InterpolationType )) { return (false); }
        InterpolationType c = (InterpolationType) obj;
        
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
    			{InterpolationType.NONE.toString (), 
    			InterpolationType.LINEAR.toString (), 
    			InterpolationType.QUADRATIC.toString (),
    			InterpolationType.CUBIC.toString (), 
    			//InterpolationType.QUARTIC.toString (), 
    			InterpolationType.SPLINE.toString ()});
    }

    /**
     * <p>Converts a string of text that is hopefully the string version of an object in this class
     * to an object of this class' type. Default is "LINEAR", assuming the output doesn't match
     * anything else.
     * 
     * @param output The string to translate into an object of InterpolationType.
     * @return An InterpolationType object that is either the default value or the objectized
     * representation of the parameter provided.
     */
	public static InterpolationType parse (String output) {
		
		if (InterpolationType.SPLINE.toString ().equals (output)) {
			
			return (InterpolationType.SPLINE);
			
		} else if (InterpolationType.QUARTIC.toString ().equals (output)) {
			
			return (InterpolationType.QUARTIC);
			
		} else if (CUBIC.toString ().equals (output)) {
			
			return (InterpolationType.CUBIC);
			
		} else if (InterpolationType.QUADRATIC.toString ().equals (output)) {
			
			return (InterpolationType.QUADRATIC);
			
		} else {
			
			return (InterpolationType.LINEAR);
			
		}
		
	}
}
