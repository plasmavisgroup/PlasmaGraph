package org.plasmagraph.sanitizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.io.CSV;

// Uses: http://www.jfree.org/jfreechart/api/javadoc/org/jfree/data/io/CSV.html
// Uses: http://docs.oracle.com/javase/6/docs/api/java/io/FileReader.html
// Uses: http://www.jfree.org/jfreechart/api/javadoc/org/jfree/data/category/DefaultCategoryDataset.html

public class CSVExtractor {

	File csv;
	
	public CSVExtractor (File f) {
		this.csv = f;
	}
	
	public DefaultCategoryDataset extract () {
		DefaultCategoryDataset set = new DefaultCategoryDataset ();
		try {
			FileReader reader = new FileReader (this.csv);
			CSV c = new CSV ();
			set = (DefaultCategoryDataset) c.readCategoryDataset (reader);
		} catch (FileNotFoundException e) {
			// Catch for File "f" being opened.
			// TODO Properly deal with this exception.
			e.printStackTrace();
		} catch (IOException e) {
			// Catch for reading data from the File "f".
			// TODO Properly deal with this exception.
			e.printStackTrace();
		}
		
		return (set);
	}
}
