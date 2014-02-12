package org.pvg.plasmagraph.utils.tools;

import java.util.ArrayList;
import java.util.Arrays;

import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.types.OutlierResponse;

public class OutlierSearch {
    
    /**
     * Given a DataSet with only the desired variables to scan, provides any
     * and all outliers via the IQR (Interquartile Range) method.
     * 
     * @param ds The focused DataSet of values.
     */
    public static void scanForOutliers (DataSet ds, Template t) {
    	// Prepare tools for Outlier Scan use.
        DataSet rds = new DataSet ();
        
        // Determine if User Validation will be performed.
        // I.E. This is the second step in validation.
        if (t.getOutlierResponse ().equals (OutlierResponse.WARN)) {
        	searchWithUserValidation (ds, t);
        } else {
        	searchWithoutUserValidation (ds, t);
        }
        
       
    }
    
    private static void searchWithUserValidation (DataSet ds, Template t) {
    	// Obtain a list of possible outliers via phase 1 tests.
    	ArrayList <Integer> possible_outliers = phase1 (ds, t);
    }
    
    private static void searchWithoutUserValidation (DataSet ds, Template t) {
    	
    }
    
    private static ArrayList<Integer> phase1 (DataSet ds, Template t) {
    	// Prepare return data container.
    	ArrayList<Integer> possible_outliers = new ArrayList<Integer> ();
    	
    	// Iterate through the DataSet.
    	for (int row = 0; (row < ds.getColumnLength ()); ++row) {
    		
    	}
    	
		return possible_outliers;
    }
    
    private static void phase2 (DataSet ds, Template t) {
    	
    }
    
}
