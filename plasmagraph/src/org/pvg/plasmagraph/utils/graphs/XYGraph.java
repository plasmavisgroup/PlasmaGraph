package org.pvg.plasmagraph.utils.graphs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartTheme;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.util.ParamChecks;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.InvalidParametersException;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.types.AxisType;
import org.pvg.plasmagraph.utils.types.InterpolationType;

/**
 * <p>Graph class. Contains the methods required to take in numerical coordinate data that
 * the rest of the system may provide it and shape it into a proper XY Scatterplot Graph
 * according to the settings in the Template.
 * 
 * @author Plasma Visualization Group
 */
public class XYGraph implements Graph {
	
	/** The chart theme. */
	private static ChartTheme currentTheme = new StandardChartTheme ("JFree");
	
	JFreeChart chart;

	/**
	 * <p>Constructor. Makes a graph without the necessary data.
	 * 
	 * @param t Template reference used in the formation of various parts of
	 *            the graph.
	 */
	public XYGraph (Template t) {
		chart = createChart (createDataset (t), t);
	}

	/**
	 * <p>Automates the changes to create a default XY Graph.
	 * 
	 * @return A JFreeChart with a default set of data points.
	 */
	private JFreeChart createChart (XYDataset series, Template t) {
		// Make the chart.
		ParamChecks.nullNotPermitted (t.getOrientation (), "orientation");
		NumberAxis xAxis = new NumberAxis (t.getXAxisLabel ());
		xAxis.setAutoRangeIncludesZero (false);
		
		NumberAxis yAxis = new NumberAxis (t.getYAxisLabel ());
		yAxis.setAutoRangeIncludesZero (false);

		XYPlot plot = new XYPlot (series, xAxis, yAxis, null);

		XYToolTipGenerator toolTipGenerator = null;
		if (t.generatesTooltips ()) {
			toolTipGenerator = new StandardXYToolTipGenerator ();
		}

		XYURLGenerator urlGenerator = null;
		if (t.generatesURLs ()) {
			urlGenerator = new StandardXYURLGenerator ();
		}
		
		XYItemRenderer renderer = new XYLineAndShapeRenderer (false, true);
		renderer.setBaseToolTipGenerator (toolTipGenerator);
		renderer.setURLGenerator (urlGenerator);
		plot.setRenderer (renderer);
		plot.setOrientation (t.getOrientation ());

		JFreeChart c = new JFreeChart (t.getChartName (), 
				JFreeChart.DEFAULT_TITLE_FONT, plot, t.generatesLegend ());
		currentTheme.apply (c);
		
		// Edit the axes.
		this.modifyPlot (t, c);

		return (c);
	}

	/**
	 * <p>Helper method. Automates the changes to create a default XY Graph
	 * Dataset.
	 * 
	 * @return An XYDataset with a default set of data points.
	 */
	private XYDataset createDataset (Template t) {
		// Populate a single-valued XYSeries centered at the origin.
		XYSeries s1 = new XYSeries ("Default");
		s1.add (0.0, 0.0);

		// Wrap the XYSeries in a XYSeriesCollection, which inherits from
		// Dataset.
		return (new XYSeriesCollection (s1));
	}

	//====================================================//
	
	/**
	 * <p>Basic constructor. Creates an XYDataset before creating the JFreeChart
	 * itself.
	 * 
	 * @param t
	 *            Template reference used in the formation of various parts of
	 *            the graph.
	 * @param ds
	 *            DataSet reference used in the creation of the graph.
	 * @param p
	 *            GraphPair that contains the columns to graph.
	 * @throws InvalidParametersException Whenever an invalid column is found in this process.
	 */
	public XYGraph (Template t, DataSet ds, GraphPair p) throws InvalidParametersException {
		if (ds.getNumberOfUniqueGroups () > 40) {
			
			JOptionPane.showMessageDialog (null, "The group column for this graph contains 40 or more unique groups.\n"
					+ "As a result, the graph will probably be badly-formed. Verify the columns being graphed\n"
					+ "and try again with a better group column selection.");
			
		} else if (ds.getNumberOfUniqueGroups () == 1) {
			
			JOptionPane.showMessageDialog (null, "The group column for this graph contains only one unique group.\n"
					+ "As a result, the graph is unchanged compared to its ungrouped state."
					+ "If this was not your intention, try again with a better group column selection.");
			
		}
		
		chart = createChart (createDataset (t, ds, p), t, p);
	}

	/**
	 * <p>Constructor using HeaderData. Calls HeaderData's "populateData" method
	 * to create a DataSet from it.
	 * 
	 * @param t
	 *            Template reference used in the formation of various parts of
	 *            the graph.
	 * @param hd
	 *            HeaderData reference used in the creation of the graph.
	 * @param p
	 *            GraphPair that contains the columns to graph.
	 * @throws InvalidParametersException Whenever an invalid column is found in this process.
	 */
	public XYGraph (Template t, HeaderData hd, GraphPair p) throws InvalidParametersException {
		this (t, hd.populateData (p), p);
	}

	/**
	 * <p>Interpolation constructor. Creates a XYGraph from a Template and
	 * XYSeriesCollection reference, which contains both the original
	 * uninterpolated XYSeries and an interpolated XYSeries.
	 * 
	 * @param t
	 *            Template reference used in the formation of various parts of
	 *            the graph.
	 * @param graph_data
	 *            XYSeriesCollection with two sets of data used in the creation
	 *            of the graph.
	 * @param p
	 *            GraphPair reference used in
	 */
	public XYGraph (Template t, XYSeriesCollection graph_data, GraphPair p) {
		chart = createChart (graph_data, t, p);
	}

	/**
	 * <p>Creates an XYDataset specifically for the purposes of graphing the data
	 * using the DataSet's provided values.
	 * 
	 * @param t
	 *            Template reference used in the formation of various parts of
	 *            the graph.
	 * @param ds
	 *            DataSet reference used in the creation of the graph.
	 * @return An XYDataset containing the DataSet's data values
	 * @throws InvalidParametersException Whenever a non-numeric column is found in this process.
	 */
	@Override
	public XYDataset createDataset (Template t, DataSet ds, GraphPair p) throws InvalidParametersException {
		// Create the Dataset
		XYSeriesCollection set = new XYSeriesCollection ();
		
		//System.out.println (p.isGrouped ());
		
		if (p.isGrouped ()) {
			
			//System.out.println ("It's grouped!");
			set = ds.toGroupedXYGraphDataset (p);
			
		} else {
			
			set.addSeries (ds.toXYGraphDataset (p));
			
		}

		// Return the Dataset
		return (set);
	}

	/**
	 * <p>Creates a JFreeChart, an object containing the visual representation of
	 * the requested graph, from a group of settings and a JFreeChart Dataset.
	 * Code for this method is taken from the "ChartFactory" class in the
	 * JFreeChart library, but modified so as to fit the changing details.
	 * 
	 * @param set
	 *            Dataset reference used in the creation of the graph.
	 * @param t
	 *            Template reference used in the formation of various parts of
	 *            the graph.
	 * @return A JFreeChart containing the visual representation of the graph.
	 */
	@Override
	public JFreeChart createChart (Dataset set, Template t, GraphPair p) {

		ParamChecks.nullNotPermitted (t.getOrientation (), "orientation");
		NumberAxis xAxis = new NumberAxis (t.getXAxisLabel ());
		xAxis.setAutoRangeIncludesZero (false);
		
		NumberAxis yAxis = new NumberAxis (t.getYAxisLabel ());
		yAxis.setAutoRangeIncludesZero (false);

		XYPlot plot = new XYPlot ((XYDataset) set, xAxis, yAxis, null);

		XYToolTipGenerator toolTipGenerator = null;
		if (t.generatesTooltips ()) {
			toolTipGenerator = new StandardXYToolTipGenerator ();
		}

		XYURLGenerator urlGenerator = null;
		if (t.generatesURLs ()) {
			urlGenerator = new StandardXYURLGenerator ();
		}
		
		XYItemRenderer renderer = new XYLineAndShapeRenderer (false, true);
		renderer.setBaseToolTipGenerator (toolTipGenerator);
		renderer.setURLGenerator (urlGenerator);
		plot.setRenderer (renderer);
		plot.setOrientation (t.getOrientation ());

		JFreeChart c = new JFreeChart (t.getChartName (), 
				JFreeChart.DEFAULT_TITLE_FONT, plot, t.generatesLegend ());
		currentTheme.apply (c);

		// Set axis names and scales.
		this.modifyPlot (t, p, c);

		return (c);
	}

	/**
	 * <p>Helper method. Handles the changes made to XYGraphs made without 
	 * data. (AKA, empty plots made due to program errors or user mistakes.
	 * 
	 * @param t The Template object used to contain most of the graph settings.
	 * @param c The JFreeChart object that will be modified.
	 */
	private void modifyPlot (Template t, JFreeChart c) {
		// X Axis
		if (AxisType.STANDARD.equals (t.getXAxisType ())) {

			ValueAxis domain = new NumberAxis (t.getXAxisLabel ());
			domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
			domain.setRange (-10.0, 10.0);
			c.getXYPlot ().setDomainAxis (domain);

		} else {// if (AxisType.LOG.equals (t.getXAxisType ())) {
			
			ValueAxis domain = new LogAxis (t.getXAxisLabel ());
			domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
			c.getXYPlot ().setDomainAxis (domain);

		}

		// Y Axis
		if (AxisType.STANDARD.equals (t.getYAxisType ())) {

			ValueAxis range = new NumberAxis (t.getYAxisLabel ());
			range.setRange (-10.0, 10.0);
			range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
			c.getXYPlot ().setRangeAxis (range);

		} else {// if (AxisType.LOG.equals (t.getYAxisType ())) {

			ValueAxis range = new LogAxis (t.getYAxisLabel ());
			range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
			c.getXYPlot ().setRangeAxis (range);

		}
		
		// Create XYPlot for additional modifications.
		XYPlot plot = (XYPlot) c.getXYPlot ();
		
		// Change background color.
		plot.setBackgroundPaint (Color.WHITE);
		plot.setRangeGridlinePaint (Color.BLACK);
		plot.setDomainGridlinePaint (Color.BLACK);

	}

	/**
	 * <p>Helper method. Handles the changes made to XYGraphs made with data.
	 * (AKA, those graphs made that contain no procedure-stopping errors or user
	 * mistakes.)
	 * 
	 * @param t The Template object used to contain most of the graph settings.
	 * @param p The GraphPair object used to contain the column and group data for the graph.
	 * @param c The JFreeChart object that will be modified.
	 */
	private void modifyPlot (Template t, GraphPair p, JFreeChart c) {
		// Create XYPlot
		XYPlot plot = (XYPlot) c.getXYPlot ();

		// X Axis
		if (AxisType.STANDARD.equals (t.getXAxisType ())) {

			if (t.isDefaultXAxisLabel ()) {

				ValueAxis domain = new NumberAxis (p.getXIndexName ());
				domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setDomainAxis (domain);

			} else {

				ValueAxis domain = new NumberAxis (t.getXAxisLabel ());
				domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setDomainAxis (domain);

			}

		} else {// if (AxisType.LOG.equals (t.getXAxisType ())) {

			if (t.isDefaultXAxisLabel ()) {

				ValueAxis domain = new LogAxis (p.getXIndexName ());// NumberAxis
																	// ();
				domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setDomainAxis (domain);

			} else {

				ValueAxis domain = new LogAxis (t.getXAxisLabel ());
				domain.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setDomainAxis (domain);

			}

		}

		// Y Axis
		if (AxisType.STANDARD.equals (t.getYAxisType ())) {

			if (t.isDefaultYAxisLabel ()) {

				ValueAxis range = new NumberAxis (p.getYIndexName ());
				range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setRangeAxis (range);

			} else {

				ValueAxis range = new NumberAxis (t.getYAxisLabel ());
				range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setRangeAxis (range);

			}

		} else {// if (AxisType.LOG.equals (t.getYAxisType ())) {

			if (t.isDefaultYAxisLabel ()) {

				ValueAxis range = new LogAxis (p.getYIndexName ());
				range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setRangeAxis (range);

			} else {

				ValueAxis range = new LogAxis (t.getYAxisLabel ());
				range.setLabelFont (new Font ("Arial", Font.BOLD, 16));
				plot.setRangeAxis (range);

			}

		}

		// Set Chart name.
		if (t.isDefaultChartName ()) {
			c.setTitle (p.getXIndexName () + " vs. " + p.getYIndexName ());
		}

		// Change background color.
		plot.setBackgroundPaint (Color.WHITE);
		plot.setRangeGridlinePaint (Color.BLACK);
		plot.setDomainGridlinePaint (Color.BLACK);
		
		c.getXYPlot ().setDrawingSupplier (new DefaultDrawingSupplier (
				this.getXYPaintColors (),
				DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));

		// Change thickness of interpolation line, if it exists
		if (!InterpolationType.NONE.equals (t.getInterpolationType ())) {
			XYItemRenderer renderer = c.getXYPlot ().getRenderer ();
			
			int number_of_series = ((XYSeriesCollection) (c.getXYPlot ().getDataset ())).getSeriesCount ();
			
			for (int i = (number_of_series/ 2); (i < number_of_series); ++i) {
				renderer.setSeriesShape (i, new Ellipse2D.Double (-3.0, -3.0, 1.0, 1.0));
			}
		}
	}

	/**
	 * <p>Getter method. Provides the internal JFreeChart object that contains the
	 * graph.
	 * 
	 * @return A JFreeChart created by the object.
	 */
	@Override
	public JFreeChart getChart () {
		return (this.chart);
	}

	/**
	 * <p>Opens up a JFrame containing the graph! Primarily used for testing purposes.
	 */
	@Override
	public void testGraph () {
		JFrame frame = new JFrame ();
		frame.add (new ChartPanel (this.chart));

		frame.pack ();
		frame.setVisible (true);
	}
	
	/**
	 * <p>Helper method. Creates the list of valid colors to use in XYGraphs.
	 * Used specifically for JFreeChart's chart points.
	 * 
	 * @return An array of Paint objects representing various usable colors.
	 */
	private Paint [] getXYPaintColors () {
		Paint [] color_set = new Paint [8];
		
		color_set[0] = ChartColor.DARK_RED;
		color_set[1] = ChartColor.DARK_BLUE;
		color_set[2] = ChartColor.DARK_GREEN;
		color_set[3] = ChartColor.DARK_CYAN;
		color_set[4] = Color.BLACK;
		color_set[5] = Color.DARK_GRAY;
		color_set[6] = Color.ORANGE;
		color_set[7] = ChartColor.DARK_MAGENTA;
		
		return (color_set);
	}
}
