package org.example.proyectofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.example.proyectofx.model.Biblioteca;
import org.example.proyectofx.model.Docente;
import org.example.proyectofx.model.Usuario;

public class App extends Application {

    /**
     * Método que se encarga de iniciar el programa
     * @param stage Stage
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("/org/example/proyectofx/inicio.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Biblioteca");
        stage.setResizable(false);
        stage.show();
        inicializarData();
    }

    public static void main(String[] args){
        launch();
    }

    public void inicializarData() {

        Usuario docente = new Docente("Leon",1,"leon@uq.com", "123", false);
    }
}