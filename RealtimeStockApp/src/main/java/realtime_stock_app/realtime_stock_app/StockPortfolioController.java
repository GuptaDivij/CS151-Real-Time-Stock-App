package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static realtime_stock_app.realtime_stock_app.PolygonService.getPriceInfo;

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
    private String currentStockTicker;

    private double[] priceArr; // Placeholder for actual stock price data

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Placeholder for initialization
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
        currentStockTicker = stockTickerInput; // Store the current stock ticker
        stockTicker.setText(stockTickerInput);
        stockPrice.setText(String.format("$%.2f", priceArr[1]));
    }

    private boolean checkIfStockExists(String stockTicker) {
        // Simulate stock checking (replace with real data fetching)
        try {
            priceArr = getPriceInfo(stockTicker); // Simulated stock price data
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
        buyController.setCurrentStockTicker(currentStockTicker); // Set the current stock ticker
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public double getCurrentStockPrice() {
        return priceArr.length > 0 ? priceArr[1] : 0.0; // Return current price or 0 if unavailable
    }

    public void addStockToPortfolio(String ticker, int quantity, double price) {
        Text stockTickerText = new Text(ticker);
        stockTickerText.setFill(Paint.valueOf("WHITE"));
        stockTickerText.setStrokeType(StrokeType.OUTSIDE);
        stockTickerText.setStrokeWidth(0.0);
        Text quantityText = new Text("Qty: " + quantity);
        quantityText.setFill(Paint.valueOf("WHITE"));
        quantityText.setStrokeType(StrokeType.OUTSIDE);
        quantityText.setStrokeWidth(0.0);
        Text priceText = new Text(String.format("Price: $%.2f", price));
        priceText.setFill(Paint.valueOf("WHITE"));
        priceText.setStrokeType(StrokeType.OUTSIDE);
        priceText.setStrokeWidth(0.0);
        double totalWorth = quantity * price;
        Text totalWorthText = new Text(String.format("Total Worth: $%.2f", totalWorth));
        totalWorthText.setFill(Paint.valueOf("WHITE"));
        totalWorthText.setStrokeType(StrokeType.OUTSIDE);
        totalWorthText.setStrokeWidth(0.0);
        Button sellButton = new Button("Sell");
        sellButton.setStyle("-fx-background-color: ADD8E6; -fx-background-radius: 5");

        VBox stockBox = new VBox(stockTickerText, priceText, quantityText, totalWorthText, sellButton);
        stockBox.setAlignment(javafx.geometry.Pos.CENTER);
        stockOnPortfolio.setPadding(new Insets(10));
        //stockOnPortfolio.setStyle("-fx-background-radius: 10");
        VBox.setMargin(stockBox, new Insets(0, 0, 10, 0)); // Bottom margin only
        stockBox.setStyle("-fx-background-color: #F28963; -fx-padding: 10;");
        stockOnPortfolio.setAlignment(javafx.geometry.Pos.CENTER);
        stockOnPortfolio.getChildren().add(stockBox);
        stockOnPortfolio.setVisible(true);
    }
}
