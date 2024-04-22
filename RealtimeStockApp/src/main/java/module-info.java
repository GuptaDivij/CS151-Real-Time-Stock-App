module realtime_stock_app.realtime_stock_app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;

    opens realtime_stock_app.realtime_stock_app to javafx.fxml;
    exports realtime_stock_app.realtime_stock_app;
}