module com.example.bank2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bank2 to javafx.fxml;
    exports com.example.bank2;
}