package org.pvg.plasmagraph.controllers;

//Class Import Block
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import org.pvg.plasmagraph.models.AestheticModel;
import org.pvg.plasmagraph.views.AestheticView;

/**
 * Controller for the visual modification pane, AestheticView.
 * Controls a JPanel embedded into MainView's JTabbedPane, and
 * allows for communication between the View and Model.
 * 
 * @author Gerardo A. Navas Morales
 */
public class AestheticController {
	/** Reference to model related to this controller. */
	private AestheticModel aesthetic_model;
	/** Reference to view related to this controller. */
	private AestheticView aesthetic_view;
	
	/**
     * Creates a new Controller for the AestheticModel/View.
     * Manages messages (mostly FocusGained/Lost messages) sent by the user via the View.
     * Relies on the Model to change data.
     * 
     * @param aesthetic_model Model component reference of the Aesthetic Panel.
     * @param aesthetic_view View component reference of the Aesthetic Panel.
     */
	public AestheticController(AestheticModel aesthetic_model, AestheticView aesthetic_view) {
		// Set related objects into proper positions in object.
		this.aesthetic_model = aesthetic_model;
		this.aesthetic_view = aesthetic_view;
		
		// Automatically add listeners to Aesthetic Tab via view.
		aesthetic_view.addChartNameListener (new ChartNameListener ());
		aesthetic_view.addXAxisLabelListener (new XAxisLabelListener ());
		aesthetic_view.addYAxisLabelListener (new YAxisLabelListener ());
		aesthetic_view.addHorizontalOrientationListener (new HorizontalOrientationListener ());
		aesthetic_view.addVerticalOrientationListener (new VerticalOrientationListener ());

	}
	
	/** Listener for the "chart_name" text box part of the AestheticView. 
	 * Relies on FocusListener in order to manage messages.
	 */
	class ChartNameListener implements FocusListener {

		/** TODO */
		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		/** TODO */
		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/** Listener for the "x_axis_label" text box part of the AestheticView. 
	 * Relies on FocusListener in order to manage messages.
	 */
	class XAxisLabelListener implements FocusListener {

		/** TODO */
		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

		/** TODO */
		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/** Listener for the "y_axis_label" text box part of the AestheticView. 
	 * Relies on FocusListener in order to manage messages.
	 */
	class YAxisLabelListener implements FocusListener {

		/** TODO */
		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

		/** TODO */
		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	/** Listener for the "horizontal_orientation" radio button part of the AestheticView. 
	 * Relies on FocusListener in order to manage messages.
	 */
	class HorizontalOrientationListener implements FocusListener {

		/** TODO */
		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

		/** TODO */
		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/** Listener for the "vertical_orientation" radio button part of the AestheticView. 
	 * Relies on FocusListener in order to manage messages.
	 */
	class VerticalOrientationListener implements FocusListener {

		/** TODO */
		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

		/** TODO */
		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
