package org.pvg.plasmagraph.views;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.JFreeChart;
import org.pvg.plasmagraph.models.GraphModel;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.types.ChartType;

/**
 * <p>Graphical User Interface class designed to present the organization of the Graph Window, including the graph it contains.
 * 
 * @author Plasma Visualization Group
 */
@SuppressWarnings ("serial")
public class GraphView extends JFrame {
	
	/** Reference to model related to this controller. */
	private GraphModel graph_model;
	/** Chart displayed in this view. */
	private JFreeChart chart;
	
	/**
	 * Constructor for GraphViews. Used only by the PlasmaGraph class, and only used once.
	 * 
	 * @param graph_model Reference to this object's model.
	 */
	public GraphView (GraphModel graph_model) {
		this.graph_model = graph_model;
		
		this.initComponents ();
	}

	/**
	 * Triggers on changes to the HeaderData. 
	 * Resets the chart, but not its template details, to its default form.
	 */
	public void graphUpdate () {
		org.pvg.plasmagraph.utils.data.HeaderData hd = this.graph_model.getHeaderData ();
		org.pvg.plasmagraph.utils.data.GraphPair p = this.graph_model.getGraphPair ();
		
		// If the HeaderData was reset, reset the DataReference.
		// Otherwise, it was just the user adding another file, and the graph
		// should be made as normal.
		if (hd.isEmpty ()) {
			
			this.graph_model.getGraphPair ().reset ();
			JOptionPane.showMessageDialog (this, "There is no data file currently selected.\n"
					+ "Please perform the \"Import Data\" function from the Data Menu before proceeding.");
		
		} 
		
		// Verify if data exists in the HeaderData object.
		else if (!hd.hasValidGraphTypes (
				this.graph_model.getTemplate ().getChartType (), p)) {
				
				JOptionPane.showMessageDialog (
						this, "Error: This graph does not have the correct column types for the selected chart type.\n"
						+ "Please try again with the proper column types for a " + this.graph_model.getTemplate ().getChartType ().toString () + ".");
				
				this.checkAndDisplayGraph (this.graph_model.graphEmptyChart ().getChart ());
				
			}
			
		// Verify if there is a GraphPair ready to be graphed.
		else if (!p.isReady ()) {
			
			JOptionPane.showMessageDialog (
					this, "Error: This graph requires both an X Axis and Y Axis Column\n"
							+ "selected before it can be graphed.\n"
							+ "Please select both an X and Y Column before continuing.");
			
			this.checkAndDisplayGraph (this.graph_model.graphEmptyChart ().getChart ());
			
		} 
		
		// Verify if the X and Y Columns are the same.
		else if (hd.get (p.getXColumnIndex ()).getKey ().equals (
				hd.get (p.getYColumnIndex ()).getKey ())) {
			
			JOptionPane.showMessageDialog (
					this, "Error: Graphing requires different columns to be selected.\n"
					+ "Please select two different columns before attempting to graph again.");
			
			this.checkAndDisplayGraph (this.graph_model.graphEmptyChart ().getChart ());
			
		} 
		// Verify if the X and Y Columns are the same as the Group column.
		else if (hd.get (p.getXColumnIndex ()).getKey ().equals (
						p.getGroupName ()) ||
				hd.get (p.getYColumnIndex ()).getKey ().equals (
						p.getGroupName ())) {
			
			JOptionPane.showMessageDialog (
					this, "Error: Graphing in groups requires the X and Y Columns\n"
							+ "to be different from the Group Column.\n"
							+ "Please select a different Column to Group by.");
			
			this.checkAndDisplayGraph (this.graph_model.graphEmptyChart ().getChart ());
			
		} else {
		
			// if you have a scanning graph, graph a basic one (without interpolations),
			// first!
			if (this.graph_model.getTemplate ().isSearching ()) {
				
				this.checkAndDisplayGraph (
						this.graph_model.unscannedGraphing (false).getChart ());
			}
			
			// Create the graph.
			this.checkAndDisplayGraph (this.graph_model.graph ());
			
		}
		
	}
	
	private void checkAndDisplayGraph (JFreeChart c) {
		if (c != null) {
			
			this.chart = c;
			
			// Display the chart!
			setContentPane (new org.jfree.chart.ChartPanel (chart, false, true, false, false, true));
			this.pack ();
			this.setVisible (true);
			
			// Display any extra information!
			if (this.graph_model.getInterpolation () != null) {
				
				this.graph_model.getInterpolation ().showInterpolationValidity ();
				
			}
			
		} /*else {
			JOptionPane.showMessageDialog (this, "Due to the previously-mentioned error, "
					+ "the graph will not change.");
		}*/
	}

	private void initComponents () {
		// Set default operation responses.
		setDefaultCloseOperation (javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		// Create the chart and make it visible!
		chart = new XYGraph (this.graph_model.getTemplate ()).getChart ();
		//this.graphUpdate ();
		
		// Change background color.
		chart.setBackgroundPaint (Color.WHITE);
		
		if (ChartType.XY_GRAPH.equals (
				this.graph_model.getTemplate ().getChartType ())) {
			
			chart.getXYPlot ().setRangeGridlinePaint (Color.BLACK);
			chart.getXYPlot ().setDomainGridlinePaint (Color.BLACK);
			
		} else {
			
			chart.getCategoryPlot ().setRangeGridlinePaint (Color.BLACK);
			chart.getCategoryPlot ().setDomainGridlinePaint (Color.BLACK);
			
		}
		
		setContentPane (new org.jfree.chart.ChartPanel (chart, false, true, false, false, true));
		this.setLocation (300, 120);
		this.pack ();
		this.setVisible (true);

	}
}