package realtime_stock_app.realtime_stock_app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class PolygonService {


    private static final String API_KEY = "2QwN4O5Ornp3dcPXHp0Id6ygE1wuu2dK";
    public static String[] getPriceInfo(String symbol) throws IOException, InterruptedException {
        String url = String.format("https://api.polygon.io/v1/open-close/%s/2024-04-19?adjusted=true&apiKey=%s", symbol, API_KEY);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String[] responseBody = response.body().split(",");
        return responseBody;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        String[] arr = getPriceInfo("AAPL");
        for(String s: arr){
            System.out.println(s);
        }
    }

}
