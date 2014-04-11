package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;

import javax.swing.JOptionPane;

import org.junit.Test;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.readers.CSVProcessor;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.interpolation.Interpolator;
import org.pvg.plasmagraph.utils.types.FileType;
import org.pvg.plasmagraph.utils.types.InterpolationType;

@SuppressWarnings ("javadoc")
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
		HeaderData hd = new HeaderData ();
		csv.getHeaders (hd);
		GraphPair p = new GraphPair (0, "Time (s)", 1, "Distance (m)");
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.LINEAR);
		
		// Throw into interpolator.
		Interpolator.interpolate (hd, t, p);
		
		// Check if it worked.
		assertTrue ("Proper Fit Test", JOptionPane.showConfirmDialog
				(null, "Does the interpolation curve match the points of the graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateQuadratic () throws Exception {
		// Pull the data out.
		CSVProcessor csv = new CSVProcessor (new File (quadratic_data));
		HeaderData hd = new HeaderData ();
		csv.getHeaders (hd);
		GraphPair p = new GraphPair (0, "Time (s)", 1, "Distance (m)");
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.QUADRATIC);
		// Throw into interpolator.
		Interpolator.interpolate (hd, t, p);
		
		// Check if it worked.
		assertTrue ("Proper Fit Test", JOptionPane.showConfirmDialog
				(null, "Does the interpolation curve match the points of the graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateCubic () throws Exception {
		// Pull the data out.
		CSVProcessor csv = new CSVProcessor (new File (cubic_data));
		HeaderData hd = new HeaderData ();
		csv.getHeaders (hd);
		GraphPair p = new GraphPair (0, "Time (s)", 1, "Distance (m)");
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.CUBIC);
		
		// Throw into interpolator.
		Interpolator.interpolate (hd, t, p);
		
		// Check if it worked.
		assertTrue ("Proper Fit Test", JOptionPane.showConfirmDialog
				(null, "Does the interpolation curve match the points of the graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateQuartic () throws Exception {
		// Pull the data out.
		CSVProcessor csv = new CSVProcessor (new File (spline_data));//quartic_data));
		HeaderData hd = new HeaderData ();
		csv.getHeaders (hd);
		GraphPair p = new GraphPair (0, "Time (s)", 1, "Distance (m)");
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.QUARTIC);
		
		// Throw into interpolator.
		Interpolator.interpolate (hd, t, p);
		
		// Check if it worked.
		assertFalse ("Proper Fit Test", JOptionPane.showConfirmDialog
				(null, "Does the interpolation curve match the points of the graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateSpline () throws Exception {
		// Pull the data out.
		CSVProcessor csv = new CSVProcessor (new File (linear_data));//spline_data));
		HeaderData hd = new HeaderData ();
		csv.getHeaders (hd);
		GraphPair p = new GraphPair (0, "Time (s)", 1, "Distance (m)");
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.SPLINE);
		t.setUpperInterval (10.0);
		t.setInterpolationInterval (500);
		
		// Throw into interpolator.
		Interpolator.interpolate (hd, t, p);
		
		// Check if it worked.
		assertTrue ("Proper Fit Test", JOptionPane.showConfirmDialog
				(null, "Does the interpolation curve match the points of the graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}

}
