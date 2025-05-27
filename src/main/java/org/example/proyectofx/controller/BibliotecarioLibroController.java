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
            tipolibrocbox.setDisable(true);
            idtxt.setText(libro.getID()+"");
            autortxt.setText(libro.getAutor());
            anioPublicaciontxt.setText(libro.getAnioPublicacion()+"");
            titulotxt.setText(libro.getTitulo());
            generotxt.setText(libro.getGenero());
            estadocbox.setValue(libro.getEstado());
            switch (libro) {
                case LibroFisico libroFisico -> {
                    numeroPaginastxt.setText(libroFisico.getNumeroPaginas() + "");
                    editorialtxt.setText(libroFisico.getEditorial());
                    ubicacionEstanteriatxt.setText(libroFisico.getUbicacionEstanteria());
                    tipolibrocbox.setValue(TipoLibro.FISICO);
                }
                case LibroDigital libroDigital -> {
                    formatocbox.setValue(libroDigital.getFormato());
                    enlacetxt.setText(libroDigital.getEnlaceDescarga());
                    tipolibrocbox.setValue(TipoLibro.DIGITAL);
                }
                case LibroReferencia libroReferencia -> tipolibrocbox.setValue(TipoLibro.REFERENCIA);
                default -> {
                    controller.crearAlerta("Ha ocurrido un errror", Alert.AlertType.ERROR);
                }
            }
        }
    }

    private void limpiarSeleccion() {
        tblListLibros.getSelectionModel().clearSelection();
        limpiarCamposLibro();
    }

    private void limpiarCamposLibro() {
        idtxt.clear();
        autortxt.clear();
        anioPublicaciontxt.clear();
        titulotxt.clear();
        generotxt.clear();
        estadocbox.getSelectionModel().clearSelection();
        numeroPaginastxt.clear();
        editorialtxt.clear();
        ubicacionEstanteriatxt.clear();
        formatocbox.getSelectionModel().clearSelection();
        enlacetxt.clear();
    }

    @FXML
    void onActualizarlibro(ActionEvent event) {
        Libro libro = tblListLibros.getSelectionModel().getSelectedItem();
        if (libro == null){
            controller.crearAlerta("Debe seleccionar un libro", Alert.AlertType.WARNING);
            return;
        }
        String idString = idtxt.getText();
        String anioPublicacionString = anioPublicaciontxt.getText();
        String titulo = titulotxt.getText();
        String autor = autortxt.getText();
        String genero = generotxt.getText();
        Estado estado = estadocbox.getSelectionModel().getSelectedItem();
        int id = 0;
        int anioPublicacion = 0;
        try{
            id = Integer.parseInt(idString);
            anioPublicacion = Integer.parseInt(anioPublicacionString);
        }
        catch (NumberFormatException e){
            controller.crearAlerta("Verifique el ID o el año de publicación deben ser un número", Alert.AlertType.ERROR);
        }
        if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || estado == null){
            controller.crearAlerta("Verifique los campos", Alert.AlertType.ERROR);
        }
        else {
            if (libro instanceof LibroFisico){
                String numPagString = numeroPaginastxt.getText();
                String editorial = editorialtxt.getText();
                String ubicacion = ubicacionEstanteriatxt.getText();
                int numPag = 0;
                try{
                    numPag = Integer.parseInt(numPagString);
                } catch (NumberFormatException e) {
                    controller.crearAlerta("Verifique el número de páginas debe ser un número", Alert.AlertType.ERROR);
                }
                if (editorial.isEmpty() || ubicacion.isEmpty()){
                    controller.crearAlerta("Verifique los campos", Alert.AlertType.ERROR);
                }
                else {
                    Libro libroEditado = new LibroFisico(id, titulo, autor, genero, anioPublicacion, estado, numPag, editorial, ubicacion);
                    try {
                        biblioteca.modificarLibro(libro.getID(), libroEditado);
                        listaLibros.setAll(biblioteca.getListaLibros());
                        tblListLibros.setItems(listaLibros);
                        controller.crearAlerta("Se ha actualizado el libro fisico correctamente", Alert.AlertType.INFORMATION);
                        limpiarSeleccion();
                    } catch (Exception e) {
                        controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                    }
                }
            } else if (libro instanceof LibroDigital) {
                Formato formato = formatocbox.getSelectionModel().getSelectedItem();
                String enlace = enlacetxt.getText();
                if (formato == null || enlace.isEmpty()){
                    controller.crearAlerta("Verifique los campos", Alert.AlertType.ERROR);
                }
                else {
                    Libro libroEditado = new LibroDigital(id, titulo, autor, genero, anioPublicacion, estado, formato, enlace);
                    try {
                        biblioteca.modificarLibro(libro.getID(), libroEditado);
                        listaLibros.setAll(biblioteca.getListaLibros());
                        tblListLibros.setItems(listaLibros);
                        controller.crearAlerta("Se ha actualizado el libro digital correctamente", Alert.AlertType.INFORMATION);
                        limpiarSeleccion();
                    } catch (Exception e) {
                        controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                    }
                }
            } else if (libro instanceof LibroReferencia) {
                Libro libroEditado = new LibroReferencia(id, titulo, autor, genero, anioPublicacion);
                try {
                    biblioteca.modificarLibro(libro.getID(), libroEditado);
                    listaLibros.setAll(biblioteca.getListaLibros());
                    tblListLibros.setItems(listaLibros);
                    controller.crearAlerta("Se ha actualizado el libro digital correctamente", Alert.AlertType.INFORMATION);
                    limpiarSeleccion();
                } catch (Exception e) {
                    controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        }

    }

    @FXML
    void onAgregarlibro(ActionEvent event) {
        TipoLibro tipoLibro = tipolibrocbox.getSelectionModel().getSelectedItem();
        if (tipoLibro == null){
            controller.crearAlerta("Primero debe seleccionar un tipo de libro", Alert.AlertType.WARNING);
            return;
        }
        String idString = idtxt.getText();
        String anioPublicacionString = anioPublicaciontxt.getText();
        String titulo = titulotxt.getText();
        String autor = autortxt.getText();
        String genero = generotxt.getText();
        Estado estado = estadocbox.getSelectionModel().getSelectedItem();
        int id = 0;
        int anioPublicacion = 0;
        try{
            id = Integer.parseInt(idString);
            anioPublicacion = Integer.parseInt(anioPublicacionString);
        }
        catch (NumberFormatException e){
            controller.crearAlerta("Verifique el ID y el año de publicación deben ser un número", Alert.AlertType.ERROR);
        }
        if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || estado == null){
            controller.crearAlerta("Verifique los campos", Alert.AlertType.ERROR);
        }
        else {
            if (tipoLibro.equals(TipoLibro.FISICO)){
                String numPagString = numeroPaginastxt.getText();
                String editorial = editorialtxt.getText();
                String ubicacion = ubicacionEstanteriatxt.getText();
                int numPag = 0;
                try{
                    numPag = Integer.parseInt(numPagString);
                } catch (NumberFormatException e) {
                    controller.crearAlerta("Verifique el número de páginas debe ser un número", Alert.AlertType.ERROR);
                }
                if (editorial.isEmpty() || ubicacion.isEmpty()){
                    controller.crearAlerta("Verifique los campos", Alert.AlertType.ERROR);
                }
                else {
                    Libro libro = new LibroFisico(id, titulo, autor, genero, anioPublicacion, estado, numPag, editorial, ubicacion);
                    try {
                        biblioteca.registrarLibro(libro);
                        listaLibros.add(libro);
                        tblListLibros.setItems(listaLibros);
                        controller.crearAlerta("Se ha agregado el libro fisico correctamente", Alert.AlertType.INFORMATION);
                        limpiarSeleccion();
                    } catch (Exception e) {
                        controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                    }
                }

            } else if (tipoLibro.equals(TipoLibro.DIGITAL)) {
                Formato formato = formatocbox.getSelectionModel().getSelectedItem();
                String enlace = enlacetxt.getText();
                if (formato == null || enlace.isEmpty()){
                    controller.crearAlerta("Verifique los campos", Alert.AlertType.ERROR);
                }
                else {
                    Libro libro = new LibroDigital(id, titulo, autor, genero, anioPublicacion, estado, formato, enlace);
                    try {
                        biblioteca.registrarLibro(libro);
                        listaLibros.add(libro);
                        tblListLibros.setItems(listaLibros);
                        controller.crearAlerta("Se ha agregado el libro digital correctamente", Alert.AlertType.INFORMATION);
                        limpiarSeleccion();
                    } catch (Exception e) {
                        controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                    }
                }
            } else if (tipoLibro.equals(TipoLibro.REFERENCIA)) {
                Libro libro = new LibroReferencia(id, titulo, autor, genero, anioPublicacion);
                try {
                    biblioteca.registrarLibro(libro);
                    listaLibros.add(libro);
                    tblListLibros.setItems(listaLibros);
                    controller.crearAlerta("Se ha agregado el libro digital correctamente", Alert.AlertType.INFORMATION);
                    limpiarSeleccion();
                } catch (Exception e) {
                    controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        }
    }

    @FXML
    void onEliminarlibro(ActionEvent event) {
        Libro libros = tblListLibros.getSelectionModel().getSelectedItem();
        if (libros == null){
            controller.crearAlerta("Debe seleccionar un libro", Alert.AlertType.WARNING);
        }
        else {
            listaLibros.remove(libros);
            tblListLibros.setItems(listaLibros);
            controller.crearAlerta("Se ha eliminado el libro correctamente", Alert.AlertType.INFORMATION);
            limpiarSeleccion();
            try{
                biblioteca.eliminarLibro(libros.getID());
            }
            catch (Exception e){
                controller.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void onPrestamosLibro(ActionEvent event) {
        controller.navegarVentana(Prestamosbtn,"/org/example/proyectofx/PrestamoBibliotecario.fxml", "Prestamo Bibliotecario");
    }
}
