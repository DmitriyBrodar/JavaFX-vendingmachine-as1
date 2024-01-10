import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	public static Button login;
	public static Button signUp;
	public static Button guest;
	public static Button manager;
	public static Stage primaryStage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Create Buttons
		login = new Button("Login");
		signUp = new Button("Sign Up");
		guest = new Button("Guest");
		manager = new Button("Manager");
		
		// Create a Grid pane for Layout
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(10);
		
		// Add buttons to the grid
		grid.add(login, 0, 0);
		grid.add(signUp, 1, 0);
		grid.add(guest, 0, 1);
		grid.add(manager, 1, 1);
		
		// Set widths for buttons
		setWidths();
		
		// handle login button clic
		login.setOnAction(e -> {
				System.out.println("Pressed Login");
				LoginWindow.openLoginWindow(primaryStage);
		});
		
		// handl sign up button click
		signUp.setOnAction(e -> {
				System.out.println("Pressed SignUp");
				SignUp.openSignUpWindow(primaryStage);
		});
		
		// handle guest button click
		guest.setOnAction(e -> {
			
				System.out.println("Pressed Guest");
				VendingMachine.openVendingMachine(primaryStage, null, true);
		});
		
		// handle manager button click
		manager.setOnAction(e -> {
			
				System.out.println("Pressed Manager");
				ManagerWindow.openManagerWindow(primaryStage);
		});
		
		// Create a csene and set it on the  stage
		Scene scene = new Scene(grid, 300, 400);
		primaryStage.setTitle("Vending Machine");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// set widths for buttons
	private void setWidths() {
		login.setPrefWidth(70);
		signUp.setPrefWidth(70);
		guest.setPrefWidth(70);
		manager.setPrefWidth(70);
	}
	
	
}
