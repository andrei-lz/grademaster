package Accounts;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TextField extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1859640459288139785L;


	boolean selection;
	private JTextField field;
	private JPasswordField passwordField;

	public TextField(String name, boolean password) {

		initialise(name, password);

	}

	private void initialise(String name, boolean password) {

		JLabel label = new JLabel(name+": ");
		add(label);
		
		if (!password) {
			field = new JTextField(20);
			field.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if(!selection) {
						field.selectAll();
						selection = true;
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					selection = false;
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			});
			
			add(field);
			
		} else {
			
			passwordField = new JPasswordField(20);
			passwordField.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if(!selection) {
						passwordField.selectAll();
						selection = true;
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					selection = false;
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			});
			
			add(passwordField);
			
		}

	}
	
	public void clearField() {
		if (passwordField!=null) passwordField.setText("");
		if (field!=null) field.setText("");
	}

	public JTextField getField() {
		return field;
	}
	
	public JPasswordField getPField() {
		return passwordField;
	}
	
}
