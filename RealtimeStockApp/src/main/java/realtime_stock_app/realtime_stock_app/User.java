package realtime_stock_app.realtime_stock_app;

import java.io.IOException;

public class User{
    private String userID;
    private String username;
    private String userpwd;
    private String email;
    private Portfolio portfolio;
    public User(String userID, String usrname, String usrpwd) {
        this.userID = userID;
        this.username = usrname;
        this.userpwd = usrpwd;
        portfolio = new Portfolio();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsrname() {
        return username;
    }

    public void setUsrname(String usrname) {
        this.username = usrname;
    }

    public String getUsrpwd() {
        return userpwd;
    }

    public void setUsrpwd(String usrpwd) {
        this.userpwd = usrpwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //methods

    public void buy(int quantity, String stockTicker) throws IOException, InterruptedException {

        double[] arr = PolygonService.getPriceInfo(stockTicker);
        double thePrice = arr[1];
        double openPrice = arr[0];
        // no use of stockworth here
        // double stockWorth = quantity * thePrice;
        double priceChange = (thePrice - openPrice) / openPrice;

        portfolio.addStocks(new Stock(stockTicker, thePrice, priceChange), quantity);

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