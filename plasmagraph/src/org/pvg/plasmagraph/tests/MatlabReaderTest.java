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
	
	@Test
	public void testToDataSet() {
		
		File dummyMat = new File("./test/matlab/Parameter2013-06-11.mat");
		MatlabReader mlr = new MatlabReader();
		
		DataSet tstDataSet = mlr.toDataSet(dummyMat);		
		DataSet dsExpected = getExpectedDataSet(1);
		
		assertEquals("Checking for expected object in DataSet column 0: ", tstDataSet.get(0), dsExpected.get(0));
		assertEquals("Checking for expected object in DataSet column 1: ", tstDataSet.get(1), dsExpected.get(1));
		assertEquals("Checking for expected object in DataSet column 2: ", tstDataSet.get(2), dsExpected.get(2));
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
		DataColumn CurrentLevelA = new DataColumn("CurrentLevelA", "Double");
		DataColumn VacuumPreasureTorr = new DataColumn("VacuumPreasureTorr", "Double");
		
		/** define the variables that will be added to the DataColumn **/
		double datum1;
		double datum2;
		double datum3;
		double datum4;
		double datum5;
		double datum6;
		double datum7;
		double datum8;
		
		/** these variables will be added to column: ExperimentNumber **/
		datum1 = 1.0;
		datum2 = 2.0;
		datum3 = 0.0/0.0; /** this is how I make my NaN **/
		
		/** Populate DataSet ExperimentNumber **/
		for(int i = 0; i < 31; i++){
			
			/** add datum1 to the 1st 15 rows of the DataColumn **/
			if(i < 15){
				dcExperimentNumber.add(datum1);
			}
			
			/** add datum2 to the next 16 rows of the DataColumn **/
			if(i > 14 && i < 30){
				dcExperimentNumber.add(datum2);				
			}
			
			/** add datum3 to the last row of the DataColumn **/			
			if(i == 30){
				dcExperimentNumber.add(datum3);
			}
		}
		
		/** these variables will be added to column: CurrentLevelA **/
		datum1 = 400.0;
		datum2 = 480.0;
		datum3 = 470.0;
		datum4 = 450.0;
		datum5 = 460.0;
		datum6 =  0.0/0.0; /** this is how I make my NaN **/
		
		/** Populate DataSet CurrentLevelA **/
		for(int i = 0; i < 31; i++){
			
			/** add datum1 to the 1st 15 rows of the DataColumn **/
			if(i < 15){
				CurrentLevelA.add(datum1);
			}
			
			/** add datum2 to the next 16 rows of the DataColumn **/
			if(i > 14 && i < 21){
				CurrentLevelA.add(datum2);				
			}
			
			/** add datum2 to the next 16 rows of the DataColumn **/
			if(i > 20 && i < 24){
				CurrentLevelA.add(datum3);				
			}
			
			/** add datum2 to the next 16 rows of the DataColumn **/
			if(i > 23 && i < 27){
				CurrentLevelA.add(datum4);				
			}
			
			/** add datum2 to the next 16 rows of the DataColumn **/
			if(i > 26 && i < 30){
				CurrentLevelA.add(datum5);				
			}
			
			/** add datum3 to the last row of the DataColumn **/			
			if(i == 30){
				CurrentLevelA.add(datum6);
			}
		}
		
		/** these variables will be added to column: VacuumPreasureTorr **/
		datum1 = 8.0E-5;
		datum2 = 9.5E-5;
		datum3 = 9.8E-5;
		datum4 = datum2;
		datum5 = 9.0E-5;
		datum6 = 0.0/0.0; /** this is how I make my NaN **/
		datum7 =  1.0E-4;
		datum8 = datum6;
		
		/** Populate DataSet VacuumPreasureTorr **/
		for(int i = 0; i < 31; i++){
			
			/** add datum1 to the 1st 15 rows of the DataColumn **/
			if(i < 3){
				VacuumPreasureTorr.add(datum1);
			}
			
			/** add datum2 to the next 16 rows of the DataColumn **/
			if(i > 2 && i < 6){
				VacuumPreasureTorr.add(datum2);				
			}
			
			/** add datum2 to the next 16 rows of the DataColumn **/
			if(i > 5 && i < 9){
				VacuumPreasureTorr.add(datum3);				
			}
			
			/** add datum2 to the next 16 rows of the DataColumn **/
			if(i > 8 && i < 12){
				VacuumPreasureTorr.add(datum4);				
			}
			
			/** add datum2 to the next 16 rows of the DataColumn **/
			if(i > 11 && i < 15){
				VacuumPreasureTorr.add(datum5);				
			}
			
			/** add datum2 to the next 16 rows of the DataColumn **/
			if(i > 14 && i < 18){
				VacuumPreasureTorr.add(datum6);				
			}
			
			/** add datum2 to the next 16 rows of the DataColumn **/
			if(i > 17 && i < 30){
				VacuumPreasureTorr.add(datum7);				
			}
			
			/** add datum3 to the last row of the DataColumn **/			
			if(i == 30){
				VacuumPreasureTorr.add(datum8);
			}
		}
		
		/** add columns to data set **/
		ds.add(dcExperimentNumber);
		ds.add(CurrentLevelA);
		ds.add(VacuumPreasureTorr);
			
		return ds;
	}
	
	static String readFile(String path, Charset encoding) throws IOException 
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}
	
	

}
