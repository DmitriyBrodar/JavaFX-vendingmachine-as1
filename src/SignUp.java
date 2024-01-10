import java.time.LocalDate;
import java.time.Period;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUp {
	public static void openSignUpWindow (Stage primaryStage) {
		Text firstNameLabel = new Text("First name");
		TextField firstName = new TextField();
		firstName.setPromptText("First name");
		Text lastNameLabel = new Text("Last name");
		TextField lastName = new TextField();
		lastName.setPromptText("Last name");
		Text textPasswordLabel = new Text("Password");
		PasswordField  password = new PasswordField();
		password.setPromptText("Password");
		Text dateOfBirthLabel = new Text("Date of birth");
		DatePicker checkInDatePicker = new DatePicker();
		Text genderLabel = new Text("Gender");
		
		ToggleGroup groupGender = new ToggleGroup();
		RadioButton maleRadio = new RadioButton("Male");
		maleRadio.setToggleGroup(groupGender);
		RadioButton femaleRadio = new RadioButton("Female");
		femaleRadio.setToggleGroup(groupGender);
		RadioButton xRadio = new RadioButton("X");
		xRadio.setToggleGroup(groupGender);
		
		Text emailLabel = new Text("Email address");
		TextField email = new TextField();
		email.setPromptText("name@mail.com");
		Text mobileLabel = new Text("Mobile number");
		TextField mobile = new TextField();
		mobile.setPromptText("+3538011111111");
		Text addressLabel = new Text("Address");
		TextField address = new TextField();
		address.setPromptText("Street address");
		Text countyLabel = new Text("County");
		ChoiceBox<String> countyChoiceBox = new ChoiceBox<String>();
		countyChoiceBox.getItems().addAll("None", "Carlow", "Cavan", "Clare", "Cork", "Donegal", "Dublin", "Galway", "Kerry", "Kildare", "Kilkenny", "Laois", "Leitrim", "Limerick", "Longford", "Louth", "Mayo", "Meath", "Monaghan", "Offaly", "Roscommon", "Sligo", "Tipperary", "Waterford", "Westmeath", "Wexford", "Wicklow");
		countyChoiceBox.setValue("None");
		Text countryLabel = new Text("Country");
		TextField country = new TextField();
		country.setPromptText("Country");
		Text creditCardHeaderLabel = new Text("Credit card detail");
		Text topUpAccountLabel = new Text("Top up your account");
		TextField topUpAccount = new TextField();
		topUpAccount.setPromptText("$0");
		Text creditCardLabel = new Text("Card Number");
		TextField creditCard = new TextField();
		creditCard.setPromptText("0000 0000 0000 0000");
		Text expiryDateLabel = new Text("Expiry Date");
		ChoiceBox<String> expiryDateMonth = new ChoiceBox<String>();
		expiryDateMonth.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
		expiryDateMonth.setValue("Month");
		ChoiceBox<String> expiryDateYear = new ChoiceBox<String>();
		expiryDateYear.getItems().addAll("2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034");
		expiryDateYear.setValue("Year");
		Text cvvCodeLabel = new Text("Card CVV/CVC");
		TextField cvvCode = new TextField();
		cvvCode.setPromptText("CVV");
		Text termsAndCondLabel = new Text("I accept the ");
        Label termsAndCondLink = new Label("Terms and Conditions");
        termsAndCondLink.setStyle("-fx-text-fill: blue; -fx-underline: true;");
		CheckBox termsAndCondCheckBox = new CheckBox();
		Button signUpButton = new Button("Create Account");
		Button back = new Button("< Back");
		Button help = new Button ("? Help");
		
		back.setPrefWidth(100);
		help.setPrefWidth(100);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(10);
		
		// Add fields to the grid
		grid.add(firstNameLabel, 0, 0);
		grid.add(firstName, 1, 0);
		grid.add(lastNameLabel, 0, 1);
		grid.add(lastName, 1, 1);
		grid.add(textPasswordLabel, 0, 2);
		grid.add(password, 1, 2);
		grid.add(dateOfBirthLabel, 0, 3);
		grid.add(checkInDatePicker, 1, 3);
		grid.add(genderLabel, 1, 4);
		grid.add(maleRadio, 0, 5);
		grid.add(femaleRadio, 1, 5);
		grid.add(xRadio, 2, 5);
		grid.add(emailLabel, 0, 6);
		grid.add(email, 1, 6);
		grid.add(mobileLabel, 0, 7);
		grid.add(mobile, 1, 7);
		grid.add(addressLabel, 0, 8);
		grid.add(address, 1, 8);
		grid.add(countyLabel, 0, 9);
		grid.add(countyChoiceBox, 1, 9);
		grid.add(countryLabel, 0, 10);
		grid.add(country, 1, 10);
		grid.add(creditCardHeaderLabel, 1, 11);
		grid.add(topUpAccountLabel, 0, 12);
		grid.add(topUpAccount, 1, 12);
		grid.add(creditCardLabel, 0, 13);
		grid.add(creditCard, 1, 13);
		grid.add(expiryDateLabel, 0, 14);
		grid.add(expiryDateMonth, 1, 14);
		grid.add(expiryDateYear, 2, 14);
		grid.add(cvvCodeLabel, 0, 15);
		grid.add(cvvCode, 1, 15);
		grid.add(termsAndCondLabel, 0, 16);
		grid.add(termsAndCondLink, 1, 16);
		grid.add(termsAndCondCheckBox, 2, 16);
		grid.add(signUpButton, 1, 18);
		grid.add(back, 0, 20);
		grid.add(help, 2, 20);
		
		// Styling for text
		firstNameLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
		lastNameLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		textPasswordLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		dateOfBirthLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		genderLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		emailLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
		mobileLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		addressLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		countyLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		countryLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		creditCardHeaderLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		creditCardLabel.setStyle("-fx-font: normal bold 15px 'serif' ");
		expiryDateLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		cvvCodeLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		termsAndCondLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		topUpAccountLabel.setStyle("-fx-font: normal bold 15px 'serif' "); 
		
		// Date of birth validation
		checkInDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                LocalDate currentDate = LocalDate.now();
                Period agePeriod = Period.between(newValue, currentDate);
                int age = agePeriod.getYears();

                if (age < 18) {
                	UserDataManager.showAlert("You must be at least 18 years old to avail of the service", Alert.AlertType.WARNING);
                    checkInDatePicker.setValue(null);
                }
            }
        });
		
        // Create a click event handler for the "Terms and Conditions" link
        termsAndCondLink.setOnMouseClicked(event -> {
            // Display the terms and conditions in a pop-up
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Terms and Conditions");
            alert.setHeaderText("Terms and Conditions");
            alert.getDialogPane().setContent(new Text("\tUser Accounts\r\n"
            		+ "\tUsers must be at least 18 years old to create an account.\r\n"
            		+ "\tAccount holders are responsible for their account security.\r\n"
            		+ "\r\n\tPurchases\r\n"
            		+ "\tUsers can purchase drinks, and the cost is deducted from their account balance.\r\n"
            		+ "\tThe machine does not provide change.\r\n"
            		+ "\r\n\tManagerial Functions\r\n"
            		+ "\tThe manager is responsible for restocking, revenue collection, and maintenance.\r\n"
            		+ "\r\n\tMaintenance and Issue Reporting\r\n"
            		+ "\tUsers can report issues with the machine.\r\n"
            		+ "\tThe manager will address reported issues promptly.\r\n"
            		+ "\r\n\tPrivacy and Data Protection\r\n"
            		+ "\tUser data will be stored securely and used solely for machine operations.\r\n"
            		+ "\r\n\tTop-up and Account Balances\r\n"
            		+ "\tUsers must maintain a minimum €2.00 balance for purchases.\r\n"
            		+ "\tUsers can top up their accounts with a credit card.\r\n"
            		+ "\r\n\tLiability\r\n"
            		+ "\tThe operator is not responsible for losses or damages due to machine use.\r\n"
            		+ "\tUsers must report any suspicious activities.\r\n"
            		+ "\r\n\tAcceptance of Terms and Conditions\r\n"
            		+ "\tUse of the machine implies agreement with these terms.\r\n"
            		+ "\tNon-compliance may result in account suspension or termination."));
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        });
        
        countyChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("None")) {
                country.clear(); // Set the country field to empty
            } else {
                country.setText("Ireland"); // Set the country field to "Ireland"
            }
        });
		
        signUpButton.setOnAction(event -> {
        	// Check if firstName and password fields are empty
        	if (firstName.getText().isEmpty() || password.getText().isEmpty()) {
                UserDataManager.showAlert("Please fill First Name and Password fields for creating account.", Alert.AlertType.WARNING);
                return; 
            }
        	
        	// Validate and process user input
        	User newUser = new User(null, null, 0);
        	newUser.setUsername(firstName.getText());
        	newUser.setPin(password.getText());
        	
        	if (UserDataManager.isUsernameTaken(newUser.getUsername())) {
                UserDataManager.showAlert("A user with this Username already exists, ", Alert.AlertType.WARNING);
                return; 
            }
        	
        	// Validate PIN (must be 4 digits)
        	if(!newUser.getPin().matches("\\d{4}")) {
        		UserDataManager.showAlert("Pin must be 4 digits.", Alert.AlertType.WARNING);
        		return; 
        	}
        	
        	// Validate First Name 
        	if (!newUser.getUsername().matches("[a-zA-Z]+")) {
        		UserDataManager.showAlert("First Name must contain only letters.", Alert.AlertType.WARNING);
        		return; 
        	}
        	
        	// Validate Last Name 
        	if(!lastName.getText().matches("[a-zA-Z]+")) {
        		UserDataManager.showAlert("Last Name must contain only letters", Alert.AlertType.WARNING);
        		return;
        	}
        	
        	// Validate Credit card
        	if (!creditCard.getText().matches("\\d{16}")) {
        		UserDataManager.showAlert("Credit card must contain 16 digits.", Alert.AlertType.WARNING);
        		return;
        	}
        	
        	// Validate Email
        	if(!email.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")){
        		UserDataManager.showAlert("Incorrect email address. Please check the spelling and try again.", Alert.AlertType.WARNING);
        		return;
        	}
        	
        	try {
        	    double balance = Double.parseDouble(topUpAccount.getText());
        	    // Validate deposit amount (at least €10)
        	    if(balance < 10) {
        	    	UserDataManager.showAlert("Deposit a least €10", Alert.AlertType.WARNING);
        	    	return;
        	    }
        	    newUser.setAccountBalance(balance);
        	} catch (NumberFormatException e) {
        	    // Handle the case where the input is not a valid number
        	    showAlert("Invalid account balance. Please enter a valid numerical value.");
        	    return; 
        	}
        	
        	//Registration successful
        	UserDataManager.registerUser(newUser);
        	UserDataManager.showAlert("Account successfully created!", Alert.AlertType.INFORMATION);
        	primaryStage.close();
			LoginWindow.openLoginWindow(primaryStage);

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
        
        help.setOnAction(event -> {
            // Display the terms and conditions in a pop-up
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sign Up Help");
            alert.setHeaderText("Sign Up Help");
            alert.getDialogPane().setContent(new Text("Please fill in the required information to create your account. "
            		+ "\r\nYour 'First name' and 'Last name' should be your legal names."
            		+ "\r\nChoose a secure 'Password' for your account. "
            		+ "\r\nProvide your 'Date of birth', 'Gender', 'Email address', 'Mobile number', 'Address', and 'County'."
            		+ "\r\nYou can leave 'Country' as 'Ireland' if it's correct."
            		+ "\r\nIn the 'Credit card details' section, enter your 'Card Number', select the 'Expiry Date' (Month and Year), and the 'Card CVV/CVC'."
            		+ "\r\nFinally, check the box to accept our 'Terms and Conditions' before clicking 'Create Account'."));
            alert.showAndWait();
        });
        
		Scene scene = new Scene(grid, 500, 700);
		primaryStage.setTitle("Sign Up");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	 private static void showAlert(String message) {
	        Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle("Age Validation");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
}
