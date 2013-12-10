/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmagraph.sanitizer;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.io.CSV;

/**
 *
 * @author tako
 */
public class CSVDataReader {
    CSVReader reader;
    CSVParser parser;
    ArrayList<Integer> ignorable_columns = new ArrayList ();
    ArrayList<String> ignorable_words = new ArrayList ();
    
    public CSVDataReader (String file_location) {
        try {
            reader = new CSVReader (new FileReader (new File (file_location)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        this.initializeIgnorableWords();
    }
    
    public CSVDataReader (File f) {
        try {
            reader = new CSVReader (new FileReader (f));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        this.initializeIgnorableWords();
    }
    
    public DefaultCategoryDataset convertToCategoryDataset () {
        ArrayList<ArrayList<String>> data = new ArrayList ();
        try {
            // Put everything in a single list.
            List<String[]> csv_data = reader.readAll();
            
            // Remove: "File", "Time", "file", "time", and "Gas".
            // Check to see if any of the columns include the removable words.
            // If they do include them, add that column to the "ignorable_columns" ArrayList
            initializeColumns (csv_data);
            
            // Reset the column counter.
            int col = 0;
            
            // Now, go through the rest of the rows.
            parseRows (csv_data, data);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return (listToCategory (data));
    }

    private void initializeIgnorableWords() {
        ignorable_words.add("File");
        ignorable_words.add("file");
        ignorable_words.add("Time");
        ignorable_words.add("time");
        ignorable_words.add("Gas");
        ignorable_words.add("gas");
    }

    private DefaultCategoryDataset listToCategory(ArrayList<ArrayList<String>> data) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void initializeColumns(List<String[]> csv_data) {
        int col = 0;
        for (String s : csv_data.get(0)) {
            if (this.ignorable_words.contains(s)) {
                this.ignorable_columns.add(col);
            }
            ++col;
        }
    }

    private ArrayList<ArrayList<String>> parseRows(List<String[]> csv_data, ArrayList<ArrayList<String>> data) {
        
        // Set up the variables for the loop, both for the csv_data and data arrays.
        int col = 0, cur_row = 0, cur_col = 0;
        
        // Start the loop, based on the row of the csv_data.
        for (int row = 0; row <= csv_data.size(); ++row, ++cur_row) {
            // Start on the first column, always.
            col = 0;
            // And go through all columns in each row.
            for (String s : csv_data.get(row)) {
                // @TODO: If this column contains erroneous data, delete the entire column.
                if (isGarbage(s)) {
                    // Remove that entire row.
                    data.remove(cur_row);
                }
                // If this column is to be ignored, ignore it.
                else if (!ignorable_columns.contains(col)) {
                    // Since this column has valid data, put it into the data array.
                    data.get(cur_row).add(s);
                    // Shift to the next column in the data array.
                    cur_col++;
                }
                // Shift to the next column in the csv data set.
                ++col;
            }
        }
        
        return (data);
    }

    private boolean isGarbage(String s) {
        // A data point is garbage if:
        //  1) The data is useless. (negative numbers)
        //  2) The data is text.
        //  3) -
        // 
        // You might want to just use Regular Expressions 
        //  to get some sort of garbage at this rate. :|
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
