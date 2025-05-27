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

public class BibliotecarioLibroController {
    @FXML
    private Button Prestamosbtn;

    @FXML
    private Button actualizarempleadobtn;

    @FXML
    private Button agregarlibrobtn;

    @FXML
    private TextField anioPublicaciontxt;

    @FXML
    private TextField autortxt;

    @FXML
    private TextField editorialtxt;

    @FXML
    private Button eliminarempleadobtn;

    @FXML
    private TextField enlacetxt;

    @FXML
    private ComboBox<Estado> estadocbox;

    @FXML
    private ComboBox<Formato> formatocbox;

    @FXML
    private TextField generotxt;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField numeroPaginastxt;

    @FXML
    private TableColumn<Libro, Integer> tbcAnioPublicacion;

    @FXML
    private TableColumn<Libro, String> tbcAutor;

    @FXML
    private TableColumn<Libro, Estado> tbcEstado;

    @FXML
    private TableColumn<Libro, String> tbcGenero;

    @FXML
    private TableColumn<Libro, Integer> tbcID;

    @FXML
    private TableColumn<Libro, String> tbcTitulo;

    @FXML
    private TableView<Libro> tblListLibros;

    @FXML
    private ComboBox<TipoLibro> tipolibrocbox;

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

        // Carga las opciones del combo box
        cargarOpcionesComboBox();

        // Obtiene la lista
        obtenerLibros();

        // Limpiar la tabla
        tblListLibros.getItems().clear();

        // Agregar los elementos a la tabla
        tblListLibros.setItems(listaLibros);

        // Seleccionar elemento de la tabla
        listenerSelection();

        //Metodo para resaltar casillas a rellenar
        listenerTipoLibro();
    }

    private void initDataBinding() {
        tbcID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getID()).asObject());
        tbcAnioPublicacion.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAnioPublicacion()).asObject());
        tbcTitulo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
        tbcAutor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAutor()));
        tbcGenero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGenero()));
        tbcEstado.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEstado()));
        // Usamos SimpleObjectProperty para manejar Double y Integer correctamente
    }

    private void cargarOpcionesComboBox(){
        Estado[] estados = Estado.values();
        for (Estado estado : estados){
            estadocbox.getItems().add(estado);
        }
        Formato[] formatos = Formato.values();
        for (Formato formato : formatos){
            formatocbox.getItems().add(formato);
        }
        TipoLibro[] tiposLibros = TipoLibro.values();
        for (TipoLibro tipoLibro : tiposLibros){
            tipolibrocbox.getItems().add(tipoLibro);
        }
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

    private void listenerTipoLibro() {
        tipolibrocbox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            resaltarCasillasTipoLibro();
        });
    }

    private void resaltarCasillasTipoLibro(){
        numeroPaginastxt.setDisable(false);
        editorialtxt.setDisable(false);
        ubicacionEstanteriatxt.setDisable(false);
        formatocbox.setDisable(false);
        enlacetxt.setDisable(false);
        TipoLibro tipoLibro = tipolibrocbox.getSelectionModel().getSelectedItem();
        if (tipoLibro.equals(TipoLibro.FISICO)){
            formatocbox.setDisable(true);
            enlacetxt.setDisable(true);
        } else if (tipoLibro.equals(TipoLibro.DIGITAL)) {
            numeroPaginastxt.setDisable(true);
            editorialtxt.setDisable(true);
            ubicacionEstanteriatxt.setDisable(true);
        } else if (tipoLibro.equals(TipoLibro.REFERENCIA)) {
            controller.crearAlerta("Tenga en cuenta que: \n" +
                    "El libro es de tipo REFERENCIA este no tiene datos propios y solo puede ser consultado", Alert.AlertType.WARNING);
        }
    }

    private void mostrarInformacionLibro(Libro libro) {
        if (libro != null) {
            if (libro instanceof LibroFisico)
            idtxt.setText(libro.getID()+"");
            autortxt.setText(libro.getAutor());
            anioPublicaciontxt.setText(libro.getAnioPublicacion()+"");
            titulotxt.setText(libro.getTitulo());
            generotxt.setText(libro.getGenero());
            estadotxt.setText(libro.getEstado()+"");
        }
    }

    private void limpiarSeleccion() {
        tblListLibros.getSelectionModel().clearSelection();
        limpiarCamposEmpleado();
    }

    private void limpiarCamposEmpleado() {
        idtxt.clear();
        autortxt.clear();
        anioPublicaciontxt.clear();
        titulotxt.clear();
        generotxt.clear();
        estadotxt.clear();
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
