package org.example.proyectofx.model;

public class LibroFisico extends Libro{

    public LibroFisico(int ID, String titulo, String autor, String genero, int anioPublicacion, Estado estado){
        super(ID, titulo, autor, genero, anioPublicacion, estado);
    }

    @Override
    public String toString() {
        return "LibroFisico{}";
    }
}
