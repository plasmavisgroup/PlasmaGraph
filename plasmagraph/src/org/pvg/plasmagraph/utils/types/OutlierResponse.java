package org.pvg.plasmagraph.utils.types;

/**
 * <p>Setting container object for the possible responses of the Outlier Search
 * optional tool.
 * 
 * <p>The possible options are:
 * 
 * <ul>
 * <li>NONE: (Default) No Scanning will be performed.</li>
 * <li>WARN: Scanning will be performed, and the results will be provided before requesting
 * to remove the points or not. (Recommended)</li>
 * <li>REMOVE: Scanning will be performed, and the results will be provided. However,
 * the points will be removed automatically. Suggested in cases in which the outliers have
 * already been verified beforehand.</li>
 * </ul>
 * 
 * @author Plasma Visualization Group
 */
public final class OutlierResponse {
	/** Reference for no Outlier Scanning. */
    public static final OutlierResponse NONE = new OutlierResponse ("No Scanning");
    /** Reference for a "Warn" Response. */
    public static final OutlierResponse WARN = new OutlierResponse ("Warn");
    /** Reference for a "Remove" Response. */
    public static final OutlierResponse REMOVE = new OutlierResponse ("Remove");
    
    /** Name of chart type. */
    private String name;
    
    private OutlierResponse (String name) {
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
        if (!(obj instanceof OutlierResponse )) { return (false); }
        OutlierResponse c = (OutlierResponse) obj;
        
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
    			{OutlierResponse.NONE.toString (),
    			OutlierResponse.WARN.toString (), 
    			OutlierResponse.REMOVE.toString ()});
    }

    /**
     * <p>Converts a string of text that is hopefully the string version of an object in this class
     * to an object of this class' type. Default is "NONE", assuming the output doesn't match
     * anything else.
     * 
     * @param output The string to translate into an object of OutlierResponse.
     * @return An OutlierResponse object that is either the default value or the objectized
     * representation of the parameter provided.
     */
	public static OutlierResponse parse (String output) {
		
		if (OutlierResponse.WARN.toString ().equals (output)) {
			
			return (OutlierResponse.WARN);
			
		} else if (OutlierResponse.REMOVE.toString ().equals (output)) {
			
			return (OutlierResponse.REMOVE);
			
		} else {
			
			return (OutlierResponse.NONE);
			
		}
	}
}
