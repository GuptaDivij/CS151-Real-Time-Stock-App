package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class StockLoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    private final String fileName = "C:\\Users\\ved-j\\IdeaProjects\\CS151-Real-Time-Stock-App\\RealtimeStockApp\\src\\main\\java\\realtime_stock_app\\realtime_stock_app\\UserData.json";
    //private final String fileName = "/Users/divijgupta/Desktop/Study/Cs151/Project/CS151-Real-Time-Stock-App/RealtimeStockApp/src/main/java/realtime_stock_app/realtime_stock_app/UserData.json";
    //private final String fileName = "C:\\Users\\musta\\IdeaProjects\\CS151-Real-Time-Stock-App\\RealtimeStockApp\\src\\main\\java\\realtime_stock_app\\realtime_stock_app\\UserData.json";

    public void handleWelcomeOpen(MouseEvent mouseEvent) throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("StockWelcome.fxml"));
        root = welcomeLoader.load();
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void handleLogin(MouseEvent mouseEvent) throws IOException, ParseException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty()) {
            Alerter.showAlert("Error", "Please fill out the username field.");
            return;
        }

        if (password.isEmpty()) {
            Alerter.showAlert("Error", "Please fill out the password field.");
            return;
        }
        // Check if username and password match
        if(!isUserExists(username, password)) {
            Alerter.showAlert("Error", "Please enter the correct credentials or go to sign up.");
            return;
        }

            FXMLLoader portfolioLoader = new FXMLLoader(getClass().getResource("StockPortfolio.fxml"));
            root = portfolioLoader.load();
            stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    private boolean isUserExists(String username, String password) throws IOException, ParseException {

        JSONArray jsonArray = null;
        try {
            jsonArray = FileAccessor.readJsonFile(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

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
        return false; // Username doesn't exist or error occurred
    }
}
