package org.example.proyectofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.proyectofx.App;
import org.example.proyectofx.model.Biblioteca;
import org.example.proyectofx.model.Sesion;
import org.example.proyectofx.model.Usuario;

public class InicioUsuarioController {

    @FXML
    private PasswordField contratxt;

    @FXML
    private TextField correotxt;

    @FXML
    private Button ingesarbtn;

    @FXML
    public void initialize() {
        // Inicialización si es necesaria
    }

    private final Controller controller = Controller.getInstancia();
    private final Biblioteca biblioteca = Controller.getBiblioteca();
    private final Sesion sesion = Sesion.getInstancia();

    @FXML
    public void onVerificarCredencial() {
        String correo = correotxt.getText();
        String password = contratxt.getText();

        try {
            Usuario usuario = biblioteca.verificarCredenciales(correo, password);
            if (usuario != null){
                sesion.setUsuario(usuario);
                controller.crearAlerta("Se ha iniciado sesion correctamente", Alert.AlertType.CONFIRMATION);
            }
        }
        catch (Exception e){
            controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}