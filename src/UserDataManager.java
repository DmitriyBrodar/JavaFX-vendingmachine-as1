import java.util.ArrayList;

import javafx.scene.control.Alert;


public class UserDataManager {
	
	// Array list to store registered users
	private static ArrayList<User> registeredUsers = new ArrayList<>();
	
	// Method to register a new user
	public static void registerUser(User user) {
		//Check if the username is already taken
		if (!isUsernameTaken(user.getUsername())) {
		registeredUsers.add(user);
	    System.out.println("User registered: " + user.getUsername());
		} else {
			UserDataManager.showAlert("A user with this Username already exists", Alert.AlertType.WARNING);
			
		}
	}
	
	// Check if a username is already taken
	public static boolean isUsernameTaken(String username) {
		return registeredUsers.stream().anyMatch(user -> user.getUsername().equals(username));
	}
	
	// user extraction based on user name and PIN code
	public static User getUser(String username, String pin) {
		for (User user:registeredUsers) {
			if (user.getUsername().equals(username) && user.getPin().equals(pin)) {
				return user;
			}
		}
		return null;
	}
	
	static void showAlert(String message, Alert.AlertType alertType) {
	    Alert alert = new Alert(alertType);
	    alert.setTitle("Validation");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	
	// get the total number of registered users
	public static int getTotalUsers() {
		return registeredUsers.size();
	}
	
	// get the username of the last registered user
	public static String getUsername() {
        // Implement logic to retrieve the username based on your application's requirements.
        // For simplicity, let's assume you want the username of the last registered user.
        if (!registeredUsers.isEmpty()) {
            return registeredUsers.get(registeredUsers.size() - 1).getUsername();
        } else {
            return null; // Handle the case when there are no registered users
        }
    }
}
