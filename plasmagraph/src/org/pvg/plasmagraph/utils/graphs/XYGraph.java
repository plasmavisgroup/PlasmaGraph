package org.pvg.plasmagraph.utils.graphs;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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

	// Constructors
	/**
	 * Basic constructor. Creates a XYGraph from a Template and HeaderData reference.
	 * 
	 * @param t_reference Template reference used in the formation of 
	 * various parts of the graph.
	 * @param ds_reference DataSet reference used in the creation of the graph.
	 * @throws IncorrectParametersException 
	 */
	public XYGraph (Template t_reference, HeaderData hd, GraphPair p) throws IncorrectParametersException {
		this (t_reference, hd.populateGroupedData (p, t_reference).
				toGroupedXYGraphDataset (p.getName (), t_reference));
	}
	
	/**
	 * Basic constructor. Creates a XYGraph from a Template and DataSet reference.
	 * 
	 * @param t_reference Template reference used in the formation of 
	 * various parts of the graph.
	 * @param ds_reference DataSet reference used in the creation of the graph.
	 */
	public XYGraph (Template t_reference, DataSet ds, GraphPair p) {
		super (t_reference.getChartName ());
		setContentPane (createJPanel (t_reference, ds, p));
	}
	
	/**
	 * Interpolation constructor. 
	 * Creates a XYGraph from a Template and XYSeriesCollection reference,
	 * which contains both the original uninterpolated XYSeries and an
	 * interpolated XYSeries.
	 * 
	 * @param t_reference Template reference used in the formation of 
	 * various parts of the graph.
	 * @param graph_data XYSeriesCollection with two sets of data used in the 
	 * creation of the graph.
	 */
	public XYGraph (Template t_reference, XYSeriesCollection graph_data) {
		super (t_reference.getChartName ());
		setContentPane (createJPanel (t_reference, graph_data));
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
		chart = createChart (createDataset(t, ds, p), t);
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
	public JPanel createJPanel (Template t, XYSeriesCollection graph_data) {
		chart = createChart (graph_data, t);
		ChartPanel c = new ChartPanel (chart, false, true, false, true, true);
		return (c);
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
		XYSeries s = ds.toXYGraphDataset (p.getName ());
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
	public JFreeChart createChart (Dataset set, Template t) {
		JFreeChart c = ChartFactory.createScatterPlot(t.getChartName (), 
				t.getYAxisLabel (), t.getXAxisLabel (), 
				(XYDataset) set, t.getOrientation (), t.generatesLegend (),
				t.generatesTooltips (), t.generatesURLs ());

		org.jfree.chart.plot.XYPlot plot = c.getXYPlot();
		
		// Set axis names.
		plot.getDomainAxis ().setLabel (t.getXAxisLabel ());
		plot.getRangeAxis ().setLabel (t.getYAxisLabel ());
		
		// Set chart title.
		
		return (c);
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
		chart = createChart (createDataset(t, ds, p), t);
		ChartPanel c = new ChartPanel (chart, false, true, false, true, true);
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
}
