package org.pvg.plasmagraph.utils.data.readers;

import java.io.File;

import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;

public interface FileProcessor {

	public void read ();
	public void write ();
	public void write (File f);
	
	public boolean getHeaders (HeaderData hd) throws Exception;
	
	public void toDataSet (DataSet ds, GraphPair p, HeaderData hd) throws Exception;
	//public void fromDataSet (DataSet ds) throws Exception;
	
	public File getFile ();
	public void setFile (File new_file);
	
}
