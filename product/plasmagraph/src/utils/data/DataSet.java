/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.data;

import java.util.ArrayList;


/**
 *
 * @author DangoMango-Win
 */
public class DataSet {
    
    ArrayList <DataColumn> columns;
    
    public DataSet DataSet(){
        this.columns = new <DataColumn> ArrayList();
        return this;
    }
    
    public boolean add(DataColumn column){
        this.columns.add(column);
        return true;
    }
    

    
    public void add(DataColumn column, int index){
        
        // System.out.println(column.toString());
        DataColumn this_column = this.columns.get(index);
        this_column.append(column);
        this.columns.set(index, this_column);        
    }
    
    public boolean remove(int index){
        this.columns.remove(index);
        return true;
    }    
}
