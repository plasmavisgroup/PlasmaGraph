package org.pvg.plasmagraph.utils.graphs;

import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.IncorrectParametersException;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.graphs.XYGraph;

public class GraphViewer {
	
	public static void createXYGraph (Template t, HeaderData hd, GraphPair p) throws IncorrectParametersException {
		XYGraph graph = new XYGraph (t, hd, p);
		
		graph.pack ();
		graph.setVisible (true);
	}
	
	public static void createXYGraph (Template t, DataSet ds, GraphPair p) throws IncorrectParametersException {
		XYGraph graph = new XYGraph (t, ds, p);
		
		graph.pack ();
		graph.setVisible (true);
	}
	
	public static void createBarGraph (Template t, HeaderData hd, GraphPair p) {
		BarGraph graph = new BarGraph (t, hd, p);
		
		graph.pack ();
		graph.setVisible (true);
	}

}
