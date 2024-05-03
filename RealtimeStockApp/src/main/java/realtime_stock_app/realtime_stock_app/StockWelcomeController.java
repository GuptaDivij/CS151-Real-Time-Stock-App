package realtime_stock_app.realtime_stock_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StockWelcomeController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Button signupButton;
    @FXML
    public Button loginButton;

    public void handleSignUpOpen(ActionEvent actionEvent) throws IOException {
        FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("StockSignUp.fxml"));
        root = signUpLoader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleLoginOpen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("StockLogin.fxml"));
        root = loginLoader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
