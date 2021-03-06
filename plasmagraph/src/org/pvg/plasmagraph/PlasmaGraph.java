package org.pvg.plasmagraph;

// Class Import Block
import javax.swing.SwingUtilities;

import org.pvg.plasmagraph.controllers.*;
import org.pvg.plasmagraph.models.*;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.MessageLog;
import org.pvg.plasmagraph.utils.template.*;
import org.pvg.plasmagraph.views.*;

/**
 * <p>Initiating class for the program.
 * <p>Creates the primary data objects and MVC architecture objects before
 * providing control (i.e. visibility) to MainView.
 * 
 * @author Plasma Visualization Group
 */
public class PlasmaGraph {

	@SuppressWarnings ("javadoc")
	public static void main (String [] args) {
	    SwingUtilities.invokeLater (new Runnable () {
	    	
            @SuppressWarnings ("unused")
			@Override
            public void run () {
                // Create all necessary objects.
                Template t = new Template ();
                GraphPair p = new GraphPair ();
                MessageLog ml = new MessageLog ();
                HeaderData hd = new HeaderData (ml);
                
                // MessageLog MVC
                MessageLogView ml_view = new MessageLogView ();
                MessageLogController ml_controller = new MessageLogController (ml_view);
                
                // Graph MVC
                GraphModel graph_model = new GraphModel (hd, p, t, ml, ml_controller);
                GraphView graph_view = new GraphView (graph_model);
				GraphController graph_controller = new GraphController (graph_model, graph_view);
                      
                // Data Set MVC
                DataSetModel data_model = new DataSetModel (t, hd, p, ml);
                DataSetView data_view = new DataSetView (data_model);
                DataSetController data_controller = new DataSetController (data_model, data_view, graph_controller);
                
                // Tool MVC
                ToolModel tool_model = new ToolModel (t, hd, p, ml);
                ToolView tool_view = new ToolView (tool_model);
                ToolController tool_controller = new ToolController (tool_model, tool_view, graph_controller);
                
                // Main MVC
                MainModel main_model = new MainModel (t, hd, p, ml);
                MainView main_view = new MainView (main_model);
                MainController main_controller = new MainController (main_model, main_view, data_view, tool_view);
            
                // Set the currently-visible views.
                main_view.setVisible (true);
                graph_view.setVisible (true);
                ml_view.setVisible (true);
                
                // Start off by running the Import Data function!
                main_model.importData (); 
                
            }
	    });
	    
	}
}
