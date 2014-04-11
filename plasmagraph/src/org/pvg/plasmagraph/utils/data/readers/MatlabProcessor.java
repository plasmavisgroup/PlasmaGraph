package org.pvg.plasmagraph.utils.data.readers;

import java.io.*;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.math3.util.Pair;
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
import org.pvg.plasmagraph.utils.exceptions.InvalidDataSizeException;
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
		
		// Now, verify that the data extracted has no nulls. If it does, remove them.
		/*if (this.mat_data.containsKey (null) || this.mat_data.containsValue (null)) {
			
			// Find it and remove it.
			Set <Entry <String, MLArray>> set = this.mat_data.entrySet ();
			
			set.iterator ().
			
			
		}*/
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

		// Is it empty? Are the columns and rows the same size?
		if (hd.isEmpty ()) {
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
				hd.addFile (this.mat_file, FileType.MAT);

				return (true);

			} else {
				throw (new Exception (
						"Incorrect Header sizes! This is a malformed data file!"));
			}
		} else {
			// Check if the headers in the new file are the same as those
			// already in the DataSet.
			boolean b = true;

			// TODO: Make the mat_data collection into something you can pull
			// things out of.

			for (String s : mat_data.keySet ()) {
				b = (hd.contains (s) && b);
			}

			// If they are, then add that new file to the DataSet's list of
			// files to import.
			if (b) {
				hd.addFile (this.mat_file, FileType.MAT);
			} else {
				// TODO: Throw an error!
			}

			return (b);
		}
	}

	/**
	 * Helper method. Provides the type of a MLArray's contents via its key, a
	 * String.
	 * 
	 * @param m The MLArray of data.
	 * @return A ColumnType value of the type of data the MLArray contains.
	 */
	private ColumnType getType (MLArray m) {

		// Prepare the DateValidator
		//org.apache.commons.validator.routines.DateValidator d = new org.apache.commons.validator.routines.DateValidator ();

		// Now, verify the contents of "s" for the type of value it contains.
		/*if (d.validate (col_type) != null) {

			return (ColumnType.DATETIME);

		} else */if (m.isDouble ()) {

			return (ColumnType.DOUBLE);

		} else {

			return (ColumnType.STRING);

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

				if (!((MLCell) mat_array).cells ().get (0).isChar ()) {
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
	 * Transforms the Map data object that JMatIO dumps out into a
	 * proper DataSet for the purposes of PlasmaGraph.
	 * 
	 * @param ds
	 *            A DataSet object with its DataGroups being of the DataRow
	 *            type.
	 * @throws FunctionNotImplementedException
	 * 			   A specific function of this class is currently unavailable.            
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
		ArrayList <MLArray> columns = new ArrayList <> (p.getNumberOfColumns ());
		
		/** iterate over every group of data in the level 5 MAT-File **/
		for (Entry <String, MLArray> e : this.mat_data.entrySet ()) {
			
			/** create and add the data column to the result set **/
			if (e.getKey ().equals (hd.get (p.getXIndex ()).getKey ())
					|| e.getKey ().equals (hd.get (p.getYIndex ()).getKey ())) {
				
				//System.out.println ("Log!");
				columns.add (e.getValue ());
				
				// Create the DataColumns for each column.
				createColumn (ds, e);
			}
		}
		
		// Test the arrays for valid data before insterting them into the DataSet.
		if (columns.size () == 2) {
			
			// In each rows, check the value for all MLArrays, and make sure the values are acceptable..
			for (int row = 0; (row < columns.get (0).getM ()); ++row) {
				
				// Use a flag to verify the validity of the row.
				boolean valid_row = true;
				
				// For each column.
				for (MLArray m : columns) {
					
					// If it's still valid...
					if (valid_row) {
						ColumnType c_type = this.getType (m);
						
						if (c_type.equals (ColumnType.DATETIME)) {
						
							// Dunno! Throw an error!
							throw (new FunctionNotImplementedException ("Date values."));
							
							// Change the MLArray to something that deals with dates.
							// Interpret the value of the row into a date? Can DateValidator do this?
							// if () { // Ignore the entire row. }
							
						} else if (c_type.equals (ColumnType.DOUBLE)) {
							
							valid_row = isValid(((MLDouble) m).get (row));
							
						} else { // (c_type.equals (ColumnType.STRING))
							
							valid_row = isValid(((MLChar) m).getString (row));
							
						}
					}
					
				}
				
				// If the row was verified to be valid...
				if (valid_row) {
					
					for (int column = 0; (column < columns.size ()); ++column) {
						ColumnType c_type = this.getType (columns.get (column));
						
						if (c_type.equals (ColumnType.DATETIME)) {
							
							// Dunno! Throw an error!
							throw (new FunctionNotImplementedException ("Date values."));
							
						} else if (c_type.equals (ColumnType.DOUBLE)) {
							
							ds.get (column).add (((MLDouble) columns.get (column)).get (row));
							
						} else { // (c_type.equals (ColumnType.STRING))
							
							ds.get (column).add (((MLChar) columns.get (column)).getString (row));
							
						}
						
					}
					
				}
				
			}
			
		} else {
			throw (new InvalidDataSizeException ("MatlabProcessor"));
		}

	}


	private void createColumn (DataSet ds, Entry <String, MLArray> e) {

		ColumnType c_type = this.getType (e.getValue ());
		
		if (c_type.isValidType ()) {
			
			ds.add (new DataColumn (e.getKey (), c_type));
			
		}
	}

	/**
	 * Checks if any given double is a valid value.
	 * 
	 * @param d The double to verify.
	 * @return True if the value isn't a NaN, is not Infinite, and is not null; else, False.
	 */
	private boolean isValid (Double d) {
		return (!((Double.isNaN (d)) || 
				(Double.isInfinite (d)) ||
				(d == null) ||
				(d == 0.0) ||
				(d == 0)));
	}

	/**
	 * Checks if any given String is a valid value.
	 * 
	 * @param s The String to verify.
	 * @return True if the value isn't a NaN, is not null, and is not empty (""); else, False.
	 */
	private boolean isValid (String s) {
		return (!((s == null) ||
				(s == "NaN") ||
				(s == "") ||
				(s == "0.0") ||
				(s == "0")));
	}

	/**
	 * Transforms the Map data object that JMatIO dumps out into a
	 * proper DataSet for the purposes of PlasmaGraph.
	 * 
	 * @param ds
	 *            A DataSet object with its DataGroups being of the DataRow
	 *            type.
	 * @throws Exception
	 *             Malformed data set; columns are of different sizes.
	 * @see	DataSet            
	 */
	@Override
	public void toDataSet (DataSet ds, GraphPair p, HeaderData hd, Template t)
			throws Exception {
		// First, check to see if the file's been even read.
		if (this.mat_data.isEmpty ()) {

			this.read ();

		}

		/** iterate over every group of data in the level 5 MAT-File **/
		for (Entry <String, MLArray> e : this.mat_data.entrySet ()) {

			/** create and add the data column to the result set **/
			if (e.getKey ().equals (hd.get (p.getXIndex ()).getKey ())
					|| e.getKey ().equals (hd.get (p.getYIndex ()).getKey ())
					|| e.getKey ().equals (p.getGroupName ())) {
				ds.add (getColumn (e.getValue ()));
			}
		}

	}

	/**
	 * Returns a DataColumn containing all the values in the associated MLArray
	 * provided. <p>
	 * 
	 * @param matlabArray -	An MLArray that was inside the "mat_data" Map.
	 * @return				A DataColumn containing the "matlabArray"'s data and type.
	 * @see 				DataColumn
	 */
	@SuppressWarnings ({ "rawtypes", "unchecked" })
	private DataColumn getColumn (MLArray matlabArray) {

		DataColumn column = new DataColumn (matlabArray.name,
				MLArray.typeToString (matlabArray.getType ()));
		switch (matlabArray.getType ()) {
		case mxUNKNOWN_CLASS:
			break;
		case mxCELL_CLASS:
			MLCell mxCELL_values = (MLCell) matlabArray;
			for (int i = 0; i < mxCELL_values.getM (); i++) {
				column.add (mxCELL_values.get (i, 0));
			}
			break;
		case mxSTRUCT_CLASS:
			break;
		case mxCHAR_CLASS:
			MLChar mxCHAR_values = (MLChar) matlabArray;
			for (int i = 0; i < mxCHAR_values.getM (); i++) {
				column.add (mxCHAR_values.getString (i));
			}
			break;
		case mxSPARSE_CLASS:
			break;
		case mxDOUBLE_CLASS:
			MLDouble mxDOUBLE_values = (MLDouble) matlabArray;
			for (int i = 0; i < mxDOUBLE_values.getM (); i++) {
				column.add (mxDOUBLE_values.get (i, 0));
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

		return column;
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
		StringBuilder str = new StringBuilder ();

		for (MLArray m : this.mat_data.values ()) {

			if (m.getType () == this.mxCELL_CLASS) {
				for (MLArray c : ((MLCell) m).cells ()) {
					// str.append (c.getName () + "\n");
					str.append (c.contentToString () + "\n");
				}
			} else {
				str.append (m.getName () + "\n");
				str.append (m.contentToString () + "\n");
			}
		}

		return str.toString ();
            */
            
            StringBuilder str = new StringBuilder();
            DataSet columns = toDataSet();

            str.append(columns.toString());

            return str.toString(); 
	}
        
    private DataSet toDataSet () {
        
        DataSet ds = new DataSet (false);
        for (MLArray m : this.mat_data.values()) {
            ds.add(this.getColumn(m));
        }
        
        return (ds);
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
