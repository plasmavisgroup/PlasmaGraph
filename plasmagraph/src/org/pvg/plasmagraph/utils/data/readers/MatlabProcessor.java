package org.pvg.plasmagraph.utils.data.readers;

import java.io.*;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.math3.util.Pair;
import org.pvg.plasmagraph.utils.ExceptionHandler;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
import org.pvg.plasmagraph.utils.exceptions.InvalidDataSizeException;
import org.pvg.plasmagraph.utils.exceptions.InvalidFileException;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.types.ColumnType;
import org.pvg.plasmagraph.utils.types.FileType;

/**
 * Data processor class. Manages the reading of MAT files and creates DataSets
 * for this project's usage.
 * 
 * @author Daniel Quintini
 */
public class MatlabProcessor implements FileProcessor {

	/** Matlab Array Types */
	/** Matlab Type - Unknown */
	public static final int mxUNKNOWN_CLASS = 0;
	/** Matlab Type - Cell - Collection of MLArrays */
	public static final int mxCELL_CLASS = 1;
	/** Matlab Type - Struct - Matlab Structure */
	public static final int mxSTRUCT_CLASS = 2;
	/** Matlab Type - Generic Matlab Object */
	public static final int mxOBJECT_CLASS = 3;
	/** Matlab Type - Char - Matlab Character Array */
	public static final int mxCHAR_CLASS = 4;
	/** Matlab Type - Sparse */
	public static final int mxSPARSE_CLASS = 5;
	/** Matlab Type - Double - Matlab Floating Point Number */
	public static final int mxDOUBLE_CLASS = 6;
	/** Matlab Type - Single */
	public static final int mxSINGLE_CLASS = 7;
	/** Matlab Type - Integer, 8-bit */
	public static final int mxINT8_CLASS = 8;
	/** Matlab Type - Integer, 8-bit, Unsigned */
	public static final int mxUINT8_CLASS = 9;
	/** Matlab Type - Integer, 16-bit */
	public static final int mxINT16_CLASS = 10;
	/** Matlab Type - Integer, 16-bit, Unsigned */
	public static final int mxUINT16_CLASS = 11;
	/** Matlab Type - Integer, 32-bit */
	public static final int mxINT32_CLASS = 12;
	/** Matlab Type - Integer, 32-bit, Unsigned */
	public static final int mxUINT32_CLASS = 13;
	/** Matlab Type - Integer, 64-bit */
	public static final int mxINT64_CLASS = 14;
	/** Matlab Type - Integer, 64-bit, Unsigned */
	public static final int mxUINT64_CLASS = 15;
	/** Matlab Type - Function - Matlab Function Object */
	public static final int mxFUNCTION_CLASS = 16;
	/** Matlab Type - Opaque */
	public static final int mxOPAQUE_CLASS = 17;

	/** Container for file location. */
	File mat_file;
	/** Container for MATlab data, as provided by JMatIO. */
	Map <String, MLArray> mat_data;

	/**
	 * Constructor. Creates a new MatlabProcessor with a default File location
	 * as specified by the method call.
	 * 
	 * @param f
	 *            A File object, containing the default file location for the
	 *            process.
	 */
	public MatlabProcessor (File f) {
		mat_file = f;
		this.read ();
	}

	/**
	 * Constructor. Creates a new MatlabProcessor with a default File location
	 * as specified by the method call.
	 * 
	 * @param s
	 *            A String object, containing the default file location for the
	 *            process.
	 */
	public MatlabProcessor (String s) {
		mat_file = new File (s);
		this.read ();
	}

	/**
	 * Opens the Matlab file provided in the constructor and pulls out the data
	 * into the Map variable in this class.
	 */
	@Override
	public void read () {

		// Open the Matlab file "mat_file".
		MatFileReader mat = new MatFileReader ();

		try {

			// Reads the data into "mat_data" and then closes the stream.
			this.mat_data = mat.read (this.mat_file);

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace ();

		}

	}
	
	private void removeInvalidColumns () {
		// Now, verify that the data extracted has no nulls. If it does, remove them.
		// Find it and remove it.
		Set <Map.Entry <String, MLArray>> s = this.mat_data.entrySet ();
		ArrayList <Map.Entry <String, MLArray>> remove_array = new ArrayList <> (
				this.mat_data.size ());
		
		for (Map.Entry <String, MLArray> e : s) {
			if (!this.containsData (e)) {
				// System.out.println ("Nothing in " + e.getKey () +
				// "! Removing it.");
				remove_array.add (e);
			} else {
				// System.out.println ("Found at least two points in " +
				// e.getKey () + "! Keeping it!");
			}
		}

		// If there's anything to remove, tell the user that it has mothing and
		// remove it!
		if (!remove_array.isEmpty ()) {

			for (Map.Entry <String, MLArray> e : remove_array) {
				this.mat_data.remove (e.getKey ());
			}

			ExceptionHandler.showRemovedColumnDialog (remove_array);
		}
	}

	// TODO: Does this even work?
	private boolean containsData (Map.Entry <String, MLArray> e) {
		int amount_of_data = 0;

		if (ColumnType.DOUBLE.equals (this.getType (e.getValue ()))) {

			// Figure out if it has even any useful amount of data
			// (read: 2+ usable data points)
			MLDouble double_array = (MLDouble) e.getValue ();
			for (int i = 0; (i < double_array.getM ()) && (amount_of_data <= 2); i++) {

				// System.out.println ("Value: " + double_array.get (i));

				if (this.isValid (double_array.get (i))) {
					amount_of_data += 1;
				}
			}

		} else if (ColumnType.STRING.equals (this.getType (e.getValue ()))) {

			// Figure out if it has even any useful amount of data
			// (read: 2+ usable data points)
			MLCell mxCELL_values = (MLCell) e.getValue ();
			for (int i = 0; (i < mxCELL_values.getM ())
					&& (amount_of_data <= 2); ++i) {

				// System.out.println ("Value: " + ((MLChar) mxCELL_values.get
				// (i, 0)).getString (0));
				// System.out.println ("Is valid? " + this.isValid (((MLChar)
				// mxCELL_values.get (i, 0)).getString (0)));

				if (this.isValid ( ((MLChar) mxCELL_values.get (i, 0))
						.getString (0))) {
					amount_of_data += 1;
				}
			} // TODO: Does this even work?

		} else {

			// It's not a valid column. Give up.
			amount_of_data = 0;
		}

		return (amount_of_data >= 2);
	}

	/**
	 * Getter Method; creates a set of columns with the correct names based on
	 * the data in the MAT file. Does not fill them in, however.
	 * 
	 * @param hd
	 *            HeaderData to fill out with Columns, but not with data.
	 * @return A boolean describing the success or failure of this operation.
	 */
	@Override
	public boolean getHeaders (HeaderData hd) throws Exception {
		// TODO Auto-generated method stub
		if (this.mat_data.isEmpty ()) {
			this.read ();
		}
		
		// First, check and remove any invalid columns.
		this.removeInvalidColumns ();

		// Is it empty? Are the columns and rows the same size?
		if (this.mat_data.size () >= 2) {
			// Clear out what's in the old HeaderData before putting in new
			// data.
			hd.reset ();
			if (this.checkColumnSizes ()) {
				// For each non-cell object...

				for (String s : this.mat_data.keySet ()) {
					// Get the type...
					ColumnType col_type = this.getType (this.mat_data.get (s));

					// And put it in.
					hd.add (new Pair <> (s.trim (), col_type));
				}

				// Then add that new file to the DataSet's list of files to
				// import.
				hd.setFile (this.mat_file, FileType.MAT);

				return (true);

			} else {
				throw (new Exception (
						"Incorrect Header sizes! This is a malformed data file!"));
			}
		} else {
			throw (new InvalidFileException ());
		}
	}

	/**
	 * Helper method. Provides the type of a MLArray's contents via its key, a
	 * String.
	 * 
	 * @param m
	 *            The MLArray of data.
	 * @return A ColumnType value of the type of data the MLArray contains.
	 */
	private ColumnType getType (MLArray m) {

		if (m.isDouble ()) {

			return (ColumnType.DOUBLE);

		} else if (m.isCell () || m.isChar ()) {

			return (ColumnType.STRING);

		} else {
			
			return (ColumnType.NONE);
			
		}
	}

	/**
	 * Checks the column sizes of the mat_data to see if they are all of equal
	 * size.
	 * 
	 * @return A boolean describing the success or failure of this operation.
	 */
	private boolean checkColumnSizes () {
		int n = -1;
		int m = -1;

		for (MLArray mat_array : this.mat_data.values ()) {
			// Determine if the object is an MLCell. If it is, recurse further
			// before continuing!
			if (mat_array instanceof MLCell) {

				if (! ((MLCell) mat_array).cells ().get (0).isChar ()) {
					return (false);
				}

			} else {
				// Check if they haven't been first set yet.
				if (n == -1) {

					n = mat_array.getN ();

				} else if (m == -1) {

					m = mat_array.getM ();

				}

				// Otherwise, check if the m and n are equal to its values.

				else {
					if (! ( (n == mat_array.getN ()) && (m == mat_array.getM ()))) {
						return (false);
					}
				}
			}

		}

		return (true);
	}

	/**
	 * Transforms the Map data object that JMatIO dumps out into a proper
	 * DataSet for the purposes of PlasmaGraph.
	 * 
	 * @param ds
	 *            A DataSet object with its DataGroups being of the DataRow
	 *            type.
	 * @throws FunctionNotImplementedException
	 *             A specific function of this class is currently unavailable.
	 * @throws InvalidDataSizeException
	 */
	@Override
	public void toDataSet (DataSet ds, GraphPair p, HeaderData hd)
			throws FunctionNotImplementedException, InvalidDataSizeException {

		// First, check to see if the file's been even read.
		if (this.mat_data.isEmpty ()) {

			this.read ();

		}

		// Create a container of MLArrays that will be graphed.
		//ArrayList <MLArray> columns = new ArrayList <> (p.getNumberOfColumns ());
		HashMap <String, MLArray> columns = new HashMap <> (p.getNumberOfColumns ());
		ArrayList <String> header_list = new ArrayList <> ();
		
		for (Entry <String, MLArray> e : this.mat_data.entrySet ()) {

			if (e.getKey ().equals ("Header") || e.getKey ().equals ("header")) {
				System.out.println ("Found the header!");
				// TODO: Header code.
				for (int i = 0; (i < e.getValue ().getM ()); ++i) {
					header_list.add (((MLChar) (((MLCell) e.getValue ()).
							cells ().get (i))).getString (0));
				}
			}
		}
		
		/** iterate over every group of data in the level 5 MAT-File **/
		for (Entry <String, MLArray> e : this.mat_data.entrySet ()) {
			
			/** create and add the data column to the result set **/
			if (!p.isGrouped ()) {

				if (e.getKey ().equals (hd.get (p.getXColumnIndex ()).getKey ())) {
					//System.out.println ("Got the X Column.");
					
					columns.put ("X", e.getValue ());
					
				} else if (e.getKey ().equals (hd.get (p.getYColumnIndex ()).getKey ())) {
					//System.out.println ("Got the Y Column.");
					
					columns.put ("Y", e.getValue ());
					
				} else {
					//System.out.println ("Not found."); // TODO
				}

			} else {

				if (e.getKey ().equals (hd.get (p.getXColumnIndex ()).getKey ())) {
					
					columns.put ("X", e.getValue ());
					
				} else if (e.getKey ().equals (hd.get (p.getYColumnIndex ()).getKey ())) {
					
					columns.put ("Y", e.getValue ());
					
				} else if (e.getKey ().equals (hd.get (p.getGroup ()).getKey ())) {
					
					columns.put ("G", e.getValue ());
					ds.setGroupType (this.getType (e.getValue ()));
					
				} else {
					//System.out.println ("Not found."); // TODO
				}

			}
		}

		// Now, verify the integrity of the data before saying it's all right!
		try {

			this.verifyDataIntegrity (columns, ds);

		} catch (FunctionNotImplementedException ex) {

			ex.showMessage ();
			ds = new DataSet (p);

		} catch (InvalidDataSizeException ex) {

			ex.showMessage ();
			ds = new DataSet (p);

		}

	}
	
	private void verifyDataIntegrity (HashMap<String, MLArray> columns, DataSet ds)
			throws FunctionNotImplementedException, InvalidDataSizeException {
		// Test the arrays for valid data before insterting them into the
		// DataSet.
		if (columns.size () == 2 || columns.size () == 3) {

			// In each rows, check the value for all MLArrays, and make sure the
			// values are acceptable..
			for (int row = 0; (row < columns.get ("X").getM ()); ++row) {
				
				// Use a flag to verify the validity of the row.
				boolean valid_row = true;

				// For each column.
				for (Entry<String, MLArray> m : columns.entrySet ()) {

					// If it's still valid...
					if (valid_row) {
						ColumnType c_type = this.getType (m.getValue ());

						if (ColumnType.DOUBLE.equals (c_type)) {

							valid_row = isValid (((MLDouble) m.getValue ()).get (row));

						} else { // (ColumnType.STRING.equals (c_type))

							if (m.getValue ().isChar ()) {
								
								valid_row = isValid (((MLChar) m.getValue ()).getString (row));
								
							} else if (m.getValue ().isCell ()) {
								
								valid_row = isValid (((MLChar)
										((MLCell) m.getValue ()).cells ().get (row)).
										getString (0));
								
							}

						}
					}

				}

				// If the row was verified to be valid...
				if (valid_row) {
					
					Pair <Double, Double> xy = new Pair <> (((MLDouble) columns.get ("X")).get (row),
							((MLDouble) columns.get ("Y")).get (row));
					
					ds.addToX (xy.getFirst ());
					ds.addToY (xy.getSecond ());

					if (ds.isGrouped () && columns.size () == 3) {
						if (columns.get ("G").isDouble ()) {
							//System.out.println ("This group is a double!");
							
							ds.addToGroup (((MLDouble) columns.get ("G"))
									.get (row));
							
						} /*else if (columns.get ("G").isInt8 ()) { 
							
							ds.addToGroup (((MLInt8) columns.get ("G"))
									.get (row));
							
						} else if (columns.get ("G").isInt16 ()) { 
							
							ds.addToGroup (((MLInt16) columns.get ("G"))
									.get (row));
							
						} else if (columns.get ("G").isInt32 ()) { 
							
							ds.addToGroup (((MLInt32) columns.get ("G"))
									.get (row));
							
						} else if (columns.get ("G").isInt64 ()) { 
							
							ds.addToGroup (((MLInt64) columns.get ("G"))
									.get (row));
							
						} */else { //if (columns.get (2).isCell () || columns.get (2).isChar ()) {
							
							if (columns.get ("G").isCell ()) {
								//System.out.println ("This group is a cell of chars!");
								
									ds.addToGroup (((MLChar) 
											((MLCell) columns.get ("G")).cells ().get (row)).
											getString (0));

							} else if (columns.get ("G").isChar ()) {
								//System.out.println ("This group is a char!");
								
								ds.addToGroup (((MLChar) columns.get ("G")).getString (row));

							} else {
								// TODO: error!
								System.out.println ("This group is an error!");
							}
						}
					}

				}

			}

		} else {
			throw (new InvalidDataSizeException ("MatlabProcessor"));
		}
	}/*

	private void createColumn (DataSet ds, HeaderData hd, GraphPair p,
			Entry <String, MLArray> e) {
		*//** create and add the data column to the result set **//*
		if (p.isGrouped ()) {
			if (e.getKey ().equals (hd.get (p.getXColumnIndex ()).getKey ())) {

				MLDouble mxDOUBLE_values = (MLDouble) e.getValue ();
				for (int i = 0; i < mxDOUBLE_values.getM (); ++i) {
					ds.addToX (mxDOUBLE_values.get (i, 0));
				}

			} else if (e.getKey ().equals (
					hd.get (p.getYColumnIndex ()).getKey ())) {

				MLDouble mxDOUBLE_values = (MLDouble) e.getValue ();
				for (int i = 0; i < mxDOUBLE_values.getM (); ++i) {
					ds.addToY (mxDOUBLE_values.get (i, 0));
				}

			} else if (e.getKey ().equals (p.getGroupName ())) {

				if (ColumnType.DOUBLE.equals (hd.get (p.getGroup ())
						.getValue ())) {

					MLDouble mxDOUBLE_values = (MLDouble) e.getValue ();
					for (int i = 0; i < mxDOUBLE_values.getM (); ++i) {
						ds.addToGroup (mxDOUBLE_values.get (i, 0));
					}

				} else if (ColumnType.DOUBLE.equals (hd.get (p.getGroup ())
						.getValue ())) {

					if (e.getValue ().isCell ()) {

						MLCell mxCELL_values = (MLCell) e.getValue ();
						for (int i = 0; i < mxCELL_values.getM (); ++i) {
							ds.addToGroup ( ((MLChar) mxCELL_values.get (i, 0))
									.getString (0));
						}

					} else if (e.getValue ().isChar ()) {

						MLChar mxCHAR_values = (MLChar) e.getValue ();
						for (int i = 0; i < mxCHAR_values.getM (); ++i) {
							ds.addToGroup (mxCHAR_values.getString (i));
						}

					}

				}

			}
		} else {
			if (e.getKey ().equals (hd.get (p.getXColumnIndex ()).getKey ())) {

				MLDouble mxDOUBLE_values = (MLDouble) e.getValue ();
				for (int i = 0; i < mxDOUBLE_values.getM (); ++i) {
					ds.addToX (mxDOUBLE_values.get (i, 0));
				}

			} else if (e.getKey ().equals (
					hd.get (p.getYColumnIndex ()).getKey ())) {

				MLDouble mxDOUBLE_values = (MLDouble) e.getValue ();
				for (int i = 0; i < mxDOUBLE_values.getM (); ++i) {
					ds.addToY (mxDOUBLE_values.get (i, 0));
				}

			}
		}
	}*/

	/**
	 * Checks if any given double is a valid value.
	 * 
	 * @param d
	 *            The double to verify.
	 * @return True if the value isn't a NaN, is not Infinite, and is not null;
	 *         else, False.
	 */
	private boolean isValid (Double d) {
		return (! ( (d.isNaN ()) || (d.isInfinite ()) || (d == null)));
	}

	/**
	 * Checks if any given String is a valid value.
	 * 
	 * @param s
	 *            The String to verify.
	 * @return True if the value isn't a NaN, is not null, and is not empty
	 *         (""); else, False.
	 */
	private boolean isValid (String s) {
		String t = s.trim ();
		return (! ( (t.equals (null))
				|| (t.equals (Double.toString (Double.NaN)))
				|| (t.equals (Double.toString (Double.POSITIVE_INFINITY)))
				|| (t.equals (Double.toString (Double.NEGATIVE_INFINITY)))
				|| (t.equals ("")) || (t.equals ("0.0")) || (t.equals ("0"))));
	}

	/**
	 * Getter method. Provides the file object currently in use.
	 * 
	 * @return The file object being used by this object.
	 */
	@Override
	public File getFile () {
		return (this.mat_file);
	}

	/**
	 * Setter method. Allows for changing the file object that this object is
	 * using.
	 * 
	 * @param f
	 *            The new file object to take the place of the old one.
	 */
	@Override
	public void setFile (File f) {
		this.mat_file = f;
	}

	/**
	 * Creates a string that contains the key names of each of the MLArrays
	 * 
	 * @return A string representation of the MAT file.
	 */
	public String toString () {

		/*
		 * StringBuilder str = new StringBuilder ();
		 * 
		 * for (MLArray m : this.mat_data.values ()) {
		 * 
		 * if (m.getType () == this.mxCELL_CLASS) { for (MLArray c : ((MLCell)
		 * m).cells ()) { // str.append (c.getName () + "\n"); str.append
		 * (c.contentToString () + "\n"); } } else { str.append (m.getName () +
		 * "\n"); str.append (m.contentToString () + "\n"); } }
		 * 
		 * return str.toString ();
		 */

		StringBuilder sb = new StringBuilder ();

		for (Entry <String, MLArray> m : this.mat_data.entrySet ()) {
			sb.append ("-- Column --");
			sb.append (System.getProperty ("line.separator"));
			sb.append ("Name: ");
			sb.append (m.getKey ());
			sb.append (System.getProperty ("line.separator"));

			sb.append ("Type: ");
			sb.append (MLArray.typeToString (m.getValue ().getType ()));
			sb.append (System.getProperty ("line.separator"));

			sb.append ("Size: ");
			sb.append (m.getValue ().getSize ());
			sb.append (System.getProperty ("line.separator"));

			sb.append ("Values: [");

			switch (m.getValue ().getType ()) {
				case mxUNKNOWN_CLASS:
					break;
				case mxCELL_CLASS:
					MLCell mxCELL_values = (MLCell) m.getValue ();
					for (int i = 0; i < mxCELL_values.getM (); i++) {
						sb.append (((MLChar) mxCELL_values.get (i, 0)).getString (0));
						sb.append (", ");
					}
					break;
				case mxSTRUCT_CLASS:
					break;
				case mxCHAR_CLASS:
					MLChar mxCHAR_values = (MLChar) m.getValue ();
					for (int i = 0; i < mxCHAR_values.getM (); i++) {
						sb.append (mxCHAR_values.getString (i));
						sb.append (", ");
					}
					break;
				case mxSPARSE_CLASS:
					break;
				case mxDOUBLE_CLASS:
					MLDouble mxDOUBLE_values = (MLDouble) m.getValue ();
					for (int i = 0; i < mxDOUBLE_values.getM (); i++) {
						sb.append (mxDOUBLE_values.get (i, 0));
						sb.append (", ");
					}
					break;
				case mxSINGLE_CLASS:
					break;
				case mxINT8_CLASS:
					break;
				case mxUINT8_CLASS:
					break;
				case mxINT16_CLASS:
					break;
				case mxUINT16_CLASS:
					break;
				case mxINT32_CLASS:
					break;
				case mxUINT32_CLASS:
					break;
				case mxINT64_CLASS:
					break;
				case mxUINT64_CLASS:
					break;
				case mxFUNCTION_CLASS:
					break;
				case mxOPAQUE_CLASS:
					break;
				case mxOBJECT_CLASS:
					break;
				default:
					break;
			}

			sb.append ("]");
			sb.append (System.getProperty ("line.separator"));
		}

		return sb.toString ();
	}

	/**
	 * Getter method. Provides the Map object obtained from the file.
	 * 
	 * @return The Map object that this object contains.
	 */
	public Map <String, MLArray> getData () {
		return (this.mat_data);
	}
}