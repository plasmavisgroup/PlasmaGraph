package org.pvg.plasmagraph.views;

import javax.swing.JFrame;

import org.jfree.chart.JFreeChart;
import org.pvg.plasmagraph.models.GraphModel;
import org.pvg.plasmagraph.utils.graphs.XYGraph;

/**
 * TODO
 * 
 * @author Gerardo A. Navas Morales
 */
@SuppressWarnings ("serial")
public class GraphView extends JFrame {
	
	/** Reference to model related to this controller. */
	GraphModel graph_model;
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
	 *  Triggers on changes to the Template. 
	 *  Creates a new JFreeChart via the graph method.
	 */
	public void templateUpdate () {
		
		JFreeChart c = this.graph_model.graph ();
		
		if (c != null) {
			
			this.chart = c;
			
		}
		
		// Display the chart!
		setContentPane (new org.jfree.chart.ChartPanel (chart, false, true, false, false, true));
		this.pack ();
		this.setVisible (true);
	}

	/**
	 * Triggers on changes to the HeaderData. 
	 * Resets the chart, but not its template details, to its default form.
	 */
	public void headerUpdate () {
		
		// If the HeaderData was reset, reset the DataReference.
		// Otherwise, it was just the user adding another file, and the graph
		// should be made as normal.
		if (this.graph_model.getHeaderData ().isEmpty ()) {
			
			this.graph_model.getDataReference ().reset ();
		
		} else {
			//log ("Right way");
			
			// Create the graph.
			JFreeChart c = this.graph_model.graph ();
			
			if (c != null) {
				
				this.chart = c;
				
				// Display the chart!
				setContentPane (new org.jfree.chart.ChartPanel (chart, false, true, false, false, true));
				this.pack ();
				this.setVisible (true);
				
			}
			// Otherwise, don't change anything at all.
			
		}
	}

	/**
	 * Triggers on changes to the DataReference. Automatically graphs the 
	 * data pair selected. (As for now, the selected data pair is always in
	 * "dr.get (0)".
	 */
	public void referenceUpdate () {
		
		// If the DataReference was reset, do not graph.
		// Otherwise, it is safe to graph.
		if (!this.graph_model.getDataReference ().isEmpty ()) {
			
			// Create the graph.
			JFreeChart c = this.graph_model.graph ();
			
			if (c != null) {
				
				this.chart = c;
				
			}
			
			// Display the chart!
			setContentPane (new org.jfree.chart.ChartPanel (chart, false, true, false, false, true));
			this.pack ();
			this.setVisible (true);
			
		}

	}

	private void initComponents () {
		// Set default operation responses.
		setDefaultCloseOperation (javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		// Create the chart and make it visible!
		chart = new XYGraph (this.graph_model.getTemplate ()).getChart ();
		
		setContentPane (new org.jfree.chart.ChartPanel (chart, false, true, false, false, true));
		this.setLocation (260, 120);
		this.pack ();
		this.setVisible (true);

	}
	
	private void log (String string) {
		System.out.println (string);
	}
}