package org.pvg.plasmagraph.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pvg.plasmagraph.models.MainModel;
import org.pvg.plasmagraph.utils.exceptions.TemplateGroupByColumnNotFoundException;
import org.pvg.plasmagraph.views.AestheticView;
import org.pvg.plasmagraph.views.DataSetView;
import org.pvg.plasmagraph.views.MainView;
import org.pvg.plasmagraph.views.ToolView;

/**
 * Controller for the Main MVC.
 * Manages messages being sent around the program from the MainView.
 * 
 * @author Gerardo A. Navas Morales
 */
public class MainController {
    /** Reference to model related to this controller. */
    MainModel main_model;
    /** Reference to view related to this controller. */
    MainView main_view;
    /** Reference to AestheticView included in this Frame's JTabbedFrame. */
    AestheticView aesthetic_view;
    /** Reference to DataSetView included in this Frame's JTabbedFrame. */
    DataSetView data_view;
    /** Reference to ToolView included in this Frame's JTabbedFrame. */
    ToolView tool_view;
    
    /**
     * 
     * @param main_model
     * @param main_view
     * @param aesthetic_view_reference
     * @param data_view_reference
     * @param tool_view_reference
     */
    public MainController (MainModel main_model, MainView main_view, 
                    AestheticView aesthetic_view_reference, DataSetView data_view_reference,
                    ToolView tool_view_reference) {
        // Set related objects into proper positions in object.
        this.main_model = main_model;
        this.main_view = main_view;
        aesthetic_view = aesthetic_view_reference;
        data_view = data_view_reference;
        tool_view = tool_view_reference;
        
        // Automatically add listeners to Main Panel via view.
        // Data
        main_view.addDataImportMenuListener (new DataImportMenuListener ());
        // Template
        main_view.addTemplateImportMenuListener (new TemplateImportMenuListener ());
        main_view.addTemplateSaveMenuListener (new TemplateSaveMenuListener ());
        // Data Filter
        main_view.addDataFilterImportMenuListener (new DataFilterImportMenuListener ());
        main_view.addDataFilterEditMenuListener (new DataFilterEditMenuListener ());
        main_view.addDataFilterSaveMenuListener (new DataFilterSaveMenuListener ());
        // Graph
        main_view.addGraphMenuListener (new GraphMenuListener ());
        // Exit
        main_view.addExitMenuListener (new ExitMenuListener ());
        // Tabs
        main_view.addTabListener (new TabListener ());
        // Finish up setup by preparing tabs.
        setUpTabs ();
    }
    
    public void setUpTabs () {
        // Adding Tabs to JTabbedPane
        main_view.getTabPane ().addTab (aesthetic_view.getName (), aesthetic_view);
        main_view.getTabPane ().addTab (data_view.getName (), data_view);
        main_view.getTabPane ().addTab (tool_view.getName (), tool_view);
        
        // Adding effects to JTabbedPane
        main_view.getTabPane ().setTabLayoutPolicy (JTabbedPane.SCROLL_TAB_LAYOUT);
        main_view.getTabPane ().setMnemonicAt (0, java.awt.event.KeyEvent.VK_A);
        main_view.getTabPane ().setMnemonicAt (1, java.awt.event.KeyEvent.VK_D);
        main_view.getTabPane ().setMnemonicAt (2, java.awt.event.KeyEvent.VK_T);
        
    }
    
    // Data Action Listener Inner Classes
    class DataImportMenuListener implements ActionListener {
        
        @Override
        public void actionPerformed (ActionEvent arg0) {
            SwingWorker <Void, Void> data_worker = new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    main_model.importData ();
                    return null;
                }
                
            };
            
            data_worker.run ();
            
        }
        
    }
    
    // Template Action Listener Inner Classes
    class TemplateImportMenuListener implements ActionListener {
        
        @Override
        public void actionPerformed (ActionEvent arg0) {
            SwingWorker <Void, Void> template_import_worker = 
                    new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    main_model.importTemplate ();
                    return null;
                }
                
            };
            
            template_import_worker.run ();
        }
        
    }
    
    class TemplateSaveMenuListener implements ActionListener {
        
        @Override
        public void actionPerformed (ActionEvent arg0) {
            SwingWorker <Void, Void> template_save_worker = 
                    new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    main_model.saveTemplate ();
                    return null;
                }
                
            };
            
            template_save_worker.run ();
        }
        
    }
    
    // Data Filter Action Listener Inner Classes
    class DataFilterImportMenuListener implements ActionListener {
        
        @Override
        public void actionPerformed (ActionEvent arg0) {
            SwingWorker <Void, Void> data_filter_import_worker = 
                    new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    main_model.importDataFilter ();
                    return null;
                }
                
            };
            
            data_filter_import_worker.run ();
        }
        
    }
    
    class DataFilterEditMenuListener implements ActionListener {
        
        @Override
        public void actionPerformed (ActionEvent arg0) {
            SwingWorker <Void, Void> data_filter_edit_worker = 
                    new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    main_model.editDataFilter ();
                    return null;
                }
                
            };
            
            data_filter_edit_worker.run ();
        }
        
    }
    
    class DataFilterSaveMenuListener implements ActionListener {
        
        @Override
        public void actionPerformed (ActionEvent arg0) {
            SwingWorker <Void, Void> data_filter_save_worker = 
                    new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    main_model.saveDataFilter ();
                    return null;
                }
                
            };
            
            data_filter_save_worker.run ();
        }
        
    }
    
    // Graph Action Listener Inner Class
    class GraphMenuListener implements ActionListener {
        
        @Override
        public void actionPerformed (ActionEvent arg0) {
            SwingWorker <Void, Void> graph_worker = 
                    new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    main_model.graph (main_view.getOutlierSwitch (),
                    		main_view.getInterpolationSwitch ());
                    return null;
                }
                
            };
            
            graph_worker.run ();
        }
        
    }
    
    // Exit Action Listener Inner Class
    class ExitMenuListener implements ActionListener {
        
        @Override
        public void actionPerformed (ActionEvent arg0) {
            SwingWorker <Void, Void> exit_worker = 
                    new SwingWorker <Void, Void> () {

                @Override
                protected Void doInBackground () throws Exception {
                    main_model.exit ();
                    return null;
                }
                
            };
            
            exit_worker.run ();
        }
        
    }
    
    // Tab Listener Inner Class
    class TabListener implements ChangeListener {
        
        @Override
        public void stateChanged (ChangeEvent arg0) {
        	try {
	            if (arg0.getSource () instanceof JTabbedPane) {
	                switch (((JTabbedPane) arg0.getSource ()).getSelectedComponent ().getName ()) {
	                    case "Aesthetic View": //$NON-NLS-1$
	                        aesthetic_view.updateView ();
	                        break;
	                    case "Data Set View": //$NON-NLS-1$
							data_view.updateView ();
	                        break;
	                    case "Tool View": //$NON-NLS-1$
	                        tool_view.updateView ();
	                        break;
	                    default:
	                        break;
	                }
	            }
        	} catch (TemplateGroupByColumnNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
}