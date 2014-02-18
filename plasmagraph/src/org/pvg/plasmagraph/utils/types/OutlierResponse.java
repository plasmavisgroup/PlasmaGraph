package org.pvg.plasmagraph.utils.types;

/**
 * Setting container object for the possible responses of the Outlier Search
 * optional tool.
 * 
 * @author Gerardo A. Navas Morales
 */
public final class OutlierResponse {
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
	 * Provides a list of options that may be selected.
	 * Specifically for use in the various views.
	 * 
	 * @return A String array containing all the possible types this class holds.
	 */
    public static String [] getOptions () {
    	return (new String []
    			{OutlierResponse.WARN.toString (), OutlierResponse.REMOVE.toString ()});
    }
}
