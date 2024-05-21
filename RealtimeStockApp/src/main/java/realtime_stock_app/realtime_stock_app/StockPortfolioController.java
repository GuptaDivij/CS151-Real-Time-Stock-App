package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

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
    private String username;
    private StockLoginController loginController;

    private double[] priceArr; // Placeholder for actual stock price data

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Placeholder for initialization
    }

    public void setLoginController(StockLoginController controller) {
        this.loginController = controller;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void handleStockSearch(MouseEvent mouseEvent) {
        String stockTickerUser = searchBar.getText().toUpperCase();
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
            Alerter.showAlert("Error", "Stock not found. Please try again.");
            return false;
        }
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

    public void addStockToPortfolio(String ticker, int quantity, double price) throws IOException, ParseException {
//        JSONArray jsonArray = null;
//        try {
//            jsonArray = FileAccessor.readJsonFile();
//            JSONObject stockObj = FileAccessor.createAddStockToPortfolioObject(ticker, quantity, price);
//            System.out.println(stockObj);
//            for (Object obj : jsonArray) {
//                org.json.simple.JSONObject user = (org.json.simple.JSONObject) obj;
//                System.out.println(user);
//                System.out.println(username);
//                if(user.containsKey(username)) {
//                    System.out.println(user);
//                    JSONArray jsonPortfolio = (JSONArray) user.get("portfolio");
//                    FileAccessor.writeToJsonFile(jsonPortfolio, stockObj);
//                }
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
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
        sellButton.setId(stockTickerText.getText());
        sellButton.setStyle("-fx-background-color: ADD8E6; -fx-background-radius: 5");

        sellButton.setOnAction(event -> openStockSellFXML(ticker, quantity, price)); //handle sell button


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
    private void openStockSellFXML(String ticker, int quantity, double price) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StockSell.fxml"));
            Parent root = loader.load();

            // If needed, pass data to the controller
            StockSellController controller = loader.getController();
            //might have to set it to current stockTicker!!!
            // Show the FXML in a new window or dialog
            Stage stage = new Stage();
            stage.setTitle("Sell Stock");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
