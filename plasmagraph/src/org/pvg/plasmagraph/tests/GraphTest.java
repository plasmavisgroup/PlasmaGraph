package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.util.Random;

import javax.swing.JOptionPane;

import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.readers.MatlabProcessor;
import org.pvg.plasmagraph.utils.exceptions.InvalidParametersException;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;

@SuppressWarnings ("javadoc")
public class GraphTest {
	
	Random rand = new Random ();
	
	@Test
	public void testXYGraphs () throws InvalidParametersException {
		// Generate data.
		DataSet ds = xyDataTest ();
		GraphPair p = new GraphPair ();
		p.changeX (0, "Time");
		p.changeY (1, "Position");
		
		// Generate Template.
		Template t = new Template ();
		//t.openTemplate (new java.io.File ("./template/graph_test.tem"));
		
		// Graph data via the XYGraph class!
		XYGraph chart = new XYGraph (t, ds, p);
		chart.testGraph ();
		
		// Test
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(null, "Does the graph look like an XY Graph?",
						"Proper XY Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testLargeMatlabGraph () throws Exception {
		/** test file uri **/
		String testfileURI = "./test/matlab/mat_file_limit_test.mat";
		
		// Prepare the HeaderData object.
		MatlabProcessor mat = new MatlabProcessor (testfileURI);
		HeaderData hd = new HeaderData ();
		mat.getHeaders (hd);
		
		// Prepare the Template and GraphPair
		Template t = new Template ();
		GraphPair p = new GraphPair ();
		p.changeX (0, "double_arr0");
		p.changeY (1, "double_arr1");
		
		// Graph it!
		XYGraph chart = new XYGraph (t, hd, p);
		chart.testGraph ();
		
		// Test
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(null, "Does the graph look like an XY Graph?",
						"Proper XY Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	/*@Test
	public void testBarGraph () {
		// Generate data.
		DataSet ds = BarGraphDataSet ();
		GraphPair p = new GraphPair ();
		p.changeX (0, "Pie Flavors");
		p.changeY (1, "Pie Quantity");
		
		// Generate Template.
		Template t = new Template ();
		t.setOrientation (PlotOrientation.VERTICAL);
		//t.openTemplate (new java.io.File ("./template/graph_test.tem"));
		
		// Graph data via the XYGraph class!
		BarGraph chart = new BarGraph (t, ds, p);
		chart.testGraph ();
		
		// Test
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(null, "Does the graph look like an BarGraph?",
						"Proper BarGraph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	private DataSet BarGraphDataSet () {
		
		GraphPair p = new GraphPair ();
		p.changeX (0, "Pie Flavors");
		p.changeY (1, "Pie Quantity");
		
		DataSet ds = new DataSet (p);
		
		ds.addToX ("Pecan");
		ds.addToX ("Apple");
		ds.addToX ("Cherry");
		
		ds.addToY (5.0);
		ds.addToY (10.0);
		ds.addToY (15.0);
		
		return (ds);
	}*/

	/**
	 * Generates a new DataSet for the testXYGraphs test method.
	 * Slightly randomizes the data via java.util.Random.
	 * 
	 * @return A new DataSet of DataRows with Time / Position / Velocity data.
	 */
	private DataSet xyDataTest () {
		
		GraphPair p = new GraphPair ();
		p.changeX (0, "Time");
		p.changeY (1, "Position");
		
		DataSet ds = new DataSet (p);
		
		// Columns: Time, Position, Velocity
		double prev = 0;
		// Initialize a new row...
		
		for (int i = 0; i < 5; ++i ) {
			
			// Populate it with random numbers...
			ds.addToX (new Double (i));
			ds.addToY (new Double (prev + rand.nextInt (5)));
			
			prev = ds.getYValue (i);
		}
		
		return (ds);
	}
}
