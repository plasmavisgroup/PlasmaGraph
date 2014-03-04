package org.pvg.plasmagraph.utils.types;

/**
 * File type container object. Describes the file types that are usable in this program.
 * 
 * @author Gerardo A. Navas Morales
 */
public class FileType {
	/** Reference for the "CSV" File Type. */
	public static final FileType CSV = new FileType ("csv");
	/** Reference for the "MAT" File Type. */
	public static final FileType MAT = new FileType ("mat");
	/** Name of File Type type. */
	private String name;
	
	private FileType (String name) {
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
		if (!(obj instanceof FileType )) { return (false); }
		FileType c = (FileType) obj;
		
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
    			{FileType.CSV.toString (), FileType.MAT.toString ()});
    }
}
