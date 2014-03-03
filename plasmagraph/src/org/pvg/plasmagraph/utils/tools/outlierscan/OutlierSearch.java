package org.pvg.plasmagraph.utils.tools.outlierscan;

import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.outlierscan.methods.ClusterScanning;
import org.pvg.plasmagraph.utils.tools.outlierscan.methods.ModifiedThompsonTauScanning;
import org.pvg.plasmagraph.utils.tools.outlierscan.methods.ScanMethod;
import org.pvg.plasmagraph.utils.types.ChartType;

public class OutlierSearch {
    
    /**
     * Given a DataSet with only the desired variables to scan, provides any
     * and all outliers via the IQR (Interquartile Range) method.
     * 
     * @param ds The focused DataSet of values.
     */
    public static void scanForOutliers (HeaderData hd, Template t, DataReference dr) {

    	// XY Graphs require a clustering method.
    	if (t.getChartType () == ChartType.XY_GRAPH) {
    		
    		ScanMethod sm = new ClusterScanning ();
    		
    		sm.scan (hd, t, dr);
    		
    	} // Bar Charts require a simple y-value method, like the Modified Thompson Tau!
    	else if (t.getChartType () == ChartType.BAR_GRAPH) {
    		
    		// Modified Thompson Tau!
    		ScanMethod sm = new ModifiedThompsonTauScanning ();
        	
    		sm.scan (hd, t, dr);
    		
    	}
    	else {
    		javax.swing.JOptionPane.showMessageDialog (null, 
    				"This is not a supported graph type for this function!");
    	}
    	
    }
}
