/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com.jmatio.types.MLArray;

/**
 *
 * @author DangoMango-Win
 */
public class DataColumn implements Iterable<Object>, Iterator<Object>{
    private String name;
    private int value_type;
    private ArrayList values;
    
   /** Position of Iterator object; 
    * used for the implementation of Iterator and Iterable. */
    private int position = 0;
    
/** 
 * Class constructor.
 */
    public DataColumn (int column_type) {
        if (column_type == MLArray.mxDOUBLE_CLASS) {
                values = new ArrayList<Double> ();
                value_type = MLArray.mxDOUBLE_CLASS;
        } else if (column_type == MLArray.mxCHAR_CLASS) {
                values = new ArrayList<String> ();
                value_type = MLArray.mxCHAR_CLASS;
        }
        name = "empty";
    }
	
    public DataColumn (int column_type, String n) {
        if (column_type == MLArray.mxDOUBLE_CLASS) {
                values = new ArrayList<Double> ();
                value_type = MLArray.mxDOUBLE_CLASS;
        } else if (column_type == MLArray.mxCHAR_CLASS) {
                values = new ArrayList<String> ();
                value_type = MLArray.mxCHAR_CLASS;
        }
        name = n;
    }

/** 
 * Setters & Getters.
 */
    public void setName(String name){
        this.name = name;
    }
    
    public void setType(int value_type){
        this.value_type = value_type;
    }
    
    public void setValues(ArrayList values){
        this.values = values;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getType(){
        return this.value_type;
    }
    
    public ArrayList getValues(){
        return this.values;
    }  

/** 
 * Add & Remove.
 
    public boolean add(Object datum){
        System.out.println(datum.toString());
        return this.values.add(datum);
    }
    */
    public boolean add(Double datum){
        return this.values.add(datum);
    }
    
    public boolean add(String datum){
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
    
    @Override
    public String toString () {
            return (this.values.toString ());
    }

    // Iterator / Iterable methods.
    @Override
    public boolean hasNext () {
            return (position < values.size ());
    }

    @Override
    public Object next () {
            if (position == values.size ()) {
                    throw new NoSuchElementException ();
            }
            return (values.get (++position));
    }

    @Override
    public void remove () {
            this.values.remove (position--);
    }

    @Override
    public Iterator<Object> iterator () {
            this.position = 0;
            return (this);
    }

    void append(DataColumn column) {
        if(this.values.isEmpty()){
            this.values = column.getValues();
        }else{
            for(int i = 0; i < this.values.size(); i++){
                this.values.add(column.getValues().get(i).toString());
            } 
        }               
    }
}
