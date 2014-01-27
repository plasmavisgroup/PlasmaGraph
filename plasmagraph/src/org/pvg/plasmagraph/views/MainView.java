/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvg.plasmagraph.views;

import org.pvg.plasmagraph.models.MainModel;
import org.pvg.plasmagraph.utils.template.Template;

/**
 *
 * @author Andrew Navas
 */
@SuppressWarnings("serial")
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     * @param t 
     * @param main_model 
     */
    public MainView(MainModel main_model, Template t) {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        tab_pane = new javax.swing.JTabbedPane();
        menu_bar = new javax.swing.JMenuBar();
        data_menu = new javax.swing.JMenu();
        import_data_option = new javax.swing.JMenuItem();
        data_filter_menu = new javax.swing.JMenu();
        import_data_filter_option = new javax.swing.JMenuItem();
        modify_data_filter_option = new javax.swing.JMenuItem();
        save_data_filter_option = new javax.swing.JMenuItem();
        template_menu = new javax.swing.JMenu();
        import_template_option = new javax.swing.JMenuItem();
        save_template_option = new javax.swing.JMenuItem();
        graph_menu = new javax.swing.JMenu();
        create_graph_option = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PlasmaGraph");
        setName("PlasmaGraph"); // NOI18N

        data_menu.setText("Data");
        data_menu.setToolTipText("Menu regarding data.");

        import_data_option.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        import_data_option.setText("Import Data");
        import_data_option.setToolTipText("Import a data file into the system.");
        import_data_option.setActionCommand("ImportData");
        data_menu.add(import_data_option);

        menu_bar.add(data_menu);

        data_filter_menu.setText("Data Filter");
        data_filter_menu.setToolTipText("Menu regarding data filter used in data importing.");

        import_data_filter_option.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        import_data_filter_option.setText("Import Data Filter");
        import_data_filter_option.setToolTipText("Imports an existing data filter from a file.");
        import_data_filter_option.setActionCommand("ImportDataFilter");
        data_filter_menu.add(import_data_filter_option);

        modify_data_filter_option.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        modify_data_filter_option.setText("Modify Data Filter");
        modify_data_filter_option.setToolTipText("Opens a window to modify the current data filter.");
        modify_data_filter_option.setActionCommand("ModifyDataFilter");
        data_filter_menu.add(modify_data_filter_option);

        save_data_filter_option.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        save_data_filter_option.setText("Save Data Filter");
        save_data_filter_option.setToolTipText("Store the current data filter in a file.");
        data_filter_menu.add(save_data_filter_option);

        menu_bar.add(data_filter_menu);

        template_menu.setText("Templates");
        template_menu.setToolTipText("Menu regarding option templates.");

        import_template_option.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        import_template_option.setText("Import Template");
        import_template_option.setToolTipText("Import a template file into the system.");
        import_template_option.setActionCommand("ImportTemplate");
        template_menu.add(import_template_option);

        save_template_option.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save_template_option.setText("Save Template");
        save_template_option.setToolTipText("Store the current template in a file.");
        save_template_option.setActionCommand("SaveTemplate");
        template_menu.add(save_template_option);

        menu_bar.add(template_menu);

        graph_menu.setText("Graph");
        graph_menu.setToolTipText("Creates a graph according to the selected data and options.");

        create_graph_option.setText("Create Graph");
        create_graph_option.setActionCommand("CreateGraph");
        graph_menu.add(create_graph_option);

        menu_bar.add(graph_menu);

        setJMenuBar(menu_bar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("A data-graphing program for the Java interpreted programming language.");

        pack();
    }// </editor-fold>                        

    // Variables declaration - do not modify                     
    private javax.swing.JMenuItem create_graph_option;
    private javax.swing.JMenu data_filter_menu;
    private javax.swing.JMenu data_menu;
    private javax.swing.JMenu graph_menu;
    private javax.swing.JMenuItem import_data_filter_option;
    private javax.swing.JMenuItem import_data_option;
    private javax.swing.JMenuItem import_template_option;
    private javax.swing.JMenuBar menu_bar;
    private javax.swing.JMenuItem modify_data_filter_option;
    private javax.swing.JMenuItem save_data_filter_option;
    private javax.swing.JMenuItem save_template_option;
    private javax.swing.JTabbedPane tab_pane;
    private javax.swing.JMenu template_menu;
    // End of variables declaration                   
}