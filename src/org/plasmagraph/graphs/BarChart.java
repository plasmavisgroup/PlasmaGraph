package org.plasmagraph.graphs;

// Swing / AWT
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;

// JFreeChart
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
// http://www.jfree.org/jfreechart/api/javadoc/org/jfree/data/xy/DefaultXYDataset.html
import org.jfree.data.xy.XYDataset;

// PlasmaGraph
import org.plasmagraph.template.Template;

public class BarChart extends JFrame{

	/**
	 * "Unique" ID generated by Eclipse.
	 * How unique it may be is questionable, but it should work well enough.
	 */
        // Let Eclipse do it.
	static JFreeChart chart;

	// Constructors
	public BarChart (Template t) {
		super(t.chart_name);
		setContentPane (createJPanel (t));
	}
	
	public static JPanel createJPanel (Template t) {
		chart = createChart (createDataset(t), t);
		return (new ChartPanel (chart, true, true, true, true, true));
	}
	
	private static DefaultCategoryDataset createDataset (Template t) {
		DefaultCategoryDataset set = new DefaultCategoryDataset ();
		generateTestDataset (set, t);
		
		return (set);
	}
	
	private static void generateTestDataset(DefaultCategoryDataset set, Template t) {
		for (int i = 1; i <= 6; ++i) {
			
                        set.addValue(new Integer(i), "Size " + (i), new Integer (i));
		}
	}

	private static JFreeChart createChart (DefaultCategoryDataset set, Template t) {
		JFreeChart chart = ChartFactory.createBarChart (t.chart_name, t.y_axis_name, t.x_axis_name, 
			set, t.orientation, t.using_legend, t.using_tooltips, t.generate_urls);
		
		//org.jfree.chart.plot.BarChart plot = chart.getBarChart();
		
		return (chart);
	}
}
