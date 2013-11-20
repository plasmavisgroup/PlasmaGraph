/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmagraph.sanitizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.io.CSV;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author tako
 */
public class BasicCSV {
    
    CSV csv_file;
    FileReader reader;
    
    public BasicCSV (File f) {
        try {
            this.csv_file = new CSV ();
            this.reader = new FileReader (f);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public CategoryDataset outputBarChartData () {
        CategoryDataset returned_dataset = new DefaultCategoryDataset ();
        try {
            returned_dataset = this.csv_file.readCategoryDataset(this.reader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return (returned_dataset);
    }
    
    public XYDataset outputLineChartData () {
        CategoryDataset returned_dataset = new DefaultCategoryDataset ();
        try {
            returned_dataset = this.csv_file.readCategoryDataset(this.reader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return (toXYDataset (returned_dataset));
    }
    
    public PieDataset outputPieChartData () {
        CategoryDataset returned_dataset = new DefaultCategoryDataset ();
        try {
            returned_dataset = this.csv_file.readCategoryDataset(this.reader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return (toPieDataset (returned_dataset));
    }
    
    public XYDataset outputXYPlotData () {
        CategoryDataset returned_dataset = new DefaultCategoryDataset ();
        try {
            returned_dataset = this.csv_file.readCategoryDataset(this.reader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return (toXYDataset (returned_dataset));
    }

    private XYDataset toXYDataset(CategoryDataset returned_dataset) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private PieDataset toPieDataset(CategoryDataset returned_dataset) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
