/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import utils.data.DataColumn;
import utils.data.DataFilter;
import utils.data.DataSet;
import utils.data.MatlabReader;


/**
 *
 * @author DangoMango-Win
 */
public class main {
    
   /**
    *
    * @author Administrator
    */
    public static void main(String[] args) {
        
        File dummy = new File("ExperimentData_04_09_2013_dummy.mat");
        System.out.println(dummy.getAbsolutePath());
        String s[];
        DataFilter filter = new DataFilter();
        
        System.out.println("- PlasmaGraph -");
        
        MatlabReader mlr = new MatlabReader();
        //DataSet data = mlr.toDataSet(dummy, filter);
        //mlr.toString(dummy);
        DataSet data = mlr.toDataSet(dummy);
        System.out.println(data.toString());
    }
    
}
