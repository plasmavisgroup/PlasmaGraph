package org.pvg.plasmagraph.utils.types;

/**
 * Setting container object for the possible types of Exceptions in 
 * the PlasmaGraph program.
 * 
 * @author Plasma Visualization Group
 */
public class ExceptionType {
	/** Reference for a "Incorrect JFileChooser Selection" NullPointerException. */
	public static final ExceptionType JFILECHOOSER_SELECTION = new ExceptionType ("JFileChooser Selection");
	/** Name of column type. */
	private String name;
	
	private ExceptionType (String name) {
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
		if (!(obj instanceof ColumnType )) { return (false); }
		ColumnType c = (ColumnType) obj;
		
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
    			{ExceptionType.JFILECHOOSER_SELECTION.toString ()});
    }
}
