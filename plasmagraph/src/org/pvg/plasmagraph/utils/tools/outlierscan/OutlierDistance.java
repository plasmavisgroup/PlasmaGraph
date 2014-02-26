package org.pvg.plasmagraph.utils.tools.outlierscan;

import java.util.ArrayList;

import org.apache.commons.math3.ml.clustering.DoublePoint;

public interface OutlierDistance {

	public double distance (ArrayList<DoublePoint> outlier_array);

	public String getDistanceType ();
}
