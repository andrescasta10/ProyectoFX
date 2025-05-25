package org.example.proyectofx.model;

public class LibroReferencia extends Libro{

    public LibroReferencia(int ID, String titulo, String autor, String genero, int anioPublicacion, Estado estado){
        super(ID, titulo, autor, genero, anioPublicacion, estado);
    }

    @Override
    public String toString() {
        return "LibroReferencia{}";
    }
}
