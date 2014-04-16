package org.pvg.plasmagraph.utils.tools.outlierscan;

import java.util.ArrayList;

import org.apache.commons.math3.ml.clustering.DoublePoint;

public interface OutlierDistance {

	/**
     * Calculate the maximum outlier distance of a data set.
     * 
     * @param outlier_array An array of DoublePoints that the user has requested to be
     * checked for outliers.
     * @return A double containing the approximate cartesian distance between two points.
     * A point within this distance of the primary cluster is considered a regular point;
     * elsewise, it is an outlier.
     */
	public double distance (ArrayList<DoublePoint> outlier_array);

	/**
     * Helper method. Provides the string representation of the type of distance being calculated.
     */
	public String getDistanceType ();
}
