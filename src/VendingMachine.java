import java.lang.NumberFormatException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class VendingMachine {
	
	// Fields for drink selection
	private static TextField numberChooseDrink1Field, numberChooseDrink2Field, numberChooseDrink3Field, numberChooseDrink4Field;
	private static Text numberLeftedDrink1Field, numberLeftedDrink2Field, numberLeftedDrink3Field, numberLeftedDrink4Field;
	private static Text  totalPriceField, accountBalanceField, greetingWithCustomer;
	
	// Price-related variables
	private static double totalAmount = 0;
	private static double drinkPrice;
	private static double gotAmountOfCoins;
	private static double unreturnedChange, TotalUsersSpends;
	private static double newBalance;
	private static boolean giveChange = true;
	
	// Decimal formatting
	private static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	private static DecimalFormat df = new DecimalFormat("#.##", symbols);
	
	// Vending machine interface
	public static void openVendingMachine (Stage primaryStage, User user, boolean isGuest) {
	    
		String username = UserDataManager.getUsername();
		
		// Set drink price based on user type
		if (!isGuest) {
			drinkPrice = 1.7;
			greetingWithCustomer = new Text ("Hello dear " + username + "!");
		} else {
			drinkPrice = 1.8;
			greetingWithCustomer = new Text ("Hello dear Guest!");
		}
		
		// Header for drinks selection
	    Text priceHeader = new Text("Price");
	    Text amountOfStockHeader = new Text("In Stock");
	    Text amountOfOrderHeader = new Text("Order");
	    
	    // Image for drink and UI elements for the vending machine
	    Image imageDrink1 = new Image("/cola.png", true); 
	    ImageView imageDrink1View = new ImageView(imageDrink1);
	    imageDrink1View.setFitHeight(70);
	    imageDrink1View.setFitWidth(70);
	    imageDrink1View.setPreserveRatio(true);
		Text pricePerDrink1 = new Text("€" + String.valueOf(drinkPrice));
	    numberLeftedDrink1Field = new Text("20");
	    numberChooseDrink1Field = new TextField("0");
	    numberChooseDrink1Field.setEditable(false);
	    numberChooseDrink1Field.setPrefWidth(30);
	    Button minusButtonDrink1= new Button("-");
	    Button plusButtonDrink1 = new Button("+");   
	    Image imageDrink2 = new Image("/fanta.png", true); 
	    ImageView imageDrink2View = new ImageView(imageDrink2);
	    imageDrink2View.setFitHeight(70);
	    imageDrink2View.setFitWidth(70);
        imageDrink2View.setPreserveRatio(true);  
        Text pricePerDrink2 = new Text("€" + String.valueOf(drinkPrice));
        numberLeftedDrink2Field = new Text("20");
	    numberChooseDrink2Field = new TextField("0");
        numberChooseDrink2Field.setEditable(false);
        numberChooseDrink2Field.setPrefWidth(30);
	    Button minusButtonDrink2= new Button("-");
        Button plusButtonDrink2 = new Button("+");    
	        
        Image imageDrink3 = new Image("/pepsi.png", true); 
	    ImageView imageDrink3View = new ImageView(imageDrink3);
	    imageDrink3View.setFitHeight(70);
	    imageDrink3View.setFitWidth(70);
	    imageDrink3View.setPreserveRatio(true);  
	    Text pricePerDrink3 = new Text("€" + String.valueOf(drinkPrice));
		numberLeftedDrink3Field = new Text("20");
	    numberChooseDrink3Field = new TextField("0");
	    numberChooseDrink3Field.setEditable(false);
	    numberChooseDrink3Field.setPrefWidth(30);
	    Button minusButtonDrink3= new Button("-");
	    Button plusButtonDrink3 = new Button("+");
	        
	    Image imageDrink4 = new Image("/sprite.png", true); 
	    ImageView imageDrink4View = new ImageView(imageDrink4);
	    imageDrink4View.setFitHeight(70);
	    imageDrink4View.setFitWidth(70);
	    imageDrink4View.setPreserveRatio(true);  
	    Text pricePerDrink4 = new Text("€" + String.valueOf(drinkPrice));
		numberLeftedDrink4Field = new Text("20");
	    numberChooseDrink4Field = new TextField("0");
	    numberChooseDrink4Field.setEditable(false);
	    numberChooseDrink4Field.setPrefWidth(30);
	    Button minusButtonDrink4 = new Button("-");
	    Button plusButtonDrink4 = new Button("+");
	        
	    Text totalPrice = new Text("Total price: €");
	    totalPriceField = new Text("0");
	    Text accountBalance = new Text("Account Balance: €");
	    accountBalanceField = new Text("0");
	        
	    Button topUpBalance = new Button("TopUp / Pay");
	    Button confirmPurchase = new Button("Confirm");
	    Button cancelPurchase = new Button("Cancel");
	    Button giveChangeToggle = new Button("No Change");
	    Button back = new Button("Back <");
	    Button help = new Button("Help ?");
	    Button serviceRequest = new Button("Service request");
	        
	    minusButtonDrink1.setOnAction(e -> decrementValue(numberChooseDrink1Field, numberLeftedDrink1Field));
	    plusButtonDrink1.setOnAction(e -> incrementValue(numberChooseDrink1Field, numberLeftedDrink1Field));
	    minusButtonDrink2.setOnAction(e -> decrementValue(numberChooseDrink2Field, numberLeftedDrink2Field));
	    plusButtonDrink2.setOnAction(e -> incrementValue(numberChooseDrink2Field, numberLeftedDrink2Field));
	    minusButtonDrink3.setOnAction(e -> decrementValue(numberChooseDrink3Field, numberLeftedDrink3Field));
	    plusButtonDrink3.setOnAction(e -> incrementValue(numberChooseDrink3Field, numberLeftedDrink3Field));
	    minusButtonDrink4.setOnAction(e -> decrementValue(numberChooseDrink4Field, numberLeftedDrink4Field));
	    plusButtonDrink4.setOnAction(e -> incrementValue(numberChooseDrink4Field, numberLeftedDrink4Field));

	    GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
			
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(10);
			
		grid.add(priceHeader,  1, 0);
		grid.add(amountOfStockHeader, 2, 0);
		grid.add(amountOfOrderHeader, 4, 0);
		grid.add(imageDrink1View,  0, 1);
		grid.add(pricePerDrink1, 1, 1);
		grid.add(numberLeftedDrink1Field, 2, 1);
		grid.add(minusButtonDrink1,  3, 1);
		grid.add(numberChooseDrink1Field, 4, 1);
		grid.add(plusButtonDrink1, 5, 1);
		
		grid.add(imageDrink2View,  0, 2);
		grid.add(pricePerDrink2, 1, 2);
		grid.add(numberLeftedDrink2Field, 2, 2);
		grid.add(minusButtonDrink2,  3, 2);
		grid.add(numberChooseDrink2Field, 4, 2);
		grid.add(plusButtonDrink2, 5, 2);
		
		grid.add(imageDrink3View,  0, 3);
		grid.add(pricePerDrink3, 1, 3);
		grid.add(numberLeftedDrink3Field, 2, 3);
		grid.add(minusButtonDrink3,  3, 3);
		grid.add(numberChooseDrink3Field, 4, 3);
		grid.add(plusButtonDrink3, 5, 3);
		
		grid.add(imageDrink4View,  0, 4);
		grid.add(pricePerDrink4, 1, 4);
		grid.add(numberLeftedDrink4Field, 2, 4);
		grid.add(minusButtonDrink4,  3, 4);
		grid.add(numberChooseDrink4Field, 4, 4);
		grid.add(plusButtonDrink4, 5, 4);
        
		grid.add(totalPrice, 0, 5);
		grid.add(totalPriceField, 1, 5);
		grid.add(accountBalance, 0, 6);
		grid.add(accountBalanceField, 1, 6);
		grid.add(topUpBalance, 0, 8);
		grid.add(confirmPurchase, 1, 8);
		grid.add(cancelPurchase, 2, 8);
		grid.add(giveChangeToggle, 3, 8);
		grid.add(back, 0, 10);
		grid.add(help, 1, 10);
		grid.add(serviceRequest, 2, 10);
		grid.add(greetingWithCustomer, 2, 11);
		
		// Style for font headers
		priceHeader.setStyle("-fx-font: normal bold 15px 'serif' ");
		amountOfStockHeader.setStyle("-fx-font: normal bold 15px 'serif' ");
		amountOfOrderHeader.setStyle("-fx-font: normal bold 15px 'serif' ");
		
		if (isGuest) {
			accountBalanceField.setText("0");
		} else {
			accountBalanceField.setText(String.valueOf(user.getAccountBalance()));
		}
		
		// Check and display a warning about low user balance
		if (!isGuest && user.getAccountBalance() < 2.0) {
			showAlert("Low Balance, your balance is less than €2.00. Top up it please!");
		}
		
		// Handler for a Back button and go to Main window
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
        
		// Handler for Help button
        help.setOnAction(event -> {
            // Display the terms and conditions in a pop-up
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Vending Machine Help:");
            alert.setHeaderText("Help:");
            alert.getDialogPane().setContent(new Text(
                    "\nWelcome to the Vending Machine!\n\n" +
                    "1. Select a drink by clicking the '+' and '-' buttons next to each drink.\n" +
                    "2. Your selected drinks and the total price will be displayed.\n" +
                    "3. To top up your account balance, click the 'TopUp' button.\n" +
                    "4. Confirm your purchase by clicking the 'Confirm' button.\n" +
                    "5. If you change your mind, use the 'Cancel' button to clear your selection.\n" +
                    "6. Need assistance? Click the 'Service request' button.\n" +
                    "7. To return to the main menu, click the 'Back <' button.\n" +
                    "8. If you have questions, click the 'Help ?' button for assistance."));
            alert.showAndWait();
        });
        
        // Handler for Service Request button
        serviceRequest.setOnAction(e -> {
        	TextInputDialog dialog = new TextInputDialog();
        	dialog.setHeaderText("Service Request");
        	dialog.setContentText("Enter a brief discription of the fault:");
        	
        	// Get input for request
        	Optional<String> result = dialog.showAndWait();
        	result.ifPresent(description -> {
       		MaintenanceLog.addServiceRequest(description);
       		
        	showAlert("Thank you. Service request submitted!");
	        });
	        	
	    });
	    
        // Handler for Top up / Pay button
	    topUpBalance.setOnAction(e -> {
	    	TextInputDialog dialog = new TextInputDialog();
        	dialog.setHeaderText("Enter the amount to top up / pay:");
        	dialog.setContentText("Amount:");
	        
        	// Get input for top up amount
        	Optional<String> result = dialog.showAndWait();
        	result.ifPresent(amount ->{
	        	try {
	        		double topUpAmount = Double.parseDouble(amount);
	        		newBalance = isGuest ? topUpAmount : user.getAccountBalance() + topUpAmount;
	        		if (!isGuest) {
	        			user.setAccountBalance(newBalance);	        			
	        		}
	        		accountBalanceField.setText(String.valueOf(newBalance));
	        		showAlert("You have entered money to pay.");
	        	} catch (NumberFormatException ex) {
	        		showAlert("Invalid input. Please enter a valid number");
	        	}	        		
        	});

	    });
	          
		// Handler for Confirm button	
	    confirmPurchase.setOnAction(e -> {
            double amountEntered = Double.parseDouble(accountBalanceField.getText());
        	if (isGuest) {
        		if (amountEntered >= totalAmount) {
	        		if (amountEntered > totalAmount && giveChange) {
		        		showAlert("This vending machine does not give change. You can press button 'No Change' and make a purchase.");
		        	} else {
		        		double change = amountEntered - totalAmount;
		        		unreturnedChange += change;
		        		
		        		dispenseDrink();
		        		accountBalanceField.setText("0");
		    	    	gotAmountOfCoins +=  (amountEntered - change);
		    	    	System.out.println("Amount of money spender for customers " + gotAmountOfCoins + " unreturned hange " + unreturnedChange);
		    	    	
		        	}
        		} else {
                    showAlert("Insufficient funds. Please enter the correct amount or more.");
        		}
        	} else {
        		if (amountEntered < totalAmount) {
                    showAlert("Insufficient funds. Please enter the correct amount or more.");
        		} else {
        			TotalUsersSpends += totalAmount;
        			gotAmountOfCoins += totalAmount;
        			newBalance = amountEntered - totalAmount;
        			accountBalanceField.setText(String.valueOf(df.format(newBalance)));
        			user.setAccountBalance(newBalance);
	    	    	System.out.println("Amount of money spender for customers " + gotAmountOfCoins + " newBalance = " + df.format(newBalance));
        			dispenseDrink();
        			
        		}
        	}
        });
	    
	    // Handler for the Cancel button 
        cancelPurchase.setOnAction(e -> {
        	if (isGuest) {
        		accountBalanceField.setText("0");
        	}         	
        });
        
        // Handler for the No change button 
        giveChangeToggle.setOnAction(event -> {
		    if (!giveChange) {
			    giveChange = true;
			    showAlert("No Change: Disabled");
			} else {
		        giveChange = false;
		        showAlert("No Change: Enabled");
		    }
		});
	    
        // Logged user cannot see the Cancel and No change buttons
        if (!isGuest) {
            cancelPurchase.setVisible(false);
            giveChangeToggle.setVisible(false);
        }
       
        Scene scene = new Scene(grid, 500, 700);
        primaryStage.setTitle("Vending Machine");
		primaryStage.setScene(scene);
		primaryStage.show();
}

	private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
	}
	
	// Increasing the amount of the selected drink
	private static void incrementValue(TextField c, Text l) {
	    int currentValue = Integer.parseInt(c.getText());

	    try {
	        int leftAmount = Integer.parseInt(l.getText());

	        // Limit the value to 20
	        int newValue = Math.min(currentValue + 1, 20);

	        // Update the text field with the new value
	        c.setText(String.valueOf(newValue));

	        // Update the left amount
	        int newLeftAmount = Math.max(leftAmount - 1, 0);

	        // Set the text based on the left amount
	        if (newLeftAmount > 0) {
	            l.setText(String.valueOf(newLeftAmount));
	        } else {
	            l.setText("Out of stock!");
	        }

	        // Update the total amount
	        totalAmount += drinkPrice;

	        // Update the total price field
	        totalPriceField.setText(df.format(totalAmount));

	        // Handle the case when the left amount is 0
	        if (newLeftAmount == 0) {
	            System.out.println("Empty");
	        }
	    } catch (NumberFormatException e) {
	        // Handle the case where the text is not a valid integer
	        System.out.println("Invalid left amount format");
	    }
	}

	// Reduce the amount of the selected drink     
    private static void decrementValue(TextField c, Text l) {
        int value = Integer.parseInt(c.getText());
        
        
        if (l.getText().equals("Out of stock!")) {
        	l.setText(String.valueOf(0)); 
        } else if (value > 0) {
        	int leftAmount = Integer.parseInt(l.getText());
        	totalAmount -= drinkPrice;
        	value--;
            c.setText(String.valueOf(value));
            l.setText(String.valueOf(leftAmount + 1));            
        }  
	    
        totalPriceField.setText(df.format(totalAmount));
    }
	
    // Dispense a drink
    private static void dispenseDrink() {
    	showAlert("Drink Dispensed!");
    	totalAmount = 0;
    	totalPriceField.setText("0");
    	// Clear after purchase chooser drinks field 
    	numberChooseDrink1Field.setText("0");
    	numberChooseDrink2Field.setText("0");
    	numberChooseDrink3Field.setText("0");
    	numberChooseDrink4Field.setText("0");	
    }
	 
    // Get the remaining quantity of a drink
	public static int getRemainingQuantity(String drinkName) {
        switch (drinkName) {
            case "Cola":
                return getRemainingQuantityHelper(numberLeftedDrink1Field);
            case "Fanta":
                return getRemainingQuantityHelper(numberLeftedDrink2Field);
            case "Pepsi":
                return getRemainingQuantityHelper(numberLeftedDrink3Field);
            case "Sprite":
                return getRemainingQuantityHelper(numberLeftedDrink4Field);
            default:
                return 0;
        }
    }

	private static int getRemainingQuantityHelper(Text field) {
        return (field != null) ? Integer.parseInt(field.getText()) : 0;
    }

    // Method to add drinks to the vending machine
	public static void addDrinksToMachine(String drinkName, int quantity) {
       switch (drinkName) {
            case "Cola":
                incrementValue(numberChooseDrink1Field, numberLeftedDrink1Field, quantity);
                break;
            case "Fanta":
                incrementValue(numberChooseDrink2Field, numberLeftedDrink2Field, quantity);
                break;
            case "Pepsi":
                incrementValue(numberChooseDrink3Field, numberLeftedDrink3Field, quantity);
                break;
            case "Sprite":
                incrementValue(numberChooseDrink4Field, numberLeftedDrink4Field, quantity);
                break;
        }
    }
	// Increase the value of a drink with quantity
	private static void incrementValue(TextField c, Text l, int quantity) {
        if (c == null || l == null) {
            return; // Add this check to avoid NullPointerException
        }

        int value = Integer.parseInt(c.getText());
        int leftAmount = Integer.parseInt(l.getText());
        int newValue = Math.min(value + quantity, 20);
        int newLeftAmount = Math.max(leftAmount - quantity, 0);

        c.setText(String.valueOf(newValue));
        l.setText(String.valueOf(newLeftAmount));
        totalAmount += drinkPrice * quantity;
        totalPriceField.setText(df.format(totalAmount));

        if (newLeftAmount == 0) {
            l.setText("Out of stock!");
        } else {
            l.setText(String.valueOf(newLeftAmount));
        }
    }
	// Get and reset the coins in the machine    
    public static double getAndResetCoins() {
    	double amount = unreturnedChange + gotAmountOfCoins;
    	unreturnedChange = 0;
        gotAmountOfCoins = 0;
       return amount;
    }
	
    // Get and reset the total amount of coins collected 
    public static double getAndResetGotAmountOfCoins() {
    	double amount = 0;
    	amount += gotAmountOfCoins;
        return amount;
    }
    
    // Get and reset the total unreturned change
    public static double getAndResetUnreturnedChange() {
    	double amount = 0;
    	amount += unreturnedChange;	        
	    return amount;
    }
	
    // Get the total spend by users
	public static double getTotalSpend() {
		double totalSpend = 0.0;
		//double amountBalance = Double.parseDouble(totalPriceField.getText());	
		totalSpend += TotalUsersSpends;
		return totalSpend;
	} 

}