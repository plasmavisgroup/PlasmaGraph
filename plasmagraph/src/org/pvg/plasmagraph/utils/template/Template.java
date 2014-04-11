package org.pvg.plasmagraph.utils.template;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.plot.PlotOrientation;
import org.pvg.plasmagraph.utils.types.AxisType;
import org.pvg.plasmagraph.utils.types.ChartType;
import org.pvg.plasmagraph.utils.types.InterpolationType;
import org.pvg.plasmagraph.utils.types.OutlierResponse;

/**
 * Settings container for all of PlasmaGraph.
 * Maintains every possible setting that can and will be used in graphing
 * or using the optional tools.
 * 
 * @version 0.8.4
 * @author Gerardo A. Navas Morales
 */
public class Template {
	// Variables
    // Event Firing
    /** Collection of listeners for any change that occurs in this Template. */
    private Set <ChangeListener> listeners = new HashSet <> ();
    
	// Classifications
    /** Type of the Chart. Can be XY/BarChart. */
	private ChartType chart_type;
	
	// Label names.
	/** Name of the chart. */
	private String chart_name;
	/** Name of the X Axis. */
	private String x_axis_label;
	/** Name of the Y Axis. */
	private String y_axis_label;
	
	// Including Features.
	/** Boolean concerning the usage of the JFreeChart Legend Graph Component. */
	private boolean using_legend;
	/**  Boolean concerning the usage of the JFreeChart Tooltip Graph Component.  */
	private boolean using_tooltips;
	/** Boolean concerning the usage of JFreeChart HTML / URL Graph Creation. */
	private boolean generate_urls;
	
	// Layout Features
	/** The orientation of the graph. (HORIZONTAL or VERTICAL) Provided by JFreeChart. */
	private PlotOrientation orientation;
	
	// Data Set Features
	/** Type of Axis used by the X Axis. Default is "Standard". */
	private AxisType x_axis_type;
	/** Type of Axis used by the Y Axis. Default is "Standard". */
	private AxisType y_axis_type;
	/** Name of the selected column for the X Axis. */
	//private String x_axis_column_name;
	/** Name of the selected column for the Y Axis. */
	//private String y_axis_column_name;
	
	// Tool Features
	/** The type of interpolation that is default to this Template. */
	private InterpolationType default_interpolation_type;
	/** The type of response an outlier search will provide upon finding mild or extreme outliers. */
	private OutlierResponse default_outlier_reaction;
	/** The lower range of X-Axis values. */
	private double interpolation_lower_range;
	/** The upper range of X-Axis values. */
	private double interpolation_upper_range;
	/** Number of interpolation points in regression. */
	private int interpolation_point_amount;
	
	// Constructors
	/**
	 *  (Default) Constructor for Template objects.
	 *  Provides default values to new Template object.
	 */
	public Template () {
		// Use the defaults!
		this.chart_name 					= "Empty vs. Variable";
		this.chart_type 					= ChartType.XY_GRAPH;
		this.x_axis_label					= "X Axis";
		this.y_axis_label					= "Y Axis";
		this.using_legend 					= true;
		this.using_tooltips 				= true;
		this.generate_urls 					= false;
		this.orientation 					= PlotOrientation.VERTICAL;
		//this.group_by						= false;
		this.x_axis_type					= AxisType.STANDARD;
		this.y_axis_type					= AxisType.STANDARD;
		this.default_interpolation_type 	= InterpolationType.NONE;
		this.default_outlier_reaction 		= OutlierResponse.NONE;
		this.interpolation_lower_range 		= 0.0;
		this.interpolation_upper_range		= 10.0;
		this.interpolation_point_amount		= 1000;
		
	}
	
	/**
	 * Constructor for Template objects.
	 * Provides user-assigned values to new Template object.
	 * @param name The name of the chart to be created. (String)
	 * @param type ChartType object containing the type of graph to make. (ChartType)
	 * @param x The name of the x axis for the chart to be created. (String)
	 * @param y The name of the y axis for the chart to be created. (String)
	 * @param legend If the legend will be visible on the chart. (boolean)
	 * @param tooltips If tool tips will be available on the chart. (boolean)
	 * @param urls If URLs will be generated for the chart. (boolean)
	 * @param o The orientation of the range axis. (PlotOrientation) [PlotOrientation.HORIZONTAL or PlotOrientation.VERTICAL.]
	 * @param x_type AxisType that the X Axis will use. (AxisType)
	 * @param y_type AxisType that the Y Axis will use. (AxisType)
	 * @param interpolation Type of interpolation that will be performed. (InterpolationType)
	 * @param outlier Type of response that the outlier search will provide. (OutlierResponse)
	 * @param lower Lower bound of the interpolated data. (double)
	 * @param upper Upper bound of the interpolated data. (double)
	 * @param n Number of points to graph in the interpolated data. (int)
	 */
	public Template (String name, ChartType type, String x, String y, 
			boolean legend, boolean tooltips, boolean urls, PlotOrientation o,
			AxisType x_type, AxisType y_type,
			InterpolationType interpolation, OutlierResponse outlier,
			double lower, double upper, int n) {
		this.chart_name 					= name;
		this.chart_type 					= type;
		this.x_axis_label 					= x;
		this.y_axis_label 					= y;
		this.using_legend 					= legend;
		this.using_tooltips 				= tooltips;
		this.generate_urls 					= urls;
		this.orientation					= o;
		this.x_axis_type					= x_type;
		this.y_axis_type					= y_type;
		this.default_interpolation_type 	= interpolation;
		this.default_outlier_reaction 		= outlier;
		this.interpolation_lower_range 		= lower;
		this.interpolation_upper_range		= upper;
		this.interpolation_point_amount		= n;
	}
	
	// Event Methods
	/**
	 * Adds the listener provided to the notification list.
	 * 
	 * @param listener Listener to add to the notification list.
	 */
	public void addChangeListener (ChangeListener listener) {
	    this.listeners.add (listener);
	}
	
	/**
	 * Getter Method. Provides the "using_legend" variable.
     * @return A boolean variable, "using_legend", contained by this object.
	 */
	public final boolean generatesLegend () {
		return using_legend;
	}
	
	/**
	 * Getter Method. Provides the "using_tooltips" variable.
     * @return A boolean variable, "using_tooltips", contained by this object.
	 */
	public final boolean generatesTooltips () {
		return using_tooltips;
	}

	/**
	 * Getter Method. Provides the "generate_urls" variable.
     * @return A boolean variable, "generate_urls", contained by this object.
	 */
	public final boolean generatesURLs () {
		return generate_urls;
	}
	
	// Getters and Setters
	
	/**
	 * Getter Method. Provides the "chart_name" variable.
     * @return A String variable, "chart_name", contained by this object.
	 */
	public final String getChartName () {
		return chart_name;
	}

	/**
	 * Getter Method. Provides the "chart_type" variable.
	 * @return A ChartType variable, "chart_type", contained by this object.
	 */
	public final ChartType getChartType () {
		return chart_type;
	}

	/**
	 * Getter Method. Provides the "interpolation_point_amount" variable.
     * @return A String variable, "interpolation_point_amount", contained by this object.
	 */
	public final int getInterpolationInterval () {
		return (interpolation_point_amount);
	}

	/**
	 * Getter Method. Provides the "default_interpolation_type" variable.
     * @return An InterpolationType variable, "default_interpolation_type", contained by this object.
	 */
	public final InterpolationType getInterpolationType () {
		return default_interpolation_type;
	}
	
	/**
	 * Getter Method. Provides the "interpolation_lower_range" variable.
     * @return A String variable, "interpolation_lower_range", contained by this object.
	 */
	public final double getLowerInterval () {
		return (interpolation_lower_range);
	}

	/**
	 * Getter Method. Provides the "orientation" variable.
     * @return A PlotOrientation variable, "orientation", contained by this object.
	 */
	public final PlotOrientation getOrientation () {
		return orientation;
	}
	
	/**
	 * Getter Method. Provides the "default_outlier_reaction" variable.
     * @return An OutlierResponse variable, "default_outlier_reaction", contained by this object.
	 */
	public final OutlierResponse getOutlierResponse () {
		return default_outlier_reaction;
	}

	/**
	 * Getter Method. Provides the "interpolation_upper_range" variable.
     * @return A String variable, "interpolation_upper_range", contained by this object.
	 */
	public final double getUpperInterval () {
		return (interpolation_upper_range);
	}

	/**
	 * Getter Method. Provides the "x_axis_label" variable.
     * @return A String variable, "x_axis_label", contained by this object.
	 */
	public final String getXAxisLabel () {
		return x_axis_label;
	}
	
	/**
	 * Getter Method. Provides the "x_axis_column_name" variable.
     * @return A String variable, "x_axis_column_name", contained by this object.
	 */
	/*public String getXAxisColumn () {
		return (x_axis_column_name);
	}*/
	
	/**
	 * Getter Method. Provides the current AxisType for the X Axis.
	 * 
	 * @return An AxisType object specifying the type of Axis to use.
	 */
	public AxisType getXAxisType () {
		return (this.x_axis_type);
	}

	/**
	 * Getter Method. Provides the "y_axis_label" variable.
     * @return A String variable, "y_axis_label", contained by this object.
	 */
	public final String getYAxisLabel () {
		return y_axis_label;
	}
	
	/**
	 * Getter Method. Provides the "y_axis_column_name" variable.
     * @return A String variable, "y_axis_column_name", contained by this object.
	 */
/*	public String getYAxisColumn () {
		return (y_axis_column_name);
	}
*/
	/**
	 * Getter Method. Provides the current AxisType for the Y Axis.
	 * 
	 * @return An AxisType object specifying the type of Axis to use.
	 */
	public AxisType getYAxisType () {
		return (this.y_axis_type);
	}

	/**
	 * Getter method. Checks to see if the ChartName is currently in its default
	 * state.
	 * 
	 * @return True if the chart_name variable is equal to "Empty vs. Variable"; else, False.
	 */
	public boolean isDefaultChartName () {
		return (this.chart_name.equals ("Empty vs. Variable"));
	}

	/**
	 * Getter method. Checks to see if the XAxisLabel is currently in its default
	 * state.
	 * 
	 * @return True if the x_axis_label variable is equal to "X Axis"; else, False.
	 */
	public boolean isDefaultXAxisLabel () {
		return (this.x_axis_label.equals ("X Axis"));
	}

	/**
	 * Getter method. Checks to see if the YAxisLabel is currently in its default
	 * state.
	 * 
	 * @return True if the x_axis_label variable is equal to "Y Axis"; else, False.
	 */
	public boolean isDefaultYAxisLabel () {
		return (this.y_axis_label.equals ("Y Axis"));
	}

	/**
	 * Sends a ChangeEvent to all listeners of this object,
	 * declaring that this Template object has been changed in some way.
	 */
	public void notifyListeners () {
	    for (ChangeListener c : listeners) {
	        c.stateChanged (new ChangeEvent (this));
	    }
	}

	/**
	 * Provides user-assigned values to new Template object based on file selected.
	 * 
	 * @param f File being opened.
	 */
	public void openTemplate (File f) {
	 // See if we can get this to work. Otherwise, throw an error!
        try (BufferedReader reader = new BufferedReader (new FileReader (f))) {
            //System.out.println (f.getName ());

            // Now, read and put in the correct place!
            // Classifications
            String output = reader.readLine();
            if (output.equals (ChartType.XY_GRAPH.toString ())) {
                this.chart_type = ChartType.XY_GRAPH;
            } else {//if (output.equals (ChartType.BAR_GRAPH.toString ())) {
                this.chart_type = ChartType.BAR_GRAPH;
            } 

            // Label names.
            output = reader.readLine();
            this.chart_name = output;
            output = reader.readLine();
            this.x_axis_label = output;
            output = reader.readLine();
            this.y_axis_label = output;

            // Including Features.
            output = reader.readLine();
            this.using_legend = new Boolean (output);
            output = reader.readLine();
            this.using_tooltips = new Boolean (output);
            output = reader.readLine();
            this.generate_urls = new Boolean (output);

            // Layout Features
            output = reader.readLine();
            if (output.equals (PlotOrientation.HORIZONTAL.toString())) {
                this.orientation = PlotOrientation.HORIZONTAL;
            } else {
                this.orientation = PlotOrientation.VERTICAL;
            }
            
            // Data Set Features
            output = reader.readLine ();
            if (AxisType.STANDARD.toString ().equals (output)) {
                this.x_axis_type = AxisType.STANDARD;
            } else {
            	this.x_axis_type = AxisType.LOG;
            }
            output = reader.readLine ();
            if (AxisType.STANDARD.toString ().equals (output)) {
                this.y_axis_type = AxisType.STANDARD;
            } else {
            	this.y_axis_type = AxisType.LOG;
            }
            
            // Tool Features
            // Interpolation Type
            output = reader.readLine();
            if (output.equals (InterpolationType.LINEAR.toString ())) {
                this.default_interpolation_type = InterpolationType.LINEAR;
            } 
            else if (output.equals (InterpolationType.QUADRATIC.toString ())) {
                this.default_interpolation_type = InterpolationType.QUADRATIC;
            } 
            else if (output.equals (InterpolationType.CUBIC.toString ())) {
                this.default_interpolation_type = InterpolationType.CUBIC;
            } 
            else if (output.equals (InterpolationType.QUARTIC.toString ())) {
                this.default_interpolation_type = InterpolationType.QUARTIC;
            } 
            else {
                this.default_interpolation_type = InterpolationType.SPLINE;
            }
            
            // Outlier Response
            output = reader.readLine();
            if (output.equals (OutlierResponse.WARN.toString())) {
                this.default_outlier_reaction = OutlierResponse.WARN;
            } else {
                this.default_outlier_reaction = OutlierResponse.REMOVE;
            }
            
            // Interpolation ranges and interval
            output = reader.readLine();
            this.interpolation_lower_range 		= Double.parseDouble (output);
            output = reader.readLine();
    		this.interpolation_upper_range		= Double.parseDouble (output);
    		output = reader.readLine();
    		this.interpolation_point_amount		= Integer.parseInt (output);
    		
    		// Fire messages to all listeners!
    		this.notifyListeners ();

        } catch (FileNotFoundException e) {
            // Catch for File "f" not found.
            // TODO Create an ExceptionHandler method for this.
            System.out.println ("Error in Template's \'openTemplate (File f)\' method: File was not found.");
            e.printStackTrace();
            
        } catch (IOException e) {
            // Catch for BufferedReader "reader" giving problems that don't include null!
            // TODO Create an ExceptionHandler method for this.
            System.out.println ("Error in Template's \'openTemplate (File f)\' method: IO procedures gave an error.");
            e.printStackTrace ();
            
        } catch (NumberFormatException e) {
            // Catch for BufferedReader "reader" giving problems that don't include null!
            // TODO Create an ExceptionHandler method for this.
            System.out.println ("Error in Template's \'openTemplate (File f)\' method: ParseDouble gave an error.");
            e.printStackTrace ();
        }

	}

	/**
	 * Removes the listener provided from the notification list.
	 * 
	 * @param listener Listener to remove from notification list.
	 */
	public void removeChangeListener (ChangeListener listener) {
	    this.listeners.remove (listener);
	}

	/**
	 * Saves template in a plain text format for simplicity.
	 * Calls on the string version to save the file.
	 * 
	 * @param f JFileChooser with selected file name.
	 */
	public void saveTemplate (JFileChooser f) {
		saveTemplate (f.getSelectedFile () + ".tem");
	}

	/**
     * Saves template in a plain text format for simplicity.
     * Uses BufferedWriter in order to create and manipulate said object.
     * 
     * @param file_name File name to be opened in order to save template.
     */
	public void saveTemplate (String file_name) {
	 // See if we can get this to work. Otherwise, throw an error!
        try (BufferedWriter writer = new BufferedWriter (new FileWriter (new File (file_name)))){
            // Combine the entirety of the data to write in a single string!
            StringBuilder sb = new StringBuilder ();
            // TODO: Change to getProperty if multi-platform errors.
            String ls = "\n"; //System.getProperty ("line.separator");
            
            // Classifications
            sb.append (this.chart_type.toString () + ls);
            
            // Label names.
            sb.append (this.chart_name + ls);
            sb.append (this.x_axis_label + ls);
            sb.append (this.y_axis_label + ls);
            
            // Including Features.
            sb.append (Boolean.toString (using_legend) + ls);
            sb.append (Boolean.toString (using_tooltips) + ls);
            sb.append (Boolean.toString (generate_urls) + ls);
            
            // Layout Features
            sb.append (this.orientation.toString () + ls);
            
            // Data Set Features
            sb.append (this.x_axis_type.toString () + ls);
            sb.append (this.y_axis_type.toString () + ls);
            
            // Tool Features
            sb.append (this.default_interpolation_type.toString () + ls);
            sb.append (this.default_outlier_reaction.toString () + ls);
            
            // Interpolation ranges and interval
            sb.append (this.interpolation_lower_range + ls);
            sb.append (this.interpolation_upper_range + ls);
            sb.append (this.interpolation_point_amount + ls);
            

            // Write it to the BufferedWriter
            writer.write (sb.toString ());
            
        } catch (IOException e) {
            // Catch for File "f" not being writable.
            // TODO Properly deal with this exception.
            e.printStackTrace();
        }
	}

	/**
	 * Setter Method. Changes the "chart_name" variable.
     * @param name The new ChartType variable to replace this object's "chart_type" variable's contents.
	 */
	public final void setChartName (String name) {
		this.chart_name = name;
	}

	/**
	 * Setter Method. Changes the "chart_name" variable.
	 * @param graph The new ChartType variable to replace this object's "chart_type" variable's contents.
	 */
	public final void setChartType (ChartType graph) {
		this.chart_type = graph;
	}

	/**
	 * Setter Method. Changes the "interpolation_point_amount" variable.
     * @param interval The new String variable to replace this object's "interpolation_point_amount" variable's contents.
	 */
	public final void setInterpolationInterval (int interval) {
		this.interpolation_point_amount = interval;
	}
	
	/**
	 * Setter Method. Changes the "default_interpolation_type" variable.
     * @param type The new InterpolationType variable to replace this object's "default_interpolation_type" variable's contents.
	 */
	public final void setInterpolationType (InterpolationType type) {
		this.default_interpolation_type = type;
	}

	/**
	 * Setter Method. Changes the "using_legend" variable.
     * @param legend The new boolean variable to replace this object's "using_legend" variable's contents.
	 */
	public final void setLegend (boolean legend) {
		this.using_legend = legend;
	}
	
	/**
	 * Setter Method. Changes the "interpolation_lower_range" variable.
     * @param lower The new String variable to replace this object's "interpolation_lower_range" variable's contents.
	 */
	public final void setLowerInterval (double lower) {
		this.interpolation_lower_range = lower;
	}

	/**
	 * Setter Method. Changes the "orientation" variable.
     * @param orientation The new PlotOrientation variable to replace this object's "orientation" variable's contents.
	 */
	public final void setOrientation (PlotOrientation orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * Setter Method. Changes the "default_outlier_reaction" variable.
     * @param reaction The new OutlierResponse variable to replace this object's "default_outlier_reaction" variable's contents.
	 */
	public final void setOutlierResponse (OutlierResponse reaction) {
		this.default_outlier_reaction = reaction;
	}

	/**
	 * Setter Method. Changes the "using_tooltips" variable.
     * @param tooltips The new boolean variable to replace this object's "using_tooltips" variable's contents.
	 */
	public final void setTooltips (boolean tooltips) {
		this.using_tooltips = tooltips;
	}

	/**
	 * Setter Method. Changes the "interpolation_upper_range" variable.
     * @param upper The new String variable to replace this object's "interpolation_upper_range" variable's contents.
	 */
	public final void setUpperInterval (double upper) {
		this.interpolation_upper_range = upper;
	}
	
	/**
	 * Setter Method. Changes the "generate_urls" variable.
     * @param urls The new boolean variable to replace this object's "generate_urls" variable's contents.
	 */
	public final void setURLs (boolean urls) {
		this.generate_urls = urls;
	}
	
	/**
	 * Setter Method. Changes the "x_axis_label" variable.
     * @param label The new String variable to replace this object's "x_axis_label" variable's contents.
	 */
	public final void setXAxisLabel (String label) {
		this.x_axis_label = label;
	}
	
	/**
	 * Setter Method. Changes the "x_axis_column_name" variable.
     * @param column_name The new String variable to replace this object's "x_axis_column_name" variable's contents.
	 */
/*	public void setXAxisColumn (String column_name) {
		this.x_axis_column_name = column_name;
	}*/
	
	/**
	 * Setter Method. Sets this object's X Axis AxisType to that of the parameter provided.
	 * 
	 * @param xAxisType An AxisType object specifying the type of Axis to use.
	 */
	public void setXAxisType (AxisType xAxisType) {
		this.x_axis_type = xAxisType;
	}
	
	/**
	 * Verifies if the "default_interpolation_type" object is set to its default state.
	 * @return True if the "default_interpolation_type" is InterpolationType.NONE; else, False.
	 */
	public boolean isInterpolating () {
		return (!InterpolationType.NONE.equals (this.default_interpolation_type));
	}
	
	/**
	 * Verifies if the "default_outlier_reaction" object is set to its default state.
	 * @return True if the "default_outlier_reaction" is OutlierResponse.NONE; else, False.
	 */
	public boolean isSearching () {
		return (!OutlierResponse.NONE.equals (this.default_outlier_reaction));
	}

	/**
	 * Setter Method. Checks the contents of the String provided and sets this object's
	 * X Axis AxisType accordingly.
	 * 
	 * @param xAxisType A String object specifying the type of Axis to use.
	 */
	public void setXAxisType (String xAxisType) {
		if (AxisType.STANDARD.toString ().equals (xAxisType)) {
			
			this.x_axis_type = AxisType.STANDARD;
			
		} else if (AxisType.LOG.toString ().equals (xAxisType)) {
			
			this.x_axis_type = AxisType.LOG;
			
		}
	}

	/**
	 * Setter Method. Changes the "y_axis_label" variable.
     * @param label The new String variable to replace this object's "y_axis_label" variable's contents.
	 */
	public final void setYAxisLabel (String label) {
		this.y_axis_label = label;
	}
	
	/**
	 * Setter Method. Changes the "y_axis_column_name" variable.
     * @param column_name The new String variable to replace this object's "y_axis_column_name" variable's contents.
	 */
/*	public void setYAxisColumn (String column_name) {
		this.y_axis_column_name = column_name;
	}
*/
	/**
	 * Setter Method. Sets this object's Y Axis AxisType to that of the parameter provided.
	 * 
	 * @param yAxisType An AxisType object specifying the type of Axis to use.
	 */
	public void setYAxisType (AxisType yAxisType) {
		this.y_axis_type = yAxisType;
	}
	
	/**
	 * Setter Method. Checks the contents of the String provided and sets this object's
	 * Y Axis AxisType accordingly.
	 * 
	 * @param yAxisType A String object specifying the type of Axis to use.
	 */
	public void setYAxisType (String yAxisType) {
		if (AxisType.STANDARD.toString ().equals (yAxisType)) {
			
			this.y_axis_type = AxisType.STANDARD;
			
		} else if (AxisType.LOG.toString ().equals (yAxisType)) {
			
			this.y_axis_type = AxisType.LOG;
			
		}
	}

	/**
	 * Provides a textual representation of this Template object.
	 * Uses StringBuilder in order to allow for increasing complexity while avoiding the eyesore of mile-long concatenated strings.
	 * @return A String object containing a description of this object.
	 */
	@Override
	public String toString () {
	    // Prepare the tools for this procedure.
	    StringBuilder sb = new StringBuilder ();
	    String ls = System.getProperty ("line.separator");
	    
	    // Append all the properties of this Template object.
	    sb.append ("Type: " + chart_type.toString () + ls);
	    sb.append ("Name: " + chart_name + ls);
	    sb.append ("X Axis: " + x_axis_label + ls);
	    sb.append ("Y Axis: " + y_axis_label + ls);
	    sb.append ("Orientation: " + orientation.toString () + ls);
	    sb.append ("Legend?: " + Boolean.toString (using_legend) + ls);
	    sb.append ("Tooltips?: " + Boolean.toString (using_tooltips) + ls);
	    sb.append ("URL Generation?: " + Boolean.toString (generate_urls) + ls);
	    sb.append ("Interpolation: " + default_interpolation_type.toString () + ls);
	    sb.append ("X Axis Type is: " + x_axis_type.toString () + ls);
	    sb.append ("Y Axis Type is: " + y_axis_type.toString () + ls);
	    sb.append ("Outlier Reaction: " + default_outlier_reaction.toString () + ls);
	    sb.append ("Lower Interpolation Range: " + interpolation_lower_range + ls);
        sb.append ("Upper Interpolation Range: " + interpolation_upper_range + ls);
        sb.append ("Interpolation Interval: " + interpolation_point_amount);
	    
	    // Create the String to be returned.
	    return (sb.toString ());
	}
}