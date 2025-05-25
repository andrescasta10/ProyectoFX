package org.example.proyectofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import org.example.proyectofx.controller.BibliotecaController;
import org.example.proyectofx.model.Biblioteca;
import org.example.proyectofx.model.Usuario;

public class App extends Application {
    private Stage primaryStage;
    public static Biblioteca biblioteca = new Biblioteca("UQ");

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion de Clientes");
        openViewPrincipal();
    }
    public static void main(String[] args){
        launch();
    }

    private void openViewPrincipal() {
        inicializarData();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("primary.fxml"));
            javafx.scene.layout.VBox rootLayout = (javafx.scene.layout.VBox) loader.load();
            BibliotecaController bibliotecaController = loader.getController();
            bibliotecaController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void inicializarData() {
        Usuario usuario = new Usuario("Leon",1,"leon@uq.com", "123", Boolean.FALSE);
        Biblioteca.verificarCredenciales(usuario);
    }
}