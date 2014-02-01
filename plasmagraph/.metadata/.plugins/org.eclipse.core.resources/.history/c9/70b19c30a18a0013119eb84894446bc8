package org.pvg.plasmagraph.utils;

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

	public static void createFunctionNotImplementedException (String function_name) {
		String message = "Error: The functionality requested from the program does not exist.\n"
				+ "Please try another option.";
		JOptionPane error_window = new JOptionPane (message, JOptionPane.ERROR_MESSAGE);
		
		JDialog dialog = error_window.createDialog("Error");
		
		dialog.setVisible(true);
	}

	public static void createFileSelectionException(String string) {
		// TODO Auto-generated method stub
		
	}

}
