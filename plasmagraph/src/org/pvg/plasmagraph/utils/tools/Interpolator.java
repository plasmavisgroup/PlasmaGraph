package org.pvg.plasmagraph.utils.tools;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.jfree.data.function.*;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.template.InterpolationType;

public class Interpolator {
  
	
	/**
	 * External path to interpolate data and graph said data.
	 * 
	 * @param ds
	 * @param t
	 */
    public static void interpolate (DataSet ds, Template t) {

        // Check which of the different regressions you'll be doing.
        XYSeries interpolated_dataset = getInterpolation (ds, t);
        
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
    	double [] regression; XYSeries result; double r_squared;
    	
    	XYSeries interpolation_dataset = ds.toXYGraphDataset ();
    	DefaultXYDataset regression_set = new DefaultXYDataset ();
    	regression_set.addSeries (interpolation_dataset.getKey (),
    			interpolation_dataset.toArray ());
    	
    	// Perform the regression and get the dataset out of it, depending on the type
    	// of regression to perform.
    	if (t.getInterpolationType ().equals (InterpolationType.LINEAR)) {
        	
    		regression = org.jfree.data.statistics.Regression.
        			getPolynomialRegression (regression_set, 0, 1);
    		
    		result = DatasetUtilities.sampleFunction2DToSeries
    				(new PolynomialFunction2D 
    						(new double [] {regression[0], regression[1]}),
    						t.getLowerInterval (), t.getUpperInterval (),
    						t.getInterpolationInterval (), getSeriesKey(t));
    		
    		// TODO: Change to Apache CommonsMath's PearsonCorrelation.
    		r_squared = regression[2];
        	
        } else if (t.getInterpolationType ().equals (InterpolationType.QUADRATIC)) {
        	
        	regression = org.jfree.data.statistics.Regression.
        			getPolynomialRegression (regression_set, 0, 2);
        	
        	result = DatasetUtilities.sampleFunction2DToSeries
        			(new PolynomialFunction2D
        				(new double [] {regression[0], regression[1], regression[2]}),
        			t.getLowerInterval (), t.getUpperInterval (),
					t.getInterpolationInterval (), getSeriesKey(t));
        	
        	// TODO: Change to Apache CommonsMath's PearsonCorrelation.
        	r_squared = regression[3];
        	
        }  else if (t.getInterpolationType ().equals (InterpolationType.CUBIC)) {
        	
        	regression = org.jfree.data.statistics.Regression.
        			getPolynomialRegression (regression_set, 0, 3);
        	
        	result = DatasetUtilities.sampleFunction2DToSeries
        			(new PolynomialFunction2D
        					(new double [] {regression[0], regression[1], regression[2], regression[3]}),
        			t.getLowerInterval (), t.getUpperInterval (),
					t.getInterpolationInterval (), getSeriesKey(t));
        	
        	// TODO: Change to Apache CommonsMath's PearsonCorrelation.
        	r_squared = regression[4];
        	
        }  else if (t.getInterpolationType ().equals (InterpolationType.QUARTIC)) {
        	
        	regression = org.jfree.data.statistics.Regression.
        			getPolynomialRegression (regression_set, 0, 4);
        	
        	result = DatasetUtilities.sampleFunction2DToSeries
        			(new PolynomialFunction2D
        					(new double [] {regression[0], regression[1], regression[2], regression[3], regression[4]}),
        			t.getLowerInterval (), t.getUpperInterval (),
					t.getInterpolationInterval (), getSeriesKey(t));
        	
        	// TODO: Change to Apache CommonsMath's PearsonCorrelation.
        	r_squared = regression[5];
        	
        } else { // if (t.getInterpolationType ().equals (InterpolationType.SPLINE))
        	// Get Function to create data.
        	
        	SplineInterpolator spline = new SplineInterpolator ();
        	PolynomialSplineFunction func = spline.interpolate
        			 (ds.get (0).toArray (), ds.get (1).toArray ());
        	 
        	// Create data from function.
        	result = createSeries (func, t);
        	
        	// Obtain r_squared value from data.
        	PearsonsCorrelation p_correlation = new PearsonsCorrelation ();
        	r_squared = p_correlation.correlation (createArrayFromSeries (result, true), 
        			createArrayFromSeries (result, false));
        	 
        	 
        }
    	
    	showRegressionValidity (r_squared);
    	
		return (result);
    }
	
	/**
	 * Provides an XYSeries via an Apache Commons Math Interpolation function.
	 * 
	 * @param func PolynomialSplineFunction which will generate y values.
	 * @param t Template object that contains interval bounds and interval.
	 * @return An XYSeries provided by the PolynomialSplineFunction.
	 */
	private static XYSeries createSeries (PolynomialSplineFunction func, Template t) {
		XYSeries s = new XYSeries ("Interpolation");
		double interval = t.getUpperInterval () - t.getLowerInterval ();
		double x_value;
		
		for (int i = 0; (i < t.getInterpolationInterval ()); ++i) {
			x_value = (i * interval) + t.getLowerInterval ();
			
			s.add (x_value, func.value (x_value));
		}
		
		return (s);
	}
	
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
		
		// Graph Interpolation and its original data.
		// TODO: Make new Constructor in XYGraph for XYSeriesCollection and Template.
		XYGraph graph = new XYGraph (t, graph_data);
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
     * 
     */
    private static void showRegressionValidity (double r_squared) {
    	
    }
}
