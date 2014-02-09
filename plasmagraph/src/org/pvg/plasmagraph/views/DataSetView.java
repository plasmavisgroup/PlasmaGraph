package org.pvg.plasmagraph.views;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.pvg.plasmagraph.models.DataSetModel;
import org.pvg.plasmagraph.utils.graphs.ChartType;

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
     * Updates DataSetView's ComboBox and JLists based on the current state of the
     * Template.
     */
    public void updateView () {
        this.chart_type_combo_box.setSelectedItem (this.data_model
                .getTemplate ().getChartType ().toString ());
        this.data_model.resetListModels ();
    }
    
    /**
     * Getter Method. Provides this object's "chart_type_combo_box"'s value as one of the options provided by the ChartType class.
     * @return A ChartType object based on the representation in the "chart_type_combo_box" JComboBox object.
     */
    public ChartType getSelectedChartType () {
        String c_type = (String) this.chart_type_combo_box.getSelectedItem ();

        if (c_type.equals (ChartType.XY_GRAPH.toString ())) {
            return (ChartType.XY_GRAPH);
        } else if (c_type.equals (ChartType.LINE_GRAPH.toString ())) {
            return (ChartType.LINE_GRAPH);
        } else if (c_type.equals (ChartType.BAR_GRAPH.toString ())) {
            return (ChartType.BAR_GRAPH);
        } else {
            return (ChartType.PIE_GRAPH);
        }
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
        
        available_datasets_pane = new javax.swing.JScrollPane ();
        available_datasets_list = new javax.swing.JList <String> (
                this.data_model.getAvailableListModel ());
        selected_datasets_pane = new javax.swing.JScrollPane ();
        selected_datasets_list = new javax.swing.JList <String> (
                this.data_model.getSelectedListModel ());
        chart_type_combo_box = new javax.swing.JComboBox <String> ();
        chart_type_label = new javax.swing.JLabel ();
        add_button = new javax.swing.JButton ();
        remove_button = new javax.swing.JButton ();
        
        available_datasets_list.setCursor (new java.awt.Cursor (
                java.awt.Cursor.DEFAULT_CURSOR));
        available_datasets_pane.setViewportView (available_datasets_list);
        
        selected_datasets_pane.setViewportView (selected_datasets_list);
        
        chart_type_combo_box
                .setModel (new javax.swing.DefaultComboBoxModel <String> (
                        new String [] { "Bar Graph", "XY Graph", "Pie Graph",
                                "Line Graph" }));
        
        chart_type_label.setText ("Chart Type");
        
        add_button.setText ("Add");
        
        remove_button.setText ("Remove");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout (this);
        this.setLayout (layout);
        layout.setHorizontalGroup (layout
                .createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup (
                        layout.createSequentialGroup ()
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup (
                                                        layout.createSequentialGroup ()
                                                                .addContainerGap (
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent (
                                                                        chart_type_label)
                                                                .addPreferredGap (
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent (
                                                                        chart_type_combo_box,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap (63,
                                                                        63, 63))
                                                .addGroup (
                                                        layout.createSequentialGroup ()
                                                                .addContainerGap ()
                                                                .addGroup (
                                                                        layout.createParallelGroup (
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup (
                                                                                        layout.createSequentialGroup ()
                                                                                                .addComponent (
                                                                                                        add_button,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        Short.MAX_VALUE)
                                                                                                .addPreferredGap (
                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                                                .addComponent (
                                                                                        available_datasets_pane,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        226,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap (18, 18, 18)
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                false)
                                                .addComponent (
                                                        remove_button,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)
                                                .addComponent (
                                                        selected_datasets_pane))
                                .addContainerGap ()));
        layout.setVerticalGroup (layout
                .createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup (
                        javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup ()
                                .addContainerGap ()
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent (
                                                        chart_type_combo_box,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent (chart_type_label))
                                .addPreferredGap (
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                false)
                                                .addComponent (
                                                        selected_datasets_pane,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        194, Short.MAX_VALUE)
                                                .addComponent (
                                                        available_datasets_pane))
                                .addPreferredGap (
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent (add_button)
                                                .addComponent (remove_button))
                                .addContainerGap (32, Short.MAX_VALUE)));
    }

    // Variables declaration - do not modify
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
