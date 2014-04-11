package org.pvg.plasmagraph.utils.exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings ("serial")
public class TemplateGroupByColumnNotFoundException extends Exception {

	public void showMessage () {
		JOptionPane.showMessageDialog (null, "The column " + this.getMessage () + " was not found in the data.\n"
				+ "Please select a valid column to Group By if you wish to Group the Data.");
	}
	
}
