package org.pvg.plasmagraph.models;

import javax.swing.event.ChangeListener;

import org.jfree.chart.JFreeChart;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
import org.pvg.plasmagraph.utils.exceptions.InvalidParametersException;
import org.pvg.plasmagraph.utils.graphs.BarGraph;
import org.pvg.plasmagraph.utils.graphs.Graph;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.interpolation.Interpolator;
import org.pvg.plasmagraph.utils.tools.outlierscan.OutlierSearch;
import org.pvg.plasmagraph.utils.types.ChartType;

/**
 * TODO
 * 
 * @author Plasma Visualization Group
 */
public class GraphModel {

	/** Reference to Header Data object. */
	private HeaderData hd;
	/** Reference to GraphPair object. */
	private GraphPair p;
	/** Reference to Template object. */
	private Template t;
	/** Reference to Interpolator object. */
	private Interpolator interpolator;

	/**
	 * Constructor for GraphModels. Used only by the PlasmaGraph class, and only
	 * used once.
	 * 
	 * @param hd
	 *            Reference to HeaderData object.
	 * @param p
	 *            Reference to GraphPair object.
	 * @param t
	 *            Reference to Template object.
	 */
	public GraphModel (HeaderData hd, GraphPair p, Template t) {
		this.hd = hd;
		this.p = p;
		this.t = t;
	}

	/**
	 * Graphs the columns specified in GraphPair p with data in DataSet ds
	 * according to the settings in Template t. Uses JFreeChart to create the
	 * appropriate graph!
	 * 
	 * @return A JFreeChart object containing an XYGraph or a BarGraph to display.
	 */
	public JFreeChart graph () {
		
		this.interpolator = null;
		
		if (t.isSearching ()) {

			return scannedGraphing (t.isInterpolating ()).getChart ();

		} else {

			return unscannedGraphing (t.isInterpolating ()).getChart ();

		}
	}

	/**
	 * Graphs the columns specified in GraphPair p with data in DataSet ds
	 * according to the settings in Template t. Uses JFreeChart to create the
	 * appropriate graph! Does not scan the data for outliers before other
	 * functions.
	 * 
	 * @param interpolation_switch
	 */
	public Graph unscannedGraphing (boolean interpolation_switch) {
		
		//System.out.println ("Ready status: " + p.isReady ());
		//System.out.println ("Grouped status: " + p.isGrouped ());
		//System.out.println (p.toString ());
		//System.out.println (p.getIndexes ());

		try {
	
			if (interpolation_switch) {

					this.interpolator = new Interpolator (hd, t, p);
					return (interpolator.interpolate ());

			} else {

				if (ChartType.XY_GRAPH.equals (t.getChartType ())) {
					
					// Create the graph
					return (new XYGraph (t, hd, p));

				} else {// if (t.getChartType ().equals (ChartType.BAR_GRAPH)) {

					// Create the graph
					return (new BarGraph (t, hd, p));

				}
			}
		
		} catch (InvalidParametersException e) {

			e.showMessage ();
			return (this.graphEmptyChart ());
		}
	}

	/**
	 * Graphs the columns specified in GraphPair p with data in DataSet ds
	 * according to the settings in Template t. Uses JFreeChart to create the
	 * appropriate graph! Scans the data for outliers before other functions.
	 * 
	 * @param interpolation_switch
	 */
	public Graph scannedGraphing (boolean interpolation_switch) {
		try {
			
			// Now, scan and show the graph after scanning, if requested.
			DataSet ds = OutlierSearch.scanForOutliers (hd, t, p);

			if (interpolation_switch) {

				this.interpolator = new Interpolator (ds, t, p);
				return (interpolator.interpolate ());

			} else {

				if (ChartType.XY_GRAPH.equals (t.getChartType ())) {
					// Create the graph
					return (new XYGraph (t, ds, p));

				} else {// if (t.getChartType ().equals (ChartType.BAR_GRAPH)) {

					// Create the graph
					return (new BarGraph (t, ds, p));

				}
			}
		} catch (FunctionNotImplementedException ex) {
			
			ex.showMessage ();
			return (this.graphEmptyChart ());
			
		} catch (InvalidParametersException e) {
			
			e.showMessage ();
			return (this.graphEmptyChart ());
			
		}
	}
	
	public Graph graphEmptyChart () {
		// Create a dummy graph, instead.
		if (ChartType.XY_GRAPH.equals (t.getChartType ())) {

			// Create a dummy XYGraph.
			return (new XYGraph (t));

		} else {// if (t.getChartType ().equals (ChartType.BAR_GRAPH)) {

			// Create a dummy BarGraph.
			return (new BarGraph (t));

		}
	}

	/**
	 * Adds a ChangeListener connected to the Template.
	 * 
	 * @param graphViewTemplateListener
	 *            The ChangeListener connected to the desired object.
	 */
	public void addTemplateChangeListener (
			ChangeListener graphViewTemplateListener) {
		this.t.addChangeListener (graphViewTemplateListener);
	}

	/**
	 * Adds a ChangeListener connected to the HeaderData.
	 * 
	 * @param graphViewHeaderDataListener
	 *            The ChangeListener connected to the desired object.
	 */
	public void addHeaderDataChangeListener (
			ChangeListener graphViewHeaderDataListener) {
		this.hd.addChangeListener (graphViewHeaderDataListener);
	}

	/**
	 * Adds a ChangeListener connected to the GraphPair.
	 * 
	 * @param graphViewReferenceListener
	 *            The ChangeListener connected to the desired object.
	 */
	public void addGraphPairChangeListener (
			ChangeListener graphViewReferenceListener) {
		this.p.addChangeListener (graphViewReferenceListener);
	}

	/**
	 * Helper method. Provides easy access to System.out.println ();
	 * 
	 * @param txt
	 *            Text to print in console.
	 */
	@SuppressWarnings ("unused")
	private void log (String txt) {
		System.out.println (txt);
	}
	
	/**
	 * Getter method. Provides external access to the Template object of this
	 * program.
	 * 
	 * @return The Template object being used.
	 */
	public Template getTemplate () {
		return (t);
	}

	/**
	 * Getter method. Provides external access to the HeaderData object.
	 * 
	 * @return The HeaderData object being used.
	 */
	public HeaderData getHeaderData () {
		return (this.hd);
	}

	/**
	 * Getter method. Provides external access to the GraphPair object.
	 * 
	 * @return The GraphPair object being used.
	 */
	public GraphPair getGraphPair () {
		return (this.p);
	}

	/**
	 * Getter method. Provides external access to the Interpolator object.
	 * 
	 * @return The Interpolator object being used.
	 */
	public Interpolator getInterpolation () {
		return (this.interpolator);
	}
}
