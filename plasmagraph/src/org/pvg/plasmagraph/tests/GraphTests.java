package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.util.Random;

import javax.swing.JOptionPane;

import org.jfree.chart.plot.PlotOrientation;
import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.Pair;
import org.pvg.plasmagraph.utils.graphs.BarGraph;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;
public class GraphTests {
	
	Random rand = new Random ();
	
	@Test
	public void testXYGraphs () {
		// Generate data.
		DataSet ds = xyDataTest ();
		Pair p = new Pair (0, 1, "XY Graph Test");
		
		// Generate Template.
		Template t = new Template ();
		//t.openTemplate (new java.io.File ("./template/graph_test.tem"));
		
		// Graph data via the XYGraph class!
		XYGraph chart = new XYGraph (t, ds, p);
		chart.pack ();
		chart.setVisible (true);
		
		// Test
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(chart, "Does the graph look like an XY Graph?",
						"Proper XY Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testBarGraph () {
		// Generate data.
		DataSet ds = BarGraphDataSet ();
		Pair p = new Pair (0, 1, "Bar Graph Test");
		
		// Generate Template.
		Template t = new Template ();
		t.setOrientation (PlotOrientation.VERTICAL);
		//t.openTemplate (new java.io.File ("./template/graph_test.tem"));
		
		// Graph data via the XYGraph class!
		BarGraph chart = new BarGraph (t, ds, p);
		chart.pack ();
		chart.setVisible (true);
		
		// Test
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(chart, "Does the graph look like an BarGraph?",
						"Proper BarGraph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	private DataSet BarGraphDataSet () {
		DataSet ds = new DataSet ();
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

	/**
	 * Generates a new DataSet for the testXYGraphs test method.
	 * Slightly randomizes the data via java.util.Random.
	 * 
	 * @return A new DataSet of DataRows with Time / Position / Velocity data.
	 */
	private DataSet xyDataTest () {
		DataSet ds = new DataSet ();
		
		// Columns: Time, Position, Velocity
		double prev = 0;
		// Initialize a new row...
		DataColumn <Double> r1 = new DataColumn <Double> ("Time", "double");
		DataColumn <Double> r2 = new DataColumn <Double> ("Position", "double");
		
		for (int i = 0; i < 5; ++i ) {
			
			// Populate it with random numbers...
			r1.add (new Double (i));
			r2.add (new Double (prev + rand.nextInt (5)));
			
			prev = r2.get (i);
		} 
		
		ds.add (r1);
		ds.add (r2);
		
		return (ds);
	}
}
