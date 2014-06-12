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
import org.pvg.plasmagraph.utils.types.OutlierDistanceType;
import org.pvg.plasmagraph.utils.types.OutlierResponse;

/**
 * <p>Settings container for all of PlasmaGraph.
 * 
 * <p>Maintains every possible setting that can and will be used in graphing
 * or using the optional tools.
 * 
 * <p>It is important to note that this class fires events! Therefore, this class
 * should not be made more than once. (This class may be made into a singleton
 * someday, but not yet.)
 * 
 * @author Plasma Visualization Group
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
	/** Maximum cartesian distance allowed in Outlier Scanning. */
	private double outlier_distance;
	/** Type of distance that will be used in the Outlier Scanning procedure. */
	private OutlierDistanceType outlier_distance_type;
	
	// Program settings
	//private boolean show_info_messages;
	
	// Constructors
	/**
	 *  <p>Constructor for Template objects. Provides default values to new Template object.
	 */
	public Template () {
		// Use the defaults!
		this.chart_name 					= "Default Chart Name";
		this.chart_type 					= ChartType.XY_GRAPH;
		this.x_axis_label					= "X Axis";
		this.y_axis_label					= "Y Axis";
		this.using_legend 					= true;
		this.using_tooltips 				= true;
		this.generate_urls 					= false;
		this.orientation 					= PlotOrientation.VERTICAL;
		this.x_axis_type					= AxisType.STANDARD;
		this.y_axis_type					= AxisType.STANDARD;
		this.default_interpolation_type 	= InterpolationType.NONE;
		this.default_outlier_reaction 		= OutlierResponse.NONE;
		this.interpolation_lower_range 		= 0.0;
		this.interpolation_upper_range		= 10.0;
		this.interpolation_point_amount		= 5000;
		this.outlier_distance_type 			= OutlierDistanceType.USER;
		this.outlier_distance				= 10.0;
		//this.show_info_messages 			= true;
	}
	
	// Event Methods
	/**
	 * <p>Adds the listener provided to the notification list.
	 * 
	 * @param listener Listener to add to the notification list.
	 */
	public void addChangeListener (ChangeListener listener) {
	    this.listeners.add (listener);
	}
	
	/**
	 * <p>Getter Method. Provides the "using_legend" variable.
	 * 
     * @return A boolean variable, "using_legend", contained by this object.
	 */
	public final boolean generatesLegend () {
		return using_legend;
	}
	
	/**
	 * <p>Getter Method. Provides the "using_tooltips" variable.
	 * 
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
	 * <p>Getter Method. Provides the "chart_name" variable.
	 * 
     * @return A String variable, "chart_name", contained by this object.
	 */
	public final String getChartName () {
		return chart_name;
	}

	/**
	 * <p>Getter Method. Provides the "chart_type" variable.
	 * 
	 * @return A ChartType variable, "chart_type", contained by this object.
	 */
	public final ChartType getChartType () {
		return chart_type;
	}

	/**
	 * <p>Getter Method. Provides the "interpolation_point_amount" variable.
	 * 
     * @return A String variable, "interpolation_point_amount", contained by this object.
	 */
	public final int getInterpolationInterval () {
		return (interpolation_point_amount);
	}

	/**
	 * <p>Getter Method. Provides the "default_interpolation_type" variable.
	 * 
     * @return An InterpolationType variable, "default_interpolation_type", contained by this object.
	 */
	public final InterpolationType getInterpolationType () {
		return default_interpolation_type;
	}
	
	/**
	 * <p>Getter Method. Provides the "interpolation_lower_range" variable.
	 * 
     * @return A String variable, "interpolation_lower_range", contained by this object.
	 */
	public final double getLowerInterval () {
		return (interpolation_lower_range);
	}

	/**
	 * <p>Getter Method. Provides the "orientation" variable.
	 * 
     * @return A PlotOrientation variable, "orientation", contained by this object.
	 */
	public final PlotOrientation getOrientation () {
		return orientation;
	}
	
	/**
	 * <p>Getter Method. Provides the "default_outlier_reaction" variable.
	 * 
     * @return An OutlierResponse variable, "default_outlier_reaction", contained by this object.
	 */
	public final OutlierResponse getOutlierResponse () {
		return default_outlier_reaction;
	}

	/**
	 * <p>Getter Method. Provides the "interpolation_upper_range" variable.
	 * 
     * @return A String variable, "interpolation_upper_range", contained by this object.
	 */
	public final double getUpperInterval () {
		return (interpolation_upper_range);
	}

	/**
	 * <p>Getter Method. Provides the "x_axis_label" variable.
	 * 
     * @return A String variable, "x_axis_label", contained by this object.
	 */
	public final String getXAxisLabel () {
		return x_axis_label;
	}
	
	/**
	 * <p>Getter Method. Provides the "x_axis_column_name" variable.
	 * 
     * @return A String variable, "x_axis_column_name", contained by this object.
	 */
	/*public String getXAxisColumn () {
		return (x_axis_column_name);
	}*/
	
	/**
	 * <p>Getter Method. Provides the current AxisType for the X Axis.
	 * 
	 * @return An AxisType object specifying the type of Axis to use.
	 */
	public AxisType getXAxisType () {
		return (this.x_axis_type);
	}

	/**
	 * <p>Getter Method. Provides the "y_axis_label" variable.
	 * 
     * @return A String variable, "y_axis_label", contained by this object.
	 */
	public final String getYAxisLabel () {
		return y_axis_label;
	}
	
	/**
	 * <p>Getter Method. Provides the "y_axis_column_name" variable.
	 * 
     * @return A String variable, "y_axis_column_name", contained by this object.
	 */
/*	public String getYAxisColumn () {
		return (y_axis_column_name);
	}
*/
	/**
	 * <p>Getter Method. Provides the current AxisType for the Y Axis.
	 * 
	 * @return An AxisType object specifying the type of Axis to use.
	 */
	public AxisType getYAxisType () {
		return (this.y_axis_type);
	}
	
	/**
	 * <p>Getter Method. Provides the current OutlierDistancetype for outlier scanning.
	 * 
	 * @return An OutlierDistancetype object specifying the distance calculation
	 * mechanism for Outlier Scanning.
	 */
	public OutlierDistanceType getOutlierDistanceType () {
		return (outlier_distance_type);
	}
	
	/**
	 * <p>Getter Method. Provides the current distance for outlier scanning.
	 * 
	 * @return A double object specifying the maximum acceptable distance
	 * between points.
	 */
	public double getOutlierDistance () {
		return outlier_distance;
	}

	/**
	 * <p>Getter method. Checks to see if the ChartName is currently in its default
	 * state.
	 * 
	 * @return True if the chart_name variable is equal to "Default Chart Name"; else, False.
	 */
	public boolean isDefaultChartName () {
		return ("Default Chart Name".equals (this.chart_name));
	}

	/**
	 * <p>Getter method. Checks to see if the XAxisLabel is currently in its default
	 * state.
	 * 
	 * @return True if the x_axis_label variable is equal to "X Axis"; else, False.
	 */
	public boolean isDefaultXAxisLabel () {
		return ("X Axis".equals (this.x_axis_label) ||
				"".equals (x_axis_label));
	}

	/**
	 * <p>Getter method. Checks to see if the YAxisLabel is currently in its default
	 * state.
	 * 
	 * @return True if the x_axis_label variable is equal to "Y Axis"; else, False.
	 */
	public boolean isDefaultYAxisLabel () {
		return ("Y Axis".equals (this.y_axis_label) ||
				"".equals (y_axis_label));
	}
	
	/**
	 * <p>Getter method. Checks to see if the user has selected to show popup messages
	 * such as the rows removed while verifying data or R/R^2 values of an interpolation.
	 * 
	 * @return True if "show_info_messages" is set to True; else, False.
	 */
	/*public boolean isShowingInfoMessages () {
		return (this.show_info_messages);
	}*/

	/**
	 * <p>Sends a ChangeEvent to all listeners of this object,
	 * declaring that this Template object has been changed in some way.
	 */
	public void notifyListeners () {
	    for (ChangeListener c : listeners) {
	        c.stateChanged (new ChangeEvent (this));
	    }
	}

	/**
	 * <p>Provides user-assigned values to new Template object based on file selected.
	 * <p>TODO: Update this method!
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
            this.chart_type = ChartType.parse (output);

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
            this.x_axis_type = AxisType.parse (output);
            output = reader.readLine ();
            this.y_axis_type = AxisType.parse (output);
            
            // Tool Features
            // Interpolation Type
            output = reader.readLine();
            this.default_interpolation_type = InterpolationType.parse (output);
            
            // Outlier Response
            output = reader.readLine();
            this.default_outlier_reaction = OutlierResponse.parse (output);
            
            // Interpolation ranges and interval
            output = reader.readLine();
            this.interpolation_lower_range 		= Double.parseDouble (output);
            output = reader.readLine();
    		this.interpolation_upper_range		= Double.parseDouble (output);
    		output = reader.readLine();
    		this.interpolation_point_amount		= Integer.parseInt (output);
    		
    		// Outlier distance and type
    		output = reader.readLine ();
    		this.outlier_distance = Double.parseDouble (output);
    		output = reader.readLine ();
    		this.outlier_distance_type = OutlierDistanceType.parse (output);
    		
    		// Options selections
    		/*output = reader.readLine();
            this.show_info_messages = new Boolean (output);*/
    		
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
	 * <p>Removes the listener provided from the notification list.
	 * 
	 * @param listener Listener to remove from notification list.
	 */
	public void removeChangeListener (ChangeListener listener) {
	    this.listeners.remove (listener);
	}

	/**
	 * <p>Saves template in a plain text format for simplicity.
	 * Calls on the string version to save the file.
	 * 
	 * @param f JFileChooser with selected file name.
	 */
	public void saveTemplate (JFileChooser f) {
		saveTemplate (f.getSelectedFile () + ".tem");
	}

	/**
     * <p>Saves template in a plain text format for simplicity.
     * Uses BufferedWriter in order to create and manipulate said object.
     * <p>TODO: Update this method!
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
            
        	// Outlier distance and type
            sb.append (this.outlier_distance + ls);
            sb.append (this.outlier_distance_type.toString () + ls);
            
            // Options selections
            /*sb.append (this.show_info_messages + ls);*/

            // Write it to the BufferedWriter
            writer.write (sb.toString ());
            
        } catch (IOException e) {
            // Catch for File "f" not being writable.
            // TODO Properly deal with this exception.
            e.printStackTrace();
        }
	}

	/**
	 * <p>Setter Method. Changes the "chart_name" variable.
	 * 
     * @param name The new ChartType variable to replace this object's "chart_type" variable's contents.
	 */
	public final void setChartName (String name) {
		this.chart_name = name;
	}

	/**
	 * <p>Setter Method. Changes the "chart_name" variable.
	 * 
	 * @param graph The new ChartType variable to replace this object's "chart_type" variable's contents.
	 */
	public final void setChartType (ChartType graph) {
		this.chart_type = graph;
	}

	/**
	 * <p>Setter Method. Changes the "interpolation_point_amount" variable.
	 * 
     * @param interval The new String variable to replace this object's "interpolation_point_amount" variable's contents.
	 */
	public final void setInterpolationInterval (int interval) {
		this.interpolation_point_amount = interval;
	}
	
	/**
	 * <p>Setter Method. Changes the "default_interpolation_type" variable.
	 * 
     * @param type The new InterpolationType variable to replace this object's "default_interpolation_type" variable's contents.
	 */
	public final void setInterpolationType (InterpolationType type) {
		this.default_interpolation_type = type;
	}

	/**
	 * <p>Setter Method. Changes the "using_legend" variable.
	 * 
     * @param legend The new boolean variable to replace this object's "using_legend" variable's contents.
	 */
	public final void setLegend (boolean legend) {
		this.using_legend = legend;
	}
	
	/**
	 * <p>Setter Method. Changes the "interpolation_lower_range" variable.
	 * 
     * @param lower The new String variable to replace this object's "interpolation_lower_range" variable's contents.
	 */
	public final void setLowerInterval (double lower) {
		this.interpolation_lower_range = lower;
	}

	/**
	 * <p>Setter Method. Changes the "orientation" variable.
	 * 
     * @param orientation The new PlotOrientation variable to replace this object's "orientation" variable's contents.
	 */
	public final void setOrientation (PlotOrientation orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * <p>Setter Method. Changes the "default_outlier_reaction" variable.
	 * 
     * @param reaction The new OutlierResponse variable to replace this object's "default_outlier_reaction" variable's contents.
	 */
	public final void setOutlierResponse (OutlierResponse reaction) {
		this.default_outlier_reaction = reaction;
	}

	/**
	 * <p>Setter Method. Changes the "using_tooltips" variable.
	 * 
     * @param tooltips The new boolean variable to replace this object's "using_tooltips" variable's contents.
	 */
	public final void setTooltips (boolean tooltips) {
		this.using_tooltips = tooltips;
	}

	/**
	 * <p>Setter Method. Changes the "interpolation_upper_range" variable.
	 * 
     * @param upper The new String variable to replace this object's "interpolation_upper_range" variable's contents.
	 */
	public final void setUpperInterval (double upper) {
		this.interpolation_upper_range = upper;
	}
	
	/**
	 * <p>Setter Method. Changes the "generate_urls" variable.
	 * 
     * @param urls The new boolean variable to replace this object's "generate_urls" variable's contents.
	 */
	public final void setURLs (boolean urls) {
		this.generate_urls = urls;
	}
	
	/**
	 * <p>Setter Method. Changes the "x_axis_label" variable.
	 * 
     * @param label The new String variable to replace this object's "x_axis_label" variable's contents.
	 */
	public final void setXAxisLabel (String label) {
		this.x_axis_label = label;
	}
	
	/**
	 * <p>Setter Method. Sets this object's X Axis AxisType to that of the parameter provided.
	 * 
	 * @param xAxisType An AxisType object specifying the type of Axis to use.
	 */
	public void setXAxisType (AxisType xAxisType) {
		this.x_axis_type = xAxisType;
	}
	
	/**
	 * <p>Verifies if the "default_interpolation_type" object is set to its default state.
	 * 
	 * @return True if the "default_interpolation_type" is InterpolationType.NONE; else, False.
	 */
	public boolean isInterpolating () {
		return (!InterpolationType.NONE.equals (this.default_interpolation_type));
	}
	
	/**
	 * <p>Verifies if the "default_outlier_reaction" object is set to its default state.
	 * 
	 * @return True if the "default_outlier_reaction" is OutlierResponse.NONE; else, False.
	 */
	public boolean isSearching () {
		return (!OutlierResponse.NONE.equals (this.default_outlier_reaction));
	}

	/**
	 * <p>Setter Method. Checks the contents of the String provided and sets this object's
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
	 * <p>Setter Method. Changes the "y_axis_label" variable.
	 * 
     * @param label The new String variable to replace this object's "y_axis_label" variable's contents.
	 */
	public final void setYAxisLabel (String label) {
		this.y_axis_label = label;
	}
	
	/**
	 * <p>Setter Method. Sets this object's Y Axis AxisType to that of the parameter provided.
	 * 
	 * @param yAxisType An AxisType object specifying the type of Axis to use.
	 */
	public void setYAxisType (AxisType yAxisType) {
		this.y_axis_type = yAxisType;
	}
	
	/**
	 * <p>Setter Method. Checks the contents of the String provided and sets this object's
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
	 * <p>Setter Method. Sets the current distance calulcation mechanism for 
	 * Outlier Scanning.
	 * 
	 * @param t An OutlierDistanceType specifying the Outlier Scanning distance
	 * calculation mechanism that will be used.
	 */
	public void setOutlierDistanceType (OutlierDistanceType t) {
		this.outlier_distance_type = t;
	}
	
	/**
	 * <p>Setter Method. Sets the current distance for outlier scanning.
	 * 
	 * @param outlier_distance A double specifying the maximum acceptable 
	 * distance between points.
	 */
	public void setOutlierDistance (double outlier_distance) {
		this.outlier_distance = outlier_distance;
	}
	
	/**
	 * <p>Setter Method. Sets the visibility of popup messages in the program.
	 * 
	 * @param popups A boolean stating if popup messages will be shown or not.
	 */
	/*public void setShowInfoMessages (boolean messages) {
		this.show_info_messages = messages;
	}*/

	/**
	 * <p>Provides a textual representation of this Template object.
	 * <p>Uses StringBuilder in order to allow for increasing complexity while avoiding the eyesore of mile-long concatenated strings.
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
        sb.append ("Interpolation Interval: " + interpolation_point_amount + ls);
        /*sb.append ("Info Messages are being displayed:  " + show_info_messages);*/
	    
	    // Create the String to be returned.
	    return (sb.toString ());
	}
}