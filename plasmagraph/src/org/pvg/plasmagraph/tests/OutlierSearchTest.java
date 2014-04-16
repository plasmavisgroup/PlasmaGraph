package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.jfree.chart.plot.PlotOrientation;
import org.junit.Test;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.readers.CSVProcessor;
import org.pvg.plasmagraph.utils.data.readers.MatlabProcessor;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.outlierscan.OutlierSearch;
import org.pvg.plasmagraph.utils.tools.outlierscan.distances.MahalanobisDistance;
import org.pvg.plasmagraph.utils.types.ChartType;
import org.pvg.plasmagraph.utils.types.OutlierResponse;

/**
 * TODO
 * @author Gerardo A. Navas Morales
 */
@SuppressWarnings ("javadoc")
public class OutlierSearchTest {

	private String default_file_path = "C:/Users/tako/Documents/GitHub/PlasmaGraph"
			+ "/plasmagraph/test/matlab/Parameter2013-06-13.mat";
	
	@Test
	public void testClusterScanning () throws Exception {
		
		// Prepare the data.
		MatlabProcessor mat = new MatlabProcessor (new File (default_file_path));
		//System.out.println (csv.toString ());
		HeaderData hd = new HeaderData ();
		mat.getHeaders (hd);
		
		// Prepare the template.
		Template t = new Template ();
		t.setChartType (ChartType.XY_GRAPH);
		t.setOutlierResponse (OutlierResponse.WARN);
		t.setOrientation (PlotOrientation.VERTICAL);
		
		// Prepare the GraphPair
		GraphPair p = new GraphPair ();
		p.changeX (6, hd.get (6).getKey ());
		p.changeY (7, hd.get (7).getKey ());
		
		// Perform the procedure.
		XYGraph g = new XYGraph (t, OutlierSearch.scanForOutliers (hd, t, p), p);
		g.testGraph ();
		
		// Check if it worked.
		assertTrue ("Proper Outlier Removal Test", JOptionPane.showConfirmDialog
				(null, "Did the procedure provide a proper de-outliered graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
		
	}
	
	@Test
	public void testMahalanobisDistance () throws Exception {
		// Prepare data.
		ArrayList <DoublePoint> outliers = new ArrayList <> ();
		
		Random r = new Random (84273548);
		
		for (int i = 1; (i < 101); ++i) {
			outliers.add (new DoublePoint (new double [] {
					i * r.nextDouble (), (i + 5) * r.nextDouble ()
			}));
		}
		
		MahalanobisDistance m_dist_calculator = new MahalanobisDistance ();
		
		// Perform the procedure.
		double mahalanobis_distance = m_dist_calculator.distance (outliers);
		
		// Check if it worked.
		assertTrue ("Proper Mahalanobis Distance Value Test", JOptionPane.showConfirmDialog
				(null, "Is " + mahalanobis_distance + " a non-null / non-NaN number?",
						"Proper return value?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
		
		
		
	}

}
