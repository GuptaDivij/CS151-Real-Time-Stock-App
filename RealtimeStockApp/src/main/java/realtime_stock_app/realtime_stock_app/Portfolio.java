package realtime_stock_app.realtime_stock_app;
import java.io.IOException;
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
        if(quantity > getPortfolio().get(stock)){
            throw new Exception("Cannot sell more quantity than owned in the portfolio");
        }
        getPortfolio().put(stock, getPortfolio().get(stock)-quantity);
    }

    public double calculateNetWorth(){
        double networth = 0;
        for(Stock stock: getPortfolio().keySet()){
            networth += stock.getCurrentPrice() * getPortfolio().get(stock);
        }
        return networth;
    }

    public HashMap<Stock, Integer> getPortfolio() {
        return portfolioList;
    }
}
