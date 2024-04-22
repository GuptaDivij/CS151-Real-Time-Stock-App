package realtime_stock_app.realtime_stock_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load the FXML file for the main screen
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent root = loader.load();

            // Set the scene to the stage with the loaded FXML

            Scene scene = new Scene(root, 320, 240);
            primaryStage.setScene(scene);

            // Set the title of the window (stage)
            primaryStage.setTitle("Stock Tracking Application");

            // Show the stage
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // handles the exception here with user feedback, logging, etc.
        }
    }

    public static void main(String[] args) {
        // Launch the application
        launch(args);
    }
}
