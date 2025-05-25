package org.example.proyectofx.controller;

import org.example.proyectofx.App;
import org.example.proyectofx.model.Biblioteca;
import org.example.proyectofx.model.Usuario;


public class BibliotecaController {
    Biblioteca biblioteca;

    public BibliotecaController(Biblioteca biblioteca){
        this.biblioteca = biblioteca;
    }
    public boolean verificarCredencial(Usuario usuario){
        return Biblioteca.verificarCredenciales(usuario);
    }

    public void setApp(App app) {

    }
}

