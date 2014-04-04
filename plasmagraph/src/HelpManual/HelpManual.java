/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package HelpManual;

import java.awt.Component;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.help.*;

/**
 *
 * @author Daniel E. Quintin
 */
public class HelpManual {
    
    private HelpSet hs = null;
    private HelpBroker hb = null;
    private URL hsURL;    
    
    public HelpManual(){
        File hsFile = new File("src/HelpManual/HelpManual.hs");
        try{
            ClassLoader cl = getClass().getClassLoader();
            // Define the location of the JavaHelp HelpSet .HS file
            //this.hsURL = javax.help.HelpSet.findHelpSet(cl, hsURI);
            this.hsURL = hsFile.toURI().toURL();
            // Create HelpSet
            this.hs = new javax.help.HelpSet(cl, this.hsURL);
            // Create HelpBroker
            this.hb = hs.createHelpBroker();
        } catch(MalformedURLException | HelpSetException ex) {
            System.out.println( "Error when creating HelpSet: "+(hsFile.toURI().toString())+"\n Errror:"+ex );
        }
    }
    
    public void enableOnClick(Component cmp){
        this.hb.enableHelpOnButton(cmp, "Help", this.hs);
    }
}
