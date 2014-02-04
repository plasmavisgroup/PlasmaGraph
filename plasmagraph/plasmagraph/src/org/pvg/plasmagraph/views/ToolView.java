package org.pvg.plasmagraph.views;

import org.pvg.plasmagraph.models.ToolModel;
import org.pvg.plasmagraph.utils.template.InterpolationType;
import org.pvg.plasmagraph.utils.template.OutlierResponse;

/**
 * View for the optional tools modification pane.
 * Defines the visual organization of a JPanel embedded
 * into MainView's JTabbedPane, and communicates changes
 * done to the View to the Model via the Controller's Listeners.
 * 
 * @author Gerardo A. Navas Morales
 */
@SuppressWarnings ("serial")
public class ToolView extends javax.swing.JPanel {
    // Externally-referenced variables.
    /** Reference to model related to this controller. */
    private ToolModel tool_model;
    
    /**
     * Creates a new ToolView form, containing references to its model and the underlying settings container.
     * @param tool_model Model reference provided by PlasmaGraph.
     */
    public ToolView (ToolModel tool_model) {
        // Set object variables.
        this.tool_model = tool_model;
        
        // Initialize View organization as per NetBeans IDE code.
        this.initComponents ();
    }
    
    // Created by Gerardo A. Navas Morales.
    /**
     * Updates ToolView's Components based on the current state of the Template.
     */
    public void updateView () {
        this.interpolation_type_combo_box.setSelectedItem (this.tool_model.getTemplate ().getInterpolationType ().toString ());
        this.outlier_action_combo_box.setSelectedItem (this.tool_model.getTemplate ().getOutlierResponse ().toString ());
        updateTargetDataSetComboBox ();
    }
    
    /**
     * Getter Method. Provides this object's selected interpolation type in the form of an InterpolationType object.
     * @return InterpolationType object based on its representation in the "interpolation_type_combo_box" Component.
     */
    public InterpolationType getInterpolationType () {
        String i_type = (String) this.interpolation_type_combo_box.getSelectedItem ();

        if (i_type.equals (InterpolationType.LINEAR.toString ())) {
            return (InterpolationType.LINEAR);
        } else if (i_type.equals (InterpolationType.POLYNOMIAL.toString ())) {
            return (InterpolationType.POLYNOMIAL);
        } else {
            return (InterpolationType.POWER);
        }
        
    }
    
    /**
     * Getter Method. Provides this object's selected outlier type in the form of an OutlierResponse object.
     * @return OutlierResponse object based on its representation in the "outlier_action_combo_box" Component.
     */
    public OutlierResponse getOutlierResponseType () {
        String o_type = (String) this.outlier_action_combo_box.getSelectedItem ();
        
        if (o_type.equals (OutlierResponse.WARN.toString ())) {
            return (OutlierResponse.WARN);
        } else {
            return (OutlierResponse.REMOVE); 
        }
    }

    /**
     * Getter Method. Provides this object's selected data pair's index for the
     * purposes of performing Interpolation or Outlier Search operations on them.
     * 
     * @return Integer value of the selected index for the "target_data_set_combo_box".
     */
    public int getSelectedDataPairIndex () {
        return (this.target_data_set_combo_box.getSelectedIndex ());
    }
    
    /**
     * Setter Method. Updates the selected data pair's combo box options based on the
     * values contained in "tool_model"'s DataReference object.
     */
    private void updateTargetDataSetComboBox () {
        target_data_set_combo_box.setModel
            (new javax.swing.DefaultComboBoxModel <String>
            (tool_model.getDataReferenceNames ())); 
    }
    
    // Created by NetBeans IDE.
    /**
     * Initializes the visual components of this view form.
     */
    private void initComponents () {
        this.setName ("Tool View");
        
        interpolation_label = new javax.swing.JLabel ();
        interpolation_type_combo_box = new javax.swing.JComboBox <String> ();
        target_data_set_combo_box = new javax.swing.JComboBox <String> ();
        outlier_search_label = new javax.swing.JLabel ();
        interpolation_outlier_separator = new javax.swing.JSeparator ();
        interpolation_button = new javax.swing.JButton ();
        outlier_action_combo_box = new javax.swing.JComboBox <String> ();
        outlier_button = new javax.swing.JButton ();
        outlier_model = new javax.swing.DefaultComboBoxModel <String> (
                new String [] { "Warn", "Remove" });
        interpolation_model = new javax.swing.DefaultComboBoxModel <String> (
                new String [] { "Linear", "Polynomial", "Power" });
        
        interpolation_label.setText ("Interpolation");
        
        interpolation_type_combo_box
                .setModel (interpolation_model);
        
        outlier_search_label.setText ("Outlier Search");
        
        interpolation_button.setText ("Create Interpolation");
        
        outlier_action_combo_box
                .setModel (outlier_model);
        
        outlier_button.setText ("Search for Outliers");
        
        updateTargetDataSetComboBox ();
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout (this);
        this.setLayout (layout);
        layout.setHorizontalGroup (layout
                .createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent (interpolation_outlier_separator)
                .addGroup (
                        layout.createSequentialGroup ()
                                .addContainerGap ()
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent (outlier_button)
                                                .addComponent (
                                                        interpolation_button)
                                                .addGroup (
                                                        layout.createSequentialGroup ()
                                                                .addComponent (
                                                                        interpolation_label)
                                                                .addGap (136,
                                                                        136,
                                                                        136)
                                                                .addComponent (
                                                                        interpolation_type_combo_box,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup (
                                                        layout.createSequentialGroup ()
                                                                .addComponent (
                                                                        outlier_search_label)
                                                                .addGap (136,
                                                                        136,
                                                                        136)
                                                                .addComponent (
                                                                        outlier_action_combo_box,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap (100, Short.MAX_VALUE)));
        layout.setVerticalGroup (layout
                .createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup (
                        layout.createSequentialGroup ()
                                .addContainerGap ()
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent (
                                                        interpolation_label)
                                                .addComponent (
                                                        interpolation_type_combo_box,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap (18, 18, 18)
                                .addComponent (interpolation_button)
                                .addPreferredGap (
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent (interpolation_outlier_separator,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap (
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup (
                                        layout.createParallelGroup (
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent (
                                                        outlier_search_label)
                                                .addComponent (
                                                        outlier_action_combo_box,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap (18, 18, 18)
                                .addComponent (outlier_button)
                                .addContainerGap (131, Short.MAX_VALUE)));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton interpolation_button;
    private javax.swing.JLabel interpolation_label;
    private javax.swing.JSeparator interpolation_outlier_separator;
    private javax.swing.JComboBox <String> interpolation_type_combo_box;
    private javax.swing.JComboBox <String> outlier_action_combo_box;
    private javax.swing.JComboBox <String> target_data_set_combo_box;
    private javax.swing.JButton outlier_button;
    private javax.swing.JLabel outlier_search_label;
    private javax.swing.DefaultComboBoxModel <String> outlier_model;
    private javax.swing.DefaultComboBoxModel <String> interpolation_model;
    
    // End of variables declaration  
    
    // Created by Gerardo A. Navas Morales.
    /**
     * Registers the "interpolation" button as an object that should be listened to upon being selected.
     * 
     * @param interpolationTypeListener ActionListener object provided by its Controller.
     */
    public void addInterpolationListener (
            java.awt.event.ActionListener interpolationListener) {
        this.interpolation_button.addActionListener (interpolationListener);
    }
    
    /**
     * Registers the "outlier_search" button as an object that should be listened to upon being selected.
     * 
     * @param interpolationTypeListener ActionListener object provided by its Controller.
     */
    public void addOutlierListener (
            java.awt.event.ActionListener outlierListener) {
        this.outlier_button.addActionListener (outlierListener);
    }
    
    /**
     * Registers the "outlier_response" combo box as an object that should be listened to upon an option being selected.
     * 
     * @param interpolationTypeListener ActionListener object provided by its Controller.
     */
    public void addInterpolationTypeListener (
            java.awt.event.ActionListener interpolationTypeListener) {
        this.interpolation_type_combo_box
                .addActionListener (interpolationTypeListener);
    }
    
    /**
     * Registers the "outlier_response" combo box as an object that should be listened to upon an option being selected.
     * 
     * @param outlierResponseListener ActionListener object provided by its Controller.
     */
    public void addOutlierResponseListener (
            java.awt.event.ActionListener outlierResponseListener) {
        this.outlier_action_combo_box.addActionListener (outlierResponseListener);
    }
}
