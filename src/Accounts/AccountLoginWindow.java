package Accounts;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Driver;
import Objects.Account;

public class AccountLoginWindow extends JPanel {

	JPanel cards;
	JLabel rsts;
	JLabel sts;
	final static String FIRSTLOGINPANEL = "LoginPanel";
	final static String REGISTERPANEL = "RegisterPanel";
	JLabel picLabel;
	private static AccountDBImport accs = new AccountDBImport("Accounts");
	private static LinkedList<Account> accountList = AccountDBImport.getAccounts();
	RegisterPanel registerPanel;

	public AccountLoginWindow() {

		initialise();

	}

	public boolean isAccountAdmin(Account a) {
		return a.isAdmin();
	}

	public Account performAccountExistenceCheck(String username, LinkedList<Account> accounts) {

		for (Account a: accounts) {
			if (a.getUsername().equalsIgnoreCase(username)) {
				return a;
			}
		}
		return null;
	}

	public boolean performPasswordCheck(String password, Account a) {
		if (password.equals(a.getPassword())) {
			return true;
		}
		return false;
	}

	private void initialise() {

		setLayout(new BorderLayout());
		cards = new JPanel();
		cards.setLayout(new CardLayout());

		//Title Graphic
		BufferedImage title;
		try {
			title = ImageIO.read(new File("res/title-image.png"));
			picLabel = new JLabel(new ImageIcon(title));
			add(picLabel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Login Screen
		JPanel card1 = new JPanel();
		card1.setLayout(new BoxLayout(card1, BoxLayout.Y_AXIS));
		card1.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Register Panel
		JPanel card2 = new JPanel();
		card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));
		card2.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Login Fields
		LoginPanel lp = new LoginPanel();
		lp.getPasswordField().addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String enteredUsername = LoginPanel.getUsernameField().getText();
					String enteredPassword = LoginPanel.getPasswordField().getText();
					performLogIn(enteredUsername, enteredPassword);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
		card1.add(lp);

		card1.add(addRegisterAndLoginActionButtons());

		//Login Status Label
		sts = new JLabel();
		sts.setAlignmentX(Component.CENTER_ALIGNMENT);
		card1.add(sts);

		//Add RegisterPanel

		registerPanel = new RegisterPanel();
		card2.add(registerPanel);


		card2.add(addBackAndRegisterActionButtons());

		//Registration Status Label
		rsts = new JLabel();
		rsts.setAlignmentX(Component.CENTER_ALIGNMENT);
		card2.add(rsts);

		//Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(card1, FIRSTLOGINPANEL);
		cards.add(card2, REGISTERPANEL);
		add(picLabel, BorderLayout.PAGE_START);
		add(cards, BorderLayout.CENTER);
		add(cards);
	}

	private JPanel addBackAndRegisterActionButtons() {
		//Add The Back Button
		JButton backButton = new JButton("Back to Log In");
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, FIRSTLOGINPANEL);

				registerPanel.clearFields();
				rsts.setText("");
			};

		});

		//Register Button
		JButton registerButton = new JButton("Register New Account");
		registerButton.setMinimumSize(new Dimension(100,30));
		registerButton.setMaximumSize(new Dimension(100,30));
		registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//Register Actions
				try {

					performRegistration(registerPanel.getLastName().getField().getText(), registerPanel.getUsernameField().getField().getText(), registerPanel.getPasswordField().getPField().getText(), registerPanel.getPasswordConfirmationField().getPField().getText());

				} catch (Exception e2) {
					rsts.setText("Not Possible!");
					e2.printStackTrace();
				}

			}
		});

		//Layout Correction for the login and register buttons
		JPanel layoutCorrector = new JPanel();
		layoutCorrector.setLayout(new FlowLayout());
		layoutCorrector.add(backButton);
		layoutCorrector.add(registerButton);
		return layoutCorrector;

	}

	private JPanel addRegisterAndLoginActionButtons() {
		//Register Button
		JButton registerButton = new JButton("Register");
		registerButton.setMinimumSize(new Dimension(100,30));
		registerButton.setMaximumSize(new Dimension(100,30));
		registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, AccountLoginWindow.REGISTERPANEL);
				sts.setText("");
			};

		});

		//Login Button
		JButton loginButton = new JButton("Log In");
		loginButton.setMinimumSize(new Dimension(100,30));
		loginButton.setMaximumSize(new Dimension(100,30));
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String enteredUsername = LoginPanel.getUsernameField().getText();
				String enteredPassword = LoginPanel.getPasswordField().getText();
				performLogIn(enteredUsername, enteredPassword);

			};

		});
		//Layout Correction for the login and register buttons
		JPanel layoutCorrector = new JPanel();
		layoutCorrector.setLayout(new FlowLayout());
		layoutCorrector.add(loginButton);
		layoutCorrector.add(registerButton);
		return layoutCorrector;
	}

	private void performLogIn(String enteredUsername, String enteredPassword) {
		Account a = performAccountExistenceCheck(enteredUsername, accountList);
		if (a != null) {
			if (performPasswordCheck(enteredPassword, a)) {
				Driver.switchWindows("Mr/Mrs. " + a.getSecondName(), isAccountAdmin(a));
			} else {
				sts.setText("Not Possible!");
				LoginPanel.getPasswordField();
			}
		} else {
			sts.setText("Not Possible!");
			LoginPanel.getUsernameField().selectAll();
		}
	}

	private void performRegistration(String lastName, String username, String password, String passwordConfirmation) {

		System.out.println(lastName+"  "+username+"   "+password+"   "+passwordConfirmation);

		if (password.equals(passwordConfirmation)) {

			Account newAccount = new Account(AccountDBImport.accounts.size(), username, lastName, password, false);
			AccountDBImport.accounts.add(newAccount);

			//write new account to file

			try {
				BufferedWriter w = new BufferedWriter(new FileWriter(accs.getFileName(), true));

				w.write(newAccount.getUserID()+","+newAccount.getUsername()+","+newAccount.getSecondName()+","+newAccount.getPassword()+",0"+"\n");
				
				rsts.setText("Account Created!");
				
				registerPanel.clearFields();
				
				w.close();
			} catch (IOException e) {
				rsts.setText("Not Possible!");
				e.printStackTrace();
			}

		} else {

			rsts.setText("Passwords Don't Match!");

			registerPanel.getPasswordField().getPField().setText("");
			registerPanel.getPasswordConfirmationField().getPField().setText("");
		}
	}

}
