package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StockPortfolioController{

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
    private ImageView addButton;
    @FXML
    private VBox stockOnPortfolio;
    @FXML
    private Text stockTicker1;
    @FXML
    private Text stockPrice1;
    @FXML
    private Text buy;
    @FXML
    private Text sell;
    @FXML
    private Spinner<Integer> quantity;
    @FXML
    private ScrollPane scrollPane;


    private double[] priceArr;
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize UI components or bind data here
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

    public void handleStockSearch(MouseEvent mouseEvent) {
        String stockTickerUser = searchBar.getText();
        boolean stockExists = checkIfStockExists(stockTickerUser); // Check if the stock exists

        if (stockExists) {
            //If stock exists then hide the "no results" text and show the stock pane display stock details
            noResults.setVisible(false);
            stockPane.setVisible(true);
            displayStockDetails(stockTickerUser);
        } else {
            // If stock doesn't exist then hide the stock pane and show the "no results" text
            stockPane.setVisible(false);
            noResults.setVisible(true);
        }
    }

    private void displayStockDetails(String stockTickerInput) {
        if (checkIfStockExists(stockTickerInput)) {
            stockTicker.setText(stockTickerInput);
            stockPrice.setText(String.format("$%.2f", priceArr[1]));
        }
    }

    private boolean checkIfStockExists(String stockTicker) {
        try {
            priceArr = PolygonService.getPriceInfo(stockTicker); // Getting price information for the stock
            return true; // Return true if stock exists
        } catch (IOException | InterruptedException e) {
            showAlert("Error", "Stock not found. Please try again."); // Display an alert if stock not found
            return false; // Return false if stock doesn't exist
        }
    }

    private void addStockToPortfolio(String stockTicker, String stockPrice) {
        VBox stockBox = createStockBox(stockTicker, stockPrice); // Creating a stock box with name and price
        stockOnPortfolio.getChildren().add(stockBox); // Add the stock box to the portfolio
    }
    /** public void initialize(URL url, ResourceBundle resourceBundle){
        quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE));

    } **/
    private VBox createStockBox(String stockTicker, String stockPrice) {
        VBox stockBox = new VBox();
        Text stockTickerText = new Text(stockTicker);
        Text stockPriceText = new Text(stockPrice);
        Text buyButton = new Text("Buy");
        Text sellButton = new Text("Sell");
        HBox actionBox = new HBox(buyButton, sellButton);
        stockBox.getChildren().addAll(stockTickerText, stockPriceText, actionBox);
        stockBox.setSpacing(10);
        return stockBox;
    }
    
    public void handleAddToPortfolio(MouseEvent mouseEvent) throws IOException {
        stockPane.setVisible(false);
        noResults.setVisible(true);
        Stage buyStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StockBuy.fxml"));
        Parent root = loader.load();
        buyStage.setScene(new Scene(root));
        buyStage.show();

        addStockToPortfolio(stockTicker.getText(),String.format("$%.2f", priceArr[1]));

    // Method to create a VBox for a stock

    }

}
