module com.example.bebidas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.json;


    opens com.example.bebidas to javafx.fxml;
    exports com.example.bebidas;
}