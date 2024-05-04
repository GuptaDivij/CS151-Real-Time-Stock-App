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

import java.io.IOException;
import java.util.Random;

public class StockSignUpController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void handleBackButton(MouseEvent mouseEvent) throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("StockWelcome.fxml"));
        root = welcomeLoader.load();
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String isPasswordValid(String password) {
        String finalStr = "";
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;
        boolean hasNumber = false;
        boolean isMinLength = password.length() >= 8;
        if (hasSpecialChar(password)) {
            hasSpecialChar = true;
        }
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowerCase = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            }
        }
        if(!hasUpperCase) {
            finalStr += "UpperCaseCharacterMissing";
        }
        if(!hasLowerCase) {
            finalStr += "\nLowerCaseCharacterMissing";
        }
        if(!hasSpecialChar) {
            finalStr += "\nSpecialCharacterMissing";
        }
        if(!hasNumber) {
            finalStr += "\nNumberCharacterMissing";
        }
        if(!isMinLength) {
            finalStr += "\nMinimum8CharactersRequired";
        }
        return finalStr;
    }

    public boolean hasSpecialChar(String password) {
        return !password.matches("[A-Za-z0-9 ]*");
    }


    public String generateUserID(String firstName, String lastName) {
        Random random = new Random();
        StringBuilder username = new StringBuilder((firstName.charAt(0) + "" + lastName.charAt(0)).toUpperCase() + "-");
        for (int i = 0; i < 4; i++) {
            username.append(random.nextInt(10));
        }
        return username.toString();
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

    public void showAlertWithPasswordRequirements(String title, String missingRequirement) {
        showAlert(title, "Password does not meet the following requirement:\n" + missingRequirement);
    }

    public void handleSignUp(MouseEvent mouseEvent) throws IOException {

        if(firstName.getText().isEmpty()) {
            showAlert("Alert", "Please make sure that you put your first name.");
            return;
        }
        if(lastName.getText().isEmpty()) {
            showAlert("Alert", "Please make sure that you put your last name.");
            return;
        }
        if(username.getText().isEmpty()) {
            showAlert("Alert", "Please make sure that you have a username.");
            return;
        }
        if(password.getText().isEmpty()) {
            showAlert("Alert", "Please make sure that have a password.");
            return;
        }

        String passwordAlert = isPasswordValid(password.getText());

        // Check password requirements
        if (!passwordAlert.isEmpty()) {

            showAlertWithPasswordRequirements("Error", passwordAlert);
            return;
        }

        // Generate username
        String userID = generateUserID(firstName.getText(), lastName.getText());

        // Store user information
        User user = new User(firstName.getText(), lastName.getText(), username.getText(), password.getText());

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("StockLogin.fxml"));
        root = loginLoader.load();
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
