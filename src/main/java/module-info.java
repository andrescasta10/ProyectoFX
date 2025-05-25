module org.example.proyectofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires static lombok;


    opens org.example.proyectofx to javafx.fxml;
    exports org.example.proyectofx;
}