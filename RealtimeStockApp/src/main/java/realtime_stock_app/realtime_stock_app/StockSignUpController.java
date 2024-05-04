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
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.util.Random;

public class StockSignUpController {
    private Stage stage;
    private String fileName = "C:\\Users\\ved-j\\IdeaProjects\\CS151-Real-Time-Stock-App\\RealtimeStockApp\\src\\main\\java\\realtime_stock_app\\realtime_stock_app\\UserData.json";
    //private String fileName = "C:\\Users\\musta\\IdeaProjects\\CS151-Real-Time-Stock-App\\RealtimeStockApp\\src\\main\\java\\realtime_stock_app\\realtime_stock_app\\UserData.json";
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
        // Input validation logic...
        if (firstName.getText().isEmpty()) {
            showAlert("Error", "Please fill out the first name field.");
            return;
        }
        if (lastName.getText().isEmpty()) {
            showAlert("Error", "Please fill out the last name field.");
            return;
        }
        if (username.getText().isEmpty()) {
            showAlert("Error", "Please fill out the username field.");
            return;
        }

        if (password.getText().isEmpty()) {
            showAlert("Error", "Please fill out the password field.");
            return;
        }
        if (isUsernameExists(username.getText())) {
            showAlert("Error", "Username already exists. Please choose a different one.");
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
            JSONArray jsonArray = readJsonFile();

            // Create user JSON object...
            JSONObject userObj = createUserJsonObject(user);

            // Write to JSON file...
            writeToJsonFile(jsonArray, userObj);
        } catch (ParseException | IOException | JSONException e) {
            System.out.println(e.getMessage());
            handleFileError(e);
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

    private JSONArray readJsonFile() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            Object object = jsonParser.parse(reader);
            return (JSONArray) object;
        }
    }

    private JSONObject createUserJsonObject(User user) throws JSONException {
        JSONObject userObj = new JSONObject();
        JSONObject objItem = new JSONObject();
        objItem.put("first name", user.getFirstName());
        objItem.put("last name", user.getLastName());
        objItem.put("username", user.getUsername());
        objItem.put("password", user.getPassword());
        userObj.put(user.getUsername(), objItem);
        return userObj;
    }

    private void writeToJsonFile(JSONArray jsonArray, JSONObject userObj) throws IOException {
        jsonArray.add(userObj);
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonArray.toJSONString());
        }
    }

    private void handleFileError(Exception e) {
        showAlert("Failure", "Something went wrong. Please try again later.");
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
