Team #10

Team Members: Ved Joshi, Nishit Oberoi, Divij Gupta

**Presentation Proposal:**
Divij Gupta: Worked om everinoment and technologies needed for project and prev works and problem/issue. 
Nishit Oberoi: Worked on functionlaitites and operation
Ved Joshi: Worked on functionlaitites and operation.

**On the project presentation:**
Divij presented on the slide show that we made collectively.
Nish presented the sign up and login functionality of the app.
Ved presented the search and portfolio functionalities of the app.

**Project Code:**
Divij Gupta: Stock class, user class, portfolio class, main application/controller, and polygon service API.
Nishit Oberoi: Login controller, buy controller, sell controller, signup controller, and fxml files for the scenes. 
Ved Joshi: User json file, welcome controller, signup and login controller, and fxml files. 

 **Stockify**

**State the problem/issue:**

The problem we aim to address is the need for a real-time stock tracking application that provides users with up-to-date information on stock prices, trends, and news. Many existing stock tracking applications suffer from delayed data updates, lack of user-friendly interfaces, or limited functionality. Our goal is to create a comprehensive solution that offers real-time data updates, intuitive user interfaces, and a wide range of features to assist users in making informed investment decisions.

**Previous Works:**

Several stock tracking applications exist in the market, such as Robinhood, Yahoo Finance, and Bloomberg Terminal. These platforms offer various features for tracking stocks, including real-time data updates, customizable watchlists, and news feeds [1][2][3].

**Assumptions/Operating Environments/Intended Usage:**

Users will require a stable internet connection to receive real-time updates. 

Intended users are investors and traders who rely on accurate and timely stock market information

**Diagrams**
-Check the github repo folder RealtimeStockApp

**High-Level Description of Solution:**

Our solution involves developing a real-time stock tracking application with a user-friendly interface, customizable watchlists, comprehensive stock data, and timely news updates. We plan to utilize java and javafx to create this application.

**Functionalities:**

Real-time Stock Price Updates: The application will fetch real-time closing stock prices from the previous day upon entering the stock ticker. 

Buy function: Upon searching a stock users can buy that stock by entering in a quatity. The stock (ticker, price per stock, total value of that stock owned) will appear on the user's portfolio.

Portfolio: The user's portfolio displays their owned stocks and a search bar to search for more stocks to buy. 

Sell Function: The sell function appears when stock is displayed on the portfolio (after buying). Upon clicking the sell button users can sell the desired amount. 

**Operations:**

User Registration/Login

Search for Stocks

Manage Portfolio

**Steps to run your code**

1. Clone the repository.
2. Go to Polygon API's website and generate an API Key: https://polygon.io/stocks
3. Create a class called "Environment" where you house your API key as a static variable.
4. Go to the MainApplication class and run the program.

**Snapshot of the running program:**

![Code Snapshot](https://github.com/GuptaDivij/CS151-Real-Time-Stock-App/blob/main/RealtimeStockApp/src/main/resources/realtime_stock_app/realtime_stock_app/CodeSnapshots/Screenshot%202024-05-22%20151011.png)
![Code Snapshot](https://github.com/GuptaDivij/CS151-Real-Time-Stock-App/blob/main/RealtimeStockApp/src/main/resources/realtime_stock_app/realtime_stock_app/CodeSnapshots/Screenshot%202024-05-22%20151022.png)
![Code Snapshot](https://github.com/GuptaDivij/CS151-Real-Time-Stock-App/blob/main/RealtimeStockApp/src/main/resources/realtime_stock_app/realtime_stock_app/CodeSnapshots/Screenshot%202024-05-22%20151054.png)
![Code Snapshot](https://github.com/GuptaDivij/CS151-Real-Time-Stock-App/blob/main/RealtimeStockApp/src/main/resources/realtime_stock_app/realtime_stock_app/CodeSnapshots/Screenshot%202024-05-22%20151104.png)
![Code Snapshot](https://github.com/GuptaDivij/CS151-Real-Time-Stock-App/blob/main/RealtimeStockApp/src/main/resources/realtime_stock_app/realtime_stock_app/CodeSnapshots/Screenshot%202024-05-22%20151127.png)
![Code Snapshot](https://github.com/GuptaDivij/CS151-Real-Time-Stock-App/blob/main/RealtimeStockApp/src/main/resources/realtime_stock_app/realtime_stock_app/CodeSnapshots/Screenshot%202024-05-22%20151142.png)
![Code Snapshot](https://github.com/GuptaDivij/CS151-Real-Time-Stock-App/blob/main/RealtimeStockApp/src/main/resources/realtime_stock_app/realtime_stock_app/CodeSnapshots/Screenshot%202024-05-22%20151156.png)
![Code Snapshot](https://github.com/GuptaDivij/CS151-Real-Time-Stock-App/blob/main/RealtimeStockApp/src/main/resources/realtime_stock_app/realtime_stock_app/CodeSnapshots/Screenshot%202024-05-22%20151207.png)
![Code Snapshot](https://github.com/GuptaDivij/CS151-Real-Time-Stock-App/blob/main/RealtimeStockApp/src/main/resources/realtime_stock_app/realtime_stock_app/CodeSnapshots/Screenshot%202024-05-22%20151252.png)


**References:**

Robinhood: https://robinhood.com/

Yahoo Finance: https://finance.yahoo.com/

Bloomberg Terminal: https://www.bloomberg.com/professional/solution/bloomberg-terminal/
