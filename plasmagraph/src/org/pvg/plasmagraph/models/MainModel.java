package org.pvg.plasmagraph.models;

//Class Import Block
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.pvg.plasmagraph.utils.ExceptionHandler;
import org.pvg.plasmagraph.utils.FileUtilities;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.filter.DataFilter;
import org.pvg.plasmagraph.utils.data.filter.DataFilterWindow;
import org.pvg.plasmagraph.utils.data.readers.CSVProcessor;
import org.pvg.plasmagraph.utils.data.readers.MatlabReader;
import org.pvg.plasmagraph.utils.graphs.BarGraph;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.interpolation.Interpolator;
import org.pvg.plasmagraph.utils.tools.outlierscan.OutlierSearch;
import org.pvg.plasmagraph.utils.types.ChartType;

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
    /** Reference to PlasmaGraph's Template, passed via constructor reference. */
    Template t;
    /** Reference to PlasmaGraph's DataSet, passed via constructor reference. */
    HeaderData hd;
    /** Reference to PlasmaGraph's DataFilter, passed via constructor reference. */
    DataFilter df;
    /** Reference to PlasmaGraph's DataReference, passed via constructor reference. */
    DataReference dr;
    
    // Constants
    // TODO: Change to NIO 2.0 Path class!
    String TEMPLATE_EXTENSION = ".tem";
    String DATA_FILTER_EXTENSION = ".df";
    String default_data_path = "./test/csv/";
    String default_template_path = "./test/template/";
    String default_filter_path = "./test/data_filter/";
    
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
    public MainModel (Template t_reference, HeaderData hd_reference,
            DataFilter df_reference, DataReference dr_reference) {
        // Update currently-used Template, Data, and Data Filter Sources.
        t = t_reference;
        hd = hd_reference;
        df = df_reference;
        dr = dr_reference;
        
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
        open_file.addChoosableFileFilter (csv_filter);
        open_file.addChoosableFileFilter (mat_filter);
        // Set the default file filter.
        open_file.setFileFilter (csv_filter);
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
                /*
                 * MatlabReader mat = new MatlabReader (f);
                 * try {
                 * mat.read ();
                 * if (mat.getHeaders (hd)) {
                 * // TODO: Change message to "Data Columns extracted successfully." ?
                 * JOptionPane.showConfirmDialog (null, "Data Column names extracted successfully.");
                 * }
                 * } catch (Exception ex) {
                 * ExceptionHandler.createMalformedDataFileException ("Matlab File Reader");
                 * }
                 */
                
            } else if (FileUtilities.getExtension (f).equals (
                    csv_filter.getExtensions ()[0])) {
                
                CSVProcessor csv = new CSVProcessor (f);
                try {
                	csv.read ();
	                if (csv.getHeaders (hd)) {
	                	JOptionPane.showMessageDialog (null,
	                			"Data Column names extracted successfully.");
	                	hd.notifyListeners ();
	                	// TODO: Allow for multiple data files to be used.
	                	// TODO: Only reset if a data file with a different set of headers is imported.
	                	dr.reset ();
	                }
                } catch (Exception ex) {
                	ExceptionHandler.createMalformedDataFileException ("CSV File Reader");
                }
                
            } else {
                ExceptionHandler
                        .createFunctionNotImplementedException ("Other File Readers");
            }
        } else if (return_value == JFileChooser.ERROR_OPTION) {
            ExceptionHandler.createFileSelectionException ("Importing Data");
            
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
            ExceptionHandler.createFileSelectionException ("Importing Template");
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
            ExceptionHandler.createFileSelectionException ("Saving Template");
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
                    .createFileSelectionException ("Importing Data Filter");
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
            ExceptionHandler.createFileSelectionException ("Saving Data Filter");
        }
        // "return_value == JFileChooser.CANCEL_OPTION" has no response.
    }
    
    /**
     * Helper method that automatically performs some edits to an Open-centric
     * JFileChooser.
     * TODO: Remove this method and incorporate its process to the
     * initialization, or do something else with it!
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
     * initialization, or do something else with it!
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
     * 
     * @param outlier_switch 
     * @param interpolation_switch 
     */
    public void graph (boolean outlier_switch, boolean interpolation_switch) {
    	
    	if (outlier_switch) {
    		log ("Outlier Scanning...");
    		OutlierSearch.scanForOutliers (hd, t, dr);
    	}
    	
    	if (interpolation_switch) {
    		log ("Interpolating.");
			Interpolator.interpolate (hd, t, dr);
    		
    	} else {
    		if (t.getChartType ().equals (ChartType.XY_GRAPH)) {
    			for (GraphPair p : dr) {
    				XYGraph graph = new XYGraph (t, hd, p);
    				
    				graph.pack ();
    				graph.setVisible (true);
    			}
    		} else if (t.getChartType ().equals (ChartType.BAR_GRAPH)) {
    			for (GraphPair p : dr) {
    				BarGraph graph = new BarGraph (t, hd, p);
    				
    				graph.pack ();
    				graph.setVisible (true);
    			}
    		} else {
    			ExceptionHandler.createFunctionNotImplementedException 
    					("Graph - Not XY or Bar Graph");
    		}
    	}
    }

    /**
     * Getter method. Provides the template associated with this model.
     * 
     * @return Reference to the template file linked to this object.
     */
    public Template getTemplate () {
        return (t);
    }
	
	/**
	 * Helper method. Provides easy access to System.out.println ();
	 * 
	 * @param txt Text to print in console.
	 */
	private void log (String txt){
        System.out.println (txt);
    }
}
