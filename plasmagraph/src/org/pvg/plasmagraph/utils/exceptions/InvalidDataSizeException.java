package org.pvg.plasmagraph.utils.exceptions;

public class InvalidDataSizeException extends Exception {
	
	private String message;
	
	public InvalidDataSizeException (String s) {
		this.message = s;
	}

}
