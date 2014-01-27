package org.pvg.plasmagraph;

// Class Import Block
import org.pvg.plasmagraph.controllers.*;
import org.pvg.plasmagraph.models.*;
import org.pvg.plasmagraph.utils.data.DataFilter;
import org.pvg.plasmagraph.utils.data.DataSet;
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
		// Create all necessary objects.
		Template t = new Template ();
		DataSet ds = new DataSet ();
		DataFilter df = new DataFilter ();
		
		// Create all MVC components and connect them.
		// Main MVC
		MainModel main_model = new MainModel (t, ds, df);
		MainView main_view = new MainView (main_model, t);
		MainController main_controller = new MainController (main_model, main_view);
		
		// Aesthetic MVC
		AestheticModel aesthetic_model = new AestheticModel (t);
		AestheticView aesthetic_view = new AestheticView (aesthetic_model, t);
		AestheticController aesthetic_controller = new AestheticController (aesthetic_model, aesthetic_view);
		
		// Data Set MVC
		DataSetModel data_model = new DataSetModel (t, ds);
		DataSetView data_view = new DataSetView (data_model, t, ds);
		DataSetController data_controller = new DataSetController (data_model, data_view);
		
		// Tool MVC
		ToolModel tool_model = new ToolModel (t);
		ToolView tool_view = new ToolView (tool_model, t);
		ToolController tool_controller = new ToolController (tool_model, tool_view);
		
		// Set the currently-visible view.
		main_view.setVisible(true);
	}
}