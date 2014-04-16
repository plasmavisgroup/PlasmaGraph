package org.pvg.plasmagraph.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pvg.plasmagraph.models.MainModel;
import org.pvg.plasmagraph.utils.exceptions.TemplateGroupByColumnNotFoundException;
//import org.pvg.plasmagraph.views.AestheticView;
import org.pvg.plasmagraph.views.DataSetView;
import org.pvg.plasmagraph.views.MainView;
import org.pvg.plasmagraph.views.ToolView;

import HelpManual.HelpManual;

/**
 * Controller for the Main MVC. Manages messages being sent around the program
 * from the MainView.
 * 
 * @author Gerardo A. Navas Morales
 */
public class MainController {
	/** Reference to model related to this controller. */
	MainModel main_model;
	/** Reference to view related to this controller. */
	MainView main_view;
	/** Reference to AestheticView included in this Frame's JTabbedFrame. */
	//AestheticView aesthetic_view;
	/** Reference to DataSetView included in this Frame's JTabbedFrame. */
	DataSetView data_view;
	/** Reference to ToolView included in this Frame's JTabbedFrame. */
	ToolView tool_view;

	/**
	 * Creates and manages the connections in the main JFrame.
	 * 
	 * @param main_model
	 *            Reference to the MainModel object.
	 * @param main_view
	 *            Reference to the MainView object.
	 * @param data_view_reference
	 *            Reference to the DataView object.
	 * @param tool_view_reference
	 *            Reference to the ToolView object.
	 */
	public MainController (MainModel main_model, MainView main_view,
			DataSetView data_view_reference, ToolView tool_view_reference) {
		// Set related objects into proper positions in object.
		this.main_model = main_model;
		this.main_view = main_view;
		data_view = data_view_reference;
		tool_view = tool_view_reference;

		// Automatically add listeners to Main Panel via view.
		// Data
		main_view.addDataImportMenuListener (new DataImportMenuListener ());
		//main_view.addDataResetMenuListener (new DataResetMenuListener ());
		main_view.addViewDataMenuListener (new ViewDataMenuListener ());
		// Template
		main_view.addTemplateImportMenuListener (new TemplateImportMenuListener ());
		main_view.addTemplateSaveMenuListener (new TemplateSaveMenuListener ());
		// Help
        this.addHelpMenuListener();
		// Exit
		main_view.addExitMenuListener (new ExitMenuListener ());
		// Tabs
		//main_view.addTabListener (new TabListener ());
		// Finish up setup by preparing tabs.
		setUpTabs ();
	}

	/**
	 * Sets up the tabs that will be viewed in the JTabbedPane, and configuring
	 * other related JTabbedPane settings.
	 */
	public void setUpTabs () {
		// Adding Tabs to JTabbedPane
		main_view.getTabPane ().addTab (data_view.getName (), data_view);
		main_view.getTabPane ().addTab (tool_view.getName (), tool_view);

		// Adding effects to JTabbedPane
		main_view.getTabPane ().setTabLayoutPolicy (
				JTabbedPane.SCROLL_TAB_LAYOUT);
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
/*
	class DataResetMenuListener implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent arg0) {
			SwingWorker <Void, Void> data_worker = new SwingWorker <Void, Void> () {

				@Override
				protected Void doInBackground () throws Exception {
					main_model.resetData ();
					data_view.updateXAxisColumn ();
					data_view.updateYAxisColumn ();
					data_view.updateGroupBy ();
					return null;
				}

			};

			data_worker.run ();

		}

	}*/

	class ViewDataMenuListener implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent arg0) {
			SwingWorker <Void, Void> data_worker = new SwingWorker <Void, Void> () {

				@Override
				protected Void doInBackground () throws Exception {
					main_model.prepareDataLog ();
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
			SwingWorker <Void, Void> template_import_worker = new SwingWorker <Void, Void> () {

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
			SwingWorker <Void, Void> template_save_worker = new SwingWorker <Void, Void> () {

				@Override
				protected Void doInBackground () throws Exception {
					main_model.saveTemplate ();
					return null;
				}

			};

			template_save_worker.run ();
		}

	}

	// Graph Action Listener Inner Class
	// TODO

	// Exit Action Listener Inner Class
	class ExitMenuListener implements ActionListener {

		@Override
		public void actionPerformed (ActionEvent arg0) {
			SwingWorker <Void, Void> exit_worker = new SwingWorker <Void, Void> () {

				@Override
				protected Void doInBackground () throws Exception {
					main_model.exit ();
					return null;
				}

			};

			exit_worker.run ();
		}

	}
	
	class WindowExitListener extends WindowAdapter {

		@Override
		public void windowClosing (WindowEvent arg0) {
			SwingWorker <Void, Void> exit_worker = new SwingWorker <Void, Void> () {

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
					switch ( ((JTabbedPane) arg0.getSource ())
							.getSelectedComponent ().getName ()) {
					case "Data View":
						data_view.updateView ();
						break;
					case "Options View":
						tool_view.updateView ();
						break;
					}
				}
			} catch (TemplateGroupByColumnNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
		}
	}
	
	 /**
     * Displays a help manual when the main menu option "Help >> User Guide" is clicked on.
     */
    public void addHelpMenuListener (){
        HelpManual help = new HelpManual ();
        help.enableOnClick (main_view.getHelpMenu ().getItem (0));
    }

}