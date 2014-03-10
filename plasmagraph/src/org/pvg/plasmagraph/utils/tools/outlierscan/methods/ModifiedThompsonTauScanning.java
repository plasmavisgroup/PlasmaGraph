package org.pvg.plasmagraph.utils.tools.outlierscan.methods;

import java.util.ArrayList;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.inference.TTest;
import org.pvg.plasmagraph.utils.ExceptionHandler;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
import org.pvg.plasmagraph.utils.template.Template;

/**
 * Used only in 1-Dimensional data sets, such as those used by Bar Graphs, the 
 * ModifiedThompsonTauScanning class provides a robust procedure for the analysis
 * and removal of outlying data points.
 * 
 * @author Gerardo A. Navas Morales
 */
public class ModifiedThompsonTauScanning implements ScanMethod {

	@Override
	public DataSet scan (HeaderData hd, Template t, GraphPair dr) throws FunctionNotImplementedException {
		// TODO Auto-generated method stub
		throw (new FunctionNotImplementedException ());
	}
	
	private void populate (ArrayList<Double> outlier_array, HeaderData ds, 
			GraphPair p) {
		// TODO Auto-generated method stub
		
	}

	private boolean search (ArrayList<Double> outlier_array, Template t,
			DataReference dr) {
		// TODO Auto-generated method stub
		return false;
	}

	private void graph (ArrayList<Double> outlier_array, Template t) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isInvalid (ArrayList<Double> outlier_array, int index) {
		// Prepare variables
		double tau = 0.0;
		double sample_mean = 0.0;
		double sample_standard_deviation = 0.0;
		double absolute_deviation = 0.0;
		
		// Calculate STDev.
		StandardDeviation dev = new StandardDeviation ();
		for (double d : outlier_array) {
			dev.increment (d);
		}
		sample_standard_deviation = dev.getResult ();
		
		// Calculate Mean.
		Mean mean = new Mean ();
		for (double d : outlier_array) {
			mean.increment (d);
		}
		sample_mean = mean.getResult ();
		
		// Calculate abs.(deviation)
		absolute_deviation = (outlier_array.get (index) - sample_mean);
		
		// Calculate tau.
		int n = outlier_array.size ();
		double [] critical_value_array = new double [outlier_array.size ()];
		
		for (int i = 0; (i < outlier_array.size ()); ++i) {
			critical_value_array[i] = outlier_array.get (i);
		}
		
		TTest t = new TTest ();
		double critical_t_value = t.t (sample_mean, critical_value_array);
		
		
		
		
		return (absolute_deviation > (tau * sample_standard_deviation));
	}

}
