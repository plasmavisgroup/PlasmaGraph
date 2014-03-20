package org.pvg.plasmagraph.views;

import org.pvg.plasmagraph.models.ToolModel;
import org.pvg.plasmagraph.utils.types.InterpolationType;
import org.pvg.plasmagraph.utils.types.OutlierResponse;

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
        this.updateTemplateView ();
    }
    
    /**
	* Updates ToolView's Template-based Components based on the current 
	* state of the Template.
	*/
	public void updateTemplateView () {
		this.interpolation_type_combo_box.setSelectedItem (this.tool_model.getTemplate ().getInterpolationType ().toString ());
        this.outlier_response_combo_box.setSelectedItem (this.tool_model.getTemplate ().getOutlierResponse ().toString ());
	}
    
    /**
	* Getter Method. Provides this object's selected interpolation type in the form of an InterpolationType object.
	* @return InterpolationType object based on its representation in the "interpolation_type_combo_box" Component.
	*/
    public InterpolationType getInterpolationType () {
        String i_type = (String) this.interpolation_type_combo_box.getSelectedItem ();

        if (i_type.equals (InterpolationType.NONE.toString ())) {
            return (InterpolationType.NONE);
        } else if (i_type.equals (InterpolationType.LINEAR.toString ())) {
            return (InterpolationType.LINEAR);
        } else if (i_type.equals (InterpolationType.QUADRATIC.toString ())) {
            return (InterpolationType.QUADRATIC);
        } else if (i_type.equals (InterpolationType.CUBIC.toString ())) {
            return (InterpolationType.CUBIC);
        } else if (i_type.equals (InterpolationType.QUARTIC.toString ())) {
            return (InterpolationType.QUARTIC);
        } else {
            return (InterpolationType.SPLINE);
        }
        
    }
    
    /**
	* Getter Method. Provides this object's selected outlier type in the form of an OutlierResponse object.
	* @return OutlierResponse object based on its representation in the "outlier_action_combo_box" Component.
	*/
    public OutlierResponse getOutlierResponseType () {
        String o_type = (String) this.outlier_response_combo_box.getSelectedItem ();
        
        if (o_type.equals (OutlierResponse.NONE.toString ())) {
            return (OutlierResponse.NONE);
        } else if (o_type.equals (OutlierResponse.WARN.toString ())) {
            return (OutlierResponse.WARN);
        } {
            return (OutlierResponse.REMOVE);
        }
    }
    
    /**
	* Initializes the visual components of this view form.
	*/
    private void initComponents () {
        this.setName ("Options View");
        
        // Initialize
        interpolation_type_label = new javax.swing.JLabel ();
        outlier_response_label = new javax.swing.JLabel ();
        outlier_model = new javax.swing.DefaultComboBoxModel <> (
                OutlierResponse.getOptions ());
        interpolation_model = new javax.swing.DefaultComboBoxModel <>
        		(InterpolationType.getOptions ());
		interpolation_type_combo_box = new javax.swing.JComboBox <>
        		(interpolation_model);
		outlier_response_combo_box = new javax.swing.JComboBox <>
        		(outlier_model);
        
		// Interpolation
		interpolation_type_label.setText("Interpolation Type");
		// Outlier Filtering
		outlier_response_label.setText("Outlier Scanning Response");
        		
        // Layout Organization

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outlier_response_combo_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(interpolation_type_combo_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 59, Short.MAX_VALUE)
                .addComponent(outlier_response_label)
                .addGap(53, 53, 53))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(interpolation_type_label)
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outlier_response_label)
                .addGap(11, 11, 11)
                .addComponent(outlier_response_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(interpolation_type_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(interpolation_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(188, Short.MAX_VALUE))
        );
    }

    // Variables declaration - do not modify
    private javax.swing.JComboBox <String> interpolation_type_combo_box;
    private javax.swing.JLabel interpolation_type_label;
    private javax.swing.JComboBox <String> outlier_response_combo_box;
    private javax.swing.JLabel outlier_response_label;
    private javax.swing.DefaultComboBoxModel <String> outlier_model;
    private javax.swing.DefaultComboBoxModel <String> interpolation_model;
    
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
        this.outlier_response_combo_box.addActionListener (outlierResponseListener);
    }
}