package org.pvg.plasmagraph.errors;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ExceptionHandler {
	// TODO: Everything. Forever.
	// Organize this class; make it more modular, etc.
	public static void createEmptyArrayException () {
		String message = "Error: No Data Items were selected.\n"
				+ "Please select at least one data item to add /remove.";
		JOptionPane error_window = new JOptionPane (message, JOptionPane.ERROR_MESSAGE);
		
		JDialog dialog = error_window.createDialog("Error");
		
		dialog.setVisible(true);
	}

}
