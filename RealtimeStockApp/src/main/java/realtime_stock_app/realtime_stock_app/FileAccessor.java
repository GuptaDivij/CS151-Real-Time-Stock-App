package realtime_stock_app.realtime_stock_app;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileAccessor {
    public static File file = new File("RealtimeStockApp/src/main/java/realtime_stock_app/realtime_stock_app/UserData.json");
    public static String absolutePath = file.getAbsolutePath();
    public static JSONArray readJsonFile() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(absolutePath)) {
            Object object = jsonParser.parse(reader);
            return (JSONArray) object;
        }
    }

    public static JSONObject createUserJsonObject(User user) throws JSONException {
        JSONObject userObj = new JSONObject();
        org.json.JSONArray emptyPortfolio = new org.json.JSONArray();
        JSONObject objItem = new JSONObject();
        objItem.put("first name", user.getFirstName());
        objItem.put("last name", user.getLastName());
        objItem.put("username", user.getUsername());
        objItem.put("password", user.getPassword());
        objItem.put("portfolio", emptyPortfolio);
        userObj.put(user.getUsername(), objItem);
        return userObj;
    }

    public static JSONObject createAddStockToPortfolioObject(String ticker, int quantity, double price) throws JSONException {
        JSONObject portfolioObj = new JSONObject();
        JSONObject objItem = new JSONObject();
        objItem.put("Ticker", ticker);
        objItem.put("Quantity", quantity);
        objItem.put("Price", price);

        portfolioObj.put(ticker, objItem);
        return portfolioObj;
    }

    public static void writeToJsonFile(JSONArray jsonArray, JSONObject userObj) throws IOException {
        jsonArray.add(userObj);
        try (FileWriter file = new FileWriter(absolutePath)) {
            file.write(jsonArray.toJSONString());
        }
    }

    public static void writeToPortfolioJson(JSONArray jsonArray, JSONObject userObj) throws IOException {
        jsonArray.add(userObj);
        try (FileWriter file = new FileWriter(absolutePath)) {
            file.write(jsonArray.toJSONString());
        }
    }

    public static void handleFileError(Exception e) {
        Alerter.showAlert("Failure", "Something went wrong. Please try again later.");
    }
}
