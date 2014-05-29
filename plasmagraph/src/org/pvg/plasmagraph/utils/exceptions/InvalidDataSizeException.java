package org.pvg.plasmagraph.utils.exceptions;

import javax.swing.JOptionPane;

public class InvalidDataSizeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6497515755900453564L;
	@SuppressWarnings ("unused")
	private String message;
	
	public InvalidDataSizeException (String s) {
		this.message = s;
	}
	
	public void showMessage () {
		JOptionPane.showMessageDialog (null, "This data file contains data with uneven column sizes"
				+ "\nand, thus, cannot be completely validated."
				+ "\nPlease correct the data mismatch before trying again.");
	}

}
