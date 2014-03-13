package org.pvg.plasmagraph.utils.data.readers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.math3.util.Pair;
import org.apache.commons.validator.routines.DateValidator;
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.types.ColumnType;
import org.pvg.plasmagraph.utils.types.FileType;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * Data processor class. Manages the reading of CSV files and creates DataSets
 * for this project's usage.
 * 
 * @author Gerardo A. Navas Morales
 */
public class CSVProcessor implements FileProcessor {
	/**	Data container for values to and from the CSVReader / Writer classes. */
	private List<String[]> csv_data;
	/** CSV file location. */
	private File csv_file;
	
	/**
	 * Constructor. Creates a new CSVProcessor with a default File location as
	 * specified by the method call.
	 * 
	 * @param f A File object, containing the default file location for the CSV processes.
	 */
	public CSVProcessor (File f) {
		csv_file = f;
		this.read ();
	}
	
	/**
	 * Default, full-batch way to read a CSV. Reads from the original file location.
	 */
	@Override
	public void read () {
		// Open CSV file "f".
		try (CSVReader csv = new CSVReader (new BufferedReader
				(new FileReader (csv_file)))) {
			
			// Read data into "csv_data".
			csv_data = (ArrayList<String[]>) csv.readAll ();
			
			// Close CSV file.
			csv.close ();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Default, full-batch way to write a CSV. 
	 * Writes to the original file location.
	 */
	//@Override
	public void write () {
		this.write (csv_file);
	}
	
	/**
	 * Default, full-batch way to write a CSV.
	 * Writes to a new location as specified by the method call.
	 * 
	 * @param f The file object whose location will be used instead of the default.
	 */
	//@Override
	public void write (File f) {
		// Open CSV file "f".
		try (CSVWriter csv = new CSVWriter (new BufferedWriter
				(new FileWriter (f)))) {
			
			// Read data into "csv_data".
			csv.writeAll (csv_data);
			
			// Close CSV file.
			csv.close ();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Transforms the List<String[]> data object that CSVReader dumps out
	 * into a proper DataSet for the purposes of PlasmaGraph.
	 * 
	 * @param ds A DataSet object with its DataGroups being of the DataRow type.
	 * @throws Exception Malformed data set; columns are of different sizes.
	 */
	@Override
	public void toDataSet (DataSet ds, GraphPair p, HeaderData hd) throws Exception {
		// First, check to see if the file's been even read.
		if (this.csv_data.isEmpty ()) {
			
			this.read ();
		}

		// Now, prepare the columns of the DataSet.
		this.prepareColumns (ds, p, hd);
		
		// Once the columns are ready, populate the columns with the correct data!
		this.populateColumns (ds, p);
	}
	
	/**
	 * Transforms the List<String[]> data object that CSVReader dumps out
	 * into a proper DataSet for the purposes of PlasmaGraph.
	 * 
	 * @return A DataSet object with its DataGroups being of the DataRow type.
	 * @throws Exception Malformed data set; columns are of different sizes.
	 */
	@Override
	public void toDataSet (DataSet ds, GraphPair p, HeaderData hd, Template t) throws Exception {
		// First, check to see if the file's been even read.
		if (this.csv_data.isEmpty ()) {
			
			this.read ();
		}
		
		// Figure out the column you're looking for.
		int column_index = -1;
		for (int i = 0; (i < this.csv_data.get (0).length) && (column_index == -1); ++i) {
			
			// If the current column's name is equal to the column name in the Template.
			if (t.getGroupByColumn ().equals (this.csv_data.get (0)[i].trim ())) {
				
				column_index = i;
				
			}
			
		}
		
		// Now, prepare the columns of the DataSet.
		this.prepareColumns (ds, p, hd, column_index);
		
		// Once the columns are ready, populate the columns with the correct data!
		this.populateColumns (ds, p, column_index);
	}

	/**
	 * Helper method. Prepares the DataColumn variables that are contained in
	 * the DataSet, with help from the HeaderData and a GraphPair.
	 * 
	 * @param ds DataSet container already provided.
	 * @param p GraphPair object containing the index values of the DataSet's columns.
	 * @param hd HeaderData object containing the Column names and types.
	 */
	private void prepareColumns (DataSet ds, GraphPair p, HeaderData hd) {
		// Take the first index of the GraphPair, find it in the List we have here,
		/// and create the column it needs in the DataSet.
		
		if (hd.get (p.getIndex1 ()).getValue () == ColumnType.DATETIME) {
			
			ds.add (new DataColumn <java.util.Date> (hd.get (p.getIndex1 ()).getKey (), 
					hd.get (p.getIndex1 ()).getValue ()));
			
		} else if (hd.get (p.getIndex1 ()).getValue () == ColumnType.DOUBLE) {
			
			ds.add (new DataColumn <Double> (hd.get (p.getIndex1 ()).getKey (), 
					hd.get (p.getIndex1 ()).getValue ()));
			
		} else {
			
			ds.add (new DataColumn <String> (hd.get (p.getIndex1 ()).getKey (), 
					hd.get (p.getIndex1 ()).getValue ()));
			
		}
		
		// Now do that for the other GraphPair index.
		if (hd.get (p.getIndex2 ()).getValue () == ColumnType.DATETIME) {
			
			ds.add (new DataColumn <Double> (hd.get (p.getIndex2 ()).getKey (), 
					hd.get (p.getIndex2 ()).getValue ()));
			
		} else if (hd.get (p.getIndex2 ()).getValue () == ColumnType.DOUBLE) {
			
			ds.add (new DataColumn <Double> (hd.get (p.getIndex2 ()).getKey (), 
					hd.get (p.getIndex2 ()).getValue ()));
			
		} else {
			
			ds.add (new DataColumn <Double> (hd.get (p.getIndex2 ()).getKey (), 
					hd.get (p.getIndex2 ()).getValue ()));
			
		}
	}
	
	/**
	 * Helper method. Prepares the DataColumn variables that are contained in
	 * the DataSet, with help from the HeaderData and a GraphPair.
	 * 
	 * @param ds DataSet container already provided.
	 * @param p GraphPair object containing the index values of the DataSet's columns.
	 * @param hd HeaderData object containing the Column names and types.
	 * @param group_column_index Integer containing the column index of the group_by column.
	 */
	private void prepareColumns (DataSet ds, GraphPair p, HeaderData hd, int group_column_index) {
		// First, Add the data regarding the group_by column.
		Pair <String, ColumnType> c = hd.get (hd.find (this.csv_data.get (0)
				[group_column_index].trim ()));
		
		if (c.getValue () == ColumnType.DATETIME) {
			
			ds.add (new DataColumn <java.util.Date> (c.getKey (), c.getValue ()));
			
		} else if (c.getValue () == ColumnType.DOUBLE) {
			
			ds.add (new DataColumn <Double> (c.getKey (), c.getValue ()));
			
		} else {
			
			ds.add (new DataColumn <String> (c.getKey (), c.getValue ()));
			
		}
		
		// Now, take the first index of the GraphPair, find it in the List we have here,
		/// and create the column it needs in the DataSet.
		
		if (hd.get (p.getIndex1 ()).getValue () == ColumnType.DATETIME) {
			
			ds.add (new DataColumn <java.util.Date> (hd.get (p.getIndex1 ()).getKey (), 
					hd.get (p.getIndex1 ()).getValue ()));
			
		} else if (hd.get (p.getIndex1 ()).getValue () == ColumnType.DOUBLE) {
			
			ds.add (new DataColumn <Double> (hd.get (p.getIndex1 ()).getKey (), 
					hd.get (p.getIndex1 ()).getValue ()));
			
		} else {
			
			ds.add (new DataColumn <String> (hd.get (p.getIndex1 ()).getKey (), 
					hd.get (p.getIndex1 ()).getValue ()));
			
		}
		
		// Now do that for the other GraphPair index.
		if (hd.get (p.getIndex2 ()).getValue () == ColumnType.DATETIME) {
			
			ds.add (new DataColumn <Double> (hd.get (p.getIndex2 ()).getKey (), 
					hd.get (p.getIndex2 ()).getValue ()));
			
		} else if (hd.get (p.getIndex2 ()).getValue () == ColumnType.DOUBLE) {
			
			ds.add (new DataColumn <Double> (hd.get (p.getIndex2 ()).getKey (), 
					hd.get (p.getIndex2 ()).getValue ()));
			
		} else {
			
			ds.add (new DataColumn <Double> (hd.get (p.getIndex2 ()).getKey (), 
					hd.get (p.getIndex2 ()).getValue ()));
			
		}
	}

	/**
	 * Helper method. Populates the provided DataSet with data from this object's
	 * List of String arrays, csv_data, based on the index values of the GraphPair p.
	 * 
	 * @param ds DataSet container already provided.
	 * @param p GraphPair object containing the index values of the DataSet's columns.
	 */
	private void populateColumns (DataSet ds, GraphPair p) {
		
		DateValidator dv = new DateValidator ();
		
		// For each row in the List of String Arrays that we have, save the first.
		for (int i = 1; (i < this.csv_data.size ()); ++i) {
			
			// If the data is valid...
			if (this.isValidRow (this.csv_data.get (i), p)) {
				// Take only the values of the two index values of the GraphPair p and put them
				// into the DataSet ds.
				for (int j = 0; (j < ds.size ()); ++j) {
					
					// Populate as the correct type.
					if (ds.get (j).getType ().equals (ColumnType.DATETIME.toString ())) {
						
						// Get the column index to use.
						int pair_index = (j == 0) ? p.getIndex1 () : p.getIndex2 ();
						
						// Populating the column in ds.
						ds.get (j).add (dv.validate (this.csv_data.get (i) [pair_index].trim ()));
						
					} else if (ds.get (j).getType ().equals (ColumnType.DOUBLE.toString ())) {
						
						// Get the column index to use.
						int pair_index = (j == 0) ? p.getIndex1 () : p.getIndex2 ();
						
						// Populating the column in ds.
						ds.get (j).add (
								Double.valueOf (
										this.csv_data.get (i) [pair_index].trim ()));
						
					} else { // String!
						
						// Get the column index to use.
						int pair_index = (j == 0) ? p.getIndex1 () : p.getIndex2 ();
						
						// Populating the column in ds.
						ds.get (j).add (this.csv_data.get (i) [pair_index].trim ());
						
					}
				}
			}
		}
	}
	
	/**
	 * Helper method. Populates the provided DataSet with data from this object's
	 * List of String arrays, csv_data, based on the index values of the GraphPair p.
	 * 
	 * @param ds DataSet container already provided.
	 * @param p GraphPair object containing the index values of the DataSet's columns.
	 * @param column_index Template object containing the group_by column string.
	 */
	private void populateColumns (DataSet ds, GraphPair p, int group_column_index) {
		
		DateValidator dv = new DateValidator ();
		
		// For each row in the List of String Arrays that we have, save the first.
		for (int i = 1; (i < this.csv_data.size ()); ++i) {
			
			// If the data is valid...
			if (this.isValidRow (this.csv_data.get (i), p, group_column_index)) {
				// Take only the values of the two index values of the GraphPair p and put them
				// into the DataSet ds.
				for (int j = 0; (j < ds.size ()); ++j) {
					
					// Populate as the correct type.
					if (ds.get (j).getType ().equals (ColumnType.DATETIME.toString ())) {
						
						// Get the column index to use.
						int pair_index;
						if (j == 0) {
							pair_index = group_column_index;
						} else if (j == 1) {
							pair_index = p.getIndex1 ();
						} else { // j == 2
							pair_index = p.getIndex2 ();
						}
						
						// Populating the column in ds.
						ds.get (j).add (dv.validate (this.csv_data.get (i) [pair_index].trim ()));
						
					} else if (ds.get (j).getType ().equals (ColumnType.DOUBLE.toString ())) {
						
						// Get the column index to use.
						int pair_index;
						if (j == 0) {
							pair_index = group_column_index;
						} else if (j == 1) {
							pair_index = p.getIndex1 ();
						} else { // j == 2
							pair_index = p.getIndex2 ();
						}
						
						// Populating the column in ds.
						ds.get (j).add (
								Double.valueOf (
										this.csv_data.get (i) [pair_index].trim ()));
						
					} else { // String!
						
						// Get the column index to use.
						int pair_index;
						if (j == 0) {
							pair_index = group_column_index;
						} else if (j == 1) {
							pair_index = p.getIndex1 ();
						} else { // j == 2
							pair_index = p.getIndex2 ();
						}
						
						// Populating the column in ds.
						ds.get (j).add (this.csv_data.get (i) [pair_index].trim ());
						
					}
				}
			}
		}
	}
	
	/**
	 * Getter method. Provides the entire contents of the CSV file object.
	 * 
	 * @return A List of String array object, containing the data for this object.
	 */
	public List<String[]> getCSVData () {
		return csv_data;
	}

	/**
	 * Setter method. Changes the entire contents of the CSV file object.
	 * 
	 * @param new_file A new List of String array object, containing the data for this object.
	 */
	public void setCSVData (ArrayList<String[]> csv_data) {
		this.csv_data = csv_data;
	}
	
	/**
	 * Getter method. Provides the default File object for this class.
	 * 
	 * @return A File object, containing the default file location for this object.
	 */
	@Override
	public File getFile () {
		return (csv_file);
	}
	
	/**
	 * Setter method. Changes the default File object to a method call-specified one.
	 * 
	 * @param new_file A new File object, containing the new default file location for this object.
	 */
	@Override
	public void setFile (File new_file) {
		csv_file = new_file;
	}

	/**
	 * Getter Method; creates a set of columns with the correct names based on the 
	 * data in the CSV file. Does not fill them in, however.
	 * 
	 * @param ds DataSet to fill out with Columns, but not with data.
	 * @return A boolean describing the success or failure of this operation.
	 */
	@Override
	public boolean getHeaders (HeaderData hd) throws Exception {
		// First, check to see if the file's been even read.
		if (this.csv_data.isEmpty ()) {
			this.read ();
		}
		
		// Now we can continue.
		if (hd.isEmpty ()) {
			if (this.checkColumnSizes ()) {
				// For each column in this row...
				
				for (int i = 0; (i < csv_data.get (0).length); ++i) {
					
					// What is the type of the data in that column.
					ColumnType col_type = this.getType (i);

					// And just put it in!
					hd.add (new Pair <> (csv_data.get(0)[i].trim (), col_type));

				}
				
				// Then add that new file to the DataSet's list of files to import.
				hd.addFile (this.csv_file, FileType.CSV);
				
				return (true);
			} else {
				throw (new Exception ("Incorrect Header sizes! This is a malformed data file!"));
			}
		} else {
			// Check if the headers in the new file are the same as those already in the DataSet.
			boolean b = true;
			for (int i = 0; ((i < csv_data.get (0).length) && b); ++i) {
				b = (csv_data.get (0)[i].equals (hd.get (i).getKey ()));
			}
			
			// If they are, then add that new file to the DataSet's list of files to import.
			if (b) { 
				hd.addFile (this.csv_file, FileType.CSV);
			}
			
			return (b);
		}
		
	}

	private ColumnType getType (int column_index) {
		String s = "";
		
		// Check each row whilst a proper value has not been found.
		for (int row = 1; (row < this.csv_data.size ()) && (s.equals ("")); ++row) {
			
			// if this is a proper value, then put it in s.
			if (this.isValidItem (this.csv_data.get (row)[column_index])) {
				s = this.csv_data.get (row)[column_index];
			}
			
		}
		
		// Prepare the DateValidator
		org.apache.commons.validator.routines.DateValidator d = 
				new org.apache.commons.validator.routines.DateValidator ();
		
		// Now, verify the contents of "s" for the type of value it contains.
		if (d.validate (s) != null) {
			
			return (ColumnType.DATETIME);
			
		} else if (NumberUtils.isNumber (s.trim ())) {
			
			return (ColumnType.DOUBLE);
			
		} else {
			
			return (ColumnType.STRING);
			
		}
	}

	/**
	 * Checks the column sizes of the csv_data to see if they are all of equal size.
	 * 
	 * @return A boolean describing the success or failure of this operation.
	 */
	private boolean checkColumnSizes () {
		boolean equal = true;
		int [] row_length = new int [csv_data.size ()];
		
		// Row length equality test.
		// Obtain the sizes of each row.
		for (int i = 0; (i < csv_data.size ()); ++i) {
			row_length[i] = csv_data.get (i).length;
		}

		// Verify that the size of each row is correct!
		int typical_size = row_length[0];
		for (int i = 1; ((i < csv_data.size ()) && (equal)); ++i) {
			if (row_length[i] != typical_size) {
				equal = false;
				//throw (new ArrayStoreException ("Two arrays have different sizes!"));
			}
		}
		// Proof: All rows are of the same length.
		// Or they're not. Depends on the check!
		return (equal);
	}
	
	/**
	 * Checks to see if the string provided is a null value, contains the word 
	 * NaN, or is empty.
	 * 
	 * @param s String value containing a potential term for a data set.
	 * @param p GraphPair object containing the two columns to validate.
	 * @return A boolean describing if the value is valid or not.
	 */
	private boolean isValidRow (String [] s, GraphPair p) {
		return (this.isValidItem (s[p.getIndex1 ()]) &&
				this.isValidItem (s[p.getIndex2 ()]));
	}
	
	/**
	 * Checks to see if the string provided is a null value, contains the word 
	 * NaN, or is empty.
	 * 
	 * @param string String value containing a potential term for a data set.
	 * @param p GraphPair object containing the two columns to validate.
	 * @param grouping_column Int value of the column index of the group_by column.
	 * @return A boolean describing if the value is valid or not.
	 */
	private boolean isValidRow (String [] s, GraphPair p, int grouping_column) {
		return (this.isValidItem (s [p.getIndex1 ()]) &&
				this.isValidItem (s [p.getIndex2 ()]) &&
				this.isValidItem (s [grouping_column]));
	}
	
	private boolean isValidItem (String s) {
		return ((!s.trim ().equals (null)) && (!s.trim ().equals ("")) && 
				(!s.trim ().equals ("NaN")));
	}

	@Override
	public String toString () {
		
		if (this.csv_data.size () == 0) {
			this.read ();
		} 
		
		StringBuilder sb = new StringBuilder ();
		
		for (String [] s_array : this.csv_data) {
			for (String s : s_array) {
				sb.append (s).append (", ");
			}
			sb.append ("\n");
		}
		
		return (sb.toString ());
	}
	
}