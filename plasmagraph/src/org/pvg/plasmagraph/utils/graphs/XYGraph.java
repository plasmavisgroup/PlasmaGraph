package org.pvg.plasmagraph.utils.graphs;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.IncorrectParametersException;
import org.pvg.plasmagraph.utils.template.Template;

// TODO: Documentation!
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
@SuppressWarnings ("serial")
public class XYGraph extends JFrame implements Graph {
	JFreeChart chart;

	/**
	 * Basic constructor.
	 * 
	 * @param t Template reference used in the formation of various parts of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @param p GraphPair that contains the columns to graph.
	 */
	public XYGraph (Template t, DataSet ds, GraphPair p) {
		super (t.getChartName ());
		setContentPane (createJPanel (t, ds, p));
	}
	
	// Constructors
	/**
	 * Constructor for graphs that use the GroupBy column.
	 * 
	 * @param t Template reference used in the formation of various parts of the graph.
	 * @param hd HeaderData reference used in the creation of the graph.
	 * @param p GraphPair that contains the columns to graph.
	 * 
	 * @throws IncorrectParametersException 
	 */
	public XYGraph (Template t, HeaderData hd, GraphPair p) throws IncorrectParametersException {
		super (t.getChartName ());
		if (t.getGroupByColumn () == "None") {
			setContentPane (createJPanel (t, hd.populateData (p), p));
		} else {
			setContentPane (createJPanel (t, hd.populateGroupedData (p, t), p));
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
	 */
	public XYGraph (Template t, XYSeriesCollection graph_data, GraphPair p) {
		super (t.getChartName ());
		setContentPane (createJPanel (t, graph_data, p));
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

		org.jfree.chart.plot.XYPlot plot = c.getXYPlot();
		
		// Set axis names and scales.
		String [] s = p.getName ().split ("vs", 2);
		System.out.println (p.getName ());
		
		// X Axis
		// Label
		if (t.isDefaultXAxisLabel ()) {
			ValueAxis domain = new NumberAxis ();
			System.out.println (s[0]);
			domain.setLabel (s[0]);
			//domain.setLabelFont (Font.getFont ("Arial"));
			plot.setDomainAxis (domain);
		}
		
		// Scale
		// TODO
		
		// Y Axis
		// Label
		if (t.isDefaultYAxisLabel ()) {
			ValueAxis range = new NumberAxis ();
			System.out.println (s[1]);
			range.setLabel (s[1]);
			//range.setLabelFont (Font.getFont ("Arial"));
			plot.setRangeAxis (range);
		}
		
		// Scale
		// TODO
		
		// Set Chart name.
		if (t.isDefaultChartName ()) {
			c.setTitle (p.getName ());
		}
		
		// Change background color.
		plot.setBackgroundPaint (Color.WHITE);
		
		return (c);
	}
	
	/**
	 * Creates a Dataset specifically for the purposes of graphing the data 
	 * using the DataSet's provided values.
	 * 
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @return A Dataset containing the DataSet's data values
	 */
	@Override
	public XYDataset createDataset (Template t, ArrayList ds, GraphPair p) {

		XYSeries this_series = new XYSeries (p.getName ());
		
		for (Object o : ds) {
			DoublePoint d = (DoublePoint) o;
			this_series.add (d.getPoint ()[0], d.getPoint ()[1]);
		}
		
		return (new XYSeriesCollection (this_series));
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
	 * Creates a JPanel containing the chart. Sets the availability of graph-saving.
	 * 
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @return A JPanel containing the graph.
	 */
	@Override
	public JPanel createJPanel (Template t, ArrayList ds, GraphPair p) {
		chart = createChart (createDataset(t, ds, p), t, p);
		ChartPanel c = new ChartPanel (chart, false, true, false, true, true);
		return (c);
	}
	
	/**
	 * Creates a JPanel containing the chart. Sets the availability of graph-saving.
	 * 
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @return JPanel containing the graph.
	 */
	@Override
	public JPanel createJPanel (Template t, DataSet ds, GraphPair p) {
		chart = createChart (createDataset(t, ds, p), t, p);
		ChartPanel c = new ChartPanel (chart, false, true, false, true, true);
		return (c);
	}

	/**
	 * Creates a JPanel containing the chart. Sets the availability of graph-saving.
	 * 
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param graph_data XYSeriesCollection with two sets of data used in the 
	 * creation of the graph.
	 * @return A JPanel containing the graph.
	 */
	public JPanel createJPanel (Template t, XYSeriesCollection graph_data, GraphPair p) {
		chart = createChart (graph_data, t, p);
		ChartPanel c = new ChartPanel (chart, false, true, false, true, true);
		return (c);
	}
}
