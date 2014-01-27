/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvg.plasmagraph.views;

import org.pvg.plasmagraph.models.DataSetModel;
import org.pvg.plasmagraph.utils.data.DataColumn;
import org.pvg.plasmagraph.utils.data.DataSet;
import org.pvg.plasmagraph.utils.template.Template;

/**
 *
 * @author Andrew Navas
 */
@SuppressWarnings("serial")
public class DataSetView extends javax.swing.JPanel {

    /**
     * Creates new form DataSetView
     * @param t 
     * @param data_model 
     * @param ds 
     */
    public DataSetView(DataSetModel data_model, Template t, DataSet ds) {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        available_datasets_pane = new javax.swing.JScrollPane();
        available_datasets_list = new javax.swing.JList<DataColumn>();
        selected_datasets_pane = new javax.swing.JScrollPane();
        selected_datasets_list = new javax.swing.JList<DataSet>();
        chart_type_combo_box = new javax.swing.JComboBox<String>();
        chart_type_label = new javax.swing.JLabel();
        add_button = new javax.swing.JButton();
        pair_button = new javax.swing.JButton();
        remove_button = new javax.swing.JButton();

        available_datasets_list.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        available_datasets_pane.setViewportView(available_datasets_list);

        selected_datasets_pane.setViewportView(selected_datasets_list);

        chart_type_combo_box.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Bar Graph", "XY Graph", "Pie Graph", "Line Graph" }));

        chart_type_label.setText("Chart Type");

        add_button.setText("Add");

        pair_button.setText("Pair");

        remove_button.setText("Remove");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chart_type_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chart_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(add_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pair_button, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(available_datasets_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(remove_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selected_datasets_pane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chart_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chart_type_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selected_datasets_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(available_datasets_pane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_button)
                    .addComponent(pair_button)
                    .addComponent(remove_button))
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton add_button;
    private javax.swing.JList<DataColumn> available_datasets_list;
    private javax.swing.JScrollPane available_datasets_pane;
    private javax.swing.JComboBox<String> chart_type_combo_box;
    private javax.swing.JLabel chart_type_label;
    private javax.swing.JButton pair_button;
    private javax.swing.JButton remove_button;
    private javax.swing.JList<DataSet> selected_datasets_list;
    private javax.swing.JScrollPane selected_datasets_pane;
    // End of variables declaration                   
}