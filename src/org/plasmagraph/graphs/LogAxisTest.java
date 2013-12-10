package org.plasmagraph.graphs;

import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.editor.DefaultLogAxisEditor;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class LogAxisTest extends JFrame {

	public LogAxisTest () {
		
		LogAxis l = new LogAxis ();
		
		l.setBase(2.0);
		
		DefaultLogAxisEditor editor = new DefaultLogAxisEditor (l);

		setContentPane (editor);
	}
}
