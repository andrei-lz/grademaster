package Accounts;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {

	private static JTextField usernameField;
	private static JPasswordField passwordField;
	boolean selection;
	
	public LoginPanel() {

		initialise();

	}

	private void initialise() {

		setLayout(new FlowLayout(FlowLayout.CENTER));
		setMaximumSize(new Dimension(300, 10000));

		JLabel usr = new JLabel("Username: ");
		JLabel psd = new JLabel("Password: ");

		selection = false;
		usernameField = new JTextField(20);
		usernameField.setSize(500, 15);
		usernameField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!selection) {
					usernameField.selectAll();
					selection = true;
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		usernameField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				selection = false;
			}
			
		});

		passwordField = new JPasswordField(20);
		passwordField.setSize(500, 15);
		passwordField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!selection) {
					passwordField.selectAll();
					selection = true;
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		passwordField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				selection = false;
			}
			
		});

		add(usr);
		add(usernameField);
		add(psd);
		add(passwordField);

	}
	
	public static JTextField getUsernameField() {
		return usernameField;
	}

	public static JTextField getPasswordField() {
		return passwordField;
	}
	
	

}
