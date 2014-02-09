/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvg.plasmagraph.utils.data;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * Isolated JFrame designed to allow the user to easily modify a
 * DataFilter. Does not follow the Model-View-Controller GUI pattern
 * the rest of the program follows due to the lack of required
 * flexibility.
 * 
 * @author Gerardo A. Navas Morales
 */
@SuppressWarnings("serial")
public class DataFilterWindow extends javax.swing.JFrame {
	// Externally-controlled variables
	/** Reference variable passed by its parent. */
	private DataFilter df;

    /**
     * Creates new form DataFilterFrame
     */
    public DataFilterWindow (DataFilter df_reference) {
    	// Initialize private variables.
    	df = df_reference;
    	
    	// Initialize visual components.
        initComponents();
    }
    
    // Created by Gerardo A. Navas Morales.
    /**
     * Creates a new DefaultListModel of type String for the purposes of this class' JList.
     * @return A DefaultListModel <String> object for this object.
     */
    private DefaultListModel <String> initialize_model () {
        DefaultListModel <String> m = new javax.swing.DefaultListModel<String>();
        
        for (String s : df) {
            m.addElement (s);
        }
        
        return (m);
    }

    /**
     * Adds a String to filter from any data being used.
     * @param evt The ActionEvent that triggered this method.
     */
    private void add_buttonActionPerformed (java.awt.event.ActionEvent evt) {
        // If the text box isn't empty, take that data and put it into the list!
        if (this.data_filter_text_box.getText().isEmpty()) {
            
            // Otherwise, throw an error window explaining what went wrong.
            // TODO: Move this to ExceptionHandler?
            JOptionPane.showMessageDialog (rootPane, "No word or phrase found.\n"
                    + "Please type a word or phrase to filter before pressing the \"Add\" Button.",
                    "Error - Empty Input.", JOptionPane.ERROR_MESSAGE);
            
        } else if (this.data_filter_list_model.contains (this.data_filter_text_box.getText ())) {
            
            // Otherwise, throw an error window explaining what went wrong.
            // TODO: Move this to ExceptionHandler?
            JOptionPane.showMessageDialog (rootPane, "The new word or phrase to be added already exists in the list.\n"
                    + "Please type a new word or phrase to filter before pressing the \"Add\" Button.",
                    "Error - Duplicate Word / Phrase.", JOptionPane.ERROR_MESSAGE);
            
        } else {
            
            String s = this.data_filter_text_box.getText();
            data_filter_list_model.addElement(s);
            
            // Also add it to the DataFilter object!
            df.add (s);
        }
    }                                          

    /**
     * Removes either an object selected on the list, or an object whose name is in
     * the data_filter_text_box Text Field in this window; it does not do both at the
     * same time. They are checked in that order.
     * 
     * @param evt The ActionEvent that triggered this method.
     */
    private void remove_buttonActionPerformed (java.awt.event.ActionEvent evt) {                                   
        // Did they select something from the list to remove?
        int index = this.data_filter_list.getSelectedIndex ();
        if (index != -1) {
            
            data_filter_list_model.remove (index);
            // Also remove it from the DataFilter object!
            df.remove (index);
            
        }
        // Otherwise, if the text box isn't empty, take that data and put it into the list!
        else if (!this.data_filter_text_box.getText().isEmpty ()) {
            
            String s = this.data_filter_text_box.getText ();
            if (data_filter_list_model.isEmpty ()) {
                JOptionPane.showMessageDialog(rootPane, "The list is empty!\n",
                        "Error - Empty List.", JOptionPane.ERROR_MESSAGE);
            }
            else if (!data_filter_list_model.removeElement (s)) {
                
                // Otherwise, throw an error window explaining what went wrong.
                // TODO: Move this to ExceptionHandler?
                JOptionPane.showMessageDialog(rootPane, "The word selected does not exist.\n"
                        + "Please type a filtered word before pressing the \"Remove\" Button.",
                        "Error - String not found.", JOptionPane.ERROR_MESSAGE);
                
            } else {
                // Also remove it from the DataFilter object!
                df.remove (s);
            }
            
        } 
        // Otherwise, throw an error window explaining what went wrong.
        else {
            // TODO: Move this to ExceptionHandler?
            JOptionPane.showMessageDialog(rootPane, "There was no data to remove from the data filter.\n"
                    + "Please type the name of a  before pressing the \"Add\" Button.",
                    "Error - No data to add to list.", JOptionPane.ERROR_MESSAGE);
            
        }
    }

    // Automatically-generated by NetBeans IDE.
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        this.setTitle ("Edit Data Filter Window");

    	data_filter_list_model = initialize_model ();
        data_filter_text_box = new javax.swing.JTextField();
        view_input_separator = new javax.swing.JSeparator();
        data_filter_scroll_pane = new javax.swing.JScrollPane();
        data_filter_list = new javax.swing.JList<String>(data_filter_list_model);
        add_button = new javax.swing.JButton();
        remove_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        data_filter_text_box.setToolTipText("Enter text to add or remove to the Data Filter.");
        
        // Set the selection mode for the list to only a single option.
        data_filter_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        data_filter_list.setToolTipText("");

        
        data_filter_scroll_pane.setViewportView(data_filter_list);

        add_button.setText("Add to Filter");
        add_button.setActionCommand("add");
        add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_buttonActionPerformed(evt);
            }
        });

        remove_button.setText("Remove from Filter");
        remove_button.setActionCommand("remove");
        remove_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(view_input_separator)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(remove_button, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(data_filter_scroll_pane)
                    .addComponent(data_filter_text_box))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(data_filter_scroll_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(view_input_separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(data_filter_text_box, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_button)
                    .addComponent(remove_button))
                .addGap(5, 5, 5))
        );

        data_filter_text_box.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>                        
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton add_button;
    private javax.swing.DefaultListModel<String> data_filter_list_model;
    private javax.swing.JList<String> data_filter_list;
    private javax.swing.JScrollPane data_filter_scroll_pane;
    private javax.swing.JTextField data_filter_text_box;
    private javax.swing.JButton remove_button;
    private javax.swing.JSeparator view_input_separator;
    // End of variables declaration                   
}
