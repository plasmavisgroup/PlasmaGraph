package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.math3.util.Pair;
import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.readers.CSVProcessor;
import org.pvg.plasmagraph.utils.data.readers.MatlabProcessor;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.types.ColumnType;

@SuppressWarnings ("javadoc")
public class HeaderDataTest {

	@Test
	public void testAdd () {
		
		// Prepare Data
		HeaderData hd = new HeaderData ();
		String [] column_names = new String [] {"Experiment Number", 
				"Experiment Subject Name", "Success?", "Experiment Notes", 
				"Experiment Date"};
		
		ColumnType [] column_types = new ColumnType [] {ColumnType.DOUBLE,
				ColumnType.STRING, ColumnType.STRING, ColumnType.STRING,
				ColumnType.DATETIME};
		
		// Test
		for (int i = 0; (i < column_names.length); ++i) {
			assertTrue (hd.add (column_names[i], column_types[i]));
		}
	}

	@Test
	public void testRemove () {

		// Prepare Data
		HeaderData hd = new HeaderData ();
		String [] column_names = new String [] {"Experiment Number", 
				"Experiment Subject Name", "Success?", "Experiment Notes", 
				"Experiment Date"};
		
		ColumnType [] column_types = new ColumnType [] {ColumnType.DOUBLE,
				ColumnType.STRING, ColumnType.STRING, ColumnType.STRING,
				ColumnType.DATETIME};
		
		for (int i = 0; (i < column_names.length); ++i) {
			hd.add (column_names[i], column_types[i]);
		}
		
		// Test
		assertTrue (hd.remove (new Pair <> (column_names[0], column_types[0])));
		assertFalse (hd.remove (new Pair <> (column_names[0], column_types[0])));
		
	}

	@Test
	public void testFind () {

		// Prepare Data
		HeaderData hd = new HeaderData ();
		String [] column_names = new String [] {"Experiment Number", 
				"Experiment Subject Name", "Success?", "Experiment Notes", 
				"Experiment Date"};
		
		ColumnType [] column_types = new ColumnType [] {ColumnType.DOUBLE,
				ColumnType.STRING, ColumnType.STRING, ColumnType.STRING,
				ColumnType.DATETIME};
		
		for (int i = 0; (i < column_names.length); ++i) {
			hd.add (column_names[i], column_types[i]);
		}
		
		// Test
		int i = 0;
		for (Pair <String, ColumnType> p : hd) {
			assertTrue (hd.find (p) == i);
			i += 1;
		}
		
		assertTrue (hd.find ("Experiment Date") == 4);
		
	}

	@Test
	public void testContains () {

		// Prepare Data
		HeaderData hd = new HeaderData ();
		String [] column_names = new String [] {"Experiment Number", 
				"Experiment Subject Name", "Success?", "Experiment Notes", 
				"Experiment Date"};
		
		ColumnType [] column_types = new ColumnType [] {ColumnType.DOUBLE,
				ColumnType.STRING, ColumnType.STRING, ColumnType.STRING,
				ColumnType.DATETIME};
		
		for (int i = 0; (i < column_names.length); ++i) {
			hd.add (column_names[i], column_types[i]);
		}
		
		// Test
		for (Pair <String, ColumnType> p : hd) {
			assertTrue (hd.contains (p));
		}
		
	}

	@Test
	public void testGet () {

		// Prepare Data
		HeaderData hd = new HeaderData ();
		String [] column_names = new String [] {"Experiment Number", 
				"Experiment Subject Name", "Success?", "Experiment Notes", 
				"Experiment Date"};
		
		ColumnType [] column_types = new ColumnType [] {ColumnType.DOUBLE,
				ColumnType.STRING, ColumnType.STRING, ColumnType.STRING,
				ColumnType.DATETIME};
		
		for (int i = 0; (i < column_names.length); ++i) {
			hd.add (column_names[i], column_types[i]);
		}
		
		// Test
		int i = 0;
		for (Pair <String, ColumnType> p : hd) {
			assertTrue (hd.get (i).equals (p));
			i += 1;
		}

	}

	@Test
	public void testIsDouble () {
		
		// Prepare Data
		HeaderData hd = new HeaderData ();
		String [] column_names = new String [] {"Experiment Number", 
				"Experiment Subject Name", "Success?", "Experiment Notes", 
				"Experiment Date"};
		
		ColumnType [] column_types = new ColumnType [] {ColumnType.DOUBLE,
				ColumnType.STRING, ColumnType.STRING, ColumnType.STRING,
				ColumnType.DATETIME};
		
		for (int i = 0; (i < column_names.length); ++i) {
			hd.add (column_names[i], column_types[i]);
		}
		
		// Test
		assertTrue (hd.isDouble (0));
		assertFalse (hd.isDouble (1));
		assertFalse (hd.isDouble (2));
		assertFalse (hd.isDouble (3));
		assertFalse (hd.isDouble (4));
		
	}

	@Test
	public void testIsString () {
		
		// Prepare Data
		HeaderData hd = new HeaderData ();
		String [] column_names = new String [] {"Experiment Number", 
				"Experiment Subject Name", "Success?", "Experiment Notes", 
				"Experiment Date"};
		
		ColumnType [] column_types = new ColumnType [] {ColumnType.DOUBLE,
				ColumnType.STRING, ColumnType.STRING, ColumnType.STRING,
				ColumnType.DATETIME};
		
		for (int i = 0; (i < column_names.length); ++i) {
			hd.add (column_names[i], column_types[i]);
		}
		
		// Test
		assertFalse (hd.isString (0));
		assertTrue (hd.isString (1));
		assertTrue (hd.isString (2));
		assertTrue (hd.isString (3));
		assertFalse (hd.isString (4));
		
	}

	@Test
	public void testIsDateTime () {
		
		// Prepare Data
		HeaderData hd = new HeaderData ();
		String [] column_names = new String [] {"Experiment Number", 
				"Experiment Subject Name", "Success?", "Experiment Notes", 
				"Experiment Date"};
		
		ColumnType [] column_types = new ColumnType [] {ColumnType.DOUBLE,
				ColumnType.STRING, ColumnType.STRING, ColumnType.STRING,
				ColumnType.DATETIME};
		
		for (int i = 0; (i < column_names.length); ++i) {
			hd.add (column_names[i], column_types[i]);
		}
		
		// Test
		assertFalse (hd.isDateTime (0));
		assertFalse (hd.isDateTime (1));
		assertFalse (hd.isDateTime (2));
		assertFalse (hd.isDateTime (3));
		assertTrue (hd.isDateTime (4));
		
	}

	@Test
	public void testSize () {
		
		// Prepare Data
		HeaderData hd = new HeaderData ();
		
		assertTrue (hd.size () == 0);
		
		String [] column_names = new String [] {"Experiment Number", 
				"Experiment Subject Name", "Success?", "Experiment Notes", 
				"Experiment Date"};
		
		ColumnType [] column_types = new ColumnType [] {ColumnType.DOUBLE,
				ColumnType.STRING, ColumnType.STRING, ColumnType.STRING,
				ColumnType.DATETIME};
		
		// Test
		for (int i = 0; (i < column_names.length); ++i) {
			hd.add (column_names[i], column_types[i]);
			assertTrue (hd.size () == (i + 1));
			assertFalse (hd.size () == 0);
			assertFalse (hd.size () == 10);
		}
		
		assertTrue (hd.size () == 5);
		
	}

	// TODO: Group this test's data vy Experiment Number!
	@Test
	public void testPopulateData () {
		
		String data = "C:/Users/tako/Documents/GitHub/PlasmaGraph"
				+ "/plasmagraph/test/matlab/Parameter2013-06-11.mat";
		
		try {
			// Prepare Template
			Template t = new Template ();
			
			// Prepare Data
			MatlabProcessor csv = new MatlabProcessor (new File (data));
			HeaderData hd = new HeaderData ();
			csv.getHeaders (hd);
			
			// Prepare Pair.
			GraphPair p = new GraphPair ();
			p.changeGroup (0, hd.get (0).getKey ());
			p.changeX (6, hd.get (6).getKey ());
			p.changeY (7, hd.get (7).getKey ());
			
			DataSet ds = hd.populateData (p);
			
			// Test
			assertEquals (hd.get (p.getXColumnIndex ()).getKey (), ds.getXName ());
			assertEquals (hd.get (p.getYColumnIndex ()).getKey (), ds.getYName ());
			//assertEquals (p.getGroupName (), ds.getGroupName ());

		} catch (Exception e) {
			System.out.println (e.getMessage ());
		}
	}
	
	// TODO: -
	/*@Test
	public void testMultipleFilePopulateData () {
		
		String file1 = "C:/Users/tako/Documents/GitHub/PlasmaGraph"
				+ "/plasmagraph/test/csv/test2-2.csv";
		String file2 = "C:/Users/tako/Documents/GitHub/PlasmaGraph"
				+ "/plasmagraph/test/csv/test2-3.csv";
		
		try {
			// Prepare Data
			// Set 1
			CSVProcessor csv = new CSVProcessor (new File (file1));
			HeaderData hd = new HeaderData ();
			csv.getHeaders (hd);
			
			// Set 2
			csv = new CSVProcessor (new File (file2));
			csv.getHeaders (hd);
			
			// Prepare Pair.
			GraphPair p = new GraphPair ();
			p.changeX (0, "Time (s)");
			p.changeY (1, "Distance (m)");
			
			DataSet ds = hd.populateData (p);
			
			// Test
			for (int i = 0; (i < ds.size ()); ++i) {
				assertEquals (hd.get (i).getKey (), ds.get (i).getColumnName ());
			}
			
			//System.out.println (ds.toString ());
			assertEquals ("Number of Rows test: ", 22, ds.getColumnLength ());

		} catch (Exception e) {
			System.out.println (e.getMessage ());
		}
	}*/
}
