module yt.filter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens yt.filter to javafx.fxml;
    exports yt.filter;
}
