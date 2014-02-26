package org.pvg.plasmagraph.utils.tools.outlierscan;

import java.util.ArrayList;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.stat.correlation.Covariance;

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
    public double distance (ArrayList<DoublePoint> outlier_array) {
    	
    	// Convert the outlier array provided into usable data.
    	RealMatrix xy_matrix = new Array2DRowRealMatrix (outlier_array.size (), 2);
    	
    	for (int i = 0; (i < outlier_array.size ()); ++i) {
    		xy_matrix.setRow (i, outlier_array.get (i).getPoint ());
    	}

        RealMatrix x_vector	= new Array2DRowRealMatrix (xy_matrix.getColumn (0));
        RealMatrix y_vector = new Array2DRowRealMatrix (xy_matrix.getColumn (1));
        
        // Generate the composite vector of x and y.
        RealMatrix xy_vector  = x_vector.subtract (y_vector);
        
        // Create the Inverse Covariance Matrix
        RealMatrix inverse_covariance = 
        		new LUDecomposition (new Covariance (xy_matrix).getCovarianceMatrix ())
        		.getSolver ().getInverse ();
        
        RealMatrix distSquared = xy_vector.transpose ().multiply (inverse_covariance)
                .multiply( xy_vector );

        return Math.sqrt(distSquared.getEntry(0, 0));
    }

	@Override
	public String getDistanceType () {
		return "Mahalanobis Distance";
	}
}
