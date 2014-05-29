package org.pvg.plasmagraph.utils.exceptions;

import javax.swing.JOptionPane;

public class InvalidParametersException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3721450135699901184L;
	String message;
	
	public InvalidParametersException (String s) {
		this.message = s;
	}

	public void showMessage () {
		JOptionPane.showMessageDialog (null, "There were invalid parameters provided in the process of " + this.getMessage ()
				+ ".\n" + "Please review the available settings before trying again.");
	}
}
