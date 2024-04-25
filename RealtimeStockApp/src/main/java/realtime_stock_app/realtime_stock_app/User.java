package realtime_stock_app.realtime_stock_app;

import java.io.IOException;

public class User{
    private String userID;
    private String usrname;
    private String usrpwd;
    private String email;
    private Portfolio portfolio;
    public User(String userID, String usrname, String usrpwd) {
        this.userID = userID;
        this.usrname = usrname;
        this.usrpwd = usrpwd;
        portfolio = new Portfolio();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getUsrpwd() {
        return usrpwd;
    }

    public void setUsrpwd(String usrpwd) {
        this.usrpwd = usrpwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //methods

    public void buy(int quantity, String stockTicker) throws IOException, InterruptedException {

        String[] arr = PolygonService.getPriceInfo(stockTicker);
        double thePrice = extractCloseValue(arr);
        double openPrice = extractOpenValue(arr);
        double stockWorth = quantity * thePrice;
        double priceChange = (thePrice - openPrice) / openPrice;

        portfolio.addStocks(new Stock(stockTicker, thePrice, priceChange, stockWorth), quantity);



    }
    public static double extractCloseValue(String[] arr) {
        for (String s : arr) {
            if (s.startsWith("\"close\"")) {
                String[] parts = s.split(":");
                String valueStr = parts[1].trim().replaceAll("[^\\d.]", ""); // Extracting numeric part
                return Double.parseDouble(valueStr);
            }
        }
        return 0;
    }
    public static double extractOpenValue(String[] arr){
        for (String s : arr) {
            if (s.startsWith("\"open\"")) {
                String[] parts = s.split(":");
                String valueStr = parts[1].trim().replaceAll("[^\\d.]", ""); // Extracting numeric part
                return Double.parseDouble(valueStr);
            }
        }
        return Double.NaN;
    }
    public void sell(int quantity, Stock stock){

    }

    public boolean login(String usrname, String usrpwd){
        //update
        return true;
    }

    public void logout(){

    }

    public static void main(String[] args){
        User test = new User("123", "Nish", "pwd");
        //test.buy(2, "AAPL");

    }


}