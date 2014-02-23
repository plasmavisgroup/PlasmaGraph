package org.pvg.plasmagraph.utils.data.readers;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.pvg.plasmagraph.utils.data.DataSet;

public class MatlabReaderTest {
	
	public static void main (String [] args) {
		File dummy = new File("./test/matlab/ExperimentData_04_09_2013_dummy.mat");
        //System.out.println(dummy.getAbsolutePath());
        
        System.out.println("- PlasmaGraph -");
        
        MatlabReader mlr = new MatlabReader();
        DataSet data = mlr.toDataSet(dummy);
        
        System.out.println(mlr.toString(dummy));
	}

	@Test
	public void testToDataSet() {
		fail("Not yet implemented");
	}

	@Test
	public void testToStringFile() {
		fail("Not yet implemented");
	}

}
