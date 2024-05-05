package realtime_stock_app.realtime_stock_app;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

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

    private double[] priceArr;
    public void handleStockSearch(MouseEvent mouseEvent) throws IOException, InterruptedException {
        String stockTicker = searchBar.getText();


        //if stock exists make stockPane visible and no reults invisible

        //display
        //display latest stock price and price change and give option to "+" (add) to portfolio (right side of the scene)

        boolean stockExists = checkIfStockExists(stockTicker);

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
        this.stockTicker.setText(stockTicker);
        this.stockPrice.setText(String.valueOf(priceArr[1]));
    }

    private boolean checkIfStockExists(String stockTicker) {
        try {
            // Attempt to get price info for the stock
            priceArr = PolygonService.getPriceInfo(stockTicker);
            // If getPriceInfo didn't throw an exception, the stock exists
            return true;
        } catch (IOException | InterruptedException e) {
            // Log the exception (if needed)
            showAlert("Error", "Stock not found. Please try again.");
            // If an exception occurred, the stock doesn't exist or there was an issue
            return false;
        }
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);

        // Set the content text
        Label label = new Label(message);
        label.setWrapText(true); // Allow text to wrap
        label.setMaxWidth(Double.MAX_VALUE);
        GridPane.setVgrow(label, Priority.ALWAYS);
        alert.getDialogPane().setContent(label);

        // Set the size of the dialog
        alert.getDialogPane().setPrefSize(400, 200); // Set the preferred size as needed

        alert.showAndWait();
    }


}
