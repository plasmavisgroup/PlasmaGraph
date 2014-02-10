package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.jmatio.types.MLArray;

public interface DataColumn {

	/**
	 * Getter method. Provides the current used size of the underlying ArrayList.
	 * 
	 * @return An integer containing the size of the ArrayList.
	 */
	public int size ();
	
	/**
	 * Getter method. Provides the column's name.
	 * 
	 * @return A string containing the name of this column.
	 */
	public String getColumnName ();
	
	/**
	 * Getter method. Provides the string representation of the "type" variable.
	 * 
	 * @return A String containing the type of the column.
	 */
	public String getType ();
	
	/**
	 * Comparing method. Type variable is compared to the MLArray types in JMatIO.
	 * Provides verification for Doubles.
	 * 
	 * @return Boolean containing whether the Objects of this class are Doubles.
	 */
	public boolean containsDoubles ();
	
	/**
	 * Comparing method. Type variable is compared to the MLArray types in JMatIO.
	 * Provides verification for Strings. (PlasmaGraph assumes Chars are turned into
	 * strings when read from a MATLAB file.)
	 * 
	 * @return Boolean containing whether the Objects of this class are Strings.
	 */
	public boolean containsStrings ();
	
	/**
	 * Getter method. Provides a string representation of the entire column.
	 * 
	 * @return A String containing the name, type, and values of the entire 
	 * DataColumn.
	 */
	@Override
	public String toString ();
}
