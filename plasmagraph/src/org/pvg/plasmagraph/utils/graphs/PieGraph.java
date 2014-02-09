package org.pvg.plasmagraph.utils.graphs;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.template.Template;

@SuppressWarnings ("serial")
public class PieGraph extends JFrame implements Graph{
	private JFreeChart chart;
	
    // Constructors
	/* Test Constructor */
	public PieGraph (Template t, DataSet ds) {
		super(t.getChartName ());
		setContentPane (createJPanel (t, ds));
	}
	
	public JPanel createJPanel (Template t, DataSet ds) {
		chart = createChart (createDataset(t, ds), t);
		ChartPanel c = new ChartPanel (chart, false, true, false, true, true);
		return (c);
	}
	
	public PieDataset createDataset (Template t, DataSet ds) {
		//return (generateTestDataset());
		return (ds.toPieGraphDataset ());
	}
	
	private PieDataset generateTestDataset() {
		DefaultPieDataset set = new DefaultPieDataset ();
		set.setValue("a", (Double) 5.0);
		set.setValue("b", (Double) 25.0);
		set.setValue("c", (Double) 30.0);
		set.setValue("d", (Double) 20.0);
		set.setValue("e", (Double) 20.0);
		return set;
	}

	public JFreeChart createChart (Dataset set, Template t) {
		JFreeChart c = ChartFactory.createPieChart
                        (t.getChartName (), (PieDataset) set, t.generatesLegend (),
                        t.generatesTooltips (), t.generatesURLs ());
		PiePlot plot = (PiePlot) c.getPlot();
		
		plot.setSectionOutlinesVisible(true);
		plot.setLabelFont(new java.awt.Font ("SansSerif", java.awt.Font.PLAIN, 12));
		plot.setCircular(false);
		
		return (c);
	}
}
