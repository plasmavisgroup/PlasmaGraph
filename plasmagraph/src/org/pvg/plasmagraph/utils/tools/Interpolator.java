package org.pvg.plasmagraph.utils.tools;

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
    	// TODO: Make XYGraphDataset function provide an XYSeries, instead.
        XYSeries interpolation_dataset = ds.toXYGraphDataset ();
        
        // Check which of the different regressions you'll be doing.
        XYSeries interpolated_dataset =  getRegression (interpolation_dataset, t);
        
        // Graph it!
        graphInterpolation (interpolation_dataset, interpolated_dataset, t);
        
    }

    /**W
     * 
     * @param interpolation_dataset
     * @param t
     * @return
     */
    // TODO: Implement "lower_interval", "upper_interval", and "interpolation_interval"
    // in Template, and add to a View.
	private static XYSeries getRegression (XYSeries interpolation_dataset, Template t) {
    	double [] regression; XYSeries result;
    	
    	DefaultXYDataset regression_set = new DefaultXYDataset ();
    	regression_set.addSeries (interpolation_dataset.getKey (),
    			interpolation_dataset.toArray ());
    	
    	// Linear?
    	if (t.getInterpolationType ().equals (InterpolationType.LINEAR)) {
        	
    		regression = org.jfree.data.statistics.Regression.
        			getOLSRegression (regression_set, 0);
    		
    		result = DatasetUtilities.sampleFunction2DToSeries
    				(new LineFunction2D (regression[0], regression[1]),
    						t.getLowerInterval (), t.getUpperInterval (),
    						t.getInterpolationInterval (), getSeriesKey(t));
        	
        } 
    	// Quadratic - Polynomial
    	else if (t.getInterpolationType ().equals (InterpolationType.QUADRATIC)) {
        	
        	regression = org.jfree.data.statistics.Regression.
        			getPolynomialRegression (regression_set, 0, 2);
        	
        	result = DatasetUtilities.sampleFunction2DToSeries
        			(new PolynomialFunction2D
        				(new double [] {regression[0], regression[1], regression[2]}),
        			t.getLowerInterval (), t.getUpperInterval (),
					t.getInterpolationInterval (), getSeriesKey(t));
        	
        }  else if (t.getInterpolationType ().equals (InterpolationType.CUBIC)) {
        	
        	regression = org.jfree.data.statistics.Regression.
        			getPolynomialRegression (regression_set, 0, 3);
        	
        	result = DatasetUtilities.sampleFunction2DToSeries
        			(new PolynomialFunction2D
        					(new double [] {regression[0], regression[1], regression[2], regression[3]}),
        			t.getLowerInterval (), t.getUpperInterval (),
					t.getInterpolationInterval (), getSeriesKey(t));
        	
        }  else if (t.getInterpolationType ().equals (InterpolationType.QUARTIC)) {
        	
        	regression = org.jfree.data.statistics.Regression.
        			getPolynomialRegression (regression_set, 0, 4);
        	
        	result = DatasetUtilities.sampleFunction2DToSeries
        			(new PolynomialFunction2D
        					(new double [] {regression[0], regression[1], regression[2], regression[3], regression[4]}),
        			t.getLowerInterval (), t.getUpperInterval (),
					t.getInterpolationInterval (), getSeriesKey(t));
        	
        } else { // if (t.getInterpolationType ().equals (InterpolationType.POWER))
        	
        	regression = org.jfree.data.statistics.Regression.
        			getPowerRegression (regression_set, 0);
        	
        	result = DatasetUtilities.sampleFunction2DToSeries
        			(new PowerFunction2D (regression[0], regression[1]),
        			t.getLowerInterval (), t.getUpperInterval (),
					t.getInterpolationInterval (), getSeriesKey(t));
        	
        }
    	
		return (result);
    	
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
		XYGraph graph = new XYGraph (graph_data, t);
	}
    
	/**
	 * 
	 * @param t
	 * @return
	 */
    private static String getSeriesKey (Template t) {
    	return ("Fitted Regression of " +  t.getChartName ());
    }
    
    /**
     * 
     */
    private static void showRegressionValidity () {
    	
    }
    
    /**
     * 
     * @return
     */
    private static double calculateLinearRSquared () {
    	
    }
    
    /**
     * 
     * @return
     */
    private static double calculatePowerRSquared () {
    	
    }
    
}
