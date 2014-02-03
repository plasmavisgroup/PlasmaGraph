package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import org.pvg.plasmagraph.utils.data.DataReference;
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
        fail ("Not yet implemented");
    }
    
    @Test
    public void testGetPair () {
        fail ("Not yet implemented");
    }
    
    @Test
    public void testGetNames () {
        fail ("Not yet implemented");
    }
    
    @Test
    public void testCreatePair () {
        fail ("Not yet implemented");
    }
}
