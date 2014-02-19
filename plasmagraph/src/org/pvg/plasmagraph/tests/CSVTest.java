package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.readers.CSVProcessor;

public class CSVTest {
	String default_csv_path = 
			"C:/Users/tako/Documents/GitHub/PlasmaGraph/plasmagraph/test/csv/csv_test.csv";
    
	@Test
	public void testReadCSV () {
		CSVProcessor csv = new CSVProcessor (new File (default_csv_path));
		
		csv.readCSV ();
		String ls = "\n";
		StringBuilder sb = new StringBuilder ();
		
		sb.append ("Time (s) Distance (m)" + ls);
		sb.append ("0 0" + ls);
		sb.append ("1 1" + ls);
		sb.append ("2 4" + ls);
		sb.append ("3 9" + ls);
		sb.append ("4 16" + ls);
		sb.append ("5 25" + ls);
		sb.append ("6 36" + ls);
		sb.append ("7 49" + ls);
		sb.append ("8 64" + ls);
		sb.append ("9 81" + ls);
		sb.append ("10 100" + ls);
		
		String comparison = sb.toString ();
		
		sb = new StringBuilder ();
		for (String [] s_array: csv.getCSVData ()) {
			for (String s : s_array) {
				sb.append (s);
			}
			sb.append (ls);
		}
		
		String csv_string = sb.toString ();
		
		//System.out.println (csv_string);
		//System.out.println (comparison);
		assertEquals ("Reading contents of CSV file.", comparison, csv_string);
	}


	@Test
	public void testWriteCSV () {
		// Create the file.
		ArrayList <String []> csv_file = new ArrayList <String []> ();
		csv_file.add (new String [] {"Time (s)", "Distance (m)"});
		csv_file.add (new String [] {"0", "0"});
		csv_file.add (new String [] {"1", "1"});
		csv_file.add (new String [] {"2", "4"});
		csv_file.add (new String [] {"3", "9"});
		csv_file.add (new String [] {"4", "16"});
		csv_file.add (new String [] {"5", "25"});
		csv_file.add (new String [] {"6", "36"});
		csv_file.add (new String [] {"7", "49"});
		csv_file.add (new String [] {"8", "64"});
		csv_file.add (new String [] {"9", "81"});
		csv_file.add (new String [] {"10", "100"});
		
		// Create CSV
		CSVProcessor csv = new CSVProcessor (new File ("./test/csv/test2-1.csv"));
		
		// Test write without file lication
		csv.setCSVData (csv_file);
		csv.writeCSV ();
		File test = new File ("./test/csv/test2-1.csv");
		
		assertTrue ("Did it make a file?", test.exists ());
		
		// Test write with file lication
		csv.writeCSV (new File ("./test/csv/test2-2.csv"));
		File test2 = new File ("./test/csv/test2-2.csv");
		
		assertTrue ("Did it make a file?", test2.exists ());
	}

	@Test
	public void testToDataSet () {
		CSVProcessor csv = new CSVProcessor (new File (this.default_csv_path));
		csv.readCSV ();
		// Open tested method file.
		DataSet ds1 = csv.toDataSet ();
		
		// Create simulated comparison file.
		DataSet ds2 = new DataSet ();
		DataColumn <Double> dc1 = new DataColumn <Double> ("Time (s)", "double");
		dc1.add (0.0); dc1.add (1.0); dc1.add (2.0); dc1.add (3.0); dc1.add (4.0);
		dc1.add (5.0); dc1.add (6.0); dc1.add (7.0); dc1.add (8.0); dc1.add (9.0);
		dc1.add (10.0);
		ds2.add (dc1);

		DataColumn <Double> dc2 = new DataColumn <Double> ("Distance (m)", "double");
		dc2.add (0.0); dc2.add (1.0); dc2.add (4.0); dc2.add (9.0); dc2.add (16.0);
		dc2.add (25.0); dc2.add (36.0); dc2.add (49.0); dc2.add (64.0); dc2.add (81.0);
		dc2.add (100.0);
		ds2.add (dc2);
		
		// Test
		assertTrue ("Column Length?", ds1.getColumnLength () == 11);
		assertEquals ("Column toString equality?", ds2.toString (), ds1.toString ());
		assertEquals ("Column 1 Names?", 
				ds1.get (0).getColumnName (), ds2.get (0).getColumnName ());
		assertEquals ("Column 2 Names?", 
				ds1.get (1).getColumnName (), ds2.get (1).getColumnName ());
	}

	@Test
	public void testFromDataSet () {
		fail ("Not yet implemented"); // TODO
	}

}