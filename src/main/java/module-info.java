module org.example.proyectofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.proyectofx to javafx.fxml;
    exports org.example.proyectofx;
}