package org.pvg.plasmagraph.utils.tools.outlierscan.distances;

import java.util.ArrayList;

import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.pvg.plasmagraph.utils.tools.outlierscan.OutlierDistance;
import org.pvg.plasmagraph.utils.types.OutlierDistanceType;

public class CartesianDistance implements OutlierDistance {
	
	private double outlier_distance;

	public CartesianDistance (double d) {
		
		this.outlier_distance = d;
	}

	@Override
	public double distance (ArrayList<DoublePoint> outlier_array) {
		return (this.outlier_distance);
	}

	@Override
	public String getDistanceType () {
		return (OutlierDistanceType.USER.toString ());
	}
	
	

}
