module org.example.proyectofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires static lombok;


    opens org.example.proyectofx to javafx.fxml;
    exports org.example.proyectofx;
    exports org.example.proyectofx.controller;

    opens org.example.proyectofx.controller to javafx.fxml;
}