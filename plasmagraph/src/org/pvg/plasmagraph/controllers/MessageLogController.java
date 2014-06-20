package org.pvg.plasmagraph.controllers;

import javax.swing.SwingWorker;

import org.pvg.plasmagraph.utils.data.MessageLog;
import org.pvg.plasmagraph.views.MessageLogView;

/**
 * Controller for the message log container pane, MessageLogView. 
 * Controls a JPanel and allows for communication between its View and Model.
 * 
 * @author Plasma Visualization Group
 */
public class MessageLogController {

	/** Reference to view related to this controller. */
	private MessageLogView message_view;
	
	/**
	 * Constructor for MessageLogControllers. 
	 * Used only by the PlasmaGraph class, and only used once.
	 * 
	 * @param mlview Reference to this object's view.
	 */
	public MessageLogController (MessageLogView mlview) {
	
		this.message_view = mlview;
	}
	
	/**
	 * Adds additional content to the Message Log.
	 * 
	 * @param text String to add to the Message Log.
	 */
	public void append (final MessageLog log) {
		SwingWorker <Void, Void> message_log_worker = new SwingWorker <Void, Void> () {

			@Override
			protected Void doInBackground () throws Exception {
				message_view.appendToTextArea (log.toString ());
				return null;
			}
			
		};
		
		message_log_worker.run ();
	}
	
}
