package org.example.proyectofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioBibliotecarioController {
    @FXML
    private Button librosbtn;

    @FXML
    private Button usuariosbtn;

    private final Controller controller = Controller.getInstancia();

    @FXML
    void onOpenBibliotecarioLibro(ActionEvent event) {
        controller.navegarVentana(librosbtn,"/org/example/proyectofx/BibliotecarioLibro.fxml", "Bibliotecario Libros");
    }

    @FXML
    void onOpenBibliotecarioUsuario(ActionEvent event) {
        controller.navegarVentana(usuariosbtn,"/org/example/proyectofx/BibliotecarioUsuario.fxml", "Bibliotecario Usuario");
    }
}
