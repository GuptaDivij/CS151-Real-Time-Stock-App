package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StockPortfolioController {

    @FXML
    private VBox stockPane;
    @FXML
    private Text noResults;
    @FXML
    private TextField searchBar;
    @FXML
    private Text stockTicker;
    @FXML
    private Text stockPrice;
    @FXML
    private VBox stockOnPortfolio;

    private double[] priceArr; // This should be linked to actual stock data fetching logic

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize components if necessary
    }

    public void handleStockSearch(MouseEvent mouseEvent) {
        String stockTickerUser = searchBar.getText();
        if (checkIfStockExists(stockTickerUser)) {
            noResults.setVisible(false);
            stockPane.setVisible(true);
            displayStockDetails(stockTickerUser);
        } else {
            stockPane.setVisible(false);
            noResults.setVisible(true);
        }
    }

    private void displayStockDetails(String stockTickerInput) {
        stockTicker.setText(stockTickerInput);
        stockPrice.setText(String.format("$%.2f", priceArr[1])); // Assuming priceArr is updated by checkIfStockExists
    }

    private boolean checkIfStockExists(String stockTicker) {
        // Simulate stock checking logic (replace with real data fetching)
        try {
            priceArr = new double[]{100.0, 150.0}; // Simulated stock price data
            return true;
        } catch (Exception e) {
            showAlert("Error", "Stock not found. Please try again.");
            return false;
        }
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        Label label = new Label(message);
        label.setWrapText(true);
        label.setMaxWidth(Double.MAX_VALUE);
        GridPane.setVgrow(label, Priority.ALWAYS);
        alert.getDialogPane().setContent(label);
        alert.getDialogPane().setPrefSize(400, 200);
        alert.showAndWait();
    }

    public void handleBuyAddButton(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StockBuy.fxml"));
        Parent root = loader.load();
        StockBuyController buyController = loader.getController();
        buyController.setPortfolioController(this);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void addStockToPortfolio(int quantity) {
        Text stockTickerText = new Text(searchBar.getText());
        Text quantityText = new Text("Qty: " + quantity);
        VBox stockBox = new VBox(stockTickerText, quantityText);
        stockBox.setStyle("-fx-background-color: F28963; -fx-padding: 10;");
        stockOnPortfolio.getChildren().add(stockBox);
        stockOnPortfolio.setVisible(true);
    }
}
