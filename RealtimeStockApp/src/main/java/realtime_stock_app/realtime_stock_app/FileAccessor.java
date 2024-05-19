package realtime_stock_app.realtime_stock_app;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileAccessor {
    public static JSONArray readJsonFile(String fileName) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            Object object = jsonParser.parse(reader);
            return (JSONArray) object;
        }
    }

    public static JSONObject createUserJsonObject(User user) throws JSONException {
        JSONObject userObj = new JSONObject();
        JSONObject objItem = new JSONObject();
        objItem.put("first name", user.getFirstName());
        objItem.put("last name", user.getLastName());
        objItem.put("username", user.getUsername());
        objItem.put("password", user.getPassword());
        userObj.put(user.getUsername(), objItem);
        return userObj;
    }

    public static void writeToJsonFile(JSONArray jsonArray, JSONObject userObj, String fileName) throws IOException {
        jsonArray.add(userObj);
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonArray.toJSONString());
        }
    }

    public static void handleFileError(Exception e) {
        Alerter.showAlert("Failure", "Something went wrong. Please try again later.");
    }
}
