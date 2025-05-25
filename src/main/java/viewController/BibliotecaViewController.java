package viewController;

import java.net.URL;
import java.util.ResourceBundle;

import org.example.proyectofx.App;
import org.example.proyectofx.controller.BibliotecaController;
import org.example.proyectofx.model.Biblioteca;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.proyectofx.model.Usuario;

//import static org.example.proyectofx.controller.BibliotecaController.verificarCredencial;

public class BibliotecaViewController {
    BibliotecaController bibliotecaController;
    ObservableList<Usuario> listUsuarios = FXCollections.observableArrayList();
    Usuario selectedUsuario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public void onVerificarCredencial(){
        bibliotecaController.verificarCredencial(selectedUsuario);
    }

    @FXML
    App app;
    void initialize() {

    }
    public void setApp(App app){
        this.app = app;
    }



}
