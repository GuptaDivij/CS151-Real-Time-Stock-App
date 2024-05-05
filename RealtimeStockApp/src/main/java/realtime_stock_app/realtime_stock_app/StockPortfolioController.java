package realtime_stock_app.realtime_stock_app;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class StockPortfolioController {
    @FXML
    private TextField searchBar;
    public void handleStockSearch(MouseEvent mouseEvent) throws IOException, InterruptedException {
        String stockTicker = searchBar.getText();
        try {
            Stock stock = new Stock(stockTicker);

            // Display stock information
            stockSymbolLabel.setText("Symbol: " + stock.getSymbol());
            currentPriceLabel.setText("Current Price: $" + stock.getCurrentPrice());
            priceChangeLabel.setText("Price Change: " + String.format("%.2f%%", stock.getPriceChange() * 100));

            // Enable the button to add to portfolio
            addToPortfolioButton.setDisable(false);
        } catch (Exception e) {
            // If stock doesn't exist, display an error message
            stockSymbolLabel.setText("Error: Stock not found");
            currentPriceLabel.setText("");
            priceChangeLabel.setText("");
            addToPortfolioButton.setDisable(true);
        }

        //if stock exists do this else throw an error message


        //display latest stock price and price change and give option to "+" (add) to portfolio (right side of the scene)

    }


}
