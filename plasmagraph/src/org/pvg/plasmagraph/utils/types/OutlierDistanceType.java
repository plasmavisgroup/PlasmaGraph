package org.pvg.plasmagraph.utils.types;

public class OutlierDistanceType {
	
	/** Reference for the "Mahalanobis Distance" Distance Calculation. */
    public static final OutlierDistanceType MAHALANOBIS = new OutlierDistanceType ("Mahalanobis Distance");
    /** Reference for the "User-Provided Distance" Distance Calculation. */
    public static final OutlierDistanceType USER = new OutlierDistanceType ("User-Provided Distance");
    /** Reference for the "Standard Deviation" Distance Calculation. */
    //public static final OutlierDistanceType STDEV = new OutlierDistanceType ("Standard Deviation");
    
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
	 * Provides a list of options that may be selected.
	 * Specifically for use in the various views.
	 * 
	 * @return A String array containing all the possible types this class holds.
	 */
    public static String [] getOptions () {
    	return (new String []
    			{OutlierDistanceType.MAHALANOBIS.toString (),
    			OutlierDistanceType.USER.toString ()
    			//OutlierDistanceType.STDEV.toString ()
    			});
    }
}
