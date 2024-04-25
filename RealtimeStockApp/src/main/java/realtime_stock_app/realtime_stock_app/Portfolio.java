package realtime_stock_app.realtime_stock_app;
import java.util.*;
public class Portfolio {
    private HashMap<Stock, Integer> portfolioList;

    public Portfolio(){
        portfolioList = new HashMap<>();
    }

    public void addStocks(Stock stock, int quantity){
        portfolioList.put(stock, portfolioList.getOrDefault(stock, 0) + quantity);
    }

    public HashMap<Stock, Integer> getPortfolio() {
        return portfolioList;
    }
}
