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
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
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
 * Manages the graphing of any and all XY Plots.
 * 
 * @author Gerardo A. Navas Morales
 */
public class XYGraph implements Graph {
	JFreeChart chart;

	/**
	 * Constructor. Makes a graph without the necessary data.
	 * 
	 * @param t Template reference used in the formation of various parts of the graph.
	 */
	public XYGraph (Template t) {
		chart = createChart (createDataset(t), t);
	}
	
	/**
	 * Automates the changes to create a default XY Graph.
	 * 
	 * @return A JFreeChart with a default set of data points.
	 */
	private JFreeChart createChart (XYDataset series, Template t) {
		// Make the chart.
		JFreeChart c = ChartFactory.createScatterPlot (t.getChartName (), 
				t.getXAxisLabel (), 
				t.getYAxisLabel (), series);
		
		// Edit the axes.
		this.modifyPlot (t, c);
		
		return (c);
	}

	/**
	 * Helper method. Automates the changes to create a default XY Graph Dataset.
	 * 
	 * @return An XYDataset with a default set of data points.
	 */
	private XYDataset createDataset (Template t) {
		// Populate a single-valued XYSeries centered at the origin.
		XYSeries s1 = new XYSeries ("Default");
		s1.add (0.0, 0.0);
		
		// Wrap the XYSeries in a XYSeriesCollection, which inherits from Dataset.
		return (new XYSeriesCollection (s1));
	}

	/**
	 * Constructor. Does not manage grouped data.
	 * 
	 * @param t Template reference used in the formation of various parts of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @param p GraphPair that contains the columns to graph.
	 */
	public XYGraph (Template t, DataSet ds, GraphPair p) {
		chart = createChart (createDataset(t, ds, p), t, p);
	}

	/**
	 * Constructor. Can switch between managing grouped data and not.
	 * 
	 * @param t Template reference used in the formation of various parts of the graph.
	 * @param hd HeaderData reference used in the creation of the graph.
	 * @param p GraphPair that contains the columns to graph.
	 */
	public XYGraph (Template t, HeaderData hd, GraphPair p) {
		if (p.isGrouped ()) {
			chart = createChart (createDataset(t, hd.populateGroupedData (p, t), p), t, p);
		} else {
			chart = createChart (createDataset(t, hd.populateData (p), p), t, p);
		}
	}
	
	/**
	 * Interpolation constructor. 
	 * Creates a XYGraph from a Template and XYSeriesCollection reference,
	 * which contains both the original uninterpolated XYSeries and an
	 * interpolated XYSeries.
	 * 
	 * @param t Template reference used in the formation of 
	 * various parts of the graph.
	 * @param graph_data XYSeriesCollection with two sets of data used in the 
	 * creation of the graph.
	 * @param p GraphPair reference used in 
	 */
	public XYGraph (Template t, XYSeriesCollection graph_data, GraphPair p) {
		chart = createChart (graph_data, t, p);
	}
	
	/**
	 * Creates an XYDataset specifically for the purposes of graphing the data 
	 * using the DataSet's provided values.
	 * 
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @return An XYDataset containing the DataSet's data values
	 */
	@Override
	public XYDataset createDataset (Template t, DataSet ds, GraphPair p) {
		DefaultXYDataset set = new DefaultXYDataset ();
		XYSeries s = ds.toXYGraphDataset (p);
		set.addSeries (s.getKey (), s.toArray ());

		return (set);
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
		JFreeChart c = ChartFactory.createScatterPlot(t.getChartName (), 
				t.getXAxisLabel (), t.getYAxisLabel (), 
				(XYDataset) set, t.getOrientation (), t.generatesLegend (),
				t.generatesTooltips (), t.generatesURLs ());

		// Set axis names and scales.
		this.modifyPlot (t, p, c);
		
		return (c);
	}
	
	private void modifyPlot (Template t, JFreeChart c) {
		// X Axis
		if (AxisType.STANDARD.equals (t.getXAxisType ())) {
				
			ValueAxis domain = new NumberAxis (t.getXAxisLabel ());
			domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
			domain.setRange (-10.0, 10.0);
			((XYPlot) c.getPlot ()).setDomainAxis (domain);
		
		} else {//if (AxisType.LOG.equals (t.getXAxisType ())) {
			
			ValueAxis domain = new LogAxis (t.getXAxisLabel ());
			domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
			((XYPlot) c.getPlot ()).setDomainAxis (domain);

		}
		
		// Y Axis
		if (AxisType.STANDARD.equals (t.getYAxisType ())) {
			
			ValueAxis range = new NumberAxis (t.getYAxisLabel ());
			range.setRange (-10.0, 10.0);
			range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
			((XYPlot) c.getPlot ()).setRangeAxis (range);
		
		} else {//if (AxisType.LOG.equals (t.getYAxisType ())) {
			
			ValueAxis range = new LogAxis (t.getYAxisLabel ());
			range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
			((XYPlot) c.getPlot ()).setRangeAxis (range);

		}
	}
	
	private void modifyPlot (Template t, GraphPair p, JFreeChart c) {
		// Create XYPlot
		XYPlot plot = (XYPlot) c.getXYPlot ();
		
		// X Axis
		if (AxisType.STANDARD.equals (t.getXAxisType ())) {
			
			if (t.isDefaultXAxisLabel ()) {
				
				ValueAxis domain = new NumberAxis (p.getIndex1Name ());
				domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setDomainAxis (domain);
				
			} else {
				
				ValueAxis domain = new NumberAxis (t.getXAxisLabel ());
				domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setDomainAxis (domain);
				
			}
			
		} else if (AxisType.LOG.equals (t.getXAxisType ())) {
			
			if (t.isDefaultXAxisLabel ()) {
				
				ValueAxis domain = new LogAxis (p.getIndex1Name ());//NumberAxis ();
				domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setDomainAxis (domain);
				
			} else {
				
				ValueAxis domain = new LogAxis (t.getXAxisLabel ());
				domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setDomainAxis (domain);
				
			}
			
		} else {
			
			// TODO
			
		}
		
		// Y Axis
		if (AxisType.STANDARD.equals (t.getYAxisType ())) {
			
			if (t.isDefaultYAxisLabel ()) {
				
				ValueAxis range = new NumberAxis (p.getIndex2Name ());
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
				
				ValueAxis range = new LogAxis (p.getIndex2Name ());
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
			c.setTitle (p.getIndex1Name () + " vs. " + p.getIndex2Name ());
		}
		
		// Change background color.
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
