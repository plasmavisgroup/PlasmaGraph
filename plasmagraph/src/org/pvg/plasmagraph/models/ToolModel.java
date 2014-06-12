package org.pvg.plasmagraph.models;

// Class Import Block

import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.MessageLog;
import org.pvg.plasmagraph.utils.template.Template;


/**
 * Model for the usage of additional tools to aid in data modelling.
 * Manipulates the ToolView to provide functionality to the multiple
 * tools available.
 * 
 * @author Plasma Visualization Group
 */
public class ToolModel {
    // Externally-contained variables.
	// Externally-contained variables.
    /** Reference to PlasmaGraph's Template, passed via constructor reference. */
    private Template t;
    /** Reference to PlasmaGraph's HeaderData, passed via constructor reference. */
    @SuppressWarnings ("unused")
	private HeaderData hd;
    /** Reference to PlasmaGraph's GraphPair, passed via constructor reference. */
    private GraphPair p;
    /** Reference to PlasmaGraph's MessageLog, passed via constructor reference. */
    @SuppressWarnings ("unused")
	private MessageLog ml;
    
    /**
     * Creates a new ToolModel with references to MainModel's graph-manipulation
     * objects, as well as creates and updates its respective view.
     * 
     * @param t_reference Template reference provided by PlasmaGraph.
     * @param hd_reference HeaderData reference provided by PlasmaGraph.
     * @param p_reference GraphPair reference provided by PlasmaGraph.
     * @param ml 
     */
    public ToolModel (Template t_reference, HeaderData hd_reference,
            GraphPair p_reference, MessageLog ml_reference) {
        // Update currently-used Template and Data Sources.
        t = t_reference;
        hd = hd_reference;
        p = p_reference;
        ml = ml_reference;
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
     * Support method to add listeners to the Template.
     * 
     * @param c Listener to add to Template Notifier.
     */
    public void addTemplateChangeListener (javax.swing.event.ChangeListener c) {
        t.addChangeListener (c);
    }
    
    /**
     * Support method to add listeners to the GraphPair.
     * 
     * @param c Listener to add to GraphPair Notifier.
     */
    public void addGraphPairChangeListener (javax.swing.event.ChangeListener c) {
        p.addChangeListener (c);
    }
}
