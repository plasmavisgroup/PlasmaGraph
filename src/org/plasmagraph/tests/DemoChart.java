package org.plasmagraph.tests;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.*;

public class DemoChart extends JFrame{

	// Constructors
	public DemoChart (String title) {
		super(title);
		setContentPane (createJPanel ());
	}
	
	public static JPanel createJPanel () {
		JFreeChart chart = createChart (createDataset());
		return (new ChartPanel (chart, true, true, true, true, true));
	}
	
	private static PieDataset createDataset () {
		DefaultPieDataset set = new DefaultPieDataset ();
		set.setValue ("http", new Double (53.2));
		set.setValue ("ftp", new Double (10.0));
		set.setValue ("ssh", new Double (27.5));
		set.setValue ("https", new Double (7.5));
		set.setValue ("telnet", new Double (11.0));
		set.setValue ("rlogin", new Double (19.4));
		
		return (set);
	}
	
	private static JFreeChart createChart (PieDataset set) {
		JFreeChart chart = ChartFactory.createPieChart ("Application Distribution", set, true, true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		//plot.setExplodePercent (0, 0.3);
		
		plot.setSectionOutlinesVisible(true);
		plot.setLabelFont(new Font ("SansSerif", Font.PLAIN, 12));
		plot.setCircular(false);
		
		return (chart);
	}
}
