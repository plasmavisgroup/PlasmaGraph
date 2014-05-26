package org.pvg.plasmagraph.views;

import java.awt.event.FocusAdapter;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import org.pvg.plasmagraph.models.DataSetModel;
import org.pvg.plasmagraph.utils.exceptions.TemplateGroupByColumnNotFoundException;
import org.pvg.plasmagraph.utils.types.AxisType;
import org.pvg.plasmagraph.utils.types.ChartType;

/**
 * <p>Graphical User Interface class designed to present the organization of the Data Set Tab on the Settings Window.
 * @author Plasma Visualization Group
 */
@SuppressWarnings ("serial")
public class DataSetView extends javax.swing.JPanel {

	/** Reference to model related to this controller. */
    private DataSetModel data_model;
    
    /**
     * Creates a new OptionsView form, containing references to its model and the underlying settings container.
     * @param dm DataSetModel reference provided by PlasmaGraph.
     */
	public DataSetView (DataSetModel dm) {
		initComponents ();
		
		this.data_model = dm;
	}
	
	/**
     * Updates DataSetView's available options and selected options as available <p>
     * in the ChartType, XAxis, YAxis, and GroupBy settings.
     * 
     * @throws TemplateGroupByColumnNotFoundException 
     */
    public void updateView () throws TemplateGroupByColumnNotFoundException {
    	if (ChartType.XY_GRAPH.equals (data_model.getTemplate ().getChartType ())) {
    		
    		this.updateXYView ();
    		
    	} else {//if (ChartType.BAR_GRAPH.equals (data_model.getTemplate ().getChartType ())) {
    		
    		this.updateBarView ();
    		
    	}
    }
    
    private void updateXYView () {
    	// Enable the X Axis Type.
    	this.x_axis_type_combo_box.setEnabled (true);
    	
    	// Update view.
    	this.updateChartTitle ();
    	this.updateChartType ();
    	this.updateAxesTypes ();
    	this.updateAxesNames ();
	}
	
    private void updateBarView () {
		// Disable the X Axis Type.
    	this.x_axis_type_combo_box.setEnabled (false);
    	
    	// Update view.
    	this.updateChartTitle ();
    	this.updateChartType ();
    	this.updateAxesTypes ();
    	this.updateAxesNames ();
	}
    
    /**
     * Updates the ChartTitle's selected option.
     * Called by a Template change.
     */
    public void updateChartTitle () {

    	// Update selected option.
        this.chart_title_text_field.setText (this.data_model.getTemplate ()
        		.getChartName ());
    }
    
    /**
     * Updates the ChartType options available options and the selected option.
     */
    public void updateChartType () {
    	// Update available options.
    	this.chart_type_combo_box.setModel (
    			new DefaultComboBoxModel <> (ChartType.getOptions ()));
    	
    	// Update selected option.
        this.chart_type_combo_box.setSelectedItem (this.data_model
                .getTemplate ().getChartType ().toString ());
    }
    
    /**
     * Update the XAxisType and YAxisType combo boxes with their proper selections
     * as per the Template.
     */
    public void updateAxesTypes () {
    	if (ChartType.XY_GRAPH.equals (data_model.getTemplate ().getChartType ())) {
    		
    		// Update available options.
    		x_axis_type_combo_box.setModel (
    				new javax.swing.DefaultComboBoxModel <String> (
    				AxisType.getXYOptions ()));
    		
    		y_axis_type_combo_box.setModel (
    				new javax.swing.DefaultComboBoxModel <String> (
					AxisType.getXYOptions ()));

    		
    		// Update selected option.
    		this.x_axis_type_combo_box.setSelectedItem (
        			this.data_model.getTemplate ().getXAxisType ().toString ());
        	
        	this.y_axis_type_combo_box.setSelectedItem (
        			this.data_model.getTemplate ().getYAxisType ().toString ());
    		
    	} else {//if (ChartType.BAR_GRAPH.equals (data_model.getTemplate ().getChartType ())) {
    		
    		// Update available options.
    		x_axis_type_combo_box.setModel (
    				new javax.swing.DefaultComboBoxModel <String> (
    				AxisType.getBarOptions ()));
    		
    		y_axis_type_combo_box.setModel (
    				new javax.swing.DefaultComboBoxModel <String> (
					AxisType.getXYOptions ()));
    		
    		// Update selected option.
    		this.x_axis_type_combo_box.setSelectedItem (AxisType.CATEGORY.toString ());
        			//this.data_model.getTemplate ().getXAxisType ().toString ());
        	
        	this.y_axis_type_combo_box.setSelectedItem (
        			this.data_model.getTemplate ().getYAxisType ().toString ());
    		
    	}
    }
    
    /**
     * Update the X and Y Axis Name Text Boxes with their proper names as per
     * the Template.
     */
    public void updateAxesNames () {
    	this.x_axis_name_text_field.setText (
    			this.data_model.getTemplate ().getXAxisLabel ());
    	
    	this.y_axis_name_text_field.setText (
    			this.data_model.getTemplate ().getYAxisLabel ());
    }
    
    /**
     * Updates the model for the <code>x_column_combo_box</code> variable.
     */
    public void updateXAxisColumn () {
    	// Reset model for this combo box.
    	this.x_column_combo_box.setModel (this.data_model.resetXAxisColumn ());
    	
    	// Update Selected option.
    	if (this.data_model.getGraphPair ().getXColumnIndex () != 0) {
    		this.x_column_combo_box.setSelectedIndex (
    				this.data_model.getGraphPair ().getXColumnIndex ());
    	} else {
    		this.x_column_combo_box.setSelectedIndex (0);
    	}
    }
    
    /**
     * <p>Updates the model for the <code>y_column_combo_box</code> variable.
     */
    public void updateYAxisColumn () {
    	// Reset model for this combo box.
    	this.y_column_combo_box.setModel (this.data_model.resetYAxisColumn ());
    	
    	// Update Selected option.
    	if (this.data_model.getGraphPair ().getYColumnIndex () != 0) {
    		this.y_column_combo_box.setSelectedIndex (
    				this.data_model.getGraphPair ().getYColumnIndex ());
    	} else {
    		this.y_column_combo_box.setSelectedIndex (0);
    	}
    }
    
    /**
     * <p>Updates the model for the <code>group_by_column_combo_box</code> variable based on the current state of the
     * Template. If the value being put in doesn't exist yet, then it will revert
     * to the default value, "None".
     */
    public void updateGroupBy () {
    	// Reset model for this combo box.
    	this.group_by_column_combo_box.setModel (this.data_model.resetGroupByBox ());
    }

	/**
	 * <p>Initializes the Swing variables in this object to the desired form.
	 */
	private void initComponents () {
		this.setName ("Data View");

		x_column_label = new javax.swing.JLabel ();
		x_axis_name_label = new javax.swing.JLabel ();
		x_axis_type_label = new javax.swing.JLabel ();
		y_column_label = new javax.swing.JLabel ();
		y_axis_name_label = new javax.swing.JLabel ();
		y_axis_type_label = new javax.swing.JLabel ();
		x_column_combo_box = new javax.swing.JComboBox <String> ();
		x_axis_name_text_field = new javax.swing.JTextField ();
		x_axis_type_combo_box = new javax.swing.JComboBox <String> ();
		y_column_combo_box = new javax.swing.JComboBox <String> ();
		y_axis_name_text_field = new javax.swing.JTextField ();
		y_axis_type_combo_box = new javax.swing.JComboBox <String> ();
		jSeparator1 = new javax.swing.JSeparator ();
		jSeparator2 = new javax.swing.JSeparator ();
		chart_type_label = new javax.swing.JLabel ();
		chart_type_combo_box = new javax.swing.JComboBox <String> ();
		chart_title_label = new javax.swing.JLabel ();
		chart_title_text_field = new javax.swing.JTextField ();
		jSeparator3 = new javax.swing.JSeparator ();
		group_by_column_label = new javax.swing.JLabel ();
		group_by_column_combo_box = new javax.swing.JComboBox <String> ();
		graph_button = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();

		x_column_label.setText ("X Column");
		x_axis_name_label.setText ("X Axis Name");
		x_axis_type_label.setText ("X Axis Type");

		y_column_label.setText ("Y Column");
		y_axis_name_label.setText ("Y Axis Name");
		y_axis_type_label.setText ("Y Axis Type");

		x_axis_type_combo_box
				.setModel (new javax.swing.DefaultComboBoxModel <String> (
						AxisType.getXYOptions ()));
		y_axis_type_combo_box
				.setModel (new javax.swing.DefaultComboBoxModel <String> (
						AxisType.getXYOptions ()));

		chart_type_label.setText ("Chart Type");
		chart_type_combo_box
				.setModel (new javax.swing.DefaultComboBoxModel <String> (
						ChartType.getOptions ()));

		chart_title_label.setText ("Chart Title");
		group_by_column_label.setText ("Group By Column");
		group_by_column_combo_box
				.setModel (new javax.swing.DefaultComboBoxModel <String> (
						new String [] { "None" }));

		graph_button.setText("Graph");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addComponent(jSeparator4)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chart_title_label)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(x_axis_name_text_field, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(x_axis_type_combo_box, javax.swing.GroupLayout.Alignment.TRAILING, 0, 220, Short.MAX_VALUE)
                            .addComponent(x_column_combo_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(y_axis_name_text_field, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(y_axis_type_combo_box, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(y_column_combo_box, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(group_by_column_combo_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chart_title_text_field)
                            .addComponent(chart_type_combo_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chart_type_label)
                                    .addComponent(x_column_label)
                                    .addComponent(x_axis_name_label)
                                    .addComponent(x_axis_type_label)
                                    .addComponent(y_column_label)
                                    .addComponent(y_axis_name_label)
                                    .addComponent(y_axis_type_label)
                                    .addComponent(group_by_column_label))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(graph_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart_title_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart_title_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chart_type_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(x_column_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(x_column_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(x_axis_name_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(x_axis_name_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(x_axis_type_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(x_axis_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(y_column_label)
                .addGap(4, 4, 4)
                .addComponent(y_column_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(y_axis_name_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(y_axis_name_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(y_axis_type_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(y_axis_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(group_by_column_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(group_by_column_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graph_button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel chart_title_label;
	private javax.swing.JTextField chart_title_text_field;
	private javax.swing.JComboBox <String> chart_type_combo_box;
	private javax.swing.JLabel chart_type_label;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator3;
	private javax.swing.JLabel x_axis_name_label;
	private javax.swing.JTextField x_axis_name_text_field;
	private javax.swing.JComboBox <String> x_axis_type_combo_box;
	private javax.swing.JLabel x_axis_type_label;
	private javax.swing.JComboBox <String> x_column_combo_box;
	private javax.swing.JLabel x_column_label;
	private javax.swing.JLabel y_axis_name_label;
	private javax.swing.JTextField y_axis_name_text_field;
	private javax.swing.JComboBox <String> y_axis_type_combo_box;
	private javax.swing.JLabel y_axis_type_label;
	private javax.swing.JComboBox <String> y_column_combo_box;
	private javax.swing.JLabel y_column_label;
	private javax.swing.JComboBox <String> group_by_column_combo_box;
	private javax.swing.JLabel group_by_column_label;
	private javax.swing.JSeparator jSeparator4;
	private javax.swing.JButton graph_button;
	// End of variables declaration
	
	// Listener Methods.
	/**
	 * Registers the "chart_title" JTextField as an object that should be
	 * listened upon deselecting the box.
	 * 
	 * @param chartTitleListener ActionListener object provided by its Controller.
	 */
	public void addChartTitleListener (FocusAdapter chartTitleListener) {
		this.chart_title_text_field.addFocusListener (chartTitleListener);
	}
	
	/**
     * Registers the "chart_type" JComboBox as an object that should be
     * listened upon a new option being chosen.
     * 
     * @param chartTypeListener
     *            ActionListener object provided by its Controller.
     */
    public void addChartTypeListener (ActionListener chartTypeListener) {
        this.chart_type_combo_box.addActionListener (chartTypeListener);
    }
    
    /**
	 * Registers the "x_column" JComboBox as an object that should be
	 * listened upon a new option being chosen.
	 * 
	 * @param xColumnListener ActionListener object provided by its Controller.
	 */
	public void addXColumnListener (ActionListener xColumnListener) {
		this.x_column_combo_box.addActionListener (xColumnListener);
	}

	/**
	 * Registers the "y_column" JComboBox as an object that should be
	 * listened upon a new option being chosen.
	 * 
	 * @param yColumnListener ActionListener object provided by its Controller.
	 */
	public void addYColumnListener (ActionListener yColumnListener) {
		this.y_column_combo_box.addActionListener (yColumnListener);
	}
	
	/**
     * Registers the "group_by" JComboBox as an object that should be
     * listened upon a new option being chosen.
     * 
     * @param groupByColumnListener
     *            ActionListener object provided by its Controller.
     */
	public void addGroupByColumnListener (ActionListener groupByColumnListener) {
		 this.group_by_column_combo_box.addActionListener (groupByColumnListener);
	}

	/**
	 * Registers the "x_axis_name" JTextField as an object that should be
	 * listened upon deselecting the box.
	 * 
	 * @param xAxisNameListener ActionListener object provided by its Controller.
	 */
	public void addXAxisNameListener (FocusAdapter xAxisNameListener) {
		this.x_axis_name_text_field.addFocusListener (xAxisNameListener);
	}

	/**
	 * Registers the "y_axis_name" JTextField as an object that should be
	 * listened upon deselecting the box.
	 * 
	 * @param yAxisNameListener ActionListener object provided by its Controller.
	 */
	public void addYAxisNameListener (FocusAdapter yAxisNameListener) {
		this.y_axis_name_text_field.addFocusListener (yAxisNameListener);
	}

	/**
	 * Registers the "x_axis_type" JComboBox as an object that should be
	 * listened upon a new option being chosen.
	 * 
	 * @param xAxisTypeListener ActionListener object provided by its Controller.
	 */
	public void addXAxisTypeListener (ActionListener xAxisTypeListener) {
		this.x_axis_type_combo_box.addActionListener (xAxisTypeListener);
	}

	/**
	 * Registers the "y_axis_type" JComboBox as an object that should be
	 * listened upon a new option being chosen.
	 * 
	 * @param yAxisTypeListener ActionListener object provided by its Controller.
	 */
	public void addYAxisTypeListener (ActionListener yAxisTypeListener) {
		this.y_axis_type_combo_box.addActionListener (yAxisTypeListener);
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
	
	// Getter Methods
	
	/**
	 * Getter method. Provides the value selected in the "chart_title" JTextField as a String object.
	 * 
	 * @return The String of the currently-selected option in the "chart_title" JTextField.
	 */
	public String getChartName () {
		return (this.chart_title_text_field.getText ());
	}
	
	/**
     * Getter Method. Provides this object's "chart_type_combo_box"'s value as one of the options provided by the ChartType class.
     * @return A ChartType object based on the representation in the "chart_type_combo_box" JComboBox object.
     */
    public ChartType getChartType () {
        String c_type = (String) this.chart_type_combo_box.getSelectedItem ();

        if (c_type.equals (ChartType.XY_GRAPH.toString ())) {
            return (ChartType.XY_GRAPH);
        } else { //if (c_type.equals (ChartType.BAR_GRAPH.toString ())) {
            return (ChartType.BAR_GRAPH);
        } 
    }
	
	/**
	 * Getter method. Provides the value selected in the "x_column" JComboBox as a String object.
	 * 
	 * @return The String of the currently-selected option in the "x_column" JComboBox.
	 */
	public String getXColumn () {
		return ((String) this.x_column_combo_box.getSelectedItem ());
	}
	
	/**
	 * Getter method. Provides the value selected in the "y_column" JComboBox as a String object.
	 * 
	 * @return The String of the currently-selected option in the "y_column" JComboBox.
	 */
	public String getYColumn () {
		return ((String) this.y_column_combo_box.getSelectedItem ());
	}
	
	/**
     * Getter Method. Provides this object's "group_by_combo_box"'s value.
     * 
     * @return A String object selected by the user from the list of available data columns.
     */
    public String getGroupingByElement () {
    	return (String) (this.group_by_column_combo_box.getSelectedItem ());
    }
	
	/**
	 * Getter method. Provides the value selected in the "x_axis_name" JTextField as a String object.
	 * 
	 * @return The String of the currently-selected option in the "x_axis_name" JTextField.
	 */
	public String getXAxisName () {
		return (this.x_axis_name_text_field.getText ());
	}

	/**
	 * Getter method. Provides the value selected in the "y_axis_name" JTextField as a String object.
	 * 
	 * @return The String of the currently-selected option in the "y_axis_name" JTextField.
	 */
	public String getYAxisName () {
		return (this.y_axis_name_text_field.getText ());
	}

	/**
	 * Getter method. Provides the value selected in the "x_axis_type" JComboBox as a String object.
	 * 
	 * @return The String of the currently-selected option in the "x_axis_type" JComboBox.
	 */
	public String getXAxisType () {
		return ((String) this.x_axis_type_combo_box.getSelectedItem ());
	}

	/**
	 * Getter method. Provides the value selected in the "y_axis_type" JComboBox as a String object.
	 * 
	 * @return The String of the currently-selected option in the "y_axis_type" JComboBox.
	 */
	public String getYAxisType () {
		return ((String) this.y_axis_type_combo_box.getSelectedItem ());
	}
}
