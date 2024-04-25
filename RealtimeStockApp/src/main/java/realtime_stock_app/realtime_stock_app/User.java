package realtime_stock_app.realtime_stock_app;

import java.io.IOException;

public class User{
    private String userID;
    private String usrname;
    private String usrpwd;
    private String email;

    public User(String userID, String usrname, String usrpwd, String email) {
        this.userID = userID;
        this.usrname = usrname;
        this.usrpwd = usrpwd;
        this.email = email;
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

    public void buy(int quantity, String stock) throws IOException, InterruptedException {

        String[] arr = PolygonService.getPriceInfo(stock);
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