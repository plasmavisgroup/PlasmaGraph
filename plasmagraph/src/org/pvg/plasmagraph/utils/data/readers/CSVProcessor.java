package org.pvg.plasmagraph.utils.data.readers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CSVProcessor {
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
	}
	
	/**
	 * Default, full-batch way to read a CSV. Reads from the original file location.
	 */
	public void readCSV () {
		// Open CSV file "f".
		try (CSVReader csv = new CSVReader (new BufferedReader
				(new FileReader (csv_file)))) {
			
			// Read data into "csv_data".
			csv_data = csv.readAll ();
			
			// Close CSV file.
			csv.close ();
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Default, full-batch way to write a CSV. Writes to the original file location.
	 */
	public void writeCSV () {
		// Open CSV file "f".
		try (CSVWriter csv = new CSVWriter (new BufferedWriter
				(new FileWriter (csv_file)))) {
			
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
	 * Default, full-batch way to write a CSV.
	 * Writes to a new location as specified by the method call.
	 * 
	 * @param f The file object whose location will be used instead of the default.
	 */
	public void writeCSV (File f) {
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
	 * TODO: First row has column names. Need to pull those out first.
	 * Transforms the List<String[]> data object that CSVReader dumps out
	 * into a proper DataSet for the purposes of PlasmaGraph.
	 * 
	 * @return A DataSet object with its DataGroups being of the DataRow type.
	 */
	@SuppressWarnings ("unchecked")
	public DataSet toDataSet () {
		DataSet ds = new DataSet ();
		int [] row_length = new int [csv_data.size ()];
		
		// Row length equality test.
		// Obtain the sizes of each row.
		for (int i = 0; (i < csv_data.size ()); ++i) {
			row_length[i] = csv_data.get (i).length;
		}
		
		// Verify that the size of each row is correct!
		int typical_size = row_length[0];
		for (int i = 1; (i < csv_data.size ()); ++i) {
			if (row_length[i] != typical_size) {
				throw (new ArrayStoreException ("Two arrays have different sizes!"));
			}
		}
		// Proof: All rows are of the same length. 
		
		// DataColumn creation.
		// Set up the DataColumns first.
		// For each column in this row...
		for (int i = 0; (i < csv_data.get (0).length); ++i) {
			
			// What is the type of the data in that column.
			if (NumberUtils.isNumber (csv_data.get (1)[i].trim ())) {
				
				// Number? Then it's a double; add a DataColumn <Double>.
				// Name's at csv_data.get(0)[i].
				DataColumn <Double> dc = 
						new DataColumn <Double> (csv_data.get(0)[i].trim (), "double");
				ds.add (dc);
				
			} else {
				
				// Not a number? Then it's a string. Add a DataColumn <String>.
				// Name's at csv_data.get(0)[i].
				DataColumn <String> dc = 
						new DataColumn <String> (csv_data.get(0)[i].trim (), "string");
				
				ds.add (dc);
				
			}
		}
		
		// Now, fill in all the columns!
		for (int row = 1; (row < csv_data.size ()); ++row) {
			// Obtain the second, third, ..., last row in the List<String[]> "csv_data".
			// Ignore the first; it's only got column names.
			String [] s = csv_data.get (row);
			
			for (int col = 0; (col < ds.size ()); ++col) {
				
				// Number or String?
				if (ds.get (col).containsDoubles () == (NumberUtils.isNumber (s[col].trim ()))) {
					
					// It's a number.
					ds.get (col).add (NumberUtils.toDouble (s[col].trim ()));
					
				} else if (ds.get (col).containsStrings () != (NumberUtils.isNumber (s[col].trim ()))) {
					
					// It's a string.
					ds.get (col).add (s[col].trim ());
					 
				}
			}
		}
		// Return the fully-formed DataSet!
		return (ds);
	}

	/**
	 * TODO: Create method.
	 * Transforms a DataSet provided by the method call into a List<String[]>,
	 * the default format for the CSVReader / CSVWriter classes provided by 
	 * OpenCSV.
	 * 
	 * @param ds A DataSet object populated by DataRows.
	 */
	public void fromDataSet (DataSet ds) {
		// TODO: Everything.
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
	public void setCSVData (List<String[]> csv_data) {
		this.csv_data = csv_data;
	}
	
	/**
	 * Getter method. Provides the default File object for this class.
	 * 
	 * @return A File object, containing the default file location for this object.
	 */
	public File getFile () {
		return (csv_file);
	}
	
	/**
	 * Setter method. Changes the default File object to a method call-specified one.
	 * 
	 * @param new_file A new File object, containing the new default file location for this object.
	 */
	public void setFile (File new_file) {
		csv_file = new_file;
	}
	
}