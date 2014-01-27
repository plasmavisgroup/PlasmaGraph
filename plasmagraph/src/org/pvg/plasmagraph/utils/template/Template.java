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
	public String chart_type;
	public String chart_sub_type;
	
	// Label names.
	public String chart_name;
	public String x_axis_label;
	public String y_axis_label;
	
	// Including Features.
	public boolean using_legend;
	public boolean using_tooltips;
	public boolean generate_urls;
	
	// Layout Features
	public PlotOrientation orientation;
	
	// Constructors
	/**
	 *  Constructor for Template objects.
	 *  Provides default values to new Template object.
	 *  @param Nothing.
	 *  @returns Nothing.
	 */
	public Template () {
		// Use the defaults!
		this.chart_name 		= "Default Chart Name";
		this.chart_type 		= "Pie Chart";
		this.x_axis_label		= "X Axis";
		this.y_axis_label		= "Y Axis";
		this.using_legend 		= true;
		this.using_tooltips 	= true;
		this.generate_urls 		= false;
		this.orientation 		= PlotOrientation.HORIZONTAL;
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
			boolean legend, boolean tooltips, boolean urls, PlotOrientation o) {
		this.chart_name 		= name;
		this.chart_type 		= type;
		this.x_axis_label 		= x;
		this.y_axis_label 		= y;
		this.using_legend 		= legend;
		this.using_tooltips 	= tooltips;
		this.generate_urls 		= urls;
		this.orientation		= o;
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
			int x_min, int x_max, int y_min, int y_max) {
		this.chart_name 		= name;
		this.chart_type 		= type;
		this.x_axis_label 		= x;
		this.y_axis_label 		= y;
		this.using_legend 		= legend;
		this.using_tooltips 	= tooltips;
		this.generate_urls 		= urls;
		this.orientation		= o;
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
			output = reader.readLine();
			this.chart_sub_type = output;

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
			writer.write("" + this.chart_sub_type + "\n");
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
			
			writer.close();
		} catch (IOException e) {
			// Catch for File "f" not being writable.
			// TODO Properly deal with this exception.
			e.printStackTrace();
		}
	}
}