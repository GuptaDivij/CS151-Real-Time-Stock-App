package realtime_stock_app.realtime_stock_app;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class StockPortfolioController {

    @FXML
    private TextField stockPane;
    @FXML
    private TextField noResults;
    @FXML
    private TextField searchBar;
    public void handleStockSearch(MouseEvent mouseEvent) throws IOException, InterruptedException {
        String stockTicker = searchBar.getText();


        //if stock exists make stockPane visible and no reults invisible


        //display latest stock price and price change and give option to "+" (add) to portfolio (right side of the scene)

        boolean stockExists = stockExists(stockTicker);

        if(stockExists){
            noResults.setVisible(false);
            stockPane.setVisible(true);
            displayStockDetails(stockTicker);
        }
        else{
            stockPane.setVisible(false);
            noResults.setVisible(true);
        }
    }

    private void displayStockDetails(String stockTicker) {

    }

    private boolean stockExists(String stockTicker) {

    }


}
