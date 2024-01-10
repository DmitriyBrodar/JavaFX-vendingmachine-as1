import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MaintenanceLog {

    private static ObservableList<MaintenanceEntry> maintenanceLog = FXCollections.observableArrayList();
    private static ObservableList<MaintenanceEntry> repairedLog = FXCollections.observableArrayList();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // add a new service request to a maintenance log
    public static void addServiceRequest(String description) {
        MaintenanceEntry entry = new MaintenanceEntry(description);
        maintenanceLog.add(entry);
    }

    public static void handleRepair(MaintenanceEntry entry) {
        // Move the entry from maintenanceLog to repairedLog
        maintenanceLog.remove(entry);
        entry.setTimestamp(LocalDateTime.now());
        repairedLog.add(entry);
    }
    
    // display the maintenance and repaired logs
    public static void displayLogs(Stage primaryStage) {
        primaryStage.setTitle("Maintenance Log");
        
        // list view for displaying the maintenance log
        ListView<MaintenanceEntry> maintenanceListView = new ListView<>(maintenanceLog);
        maintenanceListView.setCellFactory(param -> new ListCell<MaintenanceEntry>() {
            @Override
            protected void updateItem(MaintenanceEntry item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getDescription() + " - " + item.getTimestamp());
                    
                    Button resolveButton = new Button("Resolved");
                    
                    // handler for the Resolved button
                    resolveButton.setOnAction(event -> {
                        handleRepair(item);
                        showAlert("Report marked as Resolved.");
                        refreshLogs(primaryStage);
                    });
                    
                    HBox buttonsBox = new HBox(resolveButton);
                    buttonsBox.setAlignment(Pos.CENTER);
                    setGraphic(buttonsBox);
                }
            }
        });
        
        // list view for displaying the repaired log
        ListView<MaintenanceEntry> repairedListView = new ListView<>(repairedLog);
        Text repairedLog = new Text("Repaired log");
        
        Button back = new Button("Back <");

        VBox vBox = new VBox(maintenanceListView, repairedLog, repairedListView, back);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        
        // handler for the back button to the Manager window
        back.setOnAction(e -> {
        	primaryStage.close();
        	
        	try {
        	ManagerWindow.openManagerWindow(new Stage());
        	} catch (Exception ex) {
        		ex.printStackTrace();
        	}
        	
        });

        Scene logScene = new Scene(vBox, 600, 400);
        primaryStage.setScene(logScene);
        primaryStage.show();
    }

    // refresh the logs
    private static void refreshLogs(Stage primaryStage) {
        displayLogs(primaryStage);
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Inner class representing a maintenance log entry
    private static class MaintenanceEntry {
        private String description;
        private LocalDateTime timestamp;
        
        // Constructor for creating a new entry with a description and timestamp
        public MaintenanceEntry(String description) {
            this.description = description;
            this.timestamp = LocalDateTime.now();
        }

        public String getDescription() {
            return description;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
        
        // method for correct display in ListView
        public String toString() {
            return description + " - " + timestamp.format(formatter);
        }
    }
    


}