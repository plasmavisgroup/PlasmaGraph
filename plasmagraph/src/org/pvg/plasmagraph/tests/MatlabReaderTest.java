package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	
		
		File dummy = new File("./test/matlab/Parameter2013-06-14-dif-dims.mat");
		MatlabReader mlr = new MatlabReader();
		String str1 = mlr.toString(dummy);
		
		PrintWriter out;
		try {
			out = new PrintWriter("./test/matlab/Parameter2013-06-14-dif-dims.txt");
			out.println(str1);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
        
	}
*/
	@Test
	public void testToDataSet() {
		
		//DataSet dsExpected = new DataSet();
		//dsExpected.add(o);
	    
	    fail("Not yet implemented");
	}

	@Test
	public void testToStringFile() throws IOException {
		
		/** test string output for file: Parameter2013-06-11.mat **/
		File dummyMat = new File("./test/matlab/Parameter2013-06-11.mat");
		MatlabReader mlr = new MatlabReader();		
		
		String str1 = mlr.toString(dummyMat);
		String str2 = readFile("./test/matlab/Parameter2013-06-11.txt", Charset.defaultCharset());        
		
		assertEquals("Checking for expected string 1: ", str1, str2);
		
		/** test string output for file: Parameter2013-06-12.mat **/
		dummyMat = new File("./test/matlab/Parameter2013-06-12.mat");
		
		str1 = mlr.toString(dummyMat);
		str2 = readFile("./test/matlab/Parameter2013-06-12.txt", Charset.defaultCharset());
		
		assertEquals("Checking for expected string 2: ", str1, str2);
		
		/** test string output for file: Parameter2013-06-13.mat **/
		dummyMat = new File("./test/matlab/Parameter2013-06-13.mat");
		
		str1 = mlr.toString(dummyMat);
		str2 = readFile("./test/matlab/Parameter2013-06-13.txt", Charset.defaultCharset());
		
		assertEquals("Checking for expected string 3: ", str1, str2);			
		
		/** test string output for file: Parameter2013-06-14-dif-dims.mat **/
		dummyMat = new File("./test/matlab/Parameter2013-06-14-dif-dims.mat");
		
		str1 = mlr.toString(dummyMat);
		str2 = readFile("./test/matlab/Parameter2013-06-14-dif-dims.txt", Charset.defaultCharset());
		
		assertEquals("Checking for expected string 4: ", str1, str2);
		
	}
        
	@Test
	public void testEquals() {
        File dummy = new File("./test/matlab/Parameter2013-06-11.mat");
        MatlabReader mlr = new MatlabReader();
        DataSet data = mlr.toDataSet(dummy);
        DataSet data2 = mlr.toDataSet(dummy);
        assertEquals("Checking for equality: ",data, data2);
	}
	
	public DataSet getExpectedDataSet(int dsNum){
		/** create an empty data set **/
		DataSet ds = new DataSet();
		
		/** create some columns for the data set **/	
		DataColumn dcExperimentNumber = new DataColumn("ExperimentNumber", "Double");
		
		/** add contents to the columns **/
		//dcExperimentNumber.add(o)
		
		/** add columns to data set **/
		ds.add(dcExperimentNumber);
		return ds;
	}
	
	static String readFile(String path, Charset encoding) throws IOException 
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}
	
	

}
