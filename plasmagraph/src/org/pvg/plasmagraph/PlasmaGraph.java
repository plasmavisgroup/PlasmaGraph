package org.pvg.plasmagraph;

// Class Import Block
import javax.swing.SwingUtilities;

import org.pvg.plasmagraph.controllers.*;
import org.pvg.plasmagraph.models.*;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.filter.DataFilter;
import org.pvg.plasmagraph.utils.template.*;
import org.pvg.plasmagraph.views.*;

/**
 * Initiating class for the product.
 * Creates the primary data objects and MVC architecture objects
 * before providing control (i.e. visibility) to MainView.
 * 
 * @author Gerardo A. Navas Morales
 */
public class PlasmaGraph {

	public static void main (String [] args) {
	    SwingUtilities.invokeLater (new Runnable () {
            @Override
            public void run () {
                // Create all necessary objects.
                Template t = new Template ();
                DataSet ds = new DataSet ();
                DataReference dr = new DataReference ();
                DataFilter df = new DataFilter ();
                
                // Create all MVC components and connect them.
                // Aesthetic MVC
                AestheticModel aesthetic_model = new AestheticModel (t);
                AestheticView aesthetic_view = new AestheticView (aesthetic_model);
                AestheticController aesthetic_controller = new AestheticController (aesthetic_model, aesthetic_view);
                
                // Data Set MVC
                DataSetModel data_model = new DataSetModel (t, ds, dr);
                DataSetView data_view = new DataSetView (data_model);
                DataSetController data_controller = new DataSetController (data_model, data_view);
                
                // Tool MVC
                ToolModel tool_model = new ToolModel (t, ds, dr);
                ToolView tool_view = new ToolView (tool_model);
                ToolController tool_controller = new ToolController (tool_model, tool_view);
                
                // Main MVC
                MainModel main_model = new MainModel (t, ds, df);
                MainView main_view = new MainView (main_model);
                MainController main_controller = new MainController (main_model, main_view, aesthetic_view, data_view, tool_view);
            
                // Set the currently-visible view.
                main_view.setVisible(true);
            }
	    });
	}
}
