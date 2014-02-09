package org.pvg.plasmagraph.models;

// Class Import Block
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.template.Template;

// import utils.data.DataSet;
import org.pvg.plasmagraph.utils.tools.*;

/**
 * Model for the usage of additional tools to aid in data modelling.
 * Manipulates the ToolView to provide functionality to the multiple
 * tools available.
 * 
 * @author Gerardo A. Navas Morales
 */
public class ToolModel {
    // Externally-contained variables.
    /** Reference to MainModel's Template, passed via constructor reference. */
    private Template t;
    /** Reference to MainModel's DataSet, passed via constructor reference. */
    private DataSet ds;
    /** Reference to MainModel's DataReference, passed via constructor reference. */
    private DataReference dr;
    
    /**
     * Creates a new ToolModel with references to MainModel's graph-manipulation
     * objects,
     * as well as creates and updates its respective view.
     * 
     * @param t_reference
     *            Template reference provided by MainModel.
     * @param dr
     */
    public ToolModel (Template t_reference, DataSet ds_reference,
            DataReference dr_reference) {
        // Update currently-used Template and Data Sources.
        t = t_reference;
        ds = ds_reference;
        dr = dr_reference;
    }
    
    /**
     * Getter method. Returns template. Used for ToolView's "updateView ()" method.
     * 
     * @return t, a reference to the Template object.
     */
    public Template getTemplate () {
        return (t);
    }
    
    /**
     * Provides the names of each Data Pair in the DataReference variable in a
     * String array format, specifically for its DefaultComboBoxModel class.
     * 
     * @return A String array of all the DataReference pair names.
     */
    public String [] getDataReferenceNames () {
        return (dr.getNames ());
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
