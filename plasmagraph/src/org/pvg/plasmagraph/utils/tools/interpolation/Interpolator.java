package org.pvg.plasmagraph.utils.tools.interpolation;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.data.function.*;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.pvg.plasmagraph.utils.JFreeChartUtilities;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.graphs.Graph;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.DataConfidence;
import org.pvg.plasmagraph.utils.types.ColumnType;
import org.pvg.plasmagraph.utils.types.InterpolationType;

/**
 * TODO
 * 
 * @author Gerardo A. Navas Morales
 */
public class Interpolator {
	
	/** Reference to Header Data object. */
	DataSet ds;
	/** Reference to DataReference object. */
	DataReference dr;
	/** Reference to Template object. */
	Template t;
	/** Value of the R or adjusted R^2 value. */
	private double r_container;
	/** Flag defining the "r_value" variable's contents: either the R or R^2 
	 * value of the interpolation. */
	private boolean is_r_squared;
	/** Container for a polynomial function as per JFreeChart's methods. */
	private PolynomialFunction2D f_polynomial;
	/**Container for a polynomial spline function as per Apache's Common Math methods. */
	private PolynomialSplineFunction f_spline;
	
	/**
	 * Class constructor. Creates a new Interpolator object and has it automatically
	 * start processing the data.
	 * 
	 * @param hd The Headers to use for the interpolation.
	 * @param t The settings the DataSet is based upon.
	 * @param dr The DataReference object containing all the pairs to interpolate. 
	 */
	public Interpolator (HeaderData hd, Template t, DataReference dr) {
		
		if (!hd.hasValidGraphTypes (t.getChartType (), dr.get ())) {
    		
    		// TODO Better reporting than this, please!
    		JOptionPane.showMessageDialog (null, 
    				"Error: Incorrect column types for interpolation.");
    		
    	} else {
    		
    		this.ds = hd.populateData (dr.get ());
    		this.dr = dr;
    		this.t = t;
    		
    		this.is_r_squared = false;
    		this.r_container = 0.0;
    		this.f_polynomial = null;
    		this.f_spline = null;
    		
    	}
	}
	
	/**
	 * Class constructor. Creates a new Interpolator object and has it automatically
	 * start processing the data.
	 * 
	 * @param ds The DataSet to use for the interpolation.
	 * @param t The settings the DataSet is based upon.
	 * @param dr The DataReference object containing all the pairs to interpolate. 
	 */
	public Interpolator (DataSet ds, Template t, DataReference dr) {
		this.ds = ds;
		this.dr = dr;
		this.t = t;
		
		this.is_r_squared = false;
		this.r_container = 0.0;
		this.f_polynomial = null;
		this.f_spline = null;
	}
	
	/**
	 * External path to interpolate data and graph said data.
	 * 
	 * @return An XYGraph object containing an interpolated chart.
	 */
    public Graph interpolate () {
    	
    	if (!(ColumnType.DOUBLE.toString ().equals (ds.getX ().getType ()) &&
    			(ColumnType.DOUBLE.toString ().equals (ds.getY ().getType ())))) {
    		
    		// TODO Better reporting than this, please!
    		JOptionPane.showMessageDialog (null, 
    				"Error: Incorrect column types for interpolation.");
    		
    		return (null);
    		
    	} else {

        	if (dr.get ().isGrouped ()) {
        		
        		// Get all of the XYSeries to use for this procedure.
        		XYSeriesCollection grouped_sets = ds.toGroupedXYGraphDataset (dr.get ());
        		XYSeriesCollection grouped_interpolations = new XYSeriesCollection ();
        		
        		// Figure out the lower / upper boundaries of the interpolation!
        		this.t.setLowerInterval (grouped_sets.getDomainLowerBound (false));
            	this.t.setUpperInterval (grouped_sets.getDomainUpperBound (false));
        		
        		// Make each individual interpolation!
        		for (Object s : grouped_sets.getSeries ()) {
        			// TODO: CAST TO XYSERIES OR FIND A WAY TO AUTOCAST IT.
        			XYSeries set = (XYSeries) s;
        			
        			grouped_interpolations.addSeries (getInterpolation (set));
        		}
        		
        		// Combine both Collections, and then return an XYGraph of them all!
        		for (Object s : grouped_interpolations.getSeries ()) {
        			grouped_sets.addSeries ((XYSeries) s);
        		}
        		
        		return (new XYGraph (t, grouped_sets, dr.get ()));
        		
        	} else {
        	
        		XYSeries original_dataset = ds.toXYGraphDataset (this.dr.get ());
        		
        		// Figure out the lower / upper boundaries of the interpolation!
        		this.t.setLowerInterval (original_dataset.getMinX ());
            	this.t.setUpperInterval (original_dataset.getMaxX ());
        		
        		// Check which of the different regressions you'll be doing.
    	        XYSeries interpolated_dataset = getInterpolation ();
    	        
    	        // Graph it!
    	        
    	        return (createDataset (original_dataset, 
    	        		interpolated_dataset, t, this.dr.get ()));
        		
        	}
    	}

    }

    private XYSeries getInterpolation (XYSeries set) {

    	//======================================================================================//
    	// Perform the regression and get the dataset out of it, depending on the type
    	// of regression to perform.
    	if (t.getInterpolationType ().equals (InterpolationType.LINEAR)) {
    		
    		return (this.createLinearInterpolation (set));
        	
        } //======================================================================================//
    	else if (t.getInterpolationType ().equals (InterpolationType.QUADRATIC)) {
    		
    		return (this.createQuadraticInterpolation (set));
        	
        }  //======================================================================================//
        else if (t.getInterpolationType ().equals (InterpolationType.CUBIC)) {
        	
        	return (this.createCubicInterpolation (set));
        	
        }  //======================================================================================// 
        else if (t.getInterpolationType ().equals (InterpolationType.QUARTIC)) {

        	return (this.createQuarticInterpolation (set));
        	
        } //======================================================================================//
    	else { // if (t.getInterpolationType ().equals (InterpolationType.SPLINE))
    		
    		return (this.createSplineInterpolation (set));
        	
        }  //======================================================================================//
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
	private XYSeries getInterpolation () {

    	//======================================================================================//
    	// Perform the regression and get the dataset out of it, depending on the type
    	// of regression to perform.
    	if (t.getInterpolationType ().equals (InterpolationType.LINEAR)) {
    		
    		return (this.createLinearInterpolation ());
        	
        } //======================================================================================//
    	else if (t.getInterpolationType ().equals (InterpolationType.QUADRATIC)) {
    		
    		return (this.createQuadraticInterpolation ());
        	
        }  //======================================================================================//
        else if (t.getInterpolationType ().equals (InterpolationType.CUBIC)) {
        	
        	return (this.createCubicInterpolation ());
        	
        }  //======================================================================================// 
        else if (t.getInterpolationType ().equals (InterpolationType.QUARTIC)) {

        	return (this.createQuarticInterpolation ());
        	
        } //======================================================================================//
    	else { // if (t.getInterpolationType ().equals (InterpolationType.SPLINE))
    		
    		return (this.createSplineInterpolation ());
        	
        }  //======================================================================================//
    }

	
	// ================================================================== //
	// Internal processing methods.
	// ================================================================== //
	// Ungrouped methods.
	// ================================================================== //
	private XYSeries createLinearInterpolation () {
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		
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
					t.getLowerInterval (), t.getUpperInterval (), t.getInterpolationInterval (), "Interpolation of " + dr.get ().getName ());
		} else {
			regression_dataset = DatasetUtilities.sampleFunction2DToSeries (new LineFunction2D (0.0, regression_params[1]),
					t.getLowerInterval (), t.getUpperInterval (), t.getInterpolationInterval (), "Interpolation of " + dr.get ().getName ());
		}
		
		// Obtain and show the R value.
		this.is_r_squared = false;
		this.r_container = linear_regression.getR ();
		
		return (regression_dataset);
	}
	
	private XYSeries createQuadraticInterpolation () {
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		
		// XYDataset container for some JFree operations.
    	XYSeriesCollection regression_set = 
    			new XYSeriesCollection (ds.toXYGraphDataset (dr.get ()));
    	
		regression_params = org.jfree.data.statistics.Regression.
    			getPolynomialRegression (regression_set, 0, 2);
		PolynomialFunction2D func = new PolynomialFunction2D (new double [] 
    			{regression_params[0], regression_params[1], 
    			regression_params[2]});
		
    	regression_dataset = DatasetUtilities.sampleFunction2DToSeries
    			(func, t.getLowerInterval (), t.getUpperInterval (),
				t.getInterpolationInterval (), this.getSeriesKey (regression_set.getSeries (0)));
    	
    	// Obtain and show the R-squared value.
    	this.is_r_squared = true;
    	this.f_polynomial = func;
		
		return (regression_dataset);
	}
	
	private XYSeries createCubicInterpolation () {
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		
		// XYDataset container for some JFree operations.
    	XYSeriesCollection regression_set = 
    			new XYSeriesCollection (ds.toXYGraphDataset (dr.get ()));
    	
    	regression_params = org.jfree.data.statistics.Regression.
    			getPolynomialRegression (regression_set, 0, 3);
    	PolynomialFunction2D func = new PolynomialFunction2D (new double [] 
    			{regression_params[0], regression_params[1], 
    			regression_params[2], regression_params[3]});
    	
    	regression_dataset = DatasetUtilities.sampleFunction2DToSeries
    			(func, t.getLowerInterval (), t.getUpperInterval (),
				t.getInterpolationInterval (), this.getSeriesKey (regression_set.getSeries (0)));
    	
    	// Prepare the R-squared value for displaying.
    	this.is_r_squared = true;
    	this.f_polynomial = func;
		
		return (regression_dataset);
	}
	
	private XYSeries createQuarticInterpolation () {
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		
		// XYDataset container for some JFree operations.
    	XYSeriesCollection regression_set = 
    			new XYSeriesCollection (ds.toXYGraphDataset (dr.get ()));
    	
    	regression_params = org.jfree.data.statistics.Regression.
    			getPolynomialRegression (regression_set, 0, 4);
    	PolynomialFunction2D func = new PolynomialFunction2D (new double [] 
    			{regression_params[0], regression_params[1], 
    			regression_params[2], regression_params[3], 
    			regression_params[4]});
    	
    	regression_dataset = DatasetUtilities.sampleFunction2DToSeries
    			(func, t.getLowerInterval (), t.getUpperInterval (),
				t.getInterpolationInterval (), this.getSeriesKey (regression_set.getSeries (0)));
    	
    	// Prepare the R-squared value for displaying.
    	this.is_r_squared = true;
    	this.f_polynomial = func;
		
		return (regression_dataset);
	}
	
	private XYSeries createSplineInterpolation () {
		// Return container.
		XYSeries regression_dataset;
		
		// Prepare data.
		double [] x_column = new double [ds.getColumnLength ()];
		double [] y_column = new double [ds.getColumnLength ()];
		ds.orderData (x_column, y_column);
		
		// Test: Show what the hell the columns contain.
		//for (int i = 0; (i < x_column.length); ++i) {
		//	System.out.println ("(" + x_column[i] + ", " + y_column[i] + ")");
		//}
		
    	// Get Function to create data.
    	SplineInterpolator spline = new SplineInterpolator ();
    	PolynomialSplineFunction func = spline.interpolate (x_column, y_column);
    	
    	// Prepare template for minimum and maximum bounds.
    	t.setLowerInterval (func.getKnots ()[0]);
    	t.setUpperInterval (func.getKnots ()[func.getKnots ().length - 1]);
    	 
    	// Create data from function.
    	regression_dataset = createSeries (func, t, dr.get ().getName ());
    	
    	for (int i = 0; (i < regression_dataset.getItemCount ()); ++i) {
    		System.out.println ("(" + regression_dataset.getX (i) + ", " + 
    							regression_dataset.getY (i) + ")");
    	}
    	
    	
    	// Prepare the R-squared value for displaying.
    	this.is_r_squared = true;
    	this.f_spline = func;
    	
    	
    	return (regression_dataset);
	}
	
	// ================================================================== //
	// Grouped methods.
	// ================================================================== //
	private XYSeries createLinearInterpolation (XYSeries group) {
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		
		RealMatrix m = MatrixUtils.createRealMatrix (group.toArray ());
		
		// Create the double [][] container and regress it.
		SimpleRegression linear_regression = new SimpleRegression (true);
		linear_regression.addData (m.transpose ().getData ());
		RegressionResults lin_regression = linear_regression.regress ();
		
		// Obtain the parameters.
		regression_params = lin_regression.getParameterEstimates ();
		
		// Create an XYSeries based on those parameters.
		// Note that, sometimes, there is no intercept! Check for it!
		if (lin_regression.hasIntercept ()) {
			regression_dataset = DatasetUtilities.sampleFunction2DToSeries (new LineFunction2D (regression_params[0], regression_params[1]),
					t.getLowerInterval (), t.getUpperInterval (), t.getInterpolationInterval (), this.getSeriesKey (group));
		} else {
			regression_dataset = DatasetUtilities.sampleFunction2DToSeries (new LineFunction2D (0.0, regression_params[1]),
					t.getLowerInterval (), t.getUpperInterval (), t.getInterpolationInterval (), this.getSeriesKey (group));
		}
		
		// Obtain and show the R value.
		this.is_r_squared = false;
		this.r_container = linear_regression.getR ();
		
		return (regression_dataset);
	}
	
	private XYSeries createQuadraticInterpolation (XYSeries group) {
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		
		// XYDataset container for some JFree operations.
    	XYSeriesCollection regression_set = new XYSeriesCollection (group);
    	
		regression_params = org.jfree.data.statistics.Regression.
    			getPolynomialRegression (regression_set, 0, 2);
		PolynomialFunction2D func = new PolynomialFunction2D (new double [] 
    			{regression_params[0], regression_params[1], 
    			regression_params[2]});
		
    	regression_dataset = DatasetUtilities.sampleFunction2DToSeries
    			(func, t.getLowerInterval (), t.getUpperInterval (),
				t.getInterpolationInterval (), this.getSeriesKey (group));
    	
    	// Obtain and show the R-squared value.
    	this.is_r_squared = true;
    	this.f_polynomial = func;
		
		return (regression_dataset);
	}
	
	private XYSeries createCubicInterpolation (XYSeries group) {
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		
		// XYDataset container for some JFree operations.
    	XYSeriesCollection regression_set = new XYSeriesCollection (group);
    	
    	regression_params = org.jfree.data.statistics.Regression.
    			getPolynomialRegression (regression_set, 0, 3);
    	PolynomialFunction2D func = new PolynomialFunction2D (new double [] 
    			{regression_params[0], regression_params[1], 
    			regression_params[2], regression_params[3]});
    	
    	regression_dataset = DatasetUtilities.sampleFunction2DToSeries
    			(func, t.getLowerInterval (), t.getUpperInterval (),
				t.getInterpolationInterval (), this.getSeriesKey (group));
    	
    	// Prepare the R-squared value for displaying.
    	this.is_r_squared = true;
    	this.f_polynomial = func;
		
		return (regression_dataset);
	}
	
	private XYSeries createQuarticInterpolation (XYSeries group) {
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		
		// XYDataset container for some JFree operations.
    	XYSeriesCollection regression_set = new XYSeriesCollection (group);
    	
    	regression_params = org.jfree.data.statistics.Regression.
    			getPolynomialRegression (regression_set, 0, 4);
    	PolynomialFunction2D func = new PolynomialFunction2D (new double [] 
    			{regression_params[0], regression_params[1], 
    			regression_params[2], regression_params[3], 
    			regression_params[4]});
    	
    	regression_dataset = DatasetUtilities.sampleFunction2DToSeries
    			(func, t.getLowerInterval (), t.getUpperInterval (),
				t.getInterpolationInterval (), this.getSeriesKey (group));
    	
    	// Prepare the R-squared value for displaying.
    	this.is_r_squared = true;
    	this.f_polynomial = func;
		
		return (regression_dataset);
	}
	
	private XYSeries createSplineInterpolation (XYSeries group) {
		// Return container.
		XYSeries regression_dataset;
		
		// Prepare data.
		double [] x_column = new double [group.getItemCount ()];
		double [] y_column = new double [group.getItemCount ()];
		this.orderData (group, x_column, y_column);
		
		// Test: Show what the hell the columns contain.
		//for (int i = 0; (i < x_column.length); ++i) {
		//	System.out.println ("(" + x_column[i] + ", " + y_column[i] + ")");
		//}
		
    	// Get Function to create data.
    	SplineInterpolator spline = new SplineInterpolator ();
    	PolynomialSplineFunction func = spline.interpolate (x_column, y_column);
    	
    	// Prepare template for minimum and maximum bounds.
    	t.setLowerInterval (func.getKnots ()[0]);
    	t.setUpperInterval (func.getKnots ()[func.getKnots ().length - 1]);
    	 
    	// Create data from function.
    	regression_dataset = createSeries (func, t, this.getSeriesKey (group));
    	
    	for (int i = 0; (i < regression_dataset.getItemCount ()); ++i) {
    		System.out.println ("(" + regression_dataset.getX (i) + ", " + 
    							regression_dataset.getY (i) + ")");
    	}
    	
    	
    	// Prepare the R-squared value for displaying.
    	this.is_r_squared = true;
    	this.f_spline = func;
    	
    	
    	return (regression_dataset);
	}
	
	// ================================================================== //
	
	private void orderData (XYSeries group, double [] x_column,
			double [] y_column) {

		// Create the data column map.
		final Map<Double, Double> map = new HashMap <Double, Double> ();
		
		// Populate the map.
		for (int i = 0; (i < group.getItemCount ()); ++i) {
			map.put ((Double) group.getX (i), (Double) group.getY (i));
		}
		
		// Create the sorting map.
		Map <Double, Double> sorted_map = new TreeMap <Double, Double> (
				new Comparator <Double> () {
	        public int compare (Double o1, Double o2) {
	            return o1.compareTo (o2);
	        }
	    });
		
		// Include the data column map into the sorting map and let it sort automatically.
		sorted_map.putAll(map);
	    
	    // Now, pull the data out into the arrays.
		Iterator <Map.Entry <Double, Double>> row_iterator = sorted_map.entrySet ().iterator ();
		Map.Entry <Double, Double> row;
		
		// For each value in the sorted map, put them in their respective arrays.
		for (int i = 0; (i < sorted_map.size ()); ++i) {
			row = row_iterator.next ();
			
			x_column [i] = row.getKey ();
			y_column [i] = row.getValue ();
	    }
		
	}

	/**
	 * Provides an XYSeries via an PolynomialSplineFunction's result value-generation function.
	 * 
	 * @param func PolynomialSplineFunction which will generate y values.
	 * @param t Template object that contains interval bounds and interval.
	 * @param name The name of the XYSeries to return.
	 * @return An XYSeries provided by the PolynomialSplineFunction.
	 * 
	 * TODO: Should include ArithmeticException checking due to BigDecimal calculations.
	 */
	private XYSeries createSeries (PolynomialSplineFunction func, Template t, String name) {
		XYSeries s = new XYSeries (name);
		
		//t.setInterpolationInterval ((int) (500 * (t.getUpperInterval () - t.getLowerInterval ())));
		
		BigDecimal lower = new BigDecimal (t.getLowerInterval ());
		BigDecimal x = new BigDecimal (t.getLowerInterval ()); 
		BigDecimal delta = new BigDecimal (t.getUpperInterval ()).subtract (x);
		BigDecimal interval = BigDecimal.ONE.divide (new BigDecimal (t.getInterpolationInterval ()));
		
		//System.out.println (interval.toString ());
		
		// Insert the lowest value and its result.
		// Insert every value that isn't the lowest or highest values and their results.
		for (int i = 0; (i < t.getInterpolationInterval ()); ++i) {
			
			x = lower.add (delta.multiply (interval.multiply (new BigDecimal (i))));
			
			s.add (x.doubleValue (), func.value (x.doubleValue ()));
		}
		
		// Return the series.
		return (s);
	}
	
	/**
	 * Graphs the original and interpolated data.
	 * Places both data sets into the same XYSeriesCollection and gives the collection
	 * to XYGraph to graph it.
	 * 
	 * @param interpolation_dataset
	 * @param interpolated_dataset
	 * @param t
	 * @param p 
	 */
	private XYGraph createDataset (XYSeries interpolation_dataset,
			XYSeries interpolated_dataset, Template t, GraphPair p) {
		
		// Combine the two datasets into an XYSeriesCollection
		XYSeriesCollection graph_data = new XYSeriesCollection ();
		graph_data.addSeries (interpolation_dataset);
		graph_data.addSeries (interpolated_dataset);
		
		// Graph Interpolation and its original data.
		return (new XYGraph (t, graph_data, p));
	}
    
	/**
	 * Getter Method. Provides the series key for the interpolated data graphs.
	 * 
	 * @param t Template containing the original data's chart name.
	 * @return A string, containing the name of the new series.
	 */
    private String getSeriesKey (XYSeries input_series) {
    	return ("Interpolation of " +  input_series.getKey ());
    }
    
    /**
     * Handles the displaying of Interpolation Validity dialogs.
     */
    public void showInterpolationValidity () {
    	
    	if (this.is_r_squared) {
    		
    		if (this.f_polynomial != null) {
    			
    			this.showRSquaredValidity (this.ds, this.f_polynomial);
    			
    		} else { // if (this.f_spline != null) { 
    			
    			this.showRSquaredValidity (this.ds, this.f_spline);
    			
    		}
    		
    	} else {
    		
    		this.showPearsonRValidity (this.r_container, ds.getColumnLength ());
    		
    	}
    	
    }
    
    /**
     * Provides a window that states the R value for the interpolation
     * and if it matches with the standard table's values for the 
     * 99%, 98%, 95%, and 90% CI.
     * 
     * @param r_squared The Correlation Coefficient, obtained by squaring the
     * Pearson Coefficient.
     */
    private void showPearsonRValidity (double r, int number_of_points) {
    	StringBuilder sb = new StringBuilder ();

    	sb.append ("Graph's R Value: ").append (r).append ("\n");
    	sb.append (DataConfidence.provideCIValidity (r, number_of_points));
    	
    	JOptionPane.showMessageDialog (null, sb.toString (), "Interpolation Validity Check", JOptionPane.WARNING_MESSAGE);
    	
    	
    }
    
    /**
     * Provides a window stating the R-squared value for the non-linear
     * regression performed.
     * For more information, please refer to: {@link http://www.graphpad.com/guides/prism/6/curve-fitting/index.htm?r2_ameasureofgoodness_of_fitoflinearregression.htm}
     * 
     * @param ds The DataSet to find an R-Squared value of.
     * @param f The PolynomialFunction2D used to calculate the residual Sum of Squares.
     */
    private void showRSquaredValidity (DataSet ds, PolynomialFunction2D f) {
		SummaryStatistics stat_generator = new SummaryStatistics ();
		
		// Get the Sum of Squares of the Residuals.
		for (Object o : ds.getY ()) {
			stat_generator.addValue (Math.abs (((double) o) - 
					f.getValue ((double) ds.getX ().get (ds.getY ().find (o)))));
		}
		double ss_res = stat_generator.getSumsq ();
		
		// Get the Sum of Squares of the Mean Line.
		double mean = stat_generator.getMean ();
		
		stat_generator.clear ();
		for (Object o : ds.get (1)) {
			stat_generator.addValue (Math.abs (((double) o) - mean));
		}
		
		double ss_tot = stat_generator.getSumsq ();
		
		int n = ds.getColumnLength ();
		int k = ds.getNumParameters ();
		
		// Prepare the message
		StringBuilder sb = new StringBuilder ();
		
		sb.append ("The R-Squared value for this regression is: ").
			append (1 - ((ss_res / (n - k)) / (ss_tot / (n - 1))));
		
		// Show the dialog.
		JOptionPane.showMessageDialog (null, sb.toString (), "R-Squared Calculation", JOptionPane.INFORMATION_MESSAGE);
		
	}
    
    /**
     * Provides a window stating the R-squared value for the non-linear
     * regression performed.
     * For more information, please refer to: {@link http://www.graphpad.com/guides/prism/6/curve-fitting/index.htm?r2_ameasureofgoodness_of_fitoflinearregression.htm}
     * 
     * @param ds The DataSet to find an R-Squared value of.
     * @param f The PolynomialSplineFunction used to calculate the residual Sum of Squares.
     */
    private void showRSquaredValidity (DataSet ds, PolynomialSplineFunction f) {
		SummaryStatistics stat_generator = new SummaryStatistics ();
		
		// Get the Sum of Squares of the Residuals.
		for (Object o : ds.getY ()) {
			stat_generator.addValue (Math.abs (((double) o) - 
					f.value ((double) ds.getX ().get (ds.getY ().find (o)))));
		}
		double ss_res = stat_generator.getSumsq ();
		
		// Get the Sum of Squares of the Mean Line.
		double mean = stat_generator.getMean ();
		
		stat_generator.clear ();
		for (Object o : ds.get (1)) {
			stat_generator.addValue (Math.abs (((double) o) - mean));
		}
		
		double ss_tot = stat_generator.getSumsq ();
		
		int n = ds.getColumnLength ();
		int k = ds.getNumParameters ();
		
		// Prepare the message
		StringBuilder sb = new StringBuilder ();
		
		sb.append ("The R-Squared value for this regression is: ").
			append (1 - ((ss_res / (n - k)) / (ss_tot / (n - 1))));
		
		// Show the dialog.
		JOptionPane.showMessageDialog (null, sb.toString (), "R-Squared Calculation", JOptionPane.INFORMATION_MESSAGE);
		
	}
}
