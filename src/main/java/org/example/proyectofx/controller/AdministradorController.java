package org.example.proyectofx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @FXML
    void onActualizarEmpleado(ActionEvent event) {

    }

    @FXML
    void onAgregarEmpleado(ActionEvent event) {

    }

    @FXML
    void onEliminarEmpleado(ActionEvent event) {

    }

}
