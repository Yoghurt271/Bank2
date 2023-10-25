module com.example.bank2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bank2 to javafx.fxml;
    exports com.example.bank2;
}