package org.pvg.plasmagraph.utils.types;

import org.apache.commons.lang3.math.NumberUtils;

import com.jmatio.types.MLArray;

/**
 * Setting container object for the possible types of Columns available in 
 * the PlasmaGraph program.
 * 
 * @author Gerardo A. Navas Morales
 */
public final class ColumnType {
	/** Reference for a "Double" Column. */
	public static final ColumnType DOUBLE = new ColumnType ("Double");
	/** Reference for a "String" Column. */
	public static final ColumnType STRING = new ColumnType ("String");
	/** Reference for a "DateTime" Column. */
	public static final ColumnType DATETIME = new ColumnType ("Date Time");
	/** Name of column type. */
	private String name;
	
	private ColumnType (String name) {
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
	 * Provides a list of options that may be selected.
	 * Specifically for use in the various views.
	 * 
	 * @return A String array containing all the possible types this class holds.
	 */
	public static String [] getOptions () {
    	return (new String []
    			{ColumnType.DOUBLE.toString (), ColumnType.STRING.toString (),
    			ColumnType.DATETIME.toString ()});
    }

	/**
	 * Checks if the type provided is a valid type.
	 * 
	 * @return True if the ColumnType object contains the word "Double", "String", or "Date Time"; else, False.
	 */
	public boolean isValidType () {
		return ((this.name == "Double") || (this.name == "String") 
				|| (this.name == "Date Time"));
	}
}
