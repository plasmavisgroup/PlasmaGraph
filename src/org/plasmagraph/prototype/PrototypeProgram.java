package org.plasmagraph.prototype;

import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.editor.DefaultLogAxisEditor;
import org.plasmagraph.graphs.LogAxisTest;
import org.plasmagraph.graphs.XYPlot;
import org.plasmagraph.template.Template;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrototypeProgram{
	
	public static void main (String [] args) {
		XYPlot demo = new XYPlot (new Template ());
		//LogAxisTest demo = new LogAxisTest ();
		demo.pack();
		demo.setVisible (true);	
	}
}
