package org.pvg.plasmagraph.models;

import org.jfree.chart.plot.PlotOrientation;
// Class Import Block
import org.pvg.plasmagraph.utils.template.Template;

/**
 * Model for the visual modification pane, AestheticView.
 * Controls a JPanel embedded into MainView's JTabbedPane, and
 * allows for modifications of the visual components of the data.
 * 
 * @author Gerardo A. Navas Morales
 */
public class AestheticModel {
    /** Reference to MainModel's Template, passed via constructor reference. */
    private Template t;
    
    // /** Reference to MainModel's DataSet, passed via constructor reference.
    // */
    // private DataSet ds;
    
    /**
     * Creates a new AestheticModel with references to MainModel's
     * graph-manipulation objects,
     * as well as creates and updates its respective view.
     * 
     * @param t_reference
     *            Template reference provided by MainModel.
     */
    public AestheticModel (Template t_reference) {
        // Update currently-used Template and Data Sources.
        t = t_reference;
    }
    
    /**
     * Setter method to change the chart_title parameter in the
     * Template with that of the String provided.
     * Called by AestheticController.
     * 
     * @param text
     *            String containing change to Template parameter chart_title.
     */
    public void changeChartTitle (String text) {
        this.t.setChartName (text);
    }
    
    /**
     * Setter method to change the x_axis_label parameter in the
     * Template with that of the String provided.
     * Called by AestheticController.
     * 
     * @param text
     *            String containing change to Template parameter x_axis_label.
     */
    public void changeXAxisLabel (String text) {
        this.t.setXAxisLabel (text);
    }
    
    /**
     * Setter method to change the y_axis_label parameter in the
     * Template with that of the String provided.
     * Called by AestheticController.
     * 
     * @param text
     *            String containing change to Template parameter y_axis_label.
     */
    public void changeYAxisLabel (String text) {
        this.t.setYAxisLabel (text);
    }
    
    /**
     * Setter method to change the orientation parameter in the
     * Template with that of the PlotOrientation provided.
     * Called by AestheticController.
     * 
     * @param o
     *            PlotOrientation object containing change to Template parameter
     *            orientation.
     */
    public void changePlotOrientation (PlotOrientation o) {
        this.t.setOrientation (o);
    }
    
    /**
     * Getter method. Returns template.
     * Used for AestheticView's "updateView ()" method.
     * 
     * @return t, a reference to the Template object.
     */
    public Template getTemplate () {
        // TODO Auto-generated method stub
        return (t);
    }
    
    /**
     * Support method to add listeners to the Template.
     * 
     * @param c Listener to add to Template Notifier.
     */
    public void addChangeListener (javax.swing.event.ChangeListener c) {
        t.addChangeListener (c);
    }
}
