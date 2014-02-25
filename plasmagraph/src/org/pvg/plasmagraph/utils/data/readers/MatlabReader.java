package org.pvg.plasmagraph.utils.data.readers;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.*;



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
   
	/** Matlab Array Types (Classes) **/
    public static final int mxUNKNOWN_CLASS = 0;
    public static final int mxCELL_CLASS    = 1;
    public static final int mxSTRUCT_CLASS  = 2;
    public static final int mxOBJECT_CLASS  = 3;
    public static final int mxCHAR_CLASS    = 4;
    public static final int mxSPARSE_CLASS  = 5;
    public static final int mxDOUBLE_CLASS  = 6;
    public static final int mxSINGLE_CLASS  = 7;
    public static final int mxINT8_CLASS    = 8;
    public static final int mxUINT8_CLASS   = 9;
    public static final int mxINT16_CLASS   = 10;
    public static final int mxUINT16_CLASS  = 11;
    public static final int mxINT32_CLASS   = 12;
    public static final int mxUINT32_CLASS  = 13;
    public static final int mxINT64_CLASS   = 14;
    public static final int mxUINT64_CLASS  = 15;
    public static final int mxFUNCTION_CLASS = 16;
    public static final int mxOPAQUE_CLASS  = 17;
    
   /**
    *	Reads a collection of MLArray from a level 5 MAT-File and encodes that collection of MLArray as a DataSet object
    *	
    *	@param String
    *	@return DataSet 
    */
    public DataSet toDataSet(File f){
        
        /** variables definition **/
    	DataSet result = new DataSet();
        MatFileReader mfr = new MatFileReader();               
        
        /** read the level 5 MAT-File and encode its contents as DataSet **/        
        try {
            
            /** get file's data into a Map **/
        	 Map <String, MLArray> dataMap = mfr.read(f);   
            
            /** iterate over every group of data in the level 5 MAT-File **/
            for (Iterator iterator = dataMap.values().iterator(); iterator.hasNext();){
            	
                /** get matlab array from iterator **/
            	MLArray data_group = (MLArray) iterator.next();
            	
            	/** create and add the data column to the result set **/
            	DataColumn column = getColumn(data_group);
            	result.add(column);                                               
            }           
            
        } catch (IOException ex) {
            Logger.getLogger(MatlabReader.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        /** return resulting data set **/
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
    private DataColumn getColumn(MLArray matlabArray){
        DataColumn column = new DataColumn(matlabArray.name, matlabArray.typeToString(matlabArray.getType()));
        boolean type_is_valid = true;
        switch (matlabArray.getType())
        {
            case mxUNKNOWN_CLASS:
            	type_is_valid = false;
                break;
            case mxCELL_CLASS:
                MLCell mxCELL_values = (MLCell) matlabArray;
                for(int i = 0; i< mxCELL_values.getM(); i++){
                	column.add(mxCELL_values.get(i,0));
                }
                break;
            case mxSTRUCT_CLASS:
            	type_is_valid = false;
                break;
            case mxCHAR_CLASS:
            	 MLChar mxCHAR_values = (MLChar) matlabArray;
            	 for(int i = 0; i< mxCHAR_values.getM(); i++){
                 	column.add(mxCHAR_values.getString(i));
                 }
                break;
            case mxSPARSE_CLASS:
            	type_is_valid = false;
                break;
            case mxDOUBLE_CLASS:
            	MLDouble mxDOUBLE_values = (MLDouble) matlabArray;
            	for(int i = 0; i< mxDOUBLE_values.getM(); i++){
                	column.add(mxDOUBLE_values.get(i,0));
                }
                break;
            case mxSINGLE_CLASS:
            	type_is_valid = false;
                break;
            case mxINT8_CLASS:
            	type_is_valid = false;
                break;
            case mxUINT8_CLASS:
            	type_is_valid = false;
                break;
            case mxINT16_CLASS:
            	type_is_valid = false;
                break;
            case mxUINT16_CLASS:
            	type_is_valid = false;
                break;
            case mxINT32_CLASS:
            	type_is_valid = false;
                break;
            case mxUINT32_CLASS:
            	type_is_valid = false;
                break;
            case mxINT64_CLASS:
            	type_is_valid = false;
                break;
            case mxUINT64_CLASS:
            	type_is_valid = false;
                break;
            case mxFUNCTION_CLASS:
            	type_is_valid = false;
                break;
            case mxOPAQUE_CLASS:
            	type_is_valid = false;
                break;
            case mxOBJECT_CLASS:
            	type_is_valid = false;
                break;
            default:
            	type_is_valid = false;
                break;
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
