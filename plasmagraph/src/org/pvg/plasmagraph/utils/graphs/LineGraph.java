package org.pvg.plasmagraph.utils.graphs;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.template.Template;

@SuppressWarnings ("serial")
public class LineGraph extends JFrame implements Graph{
	JFreeChart chart;

	// Constructors
	/* Test Constructor */
	public LineGraph (Template t, DataSet ds) {
		super(t.getChartName ());
		setContentPane (createJPanel (t, ds));
	}
	
	public JPanel createJPanel (Template t, DataSet ds) {
		chart = createChart (createDataset(t, ds), t);
        ChartPanel c = new ChartPanel (chart, false, true, false, true, true);
        
		return (c);
	}
	
	public XYSeriesCollection createDataset (Template t, DataSet ds) {
		//XYSeriesCollection set = new XYSeriesCollection ();
		//generateTestDataset (set, t);
		
		//return (set);
		return (ds.toLineGraphDataset ());
	}
	
	private void generateTestDataset(XYSeriesCollection set, Template t) {
        final XYSeries series1 = new XYSeries("First");
        series1.add(1.0, 1.0);
        series1.add(2.0, 4.0);
        series1.add(3.0, 3.0);
        series1.add(4.0, 5.0);
        series1.add(5.0, 5.0);
        series1.add(6.0, 7.0);
        series1.add(7.0, 7.0);
        series1.add(8.0, 8.0);

        final XYSeries series2 = new XYSeries("Second");
        series2.add(1.0, 5.0);
        series2.add(2.0, 7.0);
        series2.add(3.0, 6.0);
        series2.add(4.0, 8.0);
        series2.add(5.0, 4.0);
        series2.add(6.0, 4.0);
        series2.add(7.0, 2.0);
        series2.add(8.0, 1.0);

        final XYSeries series3 = new XYSeries("Third");
        series3.add(3.0, 4.0);
        series3.add(4.0, 3.0);
        series3.add(5.0, 2.0);
        series3.add(6.0, 3.0);
        series3.add(7.0, 6.0);
        series3.add(8.0, 3.0);
        series3.add(9.0, 4.0);
        series3.add(10.0, 3.0);

        set.addSeries(series1);
        set.addSeries(series2);
        set.addSeries(series3);
	}

	public JFreeChart createChart (Dataset set, Template t) {
		chart = ChartFactory.createXYLineChart (t.getChartName (),
				t.getYAxisLabel (), t.getXAxisLabel (), (XYSeriesCollection) set,
				t.getOrientation (), t.generatesLegend (),
				t.generatesTooltips (), t.generatesURLs ());
		
                // Customization based on template.
                chart.setBackgroundPaint(Color.white);

                //final StandardLegend legend = (StandardLegend) chart.getLegend();
                //legend.setDisplaySeriesShapes(true);

                // get a reference to the plot for further customisation...
                final org.jfree.chart.plot.XYPlot plot = chart.getXYPlot();
                plot.setBackgroundPaint(Color.lightGray);
                //plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
                plot.setDomainGridlinePaint(Color.white);
                plot.setRangeGridlinePaint(Color.white);

                //final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
                //renderer.setSeriesLinesVisible(0, false);
                //renderer.setSeriesShapesVisible(1, false);
                //plot.setRenderer(renderer);

                // change the auto tick unit selection to integer units only...
                final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
                rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
                // OPTIONAL CUSTOMISATION COMPLETED.

		return (chart);
	}
}
