package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.bson.json.JsonParseException;
import org.json.JSONObject;

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

    public void handleWelcomeOpen(MouseEvent mouseEvent) throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("StockWelcome.fxml"));
        root = welcomeLoader.load();
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void handleLogin(MouseEvent mouseEvent) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if username and password match
        boolean loggedIn = false;

            FXMLLoader portfolioLoader = new FXMLLoader(getClass().getResource("StockPortfolio.fxml"));
            root = portfolioLoader.load();
            stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
