package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@SuppressWarnings ("javadoc")
public class ReferenceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none ();
    
	@Test
    public void testAdd () {
        // Set up
        DataReference r1 = new DataReference ();
        
        // Tests
        assertTrue ("Basic Add: ", r1.add (new GraphPair (1, 2, "Dummy Reference 1 vs. Dummy Reference 2")));
        assertFalse ("Adding Negative Indexes: ", r1.add (new GraphPair (-1, Integer.MIN_VALUE, "Dummy Reference -1 vs. Dummy Reference -2^16 or something")));
        assertFalse ("Adding Pairs with Incorrect Names: ", r1.add (new GraphPair (2, 3, "Derp")));
        assertFalse ("Adding Pairs with No Names: ", r1.add (new GraphPair (3, 4, "")));
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
        GraphPair p1 = new GraphPair (1, 2, "Position vs. Time");
        GraphPair p2 = new GraphPair (1, 3, "Position vs. Velocity");
        GraphPair p3 = new GraphPair (1, 4, "Position vs. Acceleration");
        GraphPair p4 = new GraphPair (2, 1, "Time vs. Position");
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
        GraphPair p1 = new GraphPair (1, 2, "Position vs. Time");
        GraphPair p2 = new GraphPair (1, 3, "Position vs. Velocity");
        GraphPair p3 = new GraphPair (1, 4, "Position vs. Acceleration");
        GraphPair p4 = new GraphPair (2, 1, "Time vs. Position");
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
         GraphPair p1 = new GraphPair (1, 2, "Position vs. Time");
         GraphPair p2 = new GraphPair (1, 3, "Position vs. Velocity");
         GraphPair p3 = new GraphPair (1, 4, "Position vs. Acceleration");
         GraphPair p4 = new GraphPair (2, 1, "Time vs. Position");
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
        GraphPair p1 = new GraphPair (1, 2, "Position vs. Time");
        GraphPair p2 = new GraphPair (1, 3, "Position vs. Velocity");
        GraphPair p3 = new GraphPair (1, 4, "Position vs. Acceleration");
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
        GraphPair p1 = new GraphPair (1, 2, "Position vs. Time");
        GraphPair p2 = new GraphPair (1, 3, "Position vs. Velocity");
        GraphPair p3 = new GraphPair (1, 4, "Position vs. Acceleration");
        GraphPair p4 = new GraphPair (2, 1, "Time vs. Position");
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
    	GraphPair p1 = new GraphPair (1, 2, "Position vs. Time");
    	
    	assertTrue ("Checking if r1 is empty.", r1.isEmpty ());
    	r1.add (p1);
    	assertFalse ("Checking if r1 is empty.", r1.isEmpty ());
    }
    
	/**
	 * Getter method. Provides the name of
	 * @return
	 */
	public Comparable<String> getDataSetName (HeaderData hd) {
		if (hd.size () == 2) {
			return (hd.get (0).getKey () + " vs. " +
					hd.get (1).getKey ());
		} else {
			return ("Default");
		}
	}
	
}
