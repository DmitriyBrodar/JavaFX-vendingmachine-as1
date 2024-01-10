// User class representing a user in the application
public class User {
	private String username;
	private String pin;
	private double accountBalance;
	
	public User(String username, String pin, double accountBalance) {
		this.username = username;
		this.pin = pin;
		this.accountBalance = accountBalance;
	}
	
	// Getter method to get the username 
	public String getUsername() {
		return username;
	}
	
	// Getter method to get the PIN 
	public String getPin() {
		return pin;
	}
	
	// Getter method to get the account balance 
	public double getAccountBalance() {
		return accountBalance;
	}
	
	// Setter method to update the username
	public void setUsername(String username) {
		this.username = username;
	}
	
	// Setter method to update the PIN
	public void setPin(String pin) {
		this.pin = pin;
	} 
	
	// Setter method to update the account balance
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
}

