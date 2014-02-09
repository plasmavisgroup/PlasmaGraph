package org.pvg.plasmagraph.utils.graphs;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.template.Template;

// TODO: Documentation!
/**
 * 
 * @author Gerardo A. Navas Morales
 */
@SuppressWarnings ("serial")
public class XYGraph extends JFrame implements Graph{
	JFreeChart chart;
	DataSet ds;

	// Constructors
	public XYGraph (Template t_reference, DataSet ds_reference) {
		super(t_reference.getChartName ());
		setContentPane (createJPanel (t_reference, ds_reference));
	}
	
	public JPanel createJPanel (Template t, DataSet ds) {
		chart = createChart (createDataset(t, ds), t);
		ChartPanel c = new ChartPanel (chart, false, true, false, true, true);
		return (c);
	}
	
	public XYDataset createDataset (Template t, DataSet ds) {
		DefaultXYDataset set = new DefaultXYDataset ();
		XYSeries s = ds.toXYGraphDataset ();
		set.addSeries (s.getKey (), s.toArray ());

		return (set);
	}

	public JFreeChart createChart (Dataset set, Template t) {
		JFreeChart c = ChartFactory.createScatterPlot(t.getChartName (), 
				t.getYAxisLabel (), t.getXAxisLabel (), 
				(XYDataset) set, t.getOrientation (), t.generatesLegend (),
				t.generatesTooltips (), t.generatesURLs ());
		
		//org.jfree.chart.plot.XYPlot plot = c.getXYPlot();
		
		return (c);
	}
}
