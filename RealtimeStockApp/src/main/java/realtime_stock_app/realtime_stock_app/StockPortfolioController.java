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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import java.net.URL;
import java.io.IOException;
import javafx.scene.control.ScrollPane;
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
    public void handleStockSearch(MouseEvent mouseEvent) throws IOException, InterruptedException {
        String stockTickerUser = searchBar.getText();


        //if stock exists make stockPane visible and no results invisible

        //display latest stock price and price change and give option to "+" (add) to portfolio (right side of the scene)

        boolean stockExists = checkIfStockExists(stockTickerUser);

        if(stockExists){
            noResults.setVisible(false);
            stockPane.setVisible(true);
            displayStockDetails(stockTickerUser);
        }
        else{
            stockPane.setVisible(false);
            noResults.setVisible(true);
        }
    }

    private void displayStockDetails(String stockTickerInput) throws IOException, InterruptedException {
        if(checkIfStockExists(stockTickerInput)) {
            stockTicker.setText(stockTickerInput);
            stockPrice.setText(String.format("$%.2f",priceArr[1]));
        }
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

    /** public void initialize(URL url, ResourceBundle resourceBundle){
        quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE));

    } **/
    public void handleAddToPortfolio(MouseEvent mouseEvent) throws IOException {
        stockPane.setVisible(false);
        noResults.setVisible(true);
        Stage buyStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StockBuy.fxml"));
        Parent root = loader.load();
        buyStage.setScene(new Scene(root));
        buyStage.show();

        addStocktoPortfolio(stockTicker.getText(),String.format("$%.2f", priceArr[1]));

        //stockOnPortfolio.setVisible(true);
        VBox stockBox = new VBox();
        Text stockTickerText = new Text(stockTicker.getText());
        Text stockPriceText = new Text(String.format("$%.2f", priceArr[1]));
        Text buyButton = new Text("Buy");
        Text sellButton = new Text("Sell");
        HBox actionBox = new HBox(buyButton, sellButton);
        stockBox.getChildren().addAll(stockTickerText, stockPriceText, actionBox);
        AnchorPane content = (AnchorPane) scrollPane.getContent();
        content.getChildren().add(stockBox);
        AnchorPane.setTopAnchor(stockBox, 10.0);
        //stockTicker1.setText(stockTicker.getText());
        //stockPrice1.setText(String.format("$%.2f",priceArr[1]));
        //buy.setText("Buy");
        //sell.setText("Sell");



    }

    private void addStocktoPortfolio(String text, String format) {
        VBox newStockBox = new VBox();
        for (javafx.scene.Node node : stockOnPortfolio.getChildren()) {
            newStockBox.getChildren().add(cloneNode(node));
        }

    }

    private javafx.scene.Node cloneNode(javafx.scene.Node node) {
        javafx.scene.Node copy = null;
        if (node instanceof Text) {
            copy = new Text(((Text) node).getText());
        } else if (node instanceof AnchorPane) {
            // You can add further handling for other node types if necessary
            // Here, we're assuming the AnchorPane is the container for other nodes
            copy = new AnchorPane();
            for (javafx.scene.Node child : ((AnchorPane) node).getChildren()) {
                ((AnchorPane) copy).getChildren().add(cloneNode(child));
            }
        }
        // You can add further handling for other node types if necessary
        return copy;
    }

}
