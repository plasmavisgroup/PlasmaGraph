/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmagraph.tests;

/**
 *
 * @author tako
 */
public class DumbTest {
 
    public static void main (String [] args) {
        String s = "4.75E-14";
        System.out.println ("Java - parseDouble test.");
        System.out.println ("I'm going to try to parse the number " + s + " into a Double via parseDouble.");
        System.out.println ("Let's see if this works...");
        try {
            double num = Double.parseDouble(s);
            System.out.println ("The number we've got is: " + num + ".");
        } catch (NumberFormatException ex) {
            System.out.println ("Nope, doesn't work!");
        }
    }
}