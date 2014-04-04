package org.pvg.plasmagraph.utils;

import org.jfree.data.xy.XYSeries;

/**
 * Random class with methods that help manipulate the JFreeChart classes.
 * 
 * @author Gerardo A. Navas Morales
 */
public class JFreeChartUtilities {

	/**
	 * Testing method.
	 * Prints the contents of an XYSeries via a StringBuilder collecting
	 * the contents of the entire series.
	 * 
	 * @param s The XYSeries to print out.
	 * @return A String with the formatted contents of the entire XYSeries
	 */
	public static String printXYSeries (XYSeries s) {
		StringBuilder sb = new StringBuilder ();
		
		// Get both row and col indexes and start transferring data to the matrix.
		for (int i = 0; (i < s.getItemCount ()); ++i) {
			sb.append ("Pair ").append (i + 1).append (": (").append (s.getX (i)).append (", ").append (s.getY (i)).append (")\n");
		}
		
		return (sb.toString ());
	}
	
}
