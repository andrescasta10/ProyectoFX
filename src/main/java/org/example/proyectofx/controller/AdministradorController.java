package org.example.proyectofx.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectofx.model.Biblioteca;
import org.example.proyectofx.model.Bibliotecario;
import org.example.proyectofx.model.Empleado;

public class AdministradorController {
    @FXML
    private Button actualizarempleadobtn;

    @FXML
    private Button agregarempleadobtn;

    @FXML
    private Button eliminarempleadobtn;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField nombretxt;

    @FXML
    private TableColumn<Empleado, Integer> tbcID;

    @FXML
    private TableColumn<Empleado, String> tbcNombre;

    @FXML
    private TableView<Empleado> tblListEmpleados;

    ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList();
    Empleado selectedEmpleado;
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
        obtenerEmpleados();

        // Limpiar la tabla
        tblListEmpleados.getItems().clear();

        // Agregar los elementos a la tabla
        tblListEmpleados.setItems(listaEmpleados);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    private void initDataBinding() {
        tbcID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        // Usamos SimpleObjectProperty para manejar Double y Integer correctamente
    }

    private void obtenerEmpleados() {
        listaEmpleados.addAll(biblioteca.getListaEmpleados());
    }

    private void listenerSelection() {
        tblListEmpleados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedEmpleado = newSelection;
            mostrarInformacionEmpleado(selectedEmpleado);
        });
    }

    private void mostrarInformacionEmpleado(Empleado empleado) {
        if (empleado != null) {
            idtxt.setText(empleado.getId()+"");
            nombretxt.setText(empleado.getNombre());
        }
    }

    private void limpiarSeleccion() {
        tblListEmpleados.getSelectionModel().clearSelection();
        limpiarCamposEmpleado();
    }

    private void limpiarCamposEmpleado() {
        idtxt.clear();
        nombretxt.clear();
    }

    @FXML
    void onActualizarEmpleado(ActionEvent event) {

        String idString = idtxt.getText();
        String nombre = nombretxt.getText();
        Empleado empleado = tblListEmpleados.getSelectionModel().getSelectedItem();
        if (empleado == null){
            controller.crearAlerta("Seleccione el bibliotecario que desea actualizar", Alert.AlertType.WARNING);
            return;
        }
        int id = 0;
        try{
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e){
            controller.crearAlerta("Verifique el ID debe ser un número", Alert.AlertType.ERROR);
        }
        if (nombre.isEmpty()){
            controller.crearAlerta("Verifique los campos", Alert.AlertType.ERROR);
        }
        else {
            Empleado empleadoModificado = new Bibliotecario(id, nombre);
            try {
                biblioteca.modificarEmpleado(empleado.getId(), empleadoModificado);
                listaEmpleados.setAll(biblioteca.getListaEmpleados());
                tblListEmpleados.setItems(listaEmpleados);
                controller.crearAlerta("Se ha actualizado el bibliotecario correctamente", Alert.AlertType.INFORMATION);
                limpiarSeleccion();
            } catch (Exception e) {
                controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void onAgregarEmpleado(ActionEvent event) {
        String idString = idtxt.getText();
        String nombre = nombretxt.getText();
        int id = 0;
        try{
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e){
            controller.crearAlerta("Verifique el ID debe ser un número", Alert.AlertType.ERROR);
        }
        if (nombre.isEmpty()){
            controller.crearAlerta("Verifique los campos", Alert.AlertType.ERROR);
        }
        else {
            Empleado empleado = new Bibliotecario(id, nombre);
            try {
                biblioteca.registrarEmpleado(empleado);
                listaEmpleados.add(empleado);
                tblListEmpleados.setItems(listaEmpleados);
                controller.crearAlerta("Se ha agregado el bibliotecario correctamente", Alert.AlertType.CONFIRMATION);
            } catch (Exception e) {
                controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void onEliminarEmpleado(ActionEvent event) {
        Empleado empleado = tblListEmpleados.getSelectionModel().getSelectedItem();
        if (empleado == null){
            controller.crearAlerta("Debe seleccionar un bibliotecario", Alert.AlertType.WARNING);
        }
        else {
            listaEmpleados.remove(empleado);
            tblListEmpleados.setItems(listaEmpleados);
            controller.crearAlerta("Se ha eliminado el bibliotecario correctamente", Alert.AlertType.CONFIRMATION);
            try{
                biblioteca.eliminarEmpleado(empleado.getId());
            }
            catch (Exception e){
                controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

}
