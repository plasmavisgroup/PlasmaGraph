package org.pvg.plasmagraph.utils.data;

import org.pvg.plasmagraph.utils.types.ColumnType;

/**
 * <p>Container for every object in HeaderData's collection.
 * 
 * @author Plasma Visualization Group
 */
public class HeaderColumn {

	private String header_variable_name;
	//private String header_graph_name;
	private ColumnType header_column_type;
	
	/**
	 * 
	 */
	public HeaderColumn () {
		this.header_variable_name = "";
		//this.header_graph_name = "";
		this.header_column_type = ColumnType.NONE;
	}
	
	/**
	 * 
	 * @param variable
	 * @param graph
	 * @param type
	 */
	public HeaderColumn (String variable, ColumnType type) {//String graph, ColumnType type) {
		this.header_variable_name = variable;
		//this.header_graph_name = graph;
		this.header_column_type = type;
	}
	
	public String getVariableName () {
		return (this.header_variable_name);
	}
	
	public String getKey () {
		return (this.getVariableName ());
	}
	
	/*public String getGraphName () {
		return (this.header_graph_name);
	}
	*/
	public ColumnType getColumnType () {
		return (this.header_column_type);
	}
	
	public ColumnType getValue () {
		return (this.getColumnType ());
	}
	
	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		
		sb.append (this.header_variable_name).append ("\n");
		//sb.append (this.header_graph_name).append ("\n");
		sb.append (this.header_column_type.toString ()).append ("\n");
		
		return (sb.toString ());
	}
}
