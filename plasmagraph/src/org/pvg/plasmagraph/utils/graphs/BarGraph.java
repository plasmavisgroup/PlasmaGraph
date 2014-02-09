package org.pvg.plasmagraph.utils.graphs;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.template.Template;

@SuppressWarnings ("serial")
public class BarGraph extends JFrame implements Graph {
	JFreeChart chart;

	// Constructors
	/* Test Constructor */
	public BarGraph (Template t, DataSet ds) {
		super(t.getChartName ());
		setContentPane (createJPanel (t, ds));
	}
	
	public JPanel createJPanel (Template t, DataSet ds) {
		chart = createChart (createDataset(t, ds), t);
		ChartPanel c = new ChartPanel (chart, false, true, false, true, true);
		return (c);
	}
	
	public DefaultCategoryDataset createDataset (Template t, DataSet ds) {
		//DefaultCategoryDataset set = new DefaultCategoryDataset ();
		//generateTestDataset (set, t);
		
		//return (set);
		return (ds.toBarGraphDataset ());
	}
	
	private void generateTestDataset (DefaultCategoryDataset set, Template t) {
		for (int i = 1; i <= 6; ++i) {
            set.addValue(new Integer(i), "Size " + (i), new Integer (i));
		}
	}

	public JFreeChart createChart (Dataset set, Template t) {
		JFreeChart c = ChartFactory.createBarChart (t.getChartName (),
				t.getYAxisLabel (), t.getXAxisLabel (), (DefaultCategoryDataset) set,
				t.getOrientation (), t.generatesLegend (),
				t.generatesTooltips (), t.generatesURLs ());
		
		//org.jfree.chart.plot.BarChart plot = chart.getBarChart();
		
		return (c);
	}
}