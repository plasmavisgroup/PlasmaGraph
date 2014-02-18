package org.pvg.plasmagraph.utils.graphs;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.template.Template;

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
@SuppressWarnings ("serial")
public class BarGraph extends JFrame implements Graph {
	JFreeChart chart;

	// Constructors
	/**
	 * Basic constructor. Creates a BarGraph from a Template and DataSet reference.
	 * 
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 */
	public BarGraph (Template t, DataSet ds) {
		super(t.getChartName ());
		setContentPane (createJPanel (t, ds));
	}
	
	/**
	 * Creates a JPanel containing the chart. Sets the availability of graph-saving.
	 * 
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @return A JPanel containing the graph.
	 */
	public JPanel createJPanel (Template t, DataSet ds) {
		chart = createChart (createDataset(t, ds), t);
		ChartPanel c = new ChartPanel (chart, false, true, false, true, true);
		return (c);
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
	public DefaultCategoryDataset createDataset (Template t, DataSet ds) {
		//DefaultCategoryDataset set = new DefaultCategoryDataset ();
		//generateTestDataset (set, t);
		
		//return (set);
		return (ds.toBarGraphDataset ());
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
	public JFreeChart createChart (Dataset set, Template t) {
		JFreeChart c = ChartFactory.createBarChart (t.getChartName (),
				t.getYAxisLabel (), t.getXAxisLabel (), (DefaultCategoryDataset) set,
				t.getOrientation (), t.generatesLegend (),
				t.generatesTooltips (), t.generatesURLs ());
		
		//org.jfree.chart.plot.BarChart plot = chart.getBarChart();
		
		return (c);
	}
}