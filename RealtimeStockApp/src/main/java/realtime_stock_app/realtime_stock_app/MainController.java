package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;

public class MainController {

    @FXML
    private Button top10StocksButton;

    @FXML
    private Button portfolioButton;

    // This method is called when the "Top 10 Stocks" button is clicked
    @FXML
    private void handleTop10Stocks() {
        // Call the AlphaVantageService to get top 10 stocks
        // Update UI components with the data received
        // This is a placeholder for your actual implementation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Top 10 Stocks");
        alert.setHeaderText("Stocks Information");
        alert.setContentText("Top 10 stocks data goes here.");
        alert.showAndWait();
    }

    // This method is called when the "Portfolio" button is clicked
    @FXML
    private void handlePortfolio() {
        // Call the AlphaVantageService to get portfolio data
        // Update UI components with the data received
        // This is a placeholder for your actual implementation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Portfolio");
        alert.setHeaderText("Portfolio Information");
        alert.setContentText("Portfolio data goes here.");
        alert.showAndWait();
    }
}
