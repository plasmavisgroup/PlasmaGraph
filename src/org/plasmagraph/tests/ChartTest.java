package org.plasmagraph.tests;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.ApplicationFrame;
import org.junit.Test;
import org.plasmagraph.sanitizer.ChartConverter;
import org.plasmagraph.template.Template;
import org.plasmagraph.graphs.*;

import java.util.Arrays;
import java.util.ArrayList;

import javax.swing.JFrame;

public class ChartTest {
	// Create the three basic arrays.
	ArrayList <String> rows;
	ArrayList <String> cols;
	ArrayList <ArrayList <Double>> vals;
	
	public ChartTest () {
		rows = new ArrayList <String> ();
		cols = new ArrayList <String> ();
		vals = new ArrayList <ArrayList <Double>> ();
		
		populateRows (rows);
		populateCols (cols);
		populateVals (vals);
	}
	
	@Test
	public void testPieChart () {
		Template t = new Template ();
		
		editTemplate (t, "Pie Chart", rows, cols, PlotOrientation.HORIZONTAL);
		
		ChartConverter c = new ChartConverter (rows, cols, vals);
		PieChart chart = new PieChart (t, c);
		
		chart.pack();
		chart.setVisible(true);
	}
	
	public void testBarChart () {
		Template t = new Template ();
		
		editTemplate (t, "Bar Chart", rows, cols, PlotOrientation.VERTICAL);
		
		ChartConverter c = new ChartConverter (rows, cols, vals);
		BarChart chart = new BarChart (t, c);
		
		chart.pack();
		chart.setVisible(true);
	}
	
	public void testLineChart () {
		Template t = new Template ();
		
		editTemplate (t, "Line Chart", rows, cols, PlotOrientation.VERTICAL);
		
		ChartConverter c = new ChartConverter (rows, cols, vals);
		LineChart chart = new LineChart (t, c);
		
		chart.pack();
		chart.setVisible(true);
	}
	
	public void testXYPlot () {
		Template t = new Template ();
		
		editTemplate (t, "XY Plot", rows, cols, PlotOrientation.VERTICAL);
		
		ChartConverter c = new ChartConverter (rows, cols, vals);
		XYPlot chart = new XYPlot (t, c);
		
		chart.pack();
		chart.setVisible(true);
	}

	private void populateRows(ArrayList<String> rows) {
		// TODO Auto-generated method stub
		rows.add ("Nissan Spectra");
		rows.add ("Hyundai Brio");
		rows.add ("Toyota Camry");
		rows.add ("Ford Focus");
	}

	private void populateCols(ArrayList<String> cols) {
		// TODO Auto-generated method stub
		cols.add ("Time");
		cols.add ("Speed, Average");
	}
	
	private void populateVals(ArrayList<ArrayList<Double>> vals) {
		// TODO Auto-generated method stub
		ArrayList <Double> row1 = new ArrayList <Double> ();
		row1.add ((double) 10);
		row1.add ((double) 30);
		ArrayList <Double> row2 = new ArrayList <Double> ();
		row2.add ((double) 10);
		row2.add ((double) 40);
		ArrayList <Double> row3 = new ArrayList <Double> ();
		row3.add ((double) 10);
		row3.add ((double) 40);
		ArrayList <Double> row4 = new ArrayList <Double> ();
		row4.add ((double) 10);
		row4.add ((double) 60);
		
		vals.add (row1);
		vals.add (row2);
		vals.add (row3);
		vals.add (row4);
	}

	private void editTemplate(Template t, String type, ArrayList<String> rows, ArrayList<String> cols, PlotOrientation o) {
		// TODO Auto-generated method stub
		t.chart_name = "Various Cars' Average Speed on Track A";
		t.chart_type = type;
		t.using_legend = true;
		t.using_tooltips = true;
		t.generate_urls = false;
		
		t.orientation = o;
		t.x_axis_name = (String) cols.get(0);
		t.y_axis_name = (String) cols.get(1);
		
		t.x_minimum = 640;
		t.y_minimum = 480;
	}
	
}