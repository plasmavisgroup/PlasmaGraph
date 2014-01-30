/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmatio.test;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLArray;
import com.jmatio.types.MLCell;
import com.jmatio.types.MLJavaObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Administrator
 */
public class main {
    
    static java.lang.String _PROJECTS_PATH = "C:\\wamp\\www\\PlasmaGraph\\tests\\JMatIO-041212\\";
    
    public static void main(String[] args) {
        System.out.println("Test: reading ExperimentData_04_09_2013_dummy");
        MatFileReader mfr = new MatFileReader();
        try {
            Map <String, MLArray> dataMap = mfr.read( new File(_PROJECTS_PATH + "test\\ExperimentData_04_09_2013_dummy.mat"));
            
            System.out.println("Size:");
            System.out.println(dataMap.size());
            System.out.println("values");
            System.out.println(dataMap.toString());    
            
            for (Iterator iterator = dataMap.values().iterator(); iterator.hasNext();){
                MLCell cells = (MLCell) iterator.next();
                //System.out.println(cells.getM());
                for(int i = 0; i < cells.getN(); i++){
                    for(int j = 0; j < cells.getM(); j++)
                        System.out.println(cells.get(j, i));
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
