package org.pvg.plasmagraph.utils.exceptions;

import javax.swing.JOptionPane;

public class InvalidTypeException extends Exception {
	String message;
	
	public InvalidTypeException (String s) {
		this.message = s;
	}

	public void showMessage () {
		JOptionPane.showMessageDialog (null, "There were invalid parameters provided in the process of " + this.getMessage ()
				+ ".\n" + "Please review the available settings before trying again.");
	}
}
