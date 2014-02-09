package org.pvg.plasmagraph.utils.tools;

import java.util.Arrays;

import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;

public class OutlierSearch {
    
    /**
     * Given a DataSet with only the desired variables to scan, provides any
     * and all outliers via the IQR (Interquartile Range) method.
     * 
     * @param ds The focused DataSet of values.
     */
    public static void scanForOutliers (DataSet ds) {
        // TODO Auto-generated method stub
        DataSet rds = new DataSet ();
        
        
        
        // Sort.
        Arrays.sort (ds_array);
        
        // Find outliers.
        for (DataColumn d : ds) {
            
        }
    }
    
}
