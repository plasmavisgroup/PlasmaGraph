package org.pvg.plasmagraph.utils.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;

// TODO: Add more JavaDoc comments!
public class DataFilter implements Iterator <String>, Iterable <String> {
    ArrayList <String> filter;
    
    public DataFilter () {
        filter = new ArrayList <String> ();
    }
    
    public DataFilter (JFileChooser f) {
        filter = new ArrayList <String> ();
        
        try (BufferedReader in = new BufferedReader (
                new FileReader (f.getSelectedFile ()))) {

            String s = in.readLine ();
            while (s != null && s != "") {
                filter.add (s);
                s = in.readLine ();
            }
            
            System.out.println (filter.toString ());
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println ("FileNotFoundException");
            e.printStackTrace ();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println ("IOException");
            e.printStackTrace ();
            
        }
    }
    
    public void save (JFileChooser f) {
        try (FileWriter out = new FileWriter (f.getSelectedFile () + ".daf")) {
            
            for (String s : filter) {
                out.write (s);
                out.write ("\n");
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
            
        }
        
    }
    
    public boolean remove (int index) {
        return (filter.remove (index)) != null;
    }
    
    public boolean remove (String s) {
        return (filter.remove (s));
    }
    
    public boolean add (String s) {
        return (filter.add (s));
    }
    
    @Override
    public Iterator <String> iterator () {
        return (this.filter.iterator ());
    }
    
    @Override
    public boolean hasNext () {
        return (this.filter.iterator ().hasNext ());
    }
    
    @Override
    public String next () {
        return (this.filter.iterator ().next ());
    }
    
    @Override
    public void remove () {
        this.filter.iterator ().remove ();
    }
    
    @Override
    public String toString () {
        String s = "";
        for (String line : this.filter) {
            s += (line + "\n");
        }
        return (s);
    }
    
}
