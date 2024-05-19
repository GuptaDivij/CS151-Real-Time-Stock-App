package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import java.io.FileReader;


public class StockSignUpController {
    private Stage stage;
    private final String fileName = "C:\\Users\\ved-j\\IdeaProjects\\CS151-Real-Time-Stock-App\\RealtimeStockApp\\src\\main\\java\\realtime_stock_app\\realtime_stock_app\\UserData.json";
    //private final String fileName = "C:\\Users\\musta\\IdeaProjects\\CS151-Real-Time-Stock-App\\RealtimeStockApp\\src\\main\\java\\realtime_stock_app\\realtime_stock_app\\UserData.json";
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

    public void showAlertWithPasswordRequirements(String title, String missingRequirement) {
        Alerter.showAlert(title, "Password does not meet the following requirement:\n" + missingRequirement);
    }

    public void handleSignUp(MouseEvent mouseEvent) throws IOException {
        // Input validation logic...
        if (firstName.getText().isEmpty()) {
            Alerter.showAlert("Error", "Please fill out the first name field.");
            return;
        }
        if (lastName.getText().isEmpty()) {
            Alerter.showAlert("Error", "Please fill out the last name field.");
            return;
        }
        if (username.getText().isEmpty()) {
            Alerter.showAlert("Error", "Please fill out the username field.");
            return;
        }

        if (password.getText().isEmpty()) {
            Alerter.showAlert("Error", "Please fill out the password field.");
            return;
        }
        if (isUsernameExists(username.getText())) {
            Alerter.showAlert("Error", "Username already exists. Please choose a different one.");
            return;
        }

        String passwordAlert = isPasswordValid(password.getText());

        // Check password requirements
        if (!passwordAlert.isEmpty()) {
            showAlertWithPasswordRequirements("Error", passwordAlert);
            return;
        }

        // Store user information
        User user = new User(firstName.getText(), lastName.getText(), username.getText(), password.getText());

        try {
            // Read JSON file...
            JSONArray jsonArray = FileAccessor.readJsonFile(fileName);

            // Create user JSON object...
            JSONObject userObj = FileAccessor.createUserJsonObject(user);

            // Write to JSON file...
            FileAccessor.writeToJsonFile(jsonArray, userObj, fileName);
        } catch (ParseException | IOException | JSONException e) {
            System.out.println(e.getMessage());
            FileAccessor.handleFileError(e);
            return;
        }

        // Navigate to login screen...
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("StockLogin.fxml"));
        root = loginLoader.load();
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    private boolean isUsernameExists(String username) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            Object object = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) object;

            // Iterate over existing users and check if username exists
            for (Object obj : jsonArray) {
                org.json.simple.JSONObject userObj = (org.json.simple.JSONObject) obj;
                org.json.simple.JSONObject existingUser = (org.json.simple.JSONObject) userObj.get(username);
                if (existingUser == null) {
                    continue; // Username exists
                }
                String existingUsername = (String) existingUser.get("username");
                if (existingUsername != null && existingUsername.equals(username)) {
                    return true; // Username exists
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return false; // Username doesn't exist or error occurred
    }
}
