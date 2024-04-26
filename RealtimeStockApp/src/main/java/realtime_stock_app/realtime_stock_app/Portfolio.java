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

    public void sellStocks(Stock stock, int quantity) throws Exception {
        if(!portfolioList.containsKey(stock)){
            throw new Exception("Stock not found in the portfolio");
        }
        if(quantity > portfolioList.get(stock)) {
            throw new Exception("Cannot sell more quantity than owned in the portfolio");
        }
        portfolioList.put(stock, portfolioList.get(stock)-quantity);
    }

    public double calculateNetWorth(){
        double networth = 0;
        for(Stock stock: portfolioList.keySet()){
            networth += stock.getCurrentPrice() * portfolioList.get(stock);
        }
        return networth;
    }

    public HashMap<Stock, Integer> getPortfolio() {
        return portfolioList;
    }
}
