package org.pvg.plasmagraph.utils.tools.outlierscan;

import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
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
     * @param hd HeaderData object containing all possible columns.
     * @param t Template object containing specific information regarding Outlier Scan type selected by user.
     * @param p GraphPair object containing specific column index values being graphed.
     * @throws FunctionNotImplementedException 
     */
    public static DataSet scanForOutliers (HeaderData hd, Template t, GraphPair p) throws FunctionNotImplementedException {

    	// XY Graphs require a clustering method.
    	if (t.getChartType () == ChartType.XY_GRAPH) {
    		
    		ScanMethod sm = new ClusterScanning ();
    		
    		return (sm.scan (hd, t, p));
    		
    	} // Bar Charts require a simple y-value method, like the Modified Thompson Tau!
    	else if (t.getChartType () == ChartType.BAR_GRAPH) {
    		
    		// Modified Thompson Tau!
    		ScanMethod sm = new ModifiedThompsonTauScanning ();
        	
    		return (sm.scan (hd, t, p));
    		
    	}
    	else {
    		throw (new FunctionNotImplementedException ("Non-Existant Outlier Search Method"));
    	}
    	
    }
}
