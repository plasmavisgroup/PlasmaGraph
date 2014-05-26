package org.pvg.plasmagraph.utils.exceptions;

import javax.swing.JOptionPane;

public class FunctionNotImplementedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6941117534800184839L;
	private String message;
	
	public FunctionNotImplementedException (String s) {
		this.message = s;
	}
	
	public void showMessage () {
		JOptionPane.showMessageDialog (null, "The function " + this.message
				+ " is currently unavailable.\n"
				+ "Please try a different function.");
	}
}
