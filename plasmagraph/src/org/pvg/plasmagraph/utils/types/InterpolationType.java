package org.pvg.plasmagraph.utils.types;

/**
 * Setting container object for the possible types of interpolation performed
 * by the Interpolation optional tool. (Some are regressions, but they work well.
 * 
 * @author Gerardo A. Navas Morales
 */
public final class InterpolationType {
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
	 * Provides a list of options that may be selected.
	 * Specifically for use in the various views.
	 * 
	 * @return A String array containing all the possible types this class holds.
	 */
    public static String [] getOptions () {
    	return (new String []
    			{InterpolationType.LINEAR.toString (), InterpolationType.QUADRATIC.toString (),
    			InterpolationType.CUBIC.toString (), InterpolationType.QUARTIC.toString (),
    			InterpolationType.SPLINE.toString ()});
    }
}
