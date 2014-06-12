/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package HelpManual;

import java.awt.Component;
import java.net.URL;
import javax.help.*;

/**
 * <p>Help Frame loader. Uses Java Help to load a Help Window.
 * 
 * @author Plasma Visualization Group
 */
public class HelpManual {

	private HelpSet hs = null;
	private HelpBroker hb = null;
	private URL hsURL;

	public HelpManual () {
		// Define the location of the JavaHelp HelpSet .HS file
		// File hsFile = new File (".HelpManual/HelpManual.hs");
		this.hsURL = this.getClass ().getResource ("HelpManual.hs");
		try {
			ClassLoader cl = getClass ().getClassLoader ();
			
			// Create HelpSet
			this.hs = new javax.help.HelpSet (cl, this.hsURL);
			
			// Create HelpBroker
			this.hb = hs.createHelpBroker ();
			
		} catch (HelpSetException ex) {
			System.out.println ("Error when creating HelpSet: "
					+ (hsURL.toString ()) + "\n Errror:" + ex);
			
		}
	}

	public void enableOnClick (Component cmp) {
		this.hb.enableHelpOnButton (cmp, "Help", this.hs);
	}
}
