package org.pvg.plasmagraph.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.pvg.plasmagraph.controllers.AestheticController.ChartNameListener;
import org.pvg.plasmagraph.controllers.AestheticController.HorizontalOrientationListener;
import org.pvg.plasmagraph.controllers.AestheticController.VerticalOrientationListener;
import org.pvg.plasmagraph.controllers.AestheticController.XAxisLabelListener;
import org.pvg.plasmagraph.controllers.AestheticController.YAxisLabelListener;
import org.pvg.plasmagraph.models.AestheticModel;
import org.pvg.plasmagraph.models.DataSetModel;
import org.pvg.plasmagraph.views.AestheticView;
import org.pvg.plasmagraph.views.DataSetView;

/**
 * Controller for the data set modification pane, DataSetView.
 * Controls a JPanel embedded into MainView's JTabbedPane, and
 * allows for communication between its View and Model.
 * @author Gerardo A. Navas Morales
 */
public class DataSetController {
	// Externally-contained variables.
	/** Reference to model related to this controller. */
	private DataSetModel data_model;
	/** Reference to view related to this controller. */
	private DataSetView data_view;
	
	public DataSetController(DataSetModel data_model, DataSetView data_view) {
		// Set related objects into proper positions in object.
		this.data_model = data_model;
		this.data_view = data_view;
		
		// Automatically add listeners to Data Set Tab via view.
		data_view.addChartTypeListener (new ChartTypeListener ());
		data_view.addAddButtonListener (new AddButtonListener ());
		data_view.addRemoveButtonListener (new RemoveButtonListener ());
	}
	
	/**
	 * Listener for the "chart_type" JComboBox part of the DataSetView.
	 * Relies on ActionListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class ChartTypeListener implements ActionListener {

		/** Calls a DataSetModel method to change the chart type on the Template. */
		@Override
		public void actionPerformed (ActionEvent arg0) {
			data_model.changeChartType ((String) arg0.getSource());
		}
		
	}

	/**
	 * Listener for the "add_button" JButton part of the DataSetView.
	 * Relies on ActionListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class AddButtonListener implements ActionListener {

		/** 
		 * Calls a DataSetModel method to add a DataSet to ListModel "list_selected".
		 */
		@Override
		public void actionPerformed (ActionEvent arg0) {
			data_model.addToSelectedDataset (data_view.getSelectedDatasetsToAdd ());
		}
		
	}
	
	/**
	 * Listener for the "remove_button" JButton part of the DataSetView.
	 * Relies on ActionListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class RemoveButtonListener implements ActionListener {

		/** 
		 * Calls a DataSetModel method to remove DataSet from ListModel "list_selected". 
		 */
		@Override
		public void actionPerformed (ActionEvent arg0) {
			data_model.removeFromSelectedDataset (data_view.getSelectedDatasetsToRemove ());
		}
		
	}
}
