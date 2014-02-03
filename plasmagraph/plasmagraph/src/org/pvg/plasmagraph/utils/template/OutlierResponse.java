package org.pvg.plasmagraph.utils.template;


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
}
