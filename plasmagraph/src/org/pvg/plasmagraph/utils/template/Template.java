package org.pvg.plasmagraph.utils.template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.jfree.chart.plot.PlotOrientation;

public class Template {
	// Variables
	// Classifications
	private String chart_type;
	
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
	private String default_interpolation_type;
	private String default_outlier_reaction;
	
	// Constructors
	/**
	 *  Constructor for Template objects.
	 *  Provides default values to new Template object.
	 *  @param Nothing.
	 *  @returns Nothing.
	 */
	public Template () {
		// Use the defaults!
		this.chart_name 				= "Default Chart Name";
		this.chart_type 				= "XY Chart";
		this.x_axis_label				= "X Axis";
		this.y_axis_label				= "Y Axis";
		this.using_legend 				= true;
		this.using_tooltips 			= true;
		this.generate_urls 				= false;
		this.orientation 				= PlotOrientation.HORIZONTAL;
		this.default_interpolation_type = "Polynomial";
		this.default_outlier_reaction 	= "Warn";
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
	public Template (String name, String type, String x, String y, 
			boolean legend, boolean tooltips, boolean urls, PlotOrientation o,
			String interpolation, String outlier) {
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
	 * Constructor for Template objects.
	 * Provides user-assigned values to new Template object based on file selected.
	 * Essentially, this is the nonexistent "openTemplate (...)" method.
	 * @param f File being opened.
	 * @returns Nothing.
	 */
	public Template (File f) {
		// See if we can get this to work. Otherwise, throw an error!
		try {
			// Can we get the Readers working?
			BufferedReader reader = new BufferedReader (new FileReader (f));
			String output;
			// Now, read and put in the correct place!
			// Classifications
			output = reader.readLine();
			this.chart_type = output;

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
			if (output.equals(PlotOrientation.HORIZONTAL.toString())) {
				this.orientation = PlotOrientation.HORIZONTAL;
			} else {
				this.orientation = PlotOrientation.VERTICAL;
			}
			
			// Tool Features
			output = reader.readLine();
			this.default_interpolation_type = output;
			output = reader.readLine();
			this.default_outlier_reaction = output;
			
			
			// Close the File-Reading Stream "reader".
			reader.close();
		} catch (FileNotFoundException e) {
			// Catch for File "f" not found.
			// TODO Properly deal with this exception.
			e.printStackTrace();
		} catch (IOException e) {
			// Catch for BufferedReader "reader" giving problems that don't include null!
			// TODO Properly deal with this exception.
			e.printStackTrace();
		}

	}
	
	/**
	 * Saves template in a plain text format for simplicity.
	 * Uses FileWriter in order to create and manipulate said object.
	 * 
	 * @param f File to be opened in order to save template.
	 */
	public void saveTemplate (File f) {
		// See if we can get this to work. Otherwise, throw an error!
		try {
			// Can we get the Readers working?
			FileWriter writer = new FileWriter (f);
			// Okay. Write all the things into the file.
			// Classifications
			writer.write("" + this.chart_type + "\n");
			// Label names.
			writer.write("" + this.chart_name + "\n");
			writer.write("" + this.x_axis_label + "\n");
			writer.write("" + this.y_axis_label + "\n");
			// Including Features.
			writer.write("" + Boolean.toString(this.using_legend) + "\n");
			writer.write("" + Boolean.toString(this.using_tooltips) + "\n");
			writer.write("" + Boolean.toString(this.generate_urls) + "\n");
			// Layout Features
			writer.write("" + this.orientation.toString() + "\n");
			// Tool Features
			writer.write("" + this.default_interpolation_type + "\n");
			writer.write("" + this.default_outlier_reaction + "\n");
			
			writer.close();
		} catch (IOException e) {
			// Catch for File "f" not being writable.
			// TODO Properly deal with this exception.
			e.printStackTrace();
		}
	}

	
	// Getters and Setters
	
	/**
	 * @return the chart_type
	 */
	public final String getChartType () {
		return chart_type;
	}

	/**
	 * @param chart_type the chart_type to set
	 */
	public final void setChartType (String chart_type) {
		this.chart_type = chart_type;
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
	public final String getInterpolationType () {
		return default_interpolation_type;
	}

	/**
	 * @param default_interpolation_type the default_interpolation_type to set
	 */
	public final void setInterpolationType (String default_interpolation_type) {
		this.default_interpolation_type = default_interpolation_type;
	}

	/**
	 * @return the default_outlier_reaction
	 */
	public final String getOutlierResponse () {
		return default_outlier_reaction;
	}

	/**
	 * @param default_outlier_reaction the default_outlier_reaction to set
	 */
	public final void setOutlierResponse (String default_outlier_reaction) {
		this.default_outlier_reaction = default_outlier_reaction;
	}

}