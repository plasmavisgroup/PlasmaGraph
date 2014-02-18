package org.pvg.plasmagraph.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pvg.plasmagraph.models.DataSetModel;
import org.pvg.plasmagraph.views.DataSetView;

/**
 * Controller for the data set modification pane, DataSetView.
 * Controls a JPanel embedded into MainView's JTabbedPane, and
 * allows for communication between its View and Model.
 * 
 * @author Gerardo A. Navas Morales
 */
public class DataSetController {
    // Externally-contained variables.
    /** Reference to model related to this controller. */
    private DataSetModel data_model;
    /** Reference to view related to this controller. */
    private DataSetView data_view;
    
    public DataSetController (DataSetModel data_model, DataSetView data_view) {
        // Set related objects into proper positions in object.
        this.data_model = data_model;
        this.data_view = data_view;
        
        // Automatically add listeners to Data Set Tab via view.
        // Update Template or DataReference Listeners
        data_view.addChartTypeListener (new ChartTypeListener ());
        data_view.addGroupByListener (new GroupByListener ());
        data_view.addAddButtonListener (new AddButtonListener ());
        data_view.addRemoveButtonListener (new RemoveButtonListener ());
        // Update View Listener
        data_model.addChangeListener (new DataViewTemplateListener ());
    }
    
    /**
     * Listener for the "chart_type" JComboBox part of the DataSetView.
     * Relies on ActionListener to manage messages.
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
				data_model.getTemplate ().setChartType (data_view.getSelectedChartType ());
			} catch (Exception e) {
				// TODO Throw a Dialog Exception
			}
        }
        
    }
    
    /**
     * Listener for the "group_by" JComboBox part of the DataSetView.
     * Relies on ActionListener to manage messages.
     * 
     * @author Gerardo A. Navas Morales
     */
    class GroupByListener implements ActionListener {
        
        /**
         * Calls a DataSetModel method to change the chart type on the Template.
         */
        @Override
        public void actionPerformed (ActionEvent arg0) {
            try {
				data_model.getTemplate ().setGroupBy (data_view.getGroupByColumn ());
			} catch (Exception e) {
				// TODO Throw a Dialog Exception
			}
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
         * Calls a DataSetModel method to add a DataSet to ListModel
         * "list_selected".
         */
        @Override
        public void actionPerformed (ActionEvent arg0) {
           ArrayList <String> selected_columns =  data_view.getSelectedDatasetsToAdd ();
            
           if (selected_columns.size () == 2) {
        	   data_model.addToSelectedDataset (selected_columns);
           } else {
        	   // TODO: Create an ArrayListSizeException!
        	   //throw (new ArrayListSizeException ("Only two columns are allowed to be selected."));
           }
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
         * Calls a DataSetModel method to remove DataSet from ListModel
         * "list_selected".
         */
        @Override
        public void actionPerformed (ActionEvent arg0) {
            data_model.removeFromSelectedDataset (data_view
                    .getSelectedDatasetsToRemove ());
        }
        
    }
    
    /**
     * Listener for the Template that contains all settings for the program.
     * Relies on ChangeListener in order to know that a change has occurred
     * in the Template. 
     * 
     * @author Gerardo A. Navas Morales
     */
    class DataViewTemplateListener implements ChangeListener {

        /**
         * Updates the DataSetView's current Template-based state.
         */
        @Override
        public void stateChanged (ChangeEvent e) {
            SwingWorker <Void, Void> view_worker = new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    data_view.updateView ();
                    return null;
                }
                
            };
            
            view_worker.run ();
        }
        
    }
}
