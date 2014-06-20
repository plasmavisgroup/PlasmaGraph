package org.pvg.plasmagraph.models;

import javax.swing.event.ChangeListener;

import org.jfree.chart.JFreeChart;
import org.pvg.plasmagraph.controllers.MessageLogController;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.MessageLog;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
import org.pvg.plasmagraph.utils.exceptions.InvalidParametersException;
import org.pvg.plasmagraph.utils.graphs.Graph;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.interpolation.Interpolator;
import org.pvg.plasmagraph.utils.tools.outlierscan.OutlierSearch;
import org.pvg.plasmagraph.utils.types.ChartType;

/**
 * <p>Model Class for the Graph window's MVC.
 * 
 * <p>Handles the calling of classes when the Graph button has been pressed.
 * 
 * @author Plasma Visualization Group
 */
public class GraphModel {

	// Externally-contained variables.
    /** Reference to PlasmaGraph's Template, passed via constructor reference. */
    private Template t;
    /** Reference to PlasmaGraph's HeaderData, passed via constructor reference. */
    private HeaderData hd;
    /** Reference to PlasmaGraph's GraphPair, passed via constructor reference. */
    private GraphPair p;
    /** Reference to PlasmaGraph's MessageLog, passed via constructor reference. */
    private MessageLog ml;
    /** Reference to PlasmaGraph's MessageLog Controller, passed via constructor reference. */
    private MessageLogController ml_controller;
	/** Reference to Interpolator object. */
	private Interpolator interpolator;

	/**
	 * <p>Constructor for GraphModels. Used only by the PlasmaGraph class, and only
	 * used once.
	 * @param ml_controller 
	 * 
	 * @param hd_reference
	 *            Reference to HeaderData object.
	 * @param p_reference
	 *            Reference to GraphPair object.
	 * @param t_reference
	 *            Reference to Template object.
	 * @param ml_reference 
	 * 			  Reference to MessageLog object.
	 * @param ml_controller_reference
	 * 			  Reference to MessageLog object.
	 */
	public GraphModel (HeaderData hd_reference, GraphPair p_reference, 
			Template t_reference, MessageLog ml_reference, 
			MessageLogController ml_controller_reference) {
		this.hd = hd_reference;
		this.p = p_reference;
		this.t = t_reference;
		this.ml = ml_reference;
		this.ml_controller = ml_controller_reference;
	}

	/**
	 * <p>Graphs the columns specified in GraphPair p with data in DataSet ds
	 * according to the settings in Template t. Uses JFreeChart to create the
	 * appropriate graph!
	 * 
	 * @return A JFreeChart object containing an XYGraph or a BarGraph to display.
	 */
	public JFreeChart graph () {
		
		this.interpolator = null;
		
		if (t.isSearching ()) {

			return scannedGraphing ().getChart ();

		} else {

			return unscannedGraphing ().getChart ();

		}
	}

	/**
	 * <p>Graphs the columns specified in GraphPair p with data in DataSet ds
	 * according to the settings in Template t. Uses JFreeChart to create the
	 * appropriate graph! Does not scan the data for outliers before other
	 * functions.
	 * 
	 * @param interpolation_switch
	 */
	public Graph unscannedGraphing () {
		
		/*System.out.println ("Ready status: " + p.isReady ());
		System.out.println ("Grouped status: " + p.isGrouped ());
		System.out.println (p.toString ());
		System.out.println (p.getIndexes ());
		System.out.println (t.isInterpolating ());*/

		try {
	
			if (t.isInterpolating ()) {
				
				this.interpolator = new Interpolator (hd, t, p);
				return (interpolator.interpolate ());

			} else {

				if (ChartType.XY_GRAPH.equals (t.getChartType ())) {
					
					// Create the graph
					return (new XYGraph (t, hd, p));

				} else {// if (t.getChartType ().equals (ChartType.BAR_GRAPH)) {

					// Create the graph
					/*return (new BarGraph (t, hd, p));*/

					return (this.graphEmptyChart ());
				}
			}
		
		} catch (InvalidParametersException e) {

			e.showMessage ();
			return (this.graphEmptyChart ());
		}
	}

	/**
	 * <p>Graphs the columns specified in GraphPair p with data in DataSet ds
	 * according to the settings in Template t. Uses JFreeChart to create the
	 * appropriate graph! Scans the data for outliers before other functions.
	 * 
	 * @param interpolation_switch
	 */
	public Graph scannedGraphing () {
		try {
			
			// Now, scan and show the graph after scanning, if requested.
			DataSet ds = OutlierSearch.scanForOutliers (this.hd, this.t, this.p, this.ml);

			if (t.isInterpolating ()) {
				
				this.interpolator = new Interpolator (ds, t, p);
				return (interpolator.interpolate ());

			} else {

				if (ChartType.XY_GRAPH.equals (t.getChartType ())) {
					// Create the graph
					return (new XYGraph (t, ds, p));

				} else {// if (t.getChartType ().equals (ChartType.BAR_GRAPH)) {

					// Create the graph
					/*return (new BarGraph (t, ds, p));*/

					return (this.graphEmptyChart ());
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

		} else {

			return (new XYGraph (t));
		}
	}

	/**
	 * <p>Adds a ChangeListener connected to the Template.
	 * 
	 * @param graphViewTemplateListener
	 *            The ChangeListener connected to the desired object.
	 */
	public void addTemplateChangeListener (
			ChangeListener graphViewTemplateListener) {
		this.t.addChangeListener (graphViewTemplateListener);
	}

	/**
	 * <p>Adds a ChangeListener connected to the HeaderData.
	 * 
	 * @param graphViewHeaderDataListener
	 *            The ChangeListener connected to the desired object.
	 */
	public void addHeaderDataChangeListener (
			ChangeListener graphViewHeaderDataListener) {
		this.hd.addChangeListener (graphViewHeaderDataListener);
	}

	/**
	 * <p>Adds a ChangeListener connected to the GraphPair.
	 * 
	 * @param graphViewReferenceListener
	 *            The ChangeListener connected to the desired object.
	 */
	public void addGraphPairChangeListener (
			ChangeListener graphViewReferenceListener) {
		this.p.addChangeListener (graphViewReferenceListener);
	}

	/**
	 * <p>Helper method. Provides easy access to System.out.println ();
	 * 
	 * @param txt
	 *            Text to print in console.
	 */
	@SuppressWarnings ("unused")
	private void log (String txt) {
		System.out.println (txt);
	}
	
	/**
	 * <p>Getter method. Provides external access to the Template object of this
	 * program.
	 * 
	 * @return The Template object being used.
	 */
	public Template getTemplate () {
		return (t);
	}

	/**
	 * <p>Getter method. Provides external access to the HeaderData object.
	 * 
	 * @return The HeaderData object being used.
	 */
	public HeaderData getHeaderData () {
		return (this.hd);
	}

	/**
	 * <p>Getter method. Provides external access to the GraphPair object.
	 * 
	 * @return The GraphPair object being used.
	 */
	public GraphPair getGraphPair () {
		return (this.p);
	}

	/**
	 * <p>Getter method. Provides external access to the Interpolator object.
	 * 
	 * @return The Interpolator object being used.
	 */
	public Interpolator getInterpolation () {
		return (this.interpolator);
	}

	/**
	 * <p>Getter method. Provides the MessageLog's string representation to external objects.
	 * 
	 * @return The string of all the messages in the MessageLog.
	 */
	public void updateLog () {
		this.ml_controller.append (ml);
	}

	/**
	 * <p>Getter method. Provides the MessageLog's size.
	 * 
	 * @return An integer representing the number of messages in the MessageLog.
	 */
	public int getLogSize () {
		return (this.ml.size ());
	}
}
