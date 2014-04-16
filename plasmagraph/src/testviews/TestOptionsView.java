/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testviews;

/**
 *
 * @author tako
 */
public class TestOptionsView extends javax.swing.JPanel {

    /**
     * Creates new form TestOptionsView
     */
    public TestOptionsView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outlier_response_label = new javax.swing.JLabel();
        outlier_response_combo_box = new javax.swing.JComboBox();
        interpolation_type_label = new javax.swing.JLabel();
        interpolation_type_combo_box = new javax.swing.JComboBox();
        graph_button = new javax.swing.JButton();
        graph_separator = new javax.swing.JSeparator();
        outlier_distance_type_label = new javax.swing.JLabel();
        outlier_distance_type_combo_box = new javax.swing.JComboBox();
        maximum_distance_label = new javax.swing.JLabel();
        maximum_distance_text_box = new javax.swing.JTextField();

        outlier_response_label.setText("Outlier Scanning Response");

        outlier_response_combo_box.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Warn", "Remove" }));

        interpolation_type_label.setText("Interpolation Type");

        interpolation_type_combo_box.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Linear", "Quadratic", "Cubic", "Quartic", "Spline" }));

        graph_button.setText("Graph");

        outlier_distance_type_label.setText("Outlier Distance Type");

        outlier_distance_type_combo_box.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));

        maximum_distance_label.setText("Manual Maximum Distance");

        maximum_distance_text_box.setText("0.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graph_separator)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maximum_distance_text_box)
                            .addComponent(outlier_response_combo_box, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(graph_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(interpolation_type_combo_box, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(outlier_distance_type_combo_box, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(outlier_distance_type_label))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(outlier_response_label)
                                    .addComponent(maximum_distance_label))))
                        .addGap(46, 46, 46)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(interpolation_type_label)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outlier_distance_type_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outlier_distance_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outlier_response_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(outlier_response_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(maximum_distance_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(maximum_distance_text_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(graph_separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(interpolation_type_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(interpolation_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(graph_button)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton graph_button;
    private javax.swing.JSeparator graph_separator;
    private javax.swing.JComboBox interpolation_type_combo_box;
    private javax.swing.JLabel interpolation_type_label;
    private javax.swing.JLabel maximum_distance_label;
    private javax.swing.JTextField maximum_distance_text_box;
    private javax.swing.JComboBox outlier_distance_type_combo_box;
    private javax.swing.JLabel outlier_distance_type_label;
    private javax.swing.JComboBox outlier_response_combo_box;
    private javax.swing.JLabel outlier_response_label;
    // End of variables declaration//GEN-END:variables
}
