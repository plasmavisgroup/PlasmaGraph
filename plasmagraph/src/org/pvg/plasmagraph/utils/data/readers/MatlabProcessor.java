package org.pvg.plasmagraph.utils.data.readers;

import java.io.*;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.commons.math3.util.Pair;
import org.pvg.plasmagraph.utils.ExceptionHandler;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
import org.pvg.plasmagraph.utils.exceptions.InvalidDataSizeException;
import org.pvg.plasmagraph.utils.exceptions.InvalidFileException;
import org.pvg.plasmagraph.utils.types.ColumnType;
import org.pvg.plasmagraph.utils.types.FileType;

/**
 * <p>Data processor class. Manages the reading of MAT files and creates
 * HeaderData and DataSet objects for graphing operations.
 * 
 * <p>This class follows the "FileProcessor" interface, and includes additional methods
 * designed to verify the integrity of column sizes, validity of columns and individual
 * fields being used, and removal of invalid columns.
 * 
 * <p>Refer to MathWork's <a href = "http://www.mathworks.com/help/pdf_doc/matlab/matfile_format.pdf">matfile_format.pdf</a>}
 * for information regarding the Matlab file formats; specifically, focus on the Level 5 format,
 * as it is the format this class is designed to read from. Due to the nuances of Matlab Level-5
 * columns, this system uses the external library JMatIO to process .mat files into a HashMap
 * format, containing the Matlab array variable's name and contents as its keys and values,
 * respectively.
 * 
 * <p>As an aside, this entire class assumes that any Strings provided to the system are
 * contained in Matlab Cells of Characters,(MLCell and MLChar, respectively) and that the data
 * is provided in the first column of any given Matlab Array. (MLArray)
 * 
 * @author Plasma Visualization Group and Gerardo A. Navas Morales
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
	 * <p>Constructor. Creates a new MatlabProcessor with a default File location
	 * as specified by the method call.
	 * 
	 * @param f A File object, containing the default file location for the
	 *            process.
	 */
	public MatlabProcessor (File f) {
		mat_file = f;
		this.read ();
	}

	/**
	 * <p>Constructor. Creates a new MatlabProcessor with a default File location
	 * as specified by the method call.
	 * 
	 * @param s A String object, containing the default file location for the
	 *            process.
	 */
	public MatlabProcessor (String s) {
		mat_file = new File (s);
		this.read ();
	}

	/**
	 * <p>Opens the file the Class is provided initially, 
	 * and places said data into a data container for use in any other methods of this class.
	 * 
	 * <p>This method automatically handles any IOExceptions that may occur while reading the file.
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

	/**
	 * <p>Helper method for "getHeaders". Removes all invalid columns from the data already read.
	 * 
	 * <p>Invalid columns are those which do not have enough data in them to graph.
	 */
	private void removeInvalidColumns () {
		// Now, verify that the data extracted has no nulls. If it does, remove them.
		// Find it and remove it.
		Set <Map.Entry <String, MLArray>> s = this.mat_data.entrySet ();
		ArrayList <Map.Entry <String, MLArray>> remove_array = new ArrayList <> (
				this.mat_data.size ());

		// Remove only nulls in columns that aren't the Header column.
		for (Map.Entry <String, MLArray> e : s) {
			if ((!this.isHeaderColumn (e.getKey ())) && (!this.containsData (e))) {

					remove_array.add (e);
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

	/**
	 * <p>Helper method. Checks a Matlab variable for the existence of enough data to be
	 * graphable. For the purposes of this class, this refers to having at least 3 or more
	 * values.
	 * 
	 * @param e The Matlab variable to evaluate.
	 * @return A boolean describing the validity of said Matlab variable.
	 */
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

				if (this.isValid ( ((MLChar) mxCELL_values.get (i, 0))
						.getString (0))) {
					amount_of_data += 1;
				}
			}

		} else {

			// It's not a valid column. Give up.
			amount_of_data = 0;
		}

		return (amount_of_data >= 2);
	}

	/**
	 * <p>Takes the data provided by the "read ()" method and converts it to 
	 * a valid HeaderData object located in the "hd" parameter provided.
	 * This occurs only if the resulting data is formatted correctly 
	 * (Columns of equal lengths), has enough data to create graphs from it 
	 * (Needs two or more columns / fields), and if the columns that exist
	 * have at least some valid columns (Columns with at least three points of data).
	 * 
	 * <p>Please note the following: 
	 * <ul>This method checks for the existence of a column named "Header" or "header" 
	 * before populating the HeaderData object. This is to know if there are special names 
	 * that the user wishes to use for the columns automatically. If such a column exists, 
	 * the system will take the column and populate the rest of the columns with said name. 
	 * Said "Header" column will be removed from the data container during this process.</ul>
	 * <ul>This method also checks if the file has been read before processing, 
	 * and calls this class' "read ()" method beforehand if needed.</ul>
	 * 
	 * @param hd HeaderData object location where the created HeaderData will be placed.
	 * @return A boolean describing the success or failure of this operation. Failure only 
	 * results when the data file is invalid due to irregular column sizes or lack of 
	 * graphable columns.
	 * @throws Exception If the data isn't formatted correctly or if said operation isn't
	 * available for the class yet.
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

			// Clear out what's in the old HeaderData before checking if the
			// columns are well-formed.
			hd.reset ();
			if (this.checkColumnSizes ()) {

				// Scan for a header MLArray, first. (Named "header" or
				// "Header".)
				if (this.containsHeader ()) {
					for (Entry <String, MLArray> e : this.mat_data.entrySet ()) {
						
						if (this.isHeaderColumn (e.getKey ())) {
							
							//System.out.println ("This is a header column!");
							
							// Create a list of the column names contained in the header array!
							// We're going to assume that all headers are going to be MLCells of MLChars.
							// I mean, how else are you going to handle strings?
							List <String> header = new ArrayList <String> (this.mat_data.size () - 1);
							for (MLArray m : ((MLCell) e.getValue ()).cells ()) {
								header.add (((MLChar) m).getString (0));
								//System.out.println ("Parameter Name: " + ((MLChar) m).getString (0));
							}
							
							
							// Remove the Header MLArray before this next part.
							this.mat_data.remove (e.getValue ());
							
							// Fill up the contents of the HeaderData object!
							// WARNING: THIS ASSUMES BOTH THE MATLAB DATA AND HEADER LIST ARE IN THE SAME ORDER.
							int i = 0;
							for (String variable_name : this.mat_data.keySet ()) {
								
								//System.out.println ("Parameter Name: " + header.get (i));
								
								if (!this.isHeaderColumn (variable_name)) {
									// Put in the correct header name and type.
									hd.add (new Pair <> (header.get (i).trim (), 
											this.getType (this.mat_data.get (variable_name))));
								}
								++i;
							}
							
						}
					}
				}
				
				// If there is no column with said name, take the first column
				// and check if it could be it.
				else if (this.mat_data.values ().iterator ().next ().isCell ()) {
					
					// Save the column as the name column!
					List <String> header = new ArrayList <String> (this.mat_data.size () - 1);
					for (MLArray m : ((MLCell) this.mat_data.values ().iterator ().next ()).cells ()) {
						header.add (((MLChar) m).getString (0));
					}
					
					// Remove the first MLArray before this next part.
					this.mat_data.remove (this.mat_data.values ().iterator ().next ());
					
					// Fill up the contents of the HeaderData object!
					int i = 0;
					for (String variable_name : this.mat_data.keySet ()) {
						// Get the type...
						ColumnType col_type = this.getType (this.mat_data.get (variable_name));

						// And put it in.
						hd.add (new Pair <> (header.get (i).trim (), col_type));
						i += 1;
					}
					
				}
				// Otherwise, take the file's variable names as the column names as normal.
				else {
					for (String variable_name : this.mat_data.keySet ()) {
						// Get the type...
						ColumnType col_type = this.getType (this.mat_data.get (variable_name));

						// And put it in.
						hd.add (new Pair <> (variable_name.trim (), col_type));
					}
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

	private boolean containsHeader () {
		return (this.mat_data.containsKey ("Header") || this.mat_data.containsKey ("header")
				|| this.mat_data.containsKey ("Headers") || this.mat_data.containsKey ("headers"));
	}

	/**
	 * <p>Helper method. Provides the type of a MLArray's contents via its key, a
	 * String.
	 * 
	 * @param m The MLArray of data.
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
	 * <p>Checks the column sizes of the mat_data to see if they are all of equal
	 * size.
	 * 
	 * @return A boolean describing the success or failure of this operation.
	 */
	private boolean checkColumnSizes () {
		int n = -1;
		int m = -1;

		//for (MLArray mat_array : this.mat_data.values ()) {
		for (Map.Entry <String, MLArray> e : this.mat_data.entrySet ()) {
			
			// First, make sure the MLArray isn't the header column!
			if (!this.isHeaderColumn (e.getKey ())) {
				
				// If it's not, then check the contents!
				MLArray mat_array = e.getValue ();
				
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

		}

		return (true);
	}

	/**
	 * <p>Takes the data provided by the "read ()" method, as well as valid HeaderData
	 * and GraphPair objects and converts it into a valid DataSet object. Said object contains
	 * the data contained in the columns provided by the GraphPair object, and uses the HeaderData's
	 * ColumnTypes and column names to verify the validity of said columns and the data they
	 * contains.
	 * 
	 * <p>Please note the following:
	 * <ul>This method works with both un-grouped and grouped graphs.</ul>
	 * <ul>This method also checks if the file has been read before processing, 
	 * and calls this class' "read ()" method beforehand if needed.</ul>
	 * 
	 * 
	 * @param ds DataSet object location where the created DataSet will be placed.
	 * @param p GraphPair object describing the columns that will be featured in the graph.
	 * @param hd HeaderData object describing the file's columns' names and types.
	 * @throws Exception If the data isn't formatted correctly or if said operation isn't
	 * available for the class yet.
	 */
	@Override
	public void toDataSet (DataSet ds, GraphPair p, HeaderData hd)
			throws FunctionNotImplementedException, InvalidDataSizeException {

		// First, check to see if the file's been even read.
		if (this.mat_data.isEmpty ()) {

			this.read ();

		}

		// Create a container of MLArrays that will be graphed.
		// ArrayList <MLArray> columns = new ArrayList <> (p.getNumberOfColumns
		// ());
		HashMap <String, MLArray> columns = new HashMap <> (
				p.getNumberOfColumns ());
		
		
		// Before trying to populate the columns Map, check if there's a "header" column to use for
		// 	column name / variable translations.
		Map <String, String> header_dictionary = new HashMap <> (this.mat_data.size () - 1);
		for (Entry <String, MLArray> e : this.mat_data.entrySet ()) {

			if (e.getKey ().equals ("Header") || e.getKey ().equals ("header")) {
				
				//System.out.println ("Found the header!");
				for (int i = 0; (i < e.getValue ().getM ()); ++i) {
					
					// The "header_name" is obtained from the MLCell in the same way any other Matlab String is obtained in this formatting of data.
					String header_name = ((MLChar) ( ((MLCell) e.getValue ())
							.cells ().get (i))).getString (0);
					
					// The "variable_name" is obtained from the Map's MLArray's name variable.
					String variable_name = ((ArrayList <MLArray>) this.mat_data.values ()).get (i + 1).getName ();
					
					// Finally, put that combination into the dictionary.
					header_dictionary.put (header_name, variable_name);
				}
				
				// After all that, make sure to remove this entry from the "mat_data" object!
				this.mat_data.remove (e.getKey ());
			}
		}

		// Verify if said "header" column was ever found.
		if (header_dictionary.isEmpty ()) {
			
			// It wasn't. Follow standard procedure.
			/** iterate over every group of data in the level 5 MAT-File **/
			for (Entry <String, MLArray> e : this.mat_data.entrySet ()) {
				
				// Store the variable name; it'll make it a bit more readable.
				String variable_name = e.getKey ();

				/** create and add the data column to the result set **/
				if (!p.isGrouped ()) {

					// Note: "header_dictionary"'s "get" method will translate a "header"-sourced name into the variable name.
					if (variable_name.equals (hd.get (p.getXColumnIndex ()).getKey ())) {
						// System.out.println ("Got the X Column.");

						columns.put ("X", e.getValue ());

					} else if (variable_name.equals (hd.get (p.getYColumnIndex ()).getKey ())) {
						// System.out.println ("Got the Y Column.");

						columns.put ("Y", e.getValue ());

					} else {
						// System.out.println ("Not found."); // TODO
					}

				} else {

					if (variable_name.equals (hd.get (p.getXColumnIndex ()).getKey ())) {

						columns.put ("X", e.getValue ());

					} else if (variable_name.equals (hd.get (p.getYColumnIndex ()).getKey ())) {

						columns.put ("Y", e.getValue ());

					} else if (variable_name.equals (hd.get (p.getGroup ()).getKey ())) {

						columns.put ("G", e.getValue ());
						ds.setGroupType (this.getType (e.getValue ()));

					} else {
						// System.out.println ("Not found."); // TODO
					}

				}
			}
			
		} else {
			
			// It was! Follow modified procedure!
			/** iterate over every group of data in the level 5 MAT-File **/
			for (Entry <String, MLArray> e : this.mat_data.entrySet ()) {
				
				// Save the old name!
				String variable_name = header_dictionary.get (e.getKey ());

				/** create and add the data column to the result set **/
				if (!p.isGrouped ()) {

					if (variable_name.equals (hd.get (p.getXColumnIndex ()).getKey ())) {
						// System.out.println ("Got the X Column.");

						columns.put ("X", e.getValue ());

					} else if (variable_name.equals (hd.get (p.getYColumnIndex ()).getKey ())) {
						// System.out.println ("Got the Y Column.");

						columns.put ("Y", e.getValue ());

					} else {
						// System.out.println ("Not found."); // TODO
					}

				} else {

					if (variable_name.equals (hd.get (p.getXColumnIndex ()).getKey ())) {

						columns.put ("X", e.getValue ());

					} else if (variable_name.equals (hd.get (p.getYColumnIndex ()).getKey ())) {

						columns.put ("Y", e.getValue ());

					} else if (variable_name.equals (hd.get (p.getGroup ()).getKey ())) {

						columns.put ("G", e.getValue ());
						ds.setGroupType (this.getType (e.getValue ()));

					} else {
						// System.out.println ("Not found."); // TODO
					}

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

	/**
	 * <p>Helper method for the "toDataSet" method. Checks if every row of
	 * the columns requested by the GraphPair (which are already provided
	 * to this method via a unique HashMap parameter) is a valid value for
	 * the column's type. A row is valid if all of its values, including the
	 * Group column if included in the graph, are valid as per the "isValid"
	 * methods; said values are included into the DataSet. Thus, THIS is the
	 * method that puts values into the DataSet.
	 * 
	 * @param columns The HashMap of Matlab variables to be graphed.
	 * @param ds The DataSet to place said Matlab variable data in.
	 * @throws FunctionNotImplementedException Occurs when an invalid Column type
	 * is provided to this system.
	 * @throws InvalidDataSizeException Occurs when the columns requested are of
	 * the incorrect format.
	 */
	private void verifyDataIntegrity (HashMap <String, MLArray> columns,
			DataSet ds) throws FunctionNotImplementedException,
			InvalidDataSizeException {
		int number_of_invalid_rows = 0;
		// Test the arrays for valid data before inserting them into the
		// DataSet.
		if (columns.size () == 2 || columns.size () == 3) {

			// In each rows, check the value for all MLArrays, and make sure the
			// values are acceptable..
			for (int row = 0; (row < columns.get ("X").getM ()); ++row) {

				// Use a flag to verify the validity of the row.
				boolean valid_row = true;

				// For each column.
				for (Entry <String, MLArray> m : columns.entrySet ()) {

					// If it's still valid...
					if (valid_row) {
						ColumnType c_type = this.getType (m.getValue ());

						if (ColumnType.DOUBLE.equals (c_type)) {

							valid_row = isValid ( ((MLDouble) m.getValue ())
									.get (row));

						} else { // (ColumnType.STRING.equals (c_type))

							if (m.getValue ().isChar ()) {

								valid_row = isValid ( ((MLChar) m.getValue ())
										.getString (row));

							} else if (m.getValue ().isCell ()) {

								valid_row = isValid ( ((MLChar) ((MLCell) m
										.getValue ()).cells ().get (row))
										.getString (0));

							}

						}
					}

				}

				// If the row was verified to be valid...
				if (valid_row) {

					Pair <Double, Double> xy = new Pair <> (
							((MLDouble) columns.get ("X")).get (row),
							((MLDouble) columns.get ("Y")).get (row));

					ds.addToX (xy.getFirst ());
					ds.addToY (xy.getSecond ());

					if (ds.isGrouped () && columns.size () == 3) {
						if (columns.get ("G").isDouble ()) {
							// System.out.println ("This group is a double!");

							ds.addToGroup ( ((MLDouble) columns.get ("G"))
									.get (row));

						} /*
						 * else if (columns.get ("G").isInt8 ()) {
						 * 
						 * ds.addToGroup (((MLInt8) columns.get ("G")) .get
						 * (row));
						 * 
						 * } else if (columns.get ("G").isInt16 ()) {
						 * 
						 * ds.addToGroup (((MLInt16) columns.get ("G")) .get
						 * (row));
						 * 
						 * } else if (columns.get ("G").isInt32 ()) {
						 * 
						 * ds.addToGroup (((MLInt32) columns.get ("G")) .get
						 * (row));
						 * 
						 * } else if (columns.get ("G").isInt64 ()) {
						 * 
						 * ds.addToGroup (((MLInt64) columns.get ("G")) .get
						 * (row));
						 * 
						 * }
						 */else { // if (columns.get (2).isCell () ||
									// columns.get (2).isChar ()) {

							if (columns.get ("G").isCell ()) {
								// System.out.println
								// ("This group is a cell of chars!");

								ds.addToGroup ( ((MLChar) ((MLCell) columns
										.get ("G")).cells ().get (row))
										.getString (0));

							} else if (columns.get ("G").isChar ()) {
								// System.out.println ("This group is a char!");

								ds.addToGroup ( ((MLChar) columns.get ("G"))
										.getString (row));

							} else {
								// TODO: error!
								System.out.println ("This group is an error!");
							}
						}
					}

				} else {
					number_of_invalid_rows += 1;
				}

			}

		} else {
			
			throw (new InvalidDataSizeException ("MatlabProcessor"));
		}
		
		// Finally, show the number of rows removed due to invalid data.
		JOptionPane.showMessageDialog (null, "There were " + number_of_invalid_rows + 
				"rows removed from the graph due to invalid data.");
	}

	/**
	 * <p>Checks if any given double is a valid value.
	 * 
	 * @param d The double to verify.
	 * @return True if the value isn't a NaN, is not Infinite, and is not null;
	 *         else, False.
	 */
	private boolean isValid (Double d) {
		return (! ( (d.isNaN ()) || (d.isInfinite ()) || (d == null)));
	}

	/**
	 * <p>Checks if any given String is a valid value.
	 * 
	 * @param s The String to verify.
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
	 * <p>Getter method. Provides the File object being used in this object.
	 * 
	 * @return The File object contained in this object.
	 */
	@Override
	public File getFile () {
		return (this.mat_file);
	}

	/**
	 * <p>Creates a string that contains the key names of each of the MLArrays
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
			
			String prefix = "";
			switch (m.getValue ().getType ()) {
				case mxUNKNOWN_CLASS:
					break;
				case mxCELL_CLASS:
					prefix = "";
					MLCell mxCELL_values = (MLCell) m.getValue ();
					for (int i = 0; i < mxCELL_values.getM (); i++) {
						sb.append(prefix);
						sb.append ( ((MLChar) mxCELL_values.get (i, 0))
								.getString (0));
						prefix = ", ";
					}
					break;
				case mxSTRUCT_CLASS:
					break;
				case mxCHAR_CLASS:
					prefix = "";
					MLChar mxCHAR_values = (MLChar) m.getValue ();
					for (int i = 0; i < mxCHAR_values.getM (); i++) {
						sb.append(prefix);
						sb.append (mxCHAR_values.getString (i));
						prefix = ", ";
					}
					break;
				case mxSPARSE_CLASS:
					break;
				case mxDOUBLE_CLASS:
					prefix = "";
					MLDouble mxDOUBLE_values = (MLDouble) m.getValue ();
					for (int i = 0; i < mxDOUBLE_values.getM (); i++) {
						sb.append(prefix);
						sb.append (mxDOUBLE_values.get (i, 0));
						prefix = ", ";
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
			sb.append (System.getProperty ("line.separator"));
		}

		return sb.toString ();
	}

	/**
	 * <p>Getter method. Provides the Map object obtained from the file.
	 * 
	 * @return The Map object that this object contains.
	 */
	public Map <String, MLArray> getData () {
		return (this.mat_data);
	}
	
	private boolean isHeaderColumn (String variable_name) {
		return ("header".equals (variable_name) || "Header".equals (variable_name)
				|| "headers".equals (variable_name) || "Headers".equals (variable_name));
	}
}