package org.pvg.plasmagraph.utils.exceptions;

import javax.swing.JOptionPane;

public class FunctionNotImplementedException extends Exception {
	
	private String message;
	
	public FunctionNotImplementedException (String s) {
		this.message = s;
	}
	
	public void showMessage () {
		JOptionPane.showMessageDialog (null, "This function is currently unavailable. "
				+ "Please try a different function.");
	}
}
