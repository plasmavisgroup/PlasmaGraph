package org.pvg.plasmagraph.utils;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ExceptionHandler {
	
	// TODO: Everything. Forever.
	// Organize this class; make it more modular, etc.

	public static void createFunctionNotImplementedException (String function_name) {
		String message = "Error: The functionality requested from the program does not exist.\n"
				+ "Please try another option.";
		JOptionPane error_window = new JOptionPane (message, JOptionPane.ERROR_MESSAGE);
		
		JDialog dialog = error_window.createDialog("Error");
		
		dialog.setVisible(true);
	}

	public static void createFileSelectionException (String string) {
		String message = "Error: There was an error in selecting files in the option:"
				+ string;
		JOptionPane error_window = new JOptionPane (message, JOptionPane.ERROR_MESSAGE);
		
		JDialog dialog = error_window.createDialog ("Error");
		
		dialog.setVisible (true);
	}

	public static void createEmptyArrayException (String string) {
		String message = "Error:" + string;
		JOptionPane error_window = new JOptionPane (message, JOptionPane.ERROR_MESSAGE);
		
		JDialog dialog = error_window.createDialog("Error");
		
		dialog.setVisible(true);
	}
	
	public static void createMalformedDataFileException (String string) {
		String message = "File Error: " + string + " has found that this file "
				+ "contains columns with varying sizes. Please check the data file "
				+ "provided and correct appropriately.";
		JOptionPane error_window = new JOptionPane (message, JOptionPane.ERROR_MESSAGE);
		
		JDialog dialog = error_window.createDialog("Error");
		
		dialog.setVisible(true);
	}

}
