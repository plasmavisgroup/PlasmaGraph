package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;

import javax.swing.JOptionPane;

import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.readers.CSVProcessor;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.Interpolator;
import org.pvg.plasmagraph.utils.types.InterpolationType;

public class InterpolatorTest {
	String linear_data = "C:/Users/tako/Documents/GitHub/PlasmaGraph"
			+ "/plasmagraph/test/interpolation/lindata.csv";
	String quadratic_data = "C:/Users/tako/Documents/GitHub/PlasmaGraph"
			+ "/plasmagraph/test/interpolation/quaddata.csv";
	String cubic_data = "C:/Users/tako/Documents/GitHub/PlasmaGraph"
			+ "/plasmagraph/test/interpolation/cubicdata.csv";
	String quartic_data = "C:/Users/tako/Documents/GitHub/PlasmaGraph"
			+ "/plasmagraph/test/interpolation/quartdata.csv";
	String spline_data = "C:/Users/tako/Documents/GitHub/PlasmaGraph"
			+ "/plasmagraph/test/interpolation/splinedata.csv";
	

	@Test
	public void testInterpolateLinear () throws Exception {
		// Pull the data out.
		CSVProcessor csv = new CSVProcessor (new File (linear_data));
		csv.readCSV ();
		DataSet ds = new DataSet ();
		csv.toDataSet (ds);
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.LINEAR);
		
		// Throw into interpolator.
		Interpolator.interpolate (ds, t);
		
		// Check if it worked.
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(null, "Does the graph look like a Linear Graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateQuadratic () throws Exception {
		// Pull the data out.
				CSVProcessor csv = new CSVProcessor (new File (quadratic_data));
				csv.readCSV ();
				DataSet ds = new DataSet ();
				csv.toDataSet (ds);
				
				// Prepare the Template
				Template t = new Template ();
				t.setInterpolationType (InterpolationType.QUADRATIC);
				// Throw into interpolator.
				Interpolator.interpolate (ds, t);
				
				// Check if it worked.
				assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
						(null, "Does the graph look like a Quadratic Graph?",
								"Proper Graph?",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateCubic () throws Exception {
		// Pull the data out.
		CSVProcessor csv = new CSVProcessor (new File (cubic_data));
		csv.readCSV ();
		DataSet ds = new DataSet ();
		csv.toDataSet (ds);
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.CUBIC);
		
		// Throw into interpolator.
		Interpolator.interpolate (ds, t);
		
		// Check if it worked.
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(null, "Does the graph look like a Cubic Graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateQuartic () throws Exception {
		// Pull the data out.
		CSVProcessor csv = new CSVProcessor (new File (quartic_data));
		csv.readCSV ();
		DataSet ds = new DataSet ();
		csv.toDataSet (ds);
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.QUARTIC);
		
		// Throw into interpolator.
		Interpolator.interpolate (ds, t);
		
		// Check if it worked.
		assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
				(null, "Does the graph look like a Quartic Graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateSpline () throws Exception {
		// Pull the data out.
				CSVProcessor csv = new CSVProcessor (new File (spline_data));
				csv.readCSV ();
				DataSet ds = new DataSet ();
				csv.toDataSet (ds);
				
				// Prepare the Template
				Template t = new Template ();
				t.setInterpolationType (InterpolationType.SPLINE);
				t.setUpperInterval (10.0);
				
				// Throw into interpolator.
				Interpolator.interpolate (ds, t);
				
				// Check if it worked.
				assertTrue ("Correctly Displayed?: ", JOptionPane.showConfirmDialog
						(null, "Does the graph look like a Spline Graph?",
								"Proper Graph?",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}

}
