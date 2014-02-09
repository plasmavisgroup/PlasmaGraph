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
        } else if (i_type.equals (InterpolationType.QUADRATIC.toString ())) {
            return (InterpolationType.QUADRATIC);
        } else if (i_type.equals (InterpolationType.CUBIC.toString ())) {
            return (InterpolationType.CUBIC);
        } else if (i_type.equals (InterpolationType.QUARTIC.toString ())) {
            return (InterpolationType.QUARTIC);
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
     * Getter Method. Returns the contents of the "lower_bound_text_field" as a
     * double.
     * 
     * @return A double, containing the value for use elsewhere.
     */
	public double getLowerInterval () {
		return (Double.parseDouble (this.lower_bound_text_field.getText ()));
	}

	/**
	 * Getter Method. Returns the contents of the "upper_bound_text_field" as a
     * double.
     * 
     * @return A double, containing the value for use elsewhere.
	 */
	public double getUpperInterval () {
		return (Double.parseDouble (this.upper_bound_text_field.getText ()));
	}

	/**
	 * Getter Method. Returns the contents of the "interval_text_field" as an
     * integer.
     * 
     * @return An int, containing the value for use elsewhere.
	 */
	public int getInterval () {
		return (Integer.parseInt (this.interval_text_field.getText ()));
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
    
    /**
	* Initializes the visual components of this view form.
	*/
    private void initComponents () {
        this.setName ("Tool View");
        
        // Initialize
        interpolation_label = new javax.swing.JLabel ();
        outlier_filtering_label = new javax.swing.JLabel ();
        lower_bound_label = new javax.swing.JLabel();
        upper_bound_label = new javax.swing.JLabel();
        interval_label = new javax.swing.JLabel();
        lower_bound_text_field = new javax.swing.JTextField();
        upper_bound_text_field = new javax.swing.JTextField();
        interval_text_field = new javax.swing.JTextField();
        outlier_model = new javax.swing.DefaultComboBoxModel <String> (
                new String [] { "Warn", "Remove" });
        interpolation_model = new javax.swing.DefaultComboBoxModel <String> (
                new String [] { "Linear", "Polynomial", "Power" });
        target_data_model = new javax.swing.DefaultComboBoxModel <String> (
        		new String [] {"Derp", "Nope"});
        interpolation_type_combo_box = new javax.swing.JComboBox <String>
        		(interpolation_model);
        target_data_set_combo_box = new javax.swing.JComboBox <String>
        		(target_data_model);
        outlier_action_combo_box = new javax.swing.JComboBox <String>
        		(outlier_model);
        interpolation_separator = new javax.swing.JSeparator();
        outlier_separator = new javax.swing.JSeparator();
        
        // Interpolation
        interpolation_label.setText ("Interpolation Type");
        lower_bound_label.setText ("Lower Bound");
        upper_bound_label.setText ("Upper Bound");
        interval_label.setText ("# of Points");
        lower_bound_text_field.setHorizontalAlignment (javax.swing.JTextField.TRAILING);
        upper_bound_text_field.setHorizontalAlignment (javax.swing.JTextField.TRAILING);
        interval_text_field.setHorizontalAlignment (javax.swing.JTextField.TRAILING);
        
        // Outlier Filtering
        outlier_filtering_label.setText ("Outlier Filtering");

        // TODO: Update layout!
        // Layout Organization
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(interpolation_separator)
            .addComponent(outlier_separator)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(interpolation_label)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lower_bound_label)
                                    .addComponent(interval_label))))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(interpolation_type_combo_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lower_bound_text_field)
                            .addComponent(interval_text_field))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(upper_bound_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(upper_bound_text_field))
                            .addComponent(target_data_set_combo_box, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(outlier_filtering_label)
                        .addGap(42, 42, 42)
                        .addComponent(outlier_action_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(target_data_set_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(interpolation_label)
                        .addComponent(interpolation_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lower_bound_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lower_bound_label))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(upper_bound_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(upper_bound_label)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(interval_label)
                    .addComponent(interval_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(outlier_separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outlier_action_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outlier_filtering_label))
                .addGap(18, 18, 18)
                .addComponent(interpolation_separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel interpolation_label;
    private javax.swing.JLabel outlier_filtering_label;
    private javax.swing.JLabel lower_bound_label;
    private javax.swing.JLabel upper_bound_label;
    private javax.swing.JLabel interval_label;
    private javax.swing.JComboBox <String> interpolation_type_combo_box;
    private javax.swing.JComboBox <String> outlier_action_combo_box;
    private javax.swing.JComboBox <String> target_data_set_combo_box;
    private javax.swing.DefaultComboBoxModel <String> outlier_model;
    private javax.swing.DefaultComboBoxModel <String> interpolation_model;
    private javax.swing.DefaultComboBoxModel <String> target_data_model;
    private javax.swing.JSeparator interpolation_separator;
    private javax.swing.JSeparator outlier_separator;
    private javax.swing.JTextField lower_bound_text_field;
    private javax.swing.JTextField upper_bound_text_field;
    private javax.swing.JTextField interval_text_field;
    
    // End of variables declaration
    
    // Created by Gerardo A. Navas Morales.
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

	/**
	 * 
	 * @param lowerBoundListener
	 */
	public void addLowerBoundListener 
			(java.awt.event.FocusListener lowerBoundListener) {
		this.lower_bound_text_field.addFocusListener (lowerBoundListener);
	}

	/**
	 * 
	 * @param upperBoundListener
	 */
	public void addUpperBoundListener 
			(java.awt.event.FocusListener upperBoundListener) {
		this.upper_bound_text_field.addFocusListener (upperBoundListener);
	}

	/**
	 * 
	 * @param upperBoundListener
	 */
	public void addIntervalListener 
			(java.awt.event.FocusListener upperBoundListener) {
		this.interval_text_field.addFocusListener (upperBoundListener);
	}
}