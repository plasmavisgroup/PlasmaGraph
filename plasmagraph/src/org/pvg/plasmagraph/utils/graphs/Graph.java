package org.pvg.plasmagraph.utils.graphs;

import javax.swing.JPanel;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.template.Template;

public interface Graph {
	
	/**
	 * Creates a JPanel containing the chart. Sets the availability of graph-saving.
	 * 
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @return A JPanel containing the graph.
	 */
	JPanel createJPanel (Template t, DataSet ds);
	
	/**
	 * Creates a Dataset specifically for the purposes of graphing the data 
	 * using the DataSet's provided values.
	 * 
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @return A Dataset containing the DataSet's data values
	 */
	Dataset createDataset (Template t, DataSet ds);
	
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
	JFreeChart createChart (Dataset set, Template t);
}
