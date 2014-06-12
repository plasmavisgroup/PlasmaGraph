package org.pvg.plasmagraph.utils.exceptions;

import javax.swing.JOptionPane;

public class InvalidFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9187370569779363503L;

	public InvalidFileException () {
	}

	public void showMessage () {
		JOptionPane.showMessageDialog (null, "Invalid MATLAB file. "
				+ "\n" + "This file does not have two or more columns."
				+ "\n" + "Please import a file with at least two columns.");
	}
}
