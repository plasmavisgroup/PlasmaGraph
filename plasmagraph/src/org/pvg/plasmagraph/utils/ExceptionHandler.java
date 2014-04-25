package org.pvg.plasmagraph.utils;

import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.pvg.plasmagraph.utils.types.ExceptionType;

import com.jmatio.types.MLArray;

/**
 * JFrame handler for all Exceptions. Contains all error-handling code that would be 
 * shown to the user.
 * 
 * @author Plasma Visualization Group
 */
public class ExceptionHandler {
	
	// TODO: Everything. Forever.
	// Organize this class; make it more modular, etc.

	/**
	 * @param function_name
	 */
	public static void handleFunctionNotImplementedException (String function_name) {
		String message = "Error: The functionality requested from the program\n"
				+ "does not exist. Please try another option.";
		JOptionPane error_window = new JOptionPane (message, JOptionPane.ERROR_MESSAGE);
		
		JDialog dialog = error_window.createDialog("Error");
		
		dialog.setVisible(true);
	}

	/**
	 * @param string
	 */
	public static void handleEmptyArrayException (String string) {
		String message = "Error: " + string;
		JOptionPane error_window = new JOptionPane (message, JOptionPane.ERROR_MESSAGE);
		
		JDialog dialog = error_window.createDialog("Error");
		
		dialog.setVisible(true);
	}
	
	/**
	 * @param string
	 */
	public static void handleMalformedDataFileException (String string) {
		String message = "File Error: " + string + " has found that this file\n"
				+ "contains columns with varying sizes. Please check the data file\n"
				+ "provided and correct appropriately.";
		JOptionPane error_window = new JOptionPane (message, JOptionPane.ERROR_MESSAGE);
		
		JDialog dialog = error_window.createDialog("Error");
		
		dialog.setVisible(true);
	}

	/**
	 * @param type
	 */
	public static void handleNullPointerException (ExceptionType type) {

		if (ExceptionType.JFILECHOOSER_SELECTION.equals (type)) {
			String message = "The file selected is not an acceptable file.\n"
					+ "Please try again later.";
			JOptionPane error_window = new JOptionPane (message, JOptionPane.ERROR_MESSAGE);
			
			JDialog dialog = error_window.createDialog("Error");
			
			dialog.setVisible(true);
		}
	}

	public static void showRemovedColumnDialog (
			ArrayList <Entry <String, MLArray>> remove_array) {
		
		StringBuilder sb = new StringBuilder ();
		
		sb.append ("The following columns cannot be graphed due to lack of data: ").append ("\n");
		
		for (Entry <String, MLArray> e : remove_array) {
			sb.append (e.getKey ()).append ("\n");
		}
		
		JOptionPane.showMessageDialog (null, sb.toString ());
		
	}

}
