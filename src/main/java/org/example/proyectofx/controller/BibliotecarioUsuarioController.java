package org.example.proyectofx.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectofx.model.*;

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
    private ComboBox<String> deudacbox;

    @FXML
    private Button eliminarUsuariobtn;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField nombretxt;

    @FXML
    private ComboBox<TipoUsuario> tipousuariocbox;

    @FXML
    private TableColumn<Usuario, String> tbcContrasenia;

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

        // Carga las opciones del combo box
        cargarOpcionesComboBox();

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
        tbcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        tbcContrasenia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContrasenia()));
        tbcDeuda.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDeuda()));
        // Usamos SimpleObjectProperty para manejar Double y Integer correctamente
    }

    private void cargarOpcionesComboBox(){
        TipoUsuario[] tipoUsuarios = TipoUsuario.values();
        for (TipoUsuario tipoUsuario : tipoUsuarios){
            tipousuariocbox.getItems().add(tipoUsuario);
        }
        deudacbox.getItems().add("Si");
        deudacbox.getItems().add("No");
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
            deudacbox.setValue(usuario.getDeuda() ? "Si": "No");
        }
    }

    private void limpiarSeleccion() {
        tblListUsuarios.getSelectionModel().clearSelection();
        limpiarCamposEmpleado();
    }

    private void limpiarCamposEmpleado() {
        idtxt.clear();
        nombretxt.clear();
        correotxt.clear();
        contraseniatxt.clear();
        deudacbox.getSelectionModel().clearSelection();
    }

    @FXML
    void onActualizarUsuario(ActionEvent event) {
        Usuario usuario = tblListUsuarios.getSelectionModel().getSelectedItem();
        if (usuario == null){
            controller.crearAlerta("Debe seleccionar un usuario", Alert.AlertType.WARNING);
            return;
        }
        String idString = idtxt.getText();
        String nombre = nombretxt.getText();
        String correo = correotxt.getText();
        String contrasenia = contraseniatxt.getText();
        String deudaString = deudacbox.getSelectionModel().getSelectedItem();
        int id = 0;
        try{
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e){
            controller.crearAlerta("Verifique el ID debe ser un número", Alert.AlertType.ERROR);
        }
        if (nombre.isEmpty() || correo.isEmpty() || contrasenia.isEmpty() || deudaString.isEmpty()){
            controller.crearAlerta("Verifique los campos", Alert.AlertType.ERROR);
        }
        else{
            boolean deuda = deudaString.equals("Si") && deudaString != null ? true : false;
            if (usuario instanceof Docente){
                Usuario docente = new Docente(nombre, id, correo, contrasenia, deuda);
                try {
                    biblioteca.modificarUsuario(usuario.getId(), docente);
                    listaUsuarios.setAll(biblioteca.getListaUsuarios());
                    tblListUsuarios.setItems(listaUsuarios);
                    controller.crearAlerta("Se ha actualizado el docente correctamente", Alert.AlertType.INFORMATION);
                    limpiarSeleccion();
                } catch (Exception e) {
                    controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            } else if (usuario instanceof Estudiante) {
                Usuario estudiante = new Estudiante(nombre, id, correo, contrasenia, deuda);
                try {
                    biblioteca.modificarUsuario(usuario.getId(), estudiante);
                    listaUsuarios.setAll(biblioteca.getListaUsuarios());
                    tblListUsuarios.setItems(listaUsuarios);
                    controller.crearAlerta("Se ha actualizado el estudiante correctamente", Alert.AlertType.INFORMATION);
                    limpiarSeleccion();
                } catch (Exception e) {
                    controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            } else if (usuario instanceof Visitante) {
                Visitante visitante = new Visitante(nombre, id, correo, contrasenia, deuda);
                try {
                    biblioteca.modificarUsuario(usuario.getId(), visitante);
                    listaUsuarios.setAll(biblioteca.getListaUsuarios());
                    tblListUsuarios.setItems(listaUsuarios);
                    controller.crearAlerta("Se ha actualizado el visitante correctamente", Alert.AlertType.INFORMATION);
                    limpiarSeleccion();
                } catch (Exception e) {
                    controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        }
    }

    @FXML
    void onAgregarUsuario(ActionEvent event) {
        TipoUsuario tipoUsuario = tipousuariocbox.getSelectionModel().getSelectedItem();
        if (tipoUsuario == null){
            controller.crearAlerta("Primero debe seleccionar un tipo de usuario", Alert.AlertType.WARNING);
            return;
        }
        String idString = idtxt.getText();
        String nombre = nombretxt.getText();
        String correo = correotxt.getText();
        String contrasenia = contraseniatxt.getText();
        String deudaString = deudacbox.getSelectionModel().getSelectedItem();
        int id = 0;
        try{
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e){
            controller.crearAlerta("Verifique el ID debe ser un número", Alert.AlertType.ERROR);
        }
        if (nombre.isEmpty() || correo.isEmpty() || contrasenia.isEmpty() || deudaString.isEmpty()){
            controller.crearAlerta("Verifique los campos", Alert.AlertType.ERROR);
        }
        else {
            boolean deuda = deudaString.equals("Si") && deudaString != null ? true : false;
            if (tipoUsuario.equals(TipoUsuario.DOCENTE)){
                Usuario docente = new Docente(nombre, id, correo, contrasenia, deuda);
                try {
                    biblioteca.registrarUsuario(docente);
                    listaUsuarios.add(docente);
                    tblListUsuarios.setItems(listaUsuarios);
                    controller.crearAlerta("Se ha agregado el docente correctamente", Alert.AlertType.INFORMATION);
                    limpiarSeleccion();
                } catch (Exception e) {
                    controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            } else if (tipoUsuario.equals(TipoUsuario.ESTUDIANTE)) {
                Usuario estudiante = new Estudiante(nombre, id, correo, contrasenia, deuda);
                try {
                    biblioteca.registrarUsuario(estudiante);
                    listaUsuarios.add(estudiante);
                    tblListUsuarios.setItems(listaUsuarios);
                    controller.crearAlerta("Se ha agregado el estudiante correctamente", Alert.AlertType.INFORMATION);
                    limpiarSeleccion();
                } catch (Exception e) {
                    controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            } else if (tipoUsuario.equals(TipoUsuario.VISITANTE)) {
                Usuario visitante = new Visitante(nombre, id, correo, contrasenia, deuda);
                try {
                    biblioteca.registrarUsuario(visitante);
                    listaUsuarios.add(visitante);
                    tblListUsuarios.setItems(listaUsuarios);
                    controller.crearAlerta("Se ha agregado el visitante correctamente", Alert.AlertType.INFORMATION);
                    limpiarSeleccion();
                } catch (Exception e) {
                    controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        }
    }

    @FXML
    void onEliminarUsuario(ActionEvent event) {
        Usuario usuario = tblListUsuarios.getSelectionModel().getSelectedItem();
        if (usuario == null){
            controller.crearAlerta("Debe seleccionar un bibliotecario", Alert.AlertType.WARNING);
        }
        else {
            listaUsuarios.remove(usuario);
            tblListUsuarios.setItems(listaUsuarios);
            controller.crearAlerta("Se ha eliminado el bibliotecario correctamente", Alert.AlertType.INFORMATION);
            limpiarSeleccion();
            try{
                biblioteca.eliminarUsuario(usuario.getId());
            }
            catch (Exception e){
                controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
}
