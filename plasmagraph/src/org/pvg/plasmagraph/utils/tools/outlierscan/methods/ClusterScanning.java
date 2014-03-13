package org.pvg.plasmagraph.utils.tools.outlierscan.methods;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.pvg.plasmagraph.utils.ExceptionHandler;
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataReference;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.data.GraphPair;
import org.pvg.plasmagraph.utils.data.HeaderData;
import org.pvg.plasmagraph.utils.exceptions.FunctionNotImplementedException;
import org.pvg.plasmagraph.utils.graphs.XYGraph;
import org.pvg.plasmagraph.utils.template.Template;
import org.pvg.plasmagraph.utils.tools.outlierscan.OutlierDistance;
import org.pvg.plasmagraph.utils.tools.outlierscan.distances.MahalanobisDistance;
import org.pvg.plasmagraph.utils.types.ChartType;
import org.pvg.plasmagraph.utils.types.ColumnType;
import org.pvg.plasmagraph.utils.types.OutlierResponse;

public class ClusterScanning implements ScanMethod {

	@Override
	public DataSet scan (HeaderData hd, Template t, GraphPair p) throws FunctionNotImplementedException {

		// Prepare tools for Outlier Scan use.
    	ArrayList <DoublePoint> outlier_array = new ArrayList<> ();
    	List <Cluster<DoublePoint>> dbl_cluster = new ArrayList <> ();
    	
    	// ds will always contain the original data.
    	DataSet ds;
    	
		ds = hd.populateData (p);
		
		// Populate the outlier_array with the correct values.
		populate (outlier_array, ds, p);
		
		// Separate the main values from the outliers, and ask if they'll be removed.
		if (search (dbl_cluster, outlier_array, t)) {
			
			// Add the core data to the ArrayList.
			ds = toDataSet (outlier_array, hd.get (p.getIndex1 ()).getKey (),
					hd.get (p.getIndex2 ()).getKey ());
			
			// Graph as desired.
    		//graph (outlier_array, t, p);
			
		}
    	
		return (ds);
	}

	private void populate (List<DoublePoint> outlier_array, DataSet ds, GraphPair p) {

		// For each line in the DataSet, add the Pair's values to the outlier_array.
		for (int i = 0; (i < ds.getColumnLength ()); ++i) {
			outlier_array.add (new DoublePoint (new double [] {
					(double) ds.get (0).get (i),
					(double) ds.get (1).get (i)
				}));
		}
	}

	/**
	 * Delegates the search function based on the type of outlier_response requested.
	 * Regardless, it will provide the clusters to an external container.
	 * @param graph 
	 * 
	 * @param outlier_array Data container for the DataSet to scan through.
	 * @param t Template object of the PlasmaGraph program that defines graph qualities.
	 */
	private static boolean search (List <Cluster<DoublePoint>> dbl_cluster, 
			ArrayList<DoublePoint> outlier_array, Template t) {
		
		// Determine the clusters of the data with a maximum distance provided by MahalanobisDistance!
		OutlierDistance d = new MahalanobisDistance ();
		DBSCANClusterer <DoublePoint> outlier_clustering = 
				new DBSCANClusterer <> (d.distance (outlier_array), 1);
		dbl_cluster = outlier_clustering.cluster (outlier_array);
		
		// Determine if the user wants to be told of the outliers.
		if (t.getOutlierResponse () == OutlierResponse.WARN) {
			return (warn (dbl_cluster, outlier_array, d.getDistanceType (), true));
		} else {
			return (warn (dbl_cluster, outlier_array, d.getDistanceType (), false));
		}
	}
	
	// "scan" support functions
	/**
	 * Shows a modal pane explaining which points are outliers, if any!
	 * 
	 * @param dbl_cluster Data container with all the possible data points!
	 * @param outlier_array 
	 * @param distance_type The type of distance being used as provided by
	 * the OutlierDistance class.
	 * @param ask Boolean stating whether the user will be prompted if they
	 * want to remove the outliers or not.
	 */
	private static boolean warn (List<Cluster<DoublePoint>> dbl_cluster,
			ArrayList<DoublePoint> outlier_array, String distance_type, boolean ask) {
		
		// Prepare the outlier container.
		List <DoublePoint> outliers = getOutliers (dbl_cluster);
		
		// Generate the message.
		StringBuilder sb = new StringBuilder ();
		
		sb.append ("Distance Type used: ")
				.append (distance_type)
				.append ("\n");
		sb.append ("Total number of points: ")
				.append (dbl_cluster.size ())
				.append ("\n");
		sb.append ("Number of outliers found: ")
				.append (outliers.size ())
				.append ("\n");
		sb.append ("Outliers found: ")
				.append ("\n");
		int count = 0;
		for (DoublePoint p : outliers) {
			
			sb.append ("[")
				.append (p.getPoint ()[0])
				.append (", ")
				.append (p.getPoint ()[1])
				.append ("] - ");
			
			if (count == 7) {
				sb.append ("\n");
			}
			++count;
		}
		
		JOptionPane.showMessageDialog (null, sb.toString (), 
				"Outlier Scan: Results", JOptionPane.INFORMATION_MESSAGE);
		
		// Determine if User Validation will be performed, and return that.
		if (ask) {
			String message = "Do you want to graph this data?"; String title = "Graph?";
			if (JOptionPane.showConfirmDialog (null, message, title, JOptionPane.YES_NO_OPTION)
					== JOptionPane.YES_OPTION) {
				return (remove (outliers, outlier_array));
			} else {
				return (false);
			}
		} else {
			return (remove (outliers, outlier_array));
		}
	}

	/**
	 * Provides a list of outliers from the total group of clustered data.
	 * Assumes largest concentration of points is the line/curve, and all other
	 * clusters are outliers.
	 * 
	 * @param dbl_cluster The collection of clusters created by the DBSCAN procedure.
	 * @return A list of points that are outliers from the "dbl_cluster" input.
	 */
	private static List <DoublePoint> getOutliers (List<Cluster<DoublePoint>> dbl_cluster) {
		
		// Prepare the data containers.
		Cluster <DoublePoint> prime_cluster = new Cluster <> ();
		List <DoublePoint> outliers = new ArrayList <> ();
		
		// Find 
		for (Cluster<DoublePoint> cluster : dbl_cluster) {
			
			// If the old main cluster is smaller than the one we're holding.
			if (prime_cluster.getPoints ().size () < cluster.getPoints ().size ()) {
				
				// Add the old main cluster to the outliers and set the main cluster to the one we're holding.
				outliers.addAll (prime_cluster.getPoints ());
				prime_cluster = cluster;
				
			} else {
				
				// Add the cluster we're holding to the outlier list.
				outliers.addAll (cluster.getPoints ());
				
			}
		}
		
		return (outliers);
	}
	
	/**
	 * Removes the outliers from the primary array.
	 * 
	 * @param outliers List of outliers.
	 * @param outlier_array List of all points.
	 * @return Boolean declaring the success or failure of the removal procedure.
	 */
	private static boolean remove (List<DoublePoint> outliers,
			ArrayList<DoublePoint> outlier_array) {
		
		boolean b = true;
		// Checks all points in outliers and removes them all from "outlier_array".
		for (DoublePoint d : outliers) {
			if (outlier_array.contains (d) && b) {
				b = outlier_array.remove (d);
			} else {
				outlier_array.remove (d);
			}
		}
		
		return (b);
	}

	/**
	 * Turns an ArrayList of DoublePoints into a DataSet.
	 * 
	 * @param outlier_array ArrayList of data obtained.
	 * @return A DataSet containing said data.
	 */
	private DataSet toDataSet (ArrayList <DoublePoint> outlier_array,
			String column_name1, String column_name2) {

		// Prepare DataSet and DataColumns.
		DataSet ds = new DataSet (false);
		DataColumn<Double> dc1 = new DataColumn<> (column_name1, ColumnType.DOUBLE);
		DataColumn<Double> dc2 = new DataColumn<> (column_name2, ColumnType.DOUBLE);
		
		// Populate DataColumns
		for (DoublePoint dp : outlier_array) {
			
			dc1.add (new Double (dp.getPoint ()[0]));
			dc2.add (new Double (dp.getPoint ()[1]));
			
		}
		
		// Add DataColumns to DataSet.
		ds.add (dc1); ds.add (dc2);
		
		// Return DataSet
		return (ds);
	}
}