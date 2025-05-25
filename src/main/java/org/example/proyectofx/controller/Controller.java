package org.example.proyectofx.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import lombok.Getter;
import org.example.proyectofx.model.Biblioteca;

public class Controller {
    @Getter
    public static Biblioteca biblioteca;

    public static Controller INSTANCIA;
    private Controller (){
        biblioteca = new Biblioteca("UQ");
    }
    public static Controller getInstancia(){
        if (INSTANCIA == null){
            return new Controller();
        }
        return INSTANCIA;
    }


    /**
     * Método que permite ir a la venana indicada por el nombre del archivo FXML
     * @param nombreArchivoFxml Nombre del archivo FXML
     * @param tituloVentana Título de la ventana
     */
    public void navegarVentana(Button button, String nombreArchivoFxml, String tituloVentana) {
        try {
            // Cerrar ventana actual
            Stage stageClose = (Stage) button.getScene().getWindow();
            stageClose.close();

            // Cargar la nueva vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            // Crear y mostrar nueva ventana
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que se encarga de mostrar una alerta en pantalla
     * @param mensaje mensaje a mostrar
     * @param tipo tipo de alerta
     */
    public void crearAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
