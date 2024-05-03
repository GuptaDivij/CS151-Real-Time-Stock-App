package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class StockLoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void handleWelcomeOpen(MouseEvent mouseEvent) throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("StockWelcome.fxml"));
        root = welcomeLoader.load();
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
