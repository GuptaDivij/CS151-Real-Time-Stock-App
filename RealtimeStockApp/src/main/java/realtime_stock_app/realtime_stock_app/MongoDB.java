package realtime_stock_app.realtime_stock_app;
import com.mongodb.*;

public class MongoDB {
    public static MongoClient mongoClient;
    public static DB database;
    public static DBCollection users;

    public static void main(String[] args) {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDB("StockAppUsers");
        users = database.getCollection("Users");

    }

    public static DBObject convert(Portfolio portfolio, Stock stock) {
        return new BasicDBObject("User", "TODO: Add userID").append("Portfolio", portfolio.getPortfolio());
    }
}

