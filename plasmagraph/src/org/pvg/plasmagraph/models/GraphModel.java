package org.pvg.plasmagraph.models;

import javax.swing.event.ChangeListener;

import org.jfree.chart.JFreeChart;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
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
 * @author Gerardo A. Navas Morales
 */
public class GraphModel {

	/** Reference to Header Data object. */
	HeaderData hd;
	/** Reference to DataReference object. */
	DataReference dr;
	/** Reference to Template object. */
	Template t;

	/**
	 * Constructor for GraphModels. Used only by the PlasmaGraph class, and only
	 * used once.
	 * 
	 * @param hd
	 *            Reference to HeaderData object.
	 * @param dr
	 *            Reference to DataReference object.
	 * @param t
	 *            Reference to Template object.
	 */
	public GraphModel (HeaderData hd, DataReference dr, Template t) {
		this.hd = hd;
		this.dr = dr;
		this.t = t;
	}

	/**
	 * Graphs the columns specified in DataReference dr with data in DataSet ds
	 * according to the settings in Template t. Uses JFreeChart to create the
	 * appropriate graph!
	 * 
	 * @return A JFreeChart object containing an XYGraph or a BarGraph to display.
	 */
	public JFreeChart graph () {

		if (t.isSearching ()) {

			return scannedGraphing (t.isInterpolating ()).getChart ();

		} else {

			return unscannedGraphing (t.isInterpolating ()).getChart ();

		}

	}

	/**
	 * Graphs the columns specified in DataReference dr with data in DataSet ds
	 * according to the settings in Template t. Uses JFreeChart to create the
	 * appropriate graph! Does not scan the data for outliers before other
	 * functions.
	 * 
	 * @param interpolation_switch
	 */
	private Graph unscannedGraphing (boolean interpolation_switch) {
		
		// Verify if data exists in the HeaderData object.
		if (!(hd.isEmpty () && dr.isEmpty ())) {

			if (interpolation_switch) {

				return (Interpolator.interpolate (hd, t, dr.get ()));

			} else {

				if (ChartType.XY_GRAPH.equals (t.getChartType ())) {
					
					// Create the graph
					return (new XYGraph (t, hd, dr.get ()));

				} else {// if (t.getChartType ().equals (ChartType.BAR_GRAPH)) {

					// Create the graph
					return (new BarGraph (t, hd, dr.get ()));

				}
			}
		} else {

			// Create a dummy graph, instead.
			if (ChartType.XY_GRAPH.equals (t.getChartType ())) {

				// Create a dummy XYGraph.
				return (new XYGraph (t));

			} else {// if (t.getChartType ().equals (ChartType.BAR_GRAPH)) {

				// Create a dummy BarGraph.
				return (new BarGraph (t));

			}

		}
	}

	/**
	 * Graphs the columns specified in DataReference dr with data in DataSet ds
	 * according to the settings in Template t. Uses JFreeChart to create the
	 * appropriate graph! Scans the data for outliers before other functions.
	 * 
	 * @param interpolation_switch
	 */
	private Graph scannedGraphing (boolean interpolation_switch) {
		try {
			// Verify if data exists in the HeaderData object.
			if (!hd.isEmpty () && dr.isEmpty ()) {
	
				DataSet ds = OutlierSearch.scanForOutliers (hd, t, dr.get ());
	
				if (interpolation_switch) {
	
					return (Interpolator.interpolate (ds, t, dr.get ()));
	
				} else {
	
					if (ChartType.XY_GRAPH.equals (t.getChartType ())) {
						// Create the graph
						return (new XYGraph (t, ds, dr.get ()));
	
					} else {// if (t.getChartType ().equals (ChartType.BAR_GRAPH)) {
	
						// Create the graph
						return (new BarGraph (t, ds, dr.get ()));
	
					}
				}
	
			} else {
	
				// Create a dummy graph, instead.
				if (ChartType.XY_GRAPH.equals (t.getChartType ())) {
	
					// Create a dummy XYGraph.
					return (new XYGraph (t));
	
				} else {// if (t.getChartType ().equals (ChartType.BAR_GRAPH)) {
	
					// Create a dummy BarGraph.
					return (new BarGraph (t));
	
				}
	
			}
		
		} catch (FunctionNotImplementedException e) {
			e.showMessage ();
			return null;
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
	 * Adds a ChangeListener connected to the DataReference.
	 * 
	 * @param graphViewReferenceListener
	 *            The ChangeListener connected to the desired object.
	 */
	public void addDataReferenceChangeListener (
			ChangeListener graphViewReferenceListener) {
		this.dr.addChangeListener (graphViewReferenceListener);
	}

	/**
	 * Helper method. Provides easy access to System.out.println ();
	 * 
	 * @param txt
	 *            Text to print in console.
	 */
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
	 * Getter method. Provides external access to the DataReference object.
	 * 
	 * @return The DataReference object being used.
	 */
	public DataReference getDataReference () {
		return (this.dr);
	}
}
