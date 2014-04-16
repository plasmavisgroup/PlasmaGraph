package org.pvg.plasmagraph.views;

/**
 * 
 * @author Daniel Quintini
 */
@SuppressWarnings ("serial")
public class DatasetLogView extends javax.swing.JFrame {

	/**
	 * Creates new form DatasetLogView
	 * @param toString String to put in jTextArea1.
	 */
	public DatasetLogView (String toString) {
		
		initComponents ();
		this.jTextArea1.setText (toString);
		
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents () {

		jScrollPane1 = new javax.swing.JScrollPane ();
		jTextArea1 = new javax.swing.JTextArea ();

		setDefaultCloseOperation (javax.swing.WindowConstants.HIDE_ON_CLOSE);

		jTextArea1.setEditable (false);
		jTextArea1.setColumns (20);
		jTextArea1.setRows (5);
		jScrollPane1.setViewportView (jTextArea1);
		
		jTextArea1.setLineWrap (true);
		jTextArea1.setWrapStyleWord (true);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout (
				getContentPane ());
		getContentPane ().setLayout (layout);
		layout.setHorizontalGroup (layout.createParallelGroup (
				javax.swing.GroupLayout.Alignment.LEADING).addComponent (
				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 571,
				Short.MAX_VALUE));
		layout.setVerticalGroup (layout.createParallelGroup (
				javax.swing.GroupLayout.Alignment.LEADING).addComponent (
				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436,
				Short.MAX_VALUE));

		pack ();
	}

	// Variables declaration - do not modify
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	// End of variables declaration
}
