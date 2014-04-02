/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvg.plasmagraph.utils;

import javax.help.*;
import java.net.URL;

/**
 *
 * @author Daniel E. Quintin
 */
public class UserManual {
    
    HelpSet hs = null;
    HelpBroker hb = null;
    URL hsURL;
   
   // Initialize the JavaHelp Set
    
    try {
      ClassLoader cl = getClass().getClassLoader();
      // Define the location of the JavaHelp HelpSet .HS file
      hsURL = javax.help.HelpSet.findHelpSet(cl, "./plasmagraph/src/userManual/userManual.hs");
      // Create HelpSet
      hs = new javax.help.HelpSet(null, hsURL);
      // Create HelpBroker
      hb = hs.createHelpBroker();
    } catch(Exception ex) {
        System.out.println( "Error when creating HelpSet: "+hsURL.toString()+"\n Errror:"+ex );
        return;
    }   
}
