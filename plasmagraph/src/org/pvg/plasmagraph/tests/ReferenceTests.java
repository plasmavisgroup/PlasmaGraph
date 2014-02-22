package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.Pair;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ReferenceTests {
    @Rule
    public ExpectedException thrown = ExpectedException.none ();
    
    @Test
    public void testAdd () {
        // Set up
        DataReference r1 = new DataReference ();
        
        // Tests
        assertTrue ("Basic Add: ", r1.add (new Pair (1, 2, "Dummy Reference 1 vs. Dummy Reference 2")));
        assertFalse ("Adding Negative Indexes: ", r1.add (new Pair (-1, Integer.MIN_VALUE, "Dummy Reference -1 vs. Dummy Reference -2^16 or something")));
        assertFalse ("Adding Pairs with Incorrect Names: ", r1.add (new Pair (2, 3, "Derp")));
        assertFalse ("Adding Pairs with No Names: ", r1.add (new Pair (3, 4, "")));
        assertFalse ("Adding Pairs with No Names: ", r1.add (3, 4, ""));
        assertTrue ("Basic Add: ", r1.add (1, 2, "Dummy Reference 1 vs. Dummy Reference 2"));
        
        // Clean up
        r1 = null;
    }
    
    @Test
    public void testRemove () throws IndexOutOfBoundsException {
        // Set up
        DataReference r1 = new DataReference ();
        DataReference r2 = new DataReference ();
        Pair p1 = new Pair (1, 2, "Position vs. Time");
        Pair p2 = new Pair (1, 3, "Position vs. Velocity");
        Pair p3 = new Pair (1, 4, "Position vs. Acceleration");
        Pair p4 = new Pair (2, 1, "Time vs. Position");
        r1.add (p1);
        r1.add (p2);
        r1.add (p3);
        r1.add (p4);
        
        // Tests
        assertTrue ("Basic Remove: ", r1.remove (0).equals (p1));
        assertTrue ("Single Pair Remove: ", r1.remove (p4));
        assertTrue ("All Remove: ", r2.isEmpty ());
    
        // Clean up
        r1 = r2 = null;
        p1 = p2 = p3 = p4 = null;
        
    }
    
    @Test
    public void testNegativeIndexRemove () throws IndexOutOfBoundsException {
        DataReference r1 = new DataReference ();
        
        thrown.expect (IndexOutOfBoundsException.class);
        thrown.expectMessage ("-5");
        r1.remove (-5);
        fail ("Negative Index: Did not throw an IndexOutOfBoundsException, "
                + "or did not trickle down from ArrayList<T>'s remove method.");
    }
    
    @Test
    public void testFind () {
        DataReference r1 = new DataReference ();
        Pair p1 = new Pair (1, 2, "Position vs. Time");
        Pair p2 = new Pair (1, 3, "Position vs. Velocity");
        Pair p3 = new Pair (1, 4, "Position vs. Acceleration");
        Pair p4 = new Pair (2, 1, "Time vs. Position");
        r1.add (p1);
        r1.add (p2);
        r1.add (p3);
        
        // Find Index with Pair
        assertTrue ("Looking for the first pair inserted", 
        		r1.findIndex (p1) == 0);
        assertTrue ("Looking for a pair that was not inserted", 
        		r1.findIndex (p4) == -1);
        
        // Find Index with String
        r1.add (p4);
        assertTrue ("Looking for pair with name \"Time vs. Position\"", 
        		r1.findIndex ("Time vs. Position") == 3);
        r1.remove (p4);
        assertFalse ("Looking for pair with name \"Time vs. Position\"", 
        		r1.findIndex ("Time vs. Position") == 3);
        
        // Find Pair with index
        assertEquals ("Looking for pair p2", p2, r1.findPair (1));
        assertNotEquals ("Looking for pair p2", p2, r1.findPair (0));
        
        // Find Pair with String
        assertEquals ("Looking for pair p2", p2, r1.findPair (p2.getName ()));
        assertNotEquals ("Looking for pair p4", p4, r1.findPair (p4.getName ()));
    }
    
    @Test
    public void testGet () {
    	 DataReference r1 = new DataReference ();
         Pair p1 = new Pair (1, 2, "Position vs. Time");
         Pair p2 = new Pair (1, 3, "Position vs. Velocity");
         Pair p3 = new Pair (1, 4, "Position vs. Acceleration");
         Pair p4 = new Pair (2, 1, "Time vs. Position");
         r1.add (p1);
         r1.add (p2);
         r1.add (p3);
         
         assertEquals ("Looking for p1 with get.", p1, r1.get (0));
         thrown.expect (IndexOutOfBoundsException.class);
         thrown.expectMessage ("3");
         assertNotEquals ("Looking for p4 with get.", p4, r1.get (3));
         fail ("Negative Index: Did not throw an IndexOutOfBoundsException");
    }
    
    @Test
    public void testGetNames () {
    	DataReference r1 = new DataReference ();
        Pair p1 = new Pair (1, 2, "Position vs. Time");
        Pair p2 = new Pair (1, 3, "Position vs. Velocity");
        Pair p3 = new Pair (1, 4, "Position vs. Acceleration");
        r1.add (p1);
        r1.add (p2);
        r1.add (p3);
        
        String [] names = new String [] {"Position vs. Time", 
        		"Position vs. Velocity", "Position vs. Acceleration"};
        String [] false_names = new String [] {"Position vs. Time", 
        		"Position vs. Velocity", "Position vs. Acceleration", 
        		"Time vs. Position"};
        
        assertTrue ("Getting the names in this DataReference", 
        		names[0].equals (r1.getNames ()[0]));
        assertTrue ("Getting the names in this DataReference", 
        		names[1].equals (r1.getNames ()[1]));
        assertTrue ("Getting the names in this DataReference", 
        		names[2].equals (r1.getNames ()[2]));
        assertTrue ("Getting the names in this DataReference", 
        		false_names[0].equals (r1.getNames ()[0]));
        assertTrue ("Getting the names in this DataReference", 
        		false_names[1].equals (r1.getNames ()[1]));
        assertTrue ("Getting the names in this DataReference", 
        		false_names[2].equals (r1.getNames ()[2]));
        thrown.expect (IndexOutOfBoundsException.class);
        thrown.expectMessage ("3");
        assertFalse ("Getting the names in this DataReference", 
        		false_names[3].equals (r1.getNames ()[3]));
        fail ("Negative Index: Did not throw an IndexOutOfBoundsException");
    }
    
    @Test
    public void testContains () {
    	DataReference r1 = new DataReference ();
        Pair p1 = new Pair (1, 2, "Position vs. Time");
        Pair p2 = new Pair (1, 3, "Position vs. Velocity");
        Pair p3 = new Pair (1, 4, "Position vs. Acceleration");
        Pair p4 = new Pair (2, 1, "Time vs. Position");
        r1.add (p1);
        r1.add (p2);
        r1.add (p3);
        
        assertTrue ("Checking if p3 is in the DataReference", 
        		r1.contains (p3.getName ()));
        assertFalse ("Checking if p4 is in the DataReference", 
        		r1.contains (p4.getName ()));
    }
    
    @Test
    public void testIsEmpty () {
    	DataReference r1 = new DataReference ();
    	Pair p1 = new Pair (1, 2, "Position vs. Time");
    	
    	assertTrue ("Checking if r1 is empty.", r1.isEmpty ());
    	r1.add (p1);
    	assertFalse ("Checking if r1 is empty.", r1.isEmpty ());
    }
    
    @Test
    @SuppressWarnings ("unused")
    public void testCreateXYPair () {
    	// Set up data.
    	DataReference r1 = new DataReference ();
        Pair p1 = new Pair (1, 2, "Time vs. Position");
        Pair p2 = new Pair (1, 3, "Position vs. Velocity");
        Pair p3 = new Pair (1, 4, "Position vs. Acceleration");
		Pair p4 = new Pair (2, 1, "Time vs. Position");
        r1.add (p1);
        r1.add (p2);
        r1.add (p3);
        
        DataSet main = new DataSet ();
        DataColumn<Double> c1 = new DataColumn<Double> ("Time", "double");
        DataColumn<Double> c2 = new DataColumn<Double> ("Position", "double");
        DataColumn<Double> c3 = new DataColumn<Double> ("Velocity", "double");
        DataColumn<Double> c4 = new DataColumn<Double> ("Acceleration", "double");
        
        c1.add (1.0); c1.add (2.0); c1.add (3.0);
        c2.add (1.0); c2.add (4.0); c2.add (9.0);
        c3.add (1.0); c3.add (3.0); c3.add (5.0);
        c4.add (1.0); c4.add (2.0); c4.add (3.0);
        
        main.add (c1); main.add (c2); main.add (c3); main.add (c4);

        DataSet t1 = new DataSet ();
        t1.add (c1); t1.add (c2);
        
        // Test
        String key = (String) r1.createXYGraphPair (0, main).getKey ();
        String compared = (String) t1.getDataSetName ();
        assertTrue ("Cherry pick Position v. Time and compare to Time.", key.equals (compared));
        
        key = (String) r1.createXYGraphPair (0, main).getKey ();
        compared = "Acceleration";
        assertFalse ("Cherry pick Position v. Time and compare to Acceleration.", key.equals (compared));
    }
}
