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
import org.pvg.plasmagraph.utils.graphs.ChartType;

public class Template {
	// Variables
    // Event Firing
    private Set <ChangeListener> listeners = new HashSet <ChangeListener> ();
    
	// Classifications
	private ChartType chart_type;
	
	// Label names.
	private String chart_name;
	private String x_axis_label;
	private String y_axis_label;
	
	// Including Features.
	private boolean using_legend;
	private boolean using_tooltips;
	private boolean generate_urls;
	
	// Layout Features
	private PlotOrientation orientation;
	
	// Tool Features
	private InterpolationType default_interpolation_type;
	private OutlierResponse default_outlier_reaction;
	
	// Constructors
	/**
	 *  Constructor for Template objects.
	 *  Provides default values to new Template object.
	 *  @param Nothing.
	 *  @returns Nothing.
	 */
	public Template () {
		// Use the defaults!
		this.chart_name 				= "Empty vs. Variable";
		this.chart_type 				= ChartType.XY_GRAPH;
		this.x_axis_label				= "X Axis";
		this.y_axis_label				= "Y Axis";
		this.using_legend 				= true;
		this.using_tooltips 			= true;
		this.generate_urls 				= false;
		this.orientation 				= PlotOrientation.HORIZONTAL;
		this.default_interpolation_type = InterpolationType.LINEAR;
		this.default_outlier_reaction 	= OutlierResponse.WARN;
	}
	
	/**
	 * Constructor for Template objects.
	 * Provides user-assigned values to new Template object.
	 * @param name The name of the chart to be created. (String)
	 * @param x The name of the x axis for the chart to be created. (String)
	 * @param y The name of the y axis for the chart to be created. (String)
	 * @param legend If the legend will be visible on the chart. (boolean)
	 * @param tooltips If tool tips will be available on the chart. (boolean)
	 * @param urls If URLs will be generated for the chart. (boolean)
	 * @param o The orientation of the range axis. (PlotOrientation) [PlotOrientation.HORIZONTAL or PlotOrientation.VERTICAL.]
	 * @returns Nothing.
	 */
	public Template (String name, ChartType type, String x, String y, 
			boolean legend, boolean tooltips, boolean urls, PlotOrientation o,
			InterpolationType interpolation, OutlierResponse outlier) {
		this.chart_name 				= name;
		this.chart_type 				= type;
		this.x_axis_label 				= x;
		this.y_axis_label 				= y;
		this.using_legend 				= legend;
		this.using_tooltips 			= tooltips;
		this.generate_urls 				= urls;
		this.orientation				= o;
		this.default_interpolation_type = interpolation;
		this.default_outlier_reaction 	= outlier;
	}
	
	/**
	 * Provides user-assigned values to new Template object based on file selected.
	 * 
	 * @param f File being opened.
	 */
	public void openTemplate (File f) {
	 // See if we can get this to work. Otherwise, throw an error!
        try (BufferedReader reader = new BufferedReader (new FileReader (f))) {
            System.out.println (f.getName ());

            // Now, read and put in the correct place!
            // Classifications
            String output = reader.readLine();
            if (output.equals (ChartType.XY_GRAPH.toString ())) {
                this.chart_type = ChartType.XY_GRAPH;
            } else if (output.equals (ChartType.LINE_GRAPH.toString ())) {
                this.chart_type = ChartType.LINE_GRAPH;
            } else if (output.equals (ChartType.BAR_GRAPH.toString ())) {
                this.chart_type = ChartType.BAR_GRAPH;
            } else {
                this.chart_type = ChartType.PIE_GRAPH;
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
            
            // Tool Features
            output = reader.readLine();
            if (output.equals (InterpolationType.LINEAR.toString ())) {
                this.default_interpolation_type = InterpolationType.LINEAR;
            } else if (output.equals (InterpolationType.POLYNOMIAL.toString ())) {
                this.default_interpolation_type = InterpolationType.POLYNOMIAL;
            } else {
                this.default_interpolation_type = InterpolationType.POWER;
            }
            
            output = reader.readLine();
            if (output.equals (OutlierResponse.WARN.toString())) {
                this.default_outlier_reaction = OutlierResponse.WARN;
            } else {
                this.default_outlier_reaction = OutlierResponse.REMOVE;
            }
            
            // Tell everyone that you updated something!
            for (ChangeListener c : listeners) {
                System.out.println ("Listenter: " + c.toString ());
            }
            this.notifyListeners ();

        } catch (FileNotFoundException e) {
            // Catch for File "f" not found.
            // TODO Properly deal with this exception.
            e.printStackTrace();
            
        } catch (IOException e) {
            // Catch for BufferedReader "reader" giving problems that don't include null!
            System.out.println ("Uh, we have a problem! There's a Saving error!");
            e.printStackTrace ();
        }

	}
	
	/**
     * Saves template in a plain text format for simplicity.
     * Uses BufferedWriter in order to create and manipulate said object.
     * 
     * @param f File name to be opened in order to save template.
     */
	public void saveTemplate (String file_name) {
	 // See if we can get this to work. Otherwise, throw an error!
        try (BufferedWriter writer = new BufferedWriter (new FileWriter (new File (file_name)))){
            // Combine the entirety of the data to write in a single string!
            StringBuilder sb = new StringBuilder ();
            
            // Classifications
            sb.append (this.chart_type.toString () + System.getProperty ("line.separator"));
            // Label names.
            sb.append (this.chart_name + System.getProperty ("line.separator"));
            sb.append (this.x_axis_label + System.getProperty ("line.separator"));
            sb.append (this.y_axis_label + System.getProperty ("line.separator"));
            // Including Features.
            sb.append (Boolean.toString (using_legend) + System.getProperty ("line.separator"));
            sb.append (Boolean.toString (using_tooltips) + System.getProperty ("line.separator"));
            sb.append (Boolean.toString (generate_urls) + System.getProperty ("line.separator"));
            // Layout Features
            sb.append (this.orientation.toString () + System.getProperty ("line.separator"));
            // Tool Features
            sb.append (this.default_interpolation_type.toString () + System.getProperty ("line.separator"));
            sb.append (this.default_outlier_reaction.toString () + System.getProperty ("line.separator"));

            // Write it to the BufferedWriter
            writer.write (sb.toString ());
            
        } catch (IOException e) {
            // Catch for File "f" not being writable.
            // TODO Properly deal with this exception.
            e.printStackTrace();
        }
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

	@Override
	public String toString () {
	    String s = "";
	    s += ("Type: " + chart_type.toString () + "\n" + 
	        "Name: " + chart_name + "\n" + 
	        "X Axis: " + x_axis_label + "\n" + 
	        "Y Axis: " + y_axis_label + "\n" + 
            "Orientation: " + orientation.toString () + "\n" + 
            "Interpolation: " + default_interpolation_type.toString () + "\n" + 
            "Outlier Reaction: " + default_outlier_reaction.toString ());
	    return (s);
	}
	
	// Getters and Setters
	
	/**
	 * @return the chart_type
	 */
	public final ChartType getChartType () {
		return chart_type;
	}

	/**
	 * @param graph the chart_type to set
	 */
	public final void setChartType (ChartType graph) {
		this.chart_type = graph;
	}

	/**
	 * @return the chart_name
	 */
	public final String getChartName () {
		return chart_name;
	}

	/**
	 * @param chart_name the chart_name to set
	 */
	public final void setChartName (String chart_name) {
		this.chart_name = chart_name;
	}

	/**
	 * @return the x_axis_label
	 */
	public final String getXAxisLabel () {
		return x_axis_label;
	}

	/**
	 * @param x_axis_label the x_axis_label to set
	 */
	public final void setXAxisLabel (String x_axis_label) {
		this.x_axis_label = x_axis_label;
	}

	/**
	 * @return the y_axis_label
	 */
	public final String getYAxisLabel () {
		return y_axis_label;
	}

	/**
	 * @param y_axis_label the y_axis_label to set
	 */
	public final void setYAxisLabel (String y_axis_label) {
		this.y_axis_label = y_axis_label;
	}

	/**
	 * @return the using_legend
	 */
	public final boolean generatesLegend () {
		return using_legend;
	}

	/**
	 * @param using_legend the using_legend to set
	 */
	public final void setLegend (boolean using_legend) {
		this.using_legend = using_legend;
	}

	/**
	 * @return the using_tooltips
	 */
	public final boolean generatesTooltips () {
		return using_tooltips;
	}

	/**
	 * @param using_tooltips the using_tooltips to set
	 */
	public final void setTooltips (boolean using_tooltips) {
		this.using_tooltips = using_tooltips;
	}

	/**
	 * @return the generate_urls
	 */
	public final boolean generatesURLs () {
		return generate_urls;
	}

	/**
	 * @param generate_urls the generate_urls to set
	 */
	public final void setURLs (boolean generate_urls) {
		this.generate_urls = generate_urls;
	}

	/**
	 * @return the orientation
	 */
	public final PlotOrientation getOrientation () {
		return orientation;
	}

	/**
	 * @param orientation the orientation to set
	 */
	public final void setOrientation (PlotOrientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * @return the default_interpolation_type
	 */
	public final InterpolationType getInterpolationType () {
		return default_interpolation_type;
	}

	/**
	 * @param default_interpolation_type the default_interpolation_type to set
	 */
	public final void setInterpolationType (InterpolationType default_interpolation_type) {
		this.default_interpolation_type = default_interpolation_type;
	}

	/**
	 * @return the default_outlier_reaction
	 */
	public final OutlierResponse getOutlierResponse () {
		return default_outlier_reaction;
	}

	/**
	 * @param default_outlier_reaction the default_outlier_reaction to set
	 */
	public final void setOutlierResponse (OutlierResponse default_outlier_reaction) {
		this.default_outlier_reaction = default_outlier_reaction;
	}
	
	// Event Methods
	/**
	 * Adds the listener provided to the notification list.
	 * 
	 * @param listener Listener to add to the notification list.
	 */
	public void addChangeListener (ChangeListener listener) {
	    System.out.println ("Added someone!");
	    this.listeners.add (listener);
	}
	
	/**
	 * Removes the listener provided from the notification list.
	 * 
	 * @param listener Listener to remove from notification list.
	 */
	public void removeChangeListener (ChangeListener listener) {
	    System.out.println ("Removed someone!");
	    this.listeners.remove (listener);
	}
	
	/**
	 * Sends a ChangeEvent to all listeners of this object,
	 * declaring that this Template object has been changed in some way.
	 */
	public void notifyListeners () {
	    for (ChangeListener c : listeners) {
	        System.out.println ("Notifying: " + c.toString ());
	        c.stateChanged (new ChangeEvent (this));
	    }
	}

}