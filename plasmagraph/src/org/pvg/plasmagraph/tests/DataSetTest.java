package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.jfree.chart.plot.PlotOrientation;
import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.graphs.BarGraph;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;

public class DataSetTest {

	@Test
	public void testDataSet () {
		DataColumn<String> dc = new DataColumn<> ("Words per Minute", "string");
		assertTrue ("Test the type parameter:", dc.getType ().equals ("String"));
	}

	@Test
	public void testAdd () {
		// Set up test.
		DataSet ds = new DataSet (false);
		DataColumn<String> dc1 = new DataColumn<> ("Pie Flavors", "string");
		DataColumn<Double> dc2 = new DataColumn<> ("Pie Quantity", "double");
		
		// add(string)
		assertFalse ("Basic add (String) test with DataColumn: ", dc1.add (""));
		assertTrue ("Basic add (String) test with DataColumn: ", dc1.add ("Bland"));
		assertTrue ("Basic add (String) test with DataColumn: ", dc1.add ("Pecan"));
		assertFalse ("Basic add (String) test with DataColumn: ", dc1.add (null));
		
		// add(double)
		assertFalse ("Basic add (String) test with DataColumn: ", dc2.add (null));
		assertTrue ("Basic add (Double) test with DataColumn: ", dc2.add (3 * Math.E));
		assertTrue ("Basic add (Double) test with DataColumn: ", dc2.add (20.0));
		
		// add(DataColumn)
		assertTrue ("Basic column placement in DataSet: ", ds.add (dc1));
		assertTrue ("Basic column placement in DataSet: ", ds.add (dc2));
		
		System.out.println (ds.toString () );
	}
	
	@Test
	public void testRemove () {
		// Set up test.
		DataSet ds = prepareDataset ();
		
		// remove Strings
		assertTrue ("Basic removal of a string value.", 
				(ds.get (0).remove (1).equals ("Apple")));
		assertTrue ("Basic removal of a string value.", 
				(ds.get (0).remove ("Pecan")));
		
		// remove numbers
		assertTrue ("Basic removal of a number value.", 
				(ds.get (1).remove (0).equals (5.0)));
		assertTrue ("Basic removal of a number value.", 
				(ds.get (1).remove (15.0)));
		
		System.out.println (ds.toString ());
	}

	//@Test
	//public void testRemoveDataColumn () {
	//	DataSet ds = prepareDataset ();
	//	System.out.println (ds.toString ());
	//	
	//	assertTrue ("Removing first datacolumn: ", ds.remove (0) != null);
	//}


	@Test
	public void testFind () {
		// Generate DataSet
		DataSet ds = new DataSet (false);
		DataColumn <Double> dc1 = new DataColumn <> ("Time", "double");
		DataColumn <Double> dc2 = new DataColumn <> ("Distance", "double");
		
		dc1.add (0.0); dc2.add (0.0);
		dc1.add (1.0); dc2.add (5.0);
		dc1.add (2.0); dc2.add (10.0);
		dc1.add (3.0); dc2.add (15.0);
		dc1.add (4.0); dc2.add (20.0);
		
		ds.add (dc1);
		ds.add (dc2);
		
		// Test
		assertTrue ("Column 1 found: ", ds.find (dc1) == 0);
		assertTrue ("Column 2 found: ", ds.find (dc2) == 1);
		assertTrue ("Column named \"Time\" found: ", 
				ds.find ("Time") == 0);
		assertTrue ("Column named \"Distance\"  found: ", 
				ds.find ("Distance") == 1);
		assertFalse ("Column named \"Velocity\" not found: ", 
				ds.find ("Velocity") == 2);
	}

	@Test
	public void testContains () {
		DataSet ds = prepareDataset ();
		
		DataColumn<String> dc1 = new DataColumn<> ("Pie Flavors", "string");
		dc1.add ("Pecan");
		dc1.add ("Apple");
		dc1.add ("Cherry");
		ds.add (dc1);
		
		assertTrue ("Checking for a Pie Flavors element: ", ds.contains (dc1));
		assertFalse ("Trying to see if it contains a null element: ", ds.contains (null));
	}

	@Test
	public void testGet () {
		// Generate DataSet
		DataSet ds = new DataSet (false);
		DataColumn <Double> dc1 = new DataColumn <> ("Time", "double");
		DataColumn <Double> dc2 = new DataColumn <> ("Distance", "double");
		
		dc1.add (0.0); dc2.add (0.0);
		dc1.add (1.0); dc2.add (5.0);
		dc1.add (2.0); dc2.add (10.0);
		dc1.add (3.0); dc2.add (15.0);
		dc1.add (4.0); dc2.add (20.0);
		
		ds.add (dc1);
		ds.add (dc2);
		
		// Test
		assertTrue ("Same column: ", ds.get (0).equals (dc1));
		assertFalse ("Different column: ", ds.get (0).equals (dc2));
	}

	@Test
	public void testIsDouble () {
		DataSet ds = prepareDataset ();
		
		assertFalse ("Checking if the entire set is of doubles: ", 
				ds.isDouble ());
	}

	@Test
	public void testIsDoubleInt () {
		DataSet ds = prepareDataset ();
		
		assertTrue ("Checking if the second column is of doubles: ",
				ds.isDouble (1));
	}

	@Test
	public void testIsString () {
		DataSet ds = prepareDataset ();
		
		assertFalse ("Checking if the entire set is of strings: ", 
				ds.isString ());
	}

	@Test
	public void testIsStringInt () {
		DataSet ds = prepareDataset ();
		
		assertTrue ("Checking if the first column is of strings: ",
				ds.isString (0));
	}

	@Test
	public void testSize () {
		DataSet ds = prepareDataset ();
		
		assertTrue ("Correct number of DataColumns: ", ds.size () == 2);
	}

	@Test
	public void testToXYGraphDataset () {
		// Generate DataSet
		DataSet ds = new DataSet (false);
		DataColumn <Double> dc1 = new DataColumn <> ("Time", "double");
		DataColumn <Double> dc2 = new DataColumn <> ("Distance", "double");
		GraphPair p = new GraphPair (0, 1, "To XY Graph Dataset Test");
		
		dc1.add (0.0); dc2.add (0.0);
		dc1.add (1.0); dc2.add (5.0);
		dc1.add (2.0); dc2.add (10.0);
		dc1.add (3.0); dc2.add (15.0);
		dc1.add (4.0); dc2.add (20.0);
		
		ds.add (dc1);
		ds.add (dc2);
		
		// Generate Template.
		Template t = new Template ();
		//t.openTemplate (new java.io.File ("./template/graph_test.tem"));
		
		// Graph data via the XYGraph class!
		XYGraph chart = new XYGraph (t, ds, p);
		chart.pack ();
		chart.setVisible (true);
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(chart, "Does the graph look like an XY Graph?",
						"Proper XY Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}

	@Test
	public void testToBarGraphDataset () {
		DataSet ds = prepareDataset ();
		GraphPair p = new GraphPair (0, 1, "To Bar Graph Dataset Test");
		
		// Generate Template.
		Template t = new Template ();
		t.setOrientation (PlotOrientation.VERTICAL);
		//t.openTemplate (new java.io.File ("./template/graph_test.tem"));
		
		// Graph data via the XYGraph class!
		BarGraph chart = new BarGraph (t, ds, p);
		chart.pack ();
		chart.setVisible (true);
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(chart, "Does the graph look like a Bar Graph?",
						"Proper Bar Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}

	@Test
	public void testGetColumnLength () {
		DataSet ds = prepareDataset ();
		DataSet empty = new DataSet (false);
		
		assertTrue ("Checking column length.", ds.getColumnLength () == 3);
		assertTrue ("Checking col. len. of empty col.: ", empty.getColumnLength () == 0);
		assertTrue ("Checking index columnLength:", ds.getColumnLength () == ds.getColumnLength (1));
	}
	
	@Test
	public void testAppendDataSet () {
		DataSet ds1 = prepareDataset ();
		DataSet ds2 = prepareDataset ();
		
		assertTrue ("Testing append success.", ds1.append (ds2));
		assertEquals ("Testing resulting DataSet size.", 2, ds1.size ());
		assertEquals ("Testing resulting DataSet column size.", 6, ds1.getColumnLength ());
	}
	
	// Support methods.
	/**
	 * Prepares a basic dataset.
	 * @return A basic dataset to test over.
	 */
	private DataSet prepareDataset () {
		DataSet ds = new DataSet (false);
		DataColumn<String> dc1 = new DataColumn<String> ("Pie Flavors", "string");
		DataColumn<Double> dc2 = new DataColumn<Double> ("Pie Quantity", "double");
		
		dc1.add ("Pecan");
		dc1.add ("Apple");
		dc1.add ("Cherry");
		
		dc2.add (5.0);
		dc2.add (10.0);
		dc2.add (15.0);
		
		ds.add (dc1);
		ds.add (dc2);
		
		return (ds);
	}

}
