package org.pvg.plasmagraph.views;

import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import org.pvg.plasmagraph.models.MainModel;

/**
 * View for the program settings modification pane.
 * Defines the visual organization of the primary JFrame, its menu options, and the JTabbedFrame which contains the AestheticView, DataSetView, and ToolView.
 * Also communicates changes done to the View to the Model via the Controller's Listeners.
 * 
 * @author Gerardo A. Navas Morales
 */
@SuppressWarnings ("serial")
public class MainView extends javax.swing.JFrame {
    // Externally-referenced variables.
    /** Reference to model related to this controller. */
    @SuppressWarnings ("unused")
    private MainModel main_model;
    
    
    /**
     * Creates a new MainView form, containing references to its model and the underlying settings container.
     * @param main_model_reference Model reference provided by PlasmaGraph.
     */
    public MainView (MainModel main_model_reference) {
        // Initialize model and view references.
        main_model = main_model_reference;
        
        // Initialize visual components.
        initComponents ();
    }
     
    /**
     * Initializes the visual components of this view form.
     */
    private void initComponents () {
        tab_pane = new javax.swing.JTabbedPane ();
        menu_bar = new javax.swing.JMenuBar ();
        data_menu = new javax.swing.JMenu ();
        import_data_option = new javax.swing.JMenuItem ();
        data_filter_menu = new javax.swing.JMenu ();
        import_data_filter_option = new javax.swing.JMenuItem ();
        modify_data_filter_option = new javax.swing.JMenuItem ();
        save_data_filter_option = new javax.swing.JMenuItem ();
        template_menu = new javax.swing.JMenu ();
        import_template_option = new javax.swing.JMenuItem ();
        save_template_option = new javax.swing.JMenuItem ();
        graph_menu = new javax.swing.JMenu ();
        interpolation_check_box = new javax.swing.JCheckBox ();
        outlier_check_box = new javax.swing.JCheckBox ();
        create_graph_option = new javax.swing.JMenuItem ();
        options_menu = new javax.swing.JMenu ();
        exit_menu_option = new javax.swing.JMenuItem ();
        
        setDefaultCloseOperation (javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle ("PlasmaGraph");
        setName ("PlasmaGraph"); // NOI18N
        
        // Data Menu
        data_menu.setText ("Data");
        data_menu.setToolTipText ("Menu regarding data.");
        
        // Import Data
        import_data_option.setAccelerator (javax.swing.KeyStroke.getKeyStroke (
                java.awt.event.KeyEvent.VK_D,
                java.awt.event.InputEvent.CTRL_MASK));
        import_data_option.setText ("Import Data");
        import_data_option
                .setToolTipText ("Import a data file into the system.");
        import_data_option.setActionCommand ("ImportData");
        data_menu.add (import_data_option);
        
        menu_bar.add (data_menu);
        
        // Data Filter Menu
        data_filter_menu.setText ("Data Filter");
        data_filter_menu
                .setToolTipText ("Menu regarding data filter used in data importing.");
        
        // Import Data Filter
        import_data_filter_option.setAccelerator (javax.swing.KeyStroke
                .getKeyStroke (java.awt.event.KeyEvent.VK_F,
                        java.awt.event.InputEvent.CTRL_MASK));
        import_data_filter_option.setText ("Import Data Filter");
        import_data_filter_option
                .setToolTipText ("Imports an existing data filter from a file.");
        import_data_filter_option.setActionCommand ("ImportDataFilter");
        data_filter_menu.add (import_data_filter_option);
        
        // Modify Data Filter
        modify_data_filter_option.setAccelerator (javax.swing.KeyStroke
                .getKeyStroke (java.awt.event.KeyEvent.VK_M,
                        java.awt.event.InputEvent.CTRL_MASK));
        modify_data_filter_option.setText ("Modify Data Filter");
        modify_data_filter_option
                .setToolTipText ("Opens a window to modify the current data filter.");
        modify_data_filter_option.setActionCommand ("ModifyDataFilter");
        data_filter_menu.add (modify_data_filter_option);
        
        // Save Data Filter
        save_data_filter_option.setAccelerator (javax.swing.KeyStroke
                .getKeyStroke (java.awt.event.KeyEvent.VK_I,
                        java.awt.event.InputEvent.CTRL_MASK));
        save_data_filter_option.setText ("Save Data Filter");
        save_data_filter_option
                .setToolTipText ("Store the current data filter in a file.");
        data_filter_menu.add (save_data_filter_option);
        
        menu_bar.add (data_filter_menu);
        
        // Template Menu
        template_menu.setText ("Templates");
        template_menu.setToolTipText ("Menu regarding option templates.");
        
        // Import Template
        import_template_option.setAccelerator (javax.swing.KeyStroke
                .getKeyStroke (java.awt.event.KeyEvent.VK_T,
                        java.awt.event.InputEvent.CTRL_MASK));
        import_template_option.setText ("Import Template");
        import_template_option
                .setToolTipText ("Import a template file into the system.");
        import_template_option.setActionCommand ("ImportTemplate");
        template_menu.add (import_template_option);
        
        // Save Template
        save_template_option.setAccelerator (javax.swing.KeyStroke
                .getKeyStroke (java.awt.event.KeyEvent.VK_S,
                        java.awt.event.InputEvent.CTRL_MASK));
        save_template_option.setText ("Save Template");
        save_template_option
                .setToolTipText ("Store the current template in a file.");
        save_template_option.setActionCommand ("SaveTemplate");
        template_menu.add (save_template_option);
        
        menu_bar.add (template_menu);
        
        // Graph Menu
        graph_menu.setText ("Graph");
        graph_menu
                .setToolTipText ("Creates a graph according to the selected data and options.");
        
        // Create Graph
        create_graph_option.setText ("Create Graph");
        create_graph_option.setActionCommand ("CreateGraph");
        graph_menu.add (create_graph_option);
        
        // Interpolation
        interpolation_check_box.setText ("Interpolate?");
        interpolation_check_box.setToolTipText ("Performs an interpolation of data pair selected in Tools Tab. "
        		+ "Only graphs the data points and the interpolated line for one data pair.");
        graph_menu.add (interpolation_check_box);
        
        // Outlier Search
        outlier_check_box.setText ("Find Outliers?");
        outlier_check_box.setToolTipText ("Checks for outliers right before graphing. "
        		+ "Warns or immediately removes data based on selection in the Tools Tab.");
        graph_menu.add (outlier_check_box);
        
        menu_bar.add (graph_menu);
        
        // Options Menu
        options_menu.setText ("Options");
        
        // Exit Program
        exit_menu_option.setText ("Exit");
        exit_menu_option.setToolTipText ("Close this program.");
        exit_menu_option.setActionCommand ("Exit");
        options_menu.add (exit_menu_option);
        
        menu_bar.add (options_menu);
        
        // Prepare JMenuBar
        setJMenuBar (menu_bar);
        
        // Editing overall layout.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout (
                getContentPane ());
        getContentPane ().setLayout (layout);
        layout.setHorizontalGroup (layout.createParallelGroup (
                javax.swing.GroupLayout.Alignment.LEADING).addComponent (
                tab_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 643,
                Short.MAX_VALUE));
        layout.setVerticalGroup (layout.createParallelGroup (
                javax.swing.GroupLayout.Alignment.LEADING).addComponent (
                tab_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 378,
                Short.MAX_VALUE));
        
        getAccessibleContext ()
                .setAccessibleDescription (
                        "A data-graphing program for the Java interpreted programming language.");
        
        pack ();
    }

    // Variables declaration - do not modify
    private javax.swing.JMenuItem create_graph_option;
    private javax.swing.JMenu data_filter_menu;
    private javax.swing.JMenu data_menu;
    private javax.swing.JMenu graph_menu;
    private javax.swing.JMenu options_menu;
    private javax.swing.JMenuItem exit_menu_option;
    private javax.swing.JMenuItem import_data_filter_option;
    private javax.swing.JMenuItem import_data_option;
    private javax.swing.JMenuItem import_template_option;
    private javax.swing.JMenuBar menu_bar;
    private javax.swing.JMenuItem modify_data_filter_option;
    private javax.swing.JMenuItem save_data_filter_option;
    private javax.swing.JMenuItem save_template_option;
    private javax.swing.JCheckBox interpolation_check_box;
    private javax.swing.JCheckBox outlier_check_box;
    private javax.swing.JTabbedPane tab_pane;
    private javax.swing.JMenu template_menu;
    // End of variables declaration
    
    // Created by Gerardo A. Navas Morales.
    /**
     * Getter Method. Provides this class' "tab_pane" JTabbedPane object.
     * 
     * @return A JTabbedPane object, "tab_pane".
     */
    public JTabbedPane getTabPane () {
        return (this.tab_pane);
    }
    
 // Data Listener Methods
    /**
     * Creates a new ActionListener for the Import Data menu function.
     * 
     * @param dataImportMenuListener
     *            Action Listener for the Import Data menu function.
     */
    public void addDataImportMenuListener (ActionListener dataImportMenuListener) {
        this.import_data_option.addActionListener (dataImportMenuListener);
        
    }
    
    // Template Listener Methods
    /**
     * Creates a new ActionListener for the Import Template menu function.
     * 
     * @param templateImportMenuListener
     *            Action Listener for the Import Template menu function.
     */
    public void addTemplateImportMenuListener (
            ActionListener templateImportMenuListener) {
        this.import_template_option
                .addActionListener (templateImportMenuListener);
        
    }
    
    /**
     * Creates a new ActionListener for the Save Template menu function.
     * 
     * @param templateSaveMenuListener
     *            Action Listener for the Save Template menu function.
     */
    public void addTemplateSaveMenuListener (
            ActionListener templateSaveMenuListener) {
        this.save_template_option.addActionListener (templateSaveMenuListener);
        
    }
    
    // Data Filter Listener Methods
    /**
     * Creates a new ActionListener for the Import Data Filter menu function.
     * 
     * @param dataFilterImportMenuListener
     *            Action Listener for the Import Data Filter menu function.
     */
    public void addDataFilterImportMenuListener (
            ActionListener dataFilterImportMenuListener) {
        this.import_data_filter_option
                .addActionListener (dataFilterImportMenuListener);
        
    }
    
    /**
     * Creates a new ActionListener for the Edit Data Filter menu function.
     * 
     * @param dataFilterEditMenuListener
     *            Action Listener for the Edit Data Filter menu function.
     */
    public void addDataFilterEditMenuListener (
            ActionListener dataFilterEditMenuListener) {
        this.modify_data_filter_option
                .addActionListener (dataFilterEditMenuListener);
        
    }
    
    /**
     * Creates a new ActionListener for the Save Data Filter menu function.
     * 
     * @param dataFilterSaveMenuListener
     *            Action Listener for the Save Data Filter menu function.
     */
    public void addDataFilterSaveMenuListener (
            ActionListener dataFilterSaveMenuListener) {
        this.save_data_filter_option
                .addActionListener (dataFilterSaveMenuListener);
        
    }
    
    // Graph Listener Methods
    /**
     * Creates a new ActionListener for the Create Graph menu function.
     * 
     * @param graphMenuListener
     *            Action Listener for the Create Graph menu function.
     */
    public void addGraphMenuListener (ActionListener graphMenuListener) {
        this.create_graph_option.addActionListener (graphMenuListener);
    }
    
    // Exit Listener Method
    /**
     * Creates a new ActionListener for the Exit menu function.
     * 
     * @param exitMenuListener
     *            Action Listener for the Exit menu function.
     */
    public void addExitMenuListener (ActionListener exitMenuListener) {
        this.exit_menu_option.addActionListener (exitMenuListener);
    }
    
    // Tab Listener Method
    /**
     * Creates a new ChangeListener for when the Template is updated.
     * 
     * @param tabListener Change Listener for Template updating events.
     */
    public void addTabListener (javax.swing.event.ChangeListener tabListener) {
        this.tab_pane.addChangeListener (tabListener);
    }
}