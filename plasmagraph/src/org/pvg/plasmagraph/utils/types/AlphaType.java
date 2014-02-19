package org.pvg.plasmagraph.utils.types;

/**
 * Setting container object for the typical Alpha values.
 * 
 * @author Gerardo A. Navas Morales
 */
public final class AlphaType {
	/** Reference for the 99% CI. */
	public static final AlphaType CI99 = new AlphaType ("99% CI");
	/** Reference for the 98% CI. */
	public static final AlphaType CI98 = new AlphaType ("98% CI");
	/** Reference for the 95% CI. */
	public static final AlphaType CI95 = new AlphaType ("95% CI");
	/** Reference for the 90% CI. */
	public static final AlphaType CI90 = new AlphaType ("90% CI");
	/** Reference for all invalid CIs. */
	public static final AlphaType INVALID = new AlphaType ("INVALID CI");
	/** Name of chart type. */
	private String name;
	
	private AlphaType (String name) {
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
		if (!(obj instanceof AlphaType )) { return (false); }
		AlphaType c = (AlphaType) obj;
		
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
    			{AlphaType.CI99.toString (), AlphaType.CI98.toString (),
    			AlphaType.CI95.toString (), AlphaType.CI90.toString (),});
    }

	/**
	 * Provides the numeric representation of the AlphaType.
	 * 
	 * @return A double, containing the alpha value in decimal format.
	 */
	public double getValue () {
		switch (name) {
		case "99% CI":
			return (0.01);
		case "98% CI":
			return (0.02);
		case "95% CI":
			return (0.05);
		case "90% CI":
			return (0.10);
		default:
			return (0.00);
		}
	}
}