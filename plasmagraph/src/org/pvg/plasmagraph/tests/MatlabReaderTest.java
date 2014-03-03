package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.readers.MatlabReader;

public class MatlabReaderTest {
/*	
	public static void main (String [] args) {
	     
        
        
        System.out.println(mlr.toString(dummy));
            for(int i = 0; i < data.size(); i++){
                DataColumn column = data.get(i);                
                if(column.get(0).getClass().getName() == "java.lang.Double"){
                    double timestamp = (double) column.get(0);
                    Date somedate = new Date((Math.round(timestamp) * 1000));
                    System.out.println(column.getName());
                    System.out.println(somedate);                    
                }
            }
        
	}
*/
	@Test
	public void testToDataSet() {
            
            fail("Not yet implemented");
	}

	@Test
	public void testToStringFile() {
		fail("Not yet implemented");
	}
        
        @Test
	public void testEquals() {
            File dummy = new File("./test/matlab/Parameter2013-06-11.mat");
            MatlabReader mlr = new MatlabReader();
            DataSet data = mlr.toDataSet(dummy);
            DataSet data2 = mlr.toDataSet(dummy);
            assertEquals("Checking for equality: ",data, data2);
	}

}
