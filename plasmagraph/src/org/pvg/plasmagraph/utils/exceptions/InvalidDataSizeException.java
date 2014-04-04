package org.pvg.plasmagraph.utils.exceptions;

import javax.swing.JOptionPane;

public class InvalidDataSizeException extends Exception {
	
	private String message;
	
	public InvalidDataSizeException (String s) {
		this.message = s;
	}
	
	public void showMessage () {
		JOptionPane.showMessageDialog (null, "This data file contains data with uneven parameters and, thus, cannot be completely validated."
				+ "Please correct the data mismatch before trying again.");
	}

}
