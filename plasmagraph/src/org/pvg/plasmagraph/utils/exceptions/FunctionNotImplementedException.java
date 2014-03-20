package org.pvg.plasmagraph.utils.exceptions;

import javax.swing.JOptionPane;

public class FunctionNotImplementedException extends Exception {
	
	public void showMessage () {
		JOptionPane.showMessageDialog (null, "This function is currently unavailable. "
				+ "Please try a different function.");
	}
}
