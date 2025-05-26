package org.example.proyectofx.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.proyectofx.model.Biblioteca;
import org.example.proyectofx.model.Empleado;
import org.example.proyectofx.model.Usuario;

public class BibliotecarioUsuarioController {
    @FXML
    private Button actualizarUsuariobtn;

    @FXML
    private Button agregarUsuariotn;

    @FXML
    private TextField contraseniatxt;

    @FXML
    private TextField correotxt;

    @FXML
    private TextField deudatxt;

    @FXML
    private Button eliminarUsuariobtn;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField nombretxt;

    @FXML
    private TableColumn<Usuario, String> tbcContraseña;

    @FXML
    private TableColumn<Usuario, String> tbcCorreo;

    @FXML
    private TableColumn<Usuario, Boolean> tbcDeuda;

    @FXML
    private TableColumn<Usuario, Integer> tbcID;

    @FXML
    private TableColumn<Usuario, String> tbcNombre;

    @FXML
    private TableView<Usuario> tblListUsuarios;

    ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
    Usuario selectedUsuario;
    private final Controller controller = Controller.getInstancia();
    private final Biblioteca biblioteca = Controller.getBiblioteca();

    @FXML
    void initialize() {
        initView();
    }

    private void initView() {
        // Traer los datos del cliente a la tabla
        initDataBinding();

        // Obtiene la lista
        obtenerUsuarios();

        // Limpiar la tabla
        tblListUsuarios.getItems().clear();

        // Agregar los elementos a la tabla
        tblListUsuarios.setItems(listaUsuarios);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    private void initDataBinding() {
        tbcID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        // Usamos SimpleObjectProperty para manejar Double y Integer correctamente
    }

    private void obtenerUsuarios() {
        listaUsuarios.addAll(biblioteca.getListaUsuarios());
    }

    private void listenerSelection() {
        tblListUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedUsuario = newSelection;
            mostrarInformacionUsuario(selectedUsuario);
        });
    }

    private void mostrarInformacionUsuario(Usuario usuario) {
        if (usuario != null) {
            idtxt.setText(usuario.getId()+"");
            nombretxt.setText(usuario.getNombre());
            correotxt.setText(usuario.getCorreo());
            contraseniatxt.setText(usuario.getContrasenia());
            deudatxt.setText(usuario.getDeuda()+"");
        }
    }

    @FXML
    void onActualizarUsuario(ActionEvent event) {

    }

    @FXML
    void onAgregarUsuario(ActionEvent event) {

    }

    @FXML
    void onEliminarUsuario(ActionEvent event) {

    }
}
