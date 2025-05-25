package org.example.proyectofx.controller;

import org.example.proyectofx.App;
import org.example.proyectofx.model.Biblioteca;
import org.example.proyectofx.model.Usuario;


public class BibliotecaController {
    Biblioteca biblioteca;
    private App app;

    public BibliotecaController() {

    }
    public boolean verificarCredencial(Usuario usuario){
        return Biblioteca.verificarCredenciales(usuario);
    }

    public void setApp(App app) {
        this.app = app;
    }
}

