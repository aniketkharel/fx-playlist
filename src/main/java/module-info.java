module com.np {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.np to javafx.fxml;
    exports com.np;
}