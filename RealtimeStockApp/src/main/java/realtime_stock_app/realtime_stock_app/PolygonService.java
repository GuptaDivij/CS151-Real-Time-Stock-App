package realtime_stock_app.realtime_stock_app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class PolygonService {


    private static final String API_KEY = "2QwN4O5Ornp3dcPXHp0Id6ygE1wuu2dK";
    public static double[] getPriceInfo(String symbol) throws IOException, InterruptedException {
        String url = String.format("https://api.polygon.io/v1/open-close/%s/2024-04-19?adjusted=true&apiKey=%s", symbol, API_KEY);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // only need closing price (current price) and opening price (to calculate change in percent)
        // open price is at index 3, close price is at index 6
        String[] responseBody = response.body().split(",");
        double openPrice = Double.parseDouble(responseBody[3].substring(7));
        double closePrice = Double.parseDouble(responseBody[6].substring(8));

        // set up and return the double array which has open and close price
        double[] openAndClosePrice = new double[2];
        openAndClosePrice[0] = openPrice;
        openAndClosePrice[1] = closePrice;
        return openAndClosePrice;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        // NOTE: Remove before presenting
        // only for testing the API, should return a double [] with open and close price
        double[] arr = getPriceInfo("AAPL");
        for(Double s: arr){
            System.out.println(s);
        }
    }
}
