package org.pvg.plasmagraph.utils.data;

import java.util.ArrayList;

public class MessageLog {
	
	/** Container for each message. */
	private ArrayList <String> log;
	
	/**
	 * <p>Default constructor. 
	 * <p>Prepares the log for usage.
	 */
	public MessageLog () {
		log = new ArrayList <String> (5);
	}
	
	/**
	 * <p>Constructor.
	 * <p>Prepares the log for usage and passes it one log string.
	 * 
	 * @param s The first string to store in the MessageLog.
	 */
	public MessageLog (String s) {
		log = new ArrayList <String> (6);
		
		log.add (s);
	}
	
	/**
	 * <p>Appends a string to the MessageLog.
	 * 
	 * @param s String to append to the log.
	 * @return A boolean specifying the success or failure of the operation.
	 */
	public boolean add (String s) {
		if (!log.contains (s)) {
			return (log.add (s));
		} else {
			return (false);
		}
	}
	
	/**
	 * <p>Provides the number of objects in the log.
	 * 
	 * @return An integer count of the number of logged messages.
	 */
	public int size () {
		return (log.size ());
	}
	
	@Override
	public String toString () {
		
		String ls = System.getProperty ("line.separator");
		StringBuilder sb = new StringBuilder ();
		
		if (log.size () > 0) {
			
			sb.append (ls);
			
			for (String s : log) {
				sb.append (s).append (ls);
			}
		}
		
		return (sb.toString ());
	}
	

}
