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
import org.example.proyectofx.model.*;

public class BibliotecarioLibroController {
    @FXML
    private Button Prestamosbtn;

    @FXML
    private Button actualizarempleadobtn;

    @FXML
    private Button agregarlibrobtn;

    @FXML
    private TextField autortxt;

    @FXML
    private TextField anioPublicaciontxt;

    @FXML
    private TextField editorialtxt;

    @FXML
    private Button eliminarempleadobtn;

    @FXML
    private TextField enlacetxt;

    @FXML
    private TextField estadotxt;

    @FXML
    private TextField formatotxt;

    @FXML
    private TextField generotxt;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField numeroPaginastxt;

    @FXML
    private TableColumn<Libro, String> tbcAutor;

    @FXML
    private TableColumn<Libro, Integer> tbcAnioPublicacion;

    @FXML
    private TableColumn<LibroFisico, String> tbcEditorial;

    @FXML
    private TableColumn<LibroDigital, String> tbcEnlace;

    @FXML
    private TableColumn<Libro, Estado> tbcEstado;

    @FXML
    private TableColumn<LibroDigital, Formato> tbcFormato;

    @FXML
    private TableColumn<Libro, String> tbcGenero;

    @FXML
    private TableColumn<Libro, Integer> tbcID;

    @FXML
    private TableColumn<LibroFisico, Integer> tbcNumeroPaginas;

    @FXML
    private TableColumn<Libro, String> tbcTitulo;

    @FXML
    private TableColumn<LibroFisico, String> tbcUbicacionEstanteria;

    @FXML
    private TableView<Libro> tblListLibros;

    @FXML
    private TextField titulotxt;

    @FXML
    private TextField ubicacionEstanteriatxt;

    ObservableList<Libro> listaLibros = FXCollections.observableArrayList();
    Libro selectedLibro;
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
        obtenerLibros();

        // Limpiar la tabla
        tblListLibros.getItems().clear();

        // Agregar los elementos a la tabla
        tblListLibros.setItems(listaLibros);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    private void initDataBinding() {
        tbcID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getID()).asObject());
        tbcAutor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAutor()));
        // Usamos SimpleObjectProperty para manejar Double y Integer correctamente
    }

    private void obtenerLibros() {
        listaLibros.addAll(biblioteca.getListaLibros());
    }

    private void listenerSelection() {
        tblListLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedLibro = newSelection;
            mostrarInformacionLibro(selectedLibro);
        });
    }

    private void mostrarInformacionLibro(Libro libro) {
        if (libro != null) {
            idtxt.setText(libro.getID()+"");
            autortxt.setText(libro.getAutor());
            anioPublicaciontxt.setText(libro.getAnioPublicacion()+"");
            titulotxt.setText(libro.getTitulo());
            generotxt.setText(libro.getGenero());
            estadotxt.setText(libro.getEstado()+"");
        }
    }

    @FXML
    void onActualizarlibro(ActionEvent event) {

    }

    @FXML
    void onAgregarlibro(ActionEvent event) {

    }

    @FXML
    void onEliminarlibro(ActionEvent event) {

    }

    @FXML
    void onPrestamosLibro(ActionEvent event) {

    }
}
