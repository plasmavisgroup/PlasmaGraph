package org.pvg.plasmagraph.views;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.pvg.plasmagraph.models.DataSetModel;
import org.pvg.plasmagraph.utils.types.ChartType;

/**
 * View for the data set modification pane.
 * Defines the visual organization of a JPanel embedded
 * into MainView's JTabbedPane, and communicates changes
 * done to the View to the Model via the Controller's Listeners.
 * 
 * @author Gerardo A. Navas Morales
 */
@SuppressWarnings ("serial")
public class DataSetView extends javax.swing.JPanel {
    // Externally-contained variables.
    /** Reference to model related to this controller. */
    private DataSetModel data_model;
    
    /**
     * Creates a new DataSetView form, containing references to its model and the underlying settings container.
     * @param data_model Model reference provided by PlasmaGraph.
     */
    public DataSetView (DataSetModel dm) {
        data_model = dm;
        
        // Prepare visual components.
        this.initComponents ();
    }
    
    // Created by Gerardo A. Navas Morales.
    /**
     * Updates DataSetView's everything, ComboBoxes, jLists, CheckBoxes, and all.
     */
    public void updateView () {
    	this.updateTemplateView ();
    	this.updateAvailableList ();
    	this.updateSelectedList ();
    }
    
    /**
     * Updates DataSetView's ComboBox and JLists based on the current state of the
     * Template.
     */
    public void updateTemplateView () {
        this.chart_type_combo_box.setSelectedItem (this.data_model
                .getTemplate ().getChartType ().toString ());
        this.group_by_check_box.setSelected (this.data_model
        		.getTemplate ().isGroupedByExperiment ());
    }
    
    /**
     * Updates DataSetView's JLists based on the current state of the DataSet.
     */
	public void updateAvailableList () {
		this.available_datasets_list.setModel (this.data_model.resetAvailableList ());
	}
	
	/**
     * Updates DataSetView's JLists based on the current state of the Datareference.
     */
	public void updateSelectedList () {
		this.selected_datasets_list.setModel (this.data_model.resetSelectedList ());
	}

	/**
     * Getter Method. Provides this object's "chart_type_combo_box"'s value as one of the options provided by the ChartType class.
     * @return A ChartType object based on the representation in the "chart_type_combo_box" JComboBox object.
     * @throws Exception 
     */
    public ChartType getSelectedChartType () throws Exception {
        String c_type = (String) this.chart_type_combo_box.getSelectedItem ();

        if (c_type.equals (ChartType.XY_GRAPH.toString ())) {
            return (ChartType.XY_GRAPH);
        } else if (c_type.equals (ChartType.BAR_GRAPH.toString ())) {
            return (ChartType.BAR_GRAPH);
        } else {
        	throw (new Exception ("No such graph type"));
        }
    }
    
    /**
     * Getter Method. Provides this object's "group_by_combo_box"'s value.
     * @return A String object selected by the user from the list of available data columns.
     */
    public boolean getGroupingByElement () {
    	return (this.group_by_check_box.isSelected ());
    }
    
    /**
     * Getter method. Provides all datasets selected in the
     * "available_datasets_list"
     * JList object.
     * 
     * @return An ArrayList<String> object containing all selected datasets.
     */
    public ArrayList <String> getSelectedDatasetsToAdd () {
        return ((ArrayList <String>) this.available_datasets_list
                .getSelectedValuesList ());
    }
    
    /**
     * Getter method. Provides all datasets selected in the
     * "selected_datasets_list"
     * JList object.
     * 
     * @return An ArrayList<String> object containing all selected datasets.
     */
    public ArrayList <String> getSelectedDatasetsToRemove () {
        return ((ArrayList <String>) this.selected_datasets_list
                .getSelectedValuesList ());
    }
    
    // Created by NetBeans IDE.
    /**
     * Initializes the visual components of this view form.
     */
    private void initComponents () {
        this.setName ("Data Set View");

        add_button = new javax.swing.JButton();
        remove_button = new javax.swing.JButton();
        chart_type_label = new javax.swing.JLabel();
        chart_type_combo_box = new javax.swing.JComboBox <String>
				(ChartType.getOptions ());
        group_by_label = new javax.swing.JLabel();
        group_by_check_box = new javax.swing.JCheckBox();
        
        available_datasets_pane = new javax.swing.JScrollPane();
        available_datasets_list = new javax.swing.JList <String> (
                this.data_model.resetAvailableList ());
        
        selected_datasets_pane = new javax.swing.JScrollPane();
        selected_datasets_list = new javax.swing.JList <String> (
                this.data_model.resetSelectedList ());

        setMaximumSize(new java.awt.Dimension(460, 300));
        setMinimumSize(new java.awt.Dimension(460, 300));
        setPreferredSize(new java.awt.Dimension(460, 300));

        
        
        available_datasets_pane.setViewportView (available_datasets_list);
        selected_datasets_pane.setViewportView (selected_datasets_list);

        add_button.setText ("Add Available Pair");
        remove_button.setText ("Remove Selected Pair");

        chart_type_label.setText ("Chart Type:");
        group_by_label.setText ("Group By:");


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chart_type_label)
                        .addGap(18, 18, 18)
                        .addComponent(chart_type_combo_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(available_datasets_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(add_button, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(group_by_label, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(group_by_check_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(remove_button, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addComponent(selected_datasets_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(group_by_check_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chart_type_label)
                            .addComponent(chart_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(group_by_label))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(available_datasets_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(selected_datasets_pane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(remove_button)
                    .addComponent(add_button))
                .addContainerGap())
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JCheckBox group_by_check_box;
    private javax.swing.JLabel group_by_label;
    private javax.swing.JButton add_button;
    private javax.swing.JList <String> available_datasets_list;
    private javax.swing.JScrollPane available_datasets_pane;
    private javax.swing.JComboBox <String> chart_type_combo_box;
    private javax.swing.JLabel chart_type_label;
    private javax.swing.JButton remove_button;
    private javax.swing.JList <String> selected_datasets_list;
    private javax.swing.JScrollPane selected_datasets_pane;
    // End of variables declaration     
    
    // Created by Gerardo A. Navas Morales.
    /**
     * Registers the "chart_type" JComboBox as an object that should be
     * listened upon a new selection being made.
     * 
     * @param changeChartTypeListener
     *            ActionListener object provided by its Controller.
     */
    public void addChartTypeListener (ActionListener changeChartTypeListener) {
        this.chart_type_combo_box.addActionListener (changeChartTypeListener);
    }
    
    /**
     * Registers the "group_by" JComboBox as an object that should be
     * listened upon a new selection being made.
     * 
     * @param changeChartTypeListener
     *            ActionListener object provided by its Controller.
     */
    public void addGroupByListener (ActionListener changeGroupByListener) {
        this.group_by_check_box.addActionListener (changeGroupByListener);
    }
    
    /**
     * Registers the "add_button" JButton as an object that should be listened
     * upon being pressed by the user.
     * 
     * @param addButtonListener
     *            ActionListener object provided by its Controller.
     */
    public void addAddButtonListener (ActionListener addButtonListener) {
        this.add_button.addActionListener (addButtonListener);
    }
    
    /**
     * Registers the "remove_button" JButton as an object that should be
     * listened
     * upon being pressed by the user.
     * 
     * @param removeButtonListener
     *            ActionListener object provided by its Controller.
     */
    public void addRemoveButtonListener (ActionListener removeButtonListener) {
        this.remove_button.addActionListener (removeButtonListener);
    }
}
