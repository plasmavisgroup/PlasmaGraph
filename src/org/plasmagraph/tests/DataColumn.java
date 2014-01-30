/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmagraph.tests;

//import java.*;

import java.util.ArrayList;


/**
 *
 * @author DangoMango-Win
 */
public class DataColumn {
    
    private String name;
    private String value_type;
    private ArrayList values;
    
/** 
 * Class constructor.
 */
    public DataColumn(String name, String value_type, ArrayList values){
        this.name = name;
        this.value_type = value_type;
        this.values = values;
    }

/** 
 * Setters & Getters.
 */
    public void setName(String name){
        this.name = name;
    }
    
    public void setValueType(String value_type){
        this.value_type = value_type;
    }
    
    public void setValues(ArrayList values){
        this.values = values;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getValueType(){
        return this.value_type;
    }
    
    public ArrayList getValues(){
        return this.values;
    }
    
    
}
