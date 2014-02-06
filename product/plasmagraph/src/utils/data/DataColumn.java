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
public class DataColumn {
    private String name;
    private String value_type;
    private ArrayList <Object> values;
    
/** 
 * Class constructor.
 */
    public DataColumn(String name, String value_type){
        this.name = name;
        this.value_type = value_type;
    }
    
    public DataColumn(){
        this.name = null;
        this.value_type = null;
    }

/** 
 * Setters & Getters.
 */
    public void setName(String name){
        this.name = name;
    }
    
    public void setType(String value_type){
        this.value_type = value_type;
    }
    
    public void setValues(ArrayList values){
        this.values = values;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getType(){
        return this.value_type;
    }
    
    public ArrayList getValues(){
        return this.values;
    }  

/** 
 * Add & Remove.
 */
    public boolean add(Object datum){
        return this.values.add(datum);
    }
    
    public boolean remove(int index){
        this.values.remove(index);
        return true;
    }
    
    public boolean remove(Object datum){
        return this.values.remove(datum);
    }

/** 
 * .
 */
    public boolean cleanData(){
        return true;
    }
    
    public Object toPieDataset(){
        return true;
    }  
    
    public boolean isEmpty(){
        return (this.values == null);
    }
}
