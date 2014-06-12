package org.pvg.plasmagraph.utils.types;

/**
 * <p>Setting container object for the possible types of distance measurements usable
 * during outlier calculations. These settings are only used when Outlier Searching is 
 * enabled for a graph.
 * 
 * <p>The possible options are:
 * 
 * <ul>
 * <li>MAHALANOBIS: (Default) The searching will automatically utilize the Mahalanobis distance
 * to calculate a special version of the standard deviation.</li>
 * <li>USER: The user must provide the maximum acceptable distance between the line
 * cluster and any other point.</li>
 * </ul>
 * 
 * @author Plasma Visualization Group
 */
public class OutlierDistanceType {
	
	/** Reference for the "Mahalanobis Distance" Distance Calculation. */
    public static final OutlierDistanceType MAHALANOBIS = new OutlierDistanceType ("Mahalanobis Distance");
    /** Reference for the "User-Provided Distance" Distance Calculation. */
    public static final OutlierDistanceType USER = new OutlierDistanceType ("User-Provided Distance");
    
    /** Name of chart type. */
    private String name;
    
    private OutlierDistanceType (String name) {
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
    			{OutlierDistanceType.USER.toString ()
    			//, OutlierDistanceType.MAHALANOBIS.toString ()
    			});
    }

    /**
     * <p>Converts a string of text that is hopefully the string version of an object in this class
     * to an object of this class' type. Default is "MAHALANOBIS", assuming the output doesn't match
     * anything else.
     * 
     * @param output The string to translate into an object of OutlierDistanceType.
     * @return An OutlierDistanceType object that is either the default value or the objectized
     * representation of the parameter provided.
     */
	public static OutlierDistanceType parse (String output) {

		if (OutlierDistanceType.USER.toString ().equals (output)) {
			
			return (OutlierDistanceType.USER);
			
		} else {
			
			return (OutlierDistanceType.MAHALANOBIS);
			
		}
		
	}
}
