package org.pvg.plasmagraph.utils.tools.interpolation;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.Pair;
import org.jfree.data.function.*;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.InvalidParametersException;
import org.pvg.plasmagraph.utils.graphs.Graph;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.DataConfidence;
import org.pvg.plasmagraph.utils.types.InterpolationType;

/**
 * TODO
 * 
 * @author Gerardo A. Navas Morales
 */
public class Interpolator {
	
	/** Reference to Header Data object. */
	private DataSet ds;
	/** Reference to GraphPair object. */
	private GraphPair p;
	/** Reference to Template object. */
	private Template t;
	/** Value of the R or adjusted R^2 value. */
	private List <Pair <Double, Integer>> r_container;
	/** Names of each of the interpolation groups. */
	private List <String> r_container_names;
	/** Flag defining the "r_value" variable's contents: either the R or R^2 
	 * value of the interpolation. */
	private boolean is_r_squared;
	/** Integer counting the number of data sets that are uninterpolatable due to
	 * lack of data points in the set. */
	private int series_error_counter;
	
	/**
	 * Class constructor. Creates a new Interpolator object and has it automatically
	 * start processing the data.
	 * 
	 * @param hd The Headers to use for the interpolation.
	 * @param t The settings the DataSet is based upon.
	 * @param p The GraphPair object containing all the pairs to interpolate. 
	 */
	public Interpolator (HeaderData hd, Template t, GraphPair p) {
		
		if (!hd.hasValidGraphTypes (t.getChartType (), p)) {
    		
    		// TODO Better reporting than this, please!
    		JOptionPane.showMessageDialog (null, 
    				"Error: Incorrect column types for interpolation.");
    		
    	} else {
    		
    		this.ds = hd.populateData (p);
    		this.p = p;
    		this.t = t;
    		
    		this.is_r_squared = false;
    		this.r_container = new ArrayList <> ();
    		this.r_container_names = new ArrayList <> ();
    		this.series_error_counter = 0;
    		
    	}
	}
	
	/**
	 * Class constructor. Creates a new Interpolator object and has it automatically
	 * start processing the data.
	 * 
	 * @param ds The DataSet to use for the interpolation.
	 * @param t The settings the DataSet is based upon.
	 * @param p The GraphPair object containing all the pairs to interpolate. 
	 */
	public Interpolator (DataSet ds, Template t, GraphPair p) {
		this.ds = ds;
		this.p = p;
		this.t = t;
		
		this.is_r_squared = false;
		this.r_container = new ArrayList <> ();
		this.r_container_names = new ArrayList <> ();
		this.series_error_counter = 0;
	}
	
	/**
	 * External path to interpolate data and graph said data.
	 * 
	 * @return An XYGraph object containing an interpolated chart.
	 * @throws InvalidParametersException Whenever an invalid column is found in this process.
	 */
    public Graph interpolate () throws InvalidParametersException {
    	XYGraph g;
    	int grouped_error_counter = 0;
    	
    	if (p.isGrouped ()) {
    		
    		// Get all of the XYSeries to use for this procedure.
    		XYSeriesCollection grouped_sets = ds.toGroupedXYGraphDataset (p);
    		XYSeriesCollection grouped_interpolations = new XYSeriesCollection ();
    		
    		// Make each individual interpolation!
    		for (int collection_counter = 0; collection_counter < grouped_sets.getSeriesCount (); ++collection_counter) {
    			XYSeries set = grouped_sets.getSeries (grouped_sets.getSeriesKey (collection_counter));
    			XYSeries interpolation = getInterpolation (set);
    			
    			// Get the name of the interpolation and add it to the name list!
    			this.r_container_names.add ((String) set.getKey ());
    			
    			if (set.equals (interpolation)) {
    				grouped_error_counter += 1;
    			}
    			if (interpolation != null) {
    				grouped_interpolations.addSeries (interpolation);
    			} else {
    				return (null);
    			}
    		}
    		
    		// Combine both Collections, and then return an XYGraph of them all!
    		for (Object s : grouped_interpolations.getSeries ()) {
    			if (s != null) {
    				grouped_sets.addSeries ((XYSeries) s);
    			}
    		}
    		
    		g = new XYGraph (t, grouped_sets, p, this.getInterpolationValidity ());
    		
    	} else {
    		
    		XYSeries original_dataset = ds.toXYGraphDataset (this.p);
    		
    		// Figure out the lower / upper boundaries of the interpolation!
    		this.t.setLowerInterval (original_dataset.getMinX ());
        	this.t.setUpperInterval (original_dataset.getMaxX ());
    		
    		// Check which of the different regressions you'll be doing.
	        XYSeries interpolated_dataset = this.getInterpolation ();
	        this.r_container_names.add (p.getName ());
	        
	        // Graph it!
	        g = this.createDataset (original_dataset, 
	        		interpolated_dataset, t, this.p);
    		
    	}
    	//if (t.isShowingInfoMessages ()) {
    		if (grouped_error_counter != 0) {
        		this.series_error_counter = grouped_error_counter;
        		
        		if (series_error_counter == 1) {
        			JOptionPane.showMessageDialog (null, 
        					"There was one data set that had less than three data points in it.\n"
            				+ "As such, said data set was not interpolated due to lack of data.\n");
        		} else {
        			JOptionPane.showMessageDialog (null, 
        					"There were " + this.series_error_counter 
            				+ " data sets that had less than three data points in them.\n"
            				+ "As such, said data sets were not interpolated due to lack of data.\n");
        		}
        	}
    	//}
    	
    	return (g);

    }

	private XYSeries getInterpolation (XYSeries set) {

    	if (set.getItemCount () > 2) {
    		
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
    	} else {
    		return (set);
    	}
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

		if (ds.getItemCount () > 2) {
		
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
		} else {
			return (ds.toXYGraphDataset (p));
		}
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
					t.getLowerInterval (), t.getUpperInterval (), t.getInterpolationInterval (), "Interpolation of " + p.getName ());
		} else {
			regression_dataset = DatasetUtilities.sampleFunction2DToSeries (new LineFunction2D (0.0, regression_params[1]),
					t.getLowerInterval (), t.getUpperInterval (), t.getInterpolationInterval (), "Interpolation of " + p.getName ());
		}
		
		// Obtain and show the R value.
		this.is_r_squared = false;
		this.r_container.add (new Pair <> (linear_regression.getR (), ds.size ()));
		
		return (regression_dataset);
	}
	
	private XYSeries createQuadraticInterpolation () {
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		
		// XYDataset container for some JFree operations.
    	XYSeriesCollection regression_set = 
    			new XYSeriesCollection (ds.toXYGraphDataset (p));
    	
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
    	this.r_container.add (new Pair <> (this.getRSquaredValidity (ds, func), ds.size ()));
		
		return (regression_dataset);
	}
	
	private XYSeries createCubicInterpolation () {
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		
		// XYDataset container for some JFree operations.
    	XYSeriesCollection regression_set = 
    			new XYSeriesCollection (ds.toXYGraphDataset (p));
    	
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
    	this.r_container.add (new Pair <> (this.getRSquaredValidity (ds, func), ds.size ()));
		
		return (regression_dataset);
	}
	
	private XYSeries createQuarticInterpolation () {
		// Return container.
		XYSeries regression_dataset;
		// Temporary regression container.
		double [] regression_params;
		
		// XYDataset container for some JFree operations.
    	XYSeriesCollection regression_set = 
    			new XYSeriesCollection (ds.toXYGraphDataset (p));
    	
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
    	this.r_container.add (new Pair <> (this.getRSquaredValidity (ds, func), ds.size ()));
		
		return (regression_dataset);
	}
	
	private XYSeries createSplineInterpolation () {
		try {
			// Return container.
			XYSeries regression_dataset;
			
			// Prepare data.
			double [] x_column = new double [ds.size ()];
			double [] y_column = new double [ds.size ()];
			ds.orderData (x_column, y_column);
			
			// Test: Show what the hell the columns contain.
			for (int i = 0; (i < x_column.length); ++i) {
				System.out.println ("(" + x_column[i] + ", " + y_column[i] + ")");
			}
			
	    	// Get Function to create data.
	    	SplineInterpolator spline = new SplineInterpolator ();
	    	PolynomialSplineFunction func = spline.interpolate (x_column, y_column);
	    	
	    	// Prepare template for minimum and maximum bounds.
	    	t.setLowerInterval (func.getKnots ()[0]);
	    	t.setUpperInterval (func.getKnots ()[func.getKnots ().length - 1]);
	    	 
	    	// Create data from function.
	    	regression_dataset = createSeries (func, t, "Interpolation");
	    	
	    	
	    	// Prepare the R-squared value for displaying.
	    	this.is_r_squared = true;
	    	this.r_container.add (new Pair <> (this.getRSquaredValidity (ds, func), ds.size ()));
	    	
	    	return (regression_dataset);
		} catch (NonMonotonicSequenceException ex)  {
			
			JOptionPane.showMessageDialog (null, "The Spline Interpolation of Graph " + 
							this.p.getName () + " cannot be completed\n"
							+ "due to multiple X values with the same number.\n"
							+ "Please correct the data manually before attempting again.");
			
			return (null);
			
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog (null, ex.getMessage (), 
					"Spline Interpolation Error", JOptionPane.ERROR_MESSAGE);
			
			return (null);
			
		}
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

		System.out.println (m.toString ());
		
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
					group.getMinX (), group.getMaxX (), t.getInterpolationInterval (), this.getSeriesKey (group));
		} else {
			regression_dataset = DatasetUtilities.sampleFunction2DToSeries (new LineFunction2D (0.0, regression_params[1]),
					t.getLowerInterval (), t.getUpperInterval (), t.getInterpolationInterval (), this.getSeriesKey (group));
		}
		
		// Obtain and show the R value.
		this.is_r_squared = false;
		this.r_container.add (new Pair <> (linear_regression.getR (), group.getItemCount ()));
		
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
    			(func, group.getMinX (), group.getMaxX (),
				t.getInterpolationInterval (), this.getSeriesKey (group));
    	
    	// Obtain and show the R-squared value.
    	this.is_r_squared = true;
    	this.r_container.add (new Pair <> (this.getRSquaredValidity (ds, func), group.getItemCount ()));
		
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
    			(func, group.getMinX (), group.getMaxX (),
				t.getInterpolationInterval (), this.getSeriesKey (group));
    	
    	// Prepare the R-squared value for displaying.
    	this.is_r_squared = true;
    	this.r_container.add (new Pair <> (this.getRSquaredValidity (ds, func), group.getItemCount ()));
		
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
    			(func, group.getMinX (), group.getMaxX (),
				t.getInterpolationInterval (), this.getSeriesKey (group));
    	
    	// Prepare the R-squared value for displaying.
    	this.is_r_squared = true;
    	this.r_container.add (new Pair <> (this.getRSquaredValidity (ds, func), group.getItemCount ()));
		
		return (regression_dataset);
	}
	
	private XYSeries createSplineInterpolation (XYSeries group) {
		try {
			// Return container.
			XYSeries regression_dataset;
			
			// Prepare data.
			double [] x_column = new double [group.getItemCount ()];
			double [] y_column = new double [group.getItemCount ()];
			this.orderData (group, x_column, y_column);
			
	    	// Get Function to create data.
	    	SplineInterpolator spline = new SplineInterpolator ();
	    	PolynomialSplineFunction func = spline.interpolate (x_column, y_column);
	    	
	    	// Prepare template for minimum and maximum bounds.
	    	t.setLowerInterval (group.getMinX ());
	    	t.setUpperInterval (group.getMaxX ());
	    	 
	    	// Create data from function.
	    	regression_dataset = createSeries (func, t, this.getSeriesKey (group));
	    	
	    	for (int i = 0; (i < regression_dataset.getItemCount ()); ++i) {
	    		System.out.println ("(" + regression_dataset.getX (i) + ", " + 
	    							regression_dataset.getY (i) + ")");
	    	}
	    	
	    	
	    	// Prepare the R-squared value for displaying.
	    	this.is_r_squared = true;
	    	this.r_container.add (new Pair <> (this.getRSquaredValidity (ds, func), group.getItemCount ()));
	    	
	    	return (regression_dataset);
		} catch (NonMonotonicSequenceException ex)  {
			
			JOptionPane.showMessageDialog (null, "The Spline Interpolation of Graph " + 
					group.getKey () + " cannot be completed\n"
					+ "due to multiple X values with the same number.\n"
					+ "Please correct the data manually before attempting again.");
			
			return null;
		} catch (Exception ex) {
			
			JOptionPane.showMessageDialog (null, ex.getMessage (), 
					"Spline Interpolation Error", JOptionPane.ERROR_MESSAGE);
			
			return (null);
			
		}
	}
	
	// ================================================================== //
	
	/**
     * Orders data based on the X column's values. Only used when grouping is included.<p>
     * Takes advantage of Arrays.sort (); method to sort the data.
     * 
     * @param x_column 
     * @param y_column 
     */
	private void orderData (XYSeries group, double [] x_column, double [] y_column) {

		// Sort the data in the "xy_column" based on the x column values.
		Object [] xy_array = group.getItems ().toArray ();
		Arrays.sort (xy_array, new Comparator <Object> () {
			
			@Override
			public int compare (Object o1, Object o2) {
				return (Double.compare (((XYDataItem) o1).getXValue (), 
						((XYDataItem) o2).getXValue ()));
			}
		});
		
		// Split the sorted data into two double arrays.
		for (int i = 0; (i < xy_array.length); ++i) {
			XYDataItem xy = (XYDataItem) xy_array[i];
			
			x_column[i] = xy.getXValue ();
			y_column[i] = xy.getYValue ();
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
		if (interpolated_dataset != null) {
			graph_data.addSeries (interpolated_dataset);
		}
		
		// Graph Interpolation and its original data.
		return (new XYGraph (t, graph_data, p, this.getInterpolationValidity ()));
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
    			
    		this.showRSquaredValidity ();
    		
    	} else {
    		
    		this.showRValidity ();
    		
    	}
    	
    }
    
    private String getInterpolationValidity () {
    	
    	if (this.is_r_squared) {
			
    		return (this.getRSquaredValidity ());
    		
    	} else {
    		
    		return (this.getRValidity ());
    		
    	}
    }
    
    /**
     * Provides a window that states the R value for the interpolation
     * and if it matches with the standard table's values for the 
     * 99%, 98%, 95%, and 90% CI.
     */
    private void showRValidity () {
    	if (!this.r_container.isEmpty ()) {
    		JOptionPane.showMessageDialog (null, this.getRValidity (), 
    				"Interpolation Validity Check", JOptionPane.WARNING_MESSAGE);
    	}
    }
    
    private String getRValidity () {
    	StringBuilder sb = new StringBuilder ();

    	// Set up the Number formatter.
    	DecimalFormat df = new DecimalFormat ();
    	df.setMaximumFractionDigits (4);
    	
    	sb.append ("R Values\n");
    	
    	for (int i = 0; (i < this.r_container.size ()); ++i) {
    		
    		String truncated_r_value = df.format (this.r_container.get (i).getKey ());
    		
    		sb.append (this.r_container_names.get (i))
    			.append (": ")
    			.append (truncated_r_value)
    			.append (" - ")
    			.append (DataConfidence.provideCIValidity (this.r_container.get (i).getKey (), this.r_container.get (i).getValue ()))
    			.append ("\n");
    	}
    	System.out.println (sb.toString ());
    	return (sb.toString ());
    }
    
    /**
     * Provides a window that states the R^2 value for any and all interpolations
     * performed.
     */
    private void showRSquaredValidity () {
		// Show the dialog.
		if (!this.r_container.isEmpty ()) {
			JOptionPane.showMessageDialog (null, this.getRSquaredValidity (), 
					"R-Squared Calculation", JOptionPane.INFORMATION_MESSAGE);
		}
    }
    
    private String getRSquaredValidity () {
    	// Prepare the message
		StringBuilder sb = new StringBuilder ();
		
		// Set up the Number formatter.
    	DecimalFormat df = new DecimalFormat ();
    	df.setMaximumFractionDigits (4);
		
		sb.append ("R-Squared Values\n");
		
		for (int i = 0; (i < this.r_container.size ()); ++i) {
			
			String truncated_r_squared_value = df.format (this.r_container.get (i).getKey ());
			
			sb.append (this.r_container_names.get (i))
				.append (": ")
				.append (truncated_r_squared_value)
				.append ("\n");
		}
		
		return (sb.toString ());
    }
    
    /**
     * Provides a window stating the R-squared value for the non-linear
     * regression performed.
     * For more information, please refer to: {@link http://www.graphpad.com/guides/prism/6/curve-fitting/index.htm?r2_ameasureofgoodness_of_fitoflinearregression.htm}
     * 
     * @param ds The DataSet to find an R-Squared value of.
     * @param f The PolynomialFunction2D used to calculate the residual Sum of Squares.
     */
    private double getRSquaredValidity (DataSet ds, PolynomialFunction2D f) {
		SummaryStatistics stat_generator = new SummaryStatistics ();
		
		// Get the Sum of Squares of the Residuals.
		for (Double o : ds.getY ()) {
			stat_generator.addValue (Math.abs (o - f.getValue (ds.getXValue (ds.findY (o)))));
		}
		double ss_res = stat_generator.getSumsq ();
		
		// Get the Sum of Squares of the Mean Line.
		double mean = stat_generator.getMean ();
		
		stat_generator.clear ();
		for (Double o : ds.getY ()) {
			stat_generator.addValue (Math.abs ((o) - mean));
		}
		
		double ss_tot = stat_generator.getSumsq ();
		
		int n = ds.size ();
		int k = ds.getNumParameters ();
		
		return (1 - ((ss_res / (n - k)) / (ss_tot / (n - 1))));	
	}
    
    /**
     * Provides a window stating the R-squared value for the non-linear
     * regression performed.
     * For more information, please refer to: {@link http://www.graphpad.com/guides/prism/6/curve-fitting/index.htm?r2_ameasureofgoodness_of_fitoflinearregression.htm}
     * 
     * @param ds The DataSet to find an R-Squared value of.
     * @param f The PolynomialSplineFunction used to calculate the residual Sum of Squares.
     */
    private double getRSquaredValidity (DataSet ds, PolynomialSplineFunction f) {
		SummaryStatistics stat_generator = new SummaryStatistics ();
		
		// Get the Sum of Squares of the Residuals.
		for (Double o : ds.getY ()) {
			stat_generator.addValue (Math.abs (o - f.value (ds.getXValue (ds.findY (o)))));
		}
		double ss_res = stat_generator.getSumsq ();
		
		// Get the Sum of Squares of the Mean Line.
		double mean = stat_generator.getMean ();
		
		stat_generator.clear ();
		for (Double o : ds.getY ()) {
			stat_generator.addValue (Math.abs (o - mean));
		}
		
		double ss_tot = stat_generator.getSumsq ();
		
		int n = ds.size ();
		int k = ds.getNumParameters ();
		
		
		return (1 - ((ss_res / (n - k)) / (ss_tot / (n - 1))));
	}
}
