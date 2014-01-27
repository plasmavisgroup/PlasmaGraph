/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvg.plasmagraph.views;

import org.pvg.plasmagraph.models.ToolModel;
import org.pvg.plasmagraph.utils.template.Template;

/**
 *
 * @author Andrew Navas
 */
@SuppressWarnings("serial")
public class ToolView extends javax.swing.JPanel {

    /**
     * Creates new form ToolView
     * @param t 
     * @param tool_model 
     */
    public ToolView(ToolModel tool_model, Template t) {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        interpolation_label = new javax.swing.JLabel();
        interpolation_type_combo_box = new javax.swing.JComboBox<String>();
        outlier_search_label = new javax.swing.JLabel();
        interpolation_outlier_separator = new javax.swing.JSeparator();
        interpolation_button = new javax.swing.JButton();
        outlier_action_combo_box = new javax.swing.JComboBox<String>();
        outlier_button = new javax.swing.JButton();

        interpolation_label.setText("Interpolation");

        interpolation_type_combo_box.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Linear", "Polynomial", "Power" }));

        outlier_search_label.setText("Outlier Search");

        interpolation_button.setText("Create Interpolation");

        outlier_action_combo_box.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Warn", "Remove" }));

        outlier_button.setText("Search for Outliers");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(interpolation_outlier_separator)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outlier_button)
                    .addComponent(interpolation_button)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(interpolation_label)
                        .addGap(136, 136, 136)
                        .addComponent(interpolation_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(outlier_search_label)
                        .addGap(136, 136, 136)
                        .addComponent(outlier_action_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(interpolation_label)
                    .addComponent(interpolation_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(interpolation_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(interpolation_outlier_separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outlier_search_label)
                    .addComponent(outlier_action_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(outlier_button)
                .addContainerGap(131, Short.MAX_VALUE))
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton interpolation_button;
    private javax.swing.JLabel interpolation_label;
    private javax.swing.JSeparator interpolation_outlier_separator;
    private javax.swing.JComboBox<String> interpolation_type_combo_box;
    private javax.swing.JComboBox<String> outlier_action_combo_box;
    private javax.swing.JButton outlier_button;
    private javax.swing.JLabel outlier_search_label;
    // End of variables declaration                   
}