package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;

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
            Alerter.showAlert("Error", "Please enter a valid number.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public void setPortfolioController(StockPortfolioController controller) {
        this.portfolioController = controller;
    }

}
