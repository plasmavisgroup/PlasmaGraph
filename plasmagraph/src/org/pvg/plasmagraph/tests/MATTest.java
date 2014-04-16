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
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.readers.MatlabProcessor;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
import org.pvg.plasmagraph.utils.exceptions.InvalidDataSizeException;
import org.pvg.plasmagraph.utils.types.ColumnType;

import com.jmatio.types.MLArray;

@SuppressWarnings ("javadoc")
public class MATTest {

	@Test
	public void testToDataSet () throws FunctionNotImplementedException, InvalidDataSizeException {

		String matlab_test_data_1 = "./plasmagraph/test/matlab/"
				+ "Parameter2013-06-11.mat";
		MatlabProcessor mlr = new MatlabProcessor (matlab_test_data_1);
		// Prepare DataSet
		DataSet tstDataSet = new DataSet ();
					
		try {
			// Prepare HeaderData
			HeaderData hd = new HeaderData ();
			mlr.toString ();
			mlr.getHeaders (hd);
			
			// Prepare GraphPair
			GraphPair p = new GraphPair ();
			p.changeX (2, hd.get (2).getKey ());
			p.changeY (3, hd.get (3).getKey ());

			// Obtain DataSet
			mlr.toDataSet (tstDataSet, p, hd);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println (mlr.toString ());
		System.out.println (tstDataSet.toString ());
		assertEquals ("Checking for expected number of objects in the Test DataSet: ",
				27, tstDataSet.size ());
	}

	@Test
	public void testToStringFile () throws IOException {

		/** test string output for file: Parameter2013-06-11.mat **/
		File dummyMat = new File ("./plasmagraph/test/matlab/Parameter2013-06-11.mat");
		MatlabProcessor mlr = new MatlabProcessor (dummyMat);
		
		String str1 = mlr.toString ();
		String str2 = readFile ("./plasmagraph/test/matlab/Parameter2013-06-11.txt",
				Charset.defaultCharset ());

		assertEquals ("Checking for expected string 1: ", str1, str2);

		/** test string output for file: Parameter2013-06-12.mat **/
		dummyMat = new File ("./plasmagraph/test/matlab/Parameter2013-06-12.mat");

		mlr.setFile (dummyMat);
		
		str1 = mlr.toString ();
		str2 = readFile ("./plasmagraph/test/matlab/Parameter2013-06-12.txt",
				Charset.defaultCharset ());

		assertEquals ("Checking for expected string 2: ", str1, str2);

		/** test string output for file: Parameter2013-06-13.mat **/
		dummyMat = new File ("./plasmagraph/test/matlab/Parameter2013-06-13.mat");

		mlr.setFile (dummyMat);
		
		str1 = mlr.toString ();
		str2 = readFile ("./plasmagraph/test/matlab/Parameter2013-06-13.txt",
				Charset.defaultCharset ());

		assertEquals ("Checking for expected string 3: ", str1, str2);

		/** test string output for file: Parameter2013-06-14-dif-dims.mat **/
		dummyMat = new File ("./plasmagraph/test/matlab/Parameter2013-06-14-dif-dims.mat");

		mlr.setFile (dummyMat);
		
		str1 = mlr.toString ();
		str2 = readFile ("./plasmagraph/test/matlab/Parameter2013-06-14-dif-dims.txt",
				Charset.defaultCharset ());

		assertEquals ("Checking for expected string 4: ", str1, str2);

	}

/*	@Test
	public void testEquals () {
		// Prepare DataSets
		DataSet data = new DataSet (false);
		DataSet data2 = new DataSet (false);
		
		// Prepare MatlabProcessor
		File dummy = new File ("./plasmagraph/test/matlab/Parameter2013-06-11.mat");
		MatlabProcessor mlr = new MatlabProcessor (dummy);
		
		try {
			
			// Prepare HeaderData
			HeaderData hd = new HeaderData ();
			mlr.getHeaders (hd);
			
			// Prepare GraphPair
			GraphPair p = new GraphPair ();
			p.changeX (2, hd.get (2).getKey ());
			p.changeY (3, hd.get (3).getKey ());
			
			// Test
			mlr.toDataSet (data, p, hd);
			mlr.toDataSet (data2, p, hd);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals ("Checking for equality: ", data, data2);
	}*/

	@Test
	public void testPrint () {
		File dummyMat = new File ("./plasmagraph/test/matlab/Parameter2013-06-11.mat");
		MatlabProcessor mlr = new MatlabProcessor (dummyMat);
		
		//System.out.println (mlr.toString ());
	}
	
	@Test
	public void testMapFileSize () {
		File dummyMat = new File ("./plasmagraph/test/matlab/Parameter2013-06-11.mat");
		MatlabProcessor mlr = new MatlabProcessor (dummyMat);
		
		
	}
	
	//=================================================================
	
	/*public DataSet getExpectedDataSet (int dsNum) {
		*//** create an empty data set **//*
		DataSet ds = new DataSet (false);

		*//** create some columns for the data set **//*
		DataColumn dcExperimentNumber = new DataColumn ("ExperimentNumber",
				"Double");
		DataColumn CurrentLevelA = new DataColumn ("CurrentLevelA", "Double");
		DataColumn VacuumPreasureTorr = new DataColumn ("VacuumPreasureTorr",
				"Double");

		*//** define the variables that will be added to the DataColumn **//*
		double datum1;
		double datum2;
		double datum3;
		double datum4;
		double datum5;
		double datum6;
		double datum7;
		double datum8;

		*//** these variables will be added to column: ExperimentNumber **//*
		datum1 = 1.0;
		datum2 = 2.0;

		*//** Populate DataSet ExperimentNumber **//*
		for (int i = 0; i < 30; i++) {

			*//** add datum1 to the 1st 15 rows of the DataColumn **//*
			if (i < 15) {
				ds.addToX (datum1);
			}

			*//** add datum2 to the next 16 rows of the DataColumn **//*
			if (i > 14 && i < 30) {
				ds.addToX (datum2);
			}
		}

		*//** these variables will be added to column: CurrentLevelA **//*
		datum1 = 400.0;
		datum2 = 480.0;
		datum3 = 470.0;
		datum4 = 450.0;
		datum5 = 460.0;

		*//** Populate DataSet CurrentLevelA **//*
		for (int i = 0; i < 30; i++) {

			*//** add datum1 to the 1st 15 rows of the DataColumn **//*
			if (i < 15) {
				CurrentLevelA.add (datum1);
			}

			*//** add datum2 to the next 7 rows of the DataColumn **//*
			if (i > 14 && i < 21) {
				CurrentLevelA.add (datum2);
			}

			*//** add datum3 to the next 4 rows of the DataColumn **//*
			if (i > 20 && i < 24) {
				CurrentLevelA.add (datum3);
			}

			*//** add datum4 to the next 4 rows of the DataColumn **//*
			if (i > 23 && i < 27) {
				CurrentLevelA.add (datum4);
			}

			*//** add datum5 to the next 4 rows of the DataColumn **//*
			if (i > 26 && i < 30) {
				CurrentLevelA.add (datum5);
			}
		}

		*//** these variables will be added to column: VacuumPreasureTorr **//*
		datum1 = 8.0E-5;
		datum2 = 9.5E-5;
		datum3 = 9.8E-5;
		datum4 = datum2;
		datum5 = 9.0E-5;
		datum7 = 1.0E-4;

		*//** Populate DataSet VacuumPreasureTorr **//*
		for (int i = 0; i < 31; i++) {

			*//** add datum1 to the 1st 3 rows of the DataColumn **//*
			if (i < 3) {
				VacuumPreasureTorr.add (datum1);
			}

			*//** add datum2 to the next 4 rows of the DataColumn **//*
			if (i > 2 && i < 6) {
				VacuumPreasureTorr.add (datum2);
			}

			*//** add datum3 to the next 4 rows of the DataColumn **//*
			if (i > 5 && i < 9) {
				VacuumPreasureTorr.add (datum3);
			}

			*//** add datum4 to the next 4 rows of the DataColumn **//*
			if (i > 8 && i < 12) {
				VacuumPreasureTorr.add (datum4);
			}

			*//** add datum5 to the next 4 rows of the DataColumn **//*
			if (i > 11 && i < 15) {
				VacuumPreasureTorr.add (datum5);
			}

			*//** add datum7 to the next 13 rows of the DataColumn **//*
			if (i > 17 && i < 30) {
				VacuumPreasureTorr.add (datum7);
			}
		}

		*//** add columns to data set **//*
		//ds.add (dcExperimentNumber);
		ds.add (CurrentLevelA);
		ds.add (VacuumPreasureTorr);

		return ds;
	}*/

	static String readFile (String path, Charset encoding) throws IOException {
		byte [] encoded = Files.readAllBytes (Paths.get (path));
		return encoding.decode (ByteBuffer.wrap (encoded)).toString ();
	}

}
