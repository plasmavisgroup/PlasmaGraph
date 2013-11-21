package org.plasmagraph.graphs;

// Swing / AWT
import java.awt.Dimension;
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
	 * 
	 */
	private static final long serialVersionUID = 8086468548358234411L;

	JFreeChart chart;
	CategoryDataset imported_data;

	// Constructors
	public BarChart (Template t, CategoryDataset d) {
		super(t.chart_name);
                imported_data = d;
		setContentPane (createJPanel (t));
	}
	
	private JPanel createJPanel (Template t) {
		chart = createChart (createDataset(t), t);
		ChartPanel c = new ChartPanel (chart, true, true, true, true, true);
                c.setPreferredSize(new Dimension (t.x_minimum, t.y_minimum));
		return (c);
	}
	
	private DefaultCategoryDataset createDataset (Template t) {
		DefaultCategoryDataset set = new DefaultCategoryDataset ();
		generateTestDataset (set);
		
		return (set);
	}
	
	private void generateTestDataset(DefaultCategoryDataset set) {
		for (int i = 1; i <= 6; ++i) {
            set.addValue(new Integer(i), "Size " + (i), new Integer (i));
		}
	}

	private JFreeChart createChart (DefaultCategoryDataset set, Template t) {
		JFreeChart c = ChartFactory.createBarChart (t.chart_name, t.y_axis_name, t.x_axis_name, 
			set, t.orientation, t.using_legend, t.using_tooltips, t.generate_urls);
		
		//org.jfree.chart.plot.BarChart plot = chart.getBarChart();
		
		return (c);
	}
}
