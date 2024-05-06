package realtime_stock_app.realtime_stock_app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class StockBuyController {

    @FXML
    private TextField numStocks;
    @FXML
    private Pane rootPane;
    private int numStocksValue;

    public void handleBuyStocks(MouseEvent mouseEvent) {
        String numStocksText = numStocks.getText();
        try {
            numStocksValue = Integer.parseInt(numStocksText);
            // Now you have the value entered the TextField as an integer
            // You can perform further actions with this value
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.close();
            //divij write the stuff to get the vbox to clone and go under scroll pane here
            //numStocksValue is the quantity
        } catch (NumberFormatException e) {
            // Handle the case where the entered value is not a valid integer
            e.printStackTrace();
        }
    }
}
