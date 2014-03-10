package org.pvg.plasmagraph.utils.tools.outlierscan.distances;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.pvg.plasmagraph.utils.tools.outlierscan.OutlierDistance;

public class MahalanobisDistance implements OutlierDistance {

    /**
     * Does absolutely nothing save to allow for easy calling of the functions in this
     * object.
     */
    public MahalanobisDistance () {}
    
    /**
     * Calculate the maximum outlier distance of a data set.
     * 
     * @param outlier_array An array of DoublePoints that the user has requested to be
     * checked for outliers.
     * @return A double containing the approximate cartesian distance between two points.
     * A point within this distance of the primary cluster is considered a regular point;
     * elsewise, it is an outlier.
     */
    @Override
	public double distance (ArrayList<DoublePoint> outlier_array) {
    	
    	// Convert the outlier array provided into usable data.
    	RealMatrix xy_matrix = new Array2DRowRealMatrix (outlier_array.size (), outlier_array.get (0).getPoint ().length);
    	
    	for (int i = 0; (i < outlier_array.size ()); ++i) {
    		xy_matrix.setRow (i, outlier_array.get (i).getPoint ());
    	}
    	
    	// Generate the u matrix. (Mean)
    	RealMatrix u_matrix = new Array2DRowRealMatrix (outlier_array.size (), outlier_array.get (0).getPoint ().length);
    	
    	// Calculate the totals of each of the vectors.
    	for (DoublePoint p : outlier_array) {
    		u_matrix.addToEntry (0, 0, p.getPoint ()[0]);
    		u_matrix.addToEntry (0, 1, p.getPoint ()[1]);
    	}
    	
    	// Divide by the number of entries!
    	u_matrix.setEntry (0, 0, (u_matrix.getEntry (0, 0) / xy_matrix.getRowDimension ()));
    	u_matrix.setEntry (1, 0, (u_matrix.getEntry (0, 1) / xy_matrix.getRowDimension ()));
    	
    	// Propagate that value down to the other entries.
    	for (int i = 0; (i < u_matrix.getRowDimension ()); ++i) {
    		
    		for (int j = 0; (j < u_matrix.getColumnDimension ()); ++j) {
    			
    			u_matrix.setEntry (i, j, u_matrix.getEntry (0, j));
    			
    		}
    		
    	}
        
        // Generate the composite matrix of xy and u.x
        RealMatrix xu_matrix  = xy_matrix.subtract (u_matrix);
        
        // Create the Inverse Covariance Matrix
        RealMatrix inverse_covariance = 
        		new LUDecomposition (new Covariance (xy_matrix).getCovarianceMatrix ())
        		.getSolver ().getInverse ();
        
        // Important note: Although the typical formula for the Mahalanobis Distance requires the transposition of the first appearance of the first matrix, due to the format
        // of the data structures in this program, we require the transposition of the second appearance of the first matrix, instead.
        // This change does not affect the results of this calculation in any important way.
        RealMatrix distSquared = xu_matrix.multiply (inverse_covariance.multiply (xu_matrix.transpose ()));
        
        //System.out.println ("Mahalanobis Distance was: " + Math.sqrt(distSquared.getEntry(0, 0)));
        return (Math.sqrt(distSquared.getEntry(0, 0)));
    }  

	@Override
	public String getDistanceType () {
		return "Mahalanobis Distance";
	}
	
	private void printArrayContents (ArrayList <DoublePoint> p_array) {
		System.out.println ("Contents of ArrayList \'outlier_array\': ");
    	for (DoublePoint p : p_array) {
    		System.out.println (p.toString ());
    	}
	}
	
	private void printMatrixContents (RealMatrix m) {
		System.out.println (m.getRowDimension () + " x " + m.getColumnDimension ());
        System.out.println (m.toString ());
	}
}
