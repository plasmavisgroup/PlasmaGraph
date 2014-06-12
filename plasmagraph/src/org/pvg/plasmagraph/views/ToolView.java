package org.pvg.plasmagraph.views;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;

import org.pvg.plasmagraph.models.ToolModel;
import org.pvg.plasmagraph.utils.types.ChartType;
import org.pvg.plasmagraph.utils.types.InterpolationType;
import org.pvg.plasmagraph.utils.types.OutlierDistanceType;
import org.pvg.plasmagraph.utils.types.OutlierResponse;

/**
* <p>Graphical User Interface class designed to present the organization of the Tools Tab on the Settings Window.
* @author Plasma Visualization Group
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
    	if (ChartType.XY_GRAPH.equals (tool_model.getTemplate ().getChartType ())) {
    		
    		this.updateXYView ();
    		
    	} else {//if (ChartType.BAR_GRAPH.equals (tool_model.getTemplate ().getChartType ())) {
    		
    		this.updateBarView ();
    		
    	}
    }
    
    private void updateXYView () {

    	// Enable everything.
    	this.interpolation_type_combo_box.setEnabled (true);
    	this.outlier_response_combo_box.setEnabled (true);
    	this.outlier_distance_type_combo_box.setEnabled (true);
    	this.maximum_distance_text_box.setEnabled (true);
    	
    	// If the Interpolation Type is currently set to Spline, do not allow the user to change the interpolation bounds.
    	// This is due to the Spline interpolation's inability to predict beyond its bounds.
    	// See Apache Commons Math 3.2's JavaDocs for more information.
    	/*if (InterpolationType.SPLINE.equals (this.tool_model.getTemplate ().getInterpolationType ())) {
    		this.interpolation_lower_bound_text_field.setEnabled (false);
        	this.interpolation_upper_bound_text_field.setEnabled (false);
    	} else {
    		this.interpolation_lower_bound_text_field.setEnabled (true);
        	this.interpolation_upper_bound_text_field.setEnabled (true);
    	}*/
    	
    	// Update view.
    	this.interpolation_type_combo_box.setSelectedItem (
    			this.tool_model.getTemplate ().getInterpolationType ().toString ());
        this.outlier_response_combo_box.setSelectedItem (
        		this.tool_model.getTemplate ().getOutlierResponse ().toString ());
        this.outlier_distance_type_combo_box.setSelectedItem (
        		this.tool_model.getTemplate ().getOutlierDistanceType ());
        this.maximum_distance_text_box.setText (Double.toString (
        		this.tool_model.getTemplate ().getOutlierDistance ()));
       /* this.interpolation_lower_bound_text_field.setText (Double.toString (
        		this.tool_model.getTemplate ().getLowerInterval ()));
        this.interpolation_upper_bound_text_field.setText (Double.toString (
        		this.tool_model.getTemplate ().getUpperInterval ()));*/
	}
    
    private void updateBarView () {
    	
    	// Disable everything.
    	this.interpolation_type_combo_box.setEnabled (false);
    	this.outlier_response_combo_box.setEnabled (false);
    	//this.interpolation_lower_bound_text_field.setEnabled (false);
    	//this.interpolation_upper_bound_text_field.setEnabled (false);
    	
    	// Update view.
    	this.interpolation_type_combo_box.setSelectedIndex (0);
        this.outlier_response_combo_box.setSelectedIndex (0);
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
        } else {
            return (OutlierResponse.REMOVE);
        }
    }
    
    /**
	* Getter Method. Provides this object's selected outlier distance type in 
	* the form of an OutlierDistanceType object.
	* @return OutlierDistanceType object based on its representation in the "outlier_distance_type_combo_box" Component.
	*/
    public OutlierDistanceType getOutlierDistanceType () {
        String o_type = (String) this.outlier_distance_type_combo_box.getSelectedItem ();
        
        if (o_type.equals (OutlierDistanceType.MAHALANOBIS.toString ())) {
            return (OutlierDistanceType.MAHALANOBIS);
        } else {
            return (OutlierDistanceType.USER);
        }
    }
    
    public double getMaximumDistance () {
    	try {
    		return (Double.parseDouble (this.maximum_distance_text_box.getText ()));
    	} catch (NumberFormatException ex) {
    		 JOptionPane.showMessageDialog (this, "The text provided is not a valid number.\n"
    		 		+ "Please provide a valid number for the Outlier Scan distance.");
    		 return (0.0);
    	}
    }
    
    /**
	* Initializes the visual components of this view form.
	*/
    private void initComponents () {
        this.setName ("Options View");
        
        // Initialize
        outlier_response_label = new javax.swing.JLabel();
        outlier_response_combo_box = new javax.swing.JComboBox <String> ();
        
        outlier_distance_type_label = new javax.swing.JLabel();
        outlier_distance_type_combo_box = new javax.swing.JComboBox <String> ();
        
        maximum_distance_label = new javax.swing.JLabel();
        maximum_distance_text_box = new javax.swing.JTextField();
        
        interpolation_type_label = new javax.swing.JLabel();
        interpolation_type_combo_box = new javax.swing.JComboBox <String> ();
        
        graph_button = new javax.swing.JButton();
        graph_separator = new javax.swing.JSeparator();

 		// Outlier Filtering
        outlier_distance_type_label.setText ("Outlier Distance Type");
        distance_model = new javax.swing.DefaultComboBoxModel <String> (
        		OutlierDistanceType.getOptions ());
        outlier_distance_type_combo_box.setModel (distance_model);

        maximum_distance_label.setText ("Maximum Manual Cartesian Distance");
        maximum_distance_text_box.setText (Double.toString (
        		this.tool_model.getTemplate ().getOutlierDistance ()));
        
 		outlier_response_label.setText ("Outlier Scanning Response");
 		outlier_model = new javax.swing.DefaultComboBoxModel <> (
                OutlierResponse.getOptions ());
		outlier_response_combo_box = new javax.swing.JComboBox <> (
				outlier_model);

        // Interpolation
      	interpolation_type_label.setText("Interpolation Type");

      	interpolation_model = new javax.swing.DefaultComboBoxModel <> (
      			InterpolationType.getOptions ());
		interpolation_type_combo_box = new javax.swing.JComboBox <> (
				interpolation_model);

        graph_button.setText ("Graph");

        // Layout Organization
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graph_separator)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maximum_distance_text_box)
                            .addComponent(outlier_response_combo_box, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(graph_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(interpolation_type_combo_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(outlier_distance_type_combo_box, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(outlier_distance_type_label))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(outlier_response_label)
                                    .addComponent(maximum_distance_label))))
                        .addGap(46, 46, 46)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(interpolation_type_label)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outlier_distance_type_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outlier_distance_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outlier_response_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outlier_response_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(maximum_distance_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(maximum_distance_text_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(graph_separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(interpolation_type_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(interpolation_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(graph_button)
                .addContainerGap())
        );
    }

    // Variables declaration - do not modify
    private javax.swing.JComboBox <String> interpolation_type_combo_box;
    private javax.swing.JLabel interpolation_type_label;
    private javax.swing.JComboBox <String> outlier_response_combo_box;
    private javax.swing.JLabel outlier_response_label;
    private javax.swing.JLabel maximum_distance_label;
    private javax.swing.JTextField maximum_distance_text_box;
    private javax.swing.JComboBox <String> outlier_distance_type_combo_box;
    private javax.swing.JLabel outlier_distance_type_label;
    private javax.swing.DefaultComboBoxModel <String> outlier_model;
    private javax.swing.DefaultComboBoxModel <String> distance_model;
    private javax.swing.DefaultComboBoxModel <String> interpolation_model;
    private javax.swing.JSeparator graph_separator;
    private javax.swing.JButton graph_button;
    
    // End of variables declaration
    
    // Created by Gerardo A. Navas Morales.
    /**
	* Registers the "interpolation_type" combo box as an object that should be listened to upon an option being selected.
	*
	* @param interpolationTypeListener ItemListener object provided by its Controller.
	*/
    public void addInterpolationTypeListener (ItemListener interpolationTypeListener) {
        this.interpolation_type_combo_box.addItemListener (interpolationTypeListener);
    }
    
    /**
	* Registers the "interpolation_lower_bound" text box as an object that should be listened to upon an option being selected.
	*
	* @param lowerBoundListener FocusAdapter object provided by its Controller.
	*/
    public void addLowerBoundListener (FocusAdapter lowerBoundListener) {
        this.interpolation_type_combo_box.addFocusListener (lowerBoundListener);
    }
    
    /**
	* Registers the "interpolation_upper_bound" text box as an object that should be listened to upon an option being selected.
	*
	* @param upperBoundListener FocusAdapter object provided by its Controller.
	*/
    public void addUpperBoundListener (FocusAdapter upperBoundListener) {
        this.interpolation_type_combo_box.addFocusListener (upperBoundListener);
    }
    
    /**
	* Registers the "outlier_response" combo box as an object that should be listened to upon an option being selected.
	*
	* @param outlierResponseListener ItemListener object provided by its Controller.
	*/
    public void addOutlierResponseListener (ItemListener outlierResponseListener) {
        this.outlier_response_combo_box.addItemListener (outlierResponseListener);
    }
    
    /**
	* Registers the "outlier_response" combo box as an object that should be listened to upon an option being selected.
	*
	* @param outlier_distance_type_listener ItemListener object provided by its Controller.
	*/
    public void addOutlierDistanceTypeListener (ItemListener outlier_distance_type_listener) {
        this.outlier_distance_type_combo_box.addItemListener (outlier_distance_type_listener);
    }
    
    /**
	* Registers the "outlier_response" combo box as an object that should be listened to upon an option being selected.
	*
	* @param outlier_distance_listener FocusAdapter object provided by its Controller.
	*/
    public void addOutlierDistanceListener (FocusAdapter outlier_distance_listener) {
        this.maximum_distance_text_box.addFocusListener (outlier_distance_listener);
    }
    
    /**
	 * Registers the "graph_button" JButton as an object that should be
	 * listened upon being selected.
	 * 
	 * @param graphListener ActionListener object provided by its Controller.
	 */
	public void addGraphListener (ActionListener graphListener) {
		this.graph_button.addActionListener (graphListener);
	}
}