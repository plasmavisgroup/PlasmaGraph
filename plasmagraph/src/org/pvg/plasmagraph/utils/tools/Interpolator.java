package org.pvg.plasmagraph.utils.tools;

import javax.swing.JOptionPane;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.data.function.*;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.types.InterpolationType;

public class Interpolator {
  
	
	/**
	 * External path to interpolate data and graph said data.
	 * 
	 * @param ds The DataSet to interpolate.
	 * @param t The settings the DataSet is based upon.
	 */
    public static void interpolate (DataSet ds, Template t) {

        // Check which of the different regressions you'll be doing.
        XYSeries interpolated_dataset = getInterpolation (ds, t);
        
        //System.out.println (printXYSeries (interpolated_dataset));
        
        // Graph it!
        graphInterpolation (ds.toXYGraphDataset (), interpolated_dataset, t);
        
    }

    /**
     * Performs an interpolation, either via regression or a specialized interpolation
     * method, and provides a dataset generated from said process.
     * 
     * @param ds Input DataSet, used to glean values from.
     * @param t Template with values for the minimum / maximum bounds of the process and
     * the interval for each point.
     * @return An XYSeries containing the interpolated Dataset.
     */
	private static XYSeries getInterpolation (DataSet ds, Template t) {
		// Set up Variables.
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		// Pearson's r Container.
		double pearsons_r;
		// Pearsons Correlation calculator for most functions.
    	PearsonsCorrelation p_correlation = new PearsonsCorrelation ();
    	// XYDataset container for some JFree operations.
    	XYSeriesCollection regression_set = new XYSeriesCollection (ds.toXYGraphDataset ());
    	// Test Calls.
    	//System.out.println ("Series: \n" + printXYSeries (ds.toXYGraphDataset ()));
    	
    	//======================================================================================//
    	// Perform the regression and get the dataset out of it, depending on the type
    	// of regression to perform.
    	if (t.getInterpolationType ().equals (InterpolationType.LINEAR)) {
    		
    		// Create the double [][] container and regress it.
    		SimpleRegression linear_regression = new SimpleRegression (true);
    		linear_regression.addData (ds.toArray ());
    		RegressionResults lin_regression = linear_regression.regress ();
    		
    		// Obtain the parameters.
    		regression_params = lin_regression.getParameterEstimates ();
    		
    		// Create an XYSeries based on those parameters.
    		// Note that, sometimes, there is no intercept! Check for it!
    		if (lin_regression.hasIntercept ()) {
    			regression_dataset = DatasetUtilities.sampleFunction2DToSeries (new LineFunction2D (regression_params[0], regression_params[1]),
    					t.getLowerInterval (), t.getUpperInterval (), t.getInterpolationInterval (), getSeriesKey(t));
    		} else {
    			regression_dataset = DatasetUtilities.sampleFunction2DToSeries (new LineFunction2D (0.0, regression_params[1]),
    					t.getLowerInterval (), t.getUpperInterval (), t.getInterpolationInterval (), getSeriesKey(t));
    		}
    		
    		// Obtain the R-Squared value.
    		pearsons_r = p_correlation.correlation (createArrayFromSeries (regression_dataset, true),
    				createArrayFromSeries (regression_dataset, false));
        	
        } //======================================================================================//
    	else if (t.getInterpolationType ().equals (InterpolationType.QUADRATIC)) {
        	
    		regression_params = org.jfree.data.statistics.Regression.
        			getPolynomialRegression (regression_set, 0, 2);
        	
        	regression_dataset = DatasetUtilities.sampleFunction2DToSeries
        			(new PolynomialFunction2D
        				(new double [] {regression_params[0], regression_params[1], regression_params[2]}),
        			t.getLowerInterval (), t.getUpperInterval (),
					t.getInterpolationInterval (), getSeriesKey(t));
        	
        	pearsons_r = p_correlation.correlation (createArrayFromSeries (regression_dataset, true),
        			createArrayFromSeries (regression_dataset, false));
        	
        }  //======================================================================================//
        else if (t.getInterpolationType ().equals (InterpolationType.CUBIC)) {
        	
        	regression_params = org.jfree.data.statistics.Regression.
        			getPolynomialRegression (regression_set, 0, 3);
        	
        	regression_dataset = DatasetUtilities.sampleFunction2DToSeries
        			(new PolynomialFunction2D
        					(new double [] {regression_params[0], regression_params[1], regression_params[2], regression_params[3]}),
        			t.getLowerInterval (), t.getUpperInterval (),
					t.getInterpolationInterval (), getSeriesKey(t));
        	
        	pearsons_r = p_correlation.correlation (createArrayFromSeries (regression_dataset, true),
        			createArrayFromSeries (regression_dataset, false));
        	
        }  //======================================================================================// 
        else if (t.getInterpolationType ().equals (InterpolationType.QUARTIC)) {
        	
        	regression_params = org.jfree.data.statistics.Regression.
        			getPolynomialRegression (regression_set, 0, 4);
        	
        	regression_dataset = DatasetUtilities.sampleFunction2DToSeries
        			(new PolynomialFunction2D
        					(new double [] {regression_params[0], regression_params[1], regression_params[2], regression_params[3], regression_params[4]}),
        			t.getLowerInterval (), t.getUpperInterval (),
					t.getInterpolationInterval (), getSeriesKey(t));
        	
        	pearsons_r = p_correlation.correlation (createArrayFromSeries (regression_dataset, true),
        			createArrayFromSeries (regression_dataset, false));
        	
        } //======================================================================================//
    	else { // if (t.getInterpolationType ().equals (InterpolationType.SPLINE))
        	// Get Function to create data.
        	
        	SplineInterpolator spline = new SplineInterpolator ();
        	PolynomialSplineFunction func = spline.interpolate
        			 (ds.get (0).toDoubleArray (), ds.get (1).toDoubleArray ());
        	 
        	// Create data from function.
        	regression_dataset = createSeries (func, t);
        	
        	// Obtain r_squared value from data.
        	pearsons_r = p_correlation.correlation (createArrayFromSeries (regression_dataset, true), 
        			createArrayFromSeries (regression_dataset, false));
        	
        }  //======================================================================================//
    	
    	showRegressionValidity (pearsons_r, ds.getColumnLength ());
    	
		return (regression_dataset);
    }
	
	/**
	 * Provides an XYSeries via an PolynomialSplineFunction's result value-generation function.
	 * 
	 * @param func PolynomialSplineFunction which will generate y values.
	 * @param t Template object that contains interval bounds and interval.
	 * @return An XYSeries provided by the PolynomialSplineFunction.
	 */
	private static XYSeries createSeries (PolynomialSplineFunction func, Template t) {
		XYSeries s = new XYSeries ("Interpolation");
		double x_value;
		
		for (int i = 0; (i < t.getInterpolationInterval ()); ++i) {
			x_value = (t.getUpperInterval () * (i * (1.0 / t.getInterpolationInterval ()))) + t.getLowerInterval ();
			
			s.add (x_value, func.value (x_value));
		}
		
		return (s);
	}
	
	/**
	 * Helper method for the creation of double arrays for 
	 * @param s
	 * @param x
	 * @return
	 */
	private static double [] createArrayFromSeries (XYSeries s, boolean x) {
		double [] arr = new double [s.getItemCount ()];
		
		if (x) {
			for (int i = 0; (i < s.getItemCount ()); ++i) {
				arr[i] = (Double) s.getX (i);
			}
			
		} else { // y
			for (int i = 0; (i < s.getItemCount ()); ++i) {
				arr[i] = (Double) s.getY (i);
			}
		}
		
		return (arr);
	}
	
	/**
	 * Graphs the original and interpolated data.
	 * Places both data sets into the same XYSeriesCollection and gives the collection
	 * to XYGraph to graph it.
	 * 
	 * @param interpolation_dataset
	 * @param interpolated_dataset
	 * @param t
	 */
	private static void graphInterpolation (XYSeries interpolation_dataset,
			XYSeries interpolated_dataset, Template t) {
		// Combine the two datasets into an XYSeriesCollection
		XYSeriesCollection graph_data = new XYSeriesCollection ();
		graph_data.addSeries (interpolation_dataset);
		graph_data.addSeries (interpolated_dataset);
		
		//System.out.println (printXYSeries (interpolation_dataset));
		//System.out.println (printXYSeries (interpolated_dataset));
		// Graph Interpolation and its original data.
		// TODO: Make new Constructor in XYGraph for XYSeriesCollection and Template.
		XYGraph graph = new XYGraph (t, graph_data);
		graph.pack ();
		graph.setVisible (true);
	}
    
	/**
	 * Getter Method. Provides the series key for the interpolated data graphs.
	 * 
	 * @param t Template containing the original data's chart name.
	 * @return A string, containing the name of the new series.
	 */
    private static String getSeriesKey (Template t) {
    	return ("Fitted Regression of " +  t.getChartName ());
    }
    
    /**
     * Provides a window that states the R-Squared value for the interpolation
     * and if it matches with the standard table's values for the 
     * 99%, 98%, 95%, and 90% CI.
     * 
     * @param r_squared The Correlation Coefficient, obtained by squaring the
     * Pearson Coefficient.
     */
    private static void showRegressionValidity (double r, int number_of_points) {
    	StringBuilder sb = new StringBuilder ();
    	
    	sb.append ("Graph's R Value: ").append (r).append ("\n");
    	sb.append (DataConfidence.provideCIValidity (r, number_of_points));
    	
    	JOptionPane.showConfirmDialog (null, sb.toString (), 
    			"Interpolation Validity Check", JOptionPane.OK_OPTION);
    }

	/**
	 * Testing method.
	 * Prints the contents of an XYSeries via a StringBuilder collecting
	 * the contents of the entire series.
	 * 
	 * @param s The XYSeries to print out.
	 * @return A String with the formatted contents of the entire XYSeries
	 */
	public static String printXYSeries (XYSeries s) {
		StringBuilder sb = new StringBuilder ();
		
		// Get both row and col indexes and start transferring data to the matrix.
		for (int i = 0; (i < s.getItemCount ()); ++i) {
			sb.append ("Pair ").append (i + 1).append (": (").append (s.getX (i)).append (", ").append (s.getY (i)).append (")\n");
		}
		
		return (sb.toString ());
	}
}
