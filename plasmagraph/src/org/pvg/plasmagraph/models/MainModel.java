package org.pvg.plasmagraph.models;

//Class Import Block
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.pvg.plasmagraph.utils.FileUtilities;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.MessageLog;
import org.pvg.plasmagraph.utils.data.readers.MatlabProcessor;
import org.pvg.plasmagraph.utils.exceptions.ExceptionHandler;
import org.pvg.plasmagraph.utils.types.ExceptionType;
import org.pvg.plasmagraph.utils.types.FileType;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.views.DatasetLogView;

/**
 * Primary Model for the PlasmaGraph product.
 * Controls the primary JFrame's (MainView) view.
 * Manages MainView's menu option selections, and
 * deals with the modification of the Template, the DataSet, and DataFilter
 * objects.
 * 
 * @author Plasma Visualization Group
 */
public class MainModel {
    // Externally-controlled variables
    /** Reference to PlasmaGraph's Template, passed via constructor reference. */
	private Template t;
    /** Reference to PlasmaGraph's DataSet, passed via constructor reference. */
	private HeaderData hd;
    /** Reference to PlasmaGraph's GraphPair, passed via constructor reference. */
	private GraphPair p;
	/** Reference to PlasmaGraph's MessageLog, passed via constructor reference. */
    private MessageLog ml;
    
    // Constants
    // TODO: Change to NIO 2.0 Path class!
    String TEMPLATE_EXTENSION = ".tem";
    String DATA_FILTER_EXTENSION = ".df";
    String default_data_path = "./plasmagraph/test/";
    String default_template_path = "./test/template/";
    String default_filter_path = "./test/data_filter/";
    
    /**
     * Creates a new MainModel with references to the data, settings and filter,
     * as well as creates and updates its respective view.
     * 
     * @param t_reference
     *            Settings - Template reference provided by PlasmaGraph.
     * @param hd_reference Header - HeaderData reference provided by PlasmaGraph.
     * @param p_reference Graphing Pairs - GraphPair reference provided by PlasmaGraph.
     * @param ml 
     */
    public MainModel (Template t_reference, HeaderData hd_reference,
            GraphPair p_reference, MessageLog ml_reference) {
        // Update currently-used Template, Data, and Data Filter Sources.
        t = t_reference;
        hd = hd_reference;
        p = p_reference;
        ml = ml_reference;
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
        //FileNameExtensionFilter csv_filter = new FileNameExtensionFilter (
        //        "Comma-Separated Value Files", "csv");
        
        // Insert the FileFilter into the JFileChooser
        //open_file.addChoosableFileFilter (csv_filter);
        open_file.addChoosableFileFilter (mat_filter);
        // Set the default file filter.
        open_file.setFileFilter (mat_filter);
        // Set the current directory
        open_file.setCurrentDirectory (new File (default_data_path));
        
        // Open the Dialog!
        int return_value = open_file.showOpenDialog (null);
        
        // Check to see what the user selected, and act on it!
        if (return_value == JFileChooser.APPROVE_OPTION) {
        	try {
        		
        		 File f = open_file.getSelectedFile ();
                 
                 if (FileUtilities.getExtension (f).equals (
                         mat_filter.getExtensions ()[0])) {
                     
                      MatlabProcessor mat = new MatlabProcessor (f, ml);//, t.isShowingInfoMessages ());
                      try {
                     	 if (mat.getHeaders (hd)) {
                 		 	JOptionPane.showMessageDialog (null, "Data extracted successfully.");
                 		 	
                 		 	// Notify anyone interested in the HeaderData.
                 		 	hd.notifyListeners ();
                 		 	
                 		 	// Update the selected data pair.
                 		 	p.reset ();
                     	 }
                      } catch (Exception ex) {
                    	  System.out.println (ex.fillInStackTrace ());
                     	 ExceptionHandler.handleMalformedDataFileException ("Matlab File Reader");
                      }
                      
                     
                 }/* else if (FileUtilities.getExtension (f).equals (
                         csv_filter.getExtensions ()[0])) {
                     
                     CSVProcessor csv = new CSVProcessor (f);
                     try {
     	                if (csv.getHeaders (hd)) {
     	                	JOptionPane.showMessageDialog (null, "Data extracted successfully.");
     	                	hd.notifyListeners ();
     	                	// TODO: Allow for multiple data files to be used.
     	                	// TODO: Only reset if a data file with a different set of headers is imported.
     	                	p.reset ();
     	                }
                     } catch (Exception ex) {
                     	ExceptionHandler.handleMalformedDataFileException ("CSV File Reader");
                     }
                     
                 }*/ else {
                     ExceptionHandler
                             .handleFunctionNotImplementedException ("Other File Readers");
                 }
        		
        	} catch (NullPointerException ex) {
        		
        		ExceptionHandler.handleNullPointerException (ExceptionType.JFILECHOOSER_SELECTION);
        		
        	}
        } else if (return_value == JFileChooser.ERROR_OPTION) {
            ExceptionHandler.handleNullPointerException (ExceptionType.JFILECHOOSER_SELECTION);
            
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
            ExceptionHandler.handleNullPointerException (ExceptionType.JFILECHOOSER_SELECTION);
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
            ExceptionHandler.handleNullPointerException (ExceptionType.JFILECHOOSER_SELECTION);
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
	@SuppressWarnings ("unused")
	private void log (String txt) {
        System.out.println (txt);
    }

	/**
	 * Resets the HeaderData object.
	 */
	public void resetData () {
		this.hd.reset ();
		this.p.reset ();
	}

    /**
     * Opens the DataLogView in order to present a visual representation of the
     * data currently being accounted for.
     */
    public void prepareDataLog () {
    	StringBuilder sb = new StringBuilder ();
    
		sb.append ("File: ");
		
		org.apache.commons.math3.util.Pair <File, FileType> e = hd.getFile ();
		
		if (FileType.MAT.equals (e.getValue ())) {
			
			MatlabProcessor mat_reader = new MatlabProcessor (e.getKey (), ml);//, t.isShowingInfoMessages ());
			
			sb.append (e.getKey ().getName ()).append ("\n\n");
			sb.append (mat_reader.toString ());
			
		}
    	
        DatasetLogView dsview = new DatasetLogView (sb.toString ());
        dsview.pack ();
        dsview.setVisible (true);
    }
    
    /**
     * Support method to add listeners to the Template.
     * 
     * @param c Listener to add to Template Notifier.
     */
    public void addTemplateChangeListener (javax.swing.event.ChangeListener c) {
        t.addChangeListener (c);
    }
}
