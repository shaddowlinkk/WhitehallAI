import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sat Feb 15 17:16:57 EST 2020
 */



/**
 * @author mac
 */
public class editing extends JFrame {
	public editing() {
		initComponents();
	}

	private void pointsItemStateChanged(ItemEvent e) {
		// TODO add your code here
	}

	private void OKActionPerformed(ActionEvent e) {
		System.out.println("hello");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - mac
		panel1 = new JPanel();
		label1 = new JLabel();
		textField1 = new JTextField();
		label2 = new JLabel();
		textField2 = new JTextField();
		points = new JComboBox();
		textField3 = new JTextField();
		OK = new JButton();
		label3 = new JLabel();

		//======== this ========
		setMaximizedBounds(new Rectangle(0, 0, 0, 0));
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== panel1 ========
		{

			// JFormDesigner evaluation mark
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(null);

			//---- label1 ----
			label1.setText("ID");
			panel1.add(label1);
			label1.setBounds(15, 20, 20, 25);
			panel1.add(textField1);
			textField1.setBounds(40, 20, 180, 25);

			//---- label2 ----
			label2.setText("Type");
			panel1.add(label2);
			label2.setBounds(5, 50, 25, 25);
			panel1.add(textField2);
			textField2.setBounds(40, 50, 180, 25);

			//---- points ----
			points.addItemListener(e -> pointsItemStateChanged(e));
			panel1.add(points);
			points.setBounds(45, 80, 95, points.getPreferredSize().height);
			panel1.add(textField3);
			textField3.setBounds(145, 80, 60, textField3.getPreferredSize().height);

			//---- OK ----
			OK.setText("OK");
			OK.addActionListener(e -> OKActionPerformed(e));
			panel1.add(OK);
			OK.setBounds(new Rectangle(new Point(210, 80), OK.getPreferredSize()));

			//---- label3 ----
			label3.setText("Points");
			panel1.add(label3);
			label3.setBounds(new Rectangle(new Point(5, 85), label3.getPreferredSize()));

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
		setSize(285, 215);
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
	private JComboBox points;
	private JTextField textField3;
	private JButton OK;
	private JLabel label3;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
