package org.pvg.plasmagraph.utils;

@SuppressWarnings ("serial")
public class IncorrectAlphaError extends Exception {
	String message = "";

	public IncorrectAlphaError () {
		
	}
	
	public IncorrectAlphaError (String m) {
		message = m;
	}
}
