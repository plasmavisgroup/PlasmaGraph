package org.pvg.plasmagraph.views;

import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import org.pvg.plasmagraph.models.MainModel;

/**
 * <p>Graphical User Interface class designed to present the organization of the Settings Window, including its Menu Bar.
 * 
 * @author Plasma Visualization Group
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
     * Updates the view based on the MainModel's template object reference.
     */
    /*public void updateView () {
		this.setInfoMessageCheckBox (this.main_model.getTemplate ().isShowingInfoMessages ());
	}
    
    *//**
     * <p>Allows the MainController to change the current setting of the CheckBox if needed.
     * 
     * @param b Boolean defining the state that the CheckBox must be in.
     *//*
    private void setInfoMessageCheckBox (boolean b) {
		this.ignore_popups_check_box.setSelected (b);
	}*/
     
    /**
     * Initializes the visual components of this view form.
     */
    private void initComponents () {
        tab_pane = new javax.swing.JTabbedPane ();
        menu_bar = new javax.swing.JMenuBar ();
        data_menu = new javax.swing.JMenu ();
        import_data_option = new javax.swing.JMenuItem ();
        //reset_data_option = new javax.swing.JMenuItem ();
        matfile_log = new javax.swing.JMenuItem();
        template_menu = new javax.swing.JMenu ();
        import_template_option = new javax.swing.JMenuItem ();
        save_template_option = new javax.swing.JMenuItem ();
        help_menu = new javax.swing.JMenu ();
        //help_menu_about = new javax.swing.JMenuItem();
        help_menu_manual = new javax.swing.JMenuItem();
        options_menu = new javax.swing.JMenu ();
        //ignore_popups_check_box = new javax.swing.JCheckBoxMenuItem ();
        exit_menu_option = new javax.swing.JMenuItem ();
        
        // Set default names and operation responses.
        setTitle ("PlasmaGraph");
        setName ("PlasmaGraph");
        setDefaultCloseOperation (javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        // ====================================================================== //
        // Data Menu
        data_menu.setText ("Data");
        data_menu.setToolTipText ("Manages all Data-related operations.");
        
        // Import Data Option
        import_data_option.setAccelerator (javax.swing.KeyStroke.getKeyStroke (
                java.awt.event.KeyEvent.VK_D,
                java.awt.event.InputEvent.CTRL_MASK));
        import_data_option.setText ("Import Data");
        data_menu.add (import_data_option);
        
        // View Data Option
        matfile_log.setText ("View data");
        data_menu.add (matfile_log);
        
        menu_bar.add (data_menu);
        
        // ====================================================================== //
        
        // Template Menu
        template_menu.setText ("Templates"); 
        template_menu.setToolTipText ("Manages all Template-related operations."); 
        
        // Import Template Option
        import_template_option.setAccelerator (javax.swing.KeyStroke
                .getKeyStroke (java.awt.event.KeyEvent.VK_T,
                        java.awt.event.InputEvent.CTRL_MASK));
        import_template_option.setText ("Import Template"); 
        template_menu.add (import_template_option);
        
        // Save Template Option
        save_template_option.setAccelerator (javax.swing.KeyStroke
                .getKeyStroke (java.awt.event.KeyEvent.VK_S,
                        java.awt.event.InputEvent.CTRL_MASK));
        save_template_option.setText ("Save Template"); 
        template_menu.add (save_template_option);
        
        menu_bar.add (template_menu);
        
        // ====================================================================== //
        
        // Help Menu
        help_menu.setText ("Help"); //$NON-NLS-1$
        help_menu_manual.setText ("User Guide");
        help_menu.setToolTipText ("Shows information about PlasmaGraph."); //$NON-NLS-1$
        help_menu.add (help_menu_manual);
        
        menu_bar.add (help_menu);
        
        // ====================================================================== //
        
        // Options Menu
        options_menu.setText ("Options");
        options_menu.setToolTipText ("Manages program-related operations and settings.");
        
        // Ignore Popups Menu Option
        /*ignore_popups_check_box.setText ("Show Informational Messages?");
        ignore_popups_check_box.setToolTipText ("Controls whether the user will see informative popup messages. Does not control error messages.");
        this.updateView ();
        options_menu.add (ignore_popups_check_box);*/
        
        // Exit Menu Option
        exit_menu_option.setText ("Close");
        exit_menu_option.setToolTipText ("Closes the program.");
        options_menu.add (exit_menu_option);
        
        menu_bar.add (options_menu);
        
        // ====================================================================== //
        
        // Prepare JMenuBar
        setJMenuBar (menu_bar);
        
        // Editing overall layout.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout (
                getContentPane ());
        getContentPane ().setLayout (layout);
        layout.setHorizontalGroup (layout.createParallelGroup (
                javax.swing.GroupLayout.Alignment.LEADING).addComponent (
                tab_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 336,
                Short.MAX_VALUE));
        layout.setVerticalGroup (layout.createParallelGroup (
                javax.swing.GroupLayout.Alignment.LEADING).addComponent (
                tab_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 665,
                Short.MAX_VALUE));
        pack ();
    }

    // Variables declaration - do not modify
    private javax.swing.JTabbedPane tab_pane;
    
    private javax.swing.JMenuBar menu_bar;
    
    private javax.swing.JMenu data_menu;
    private javax.swing.JMenuItem import_data_option;
    private javax.swing.JMenuItem matfile_log;

    private javax.swing.JMenu template_menu;
    private javax.swing.JMenuItem import_template_option;
    private javax.swing.JMenuItem save_template_option;
    
    private javax.swing.JMenu help_menu;
    //private javax.swing.JMenuItem help_menu_about;
    private javax.swing.JMenuItem help_menu_manual; 
    
    private javax.swing.JMenu options_menu;
    //private javax.swing.JCheckBoxMenuItem ignore_popups_check_box;
    private javax.swing.JMenuItem exit_menu_option;
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

    /**
     * Creates a new ActionListener for the View Data menu function.
     * 
     * @param viewDataMenuListener Action Listener for the View Data menu function.
     */
    public void addViewDataMenuListener(ActionListener viewDataMenuListener) {
        this.matfile_log.addActionListener (viewDataMenuListener);
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
    
    // Help Menu Methods
    /**
     * Getter Method. Provides this class' "menu_bar" JMenuBar object.
     * @return JMenuBar "menu_bar"
     */
    public javax.swing.JMenuBar pvgGetMenuBar(){
        return this.menu_bar;
    }
   
    /**
     * Getter Method. Provides this class' "help_menu" JMenu object.
     * @return JMenu "help_menu"
     */
    public javax.swing.JMenu getHelpMenu(){
        return this.help_menu;
    }
    
    // Options Listener Methods
    /**
     * Creates a new ActionListener for the Exit menu function.
     * 
     * @param exitMenuListener
     *            Action Listener for the Exit menu function.
     */
    /*public void addShowInfoMessagesListener (ItemListener infoMessagesListener) {
        this.ignore_popups_check_box.addItemListener (infoMessagesListener);
    }*/
    
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