package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;

import javax.swing.JOptionPane;

import org.jfree.chart.plot.PlotOrientation;
import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.readers.CSVProcessor;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.outlierscan.OutlierSearch;
import org.pvg.plasmagraph.utils.types.ChartType;
import org.pvg.plasmagraph.utils.types.OutlierResponse;

public class OutlierSearchTest {

	private String default_file_path = 
			"C:/Users/tako/Documents/GitHub/PlasmaGraph/plasmagraph/test/csv/Parameter2013-06-11.csv"; //$NON-NLS-1$
	
	@Test
	public void testScanForOutliers () throws Exception {
		// Prepare helper tools
		StringBuilder sb = new StringBuilder ();
		
		// Prepare the data.
		CSVProcessor csv = new CSVProcessor (new File (default_file_path));
		//System.out.println (csv.toString ());
		HeaderData hd = new HeaderData ();
		csv.getHeaders (hd);
		
		// Prepare the template.
		Template t = new Template ();
		t.setChartType (ChartType.XY_GRAPH);
		t.setOutlierResponse (OutlierResponse.WARN);
		t.setOrientation (PlotOrientation.HORIZONTAL);
		
		// Prepare the DataReference
		DataReference dr = new DataReference ();
		sb.append (hd.get (1).getKey ())
		  .append (" vs. ") //$NON-NLS-1$
		  .append (hd.get (3).getKey ());
		
		dr.add (new GraphPair (2, 5, sb.toString ()));
		
		// Clean the StringBuilder. It gets clogged sometimes.
		sb.delete (0, sb.length () - 1);
		
		// Perform the procedure.
		OutlierSearch.scanForOutliers (hd, t, dr);
		
		// Check if it worked.
		assertTrue ("Proper Outlier Removal Test", JOptionPane.showConfirmDialog
				(null, "Did the procedure provide a proper de-outliered graph?",
						"Proper Graph?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
		
	}

}
