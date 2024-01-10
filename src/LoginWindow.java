import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWindow {
	
	private static Button submit, clear, back, help;
	
	
	public static void openLoginWindow (Stage primaryStage) {		
		Text loginLabel = new Text("Login:");
		TextField login = new TextField();
		login.setPromptText("First Name");
		Text passwordLabel = new Text("Password:");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");
		
		submit = new Button("Submit");
		clear = new Button("Clear");
		back = new Button("Back <");
		help = new Button("Help ?");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(10);
		
		grid.add(loginLabel, 0, 0);
		grid.add(login, 1, 0);
		grid.add(passwordLabel, 0, 1);
		grid.add(password, 1, 1);
		grid.add(submit, 0, 3);
		grid.add(clear, 1, 3);
		grid.add(back, 0, 7);
		grid.add(help, 1, 7);
		
		setWidths();
		
		String username = UserDataManager.getUsername();
		
        submit.setOnAction(e -> {
        	 // Get entered credentials
            String enteredUsername = login.getText();
            String enteredPin = password.getText();

            // Check for empty fields
            if (enteredUsername.isEmpty() || enteredPin.isEmpty()) {
                showAlert("Please enter both username and PIN.");
                return;
            }

            // Authenticate user
            User loggedInUser = authenticateUser(enteredUsername, enteredPin);

            // Check the authentication result
            if (loggedInUser != null) {
            	showConfirmation("Hello, " + username + "! You've successfully logged in.");
                primaryStage.close();
                VendingMachine.openVendingMachine(primaryStage, loggedInUser, false);
            }
            
        });

        clear.setOnAction(e -> {
            // Clear the text fields
            login.clear();
            password.clear();
        });
        
        back.setOnAction(e -> {
            // Close the current login window
            primaryStage.close();
            
            // Create a new instance of MainApp and show its window
            try {
                MainApp mainApp = new MainApp();
                mainApp.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        help.setOnMouseClicked(event -> {
            // Display the terms and conditions in a pop-up
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login Help:");
            alert.setHeaderText("Login Help:");
            alert.getDialogPane().setContent(new Text("\n\tPlease enter your login credentials to access your account."
            		+ "\r\n\tYour 'Login' should be your first name, and 'Password' should be your account password.      "
            		+ "\r\n\tIf you've forgotten your password, you can use the 'Clear' button to reset the fields."));
            alert.showAndWait();
        });
		
		Scene scene = new Scene(grid, 300, 400);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	private static User authenticateUser(String enteredUsername, String enteredPin) {
	    User loggedInUser = UserDataManager.getUser(enteredUsername, enteredPin);

	    // Check if the user exists
	    if (loggedInUser == null) {
	        showAlert("Username or PIN is incorrect. Please try again or register in the system.");
	        return null;
	    }

	    // Check if the PIN is correct
	    if (!loggedInUser.getPin().equals(enteredPin)) {
	        showAlert("Incorrect PIN. Please check your PIN.");
	        return null;
	    }

	    return loggedInUser;
	}
	
	private static void showAlert(String message) {
	    Alert alert = new Alert(AlertType.WARNING);
	    alert.setTitle("Login Error");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}

	private static void showConfirmation(String message) {
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Login Success");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}

	private static void setWidths() {
		submit.setPrefWidth(70);
		clear.setPrefWidth(70);
		back.setPrefWidth(70);
		help.setPrefWidth(70);
	}
}
