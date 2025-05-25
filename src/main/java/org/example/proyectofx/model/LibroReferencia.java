// LibroReferencia.java
package org.example.proyectofx.model;

public class LibroReferencia extends Libro {


    public LibroReferencia(int id, String titulo, String autor, String genero, int anioPublicacion) {
        super(id, titulo, autor, genero, anioPublicacion, Estado.REFERENCIA);

    }



    @Override
    public void setEstado(Estado estado) {
        // No permite cambiar el estado de un libro de referencia
        throw new UnsupportedOperationException("No se puede cambiar el estado de un Libro de Referencia");
    }

    @Override
    public String toString() {
        return "LibroReferencia{" +
                super.toString() +
                '}';
    }
}
