package org.plasmagraph.graphs;

// Swing / AWT
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;

// JFreeChart
import org.jfree.chart.*;
// http://www.jfree.org/jfreechart/api/javadoc/org/jfree/data/xy/DefaultXYDataset.html
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataset;

// PlasmaGraph
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.plasmagraph.template.Template;

public class LineChart extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5591725210053739323L;
	JFreeChart chart;
    CategoryDataset imported_data;

	// Constructors
	public LineChart (Template t, CategoryDataset d) {
		super(t.chart_name);
                this.imported_data = d;
		setContentPane (createJPanel (t));
	}
	
	private JPanel createJPanel (Template t) {
		chart = createChart (createDataset(t), t);
                ChartPanel c = new ChartPanel (chart, true, true, true, true, true);
                c.setPreferredSize(new Dimension (t.x_minimum, t.y_minimum));
		return (c);
	}
	
	private XYSeriesCollection createDataset (Template t) {
		XYSeriesCollection set = new XYSeriesCollection ();
		generateTestDataset (set, t);
		
		return (set);
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

	private JFreeChart createChart (XYSeriesCollection set, Template t) {
		chart = ChartFactory.createXYLineChart (t.chart_name, t.y_axis_name, t.x_axis_name, 
				set, t.orientation, t.using_legend, t.using_tooltips, t.generate_urls);
		
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
