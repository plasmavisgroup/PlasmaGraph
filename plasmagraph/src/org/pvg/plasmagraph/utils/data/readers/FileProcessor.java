package org.pvg.plasmagraph.utils.data.readers;

import java.io.File;

import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;

/**
 * <p>Interface for all classes that manage files for the PlasmaGraph program.
 * 
 * <p>A class that inherits this interface's methods is designed to handle the
 * pulling of data from a class of data file (.mat, .csv, etc). The purpose of
 * this type of class is to pull out data and format it into a HeaderData or
 * DataSet object; refer to each of the respective methods in this interface
 * for more details.
 * 
 * <p>It is highly-suggested that any class that inherits from this interface
 * should implement separate private methods for the verification of valid data,
 * column sizes, removal of unusable columns, and any other important subroutine
 * that these main methods describe. (Refer to the MatlabProcessor class for more
 * details.)
 * 
 * <p>Finally, the convention for inheriting classes is to add the suffix "Processor"
 * to the type of file it reads. (MatlabProcessor, CSVProcessor, etc.)
 * 
 * @author Plasma Visualization Group
 */
public interface FileProcessor {

	/**
	 * <p>Opens the file the Class is provided initially, 
	 * and places said data into a data container for use in any other methods of this class.
	 * 
	 * <p>This method automatically handles any IOExceptions that may occur while reading the file.
	 */
	public void read ();
	//public void write ();
	
	/**
	 * <p>Takes the data provided by the "read ()" method and converts it to 
	 * a valid HeaderData object located in the "hd" parameter provided.
	 * This occurs only if the resulting data is formatted correctly 
	 * (Columns of equal lengths), has enough data to create graphs from it 
	 * (Needs two or more columns / fields), and if the columns that exist
	 * have at least some valid columns (Columns with at least three points of data).
	 * 
	 * <p>Please note the following: 
	 * <ul>This method checks for the existence of a column named "Header" or "header" 
	 * before populating the HeaderData object. This is to know if there are special names 
	 * that the user wishes to use for the columns automatically. If such a column exists, 
	 * the system will take the column and populate the rest of the columns with said name. 
	 * Said "Header" column will be removed from the data container during this process.</ul>
	 * <ul>This method also checks if the file has been read before processing, 
	 * and calls this class' "read ()" method beforehand if needed.</ul>
	 * 
	 * @param hd HeaderData object location where the created HeaderData will be placed.
	 * @return A boolean describing the success or failure of this operation. Failure only 
	 * results when the data file is invalid due to irregular column sizes or lack of 
	 * graphable columns.
	 * @throws Exception If the data isn't formatted correctly or if said operation isn't
	 * available for the class yet.
	 */
	public boolean getHeaders (HeaderData hd) throws Exception;
	
	/**
	 * <p>Takes the data provided by the "read ()" method, as well as valid HeaderData
	 * and GraphPair objects and converts it into a valid DataSet object. Said object contains
	 * the data contained in the columns provided by the GraphPair object, and uses the HeaderData's
	 * ColumnTypes and column names to verify the validity of said columns and the data they
	 * contains.
	 * 
	 * <p>Please note the following:
	 * <ul>This method works with both un-grouped and grouped graphs.</ul>
	 * <ul>This method also checks if the file has been read before processing, 
	 * and calls this class' "read ()" method beforehand if needed.</ul>
	 * 
	 * 
	 * @param ds DataSet object location where the created DataSet will be placed.
	 * @param p GraphPair object describing the columns that will be featured in the graph.
	 * @param hd HeaderData object describing the file's columns' names and types.
	 * @throws Exception If the data isn't formatted correctly or if said operation isn't
	 * available for the class yet.
	 */
	public void toDataSet (DataSet ds, GraphPair p, HeaderData hd) throws Exception;
	//public void fromDataSet (DataSet ds) throws Exception;
	
	/**
	 * Getter method. Provides the File object being used in this object.
	 * 
	 * @return The File object contained in this object.
	 */
	public File getFile ();
}
