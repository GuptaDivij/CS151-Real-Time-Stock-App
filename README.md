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

 **Real Time Stock App**

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

Add Stocks to Watchlist

View Real-Time Stock Prices

Access News Feed

Perform Technical Analysis

Manage Portfolio

**Steps to run your code**

1. Clone the repository.
2. Go to Polygon API's website and generate an API Key: https://polygon.io/stocks?utm_term=polygon.io&utm_campaign=Brand+-+ALL+(Conv+Value+tROAS)&utm_source=adwords&utm_medium=ppc&hsa_acc=4299129556&hsa_cam=14536485495&hsa_grp=132004734661&hsa_ad=591202818400&hsa_src=g&hsa_tgt=kwd-812066473604&hsa_kw=polygon.io&hsa_mt=p&hsa_net=adwords&hsa_ver=3&gad_source=1&gclid=Cj0KCQjwjLGyBhCYARIsAPqTz19ilAXY25bo-cAJwQBs7dre-3_Z10Y_kigq53vj-2ln8-wkGWBgN3UaAg0ZEALw_wcB
3. Create a class called "Environment" where you house your API key as a static variable.
4. Go to the MainApplication class and run the program.

**References:**

Robinhood: https://robinhood.com/

Yahoo Finance: https://finance.yahoo.com/

Bloomberg Terminal: https://www.bloomberg.com/professional/solution/bloomberg-terminal/
