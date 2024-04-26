package realtime_stock_app.realtime_stock_app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockFrontEnd extends Application {

    private List<User> users = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Connectify");

        // Welcome Screen
        GridPane welcomeGrid = new GridPane();
        welcomeGrid.setPadding(new Insets(20, 20, 20, 20));
        welcomeGrid.setVgap(20);
        welcomeGrid.setHgap(20);
        welcomeGrid.setAlignment(Pos.CENTER);

        Label welcomeText = new Label("Welcome to Stock App!");
        welcomeGrid.add(welcomeText, 1, 0);
        GridPane.setHalignment(welcomeText, Pos.CENTER.getHpos());

        // Logo image
        //Image logoImage = new Image("C:\\Users\\ved-j\\IdeaProjects\\cs151-assignment3\\src\\main\\java\\edu\\sjsu\\cs\\assignment3\\logo.png");
        //ImageView logoImageView = new ImageView(logoImage);
        //logoImageView.setPreserveRatio(true);
        //logoImageView.fitWidthProperty().bind(primaryStage.widthProperty().multiply(0.5)); // Adjust the multiplier as needed
        //welcomeGrid.add(logoImageView, 0, 1, 2, 1);

        // Signup button
        Button signupButton = new Button("Signup");
        signupButton.setOnAction(e -> {
            // Show signup screen
            showSignupScreen(primaryStage);
        });
        welcomeGrid.add(signupButton, 1, 2);

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            // Show login screen
            showLoginScreen(primaryStage);
        });
        welcomeGrid.add(loginButton, 1, 3);
        GridPane.setHalignment(signupButton, Pos.CENTER.getHpos());
        GridPane.setHalignment(loginButton, Pos.CENTER.getHpos());
        Scene welcomeScene = new Scene(welcomeGrid, 400, 400);

        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    public void showSignupScreen(Stage primaryStage) {
        Stage signupStage = new Stage();
        signupStage.setTitle("Signup");

        GridPane signupGrid = new GridPane();
        signupGrid.setPadding(new Insets(20, 20, 20, 20));
        signupGrid.setVgap(10);
        signupGrid.setHgap(10);

        // Labels and fields
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        signupGrid.add(firstNameLabel, 0, 0);
        signupGrid.add(firstNameField, 1, 0);

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        signupGrid.add(lastNameLabel, 0, 1);
        signupGrid.add(lastNameField, 1, 1);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        signupGrid.add(usernameLabel, 0, 2);
        signupGrid.add(usernameField, 1, 2);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        signupGrid.add(passwordLabel, 0, 3);
        signupGrid.add(passwordField, 1, 3);

        Button signupConfirmButton = new Button("Signup");
        signupConfirmButton.setOnAction(e -> {
            // Signup logic
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String passwordAlert = isPasswordValid(password);
            String emailAlert = "Email is invalid";

            // Check password requirements
            if (!passwordAlert.isEmpty()) {

                showAlertWithPasswordRequirements("Error", passwordAlert);
                return;
            }

            // Generate username
            String userID = generateUserID(firstName, lastName);

            // Store user information
            User user = new User(userID, username, password);
            users.add(user);

            // Show username to the user
            showAlert("Success", "Signup successful!\nYour username is: " + username);

            // Close signup stage
            signupStage.close();

            // Show welcome screen
            primaryStage.show();
        });
        Label passwordReqs = new Label("Make sure that your password has the following:\nAt least 8 characters\nOne upper case character\nOne lower case character\nOne special character\nOne number character.");
        signupGrid.add(passwordReqs, 1,4);
        signupGrid.add(signupConfirmButton, 1, 5);

        // Add back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            // Close signup stage
            signupStage.close();

            // Show welcome screen
            primaryStage.show();
        });
        signupGrid.add(backButton, 0, 5);

        Scene signupScene = new Scene(signupGrid, 400, 400);
        signupStage.setScene(signupScene);
        signupStage.show();
    }

    public void showLoginScreen(Stage primaryStage) {
        Stage loginStage = new Stage();
        loginStage.setTitle("Login");

        GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(20, 20, 20, 20));
        loginGrid.setVgap(10);
        loginGrid.setHgap(10);

        // Labels and fields
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        loginGrid.add(usernameLabel, 0, 0);
        loginGrid.add(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        loginGrid.add(passwordLabel, 0, 1);
        loginGrid.add(passwordField, 1, 1);

        Button loginConfirmButton = new Button("Login");
        loginConfirmButton.setOnAction(e -> {
            // Login logic
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Check if username and password match
            boolean loggedIn = false;
            for (User user : users) {
                if (user.getUsrname().equals(username) && user.getUsrpwd().equals(password)) {
                    loggedIn = true;
                    // Show user profile
                    showAlert("Success", "Welcome back!");
                    // Close login stage
                    loginStage.close();
                    break;
                }
            }

            if (!loggedIn) {
                showAlert("Error", "Invalid username or password.");
            }
        });
        loginGrid.add(loginConfirmButton, 1, 2);

        // Add back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            // Close login stage
            loginStage.close();

            // Show welcome screen
            primaryStage.show();
        });
        loginGrid.add(backButton, 0, 2);

        Scene loginScene = new Scene(loginGrid, 400, 400);
        loginStage.setScene(loginScene);
        loginStage.show();
    }

    public String isPasswordValid(String password) {
        String finalStr = "";
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialChar = false;
        boolean hasNumber = false;
        boolean isMinLength = password.length() >= 8;
        if (isSpecialChar(password)) {
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

    public boolean isSpecialChar(String password) {
        return !password.matches("[A-Za-z0-9 ]*");
    }

    public boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
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

    public static void main(String[] args) {
        launch(args);
    }
}
