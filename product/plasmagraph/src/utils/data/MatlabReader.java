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
    
   /**
    *
    * 
    */
    public DataSet toDataSet(File f){
        
        /** variables definition **/
        MatFileReader mfr = new MatFileReader(); 
        DataSet result = new DataSet();
        
        try {
            
            /** variables definition **/
            Map <String, MLArray> dataMap = mfr.read(f);
            ArrayList <DataColumn> column_list = new <DataColumn> ArrayList ();            
            DataColumn first_column = new DataColumn(MLArray.mxCHAR_CLASS, "name");
            ArrayList <DataColumn> other_columns = new <DataColumn> ArrayList();
            
            /** iterate every cell group in the .mat file **/
            for (Iterator iterator = dataMap.values().iterator(); iterator.hasNext();){
                
                MLCell cell_group = (MLCell) iterator.next();
                
                /** first cell group has the header names **/
                if(first_column.isEmpty()){
                    
                }
                
                /** add group name to first column as many times as rows in the group **/
                for(int i = 0; i < cell_group.getM(); i++) {
                    first_column.add(cell_group.name);
                }
                
                /** get every column from the group and place them in the result DataSet **/
                for(int i = 0; i < cell_group.getN(); i++){
                    DataColumn column = getColumn(cell_group, i);
                    other_columns.add(column);
                }
                                               
            }
            
                /** add columns to result **/
                result.add(first_column);
                for(int i = 0; i < other_columns.size(); i++){
                    result.add(other_columns.get(i));
                }
            
            
        } catch (IOException ex) {
            Logger.getLogger(MatlabReader.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return result;
    }

   /**
    *
    * 
    */
    private DataColumn getColumn(MLCell cell, int index){
        
        DataColumn column = new DataColumn(cell.getType());
        for ( int m = 0; m < cell.getM(); m++ )
        {
           String item = cell.get(m,index).contentToString();
           //System.out.println(item.toString());
           column.add(item);
        }
        
        return column;
    }
    
   /**
    *
    * 
    */
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

   /**
    *
    * 
    */    
    private static void writeToFile(String pFilename, StringBuffer pData) throws IOException {  
        BufferedWriter out = new BufferedWriter(new FileWriter(pFilename));  
        out.write(pData.toString());  
        out.flush();  
        out.close();  
    } 
    
   /**
    *
    * 
    */    
    private static StringBuffer readFromFile(String pFilename) throws IOException {  
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
