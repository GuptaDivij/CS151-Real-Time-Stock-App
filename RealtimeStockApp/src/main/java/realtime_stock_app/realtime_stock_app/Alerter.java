package realtime_stock_app.realtime_stock_app;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Alerter {
    public static void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);

        // Set the content text
        Label label = new Label(message);
        label.setWrapText(true); // Allow text to wrap
        label.setMaxWidth(Double.MAX_VALUE);
        GridPane.setVgrow(label, Priority.ALWAYS);
        alert.getDialogPane().setContent(label);

        // Set the size of the dialog
        alert.getDialogPane().setPrefSize(400, 200); // Set the preferred size as needed

        alert.showAndWait();
    }
}
