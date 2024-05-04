package realtime_stock_app.realtime_stock_app;

import java.io.IOException;

public class User{
    private String firstName;
    private String lastName;
    private String username;
    private String userpwd;
    private Portfolio portfolio;
    public User(String firstName, String lastName, String usrname, String usrpwd) {
        this.username = usrname;
        this.userpwd = usrpwd;
        this.lastName = lastName;
        this.firstName = firstName;
        portfolio = new Portfolio();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String usrname) {
        this.username = usrname;
    }

    public String getPassword() {
        return userpwd;
    }

    public void setPassword(String usrpwd) {
        this.userpwd = usrpwd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
//methods

    public void buy(int quantity, String stockTicker) throws IOException, InterruptedException {

        double[] arr = PolygonService.getPriceInfo(stockTicker);
        double thePrice = arr[1];
        double openPrice = arr[0];
        // no use of stockworth here
        // double stockWorth = quantity * thePrice;
        double priceChange = (thePrice - openPrice) / openPrice;

        portfolio.addStocks(new Stock(stockTicker), quantity);

    }
    public void sell(int quantity, Stock stock){

    }

    public boolean login(String usrname, String usrpwd){
        //update
        return true;
    }

    public void logout(){

    }




}