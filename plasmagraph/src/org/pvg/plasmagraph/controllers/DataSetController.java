package org.pvg.plasmagraph.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pvg.plasmagraph.models.DataSetModel;
import org.pvg.plasmagraph.views.DataSetView;

/**
 * Controller for the data set modification pane, DataSetView. Controls a JPanel
 * embedded into MainView's JTabbedPane, and allows for communication between
 * its View and Model.
 * 
 * @author Gerardo A. Navas Morales
 */
public class DataSetController {
	// Externally-contained variables.
	/** Reference to model related to this controller. */
	DataSetModel data_model;
	/** Reference to view related to this controller. */
	DataSetView data_view;

	/**
	 * Constructor for DataSetControllers. Used only by the PlasmaGraph class,
	 * and only used once.
	 * 
	 * @param data_model
	 *            Reference to this object's model.
	 * @param data_view
	 *            Refernece to this object's view.
	 */
	public DataSetController (DataSetModel data_model, DataSetView data_view) {
		// Set related objects into proper positions in object.
		this.data_model = data_model;
		this.data_view = data_view;

		// Automatically add listeners to Data Set Tab via view.

		// chart_type_combo_box
		data_view.addChartTypeListener (new ChartTypeListener ());
		// chart_title_text_field
		data_view.addChartTitleListener (new ChartTitleListener ());
		// x_axis_name_text_field
		data_view.addXAxisNameListener (new XAxisNameListener ());
		// y_axis_name_text_field
		data_view.addYAxisNameListener (new YAxisNameListener ());
		// x_axis_type_combo_box
		data_view.addXAxisTypeListener (new XAxisTypeListener ());
		// y_axis_type_combo_box
		data_view.addYAxisTypeListener (new YAxisTypeListener ());
		// x_column_combo_box
		data_view.addXColumnListener (new XColumnListener ());
		// y_column_combo_box
		data_view.addYColumnListener (new YColumnListener ());
		// group_by_column_combo_box
		data_view.addGroupByColumnListener (new GroupByColumnListener ());

		// Update View Listener
		data_model.addTemplateChangeListener (new DataViewTemplateListener ());
		data_model
				.addHeaderDataChangeListener (new DataViewHeaderDataListener ());
	}

	/**
	 * Listener for the "chart_type" JComboBox part of the DataSetView. Relies
	 * on FocusListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class ChartTitleListener implements FocusListener {

		@Override
		public void focusGained (FocusEvent e) {
			// Empty
		}

		/**
		 * Calls a DataSetModel method to change the chart type on the Template.
		 */
		@Override
		public void focusLost (FocusEvent e) {
			data_model.getTemplate ().setChartName (data_view.getChartName ());

			// Notify relevant listeners.
			data_model.getTemplate ().notifyListeners ();
		}

	}

	/**
	 * Listener for the "chart_type" JComboBox part of the DataSetView. Relies
	 * on ActionListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class ChartTypeListener implements ActionListener {

		/**
		 * Calls a DataSetModel method to change the chart type on the Template.
		 */
		@Override
		public void actionPerformed (ActionEvent arg0) {
			try {
				data_model.getTemplate ().setChartType (
						data_view.getChartType ());

				// Notify relevant listeners.
				data_model.getTemplate ().notifyListeners ();
				
			} catch (Exception e) {
				// TODO Throw a Dialog Exception
			}
		}

	}

	/**
	 * Listener for the "chart_type" JComboBox part of the DataSetView. Relies
	 * on ActionListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class XColumnListener implements ActionListener {

		/**
		 * Calls a DataSetModel method to change the chart type on the Template.
		 */
		@Override
		public void actionPerformed (ActionEvent arg0) {
			try {
				
				data_model.getTemplate ().setXAxisColumn (data_view.getXColumn ());
				data_model.setGraphPair (data_view.getGroupingByElement (),
						data_view.getXColumn (), data_view.getYColumn ());

				// Notify relevant listeners.
				data_model.getTemplate ().notifyListeners ();
				data_model.getReference ().notifyListeners ();
			} catch (Exception e) {
				// TODO Throw a Dialog Exception
			}
		}

	}

	/**
	 * Listener for the "chart_type" JComboBox part of the DataSetView. Relies
	 * on ActionListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class YColumnListener implements ActionListener {

		/**
		 * Calls a DataSetModel method to change the chart type on the Template.
		 */
		@Override
		public void actionPerformed (ActionEvent arg0) {
			try {
				
				data_model.getTemplate ().setYAxisColumn (data_view.getYColumn ());
				data_model.setGraphPair (data_view.getGroupingByElement (),
						data_view.getXColumn (), data_view.getYColumn ());

				// Notify relevant listeners.
				data_model.getTemplate ().notifyListeners ();
				data_model.getReference ().notifyListeners ();
			} catch (Exception e) {
				// TODO Throw a Dialog Exception
			}
		}

	}

	/**
	 * Listener for the "chart_type" JComboBox part of the DataSetView. Relies
	 * on FocusListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class XAxisNameListener implements FocusListener {

		@Override
		public void focusGained (FocusEvent e) {
			// Empty
		}

		/**
		 * Calls a DataSetModel method to change the chart type on the Template.
		 */
		@Override
		public void focusLost (FocusEvent e) {
			data_model.getTemplate ().setXAxisLabel (data_view.getXAxisName ());

			// Notify relevant listeners.
			data_model.getTemplate ().notifyListeners ();
		}

	}

	/**
	 * Listener for the "chart_type" JComboBox part of the DataSetView. Relies
	 * on FocusListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class YAxisNameListener implements FocusListener {

		@Override
		public void focusGained (FocusEvent e) {
			// Empty
		}

		/**
		 * Calls a DataSetModel method to change the chart type on the Template.
		 */
		@Override
		public void focusLost (FocusEvent e) {
			data_model.getTemplate ().setYAxisLabel (data_view.getYAxisName ());

			// Notify relevant listeners.
			data_model.getTemplate ().notifyListeners ();
		}

	}

	/**
	 * Listener for the "chart_type" JComboBox part of the DataSetView. Relies
	 * on ActionListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class XAxisTypeListener implements ActionListener {

		/**
		 * Calls a DataSetModel method to change the chart type on the Template.
		 */
		@Override
		public void actionPerformed (ActionEvent arg0) {
			try {
				data_model.getTemplate ().setXAxisType (
						data_view.getYAxisType ());

				// Notify relevant listeners.
				data_model.getTemplate ().notifyListeners ();
			} catch (Exception e) {
				// TODO Throw a Dialog Exception
			}
		}

	}

	/**
	 * Listener for the "chart_type" JComboBox part of the DataSetView. Relies
	 * on ActionListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class YAxisTypeListener implements ActionListener {

		/**
		 * Calls a DataSetModel method to change the chart type on the Template.
		 */
		@Override
		public void actionPerformed (ActionEvent arg0) {
			try {
				data_model.getTemplate ().setYAxisType (
						data_view.getYAxisType ());

				// Notify relevant listeners.
				data_model.getTemplate ().notifyListeners ();
			} catch (Exception e) {
				// TODO Throw a Dialog Exception
			}
		}

	}

	/**
	 * Listener for the "group_by" JComboBox part of the DataSetView. Relies on
	 * ActionListener to manage messages.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class GroupByColumnListener implements ActionListener {

		/**
		 * Calls a DataSetModel method to change the chart type on the Template.
		 */
		@Override
		public void actionPerformed (ActionEvent arg0) {
			try {
				data_model.setGraphPair (data_view.getGroupingByElement (),
						data_view.getXColumn (), data_view.getYColumn ());

				// Notify relevant listeners.
				data_model.getReference ().notifyListeners ();
			} catch (Exception e) {
				// TODO Throw a Dialog Exception
			}
		}

	}

	/**
	 * Listener for the Template that contains all settings for the program.
	 * Relies on ChangeListener in order to know that a change has occurred in
	 * the Template.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class DataViewTemplateListener implements ChangeListener {

		/**
		 * Updates the DataSetView's current Template-based state.
		 */
		@Override
		public void stateChanged (ChangeEvent e) {
			SwingWorker <Void, Void> template_worker = new SwingWorker <Void, Void> () {

				@Override
				protected Void doInBackground () throws Exception {
					data_view.updateView ();
					return null;
				}

			};

			template_worker.run ();
		}

	}

	/**
	 * Listener for the DataSet that contains all settings for the program.
	 * Relies on ChangeListener in order to know that a change has occurred in
	 * the DataSet.
	 * 
	 * @author Gerardo A. Navas Morales
	 */
	class DataViewHeaderDataListener implements ChangeListener {

		/**
		 * Updates the AestheticView's current Template-based state.
		 */
		@Override
		public void stateChanged (ChangeEvent e) {
			SwingWorker <Void, Void> data_worker = new SwingWorker <Void, Void> () {

				@Override
				protected Void doInBackground () throws Exception {
					data_view.updateXAxisColumn ();
					data_view.updateYAxisColumn ();
					data_view.updateGroupBy ();
					return null;
				}

			};

			data_worker.run ();
		}

	}
}
