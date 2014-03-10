package org.pvg.plasmagraph.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.readers.MatlabReader;

public class MatlabTest {
	
	private final String matlab_test_data_1 = "ExperimentData_04_09_2013_dummy.mat";

	@Test
	public void testGeneralFunctions () {
		File dummy = new File (matlab_test_data_1);
        System.out.println(dummy.getAbsolutePath());
        //String s[];
        //DataFilter filter = new DataFilter();
        
        System.out.println("- PlasmaGraph -");
        
        MatlabReader mlr = new MatlabReader();
        //DataSet data = mlr.toDataSet(dummy, filter);
        //mlr.toString(dummy);
        HeaderData data = mlr.toDataSet(dummy);
        //System.out.println(data.toString());
        
        fail ("Not yet implemented"); // TODO
	}
	@Test
	public void testToDataSet () {
		fail ("Not yet implemented"); // TODO
	}

	@Test
	public void testToStringFile () {
		fail ("Not yet implemented"); // TODO
	}

}
