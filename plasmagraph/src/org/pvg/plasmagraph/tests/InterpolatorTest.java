package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;

import javax.swing.JOptionPane;

import org.junit.Test;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.readers.MatlabProcessor;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.interpolation.Interpolator;
import org.pvg.plasmagraph.utils.types.InterpolationType;

@SuppressWarnings ("javadoc")
public class InterpolatorTest {
	String data = "C:/Users/tako/Documents/GitHub/PlasmaGraph"
			+ "/plasmagraph/test/matlab/Parameter2013-06-11.mat";
	

	@Test
	public void testInterpolateLinear () throws Exception {
		// Pull the data out.
		MatlabProcessor mat = new MatlabProcessor (new File (data));
		HeaderData hd = new HeaderData ();
		mat.getHeaders (hd);
		
		GraphPair p = new GraphPair ();
		p.changeX (6, hd.get (6).getKey ());
		p.changeY (7, hd.get (7).getKey ());
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.LINEAR);
		
		// Throw into interpolator.
		Interpolator i = new Interpolator (hd, t, p);
		i.interpolate ().testGraph ();
		
		// Check if it worked.
		assertTrue ("Proper Fit Test", JOptionPane.showConfirmDialog
				(null, "Does the interpolation curve match the points of the graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateQuadratic () throws Exception {
		// Pull the data out.
		MatlabProcessor mat = new MatlabProcessor (new File (data));
		HeaderData hd = new HeaderData ();
		mat.getHeaders (hd);
		
		GraphPair p = new GraphPair ();
		p.changeX (6, hd.get (6).getKey ());
		p.changeY (7, hd.get (7).getKey ());
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.QUADRATIC);

		// Throw into interpolator.
		Interpolator i = new Interpolator (hd, t, p);
		i.interpolate ().testGraph ();
		
		// Check if it worked.
		assertTrue ("Proper Fit Test", JOptionPane.showConfirmDialog
				(null, "Does the interpolation curve match the points of the graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateCubic () throws Exception {
		// Pull the data out.
		MatlabProcessor mat = new MatlabProcessor (new File (data));
		HeaderData hd = new HeaderData ();
		mat.getHeaders (hd);
		
		GraphPair p = new GraphPair ();
		p.changeX (6, hd.get (6).getKey ());
		p.changeY (7, hd.get (7).getKey ());
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.CUBIC);
		
		// Throw into interpolator.
		Interpolator i = new Interpolator (hd, t, p);
		i.interpolate ().testGraph ();
		
		// Check if it worked.
		assertTrue ("Proper Fit Test", JOptionPane.showConfirmDialog
				(null, "Does the interpolation curve match the points of the graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateQuartic () throws Exception {
		// Pull the data out.
		MatlabProcessor mat = new MatlabProcessor (new File (data));//quartic_data));
		HeaderData hd = new HeaderData ();
		mat.getHeaders (hd);
		
		GraphPair p = new GraphPair ();
		p.changeX (6, hd.get (6).getKey ());
		p.changeY (7, hd.get (7).getKey ());
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.QUARTIC);
		
		// Throw into interpolator.
		Interpolator i = new Interpolator (hd, t, p);
		i.interpolate ().testGraph ();
		
		// Check if it worked.
		assertTrue ("Proper Fit Test", JOptionPane.showConfirmDialog
				(null, "Does the interpolation curve match the points of the graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}
	
	@Test
	public void testInterpolateSpline () throws Exception {
		// Pull the data out.
		MatlabProcessor mat = new MatlabProcessor (new File (data));//spline_data));
		HeaderData hd = new HeaderData ();
		mat.getHeaders (hd);
		
		GraphPair p = new GraphPair ();
		p.changeX (6, hd.get (6).getKey ());
		p.changeY (7, hd.get (7).getKey ());
		
		// Prepare the Template
		Template t = new Template ();
		t.setInterpolationType (InterpolationType.SPLINE);
		
		// Throw into interpolator.
		Interpolator i = new Interpolator (hd, t, p);
		i.interpolate ().testGraph ();
		
		// Check if it worked.
		assertTrue ("Proper Fit Test", JOptionPane.showConfirmDialog
				(null, "Does the interpolation curve match the points of the graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}

}
