package org.pvg.plasmagraph.utils.graphs;

import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.IncorrectParametersException;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.graphs.XYGraph;

/**
 * Manages the creation and displaying of the various graphs.
 * 
 * @author Gerardo A. Navas Morales
 */
public class GraphViewer {
	
	/**
	 * Creates and shows an XYGraph JFrame automatically.
	 * 
	 * @param t Template reference used in the formation of various parts of the graph.
	 * @param hd HeaderData reference used in the creation of the graph.
	 * @param p GraphPair that contains the columns to graph.
	 * 
	 * @throws IncorrectParametersException
	 */
	public static void createXYGraph (Template t, HeaderData hd, GraphPair p) throws IncorrectParametersException {
		if (t.getGroupByColumn () == "None") {
			XYGraph graph = new XYGraph (t, hd, p);
			
			graph.pack ();
			graph.setVisible (true);
		} else {
			XYGraph graph = new XYGraph (t, hd, p);
			
			graph.pack ();
			graph.setVisible (true);
		}
	}
	
	/**
	 * Creates and shows an XYGraph JFrame automatically. <p>
	 * Made specifically for Outlier Searching.
	 * 
	 * @param t Template reference used in the formation of various parts of the graph.
	 * @param ds DataSet reference used in the creation of the graph.
	 * @param p GraphPair that contains the columns to graph.
	 */
	public static void createXYGraph (Template t, DataSet ds, GraphPair p) {
		if (t.getGroupByColumn () == "None") {
			XYGraph graph = new XYGraph (t, ds, p);
			
			graph.pack ();
			graph.setVisible (true);
		} else {
			XYGraph graph = new XYGraph (t, ds, p);
			
			graph.pack ();
			graph.setVisible (true);
		}
	}
	
	/**
	 * Creates and shows a BarGraph JFrame automatically.
	 * 
	 * @param t Template reference used in the formation of various parts of the graph.
	 * @param hd HeaderData reference used in the creation of the graph.
	 * @param p GraphPair that contains the columns to graph.
	 */
	public static void createBarGraph (Template t, HeaderData hd, GraphPair p) {
		BarGraph graph = new BarGraph (t, hd, p);
		
		graph.pack ();
		graph.setVisible (true);
	}

}
