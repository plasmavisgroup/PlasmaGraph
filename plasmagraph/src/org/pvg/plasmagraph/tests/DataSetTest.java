package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.exceptions.InvalidParametersException;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.types.ColumnType;

@SuppressWarnings ("javadoc")
public class DataSetTest {

	@Test
	public void testAdd () {
		// Testing ungrouped data
		DataSet ds = this.createUngroupedDataSet ();
		
		for (int i = 0; (i < ds.size ()); ++i) {
			
			assertEquals ("Testing if the x values were added successfully", 
					i, ds.getXValue (i), 0.5);
			
			assertEquals ("Testing if the y values were added successfully", 
					i, ds.getYValue (i), 0.5);
		}
		
		// Testing grouped data - double
		ds = this.createDoublesGroupedDataSet ();
		
		for (int i = 0; (i < ds.size ()); ++i) {
			
			assertEquals ("Testing if the x values were added successfully", 
					i, ds.getXValue (i), 0.5);
			
			assertEquals ("Testing if the y values were added successfully", 
					i, ds.getYValue (i), 0.5);
			
			if (i < 25) {
				assertEquals ("Testing if the group double values were added successfully", 
						0, ds.getGroupDoubleValue (i), 0.5);
			} else {
				assertEquals ("Testing if the group double values were added successfully", 
						1, ds.getGroupDoubleValue (i), 0.5);
			}
		}

		// Testing grouped data - String
		ds = this.createStringGroupedDataSet ();
		
		for (int i = 0; (i < ds.size ()); ++i) {
			
			assertEquals ("Testing if the x values were added successfully", 
					i, ds.getXValue (i), 0.5);
			
			assertEquals ("Testing if the y values were added successfully", 
					i, ds.getYValue (i), 0.5);
			
			if (i < 25) {
				assertEquals ("Testing if the group String values were added successfully", 
						"First Half.", ds.getGroupStringValue (i));
			} else {
				assertEquals ("Testing if the group String values were added successfully", 
						"Second Half.", ds.getGroupStringValue (i));
			}
		}

	}

	@Test
	public void testRemove () {
		// Testing ungrouped data
		DataSet ds = this.createUngroupedDataSet ();
		
		assertFalse ("Testing that this isn't a grouped dataset", ds.isGrouped ());
		
		for (int i = 0; (i < ds.size ()); ++i) {
			
			assertTrue ("Testing if the row was removed successfully", ds.remove (i));
		}
		
		// Testing grouped data - double
		ds = this.createDoublesGroupedDataSet ();
		
		assertTrue ("Testing that this is a grouped dataset", ds.isGrouped ());
		assertTrue ("Testing that this is a double-grouped dataset", ds.isGroupDouble ());
		
		for (int i = 0; (i < ds.size ()); ++i) {
			
			assertTrue ("Testing if the row was removed successfully", ds.remove (i));
		}

		// Testing grouped data - String
		ds = this.createStringGroupedDataSet ();
		
		assertTrue ("Testing that this is a grouped dataset", ds.isGrouped ());
		assertTrue ("Testing that this is a String-grouped dataset", ds.isGroupString ());
		
		for (int i = 0; (i < ds.size ()); ++i) {
			
			assertTrue ("Testing if the row was removed successfully", ds.remove (i));
			
		}
	}

	@Test
	public void testFind () {
		// Testing ungrouped data
		DataSet ds = this.createUngroupedDataSet ();
		
		assertFalse ("Testing that this isn't a grouped dataset", ds.isGrouped ());
		
		assertEquals ("Trying to find an X value that doesn't exist!", -1,  ds.findX (-5));
		assertEquals ("Trying to find an X value that does exist!", 25,  ds.findX (25));
		
		assertEquals ("Trying to find a Y value that doesn't exist!", -1,  ds.findY (-5));
		assertEquals ("Trying to find a Y value that does exist!", 25,  ds.findY (25));
		
		// Testing grouped data - double
		ds = this.createDoublesGroupedDataSet ();
		
		assertTrue ("Testing that this is a grouped dataset", ds.isGrouped ());
		assertTrue ("Testing that this is a double-grouped dataset", ds.isGroupDouble ());
		
		assertEquals ("Trying to find an X value that doesn't exist!", -1,  ds.findX (-5));
		assertEquals ("Trying to find an X value that does exist!", 25,  ds.findX (25));
		
		assertEquals ("Trying to find a Y value that doesn't exist!", -1,  ds.findY (-5));
		assertEquals ("Trying to find a Y value that does exist!", 25,  ds.findY (25));
		
		assertEquals ("Trying to find a Group value that doesn't exist!",
				-1,  ds.findGroup (-5, -5));
		assertEquals ("Trying to find a Group value that doesn't exist!",
				-1,  ds.findGroup (25, 24));
		assertEquals ("Trying to find a Group value that does exist!", 
				25,  ds.findGroup (25, 25));
	}
	
	@Test
	public void testSetGroupType () {
		DataSet ds = new DataSet ();
		assertFalse ("Testing that it's not grouped", ds.isGrouped ());
		assertFalse ("Testing that it's not a double group", ds.isGroupDouble ());
		assertFalse ("Testing that it's not a string group", ds.isGroupString ());
		
		ds.setGroupType (ColumnType.DOUBLE);
		
		assertTrue ("Testing that it's grouped", ds.isGrouped ());
		assertTrue ("Testing that it's a double group", ds.isGroupDouble ());
		assertFalse ("Testing that it's not a string group", ds.isGroupString ());
		
		ds.setGroupType (ColumnType.STRING);
		
		assertTrue ("Testing that it's grouped", ds.isGrouped ());
		assertFalse ("Testing that it's not a double group", ds.isGroupDouble ());
		assertTrue ("Testing that it's a string group", ds.isGroupString ());
		
		ds.setGroupType (ColumnType.NONE);
		
		assertFalse ("Testing that it's not grouped", ds.isGrouped ());
		assertFalse ("Testing that it's not a double group", ds.isGroupDouble ());
		assertFalse ("Testing that it's not a string group", ds.isGroupString ());
	}

	@Test
	public void testToXYGraphDataset () throws InvalidParametersException {
		// Generate DataSet
		GraphPair p = new GraphPair ();
		p.changeX (0, "Time");
		p.changeY (1, "Distance");
		DataSet ds = new DataSet (p);
		
		ds.addToX (0.0); ds.addToY (0.0);
		ds.addToX (1.0); ds.addToY (5.0);
		ds.addToX (2.0); ds.addToY (10.0);
		ds.addToX (3.0); ds.addToY (15.0);
		ds.addToX (4.0); ds.addToY (20.0);
		
		// Generate Template.
		Template t = new Template ();
		//t.openTemplate (new java.io.File ("./template/graph_test.tem"));
		
		// Graph data via the XYGraph class!
		XYGraph chart = new XYGraph (t, ds, p);
		chart.testGraph ();
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(null, "Does the graph look like an XY Graph?",
						"Proper XY Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testToDoubleGroupedXYGraphDataset () throws InvalidParametersException {
		// Generate DataSet
		GraphPair p = new GraphPair ();
		p.changeX (0, "Time");
		p.changeY (1, "Distance");
		p.changeGroup (2, "Group");
		DataSet ds = new DataSet (ColumnType.DOUBLE, p);
		
		// Group 1
		ds.addToX (0.0); ds.addToY (0.0);  ds.addToGroup (0.0); 
		ds.addToX (1.0); ds.addToY (5.0);  ds.addToGroup (0.0); 
		ds.addToX (2.0); ds.addToY (10.0); ds.addToGroup (0.0); 
		ds.addToX (3.0); ds.addToY (15.0); ds.addToGroup (0.0); 
		ds.addToX (4.0); ds.addToY (20.0); ds.addToGroup (0.0); 
		
		// Group 2
		ds.addToX (0.0); ds.addToY (0.0);  ds.addToGroup (1.0); 
		ds.addToX (1.0); ds.addToY (4.0);  ds.addToGroup (1.0); 
		ds.addToX (2.0); ds.addToY (8.0); ds.addToGroup (1.0); 
		ds.addToX (3.0); ds.addToY (12.0); ds.addToGroup (1.0); 
		ds.addToX (4.0); ds.addToY (16.0); ds.addToGroup (1.0);
		
		// Generate Template.
		Template t = new Template ();
		//t.openTemplate (new java.io.File ("./template/graph_test.tem"));
		
		// Graph data via the XYGraph class!
		XYGraph chart = new XYGraph (t, ds, p);
		chart.testGraph ();
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(null, "Does the graph look like an XY Graph?",
						"Proper XY Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testToStringGroupedXYGraphDataset () throws InvalidParametersException {
		// Generate DataSet
		GraphPair p = new GraphPair ();
		p.changeX (0, "Time");
		p.changeY (1, "Distance");
		p.changeGroup (2, "Group");
		DataSet ds = new DataSet (ColumnType.STRING, p);
		
		// Group 1
		ds.addToX (0.0); ds.addToY (0.0);  ds.addToGroup ("Group 1");
		ds.addToX (1.0); ds.addToY (5.0);  ds.addToGroup ("Group 1");
		ds.addToX (2.0); ds.addToY (10.0); ds.addToGroup ("Group 1");
		ds.addToX (3.0); ds.addToY (15.0); ds.addToGroup ("Group 1");
		ds.addToX (4.0); ds.addToY (20.0); ds.addToGroup ("Group 1");
		
		// Group 2
		ds.addToX (0.0); ds.addToY (0.0);  ds.addToGroup ("Group 2");
		ds.addToX (1.0); ds.addToY (4.0);  ds.addToGroup ("Group 2");
		ds.addToX (2.0); ds.addToY (8.0);  ds.addToGroup ("Group 2");
		ds.addToX (3.0); ds.addToY (12.0); ds.addToGroup ("Group 2");
		ds.addToX (4.0); ds.addToY (16.0); ds.addToGroup ("Group 2");
		
		// Generate Template.
		Template t = new Template ();
		//t.openTemplate (new java.io.File ("./template/graph_test.tem"));
		
		// Graph data via the XYGraph class!
		XYGraph chart = new XYGraph (t, ds, p);
		chart.testGraph ();
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(null, "Does the graph look like an XY Graph?",
						"Proper XY Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	// Support methods.
	private DataSet createUngroupedDataSet () {
		DataSet ds = new DataSet ();
		
		for (int i = 0; (i < 50); ++i) {
			ds.addToX (i);
			ds.addToY (i);
		}
		
		return (ds);
	}
	
	private DataSet createDoublesGroupedDataSet () {
		DataSet ds = new DataSet (ColumnType.DOUBLE);
		
		for (int i = 0; (i < 50); ++i) {
			ds.addToX (i);
			ds.addToY (i);
			if (i < 25) {
				ds.addToGroup (0);
			} else {
				ds.addToGroup (1);
			}
		}
		
		return (ds);
	}
	
	private DataSet createStringGroupedDataSet () {
		DataSet ds = new DataSet (ColumnType.STRING);
		
		for (int i = 0; (i < 50); ++i) {
			ds.addToX (i);
			ds.addToY (i);
			if (i < 25) {
				ds.addToGroup ("First Half.");
			} else {
				ds.addToGroup ("Second Half.");
			}
		}
		
		return (ds);
	}
}
