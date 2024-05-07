package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class StockLoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    //private final String fileName = "C:\\Users\\ved-j\\IdeaProjects\\CS151-Real-Time-Stock-App\\RealtimeStockApp\\src\\main\\java\\realtime_stock_app\\realtime_stock_app\\UserData.json";
    //private final String fileName = "/Users/divijgupta/Desktop/Study/Cs151/Project/CS151-Real-Time-Stock-App/RealtimeStockApp/src/main/java/realtime_stock_app/realtime_stock_app/UserData.json";
    private final String fileName = "C:\\Users\\musta\\IdeaProjects\\CS151-Real-Time-Stock-App\\RealtimeStockApp\\src\\main\\java\\realtime_stock_app\\realtime_stock_app\\UserData.json";

    public void handleWelcomeOpen(MouseEvent mouseEvent) throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("StockWelcome.fxml"));
        root = welcomeLoader.load();
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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


    public void handleLogin(MouseEvent mouseEvent) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty()) {
            showAlert("Error", "Please fill out the username field.");
            return;
        }

        if (password.isEmpty()) {
            showAlert("Error", "Please fill out the password field.");
            return;
        }
        // Check if username and password match
        if(!isUserExists(username, password)) {
            showAlert("Error", "Please enter the correct credentials or go to sign up.");
            return;
        }

            FXMLLoader portfolioLoader = new FXMLLoader(getClass().getResource("StockPortfolio.fxml"));
            root = portfolioLoader.load();
            stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    private boolean isUserExists(String username, String password) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            Object object = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) object;

            // Iterate over existing users and check if username exists
            for (Object obj : jsonArray) {
                org.json.simple.JSONObject userObj = (org.json.simple.JSONObject) obj;
                org.json.simple.JSONObject existingUser = (org.json.simple.JSONObject) userObj.get(username);
                if(existingUser != null) {
                    String usernameText = (String) existingUser.get("username");
                    String passwordText = (String) existingUser.get("password");
                    if(password.equals(passwordText) && username.equals(usernameText)) {
                        return true;
                    }
                }

            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return false; // Username doesn't exist or error occurred
    }
}
