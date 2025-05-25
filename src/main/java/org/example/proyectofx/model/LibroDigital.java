package org.example.proyectofx.model;

public class LibroDigital extends Libro{

    public LibroDigital(int ID, String titulo, String autor, String genero, int anioPublicacion, Estado estado){
        super(ID, titulo, autor, genero, anioPublicacion, estado);
    }

    @Override
    public String toString() {
        return "LibroDigital{}";
    }
}
