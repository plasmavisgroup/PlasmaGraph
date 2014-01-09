package org.plasmagraph.demo;
import au.com.bytecode.opencsv.*;
import java.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Hello World!");
        //CSVReader reader = new CSVReader(new java.io.FileReader("C:\\Users\\DangoMango-Win\\Desktop\\PlasmaGraph\\src\\org\\plasmagraph\\demo\\testdata.csv"));   
       // java.util.List myEntries = reader.readAll(); 
        //String line = myEntries.get(0).toString();
        //System.out.println(line);
        
        CSVReader reader1 = new CSVReader(new java.io.FileReader("C:\\Users\\DangoMango-Win\\Desktop\\PlasmaGraph\\src\\org\\plasmagraph\\demo\\testdata.csv"));
        java.util.List<String[]> myDatas = reader1.readAll();
        String[] lineI = myDatas.get(0);
        for (String[] line : myDatas) {
            for (String value : line) {
                System.out.println(value);
            }
        }
        
    //Demo1 demo = new Demo1("Comparison", "Which operating system are you using?");
    //demo.pack();
    //demo.setVisible(true);

    }
}
