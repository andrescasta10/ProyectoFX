package org.example.proyectofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioController {
    @FXML
    private Button administradorbtn;

    @FXML
    private Button bibliotecariobtn;

    @FXML
    private Button usuariobtn;

    private final Controller controller = Controller.getInstancia();

    @FXML
    void onOpenInicioAdministrador(ActionEvent event) {
        controller.navegarVentana(administradorbtn,"/org/example/proyectofx/Administrador.fxml", "Ventana Administrador");
    }

    @FXML
    void onOpenInicioUsuario(ActionEvent event) {
        controller.navegarVentana(usuariobtn,"/org/example/proyectofx/inicioUsuario.fxml","Inicio Sesion");
    }

    @FXML
    void onOpenInicioBibliotecario(ActionEvent event) {
        controller.navegarVentana(bibliotecariobtn,"/org/example/proyectofx/inicioBibliotecario.fxml","Inicio Bibliotecario");
    }
}
