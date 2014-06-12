package org.pvg.plasmagraph.utils.tools.outlierscan.methods;

import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.data.MessageLog;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
import org.pvg.plasmagraph.utils.template.Template;

public interface ScanMethod {
	
	public DataSet scan (HeaderData hd, Template t, GraphPair p, MessageLog ml) throws FunctionNotImplementedException;
	//private void populate (ArrayList<E> outlier_array, DataSet ds, Pair p);
	//private boolean search (ArrayList<E> outlier_array, Template t, DataReference dr);
	//private void graph (ArrayList<E> outlier_array, Template t);

}
