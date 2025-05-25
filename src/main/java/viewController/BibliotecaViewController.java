package viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.proyectofx.App;
import org.example.proyectofx.controller.BibliotecaController;
import org.example.proyectofx.model.Usuario;

public class BibliotecaViewController {

    private BibliotecaController bibliotecaController = new BibliotecaController(); // Constructor sin argumentos
    private App app;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button primaryButton;

    @FXML
    public void initialize() {
        // Inicialización si es necesaria
    }

    @FXML
    public void onVerificarCredencial() {
        String correo = txtCorreo.getText();
        String password = txtPassword.getText();

        Usuario usuario = new Usuario("Desconocido", 0, correo, password, false);
        boolean credencialValida = bibliotecaController.verificarCredencial(usuario);

        if (credencialValida) {
            System.out.println("¡Inicio de sesión exitoso!");
        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }
    }

    public void setApp(App app) {
        this.app = app;
    }
}