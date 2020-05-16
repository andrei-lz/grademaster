package Accounts;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class RegisterPanel extends JPanel {

	boolean selection;

	//Components
	TextField lastName;
	TextField usernameField;
	TextField passwordField;
	TextField passwordConfirmationField;


	public RegisterPanel() {

		initialise();

	}

	private void initialise() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 5050));

		lastName = new TextField("Last Name: ", false);
		add(lastName);

		usernameField = new TextField("Username: ", false);
		add(usernameField);

		passwordField = new TextField("Password: ", true);
		add(passwordField);

		passwordConfirmationField = new TextField("Confirm Password: ", true);
		add(passwordConfirmationField);

	}

	public TextField getLastName() {
		return lastName;
	}

	public TextField getUsernameField() {
		return usernameField;
	}

	public TextField getPasswordField() {
		return passwordField;
	}

	public TextField getPasswordConfirmationField() {
		return passwordConfirmationField;
	}
	
	public void clearFields() {
		lastName.clearField();
		usernameField.clearField();
		passwordField.clearField();
		passwordConfirmationField.clearField();
	}

}
