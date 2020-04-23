package Editor;

import Util.DataIO;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
/*
 * Created by JFormDesigner on Fri Feb 21 16:16:11 EST 2020
 */



/**
 * @author mac
 */
// TODO workout saving bug
public class Editing extends JFrame {
	public Editing(DataIO _data) {
		 data=_data;
		 initComponents();
	}

	public void setList(String[] l){
		DefaultComboBoxModel m = new DefaultComboBoxModel(l);
		Connections.setModel(m);
	}

	public void setID(int i){
		apply=false;
		id=i;
		textField1.setText(Integer.toString(i));
	}
	public void setSelect(int s){
		select=s;

	}
	public void setType(int t){
		type=t;
		textField2.setText(Integer.toString(t));
	}
	public void setData(DataIO d){
		data=d;
	}
	public Boolean getMove(){
		return Mclick;
	}

	public Boolean getApply(){return apply;}

	public void stopMove(){
		Mclick=false;
	}

	private void textField1ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void textField2ActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void pointsItemStateChanged(ActionEvent e) {
		Connections.getSelectedIndex();
	}
	private void addActionPerformed(ActionEvent e) {
		Connections.addItem(Integer.parseInt(JOptionPane.showInputDialog("Enter MainRun.Node ID#:")));
	}
	private void delActionPerformed(ActionEvent e) {
		Connections.removeItemAt(Connections.getSelectedIndex());
	}
	private void ApplyActionPerformed(ActionEvent e) {
		apply=true;
		int index=select;
		data.setID(index,Integer.parseInt(textField1.getText()));
		data.setType(index,Integer.parseInt(textField2.getText()));
		ArrayList<Integer> links= new ArrayList<Integer>();
		for (int i=0;i<Connections.getItemCount();i++){
			links.add(Integer.parseInt( ""+Connections.getItemAt(i)));
		}
		data.addLinks(index,links);
		setVisible(false);
	}

	private void MoveActionPerformed(ActionEvent e) {
		Mclick=true;
	}

	private void CalncelActionPerformed(ActionEvent e) {
		setVisible(false);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - mac
		panel1 = new JPanel();
		label1 = new JLabel();
		textField1 = new JTextField();
		label2 = new JLabel();
		textField2 = new JTextField();
		Connections = new JComboBox();
		add = new JButton();
		label3 = new JLabel();
		Apply = new JButton();
		del = new JButton();
		Move = new JButton();
		Calncel = new JButton();

		//======== this ========
		setMaximizedBounds(new Rectangle(0, 0, 0, 0));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Editer");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== panel1 ========
		{

			// JFormDesigner evaluation mark
			/*
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});
			*/
			panel1.setLayout(null);

			//---- label1 ----
			label1.setText("ID");
			panel1.add(label1);
			label1.setBounds(15, 20, 20, 25);

			//---- textField1 ----
			textField1.addActionListener(e -> textField1ActionPerformed(e));
			panel1.add(textField1);
			textField1.setBounds(50, 20, 180, 25);

			//---- label2 ----
			label2.setText("Type");
			panel1.add(label2);
			label2.setBounds(10, 50, 30, 25);

			//---- textField2 ----
			textField2.addActionListener(e -> textField2ActionPerformed(e));
			panel1.add(textField2);
			textField2.setBounds(50, 50, 180, 25);

			//---- Connections ----
			Connections.addActionListener(e -> pointsItemStateChanged(e));
			panel1.add(Connections);
			Connections.setBounds(45, 80, 95, Connections.getPreferredSize().height);

			//---- add ----
			add.setText("Add");
			add.addActionListener(e -> addActionPerformed(e));
			panel1.add(add);
			add.setBounds(145, 80, 60, 25);

			//---- label3 ----
			label3.setText("Conns");
			panel1.add(label3);
			label3.setBounds(new Rectangle(new Point(5, 85), label3.getPreferredSize()));

			//---- Apply ----
			Apply.setText("Apply");
			Apply.addActionListener(e -> ApplyActionPerformed(e));
			panel1.add(Apply);
			Apply.setBounds(20, 130, 80, 25);

			//---- del ----
			del.setText("Del");
			del.addActionListener(e -> delActionPerformed(e));
			panel1.add(del);
			del.setBounds(210, 80, 60, 25);

			//---- Move ----
			Move.setText("Move");
			Move.addActionListener(e -> MoveActionPerformed(e));
			panel1.add(Move);
			Move.setBounds(100, 130, 80, 25);

			//---- Calncel ----
			Calncel.setText("Cancel");
			Calncel.addActionListener(e -> CalncelActionPerformed(e));
			panel1.add(Calncel);
			Calncel.setBounds(180, 130, 80, 25);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < panel1.getComponentCount(); i++) {
					Rectangle bounds = panel1.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = panel1.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				panel1.setMinimumSize(preferredSize);
				panel1.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(panel1, BorderLayout.CENTER);
		setSize(295, 215);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}



	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - mac
	private JPanel panel1;
	private JLabel label1;
	private JTextField textField1;
	private JLabel label2;
	private JTextField textField2;
	private JComboBox Connections;
	private JButton add;
	private JLabel label3;
	private JButton Apply;
	private JButton del;
	private JButton Move;
	private JButton Calncel;
	private int select;
	private boolean Mclick;
	private DataIO data;
	private String[] list;
	private int id;
	private int type;
	private  Boolean apply=false;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
