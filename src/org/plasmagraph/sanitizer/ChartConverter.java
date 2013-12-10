package org.plasmagraph.sanitizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.CategoryToPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.MatrixSeries;
import org.jfree.data.xy.MatrixSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYZDataset;

public class ChartConverter {
	// Variables
	/** List of row names for "values". */
	private ArrayList<String> row_list;
	/** List of column names for "values". */
	private ArrayList<String> column_list;
	/** Row-by-Column matrix of data values.  */
	private ArrayList<ArrayList<Double>> values;
	
	// Constructors
	/**
	 * Creates a new ChartConverter object, holding all data relevant to making Datasets.
	 * @param rows - List of row names for "values".
	 * @param columns - List of column names for "values".
	 * @param values - Row-by-Column matrix of data values.
	 */
	public ChartConverter (ArrayList<String> rows, 
			ArrayList<String> columns, ArrayList<ArrayList<Double>> values) {
		this.row_list = rows;
		this.column_list = columns;
		this.values = values;
	}
	
	// Methods
	/**
	 * Uses the data provided to create and populate a CategoryDataset.
	 * @return A populated DefaultCategoryDataset.
	 */
	public DefaultCategoryDataset toCategoryDataset () {
		// TODO Auto-generated method stub
		DefaultCategoryDataset set = new DefaultCategoryDataset ();
		for (int i = 0; i < values.size(); ++i) {
			for (int j = 0; j < column_list.size(); ++j) {
				set.addValue((Number) values.get(i).get(j), row_list.get(i), column_list.get(j));
			}
		}
		
		return (set);
	}
	
	/**
	 * Uses the data provided to create and populate an XYSeries.
	 * @param column_1 The first column to extract data from. Considered the X value pair column.
	 * @param column_2 The second column to extract data from. Considered the Y value pair column.
	 * @return A populated XYSeries.
	 */
	public XYSeries toXYSeries (int column_1, int column_2) {
		Random r = new Random ();
		XYSeries set = new XYSeries (r.nextLong());
		
		for (int i = 0; i < row_list.size(); ++i) {
			set.add(values.get(i).get(column_1), values.get(i).get(column_2));
		}
		
		return (set);
	}
	
	/**
	 * Uses the data provided to create and populate an XYDataset.
	 * @param column_1 The first column to extract data from. Considered the X value pair column.
	 * @param column_2 The second column to extract data from. Considered the Y value pair column.
	 * @return A populated XYDataset.
	 */
	public DefaultXYDataset toXYDataset (int column_1, int column_2) {
		DefaultXYDataset set = new DefaultXYDataset ();
		XYSeries series = this.toXYSeries(column_1, column_2);
		
		set.addSeries(series.getKey(), series.toArray());
		
		return (set);
	}

	/**
	 * Uses the data provided to create and populate an XYSeriesCollection.
	 * @param column_1 The first column to extract data from. Considered the X value pair column.
	 * @param column_2 The second column to extract data from. Considered the Y value pair column.
	 * @return A populated XYSeriesCollection.
	 */
	public XYSeriesCollection toXYSeriesCollection (int column_1, int column_2) {
		return (new XYSeriesCollection (this.toXYSeries(column_1, column_2)));
    }

	/**
	 * Uses the data provided to create and populate a PieDataset.
	 * @param by_column Truth value that determines whether the data will be evaluated by columns (true) or by rows (false). 
	 * @param index The index of the row (if by_column is true) or column  (if by_column is false) to evaluate.
	 * @return A populated CategoryToPieDataset.
	 */
	public CategoryToPieDataset toPieDataset (boolean by_column, int index) {
		org.jfree.util.TableOrder order;
		if (by_column) { order = org.jfree.util.TableOrder.BY_COLUMN; }
		else { order = org.jfree.util.TableOrder.BY_ROW; }
		
		return (new CategoryToPieDataset (this.toCategoryDataset (), order, index));
    }
	
	/**
	 * Uses the data provided to create and populate a KeyedValues dataset.
	 * @param by_column Truth value that determines whether the data will be evaluated by columns (true) or by rows (false). 
	 * @param index The index of the row (if by_column is true) or column  (if by_column is false) to evaluate.
	 * @return A populated DefaultKeyedValues dataset.
	 */
	public KeyedValues toKeyedValues (boolean by_column, int index) {
		DefaultKeyedValues set = new DefaultKeyedValues ();
		
		if (by_column) { // One Row, all the Valid Columns.
			for (int i = 0; i < this.column_list.size(); ++i) {
				set.addValue(this.column_list.get(i), this.values.get(index).get(i));
			}
		} else { // All the Valid Rows, one Column.
			for (int i = 0; i < this.row_list.size(); ++i) {
				set.addValue(this.row_list.get(i), this.values.get(i).get(index));
			}
		}
		
		return (set);
	}
	
	/**
	 * Uses the data provided to create and populate a BoxAndWhiskerCategoryDataset.
	 * @param column_key The single column key to add to the dataset.
	 * @return A populated DefaultBoxAndWhiskerCategoryDataset.
	 */
	public BoxAndWhiskerCategoryDataset toBoxAndWhisker (String column_key) {
		DefaultBoxAndWhiskerCategoryDataset set = new DefaultBoxAndWhiskerCategoryDataset ();
		
		for (int i = 0; i < this.values.size(); ++i) {
			set.add(this.values.get(i), this.row_list.get(i), 
					this.column_list.get(this.column_list.indexOf(column_key)));
		}
		
		return (set);
	}
	
	/**
	 * Uses the data provided to create and populate a BoxAndWhiskerCategoryDataset.
	 * @param column_key An array of strings containing all the column keys that will be added to the dataset.
	 * @return A populated DefaultBoxAndWhiskerCategoryDataset.
	 */
	public BoxAndWhiskerCategoryDataset toBoxAndWhisker (String column_key []) {
		DefaultBoxAndWhiskerCategoryDataset set = new DefaultBoxAndWhiskerCategoryDataset ();
		
		for (int i = 0; i < this.values.size(); ++i) {
			set.add(this.values.get(i), this.row_list.get(i), 
					this.column_list.get(this.column_list.indexOf(column_key[i])));
		}
		
		return (set);
	}
	
	// Less than useful data sets...
	/**
	 * Uses the data provided to create and populate an XYZDataset via the MatrixSeriesCollection class.
	 * This method uses all columns.
	 * @param name The name of the dataset.
	 * @return A populated MatrixSeriesCollection dataset.
	 */
	public XYZDataset toMatrixCollection (String name) {
		return (new MatrixSeriesCollection (this.toMatrixSeries(name)));
	}
	
	// XYZDataset - MatrixCollection - Variable Size
	/**
	 * Uses the data provided to create and populate an XYZDataset via the MatrixSeriesCollection class.
	 * This method uses only the selected columns.
	 * @param name The name of the dataset.
	 * @param selected_columns An array of ints, specifying each of the columns to add to the MatrixSeriesCollection.
	 * @return A populated MatrixSeriesCollection dataset.
	 */
	public XYZDataset toMatrixCollection (String name, int selected_columns []) {
		return (new MatrixSeriesCollection (this.toMatrixSeries(name, selected_columns)));
	}
	
	/**
	 * Uses the data provided to create and populate an XYZDataset via the DefaultXYZDataset class.
	 * @return A populated DefaultXYZDataset.
	 */
	public XYZDataset toXYZDataset () {
		DefaultXYZDataset set = new DefaultXYZDataset ();
		
		double data [][] = this.toArray (this.values);
		
		Random r = new Random ();
		set.addSeries(r.nextLong(), data);
		
		return (set);
	}

	// IntervalXYDataset
	/**
	 * Uses the data provided to create and populate an IntervalXYDataset.
	 * @param column_1 The first column to add to the dataset. Considered the X data value pair.
	 * @param column_2 The second column to add to the dataset. Considered the Y data value pair.
	 * @return A populated DefaultTableXYDataset.
	 */
	public IntervalXYDataset toTableXYDataset (int column_1, int column_2) {
		DefaultTableXYDataset set = new DefaultTableXYDataset ();
		
		set.addSeries(this.toXYSeries(column_1, column_2));
		
		return (set);
	}
	
	// Private Helpers
	/**
	 * Uses the data provided to create and populate a MatrixSeries.
	 * @param name Name of the dataset.
	 * @return A populated MatrixSeries dataset.
	 */
	private MatrixSeries toMatrixSeries (String name) {
		MatrixSeries series = new MatrixSeries (name, this.row_list.size(), this.column_list.size());
		
		// Fill in the MatrixSeries with the data in Values.
		for (int i = 0; i < this.values.size(); ++i) {
			for (int j = 0; j < this.values.get(i).size(); ++j) {
				series.update(i, j, this.values.get(i).get(j));
			}
		}
		
		return (series);
	}
	
	/**
	 * Uses the data provided to create and populate a MatrixSeries dataset.
	 * @param name Name of the dataset.
	 * @param selected_columns An array of ints specifying the columns to add to the dataset.
	 * @return A populated MatrixSeries dataset.
	 */
	private MatrixSeries toMatrixSeries (String name, int selected_columns []) {
		MatrixSeries series = new MatrixSeries (name, this.row_list.size(), selected_columns.length);
		
		// Make sure the columns selected are in ascending order.
		Arrays.sort(selected_columns);
		
		// Fill in the MatrixSeries with the data in Values.
		for (int i = 0; i < this.values.size(); ++i) {
			for (int k = 0, j = selected_columns[k]; j < selected_columns.length; j = selected_columns[++k]) {
				series.update(i, j, this.values.get(i).get(j));
			}
		}
		
		return (series);
	}
	
	/**
	 * Converts an ArrayList of ArrayLists of doubles into a 2-dimensional array of doubles.
	 * Useful for a few conversions, but not many.
	 * @param values2 The ArrayList of ArrayLists to convert. (Basically, the "values" class variable.)
	 * @return A new 2-dimensional array of doubles.
	 */
	private double[][] toArray(ArrayList<ArrayList<Double>> values2) {
		double data [][] = new double [this.row_list.size()][this.column_list.size()]; 
		for (int i = 0; i < this.row_list.size(); ++i) {
			for (int j = 0; j < this.column_list.size(); ++j) {
				data[i][j] = this.values.get(i).get(j);
			}
		}
		
		return (data);
	}
}