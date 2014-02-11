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
    
    ArrayList <DataColumn> values;
    
    public DataSet DataSet(){
        this.values = new <DataColumn> ArrayList();
        return this;
    }
    
    public boolean add(DataColumn column){
        this.values.add(column);
        return true;
    }
    
    public boolean remove(int index){
        this.values.remove(index);
        return true;
    }    
}
