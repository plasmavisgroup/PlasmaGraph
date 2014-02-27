package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;

import javax.swing.JOptionPane;

import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.Pair;
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
		DataSet ds = new DataSet ();
		CSVProcessor csv = new CSVProcessor (new File (default_file_path));
		csv.read ();
		csv.toDataSet (ds);
		
		// Prepare the template.
		Template t = new Template ();
		t.setChartType (ChartType.XY_GRAPH);
		t.setOutlierResponse (OutlierResponse.WARN);
		
		// Prepare the DataReference
		DataReference dr = new DataReference ();
		sb.append (ds.get (1).getColumnName ())
		  .append (" vs. ") //$NON-NLS-1$
		  .append (ds.get (3).getColumnName ());
		
		dr.add (new Pair (1, 3, sb.toString ()));
		
		// Clean the StringBuilder. It gets clogged sometimes.
		sb.delete (0, sb.length () - 1);
		
		// Perform the procedure.
		OutlierSearch.scanForOutliers (ds, t, dr);
		
		// Check if it worked.
		assertTrue ("Proper Outlier Removal Test", JOptionPane.showConfirmDialog //$NON-NLS-1$
				(null, "Did the procedure provide a proper de-outliered graph?", //$NON-NLS-1$
						"Proper Graph?", //$NON-NLS-1$
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
		
		//fail ("Not yet implemented"); // TODO
	}

}
