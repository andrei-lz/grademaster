package Objects;

public class Account {
	
	private int userID;
	private String username;
	private String secondName;
	private String password;
	private boolean admin;
	
	public Account(int userID, String username, String secondName, String password, boolean admin) {
		this.userID = userID;
		this.username = username;
		this.secondName = secondName;
		this.password = password;
		this.admin = admin;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Account [userID=" + userID + ", username=" + username + ", secondName="
				+ secondName + ", password=" + password + ", admin=" + admin + "]";
	}
	
	
	
}
