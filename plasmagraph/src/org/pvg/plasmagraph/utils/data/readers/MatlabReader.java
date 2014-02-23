package org.pvg.plasmagraph.utils.data.readers;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;

/**
 *
 * @author DangoMango-Win
 */
public class MatlabReader {
    
   /**
    *	Reads a collection of cell groups from a level 5 MAT-File and encodes that collection of cell groups as a DataSet object
    *	
    *	@param String
    *	@return DataSet 
    */
    public DataSet toDataSet(File f){
        
        /** variables definition **/
        MatFileReader mfr = new MatFileReader(); 
        DataSet result = new DataSet();
        
        /** read the level 5 MAT-File and encode its contents as DataSet **/        
        try {
            
            /** variables definition **/
            Map <String, MLArray> dataMap = mfr.read(f);  
            ArrayList <String> column_names = new <String> ArrayList();
            boolean first_pass = true;
            
            /** iterate over every cell group in the level 5 MAT-File **/
            for (Iterator iterator = dataMap.values().iterator(); iterator.hasNext();){
                
                /** get cell group from iterator **/
            	MLCell cell_group = (MLCell) iterator.next();
                               
                /** get every column from the group and place them in the result DataSet **/
                if(first_pass){
                	
                	/** get header names from first cell group **/
                	for(int col_index = 0, row = 0; col_index < cell_group.getN(); col_index++){
                		column_names.add(cell_group.get(0,col_index).contentToString());
                	}
                	
                	/** retrieve next cell group **/
                	cell_group = (MLCell) iterator.next();
                    
                    /** constructs a column with the name of the cell_group as many times as rows in the group **/
                	DataColumn column = new DataColumn("string", "name");
                    for(int counter = 0; counter < cell_group.getM(); counter++) {
                    	column.add(cell_group.name);
                    }
                    
                    /** add cell_group's name column to the result data set **/
                    result.add(column);
                    
                    /** get each column from this cell group and add them to the result data set **/
                    for(int col_index = 0; col_index < cell_group.getN(); col_index++){
                        column = getColumn(cell_group, col_index);
                        column.setName(column_names.get(col_index));
                        result.add(column);
                    }
                    
                }else{
                	
                	/** constructs a column with the name of the cell_group as many times as rows in the group **/
                	DataColumn column = new DataColumn("string", "name");
                    for(int counter = 0; counter < cell_group.getM(); counter++) {
                    	column.add(cell_group.name);
                    }
                    
                    /** add cell_group's name column to the result data set **/
                    result.get(0).addAll(column);
                    
                    /** get each column from this cell group and add them to the result data set **/
                    for(int col_index = 0; col_index < cell_group.getN(); col_index++){
                        column = getColumn(cell_group, col_index);
                        column.setName(column_names.get(col_index));
                        result.get(col_index+1).addAll(column);
                    }
                }
                
                /** end of first pass **/
                first_pass = false;
                                               
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
        DataColumn column = new DataColumn("string", "name");
        for ( int m = 0; m < cell.getM(); m++ )
        {
            String item = cell.get(m,index).contentToString();
           column.add(item);
        }
        
        return column;
    }
    
   /**
    *
    * 
    */
    public String toString(File f){
        
    	StringBuilder str = new StringBuilder();
    	DataSet columns = toDataSet(f); 
    	
    	/** add column's names to string **/
    	str.append('[');
    	for(int i = 0; i < columns.size(); i++){
    		str.append(columns.get(i).getName());
    		str.append(", ");
    	}
    	str.append(']');
    	
    	str.append(columns.toString());
        
        return str.toString();       
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
