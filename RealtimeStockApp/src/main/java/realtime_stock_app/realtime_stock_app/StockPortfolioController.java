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

        //if stock exists do this else throw an error message

        Stock stk = new Stock(stockTicker);

        //display latest stock price and price change and give option to "+" (add) to portfolio (right side of the scene)

    }


}
