package org.pvg.plasmagraph.utils.tools.outlierscan.methods;

import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.template.Template;

public interface ScanMethod {
	
	public void scan (HeaderData hd, Template t, DataReference dr);
	//private void populate (ArrayList<E> outlier_array, DataSet ds, Pair p);
	//private boolean search (ArrayList<E> outlier_array, Template t, DataReference dr);
	//private void graph (ArrayList<E> outlier_array, Template t);

}
