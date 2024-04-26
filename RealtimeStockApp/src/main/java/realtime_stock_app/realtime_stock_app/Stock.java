package realtime_stock_app.realtime_stock_app;

import java.io.IOException;

public class Stock {
    private String symbol;
    private double currentPrice;
    private double priceChangePercentage;

    /**
     * Constructor for the Stock class.
     *
     * @param symbol The symbol of the stock.
     * @param currentPrice The current trading price of the stock.
     * @param priceChangePercentage The percentage change in price of the stock.
     */
    public Stock(String symbol) throws IOException, InterruptedException {
        this.symbol = symbol;
        this.currentPrice = getCurrentPrice(symbol);
        this.priceChangePercentage = getPriceChange(symbol);
    }

    private double getCurrentPrice(String symbol) throws IOException, InterruptedException {
        double[] close = PolygonService.getPriceInfo(symbol);
        return close[1];
    }

    public double getPriceChange(String symbol) throws IOException, InterruptedException {
        double[] array = PolygonService.getPriceInfo(symbol);
        return (array[1] - array[0]) / array[0];
    }

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getPriceChangePercentage() {
        return priceChangePercentage;
    }

    // Setters
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setPriceChangePercentage(double priceChangePercentage) {
        this.priceChangePercentage = priceChangePercentage;
    }

    @Override
    public String toString() {
        return String.format("%s - Current Price: $%.2f, Change: %.2f%%", symbol, currentPrice, priceChangePercentage);
    }
}