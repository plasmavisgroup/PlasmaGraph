package org.pvg.plasmagraph.utils.graphs;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.types.AxisType;

/**
 * Graph class.
 * Contains all the methods required to take in data of any kind that the
 * rest of the system may provide it and shape it into a proper graph
 * according to the settings in the Template.
 * 
 * Manages the graphing of any and all Bar Charts.
 * 
 * @author Gerardo A. Navas Morales
 */
public class BarGraph implements Graph {
	JFreeChart chart;

	/**
	 * Constructor. Makes a graph without the necessary data.
	 * 
	 * @param t Template reference used in the formation of various parts of the graph.
	 */
	public BarGraph (Template t) {
		chart = createChart (createDataset (t), t);
	}

	/**
	 * Helper method. Automates the changes to create a default Bar Graph Dataset.
	 * 
	 * @return A CategoryDataset with a default set of data points.
	 */
	private CategoryDataset createDataset (Template t) {
		// Initialize a DefaultCategoryDataset.
		DefaultCategoryDataset chart_dataset = new DefaultCategoryDataset ();
		
		// Populate a DefaultCategoryDataset with a single value.
		chart_dataset.addValue (1.0, "Row", "Column");
		
		return (chart_dataset);
	}
	
	/**
	 * Helper method. Automates the changes to create a default Bar Graph.
	 * 
	 * @return A JFreeChart with a default set of data points.
	 */
	private JFreeChart createChart (CategoryDataset chart_dataset, Template t) {
		// Make the chart.
		JFreeChart c = ChartFactory.createBarChart (t.getChartName (), 
				t.getXAxisLabel (), 
				t.getYAxisLabel (), chart_dataset);
		
		// Edit the axes.
		this.modifyPlot (t, c);
		
		return (c);
	}

	/**
	 * Constructor. Does not manage grouped data.
	 * 
	 * @param t Template reference used in the formation of various parts of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @param p GraphPair that contains the columns to graph.
	 */
	public BarGraph (Template t, DataSet ds, GraphPair p) {
		chart = createChart (createDataset(t, ds, p), t, p);
	}
	
	/**
	 * Constructor. Can switch between managing grouped data and not.
	 * 
	 * @param t Template reference used in the formation of various parts of the graph.
	 * @param hd HeaderData reference used in the creation of the graph.
	 * @param p GraphPair that contains the columns to graph.
	 */
	public BarGraph (Template t, HeaderData hd, GraphPair p) {
		if (p.isGrouped ()) {
			chart = createChart (createDataset(t, hd.populateGroupedData (p, t), p), t, p);
		} else {
			chart = createChart (createDataset(t, hd.populateData (p), p), t, p);
		}
	}
	
	/**
	 * Creates a DefaultCategoryDataset specifically for the purposes of
	 * graphing the data using the DataSet's provided values.
	 * 
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @return A Dataset containing the DataSet's data values
	 */
	@Override
	public DefaultCategoryDataset createDataset (Template t, DataSet ds, GraphPair p) {
		
		return (ds.toBarGraphDataset (p));
		
	}
	
	/**
	 * Creates a JFreeChart, an object containing the visual representation
	 * of the requested graph, from a group of settings and a JFreeChart
	 * Dataset.
	 * 
	 * @param set Dataset reference used in the creation of the graph.
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @return A JFreeChart containing the visual representation of the graph.
	 */
	@Override
	public JFreeChart createChart (Dataset set, Template t, GraphPair p) {
		JFreeChart c = ChartFactory.createBarChart (t.getChartName (),
				t.getYAxisLabel (), t.getXAxisLabel (), (DefaultCategoryDataset) set,
				t.getOrientation (), t.generatesLegend (),
				t.generatesTooltips (), t.generatesURLs ());
		
		// Set axis names and scales, and other chart things.
		this.modifyPlot (t, p, c);
		
		return (c);
	}
	
	private void modifyPlot (Template t, JFreeChart c) {
		// Create XYPlot
		CategoryPlot plot = (CategoryPlot) c.getCategoryPlot ();
		
		// X Axis
		// Ignored because the only x axis that matters in a BarGraph is the categories themselves.
		
		// Y Axis
		if (AxisType.STANDARD.equals (t.getYAxisType ())) {

				ValueAxis range = new NumberAxis (t.getYAxisLabel ());
				range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setRangeAxis (range);

		} else {//if (AxisType.LOG.equals (t.getYAxisType ())) {

				ValueAxis range = new LogAxis (t.getYAxisLabel ());
				range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				range.setRange (0, 10.0);
				plot.setRangeAxis (range);
			
		}
		
		// Change background color.
		this.setDefaultBackgroundColor (plot);
	}

	private void modifyPlot (Template t, GraphPair p, JFreeChart c) {
		// Create XYPlot
		CategoryPlot plot = (CategoryPlot) c.getCategoryPlot ();
		
		// X Axis
		// Ignored because the only x axis that matters in a BarGraph is the categories themselves.
		
		// Y Axis
		if (AxisType.STANDARD.equals (t.getYAxisType ())) {
			
			if (t.isDefaultYAxisLabel ()) {
				
				ValueAxis range = new NumberAxis (p.getYIndexName ());
				//range.setLabel (s[1].trim ());
				range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setRangeAxis (range);
				
			} else {
				
				ValueAxis range = new NumberAxis (t.getYAxisLabel ());
				range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setRangeAxis (range);
				
			}
			
		} else if (AxisType.LOG.equals (t.getYAxisType ())) {
			
			if (t.isDefaultYAxisLabel ()) {
				
				ValueAxis range = new LogAxis (p.getYIndexName ());
				//range.setLabel (s[1].trim ());
				range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setRangeAxis (range);
				
			} else {
				
				ValueAxis range = new LogAxis (t.getYAxisLabel ());
				range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setRangeAxis (range);
				
			}
			
		} else {
			
			// TODO
			
		}
		
		// Set Chart name.
		if (t.isDefaultChartName ()) {
			c.setTitle (p.getXIndexName () + " vs. " + p.getYIndexName ());
		}
		
		// Change background color.
		this.setDefaultBackgroundColor (plot);
	}
	
	private void setDefaultBackgroundColor (CategoryPlot plot) {
		plot.setBackgroundPaint (Color.WHITE);
		plot.setRangeGridlinePaint (Color.BLACK);
		plot.setDomainGridlinePaint (Color.BLACK);
	}

	/**
	 * Getter method. Provides the internal JFreeChart object that contains the graph.
	 * 
	 * @return A JFreeChart created by the object.
	 */
	@Override
	public JFreeChart getChart () {
		return (this.chart);
	}
	
	/**
	 * Testing method. Opens up a JFrame containing the graph!
	 */
	@Override
	public void testGraph () {
		JFrame frame = new JFrame ();
		frame.add (new ChartPanel (this.chart));
		
		frame.pack ();
		frame.setVisible (true);
	}
}