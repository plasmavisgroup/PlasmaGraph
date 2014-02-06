/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.data;

import java.io.*;
import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLArray;
import com.jmatio.types.MLCell;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DangoMango-Win
 */
public class MatlabReader {
    
    public DataSet toDataSet(File f){
        MatFileReader mfr = new MatFileReader(); 
        DataSet r = new DataSet();
        try {
            Map <String, MLArray> dataMap = mfr.read(f);
            ArrayList <DataColumn> column_list = new ArrayList ();            
            
            DataColumn first_column = new DataColumn();
            DataColumn n_column = new DataColumn();
            for (Iterator iterator = dataMap.values().iterator(); iterator.hasNext();){
                
                MLCell cell_group = (MLCell) iterator.next();
                
                // first cell group has the header's names
                if(first_column.isEmpty()){
                                     
                }
                
                // fill first column with the variable's name
                for(int i = 0; i < cell_group.getM(); i++) {
                    first_column.add(cell_group.name);
                }
                
                // fill first column with the variable's name
                //column.add(column);
                
                //column = getColumn(cell_group, 0);                               
            }
            
              r.add(first_column);
            
            
        } catch (IOException ex) {
            Logger.getLogger(MatlabReader.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return r;
    }
    
    private DataColumn getColumn(MLCell cell, int index){
        
        DataColumn column = new DataColumn();
        for ( int m = 0; m < cell.getM(); m++ )
        {
           Object item = cell.get(m,index);
           column.add(item);
        }
        
        return column;
    }
    
    public String toString(File f){
        
        MatFileReader mfr = new MatFileReader();
        String s = "";
        
        try {
            Map <String, MLArray> dataMap = mfr.read(f);
            ArrayList <DataColumn> column_list = new ArrayList ();            
            
            boolean first = true;
            StringBuffer sb = new StringBuffer();
            for (Iterator iterator = dataMap.values().iterator(); iterator.hasNext();){
                
                MLCell cell_group = (MLCell) iterator.next();
                
                // first cell group has the header's names
                if(first){
                    first = false;
                }
                
                sb.append(cell_group.name + " =");
                sb.append(System.getProperty("line.separator"));
                for ( int m = 0; m < cell_group.getM(); m++ )
                {
                   sb.append("\t");
                   for ( int n = 0; n < cell_group.getN(); n++ )
                   {
                       sb.append( cell_group.get(m,n).contentToString() );
                       sb.append("\t");
                   }
                   sb.append(System.getProperty("line.separator"));
                }
                
                sb.append(System.getProperty("line.separator"));                
            }
            
            // writeToFile("out.txt",sb);            
            s = sb.toString();
            
        } catch (IOException ex) {
            Logger.getLogger(MatlabReader.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return s;       
    }
    
    public static void writeToFile(String pFilename, StringBuffer pData) throws IOException {  
        BufferedWriter out = new BufferedWriter(new FileWriter(pFilename));  
        out.write(pData.toString());  
        out.flush();  
        out.close();  
    }  
    public static StringBuffer readFromFile(String pFilename) throws IOException {  
        BufferedReader in = new BufferedReader(new FileReader(pFilename));  
        StringBuffer data = new StringBuffer();  
        int c = 0;  
        while ((c = in.read()) != -1) {  
            data.append((char)c);  
        }  
        in.close();  
        return data;  
    } 
}
