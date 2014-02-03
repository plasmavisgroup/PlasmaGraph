package org.pvg.plasmagraph.utils.template;


public final class InterpolationType {
    /** Reference for a "Linear" Regression. */
    public static final InterpolationType LINEAR = new InterpolationType ("Linear");
    /** Reference for a "Polynomial" Regression. */
    public static final InterpolationType POLYNOMIAL = new InterpolationType ("Polynomial");
    /** Reference for a "Power" Regression. */
    public static final InterpolationType POWER = new InterpolationType ("Power");
    
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
}
