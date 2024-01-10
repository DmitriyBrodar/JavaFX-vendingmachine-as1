import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Optional;

public class ManagerWindow {
	// Main buttons
	private static Button addDrinks, removeCoins, viewReports, maintenanceLog, back;
	private static Button selectedDrinkButton = null;
	private static double[] reportValues = {0, 0, 0};
	private static int[] reportDrinks = {0, 0, 0, 0};
	private static double[] reportRemovedMoney = {0};
	private static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	private static DecimalFormat df = new DecimalFormat("#.##", symbols);
	private static double removedMoney;
	
	public static void openManagerWindow (Stage primaryStage) {
		addDrinks = new Button("Add Drinks");
		removeCoins = new Button("Remove Coins");
		viewReports = new Button("View Reports");
		maintenanceLog = new Button("Maintenance Log");
		back = new Button("Back <");
		
		// Action buttons
		HBox hbox1 = new HBox();
		hbox1.setSpacing(10);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.getChildren().addAll(addDrinks, removeCoins, viewReports, maintenanceLog);
		
		// The Back button
		HBox hbox2 = new HBox();
		HBox.setMargin(back, new Insets(20, 20, 20, 20));
		hbox2.setAlignment(Pos.CENTER);
		hbox2.getChildren().addAll(back);
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(hbox1, hbox2);
		
		// Button to adding drink in Vending Machine
        addDrinks.setOnAction(e -> {
            // Pop-up window for adding drinks
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(primaryStage);
            popupStage.setTitle("Add Drinks");

            // Buttons for each drink
            Button colaButton = new Button("Cola");
            Button fantaButton = new Button("Fanta");
            Button pepsiButton = new Button("Pepsi");
            Button spriteButton = new Button("Sprite");

            // Text field for the quantity
            TextField quantityField = new TextField();
            quantityField.setPromptText("Enter quantity");

            // Confirm and erase buttons
            Button confirmButton = new Button("Confirm");
            Button eraseButton = new Button("Erase");

            // Set the layout
            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(10));

            HBox buttonsBox = new HBox(10, colaButton, fantaButton, pepsiButton, spriteButton);
            buttonsBox.setAlignment(Pos.CENTER);

            HBox quantityBox = new HBox(10, new Label("Quantity:"), quantityField);
            quantityBox.setAlignment(Pos.CENTER);

            HBox actionsBox = new HBox(10, confirmButton, eraseButton);
            actionsBox.setAlignment(Pos.CENTER);

            layout.getChildren().addAll(buttonsBox, quantityBox, actionsBox);

            // Actions for drink buttons
            colaButton.setOnAction(event -> {
                quantityField.setText(String.valueOf(VendingMachine.getRemainingQuantity("Cola")));
                selectedDrinkButton = colaButton;
            });
            
            fantaButton.setOnAction(event -> {
                quantityField.setText(String.valueOf(VendingMachine.getRemainingQuantity("Fanta")));
                selectedDrinkButton = fantaButton;
            });
            
            pepsiButton.setOnAction(event -> {
                quantityField.setText(String.valueOf(VendingMachine.getRemainingQuantity("Pepsi")));
                selectedDrinkButton = pepsiButton;
            });
            
            spriteButton.setOnAction(event -> {
                quantityField.setText(String.valueOf(VendingMachine.getRemainingQuantity("Sprite")));
                selectedDrinkButton = spriteButton;
            });
            
            // Confirmation that drinks are being added
            confirmButton.setOnAction(event -> {
                try {
                    String drinkName = getSelectedDrink(buttonsBox);
                    if (drinkName != null) {
                        int quantity = Integer.parseInt(quantityField.getText().trim());

                        // Validate input and add drinks
                        if (quantity > 0 && quantity <= 20) {
                            VendingMachine.addDrinksToMachine(drinkName, quantity);
                            showAlert("Added drinks to the vending machine!");
                            popupStage.close();
                        } else {
                            showAlert("Invalid quantity. Please enter a value between 1 and 20.");
                        }
                    } else {
                        showAlert("Please select a drink.");
                    }
                } catch (NumberFormatException ex) {
                    showAlert("Invalid input. Please enter a valid number.");
                }
            });
            
            // Clear number of drinks
            eraseButton.setOnAction(event -> {
                quantityField.clear();
            });

            Scene scene = new Scene(layout);
            popupStage.setScene(scene);
            popupStage.showAndWait();
        });
        
        // Remove coins from Vending Machine
		removeCoins.setOnAction(e -> {
			System.out.println("Pressed Remove Coins");
			removedMoney = VendingMachine.getAndResetCoins();

		    showAlert("Coins removed successfully!" + "\n\nRemoved money: €" + df.format(removedMoney));
		    
		});
		
		// Create view report
		viewReports.setOnAction(e -> {
			double salesAmount = VendingMachine.getAndResetGotAmountOfCoins();
		    double unreturnedChangeAmount = VendingMachine.getAndResetUnreturnedChange();
		    double withdrawnAmount = salesAmount + unreturnedChangeAmount;
		    
		    reportRemovedMoney[0] += removedMoney;
		    
			// Array of paid money
		    reportValues[0] += salesAmount;
		    reportValues[1] += unreturnedChangeAmount;
		    reportValues[2] += withdrawnAmount;
		    
		    int colaSold = 20 - VendingMachine.getRemainingQuantity("Cola");
		    int fantaSold = 20 - VendingMachine.getRemainingQuantity("Fanta");
		    int pepsiSold = 20 - VendingMachine.getRemainingQuantity("Pepsi");
		    int spriteSold = 20 - VendingMachine.getRemainingQuantity("Sprite");
		    
		    // Array of drinks sold
		    reportDrinks[0] += colaSold;
		    reportDrinks[1] += fantaSold;
		    reportDrinks[2] += pepsiSold;
		    reportDrinks[3] += spriteSold;

		    int totalUsers = UserDataManager.getTotalUsers();
		    double totalSpend = VendingMachine.getTotalSpend();
			
		    ButtonType clearReportButton = new ButtonType("Clear Report", ButtonBar.ButtonData.OK_DONE);
		    
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Sales Report");
		    alert.setHeaderText(null);
		  
		    alert.setContentText("Sales Amount: €" + df.format(reportValues[0]) + "\n" + "Unreturned Change Amount: €" + df.format(reportValues[1]) + "\n" + "Revenue Amount: €" + df.format(reportValues[2]) + "\n\n" +
		    		"Quantity of Cola Sold: " + reportDrinks[0] + "\n" + "Quantity of Fanta Sold: " + reportDrinks[1] + "\n" + "Quantity of Pepsi Sold: " + reportDrinks[2] + "\n" + "Quantity of Sprite Sold: " + reportDrinks[3] + "\n\n" +
		    		"Removed money: €" + df.format(reportRemovedMoney[0]) + "\n\n" +
		    		"Number of Users: " + totalUsers + "\n" + "Total Spend by Users: €" + df.format(totalSpend));
		    //alert.showAndWait();
			System.out.println("Pressed View Reports");
			
		   // Clear the report data
		    alert.getButtonTypes().add(clearReportButton);

		    // Show the alert and get the clicked button
		    Optional<ButtonType> result = alert.showAndWait();

		    if (result.isPresent() && result.get() == clearReportButton) {
		        showAlert("Report data cleared!");
		    }
		    

		});
		
		// Action for Maintenance Log button
		maintenanceLog.setOnAction(e -> {
		
			System.out.println("Pressed Maintenance Log");
			openMaintenanceLogWindow(primaryStage);
		});
		
		// Action for Back button
        back.setOnAction(e -> {
            primaryStage.close();
            
            try {
                MainApp mainApp = new MainApp();
                mainApp.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
		Scene scene = new Scene(vbox, 500, 400);
		primaryStage.setTitle("Manager");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Open the Maintenance log window
	private static void openMaintenanceLogWindow(Stage primaryStage) {
        MaintenanceLog.displayLogs(primaryStage);
		
	}
	
	// Get the selected drink from the button box
	private static String getSelectedDrink(HBox buttonsBox) {
	    // Method to get the selected drink from the button box
	    return (selectedDrinkButton != null) ? selectedDrinkButton.getText() : null;
	}
	

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
}

