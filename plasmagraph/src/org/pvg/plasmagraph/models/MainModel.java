package org.pvg.plasmagraph.models;

//Class Import Block
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.pvg.plasmagraph.utils.ExceptionHandler;
import org.pvg.plasmagraph.utils.FileUtilities;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.filter.DataFilter;
import org.pvg.plasmagraph.utils.data.filter.DataFilterWindow;
import org.pvg.plasmagraph.utils.template.Template;

/**
 * Primary Model for the PlasmaGraph product.
 * Controls the primary JFrame's (MainView) view.
 * Manages MainView's menu option selections, and
 * deals with the modification of the Template, the DataSet, and DataFilter
 * objects.
 * 
 * @author Gerardo A. Navas Morales
 */
public class MainModel {
    // Externally-controlled variables
    /** Reference to MainModel's Template, passed via constructor reference. */
    Template t;
    /** Reference to MainModel's DataSet, passed via constructor reference. */
    DataSet ds;
    /** Reference to MainModel's Template, passed via constructor reference. */
    DataFilter df;
    
    // Constants
    String TEMPLATE_EXTENSION = ".tem";
    String DATA_FILTER_EXTENSION = ".df";
    String default_data_path = "../test/data/";
    String default_template_path = "../test/template/";
    String default_filter_path = "../test/data_filter/";
    
    // Internally-controlled variables
    // /** Open File selection dialog. Disabled in favor of on-demand
    // JFileChooser creation. */
    // JFileChooser open_file_dialog;
    // /** Save File selection dialog. Disabled in favor of on-demand
    // JFileChooser creation. */
    // JFileChooser save_file_dialog;
    
    /**
     * Creates a new MainModel with references to the data, settings and filter,
     * as well as creates and updates its respective view.
     * 
     * @param t_reference
     *            Settings - Template reference provided by PlasmaGraph.
     * @param ds_reference
     *            Data - DataSet reference provided by PlasmaGraph.
     * @param df_reference
     *            Filter - DataFilter reference provided by PlasmaGraph.
     */
    public MainModel (Template t_reference, DataSet ds_reference,
            DataFilter df_reference) {
        // Update currently-used Template, Data, and Data Filter Sources.
        t = t_reference;
        ds = ds_reference;
        df = df_reference;
        
        // Prepare FileChooser.
        
    }
    
    /**
     * Imports a new Data file to the reference variable this Model contains.
     * Allows the user to specify the location wherein it will be loaded from.
     */
    public void importData () {
        // Prepare the JFileChooser for use.
        JFileChooser open_file = new JFileChooser ();
        createOpenFileChooser (open_file);
        
        // Prepare the FileFilter
        FileNameExtensionFilter mat_filter = new FileNameExtensionFilter (
                "Matlab Files", "mat");
        // TODO: Decide if you want to keep "supporting" CSV files.
        FileNameExtensionFilter csv_filter = new FileNameExtensionFilter (
                "Comma-Separated Value Files", "csv");
        
        // Insert the FileFilter into the JFileChooser
        open_file.addChoosableFileFilter (mat_filter);
        // TODO: Decide if you want to keep "supporting" CSV files.
        open_file.addChoosableFileFilter (csv_filter);
        // Set the default file filter.
        open_file.setFileFilter (mat_filter);
        // Set the current directory
        open_file.setCurrentDirectory (new File (default_data_path));
        
        // Open the Dialog!
        int return_value = open_file.showOpenDialog (null);
        
        // Check to see what the user selected, and act on it!
        if (return_value == JFileChooser.APPROVE_OPTION) {
            File f = open_file.getSelectedFile ();
            
            if (FileUtilities.getExtension (f).equals (
                    mat_filter.getExtensions ()[0])) {
                
                // TODO: Implement Data Reading for MATLAB Files.
                ExceptionHandler
                        .createFunctionNotImplementedException ("MATLAB File Reader");
                
            } else if (FileUtilities.getExtension (f).equals (
                    csv_filter.getExtensions ()[0])) {
                
                // TODO: Decide if you want to keep "supporting" CSV files.
                // TODO: Implement Data Reading for CSV Files.
                ExceptionHandler
                        .createFunctionNotImplementedException ("CSV File Reader");
                
            } else {
                ExceptionHandler
                        .createFunctionNotImplementedException ("Other File Readers");
            }
        } else if (return_value == JFileChooser.ERROR_OPTION) {
            ExceptionHandler.createFileSelectionException ("Import Data");
            
        }
        // "return_value == JFileChooser.CANCEL_OPTION" has no response.
    }
    
    /**
     * Imports a new Template file to the reference variable this Model
     * contains.
     * Allows the user to specify the location wherein it will be loaded from.
     */
    public void importTemplate () {
        // Prepare the JFileChooser for use.
        JFileChooser open_file = new JFileChooser ();
        createOpenFileChooser (open_file);
        
        // Prepare the FileFilter
        FileNameExtensionFilter template_filter = new FileNameExtensionFilter (
                "Template Files", "tem");
        
        // Insert the FileFilter into the JFileChooser
        open_file.addChoosableFileFilter (template_filter);
        // Set the default file filter.
        open_file.setFileFilter (template_filter);
        // Set the current directory
        open_file.setCurrentDirectory (new File (default_template_path));
        
        // Open the dialog!
        int return_value = open_file.showOpenDialog (null);
        
        // Check to see what the user selected, and act on it!
        if (return_value == JFileChooser.APPROVE_OPTION) {
            File f = open_file.getSelectedFile ();
            
            // Dialog: Ask if overwrite data?
            if (JOptionPane.showConfirmDialog (null,
                    "Do you wish to overwrite the current settings?",
                    "Overwrite Template", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                
                // Read the data.
                t.openTemplate (f);
                
            } else {
                JOptionPane.showMessageDialog (null,
                        "The current settings will not be overwritten.",
                        "Overwrite Template - Stopped",
                        JOptionPane.PLAIN_MESSAGE);
            }
        } else if (return_value == JFileChooser.ERROR_OPTION) {
            ExceptionHandler.createFileSelectionException ("Template");
        }
        // "return_value == JFileChooser.CANCEL_OPTION" has no response.
    }
    
    /**
     * Saves the current status of the Template.
     * Allows the user to specify the location wherein it will be saved.
     */
    public void saveTemplate () {
        // Prepare the JFileChooser for use.
        JFileChooser save_file = new JFileChooser ();
        createSaveFileChooser (save_file);
        
        // Prepare the FileFilter
        FileNameExtensionFilter template_filter = new FileNameExtensionFilter (
                "Template Files", "tem");
        
        // Insert the FileFilter into the JFileChooser
        save_file.addChoosableFileFilter (template_filter);
        // Set the default file filter.
        save_file.setFileFilter (template_filter);
        // Set the current directory
        save_file.setCurrentDirectory (new File (default_template_path));
      
        // Open the dialog!
        int return_value = save_file.showSaveDialog (null);
        
        // Check to see what the user selected, and act on it!
        if (return_value == JFileChooser.APPROVE_OPTION) {
            
            // Save the data.
            t.saveTemplate (save_file);

        } else if (return_value == JFileChooser.ERROR_OPTION) {
            ExceptionHandler.createFileSelectionException ("Template");
        }
        // "return_value == JFileChooser.CANCEL_OPTION" has no response.
    }
    
    /**
     * Imports a new DataFilter file to the reference variable this Model
     * contains.
     * Allows the user to specify the location wherein it will be loaded from.
     */
    public void importDataFilter () {
        // Prepare the JFileChooser for use.
        JFileChooser open_file = new JFileChooser ();
        createOpenFileChooser (open_file);
        
        // Prepare the FileFilter
        FileNameExtensionFilter data_filter = new FileNameExtensionFilter (
                "Data Filter Files", "daf");
        
        // Insert the FileFilter into the JFileChooser
        open_file.addChoosableFileFilter (data_filter);
        // Set the default file filter.
        open_file.setFileFilter (data_filter);
        // Set the current directory
        open_file.setCurrentDirectory (new File (default_filter_path));
        
        // Open the dialog!
        int return_value = open_file.showOpenDialog (null);
        
        // Check to see what the user selected, and act on it!
        if (return_value == JFileChooser.APPROVE_OPTION) {

            // Dialog: Ask if overwrite data?
            if (JOptionPane.showConfirmDialog (null,
                    "Do you wish to overwrite the current data filter?",
                    "Overwrite Data Filter", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                
                // Read the data.
                df = new DataFilter (open_file);
                
            } else {
                JOptionPane.showMessageDialog (null,
                        "The current data filter will not be overwritten.",
                        "Overwrite Data Filter - Stopped",
                        JOptionPane.PLAIN_MESSAGE);
            }
        } else if (return_value == JFileChooser.ERROR_OPTION) {
            ExceptionHandler
                    .createFileSelectionException ("Import Data Filter");
        }
        // "return_value == JFileChooser.CANCEL_OPTION" has no response.
    }
    
    /**
     * Initializes the DataFilterWindow JFrame, and sets it visibility to true.
     * WARNING: This is not a modal window, which means that there can be issues
     * with
     * how data is processed. There is NO EXPECTATION OF RETROACTIVE CHANGES;
     * THE USER
     * MUST REQUEST FOR A NEW IMPORT BEFORE BEING ABLE TO USE THE NEW FILTERS.
     */
    public void editDataFilter () {
        // Initialize the DataFilterWindow, and get it running!
        DataFilterWindow dfw = new DataFilterWindow (df);
        dfw.setVisible (true);
        // dfw.setAlwaysOnTop(true);
    }
    
    /**
     * Saves the current status of the DataFilter.
     * Allows the user to specify the location wherein it will be saved.
     */
    public void saveDataFilter () {
        // Prepare the JFileChooser for use.
        JFileChooser save_file = new JFileChooser ();
        createOpenFileChooser (save_file);
        
        // Prepare the FileFilter
        FileNameExtensionFilter data_filter = new FileNameExtensionFilter (
                "Data Filter Files", "daf");
        
        // Insert the FileFilter into the JFileChooser
        save_file.addChoosableFileFilter (data_filter);
        // Set the default file filter.
        save_file.setFileFilter (data_filter);
        // Set the current directory
        save_file.setCurrentDirectory (new File (default_filter_path));
        
        int return_value = save_file.showSaveDialog (null);
        
        // Check to see what the user selected, and act on it!
        if (return_value == JFileChooser.APPROVE_OPTION) {
            
            // Save the data.
            df.save (save_file);
                
        } else if (return_value == JFileChooser.ERROR_OPTION) {
            ExceptionHandler.createFileSelectionException ("Save Data Filter");
        }
        // "return_value == JFileChooser.CANCEL_OPTION" has no response.
    }
    
    /**
     * Helper method that automatically performs some edits to an Open-centric
     * JFileChooser.
     * TODO: Remove this method and incorporate its process to the
     * initialization, or
     * do something else with it!
     * 
     * @param open_file
     *            JFileChooser object to edit.
     * @return An edited JFileChooser object.
     */
    private JFileChooser createOpenFileChooser (JFileChooser open_file) {
        // File Chooser must select any kind of file or directory.
        open_file.setFileSelectionMode (JFileChooser.FILES_AND_DIRECTORIES);
        // It can only select one file.
        open_file.setMultiSelectionEnabled (false);
        
        return (open_file);
    }
    
    /**
     * Helper method that automatically performs some edits to an Save-centric
     * JFileChooser.
     * TODO: Remove this method and incorporate its process to the
     * initialization, or
     * do something else with it!
     * 
     * @param save_file
     *            JFileChooser object to edit.
     * @return An edited JFileChooser object.
     */
    private JFileChooser createSaveFileChooser (JFileChooser save_file) {
        // File Chooser must select any kind of file or directory.
        save_file.setFileSelectionMode (JFileChooser.FILES_ONLY);
        // It can only select one file.
        save_file.setMultiSelectionEnabled (false);
        // Never show full file path.
        save_file.setFileHidingEnabled (true);
        
        return (save_file);
    }
    
    /**
     * Asks the user if they want to store their changes one last time before leaving!
     */
    public void exit () {
        // Ask if user wants to save DataFilter
        if (JOptionPane.showConfirmDialog (null,
                "Do you want to save your Data Filter before exiting PlasmaGraph?",
                "Save Data Filter?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.saveDataFilter ();
        }
        
        // Ask if user wants to save Template
        if (JOptionPane.showConfirmDialog (null,
                        "Do you want to save your Template before exiting PlasmaGraph?",
                        "Save Template?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.saveTemplate ();
        }
        
        // Close.
        // This probably isn't the best way to do it, though.
        System.exit (0);
    }
    
    /**
     * Graphs the columns specified in DataReference dr with 
     * data in DataSet ds according to the settings in Template t.
     * Uses JFreeChart to create the appropriate graph!
     */
    public void graph () {
        // TODO Auto-generated method stub
        JOptionPane.showMessageDialog (null, 
                "Bang! (Sorry, we don't have anything here yet!)", 
                "Unimplemented Function Error!", JOptionPane.PLAIN_MESSAGE);
        
    }

    public Template getTemplate () {
        return (t);
    }
    
}
