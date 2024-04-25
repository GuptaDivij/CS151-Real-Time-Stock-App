package realtime_stock_app.realtime_stock_app;

import com.mongodb.*;

import java.io.IOException;

import static realtime_stock_app.realtime_stock_app.MongoDB.*;

public class User{
    private final String userID;
    private String usrname;
    private String usrpwd;

    public User(String userID, String usrname, String usrpwd) {
        this.userID = userID;
        this.usrname = usrname;
        this.usrpwd = usrpwd;
        users.insert(new BasicDBObject("User ID", this.userID).append("Username", this.usrname).append("Password", this.usrpwd));
    }

    public String getUserID() {
        return userID;
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

    //methods

    public void buy(int quantity, String stock) throws IOException, InterruptedException {

        String[] arr = PolygonService.getPriceInfo(stock);
    }

    public void sell(int quantity, Stock stock){

    }

    public boolean login(String usrname, String usrpwd){
        DBObject query = new BasicDBObject("User ID", this.userID);
        DBCursor cursor = users.find(query);
        return usrname.equals(cursor.one().get("Username")) && usrpwd.equals(cursor.one().get("Password"));

    }

    public void logout(){

    }

    public static void main(String[] args) {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDB("StockAppUsers");
        users = database.getCollection("Users");
        User newUser = new User("ABCD", "hello", "world");
        System.out.println(newUser.login("hello", "world"));
    }
}