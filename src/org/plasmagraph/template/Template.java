package org.plasmagraph.template;

import org.jfree.chart.plot.PlotOrientation;

public class Template {
	// Variables
	// Classifications
	public String chart_type;
	public String chart_sub_type;
	
	// Label names.
	public String chart_name;
	public String x_axis_name;
	public String y_axis_name;
	
	// Including Features.
	public boolean using_legend;
	public boolean using_tooltips;
	public boolean generate_urls;
	
	// Layout Features
	public PlotOrientation orientation;
	public int x_minimum;
	public int x_maximum;
	public int y_minimum;
	public int y_maximum;
	
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
		this.x_axis_name		= "X Axis";
		this.y_axis_name		= "Y Axis";
		this.using_legend 		= true;
		this.using_tooltips 	= true;
		this.generate_urls 		= false;
		this.orientation 		= PlotOrientation.HORIZONTAL;
		this.x_minimum			= 0;
		this.x_maximum 			= 0;
		this.y_minimum			= 0;
		this.y_maximum			= 0;
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
		this.x_axis_name 		= x;
		this.y_axis_name 		= y;
		this.using_legend 		= legend;
		this.using_tooltips 	= tooltips;
		this.generate_urls 		= urls;
		this.orientation		= o;
		this.x_minimum			= Integer.MIN_VALUE;
		this.x_maximum 			= Integer.MIN_VALUE;
		this.y_minimum			= Integer.MIN_VALUE;
		this.y_maximum			= Integer.MIN_VALUE;
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
	 * @param x_min The lower X-Axis boundary for the graph. (int)
	 * @param x_max The upper X-Axis boundary for the graph. (int)
	 * @param y_min The lower Y-Axis boundary for the graph. (int)
	 * @param y_max The upper Y-Axis boundary for the graph. (int)
	 * @returns Nothing.
	 */
	public Template (String name, String type, String x, String y, 
			boolean legend, boolean tooltips, boolean urls, PlotOrientation o,
			int x_min, int x_max, int y_min, int y_max) {
		this.chart_name 		= name;
		this.chart_type 		= type;
		this.x_axis_name 		= x;
		this.y_axis_name 		= y;
		this.using_legend 		= legend;
		this.using_tooltips 	= tooltips;
		this.generate_urls 		= urls;
		this.orientation		= o;
		this.x_minimum			= x_min;
		this.x_maximum 			= x_max;
		this.y_minimum			= y_min;
		this.y_maximum			= y_max;
	}
	
	public int getMinimumDrawWidth () {
		return (this.x_maximum - this.x_minimum);
	}
	
	public int getMinimumDrawHeight () {
		return (this.y_maximum - this.y_minimum);
	}
}