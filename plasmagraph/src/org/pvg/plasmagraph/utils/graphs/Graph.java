package org.pvg.plasmagraph.utils.graphs;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.exceptions.InvalidParametersException;
import org.pvg.plasmagraph.utils.template.Template;

/**
 * TODO
 * 
 * @author Plasma Visualization Group
 */
public interface Graph {
	
	/**
	 * <p>Creates a Dataset specifically for the purposes of graphing the data 
	 * using the DataSet's provided values.
	 * 
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @param p 
	 * @return A Dataset containing the DataSet's data values
	 * @throws InvalidParametersException Whenever an invalid column is found in this process.
	 */
	Dataset createDataset (Template t, DataSet ds, GraphPair p) throws InvalidParametersException;
	
	/**
	 * <p>Creates a JFreeChart, an object containing the visual representation
	 * of the requested graph, from a group of settings and a JFreeChart
	 * Dataset.
	 * 
	 * @param set Dataset reference used in the creation of the graph.
	 * @param t Template reference used in the formation of various parts 
	 * of the graph.
	 * @param p 
	 * @return A JFreeChart containing the visual representation of the graph.
	 */
	JFreeChart createChart (Dataset set, Template t, GraphPair p);

	/**
	 * <p>Getter method. Provides the internal JFreeChart object that contains the graph.
	 * 
	 * @return A JFreeChart created by the object.
	 */
	JFreeChart getChart ();
	
	/**
	 * <p>Opens up a JFrame containing the graph! Primarily used for testing purposes.
	 */
	void testGraph ();
}
