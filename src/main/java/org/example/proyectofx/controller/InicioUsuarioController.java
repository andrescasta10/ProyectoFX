package org.example.proyectofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.proyectofx.App;
import org.example.proyectofx.model.Biblioteca;
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

    @FXML
    public void onVerificarCredencial() {
        String correo = correotxt.getText();
        String password = contratxt.getText();

        Usuario usuario = new Usuario("Desconocido", 0, correo, password, false);
        boolean credencialValida = biblioteca.verificarCredencial(usuario);

        if (credencialValida) {
            System.out.println("¡Inicio de sesión exitoso!");
        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }
    }
}