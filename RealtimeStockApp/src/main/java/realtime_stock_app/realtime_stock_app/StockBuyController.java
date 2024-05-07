package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StockBuyController {

    @FXML
    private Button buyButton;
    @FXML
    private TextField numStocks;
    @FXML
    private Pane rootPane;
    private StockPortfolioController portfolioController;
    private String currentStockTicker; // To store the current stock ticker

    public void setCurrentStockTicker(String ticker) {
        currentStockTicker = ticker;
    }

    public void handleBuyStocks(MouseEvent mouseEvent) {
        String numStocksText = numStocks.getText();
        try {
            int numStocksValue = Integer.parseInt(numStocksText);
            double currentPrice = portfolioController.getCurrentStockPrice(); // Fetch the current stock price
            portfolioController.addStockToPortfolio(currentStockTicker, numStocksValue, currentPrice);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number.");
        }
    }


    public void setPortfolioController(StockPortfolioController controller) {
        this.portfolioController = controller;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
